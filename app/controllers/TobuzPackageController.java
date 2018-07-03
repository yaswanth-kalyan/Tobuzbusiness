package controllers;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import auth.BasicAuth;
import beans.tobuzPackage.TobuzPackageBean;
import constants.ListingType;
import constants.UserRole;
import models.AppUser;
import models.Country;
import models.tobuzpackage.TobuzPackage;
import play.mvc.Controller;
import play.mvc.Result;
import util.SessionProperty;

public class TobuzPackageController extends Controller {
	
	
	@BasicAuth
	public Result getPackageDetailsByCountry() {
		
		AppUser appUser = LoginController.getLoggedInUser();
		Country country = appUser.getAddress().getCountry();
		ListingType listingType = null;
		List<TobuzPackage> packageList = TobuzPackage.getPackagesByCountryAndRole(country,appUser);
		Map<TobuzPackage,Boolean> tobuzPackageActivatedList = new LinkedHashMap<TobuzPackage,Boolean>();
		for(TobuzPackage tpackage : packageList) {
			if((appUser.getRole().getUserRole().equals(UserRole.BROKER) || appUser.getRole().getUserRole().equals(UserRole.SELLER)) && listingType == null) {
				tobuzPackageActivatedList.put(tpackage, Boolean.FALSE);	
			}else {
			if(appUser != null && appUser.getUserActivatedPackages(null).contains(tpackage)) {
			tobuzPackageActivatedList.put(tpackage, Boolean.TRUE);
			}else {
				tobuzPackageActivatedList.put(tpackage, Boolean.FALSE);	
			}
			}
		}
		List<TobuzPackageBean> packageServiceMappingList = TobuzPackageBean.generatePackageData(country,appUser);
		return ok(views.html.packageList.render(tobuzPackageActivatedList,packageServiceMappingList,appUser));
	}

	@BasicAuth
	public Result getPackageDetailsForSeller() {
		
		return ok(views.html.seller.seller_packageList.render());
	}

}
