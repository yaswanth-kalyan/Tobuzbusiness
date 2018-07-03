package beans;

import constants.AlertType;
import constants.UserRole;
import models.Address;
import models.AppUser;
import models.City;
import models.Country;
import models.broker.Broker;
import models.buyer.Buyer;
import models.franchiser.Franchiser;
import models.seller.Seller;
import util.AppResponse;
import util.ResponseMessage;
import util.StringUtils;

public class AddressBean {
	public Long appUserId;
	
	public Long cityId;
	
	public Long stateId;
	
	public Long countryId;
	
	public String addressDescription;
	
	public String pincode;
	
	public Double latitude;
	
	public Double longitude;
	
	public String googleAddress;
	
	public Boolean allowAccess = Boolean.FALSE;
	
	
	public String mobileNumber;
	
	public Long mobileDerivedCountryId;
	
	
	@Override
	public String toString() {
		return "AddressBean [appUserId=" + appUserId + ", cityId=" + cityId + ", stateId=" + stateId + ", countryId="
				+ countryId + ", addressDescription=" + addressDescription + ", pincode=" + pincode + ", latitude="
				+ latitude + ", longitude=" + longitude + ", googleAddress=" + googleAddress + ", allowAccess="
				+ allowAccess + ", mobileNumber=" + mobileNumber + ", mobileDerivedCountryId=" + mobileDerivedCountryId
				+ "]";
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Long getMobileDerivedCountryId() {
		return mobileDerivedCountryId;
	}

	public void setMobileDerivedCountryId(Long mobileDerivedCountryId) {
		this.mobileDerivedCountryId = mobileDerivedCountryId;
	}

	public Boolean getAllowAccess() {
		return allowAccess;
	}

	public void setAllowAccess(Boolean allowAccess) {
		this.allowAccess = allowAccess;
	}

	public Long getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(Long appUserId) {
		this.appUserId = appUserId;
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

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public String getAddressDescription() {
		return addressDescription;
	}

	public void setAddressDescription(String addressDescription) {
		this.addressDescription = addressDescription;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public String getGoogleAddress() {
		return googleAddress;
	}

	public void setGoogleAddress(String googleAddress) {
		this.googleAddress = googleAddress;
	}
	
	
	public AppResponse validateBean() {
		AppResponse appResponse = new AppResponse(AlertType.SUCCESS);
		if(this.appUserId == null || this.appUserId == 0) {
			return new AppResponse(ResponseMessage.REQUEST_FAILED, AlertType.FAILURE);
		}
		if(StringUtils.isEmpty(this.mobileNumber)) {
			return new AppResponse(ResponseMessage.MOBILE_REQUIRED, AlertType.FAILURE);
		}
		if(this.cityId == null || this.cityId == 0) {
			return new AppResponse(ResponseMessage.CITY_REQUIRED, AlertType.FAILURE);
		}
		if(this.stateId == null || this.stateId == 0) {
			return new AppResponse(ResponseMessage.STATE_REQUIRED, AlertType.FAILURE);
		}
		if(this.countryId == null || this.countryId == 0) {
			return new AppResponse(ResponseMessage.COUNTRY_REQUIRED, AlertType.FAILURE);
		}
		if(StringUtils.isEmpty(this.pincode)) {
			return new AppResponse(ResponseMessage.ZIPCODE_REQUIRED, AlertType.FAILURE);
		}
		return appResponse;
	}
	
	public AppResponse toAddress() {
		AppResponse appResponse = validateBean();
		if(appResponse.isSuccess()) {
			AppUser appUser = AppUser.find.byId(this.appUserId);
			Address address = appUser.getAddress();
			if(address != null) {
				address = updateAddressData(address);
				address.update();
			}else {
				address = new Address();
				address = updateAddressData(address);
				address.save();
				appUser.setAddress(address);
			}
			
			appUser.setMobileNumber(this.mobileNumber);
			if(this.mobileDerivedCountryId != null) {
				Country country = Country.find.byId(this.mobileDerivedCountryId);
			appUser.setMobileDerivedCountry(Country.find.byId(this.mobileDerivedCountryId));
			appUser.setCountryCode(country.getDialingCode());
			}
			appUser.update();
		if(appUser.getRole() != null && appUser.getRole().getUserRole().equals(UserRole.BUYER)) {
			Buyer buyer = appUser.getBuyer();
			if(this.allowAccess != null) {
			buyer.setShareContactDetails(Boolean.TRUE);
			}else {
				buyer.setShareContactDetails(Boolean.FALSE);	
			}
			buyer.update();
		}else if(appUser.getRole() != null && appUser.getRole().getUserRole().equals(UserRole.SELLER)) {
			Seller seller = appUser.getSeller();
			if(this.allowAccess != null) {
				seller.setAllowBuyerToContact(Boolean.TRUE);
			}else {
				seller.setAllowBuyerToContact(Boolean.FALSE);	
			}
			seller.update();
		}else if(appUser.getRole() != null && appUser.getRole().getUserRole().equals(UserRole.FRANCHISOR)) {
			Franchiser franchiser = appUser.getFranchisor();
			if(this.allowAccess != null) {
				franchiser.setAllowFranchisorContactAccess(Boolean.TRUE);
			}else {
				franchiser.setAllowFranchisorContactAccess(Boolean.FALSE);	
			}
			franchiser.update();
		}else if(appUser.getRole() != null && appUser.getRole().getUserRole().equals(UserRole.BROKER)) {
			Broker broker = appUser.getBroker();
			if(this.allowAccess != null) {
				broker.setShareBrokerContactToBuyer(Boolean.TRUE);
			}else {
				broker.setShareBrokerContactToBuyer(Boolean.FALSE);	
			}
			broker.update();
		}
		}
		return appResponse;
	}
	
	public Address updateAddressData(Address address) {
		City city = City.find.byId(this.getCityId());
		address.setCity(city);
		address.setState(city.getState());
		address.setCountry(city.getCountry());
		address.setDetailedAddress(this.getAddressDescription());
		address.setZipCode(this.getPincode());
		if(this.getLatitude() != null && this.getLongitude() != null) {
			address.setLatitude(this.getLatitude());
			address.setLongitude(this.getLongitude());
		}
		if(StringUtils.isNotEmpty(this.getGoogleAddress())) {
			address.setGoogleAddress(this.getGoogleAddress());
		}
		return address;
	}

}
