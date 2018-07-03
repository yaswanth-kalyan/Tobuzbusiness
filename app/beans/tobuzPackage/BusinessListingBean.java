package beans.tobuzPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.Expr;

import beans.AddressBean;
import constants.AlertType;
import constants.BusinessListingStatus;
import constants.ListingFor;
import constants.ListingSalePriceType;
import constants.ListingType;
import constants.LoginType;
import constants.TimePeroid;
import models.Address;
import models.AppUser;
import models.AreaMetrics;
import models.Country;
import models.ListingKeyword;
import models.Role;
import models.business.BusinessFeature;
import models.business.BusinessListing;
import models.business.BusinessListingFeatureInfo;
import models.business.BusinessListingOutLet;
import models.business.BusinessListingSubcategoryInfo;
import models.business.Category;
import models.business.SubCategory;
import util.AppResponse;
import util.ResponseMessage;
import util.StringUtils;

public class BusinessListingBean extends AddressBean {

	private Long businessListingId;

	private List<Long> featureList = new ArrayList<Long>();

	private List<Long> subCategoryList = new ArrayList<Long>();
	
	private List<String> searchKeys = new ArrayList<String>();

	private Long listedByUserId;

	private Long roleId;

	// Business/franchise/Commercial
	private String listingType;

	// Rent / Sale(or)Lease / Others
	private String listingFor;

	// Extra option for franchise resale Master/Unit
	private String franchisePartnerType;

	// BusinessTitle
	private String title;

	private String listingDescription;

	private String businessDescription;

	private Long categoryId;

	private String seoKeyword;

	private Long businessAddressCountryId;

	// SEO params
	private String slugTitle;

	private String metaTitle;

	private String metaDescription;

	private String metaUrl;

	private BusinessListingStatus businessListingStatus;

	// contact details
	private String name;
	
	private String email;
	
	private String countryCode;

	private String contactNumber;

	private String websiteURL;

	private Boolean showContactDetailsOnListing = Boolean.FALSE;

	// company information
	private String companyType;
	private String companyStatus;
	private Float businessTurnover;
	private String businessTurnoverPer;
	private Float businessExpenses;
	private String businessExpensesPer;
	private Float businessTotalExpenses;

	/**
	 * profit and price information
	 */
	private Float grossProfit;
	private Float netProfit;
	private Boolean isPlantFixturesFittingsIncluded = Boolean.FALSE;
	private Boolean isEstimatedStockIncluded = Boolean.TRUE;
	private String listingSalePriceType;
	private Float totalBusinessSalePrice;

	/**
	 * Logo,gallery & video urls
	 */
	private Long logoId;
	private Long documentId;
	private String videoURL;

	/**
	 * Business operational information
	 */
	private Float size;
	private Long areaMetrics;
	private Integer yearOfEstablishment;
	private Integer noOfEmployees;
	private Integer noOfTradingHours;
	private Boolean isBusinessSupportingAndTrading = Boolean.FALSE;
	private String businessOutletDescription;

	/**
	 * describe your business
	 */
	// 1facebook 2.linkedin 3.google+ 4.twitter
	private String businessLinkType;
	private String businessURL;

	/**
	 * If it is distress sale Needs to be in paid package
	 */
	private Boolean isDistressSale = Boolean.FALSE;
	
	private Long paymentId;

