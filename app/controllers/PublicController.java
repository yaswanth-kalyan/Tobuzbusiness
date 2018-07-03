package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.avaje.ebean.ExpressionList;

import beans.CityBean;
import beans.CountryBean;
import beans.StateBean;
import constants.AlertType;
import models.AppUser;
import models.AreaMetrics;
import models.City;
import models.Country;
import models.FileEntity;
import models.State;
import play.Logger;
import play.Play;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.MultipartFormData.FilePart;
import play.mvc.Result;
import util.AppResponse;
import util.Constants;
import util.Constants.FolderRoots;
import util.StringUtils;

public class PublicController extends Controller{
	
	public Result getCountryData() {
		String defaultIsoCode = "";
		if(StringUtils.isNotEmpty(request().getQueryString("isoCode"))) {
			defaultIsoCode = request().getQueryString("isoCode").toUpperCase();
		}
		List<CountryBean> countryBeanList = new LinkedList<CountryBean>();
		List<Country> countryList = Country.countryList();
		for(Country country : countryList) {
			CountryBean bean = CountryBean.toCountryBean(country);
			if(defaultIsoCode.equalsIgnoreCase(country.getIsoCode())) {
				bean.isUserDefaultCountry = Boolean.TRUE;
			}
			countryBeanList.add(bean);
		}
		return ok(Json.toJson(countryBeanList));
	}
	
	public Result getCityData() {
		AppUser appUser = LoginController.getLoggedInUser();
		List<CityBean> cityBeanList = new LinkedList<CityBean>();
		List<City> cityList = City.cityList();
		for(City city : cityList) {
			CityBean bean = CityBean.toCityBean(city);
			if(appUser != null && appUser.getAddress() != null && appUser.getAddress().getCity() != null && appUser.getAddress().getCity().getId().longValue() == city.getId().longValue()) {
				bean.isUserDefaultCity = Boolean.TRUE;
			}
			cityBeanList.add(bean);
		}
		return ok(Json.toJson(cityBeanList));
	}
	
	public Result getStateData(Long countryId) {
		AppUser appUser = LoginController.getLoggedInUser();
		List<StateBean> cityBeanList = new LinkedList<StateBean>();
		List<State> stateList = State.find.where().eq("country", Country.find.byId(countryId)).eq("isActive",Boolean.TRUE).findList();
		for(State state : stateList) {
			StateBean bean = StateBean.toStateBean(state);
			cityBeanList.add(bean);
		}
		return ok(Json.toJson(cityBeanList));
	}
	
	public Result getCityDataByState(Long stateId) {
		AppUser appUser = LoginController.getLoggedInUser();
		List<CityBean> cityBeanList = new LinkedList<CityBean>();
		List<City> cityList = City.find.where().eq("state", State.find.byId(stateId)).eq("isActive", Boolean.TRUE).findList();
		for(City city : cityList) {
			CityBean bean = CityBean.toCityBean(city);
			if(appUser != null && appUser.getAddress() != null && appUser.getAddress().getCity() != null && appUser.getAddress().getCity().getId().longValue() == city.getId().longValue()) {
				bean.isUserDefaultCity = Boolean.TRUE;
			}
			cityBeanList.add(bean);
		}
		return ok(Json.toJson(cityBeanList));
	}
	
	public Result getLoggedInUserCountryMetrics() {
		AppUser appUser = LoginController.getLoggedInUser();
		ExpressionList<AreaMetrics> areaMetrics = AreaMetrics.find.where().eq("isActive",Boolean.TRUE);
		if(appUser != null) {
			areaMetrics.eq("country", appUser.getAddress().getCountry());
		}
		return ok(Json.toJson(areaMetrics.findList()));
	}
	
	public Result saveFile(Long fileEntityId) {
		AppResponse appResponse = new AppResponse();
		FileEntity fileEntity = null;
		if(fileEntityId != null && fileEntityId != 0) {
			fileEntity = FileEntity.find.byId(fileEntityId);
		}
		play.mvc.Http.MultipartFormData formData = request().body().asMultipartFormData();
		Logger.debug("fgssfdfsdfsdf"+formData);
		if(formData != null && formData.getFile("image") != null) {
			FilePart filePart = formData.getFile("image");
			Logger.debug("filePart"+filePart);
			if(!filePart.getFilename().isEmpty()) {
				File file = (File)filePart.getFile();
				try {
					File propfile = Play.application().getFile(FolderRoots.AMAZON_AWS_CREDENTIALS_FILE_PATH);
					String key = Calendar.getInstance().getTimeInMillis()+filePart.getFilename().replaceAll(" ", "_");
					AmazonS3 s3client = new AmazonS3Client(new PropertiesCredentials(propfile));
					ObjectMetadata meta = new ObjectMetadata();
					meta.setContentLength(com.google.common.io.Files.toByteArray(file).length);
		            meta.setContentType(filePart.getContentType());
		            PutObjectResult result = s3client.putObject(Constants.AMAZON_S3_BUCKET_NAME, key, file);
		            s3client.setObjectAcl(Constants.AMAZON_S3_BUCKET_NAME, key, CannedAccessControlList.PublicRead);
		            String finalUrl = "https://"+Constants.AMAZON_S3_BUCKET_NAME+".s3.amazonaws.com/" + key;
		            Logger.debug(finalUrl);
		            FileEntity finalFile = FileEntity.toFileEntity(fileEntity, filePart, finalUrl);
		            appResponse = new AppResponse(AlertType.SUCCESS, finalFile.getId());
				} catch (IllegalArgumentException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Logger.error("Error while saving image", e.getMessage());
				}
			}
		}
		return ok(Json.toJson(appResponse));
	}

}
