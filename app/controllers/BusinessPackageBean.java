package controllers;

import javax.persistence.Entity;

import constants.AlertType;
import constants.ListingType;
import constants.UserRole;
import models.AppUser;
import models.BaseEntity;
import models.Role;
import models.tobuzpackage.TobuzPackage;
import models.tobuzpackage.UserTobuzServicePackageInfo;
import util.AppResponse;
import util.ResponseMessage;
import util.StringUtils;

@Entity
public class BusinessPackageBean extends BaseEntity{
	
	public Long userTobuzServicePackInfo;
	
	public Long appUserId;
	
	public Long role;
	
	public Long businessPackageId;
	
	public String packageFor;
	
	
	public AppResponse validateBean() {
		Role userRole = null;
		if(this.appUserId == null || this.appUserId == 0) {
			return new AppResponse(ResponseMessage.USER_NOT_FOUND);
		}
		if(this.role == null || this.role == 0) {
			return new AppResponse(ResponseMessage.ROLE_REQUIRED);
		}
		if(this.businessPackageId == null || this.businessPackageId == 0) {
			return new AppResponse(ResponseMessage.BUSINESS_PACKAGE_REQUIRED);
		}else {
			userRole = Role.find.byId(this.role);
		}
		if(userRole.getUserRole().equals(UserRole.BROKER) || userRole.getUserRole().equals(UserRole.SELLER)) {
		if(StringUtils.isEmpty(this.packageFor)) {
			return new AppResponse(ResponseMessage.PACKAGE_FOR_REQUIRED);
		}
		}
		return new AppResponse(AlertType.SUCCESS);
	}
	
	public AppResponse toBusinessPackage() {
		AppResponse appResponse = validateBean();
		if(appResponse.isSuccess()) {
//		UserTobuzServicePackageInfo userTobuzServicePackageInfo = null;
//		if(this.userTobuzServicePackInfo != null && this.userTobuzServicePackInfo != 0) {
//			userTobuzServicePackageInfo = UserTobuzServicePackageInfo.find.byId(this.userTobuzServicePackInfo);
//			userTobuzServicePackageInfo = updatePackageInfo(userTobuzServicePackageInfo);
//			userTobuzServicePackageInfo.update();
//		}else {
//			userTobuzServicePackageInfo = new UserTobuzServicePackageInfo();
//			userTobuzServicePackageInfo = updatePackageInfo(userTobuzServicePackageInfo);
//			userTobuzServicePackageInfo.save();
//		}
			TobuzPackage tobuzPackage = TobuzPackage.find.byId(this.businessPackageId);
			AppUser user = AppUser.find.byId(this.appUserId);
			Role role = Role.find.byId(this.role);
			ListingType listingType = null;
			if(StringUtils.isNotEmpty(this.packageFor)) {
				listingType = ListingType.valueOf(this.packageFor);
			}
		UserTobuzServicePackageInfo	userTobuzServicePackageInfo = UserTobuzServicePackageInfo.createUserTobuzServicePackage(user, role,tobuzPackage,listingType);
	//	appResponse.setPayLoad(userTobuzServicePackageInfo);
		}
		return appResponse;
	}

	public UserTobuzServicePackageInfo updatePackageInfo(UserTobuzServicePackageInfo userTobuzServicePackageInfo) {
		TobuzPackage tobuzPackage = TobuzPackage.find.byId(this.businessPackageId);
		AppUser user = AppUser.find.byId(this.appUserId);
		Role role = Role.find.byId(this.role);
		ListingType listingType = null;
		if(StringUtils.isNotEmpty(this.packageFor)) {
			listingType = ListingType.valueOf(this.packageFor);
		}
		userTobuzServicePackageInfo = UserTobuzServicePackageInfo.createUserTobuzServicePackage(user, role,tobuzPackage,listingType);
		
		return userTobuzServicePackageInfo;
	}
}
