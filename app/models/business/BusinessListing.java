package models.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import constants.BusinessListingStatus;
import constants.FranchiseFor;
import constants.ListingFor;
import constants.ListingType;
import models.Address;
import models.AppUser;
import models.BaseEntity;
import models.Country;
import models.ListingKeyword;
import models.Role;
import util.StringUtils;

@Entity
public class BusinessListing extends BaseEntity{
	
	@ManyToOne
	private AppUser listedByUser;
	
	@ManyToOne
	private Role role;
	
	private String listingId;
	
	private FranchiseFor franchiseFor;
	
	// Business,franchise,commercial and business_advert
	private ListingType listingType;
	
	
	// Rent / Sale(or)Lease / Others
	private ListingFor listingFor;
	
	// Extra option for franchise resale Master/Unit
	// private FranchisePartnerType franchisePartnerType;
	
	//BusinessTitle
	private String title;
	
	private String listingDescription;
	
	@ManyToOne
	private Category Category;
	
	@Column(columnDefinition="TEXT")
	private String seoKeyword;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="businessListing")
	private List<ListingKeyword> keywordList = new ArrayList<ListingKeyword>();
	
	@ManyToOne
	private Country businessAddressCountry;
	

	// SEO params
	private String slugTitle;
	
	private String metaTitle;
	
	private String metaDescription;
	
	private String metaUrl;
	
	private BusinessListingStatus businessListingStatus;
	
	//contact details
	
	private String name;
	
	private String email;
	
	private String countryCode;
	
	private String contactNumber;
	
	private String websiteURL;
	
	private Boolean showContactDetailsOnListing = Boolean.FALSE; 
	
	private Date postedOn;
	
	private Date soldOn;
	
	@ManyToOne
	private AppUser soldMarkedBy;
	
	@OneToOne
	private BusinessListingOutLet businessListingOutLet;
	
	/**
	 * If it is distress sale
	 * Needs to be in paid package
	 */
	private Boolean isDistressSale =  Boolean.FALSE;
	
	private String description;
	
	private String searchIndex;
	
	private Boolean isRecomendedByAdmin = Boolean.FALSE;
	
	public static Model.Finder<Long, BusinessListing> find = new Model.Finder<Long, BusinessListing>(BusinessListing.class);

	
	public Boolean getIsRecomendedByAdmin() {
		return isRecomendedByAdmin;
	}

	public void setIsRecomendedByAdmin(Boolean isRecomendedByAdmin) {
		this.isRecomendedByAdmin = isRecomendedByAdmin;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getListingId() {
		return listingId;
	}

	public void setListingId(String listingId) {
		this.listingId = listingId;
	}

	public String getSearchIndex() {
		return searchIndex;
	}

	public void setSearchIndex(String searchIndex) {
		this.searchIndex = searchIndex;
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

	public ListingFor getListingFor() {
		return listingFor;
	}

	public void setListingFor(ListingFor listingFor) {
		this.listingFor = listingFor;
	}

	public Category getCategory() {
		return Category;
	}

	public void setCategory(Category category) {
		Category = category;
	}

	public String getSeoKeyword() {
		return seoKeyword;
	}

	public void setSeoKeyword(String seoKeyword) {
		this.seoKeyword = seoKeyword;
	}

	public Country getBusinessAddressCountry() {
		return businessAddressCountry;
	}

	public void setBusinessAddressCountry(Country businessAddressCountry) {
		this.businessAddressCountry = businessAddressCountry;
	}

	public BusinessListingOutLet getBusinessListingOutLet() {
		return businessListingOutLet;
	}

	public void setBusinessListingOutLet(BusinessListingOutLet businessListingOutLet) {
		this.businessListingOutLet = businessListingOutLet;
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

	public AppUser getListedByUser() {
		return listedByUser;
	}

	public void setListedByUser(AppUser listedByUser) {
		this.listedByUser = listedByUser;
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

	public Date getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	public Date getSoldOn() {
		return soldOn;
	}

	public void setSoldOn(Date soldOn) {
		this.soldOn = soldOn;
	}

	public AppUser getSoldMarkedBy() {
		return soldMarkedBy;
	}

	public void setSoldMarkedBy(AppUser soldMarkedBy) {
		this.soldMarkedBy = soldMarkedBy;
	}

	public FranchiseFor getFranchiseFor() {
		return franchiseFor;
	}

	public void setFranchiseFor(FranchiseFor franchiseFor) {
		this.franchiseFor = franchiseFor;
	}

	public List<ListingKeyword> getKeywordList() {
		return keywordList;
	}

	public void setKeywordList(List<ListingKeyword> keywordList) {
		this.keywordList = keywordList;
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

	public Boolean getIsDistressSale() {
		return isDistressSale;
	}

	public void setIsDistressSale(Boolean isDistressSale) {
		this.isDistressSale = isDistressSale;
	}
	
	

	@Override
	public void save(){
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getTitle().trim().toLowerCase()+""+this.getListingId()+""+this.getBusinessListingStatus().toString().toLowerCase());
		if(this.getBusinessListingOutLet() != null && this.getBusinessListingOutLet().getBusinessAddress() != null){
			Address address = this.getBusinessListingOutLet().getBusinessAddress();
			if(StringUtils.isNotEmpty(address.getDetailedAddress())){
				stringBuilder.append(address.getDetailedAddress().toLowerCase());
			}
			if(address.getCountry() != null){
				stringBuilder.append(address.getCountry().getName().trim().toLowerCase());
			}
			if(address.getState() != null){
				stringBuilder.append(address.getState().getName().trim().toLowerCase());
			}
			if(address.getCity() != null){
				stringBuilder.append(address.getCity().getName().trim().toLowerCase());
			}
		}
		if(this.getCategory() != null){
			stringBuilder.append(this.getCategory().getName().trim().toLowerCase());
		}
		this.setSearchIndex(stringBuilder.toString());
		super.save();
	}

	@Override
	public void update() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.getTitle().trim().toLowerCase()+""+this.getListingId()+""+this.getBusinessListingStatus().toString().toLowerCase());
		if(this.getBusinessListingOutLet() != null && this.getBusinessListingOutLet().getBusinessAddress() != null){
			Address address = this.getBusinessListingOutLet().getBusinessAddress();
			if(StringUtils.isNotEmpty(address.getDetailedAddress())){
				stringBuilder.append(address.getDetailedAddress().toLowerCase());
			}
			if(address.getCountry() != null){
				stringBuilder.append(address.getCountry().getName().trim().toLowerCase());
			}
			if(address.getState() != null){
				stringBuilder.append(address.getState().getName().trim().toLowerCase());
			}
			if(address.getCity() != null){
				stringBuilder.append(address.getCity().getName().trim().toLowerCase());
			}
		}
		if(this.getCategory() != null){
			stringBuilder.append(this.getCategory().getName().trim().toLowerCase());
		}
		this.setSearchIndex(stringBuilder.toString());
		super.update();

	}
	
	
	
	

}
