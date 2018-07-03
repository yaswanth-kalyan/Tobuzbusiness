package controllers;

import com.google.inject.Inject;

import beans.LoginBean;
import beans.UpdatePasswordBean;
import constants.AlertType;
import constants.UserRole;
import models.AppUser;
import play.Logger;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.LoginService;
import util.AppResponse;
import util.SessionProperty;

public class LoginController extends Controller {
	
	
	@Inject
	LoginService loginService;
	
	@Inject
	FormFactory formFactory;
	
	/**
	 * @author Lakshmi
	 * Action to get login page
	 * 
	 */
	public Result loginform(){
		return ok("");
	}
	
	/**
	 * Action allow user to login to Permasol
	 * Input Parameters are Email , Password & loginFrom(FB/Twitter etc)
	 * On success add Encrypted authentication key to session to maintain the user track 
	 * @return
	 * @throws Exception
	 * POST	/login
	 */
	public Result login() {
		AppResponse appResponse = new AppResponse();
		//play.Logger.info(request().body().asText());
		String remote = request().remoteAddress();
		try{
			Form<LoginBean> form = formFactory.form(LoginBean.class).bindFromRequest();
			play.Logger.info(form.toString());
		if(!form.hasErrors()){
			LoginBean bean = form.get();
			bean.clientIp = remote;
			play.Logger.info(bean.toString());
			appResponse = bean.validateLoginForm();
			if(appResponse.isSuccess()){
			appResponse = loginService.getAppUserLoginStatus(bean);
			}
		}
		}catch(Exception e){
			e.printStackTrace();
			appResponse = new AppResponse();
		}
		return ok(Json.toJson(appResponse));
	}
	
	
	/**
	 * @author Lakshmi
	 * Action to clear the session of logged in user
	 * GET		/logout	
	 */
	public Result logout() {
		AppResponse appResponse = new AppResponse();
		try{
		SessionProperty.clearTobuzSession();
		appResponse.setAlertType(AlertType.SUCCESS);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return redirect(controllers.routes.HomeController.index());
	}
	
	/**
	 * @author Lakshmi
	 * Action to clear the session of logged in user
	 * GET		/dashboard
	 */
	public Result dashboard() {
		try{
			AppUser appUser = SessionProperty.getLoggedInUser();
			if(appUser != null && appUser.getRole() != null){
				if(appUser.getRole().getUserRole().equals(UserRole.TOBUZ_ADMIN)){
						return ok("");
				}else if(appUser.getUserPackages().size() == 0) {
					return redirect(controllers.routes.TobuzPackageController.getPackageDetailsByCountry());	
				}else if(appUser.getRole().getUserRole().equals(UserRole.BUYER)){
					 return ok(views.html.dbindex.render(appUser));
				}else if(appUser.getRole().getUserRole().equals(UserRole.SELLER)){
					return ok("");
				}else if(appUser.getRole().getUserRole().equals(UserRole.BROKER)){
					return ok("");
				}else if(appUser.getRole().getUserRole().equals(UserRole.FRANCHISOR)){
					return ok("");
				}else if(appUser.getRole().getUserRole().equals(UserRole.BUSINESS_SERVICE)){
					return ok("");
				}
			}
			}catch(Exception ex){
				ex.printStackTrace();
			}
		return redirect(controllers.routes.LoginController.logout());
	}
	
//	public Result emailSend(){
//	//	Boolean emailResponse = EmailService.sendEmail("durga@thrymr.net");
//		Logger.info("email Response"+emailResponse);
//		
//		return ok();
//		
//	}
//	public Result countryWiseStates(){
//		Long countryId = Long.parseLong(request().getQueryString("cId"));
//		List<State> statesList = State.find.where().eq("country_id",countryId).eq("isActive",Boolean.TRUE).findList();
//		return ok(Json.toJson(statesList));
//	}
//	public Result changePassword() {
//		return ok(views.html.changePassword.render());
//	}
	public Result updatePasswordRequest(){
		AppResponse appResponse = new AppResponse();
		try {
			Form<UpdatePasswordBean> form = formFactory.form(UpdatePasswordBean.class).bindFromRequest();
			UpdatePasswordBean bean = form.get();
			Logger.error(""+bean.toString());
			appResponse = loginService.updateUserPassword(bean, SessionProperty.getLoggedInUser());
		} catch (IllegalArgumentException ill) {
			appResponse.responseMessage = ill.getMessage();
			appResponse.alertType = AlertType.FAILURE;
			ill.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ok(Json.toJson(appResponse));
	}
	
	/**
	 * Action to find User is LoggedIn
	 */
	public static AppUser getLoggedInUser(){
		// TODO Auto-generated method stub
		AppUser appUser = SessionProperty.getAppUserWithSessionAuthKey();
		if (appUser != null) {
			return appUser;
		}
		return null;
	}
}
