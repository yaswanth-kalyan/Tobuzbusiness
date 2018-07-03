package serviceImpl;

import java.security.MessageDigest;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Assert;

import com.avaje.ebean.Expr;

import beans.LoginBean;
import beans.UpdatePasswordBean;
import constants.AlertType;
import constants.LoginType;
import constants.UserRole;
import models.AppUser;
import models.Role;
import play.Logger;
import service.LoginService;
import util.AppResponse;
import util.ResponseMessage;
import util.SessionProperty;
import util.StringUtils;

public class LoginServiceImpl implements LoginService{
	
	private static String replyMessage = "";
	private static AlertType alertType = AlertType.NONE;

	@Override
	public AppResponse isAppUserExisted(String email) {
		// TODO Auto-generated method stub
		AppResponse appResponse = new AppResponse();
		List<AppUser> appUserList = AppUser.find.where().ieq("email", email.trim()).eq("isActive", Boolean.TRUE).findList();
		if(appUserList.size() == 0){
			appResponse.responseMessage = ResponseMessage.EMAIL_NOT_EXISTED;
		}else if(appUserList.size() == 1){
			appResponse.setAlertType(AlertType.SUCCESS);
			appResponse.setAppUser(appUserList.get(0));
		}else{
			appResponse.responseMessage = ResponseMessage.EMAIL_EXISTED_MULTIPLE_TIMES;
		}
		return appResponse;
	}
	
	@Override
	public AppResponse isAppUserExistedWithRole(String email,UserRole userRole) {
		// TODO Auto-generated method stub
		AppResponse appResponse = new AppResponse();
		List<Role> appUserList = Role.find.where().ieq("appUser.email", email.trim()).eq("userRole",userRole).and(Expr.eq("isActive", Boolean.TRUE),Expr.eq("appUser.isActive", Boolean.TRUE)).findList();
		if(appUserList.size() == 0){
			appResponse.responseMessage = ResponseMessage.MOBILE_NOT_EXISTED;
		}else if(appUserList.size() == 1){
			appResponse.setAlertType(AlertType.SUCCESS);
			appResponse.setAppUser(appUserList.get(0).getAppUser());
		}else{
			appResponse.responseMessage = ResponseMessage.MOBILE_EXISTED_MULTIPLE_TIMES;
		}
		return appResponse;
	}

	@Override
	public AppResponse getAppUserLoginStatus(LoginBean loginBean) {
		String email = loginBean.getEmail();
		String password = loginBean.getPassword();
		String logintype = loginBean.getLoginFrom();
		AppResponse appResponse = isAppUserExisted(email);
		LoginType userLoginType = LoginType.valueOf(logintype);
		if(appResponse.isSuccess()){
			AppUser appUser = appResponse.getAppUser();
			UserRole userRole = appUser.getUserDefaultRole();
			if(userLoginType.equals(appUser.getLoginType())){
				if(userLoginType.equals(LoginType.TOBUZ)) {
					appResponse = validateAppUserPassword(appUser,password);
				}
				if(appResponse.isSuccess()) {
				if(util.SessionProperty.createUserSessionAuthKey(appUser,loginBean.clientIp)){
					SessionProperty.updateUserRole(appUser.getAppUserDefaultRole());
					SessionProperty.updateUserAfterLoginUrl("/dashboard");
				appResponse.setAlertType(AlertType.SUCCESS);
				appResponse.setPayLoad(appUser.toAppUserPayLoadBean());
				}else{
					appResponse.setAlertType(AlertType.FAILURE);
					appResponse.setResponseMessage(ResponseMessage.ACCESS_DENIED);
				}
				}
			}else{
				appResponse.setAlertType(AlertType.FAILURE);
				appResponse.setResponseMessage(ResponseMessage.ACCESS_DENIED);
			}
		}
		return appResponse;
	}

	

	public AppResponse findAppUserWithEmail(String email) {
		AppResponse appResponse = new AppResponse();
		if (StringUtils.isNotEmpty(email)) {
			if(StringUtils.validateEmail(email.trim())) {
			List<AppUser> appUserList = AppUser.find.where().ieq("email", email.trim().toLowerCase()).eq("isActive", Boolean.TRUE).findList();
			if(appUserList.size() == 0){
				appResponse.responseMessage = ResponseMessage.EMAIL_NOT_EXISTED;
			}else if(appUserList.size() == 1){
				appResponse.setAlertType(AlertType.SUCCESS);
				appResponse.setAppUser(appUserList.get(0));
			}else{
				appResponse.responseMessage = ResponseMessage.EMAIL_EXISTED_MULTIPLE_TIMES;
			}
			}else {
				appResponse.setAlertType(AlertType.FAILURE);
				appResponse.responseMessage = ResponseMessage.INVALID_EMAIL;
				
			}
		} else {
			appResponse.setAlertType(AlertType.FAILURE);
			appResponse.responseMessage = ResponseMessage.EMAIL_REQUIRED;
			
		}
	//	Logger.info("appResponse = "+appResponse.getAlertMessage());
		return appResponse;
	}

	public AppResponse validateAppUserPassword(AppUser appUser, String password) {
		if (isPasswordMatched(appUser, password)) {
			replyMessage = " Welcome to dashboard " + appUser.getName() + " !";
			alertType = AlertType.SUCCESS;
		} else {
			replyMessage = ResponseMessage.INCORRECT_PASSWORD;
			alertType = AlertType.FAILURE;
		}
		AppResponse passwordResponse = new AppResponse(replyMessage, alertType);
		passwordResponse.setAppUser(appUser);
		return passwordResponse;
	}
	/**
	 * @author Lakshmi Action to compare password with encrypted salt for
	 *         validating user credentials
	 */
	public Boolean isPasswordMatched(AppUser appUser, String password) {
			try {
				final String passwordWithSalt = password + appUser.getSalt();
				final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
				final byte[] passBytes = passwordWithSalt.getBytes();
				final String hashedPassword = Base64.encodeBase64String(sha256.digest(passBytes));
				if (hashedPassword.compareTo(appUser.getPassword()) == 0) {
					return true;
				} else {
					return false;
				}
			} catch (final Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	

	@Override
	public AppResponse updateUserPassword(UpdatePasswordBean bean, AppUser appUser) {
		AppResponse response = new AppResponse();
		Assert.notNull(bean.oldPassword, "Please enter old Password");
		Assert.notNull(bean.newPassword, "Please enter new Password");
		Assert.notNull(bean.confirmPassword, "Please enter confirm Password");
		Assert.isTrue(bean.confirmPassword.equals(bean.newPassword),"Confirm Password and new Password doesn't matched");
		Assert.isTrue(bean.newPassword.length() > 5, " Password length should be minimum 6 characters");
		if (!isPasswordMatched(appUser, bean.oldPassword)) {
			response.responseMessage = "Old password is wrong";
			response.alertType = AlertType.FAILURE;
		} else {
			Logger.error("your new password is :"+bean.newPassword);
			appUser.setPassword(bean.newPassword);
			appUser.update();
			response.responseMessage = "Your password is updated successfully ";
			response.alertType = AlertType.SUCCESS;
		}
		Logger.info("Response  == "+response);
		return response;
	}

	
	
	
	

}
