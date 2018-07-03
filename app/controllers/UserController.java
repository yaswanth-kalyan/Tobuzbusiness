package controllers;

import com.google.inject.Inject;

import beans.AppUserPayLoadBean;
import beans.LoginBean;
import models.AppUser;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.LoginService;
import util.AppResponse;

public class UserController extends Controller{
	
	
	@Inject
	LoginService loginService;
	
	@Inject
	FormFactory formFactory;
	
	public Result getLoggedInUserProfileData() {
		AppUser appUser = LoginController.getLoggedInUser();
		AppUserPayLoadBean bean = null;
		if(appUser != null) {
			bean = appUser.toAppUserPayLoadBean();
		}
		Logger.info(" ---------------> here 1 2 3");
		Logger.info(bean.toString());
		return ok(Json.toJson(bean));
	}
	
	public Result updateUserProfile() {
		AppResponse appResponse = new AppResponse();
		AppUser appUser = LoginController.getLoggedInUser();
		//play.Logger.info(request().body().asText());
		String remote = request().remoteAddress();
		try{
			Form<AppUserPayLoadBean> form = formFactory.form(AppUserPayLoadBean.class).bindFromRequest();
			play.Logger.info(form.toString());
		if(!form.hasErrors()){
			AppUserPayLoadBean bean = form.get();
			play.Logger.info(bean.toString());
			appResponse = bean.updateProfile();
			appResponse.setPayLoad(appUser.toAppUserPayLoadBean());
		}
		}catch(Exception e){
			e.printStackTrace();
			appResponse = new AppResponse();
		}
		return ok(Json.toJson(appResponse));
	}
	
	public Result updateUserWithNewRole(String role) {
		AppResponse appResponse = new AppResponse();
		AppUser appUser = LoginController.getLoggedInUser();
		//play.Logger.info(request().body().asText());
		String remote = request().remoteAddress();
		try{
			Form<AppUserPayLoadBean> form = formFactory.form(AppUserPayLoadBean.class).bindFromRequest();
			play.Logger.info(form.toString());
		if(!form.hasErrors()){
			AppUserPayLoadBean bean = form.get();
			play.Logger.info(bean.toString());
			appResponse = bean.updateProfile();
			appResponse.setPayLoad(appUser.toAppUserPayLoadBean());
		}
		}catch(Exception e){
			e.printStackTrace();
			appResponse = new AppResponse();
		}
		return ok(Json.toJson(appResponse));
	}

}
