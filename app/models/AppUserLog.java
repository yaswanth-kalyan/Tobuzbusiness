package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

@Entity
public class AppUserLog extends BaseEntity{

	private Date loggedInOn;

	private Date loggedOutOn;
	
	private String authToken;
	
	private String ipAddress;

	@ManyToOne
	private AppUser appUser;
	
	public static Model.Finder<Long, AppUserLog> find = new Model.Finder<Long, AppUserLog>(AppUserLog.class);
	
	public Date getLoggedInOn() {
		return loggedInOn;
	}

	public void setLoggedInOn(Date loggedInOn) {
		this.loggedInOn = loggedInOn;
	}

	public Date getLoggedOutOn() {
		return loggedOutOn;
	}

	public void setLoggedOutOn(Date loggedOutOn) {
		this.loggedOutOn = loggedOutOn;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	
	public static AppUserLog createAppUserLoginInfo(AppUser appUser,String remote) {
		AppUserLog appUserLoginInfo = new AppUserLog();
		appUserLoginInfo.setAppUser(appUser);
		appUserLoginInfo.setAuthToken(appUser.generateAuthToken());
		appUserLoginInfo.setIpAddress(remote);
		appUserLoginInfo.setIsActive(Boolean.TRUE);
		appUserLoginInfo.setLoggedInOn(new Date());
		appUserLoginInfo.save();
		
		return appUserLoginInfo;
	}
	
}
