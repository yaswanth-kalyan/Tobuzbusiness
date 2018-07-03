package util;

import constants.UserRole;

import models.AppUser;
import models.AppUserLog;
import models.Country;
import models.Role;

public class SessionProperty {
	

	public static final String TOBUZ_USER_ROLE = "tobuz_user_role";
	public static final String TOBUZ_USER_ROLE_ID = "tobuz_user_role_id";
	public static final String TOBUZ_URL_AFTER_LOGIN = "tobuz_url_after_login";
	public static final String TOBUZ_USER_AUTH_TOKEN = "tobuz_auth_token";
	public static final String TOBUZ_USER_COUNTRY_ID = "tobuz_user_country_id";
	public static final String TOBUZ_USER_TIMEZONE_OFFSET_IN_MINUTES = "tobuz_user_time_zone_offset_in_mins";
	public static final String TOBUZ_USER_COUNTRY_ISO = "tobuz_user_country_iso";
	
	

	public static void clearTobuzSession() {
		play.mvc.Controller.session().clear();
	}

	public static void updateUserRole(Role role) {
		play.mvc.Controller.session(TOBUZ_USER_ROLE, role.getUserRole().toString());
		play.mvc.Controller.session(TOBUZ_USER_ROLE_ID, role.getId()+"");
	}
	
	public static void updateUserAfterLoginUrl(String url) {
		play.mvc.Controller.session(TOBUZ_URL_AFTER_LOGIN, url);
	}

	public static String getUserAfterLoginUrl() {
		return play.mvc.Controller.session(TOBUZ_URL_AFTER_LOGIN);
	}
	public static void updateOffset(String offset) {
		play.mvc.Controller.session(TOBUZ_USER_TIMEZONE_OFFSET_IN_MINUTES, offset);
	}
	public static void updateCountryISO(String isoCode) {
		play.mvc.Controller.session(TOBUZ_USER_COUNTRY_ISO, isoCode);
		if(Country.getUserLocaleCountry() != null) {
		play.mvc.Controller.session(TOBUZ_USER_COUNTRY_ID, Country.getUserLocaleCountry().getId()+"");
		}
	}
	
	public static String getUserCountryId() {
		return play.mvc.Controller.session(TOBUZ_USER_COUNTRY_ID);
	}
	
	public static String getCountryISO() {
		return play.mvc.Controller.session(TOBUZ_USER_COUNTRY_ISO);
	}
	public static String getUserRoleId() {
		return play.mvc.Controller.session(TOBUZ_USER_ROLE_ID);
	}
	
	public static void removeFromSession(String attr) {
		if(StringUtils.isNotEmpty(attr)) {
		 play.mvc.Controller.session().remove(attr);
		}
	}
	public static String getUserTimeZoneOffset() {
		return play.mvc.Controller.session(TOBUZ_USER_TIMEZONE_OFFSET_IN_MINUTES);
	}


	/**
	 * @author Lakshmi 
	 * Action to generate the sessionAuthentication key and get
	 * the session authentication key if the session already exists
	 * @return
	 */
	public static AppUser getAppUserWithSessionAuthKey() {
		String authKey = play.mvc.Controller.session(TOBUZ_USER_AUTH_TOKEN);
		if (StringUtils.isEmpty(authKey)) {
			return null;
		}
		AppUserLog log = AppUserLog.find.where().eq("authToken", authKey).setMaxRows(1).findUnique();
		if(log != null) {
			return log.getAppUser();
		}
		return null;

	}
	
	/**
	 * @author Lakshmi 
	 * Action to get the logged in user sessionAuthentication \
	 * @return
	 */
	public static String getUserSessionAuthKey() {
		String authKey = play.mvc.Controller.session(TOBUZ_USER_AUTH_TOKEN);
		return authKey;

	}
	
	/**
	 * @author Lakshmi 
	 * Action to get the user role 
	 * @return
	 */
	public static UserRole getLoggedInUserRole() {
		String role = play.mvc.Controller.session(TOBUZ_USER_ROLE);
		if (StringUtils.isNotEmpty(role)) {
			return UserRole.valueOf(role);
		} else {
			return null;
		}
	}
	
	/**
	 * @author Lakshmi 
	 * Action to get the user role id
	 * @return
	 */
	public static Role getLoggedInUserRoleId() {
		String role = play.mvc.Controller.session(TOBUZ_USER_ROLE_ID);
		if (StringUtils.isNotEmpty(role) && !role.trim().equals("0")) {
			return Role.find.byId(Long.parseLong(role.trim()));
		} else {
			return null;
		}
	}
	
	/**
	 * @author Lakshmi Action to create sessionAuthentication key and get
	 *         the session authentication key if the session already exists
	 * @return
	 */
	public static Boolean createUserSessionAuthKey(AppUser loggedInUser,String remote) {
		if(loggedInUser != null){
			AppUserLog appUserLog = AppUserLog.createAppUserLoginInfo(loggedInUser,remote);
			loggedInUser.update();
			play.mvc.Controller.session(TOBUZ_USER_AUTH_TOKEN, appUserLog.getAuthToken());
			return true;
		}
		return false;
	}
	
	
	/**
	 * @author Lakshmi Action to update sessionAuthentication key and get
	 *         the session authentication key if the session already exists
	 * @return
	 */
	public static Boolean updateUserSessionAuthKey(String remote) {
		AppUser loggedInUser = getLoggedInUser();
		if(loggedInUser != null){
			AppUserLog appUserLog = AppUserLog.createAppUserLoginInfo(loggedInUser,remote);
			loggedInUser.update();
			play.mvc.Controller.session(TOBUZ_USER_AUTH_TOKEN, appUserLog.getAuthToken());
			return true;
		}
		return false;
	}
	
	
	public static AppUser getLoggedInUser(){
		// TODO Auto-generated method stub
		AppUser appUser = SessionProperty.getAppUserWithSessionAuthKey();
		if (appUser != null) {
			return appUser;
		}
		return null;
	}

}
