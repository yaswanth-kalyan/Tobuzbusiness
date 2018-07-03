package models.broker;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;
import models.Country;
import models.business.BusinessListing;

@Entity
public class UserContactInfo extends BaseEntity{
	
	private String userName;
	
	private String email;
	
	private String mobileNumber;
	
	private String countryCode;
	
	@ManyToOne
	private Country mobileDerivedCountry;
	
	@Column(columnDefinition="TEXT")
	private String message;
	
	@ManyToOne
	private BusinessListing businessListing;
	
	private Boolean enableSmsEmailAlerts = Boolean.FALSE;
	
	private Boolean enableSimilarPropertyAlert = Boolean.FALSE;
	
	private Boolean enableBusinessProviderAdvise = Boolean.FALSE;
	
	public static Model.Finder<Long, UserContactInfo> find = new Model.Finder<Long, UserContactInfo>(UserContactInfo.class);

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Country getMobileDerivedCountry() {
		return mobileDerivedCountry;
	}

	public void setMobileDerivedCountry(Country mobileDerivedCountry) {
		this.mobileDerivedCountry = mobileDerivedCountry;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	public Boolean getEnableSmsEmailAlerts() {
		return enableSmsEmailAlerts;
	}

	public void setEnableSmsEmailAlerts(Boolean enableSmsEmailAlerts) {
		this.enableSmsEmailAlerts = enableSmsEmailAlerts;
	}

	public Boolean getEnableSimilarPropertyAlert() {
		return enableSimilarPropertyAlert;
	}

	public void setEnableSimilarPropertyAlert(Boolean enableSimilarPropertyAlert) {
		this.enableSimilarPropertyAlert = enableSimilarPropertyAlert;
	}

	public Boolean getEnableBusinessProviderAdvise() {
		return enableBusinessProviderAdvise;
	}

	public void setEnableBusinessProviderAdvise(Boolean enableBusinessProviderAdvise) {
		this.enableBusinessProviderAdvise = enableBusinessProviderAdvise;
	}
	
	
}
