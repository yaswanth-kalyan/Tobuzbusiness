package util;

import com.fasterxml.jackson.annotation.JsonIgnore;

import constants.AlertType;
import models.AppUser;

public class AppResponse {
	
	public String responseMessage;
	
	public Object payLoad;
	
	public AlertType alertType;
	
	@JsonIgnore
	public AppUser appUser;
	
	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Object getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(Object payLoad) {
		this.payLoad = payLoad;
	}

	public AlertType getAlertType() {
		return alertType;
	}

	public void setAlertType(AlertType alertType) {
		this.alertType = alertType;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public AppResponse() {
		super();
		this.alertType = AlertType.FAILURE;
		this.responseMessage = ResponseMessage.REQUEST_FAILED;
	}

	public AppResponse(String responseMessage) {
		super();
		this.alertType = AlertType.FAILURE;
		this.responseMessage = responseMessage;
	}
	
	public AppResponse(AlertType alertType) {
		super();
		this.alertType = alertType;
		if(alertType.equals(AlertType.SUCCESS)) {
			this.responseMessage = ResponseMessage.REQUEST_SUCCEESS;	
		}else {
			this.responseMessage = ResponseMessage.REQUEST_FAILED;
		}
	}
	
	public AppResponse(String responseMessage, Object payLoad, AlertType alertType, AppUser appUser) {
		super();
		this.responseMessage = responseMessage;
		this.payLoad = payLoad;
		this.alertType = alertType;
		this.appUser = appUser;
	}

	public AppResponse(String responseMessage, AlertType alertType) {
		super();
		this.responseMessage = responseMessage;
		this.alertType = alertType;
	}

	public AppResponse(String responseMessage, Object payLoad, AlertType alertType) {
		super();
		this.responseMessage = responseMessage;
		this.payLoad = payLoad;
		this.alertType = alertType;
	}
	
	public AppResponse( AlertType alertType,Object payLoad) {
		super();
		this.payLoad = payLoad;
		this.alertType = alertType;
	}
	
	public Boolean isSuccess() {
		if(this.alertType.equals(AlertType.SUCCESS)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	@Override
	public String toString() {
		return "AppResponse [responseMessage=" + responseMessage + ", payLoad=" + payLoad + ", alertType=" + alertType
				+ ", appUser=" + appUser + "]";
	}

	
	

}
