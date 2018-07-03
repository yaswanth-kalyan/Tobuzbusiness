package beans;

import java.util.List;

import constants.AlertType;
import constants.LoginType;
import constants.UserRole;
import models.AppUser;
import models.Country;
import models.Role;
import models.broker.Broker;
import models.buyer.Buyer;
import models.franchiser.Franchiser;
import models.seller.Seller;
import service.AppUserService;
import util.AppResponse;
import util.ResponseMessage;
import util.SessionProperty;
import util.StringUtils;

public class RegistrationBean {
	
	public Long appUserId;
	
	public String name;
	
	public String mobileNumber;
	
	public String countryCode;
	
	public String email;
	
	public String role;
	
	public String password;
	
	public Long mobileDerivedCountryId;
	
	public Boolean accessDetails = Boolean.FALSE;
	
	public String loginType;
	
	public String clientIp;
	
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
	}

	public Long getMobileDerivedCountryId() {
		return mobileDerivedCountryId;
	}

	public void setMobileDerivedCountryId(Long mobileDerivedCountryId) {
		this.mobileDerivedCountryId = mobileDerivedCountryId;
	}

	public AppUserService getAppUserService() {
		return appUserService;
	}

	public void setAppUserService(AppUserService appUserService) {
		this.appUserService = appUserService;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public AppUserService appUserService = null;
	
	public AppResponse validateBean() {
		AppResponse appResponse = new AppResponse(AlertType.SUCCESS);
		if(StringUtils.isEmpty(this.name)) {
			return new AppResponse(ResponseMessage.NAME_REQUIRED, AlertType.FAILURE);
		}
//		if(StringUtils.isEmpty(this.countryCode)) {
//			return new AppResponse(ResponseMessage.MOBILE_REQUIRED, AlertType.FAILURE);
//		}
//		if(StringUtils.isEmpty(this.mobileNumber)) {
//			return new AppResponse(ResponseMessage.MOBILE_REQUIRED, AlertType.FAILURE);
//		}
		if(StringUtils.isEmpty(this.email)) {
			return new AppResponse(ResponseMessage.EMAIL_REQUIRED, AlertType.FAILURE);
		}
		if(StringUtils.isEmpty(this.role)) {
			return new AppResponse(ResponseMessage.ROLE_REQUIRED, AlertType.FAILURE);
		}
		if(this.appUserId == null || this.appUserId == 0) {
		AppUser user = appUserService.findUserByEmailAndIsActiveTrue(this.email);
		if(user != null) {
			return new AppResponse(ResponseMessage.EMAIL_EXISTED_MULTIPLE_TIMES, AlertType.FAILURE);
		}
		if(StringUtils.isEmpty(this.loginType)) {
			return new AppResponse(ResponseMessage.LOGIN_FROM_REQUIRED, AlertType.FAILURE);
		}
		if(StringUtils.isEmpty(this.getPassword())) {
			if(this.loginType.equalsIgnoreCase("TOBUZ")) {
			return new AppResponse(ResponseMessage.PASSWORD_REQUIRED, AlertType.FAILURE);
			}
		}
		}else {
			AppUser appUser = appUserService.findById(this.appUserId);
			List<AppUser> existedAppUserist = appUserService.findUserByEmailAndRoleAndIdNotInAndIsActiveTrue(appUser,email);
			if(existedAppUserist.size() > 0) {
				return new AppResponse(ResponseMessage.USER_NAME_EXISTED, AlertType.FAILURE);
			}
		}
//		if(this.mobileDerivedCountryId == null || this.mobileDerivedCountryId == 0) {
//			return new AppResponse(ResponseMessage.MOBILE_COUNTRY_MISSING, AlertType.FAILURE);
//		}
		return appResponse;
	}
	
	public AppResponse toAppUser(AppUserService userService) {
		appUserService = userService;
		AppUser appUser = null;
		if(this.appUserId != null && this.appUserId != 0) {
			appUser = appUserService.findById(this.appUserId);
		}
		AppResponse appResponse = validateBean();
		if(appResponse.isSuccess()) {
			if(appUser == null) {
				appUser = new AppUser();
				appUser.setPassword(this.password);
				appUser = updateAppUserData(appUser);
				appUser.setLoginType(LoginType.valueOf(this.loginType));
				appUser.setIsActive(Boolean.TRUE);
				appUser.setUserDefaultRole(UserRole.valueOf(this.role));
				appUser.save();
				Role createdRole = appUserService.setRole(appUser,UserRole.valueOf(this.role));
				SessionProperty.updateUserRole(createdRole);
				SessionProperty.updateUserAfterLoginUrl("/dashboard");
				createRoleBasedUser(appUser);
			}else {
				appUser = updateAppUserData(appUser);
				appUser.update();
			}
			
			
			appResponse.setAppUser(appUser);
		}
		return appResponse;
	}
	
	public AppUser updateAppUserData(AppUser appUser) {
		appUser.setEmail(this.email);
		appUser.setName(this.getName());
	//	appUser.setCountryCode(this.countryCode);
	//	appUser.setMobileNumber(this.mobileNumber);
	//	appUser.setMobileDerivedCountry(Country.find.byId(this.mobileDerivedCountryId));
		return appUser;
	}
	
	public void createRoleBasedUser(AppUser appUser) {
		UserRole role = UserRole.valueOf(this.role);
		if(role.equals(UserRole.BUYER)) {
			if(Buyer.find.where().eq("user",appUser).findList().size() == 0) {
			Buyer buyer = new Buyer();
			buyer.setUser(appUser);
			buyer.setRole(appUser.getRole());
			buyer.setIsActive(Boolean.TRUE);
			buyer.setShareContactDetails(this.accessDetails);
			buyer.save();
			}
		}else if(role.equals(UserRole.BROKER)) {
			if(Broker.find.where().eq("user",appUser).findList().size() == 0) {
				Broker broker = new Broker();
				broker.setUser(appUser);
				broker.setRole(appUser.getRole());
				broker.setIsActive(Boolean.TRUE);
				broker.setShareBrokerContactToBuyer(this.accessDetails);
				broker.save();
			}
		}else if(role.equals(UserRole.FRANCHISOR)) {
			if(Franchiser.find.where().eq("user",appUser).findList().size() == 0) {
				Franchiser franchiser = new Franchiser();
				franchiser.setUser(appUser);
				franchiser.setRole(appUser.getRole());
				franchiser.setIsActive(Boolean.TRUE);
				franchiser.setAllowFranchisorContactAccess(this.accessDetails);
				franchiser.save();
			}
		}else if(role.equals(UserRole.SELLER)) {
			if(Seller.find.where().eq("user",appUser).findList().size() == 0) {
				Seller seller = new Seller();
				seller.setUser(appUser);
				seller.setRole(appUser.getRole());
				seller.setIsActive(Boolean.TRUE);
				seller.setAllowBuyerToContact(this.accessDetails);
				seller.save();
			}
		}
		
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

}
