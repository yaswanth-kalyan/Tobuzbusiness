package beans.tobuzPackage;

import java.util.ArrayList;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

import constants.AlertType;
import constants.BusinessListingStatus;
import constants.ListingType;
import models.Address;
import models.AppUser;
import models.AreaMetrics;
import models.City;
import models.Country;
import models.Role;
import models.State;
import models.business.BusinessAdvert;
import models.business.BusinessAdvertSubcategoryInfo;
import models.business.BusinessCategoryAdvertInfo;
import models.business.Category;
import models.business.SubCategory;
import util.AppResponse;
import util.ResponseMessage;
import util.StringUtils;

public class BusinessAdvertBean {
	

	private Long businessAdvertiseId;

	private Long advertedByUserId;

	private Long roleId;

	/**
	 * Business details Business/Commercial/Franchise
	 */
	private String listingType;
	
	public String investmentRange;

	private Float investmentRangeFrom;

	private Float investmentRangeTo;

	private String advertDescription;

	private List<Long> addressList = new ArrayList<Long>();

	private List<Long> categoryList = new ArrayList<Long>();

	private List<Long> subCategoryList = new ArrayList<Long>();
	
	private Long countryId;
	
	private Long stateId;
	
	private Long cityId;
	
	private String googleSearchAddress;
	
	private Double latitude;
	
	private Double longitude;

	private String title;

	private Float spaceSize;

	private Long spaceUnits;

	private String companyType;

	private String sourceOfBusiness;

	private Integer timeLineInDays;

	private String referenceURL;
	
	private String description;

	private Boolean allowContactDetailsToUsers = Boolean.FALSE;

	private BusinessListingStatus businessAdvertStatus;

	public String getInvestmentRange() {
		return investmentRange;
	}

	public void setInvestmentRange(String investmentRange) {
		this.investmentRange = investmentRange;
	}

	public Long getCountryId() {
		return countryId;
	}

	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Long getCityId() {
		return cityId;
	}

	public void setCityId(Long cityId) {
		this.cityId = cityId;
	}

