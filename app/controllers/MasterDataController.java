package controllers;

import java.util.Arrays;
import java.util.List;

import constants.TobuzPackageType;
import constants.UserRole;
import models.City;
import models.Country;
import models.State;
import models.business.BusinessFeature;
import models.tobuzpackage.TobuzPackage;
import models.tobuzpackage.TobuzPackageService;
import play.mvc.Controller;
import play.mvc.Result;
import util.CountryUtil;

public class MasterDataController extends Controller{
	
	public Result insertCountryDetails() {
//		List<String> countryList = Arrays.asList(new String[] { "India", "Pakistan", "Singapore" });
//		
//		for(String country : countryList) {
//			Country country2 = new Country();
//			country2.setIsActive(Boolean.TRUE);
//			country2.setName(country);
//			country2.save();
//		}
		CountryUtil.insertCountry();
		 return ok("Inserted countries");
	}
	
	public Result insertStates() {
		List<String> indiaStateList = Arrays.asList(new String[] { "AP", "Telangana", "Karnataka","Tamilnadu","Kerala","Maharastra" });
		
		for(String stateName : indiaStateList) {
			State state = new State();
			state.setCountry(Country.find.where().ieq("name", "India").findUnique());
			state.setName(stateName);
			state.setIsActive(Boolean.TRUE);
			state.save();
		}
	
		 return ok("Inserted States");
	}
	
	public Result insertCities() {
		List<String> apCityList = Arrays.asList(new String[] { "Guntur", "Amaravathi", "Chitoor","Nellore" });
		List<String> telanganaCityList = Arrays.asList(new String[] { "Hyderabad", "KarimNagar", "Nizamabad","Nalgonda" });
		State Ap = State.find.where().ieq("name", "AP").setMaxRows(1).findUnique();
		State TL = State.find.where().ieq("name", "Telangana").setMaxRows(1).findUnique();
		for(String cityName : apCityList) {
			City city = new City();
			city.setName(cityName);
			city.setState(Ap);
			city.setIsActive(Boolean.TRUE);
			city.setCountry(Ap.getCountry());
			city.save();
		}
		
		for(String cityName : telanganaCityList) {
			City city = new City();
			city.setName(cityName);
			city.setState(TL);
			city.setCountry(TL.getCountry());
			city.setIsActive(Boolean.TRUE);
			city.save();
		}
		 return ok("Inserted cities");
	}
	
	public Result insertBusinessPackage() {
		Integer expiryMonths = 2;
		for(TobuzPackageType name : TobuzPackageType.values()) {
			TobuzPackage tobuz = new TobuzPackage();
			tobuz.setIsActive(Boolean.TRUE);
			tobuz.setTobuzPackageType(name);
			tobuz.setCountry(Country.find.byId(1l));
			tobuz.setExpiryPeriodInMonths(expiryMonths);
			tobuz.save();
			expiryMonths = expiryMonths+2;
		}
	
		 return ok("Inserted Packages");
	}
	
	public Result insertTobuzPackageService() {
	
		List<String> businessPackageList = Arrays.asList(new String[] { "Personalised Dashboard", "Email Alerts", "Contact Seller Auto-Fill Form","Business Wanted Adverts","Seller/Broker Database","View Files","Distress Sale Database","Prioritized Enquires","Add on Business Services","Social Media and Email Marketing"});
		for(String name : businessPackageList) {
			TobuzPackageService tobuz = new TobuzPackageService();
			tobuz.setIsActive(Boolean.TRUE);
			tobuz.setTitle(name);
			tobuz.setCountry(Country.find.byId(1l));
			tobuz.setUserRole(UserRole.BUYER);
			tobuz.save();
		}
	
		 return ok("Inserted Package services");
	}
	
	public Result inserBusinessFeatures() {
		List<String> businessFeatureList = Arrays.asList(new String[] { "Adjacent to Multiple Retailers", "Attractive Business", "Central Position in Affluent Village",
				"Close to Local Amenities","Company for Sale","Currently Closed","Established Business","Free of Tie Leasehold","Fully Fitted","High Turnover","Home Based",
				"Ideal for Full Refurbishment","In Receivership","Incentives May Be Available","Large Business for Sale","New Development Approaching","New Lease","Nil Premium","Offers Invited",
				"Open Plan Trading Area","Owner Financed","Planning Consent Available","Potential for Alternative Use","Price Further Reduced","Price Reduced","Prime Location",
				"Retirement Sale","Small Business","Split Level Trading Area","Town Centre Location","Trade Gardens Available"});
		for(String name : businessFeatureList) {
			BusinessFeature tobuz = new BusinessFeature();
			tobuz.setIsActive(Boolean.TRUE);
			tobuz.setName(name);
			tobuz.save();
		}
	
		 return ok("Inserted Business Features");
	}
	
	
	

}
