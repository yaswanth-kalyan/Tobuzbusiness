package controllers;

import com.google.inject.Inject;

import auth.ListingAccessAuth;
import beans.tobuzPackage.BusinessListingBean;
import models.AppUser;
import play.data.Form;
import play.data.FormFactory;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;
import service.LoginService;
import util.AppResponse;


public class BusinessListingController extends Controller{
	
	@Inject
	LoginService loginService;
	
	@Inject
	FormFactory formFactory;
	
	/**
	 * 
	 */
	@ListingAccessAuth
	public Result saveBusinessListing() {
		
		AppResponse appResponse = new AppResponse();
		try{
			Form<BusinessListingBean> form = formFactory.form(BusinessListingBean.class).bindFromRequest();
			AppUser appuser = LoginController.getLoggedInUser();
			BusinessListingBean bean = form.get();
			bean.setListedByUserId(appuser.getId());
			bean.setRoleId(appuser.getRole().getId());
			appResponse = bean.toBusinessListing();
			play.Logger.info("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@"+form.toString());
			
		}catch(Exception e){
			e.printStackTrace();
			appResponse = new AppResponse();
		}
		return ok(Json.toJson(appResponse));
	}

}