	public String getGoogleSearchAddress() {
		return googleSearchAddress;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setGoogleSearchAddress(String googleSearchAddress) {
		this.googleSearchAddress = googleSearchAddress;
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

	public Long getAdvertedByUserId() {
		return advertedByUserId;
	}

	public void setAdvertedByUserId(Long advertedByUserId) {
		this.advertedByUserId = advertedByUserId;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getListingType() {
		return listingType;
	}

	public void setListingType(String listingType) {
		this.listingType = listingType;
	}

	public Float getInvestmentRangeFrom() {
		return investmentRangeFrom;
	}

	public void setInvestmentRangeFrom(Float investmentRangeFrom) {
		this.investmentRangeFrom = investmentRangeFrom;
	}

	public Float getInvestmentRangeTo() {
		return investmentRangeTo;
	}

	public void setInvestmentRangeTo(Float investmentRangeTo) {
		this.investmentRangeTo = investmentRangeTo;
	}

	public String getAdvertDescription() {
		return advertDescription;
	}

	public void setAdvertDescription(String advertDescription) {
		this.advertDescription = advertDescription;
	}

	public List<Long> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Long> addressList) {
		this.addressList = addressList;
	}

	public List<Long> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Long> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Long> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<Long> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Float getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(Float spaceSize) {
		this.spaceSize = spaceSize;
	}

	public Long getSpaceUnits() {
		return spaceUnits;
	}

	public void setSpaceUnits(Long spaceUnits) {
		this.spaceUnits = spaceUnits;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getSourceOfBusiness() {
		return sourceOfBusiness;
	}

	public void setSourceOfBusiness(String sourceOfBusiness) {
		this.sourceOfBusiness = sourceOfBusiness;
	}

	public Integer getTimeLineInDays() {
		return timeLineInDays;
	}

	public void setTimeLineInDays(Integer timeLineInDays) {
		this.timeLineInDays = timeLineInDays;
	}

	public String getReferenceURL() {
		return referenceURL;
	}

	public void setReferenceURL(String referenceURL) {
		this.referenceURL = referenceURL;
	}

	public Boolean getAllowContactDetailsToUsers() {
		return allowContactDetailsToUsers;
	}

	public void setAllowContactDetailsToUsers(Boolean allowContactDetailsToUsers) {
		this.allowContactDetailsToUsers = allowContactDetailsToUsers;
	}

	public BusinessListingStatus getBusinessAdvertStatus() {
		return businessAdvertStatus;
	}

	public void setBusinessAdvertStatus(BusinessListingStatus businessAdvertStatus) {
		this.businessAdvertStatus = businessAdvertStatus;
	}

	public Long getBusinessAdvertiseId() {
		return businessAdvertiseId;
	}

	public void setBusinessAdvertiseId(Long businessAdvertiseId) {
		this.businessAdvertiseId = businessAdvertiseId;
	}

	public AppResponse validateBean() {
		AppResponse appResponse = new AppResponse(AlertType.SUCCESS);
		if(this.getAdvertedByUserId() == null || this.getAdvertedByUserId() == 0 || this.getRoleId() == null || this.getRoleId() == 0) {
			return  new AppResponse(ResponseMessage.ACCESS_DENIED);
		}
		return appResponse;
	}

	public AppResponse toBusinessAdvert() {
		AppResponse appResponse = validateBean();
		if (appResponse.isSuccess()) {
			BusinessAdvert businessAdvert = null;
			if (this.getBusinessAdvertiseId() != null && this.getBusinessAdvertiseId() != 0) {
				businessAdvert = BusinessAdvert.find.byId(this.getBusinessAdvertiseId());
				businessAdvert = updateBusinessAdvertData(businessAdvert);
				businessAdvert.update();
			} else {
				businessAdvert = new BusinessAdvert();
				businessAdvert = updateBusinessAdvertData(businessAdvert);
				businessAdvert.save();
			}
			updateAdvertAddressData(businessAdvert);
			updateAdvertCategoryData(businessAdvert);
			updateAdvertSubCategoryData(businessAdvert);
			appResponse.setPayLoad(this);
		}
		return appResponse;
	}

	public BusinessAdvert updateBusinessAdvertData(BusinessAdvert businessAdvert) {
		if (this.getAdvertedByUserId() != null && this.getAdvertedByUserId() != 0) {
			AppUser appUser = AppUser.find.byId(this.getAdvertedByUserId());
			Role role = Role.find.byId(this.getRoleId());
			businessAdvert.setAdvertedByUser(appUser);
			businessAdvert.setRole(role);
			if (StringUtils.isNotEmpty(this.listingType)) {
				businessAdvert.setListingType(ListingType.valueOf(this.listingType));
			}
			businessAdvert.setInvestmentRangeFrom(this.getInvestmentRangeFrom());
			businessAdvert.setInvestmentRangeTo(this.getInvestmentRangeTo());
			businessAdvert.setAdvertDescription(this.getAdvertDescription());
			businessAdvert.setTitle(this.getTitle());
			businessAdvert.setSpaceSize(this.getSpaceSize());
			businessAdvert.setSpaceUnits(AreaMetrics.find.byId(this.spaceUnits));
			businessAdvert.setCompanyType(this.getCompanyType());
			businessAdvert.setSourceOfBusiness(this.getSourceOfBusiness());
			businessAdvert.setTimeLineInDays(this.getTimeLineInDays());
			if (this.getAllowContactDetailsToUsers() != null) {
				businessAdvert.setAllowContactDetailsToUsers(Boolean.TRUE);
			} else {
				businessAdvert.setAllowContactDetailsToUsers(Boolean.FALSE);
			}
			businessAdvert.setReferenceURL(this.getReferenceURL());
			businessAdvert.setBusinessAdvertStatus(businessAdvertStatus.UNDER_REVIEW);
		}
		return businessAdvert;
	}
	
	public void updateAdvertAddressData(BusinessAdvert businessAdvert) {
//		if(this.getAddressList().size() > 0) {
//			for(Long addressId : this.getAddressList()) {
//				Address address = Address.find.byId(addressId);
//				address.setBusinessAdvert(businessAdvert);
//				address.update();		
//			}
//		}
		Address address = new Address();
		address.setCountry(Country.find.byId(this.countryId));
		address.setState(State.find.byId(this.stateId));
		address.setCity(City.find.byId(this.cityId));
		address.setLatitude(this.getLatitude());
		address.setLongitude(this.longitude);
		address.setGoogleAddress(this.getGoogleSearchAddress());
		address.setBusinessAdvert(businessAdvert);
		address.save();
	}
	
	public void updateAdvertCategoryData(BusinessAdvert businessAdvert) {
		List<Long> updatedInfos = new ArrayList<Long>();
		if(this.getCategoryList().size() > 0) {
			for(Long catId : this.getCategoryList()) {
				Category category = Category.find.byId(catId);	
				BusinessCategoryAdvertInfo businessCategoryAdvertInfo = BusinessCategoryAdvertInfo.createBusinessAdvertCategoryInfo(businessAdvert, category);
				updatedInfos.add(businessCategoryAdvertInfo.getId());
			}
		}
		if(updatedInfos.size() > 0) {
			List<BusinessCategoryAdvertInfo> businessCategoryAdvertInfos = BusinessCategoryAdvertInfo.find.where().eq("businessAdvert", businessAdvert).not(Expr.in("id", updatedInfos)).findList();
			Ebean.deleteAll(businessCategoryAdvertInfos);
		}
	}
	
	public void updateAdvertSubCategoryData(BusinessAdvert businessAdvert) {
		List<Long> updatedInfos = new ArrayList<Long>();
		if(this.getSubCategoryList().size() > 0) {
			for(Long subCatId : this.getSubCategoryList()) {
				SubCategory category = SubCategory.find.byId(subCatId);	
				BusinessAdvertSubcategoryInfo businessAdvertSubcategoryInfo = BusinessAdvertSubcategoryInfo.createBusinessAdvertSubCategoryInfo(businessAdvert, category);
				updatedInfos.add(businessAdvertSubcategoryInfo.getId());
			}
		}
		if(updatedInfos.size() > 0) {
			List<BusinessAdvertSubcategoryInfo> businessSubCategoryAdvertInfos = BusinessAdvertSubcategoryInfo.find.where().eq("businessAdvert", businessAdvert).not(Expr.in("id", updatedInfos)).findList();
			Ebean.deleteAll(businessSubCategoryAdvertInfos);
		}
	}
}
