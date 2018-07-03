package beans;

import constants.AlertType;
import models.Address;
import models.AppUser;
import models.City;
import util.AppResponse;
import util.ResponseMessage;
import util.StringUtils;

public class AppUserPayLoadBean {
	
	public Long userId;
	
	public String authKey;
	
	public String name;
	
	public String role;
	
	public Long roleId;
	
	public Long countryId;
	
	public Long addressId;
	
	public Boolean isLocationUpdated = Boolean.FALSE;
	
	public Boolean isPackageUpdated = Boolean.FALSE;
	
	public Long cityId;
	
	public Long stateId;
	
	public String pincode;
	
	public String addressDescription;
	
	public String countryCode;
	
	public String mobileNumber;
	
	public String stateName;
	
	public String countryName;

	public String getStateName() {
		return stateName;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public Long getUserId() {
		return userId;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getAuthKey() {
		return authKey;
	}

	public void setAuthKey(String authKey) {
		this.authKey = authKey;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Boolean getIsLocationUpdated() {
		return isLocationUpdated;
	}

	public void setIsLocationUpdated(Boolean isLocationUpdated) {
		this.isLocationUpdated = isLocationUpdated;
	}

	public Boolean getIsPackageUpdated() {
		return isPackageUpdated;
	}

	public void setIsPackageUpdated(Boolean isPackageUpdated) {
		this.isPackageUpdated = isPackageUpdated;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

public AppResponse validateBean() {
	if(this.getUserId() == null || this.getUserId() == 0) {
		return new AppResponse(ResponseMessage.ACCESS_DENIED);
	}
	if(StringUtils.isEmpty(this.getName())) {
		return new AppResponse(ResponseMessage.NAME_REQUIRED);
	}
	if(StringUtils.isEmpty(this.mobileNumber)) {
		return new AppResponse(ResponseMessage.MOBILE_REQUIRED);
	}
	if((this.getAddressId() == null || this.getAddressId() == 0)) {
		return new AppResponse("Address is Required");
	}
	
	if(this.getCityId() == null || this.getCityId() == 0) {
		return new AppResponse(ResponseMessage.CITY_REQUIRED);
	}
	return new AppResponse(AlertType.SUCCESS);
}

public AppResponse updateProfile() {
	AppResponse response = validateBean();
	if(response.isSuccess()) {
	try {
	AppUser appuser = AppUser.find.byId(this.userId);
	Address address = Address.find.byId(this.addressId);
	appuser.setName(this.getName());
	appuser.setCountryCode(this.getCountryCode());
	appuser.setMobileNumber(this.getMobileNumber());
	appuser.update();
	City city = City.find.byId(this.getCityId());
	address.setCity(city);
	address.setState(city.getState());
	address.setCountry(city.getCountry());
	address.setZipCode(this.pincode);
	address.setDetailedAddress(this.getAddressDescription());
	address.update();
	}catch (Exception e) {
		// TODO: handle exception
		response = new AppResponse(AlertType.FAILURE);
	}
	}
	return response;
}
}
