package beans;

import constants.AlertType;
import constants.LoginType;
import util.AppResponse;
import util.ResponseMessage;
import util.StringUtils;

public class LoginBean {
	
	public String email;
	
	public String password;
	
	public String loginFrom;
	
	public String clientIp;
	
	public String userRole;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getLoginFrom() {
		return loginFrom;
	}

	public void setLoginFrom(String loginFrom) {
		this.loginFrom = loginFrom;
	}
	
	
	public String getClientIp() {
		return clientIp;
	}

	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public AppResponse validateLoginForm(){
		LoginType loginType = null;
		if(StringUtils.isEmpty(this.getEmail())){
			return new AppResponse(ResponseMessage.EMAIL_REQUIRED);
		}
		if(StringUtils.isEmpty(this.loginFrom)) {
			return new AppResponse(ResponseMessage.LOGIN_FROM_REQUIRED);
		}else {
			loginType = LoginType.valueOf(this.loginFrom);
		}
		if(loginType != null && loginType.equals(LoginType.TOBUZ) && StringUtils.isEmpty(this.getPassword())){
			return new AppResponse(ResponseMessage.PASSWORD_REQUIRED);
		}
		return new AppResponse(AlertType.SUCCESS);
	}
	

}
