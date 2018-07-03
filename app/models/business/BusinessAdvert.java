package models.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;

import constants.BusinessListingStatus;
import constants.ListingType;
import models.Address;
import models.AppUser;
import models.AreaMetrics;
import models.BaseEntity;
import models.Role;
import util.StringUtils;

@Entity
public class BusinessAdvert extends BaseEntity{
	
	@ManyToOne
	private AppUser advertedByUser;
	
	@ManyToOne
	private Role role;
	
	/**
	 * Business details
	 * Business/Commercial/Franchise
	 */
	private ListingType listingType;
	
	
	private Float investmentRangeFrom;
	
	private Float investmentRangeTo;
	
	@Column(columnDefinition="TEXT")
	private String advertDescription;
	
	// Many addresses
//	@OneToOne
//	private Address preferredLocation;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="businessAdvert")
	private List<Address> addressList = new ArrayList<Address>();
	
	private String title;

	private Float spaceSize;
	
	private AreaMetrics spaceUnits;
	

	// other information
	private String companyType;
	
	private String sourceOfBusiness;
	
	//Time line to acquire the property
	private Integer timeLineInDays;


	//About advertizer
	private String referenceURL;
	
	private Boolean allowContactDetailsToUsers = Boolean.FALSE;
	
	private BusinessListingStatus businessAdvertStatus;

	public String searchIndex;
	
	public static Model.Finder<Long, BusinessAdvert> find = new Model.Finder<Long, BusinessAdvert>(BusinessAdvert.class);

	public AppUser getAdvertedByUser() {
		return advertedByUser;
	}

	public void setAdvertedByUser(AppUser advertedByUser) {
		this.advertedByUser = advertedByUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public ListingType getListingType() {
		return listingType;
	}

	public void setListingType(ListingType listingType) {
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

	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	

	public BusinessListingStatus getBusinessAdvertStatus() {
		return businessAdvertStatus;
	}

	public void setBusinessAdvertStatus(BusinessListingStatus businessAdvertStatus) {
		this.businessAdvertStatus = businessAdvertStatus;
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

	public List<Address> getAddressList() {
		return addressList;
	}

	public void setAddressList(List<Address> addressList) {
		this.addressList = addressList;
	}

	public Float getSpaceSize() {
		return spaceSize;
	}

	public void setSpaceSize(Float spaceSize) {
		this.spaceSize = spaceSize;
	}

	public AreaMetrics getSpaceUnits() {
		return spaceUnits;
	}

	public void setSpaceUnits(AreaMetrics spaceUnits) {
		this.spaceUnits = spaceUnits;
	}

	public Integer getTimeLineInDays() {
		return timeLineInDays;
	}

	public void setTimeLineInDays(Integer timeLineInDays) {
		this.timeLineInDays = timeLineInDays;
	}
	
	public String getSearchIndex() {
		return searchIndex;
	}

	public void setSearchIndex(String searchIndex) {
		this.searchIndex = searchIndex;
	}

	@Override
	public void save(){
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getTitle().trim().toLowerCase()+""+this.getCompanyType()+""+this.getBusinessAdvertStatus().toString().toLowerCase()+""+this.getListingType().toString().toLowerCase());
		
		this.setSearchIndex(stringBuilder.toString());
		super.save();
	}

	@Override
	public void update() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getTitle().trim().toLowerCase()+""+this.getCompanyType()+""+this.getBusinessAdvertStatus().toString().toLowerCase()+""+this.getListingType().toString().toLowerCase());
		this.setSearchIndex(stringBuilder.toString());
		super.update();

	}
	
}