	public Long getPaymentId() {
		return paymentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getBusinessListingId() {
		return businessListingId;
	}

	public void setBusinessListingId(Long businessListingId) {
		this.businessListingId = businessListingId;
	}

	public Long getListedByUserId() {
		return listedByUserId;
	}

	public void setListedByUserId(Long listedByUserId) {
		this.listedByUserId = listedByUserId;
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

	public String getListingFor() {
		return listingFor;
	}

	public void setListingFor(String listingFor) {
		this.listingFor = listingFor;
	}

	public String getFranchisePartnerType() {
		return franchisePartnerType;
	}

	public void setFranchisePartnerType(String franchisePartnerType) {
		this.franchisePartnerType = franchisePartnerType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getListingDescription() {
		return listingDescription;
	}

	public void setListingDescription(String listingDescription) {
		this.listingDescription = listingDescription;
	}

	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public Long getBusinessAddressCountryId() {
		return businessAddressCountryId;
	}

	public void setBusinessAddressCountryId(Long businessAddressCountryId) {
		this.businessAddressCountryId = businessAddressCountryId;
	}

	public String getSlugTitle() {
		return slugTitle;
	}

	public void setSlugTitle(String slugTitle) {
		this.slugTitle = slugTitle;
	}

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	public String getMetaDescription() {
		return metaDescription;
	}

	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	public String getMetaUrl() {
		return metaUrl;
	}

	public void setMetaUrl(String metaUrl) {
		this.metaUrl = metaUrl;
	}

	public BusinessListingStatus getBusinessListingStatus() {
		return businessListingStatus;
	}

	public void setBusinessListingStatus(BusinessListingStatus businessListingStatus) {
		this.businessListingStatus = businessListingStatus;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	public Boolean getShowContactDetailsOnListing() {
		return showContactDetailsOnListing;
	}

	public void setShowContactDetailsOnListing(Boolean showContactDetailsOnListing) {
		this.showContactDetailsOnListing = showContactDetailsOnListing;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCompanyStatus() {
		return companyStatus;
	}

	public void setCompanyStatus(String companyStatus) {
		this.companyStatus = companyStatus;
	}

	public Float getBusinessTurnover() {
		return businessTurnover;
	}

	public void setBusinessTurnover(Float businessTurnover) {
		this.businessTurnover = businessTurnover;
	}

	public String getBusinessTurnoverPer() {
		return businessTurnoverPer;
	}

	public void setBusinessTurnoverPer(String businessTurnoverPer) {
		this.businessTurnoverPer = businessTurnoverPer;
	}

	public Float getBusinessExpenses() {
		return businessExpenses;
	}

	public void setBusinessExpenses(Float businessExpenses) {
		this.businessExpenses = businessExpenses;
	}

	public String getBusinessExpensesPer() {
		return businessExpensesPer;
	}

	public void setBusinessExpensesPer(String businessExpensesPer) {
		this.businessExpensesPer = businessExpensesPer;
	}

	public Float getBusinessTotalExpenses() {
		return businessTotalExpenses;
	}

	public void setBusinessTotalExpenses(Float businessTotalExpenses) {
		this.businessTotalExpenses = businessTotalExpenses;
	}

	public Float getGrossProfit() {
		return grossProfit;
	}

	public void setGrossProfit(Float grossProfit) {
		this.grossProfit = grossProfit;
	}

	public Float getNetProfit() {
		return netProfit;
	}

	public void setNetProfit(Float netProfit) {
		this.netProfit = netProfit;
	}

	public Boolean getIsPlantFixturesFittingsIncluded() {
		return isPlantFixturesFittingsIncluded;
	}

	public void setIsPlantFixturesFittingsIncluded(Boolean isPlantFixturesFittingsIncluded) {
		this.isPlantFixturesFittingsIncluded = isPlantFixturesFittingsIncluded;
	}

	public Boolean getIsEstimatedStockIncluded() {
		return isEstimatedStockIncluded;
	}

	public void setIsEstimatedStockIncluded(Boolean isEstimatedStockIncluded) {
		this.isEstimatedStockIncluded = isEstimatedStockIncluded;
	}

	public String getListingSalePriceType() {
		return listingSalePriceType;
	}

	public void setListingSalePriceType(String listingSalePriceType) {
		this.listingSalePriceType = listingSalePriceType;
	}

	public Long getAreaMetrics() {
		return areaMetrics;
	}

	public void setAreaMetrics(Long areaMetrics) {
		this.areaMetrics = areaMetrics;
	}

	public Float getTotalBusinessSalePrice() {
		return totalBusinessSalePrice;
	}

	public void setTotalBusinessSalePrice(Float totalBusinessSalePrice) {
		this.totalBusinessSalePrice = totalBusinessSalePrice;
	}

	public Long getLogoId() {
		return logoId;
	}

	public void setLogoId(Long logoId) {
		this.logoId = logoId;
	}

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public Integer getYearOfEstablishment() {
		return yearOfEstablishment;
	}

	public void setYearOfEstablishment(Integer yearOfEstablishment) {
		this.yearOfEstablishment = yearOfEstablishment;
	}

	public Integer getNoOfEmployees() {
		return noOfEmployees;
	}

	public void setNoOfEmployees(Integer noOfEmployees) {
		this.noOfEmployees = noOfEmployees;
	}

	public Integer getNoOfTradingHours() {
		return noOfTradingHours;
	}

	public void setNoOfTradingHours(Integer noOfTradingHours) {
		this.noOfTradingHours = noOfTradingHours;
	}

	public Boolean getIsBusinessSupportingAndTrading() {
		return isBusinessSupportingAndTrading;
	}

	public void setIsBusinessSupportingAndTrading(Boolean isBusinessSupportingAndTrading) {
		this.isBusinessSupportingAndTrading = isBusinessSupportingAndTrading;
	}

	public String getBusinessOutletDescription() {
		return businessOutletDescription;
	}

	public void setBusinessOutletDescription(String businessOutletDescription) {
		this.businessOutletDescription = businessOutletDescription;
	}

	public String getBusinessLinkType() {
		return businessLinkType;
	}

	public void setBusinessLinkType(String businessLinkType) {
		this.businessLinkType = businessLinkType;
	}

	public String getBusinessURL() {
		return businessURL;
	}

	public void setBusinessURL(String businessURL) {
		this.businessURL = businessURL;
	}

	public Boolean getIsDistressSale() {
		return isDistressSale;
	}

	public void setIsDistressSale(Boolean isDistressSale) {
		this.isDistressSale = isDistressSale;
	}

	public List<Long> getFeatureList() {
		return featureList;
	}

	public void setFeatureList(List<Long> featureList) {
		this.featureList = featureList;
	}

	public List<Long> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<Long> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public List<String> getSearchKeys() {
		return searchKeys;
	}

	public void setSearchKeys(List<String> searchKeys) {
		this.searchKeys = searchKeys;
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
		AppResponse appResponse = new AppResponse();
		appResponse.setAlertType(AlertType.SUCCESS);

		if (StringUtils.isEmpty(this.getListingType())) {
			return new AppResponse(ResponseMessage.LISTING_TYPE_REQUIRED);
		}
		if (StringUtils.isEmpty(this.getListingFor())) {
			return new AppResponse(ResponseMessage.LISTING_FOR_REQUIRED);
		}
		if (StringUtils.isEmpty(this.getTitle())) {
			return new AppResponse(ResponseMessage.LISTING_TITLE_REQUIRED);
		}
		if (this.getFeatureList() == null || this.getFeatureList().size() == 0) {
			return new AppResponse(ResponseMessage.LISTING_FEATURE_REQUIRED);
		}
		if (this.getCategoryId() == null || this.getCategoryId() == 0) {
			return new AppResponse(ResponseMessage.LISTING_CATEGORY_REQUIRED);
		}
		if (this.getSubCategoryList() == null || this.getSubCategoryList().size() == 0) {
			return new AppResponse(ResponseMessage.LISTING_SUBCATEGORY_REQUIRED);
		}
		if (this.getCountryId() == null || this.getCountryId() == 0) {
			return new AppResponse(ResponseMessage.LISTING_COUNTRY_REQUIRED);
		}
		if (this.getCityId() == null || this.getCityId() == 0) {
			return new AppResponse(ResponseMessage.LISTING_CITY_REQUIRED);
		}
		if (this.getStateId() == null || this.getStateId() == 0) {
			return new AppResponse(ResponseMessage.LISTING_STATE_REQUIRED);
		}
		if (this.getLatitude() == null || this.getLongitude() == null) {
			return new AppResponse(ResponseMessage.LISTING_GOOGLE_ADDRESS_REQUIRED);
		}
		return appResponse;
	}

	public AppResponse toBusinessListing() {
		AppResponse response = validateBean();
		if (response.isSuccess()) {
			BusinessListing businessListing = null;
			if (this.getBusinessListingId() != null && this.getBusinessListingId() != 0) {
				businessListing = BusinessListing.find.byId(this.getBusinessListingId());
				businessListing = updateBusinessListingData(businessListing);
				businessListing.update();
				BusinessListingOutLet businessListingOutLet = businessListing.getBusinessListingOutLet();
				businessListingOutLet = updateBusinessListingOutletData(businessListingOutLet);
				businessListingOutLet.update();
			} else {
				businessListing = new BusinessListing();
				businessListing = updateBusinessListingData(businessListing);
				businessListing.save();
				BusinessListingOutLet businessListingOutLet = new BusinessListingOutLet();
				businessListingOutLet = updateBusinessListingOutletData(businessListingOutLet);
				businessListingOutLet.save();
			}
			updateBusinessListingFeatures(businessListing);
			updateBusinessSubCategories(businessListing);
			updateSearchKeys(businessListing);
		}
		return response;
	}

	public BusinessListing updateBusinessListingData(BusinessListing businessListing) {
		businessListing.setListedByUser(AppUser.find.byId(this.listedByUserId));
		businessListing.setRole(Role.find.byId(this.roleId));
		businessListing.setBusinessAddressCountry(Country.find.byId(this.getCountryId()));
		if(StringUtils.isNotEmpty(this.listingType)) {
		businessListing.setListingType(ListingType.valueOf(this.listingType));
		}
		if(StringUtils.isNotEmpty(this.listingFor)) {
		businessListing.setListingFor(ListingFor.valueOf(this.listingFor));
		}
		businessListing.setTitle(this.title);
		businessListing.setListingDescription(this.getListingDescription());
		businessListing.setSlugTitle(this.getTitle());
		businessListing.setCategory(Category.find.byId(this.categoryId));
		businessListing.setSeoKeyword(this.seoKeyword);
		businessListing.setMetaDescription(this.getMetaDescription());
		businessListing.setMetaTitle(this.getMetaTitle());
		businessListing.setMetaUrl(this.getMetaUrl());
		businessListing.setBusinessListingStatus(BusinessListingStatus.UNDER_REVIEW);
		businessListing.setCountryCode(this.getCountryCode());
		if (this.getShowContactDetailsOnListing()) {
			businessListing.setShowContactDetailsOnListing(Boolean.TRUE);
		} else {
			businessListing.setShowContactDetailsOnListing(Boolean.FALSE);
		}
		businessListing.setContactNumber(this.getContactNumber());
		businessListing.setWebsiteURL(this.getWebsiteURL());
		businessListing.setPostedOn(new Date());
		if(this.isDistressSale != null) {
			businessListing.setIsDistressSale(Boolean.TRUE);
		}
		businessListing.setEmail(this.getEmail());
		businessListing.setName(this.getName());
		return businessListing;
	}

	public BusinessListingOutLet updateBusinessListingOutletData(BusinessListingOutLet businessListingOutLet) {

		Address outletAddress = null;
		if (businessListingOutLet != null && businessListingOutLet.getBusinessAddress() != null) {
			outletAddress = businessListingOutLet.getBusinessAddress();
			outletAddress = updateAddressData(outletAddress);
			outletAddress.update();
		} else {
			outletAddress = new Address();
			outletAddress = updateAddressData(outletAddress);
			outletAddress.save();
		}

		businessListingOutLet.setBusinessAddress(outletAddress);
		businessListingOutLet.setCompanyType(this.getCompanyType());
		if(this.getCompanyStatus() != null) {
		businessListingOutLet.setIsCompanyActive(Boolean.TRUE);
		}else {
			businessListingOutLet.setIsCompanyActive(Boolean.FALSE);	
		}
		businessListingOutLet.setBusinessTurnover(this.getBusinessTurnover());
		if(StringUtils.isNotEmpty(this.getBusinessTurnoverPer())) {
		businessListingOutLet.setBusinessTurnoverPer(TimePeroid.valueOf(this.getBusinessTurnoverPer()));
		}else {
			businessListingOutLet.setBusinessTurnoverPer(TimePeroid.ANNUAL);
		}
		if(StringUtils.isNotEmpty(this.getBusinessExpensesPer())) {
		businessListingOutLet.setBusinessTurnoverPer(TimePeroid.valueOf(this.getBusinessExpensesPer()));
		}else {
			businessListingOutLet.setBusinessTurnoverPer(TimePeroid.ANNUAL);
		}
		businessListingOutLet.setBusinessTotalExpenses(this.getBusinessTotalExpenses());
		businessListingOutLet.setGrossProfit(this.getGrossProfit());
		businessListingOutLet.setNetProfit(this.getNetProfit());
		if (this.isPlantFixturesFittingsIncluded != null) {
			businessListingOutLet.setIsPlantFixturesFittingsIncluded(Boolean.TRUE);
		}
		if (this.isEstimatedStockIncluded != null) {
			businessListingOutLet.setIsEstimatedStockIncluded(Boolean.TRUE);
		}
		if(StringUtils.isNotEmpty(this.getListingSalePriceType())) {
		businessListingOutLet.setListingSalePriceType(ListingSalePriceType.valueOf(this.getListingSalePriceType()));
		}else {
			businessListingOutLet.setListingSalePriceType(ListingSalePriceType.SALE_PRICE);
		}
		businessListingOutLet.setTotalBusinessSalePrice(this.getTotalBusinessSalePrice());

		if (this.logoId != null) {
			businessListingOutLet.setLogoId(this.getLogoId());
		}
		if (this.logoId != null) {
			businessListingOutLet.setDocumentId(this.getDocumentId());
		}
		if (StringUtils.isNotEmpty(this.videoURL)) {
			businessListingOutLet.setVideoURL(this.getVideoURL());
		}
		businessListingOutLet.setSize(this.getSize());
		businessListingOutLet.setMetrics(AreaMetrics.find.byId(this.areaMetrics));
		businessListingOutLet.setYearOfEstablishment(this.getYearOfEstablishment());
		businessListingOutLet.setNoOfEmployees(this.getNoOfEmployees());
		businessListingOutLet.setNoOfTradingHours(this.getNoOfTradingHours());
		if(this.isBusinessSupportingAndTrading != null) {
		businessListingOutLet.setIsBusinessSupportingAndTrading(Boolean.TRUE);
		}
		businessListingOutLet.setBusinessDescription(this.getBusinessDescription());
		if(StringUtils.isNotEmpty(this.getBusinessLinkType())) {
		businessListingOutLet.setBusinessLinkType(LoginType.valueOf(this.getBusinessLinkType()));
		}
		businessListingOutLet.setBusinessURL(this.getBusinessURL());
		return businessListingOutLet;
	}
	
	public void updateBusinessListingFeatures(BusinessListing businessListing) {
		List<Long> updatedList = new ArrayList<Long>();
		if(this.getFeatureList().size() > 0) {
			for(Long feature : this.getFeatureList()) {
				BusinessFeature businessFeature = BusinessFeature.find.byId(feature);
				BusinessListingFeatureInfo businessListingFeatureInfo = BusinessListingFeatureInfo.createBusinessFeatureListingInfo(businessListing,businessFeature);
				updatedList.add(businessListingFeatureInfo.getId());
			}
		}
		if(updatedList.size() > 0) {
			List<BusinessListingFeatureInfo> unUsedListingFeatures = BusinessListingFeatureInfo.find.where().eq("businessListing", businessListing).not(Expr.in("id", updatedList)).findList();
			Ebean.deleteAll(unUsedListingFeatures);
		}
		
	}
	
	public void updateBusinessSubCategories(BusinessListing businessListing) {
		List<Long> updatedList = new ArrayList<Long>();
		if(this.getSubCategoryList().size() > 0) {
			for(Long subCategoryId : this.getSubCategoryList()) {
				SubCategory subCategory = SubCategory.find.byId(subCategoryId);
				BusinessListingSubcategoryInfo businessListingSubcategoryInfo = BusinessListingSubcategoryInfo.createBusinessListingSubcategoryInfo(businessListing,subCategory);
				updatedList.add(businessListingSubcategoryInfo.getId());
			}
		}
		if(updatedList.size() > 0) {
			List<BusinessListingSubcategoryInfo> unUsedListingSubCategories = BusinessListingSubcategoryInfo.find.where().eq("businessListing", businessListing).not(Expr.in("id", updatedList)).findList();
			Ebean.deleteAll(unUsedListingSubCategories);
		}
		
	}
	
	public void updateSearchKeys(BusinessListing businessListing) {
		
		List<Long> updatedList = new ArrayList<Long>();
		if(this.getSearchKeys().size() > 0) {
			for(String searchKey : this.getSearchKeys()) {
			ListingKeyword listingKeyword = ListingKeyword.createListingKeyword(businessListing,searchKey);
				updatedList.add(listingKeyword.getId());
			}
		}
		if(updatedList.size() > 0) {
			List<ListingKeyword> unusedListingKeywords = ListingKeyword.find.where().eq("businessListing", businessListing).not(Expr.in("id", updatedList)).findList();
			Ebean.deleteAll(unusedListingKeywords);
		}
		
	}

}
