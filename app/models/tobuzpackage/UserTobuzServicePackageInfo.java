package models.tobuzpackage;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;

import constants.ListingType;
import constants.TobuzPackageType;
import constants.UserPackageStatus;
import models.AppUser;
import models.BaseEntity;
import models.Role;

@Entity
public class UserTobuzServicePackageInfo extends BaseEntity{
	
	@ManyToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	@ManyToOne
	private TobuzPackage tobuzPackage;
	
	private Date activatedOn;
	
	private Date expiredOn;
	
	private ListingType packageActiveFor;
	
	private UserPackageStatus userPackageStatus = UserPackageStatus.ACTIVE;
	
	public static Model.Finder<Long, UserTobuzServicePackageInfo> find = new Model.Finder<Long, UserTobuzServicePackageInfo>(UserTobuzServicePackageInfo.class);

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public Date getActivatedOn() {
		return activatedOn;
	}

	public void setActivatedOn(Date activatedOn) {
		this.activatedOn = activatedOn;
	}

	public Date getExpiredOn() {
		return expiredOn;
	}

	public void setExpiredOn(Date expiredOn) {
		this.expiredOn = expiredOn;
	}

	public UserPackageStatus getUserPackageStatus() {
		return userPackageStatus;
	}

	public void setUserPackageStatus(UserPackageStatus userPackageStatus) {
		this.userPackageStatus = userPackageStatus;
	}

	public ListingType getPackageActiveFor() {
		return packageActiveFor;
	}

	public void setPackageActiveFor(ListingType packageActiveFor) {
		this.packageActiveFor = packageActiveFor;
	}
	
	
	public TobuzPackage getTobuzPackage() {
		return tobuzPackage;
	}

	public void setTobuzPackage(TobuzPackage tobuzPackage) {
		this.tobuzPackage = tobuzPackage;
	}

	public static UserTobuzServicePackageInfo getUserServicePackageInfo(AppUser appUser,Role role,ListingType listingType) {
		ExpressionList<UserTobuzServicePackageInfo> infos = UserTobuzServicePackageInfo.find.where().eq("user", appUser).eq("role", role);
		if(listingType != null) {
			infos.isNotNull("packageActiveFor").eq("packageActiveFor", listingType);
		}
		return infos.setMaxRows(1).findUnique();
	}
	
	public static UserTobuzServicePackageInfo createUserTobuzServicePackage(AppUser appUser,Role role,TobuzPackage tobuzPackage,ListingType listingType) {
		//if(!tobuzPackage.getTobuzPackageType().equals(TobuzPackageType.FREE)) {
		UserTobuzServicePackageInfo userTobuzServicePackageInfo = getUserServicePackageInfo(appUser,role,listingType);
		if(userTobuzServicePackageInfo == null) {
			userTobuzServicePackageInfo = new UserTobuzServicePackageInfo();
			userTobuzServicePackageInfo.setActivatedOn(new Date());
			userTobuzServicePackageInfo.save();
		}
		if(userTobuzServicePackageInfo.getTobuzPackage() == null || userTobuzServicePackageInfo.getTobuzPackage().getId().longValue() != tobuzPackage.getId().longValue()) {
		userTobuzServicePackageInfo.setTobuzPackage(tobuzPackage);
		userTobuzServicePackageInfo.setUser(appUser);
		userTobuzServicePackageInfo.setRole(role);
		userTobuzServicePackageInfo.setIsActive(Boolean.TRUE);
		if(!tobuzPackage.getTobuzPackageType().equals(TobuzPackageType.FREE) && tobuzPackage.getExpiryPeriodInMonths() != null){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.MONTH, tobuzPackage.getExpiryPeriodInMonths());
		userTobuzServicePackageInfo.setExpiredOn(calendar.getTime());
		}
		userTobuzServicePackageInfo.setPackageActiveFor(listingType);
		userTobuzServicePackageInfo.update();
		}
		return userTobuzServicePackageInfo;
//		}
//		return null;
	}
	
}
