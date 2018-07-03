package controllers;

import com.google.inject.Inject;

import auth.BasicAuth;
import beans.AddressBean;
import beans.RegistrationBean;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.AppUserService;
import util.AppResponse;
import util.SessionProperty;

public class RegistrationController extends Controller{

	
	@Inject
	AppUserService appUserService;
	
	@Inject
	FormFactory formFactory;
	/**
	 * @author lakshmi
	 * Action to register/update a User with basic data
	 * @return
	 */
	public Result registerUser() {
		String remote = request().remoteAddress();
		AppResponse appResponse = new AppResponse();
		try {
		Form<RegistrationBean> form = formFactory.form(RegistrationBean.class).bindFromRequest();
		play.Logger.info(form.toString());
	if(!form.hasErrors()){
		RegistrationBean bean = form.get();
		bean.clientIp = remote;
		play.Logger.info(bean.toString());
		appResponse = bean.toAppUser(appUserService);
		if(appResponse.isSuccess()){
			if(bean.getAppUserId() == null || bean.getAppUserId() == 0) {
			SessionProperty.createUserSessionAuthKey(appResponse.getAppUser(), remote);
			appResponse.setPayLoad(appResponse.getAppUser().toAppUserPayLoadBean());
			}
		}
	}
	}catch(Exception e){
		e.printStackTrace();
		appResponse = new AppResponse();
	}
		return ok(Json.toJson(appResponse));
	}
	/**
	 * @author lakshmi
	 * Action to create/update the user address
	 * @return
	 */
	@BasicAuth
	public Result registerUserAddress() {
		AppResponse appResponse = new AppResponse();
		try {
		Form<AddressBean> form = formFactory.form(AddressBean.class).bindFromRequest();
		play.Logger.info(form.toString());
	if(!form.hasErrors()){
		AddressBean bean = form.get();
		play.Logger.info(bean.toString());
		appResponse = bean.toAddress();
		if(appResponse.isSuccess()){
		}
	}
	}catch(Exception e){
		e.printStackTrace();
		appResponse = new AppResponse();
	}
		return ok(Json.toJson(appResponse));
	}
	
	/**
	 * @author lakshmi
	 * Action to update business Package for User
	 */
	@BasicAuth
	public Result updateTobuzBusinessPackage() {
		AppResponse appResponse = new AppResponse();
		try {
		Form<BusinessPackageBean> form = formFactory.form(BusinessPackageBean.class).bindFromRequest();
		play.Logger.info(form.toString());
	if(!form.hasErrors()){
		BusinessPackageBean bean = form.get();
		bean.appUserId = LoginController.getLoggedInUser().getId();
		bean.role = LoginController.getLoggedInUser().getId();
		play.Logger.info(bean.toString());
		appResponse = bean.toBusinessPackage();
		if(appResponse.isSuccess()){
		}
	}
	}catch(Exception e){
		e.printStackTrace();
		appResponse = new AppResponse();
	}
		return ok(Json.toJson(appResponse));
	}
}
