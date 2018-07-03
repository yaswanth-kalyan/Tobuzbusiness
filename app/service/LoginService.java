package service;

import beans.LoginBean;
import beans.UpdatePasswordBean;
import constants.UserRole;
import models.AppUser;
import util.AppResponse;

public interface LoginService {

	AppResponse isAppUserExisted(String email);
	
	AppResponse isAppUserExistedWithRole(String email,UserRole userRole);
	
	AppResponse getAppUserLoginStatus(LoginBean loginBean);

	AppResponse updateUserPassword(UpdatePasswordBean bean, AppUser loggedInUser);

	
}
