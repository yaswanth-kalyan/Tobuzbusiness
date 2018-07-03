package models.business;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import constants.BusinessListingStatus;
import constants.ListingSalePriceType;
import constants.LoginType;
import constants.TimePeroid;
import models.Address;
import models.AppUser;
import models.AreaMetrics;
import models.BaseEntity;

@Entity
public class BusinessListingOutLet extends BaseEntity{
	
	
	@OneToOne
	private Address businessAddress;
	
	// company information
	public String companyType;
	public Boolean isCompanyActive;
	public Float businessTurnover;
	private TimePeroid businessTurnoverPer;
	
	
	// businessExpenses;
	public Float businessTotalExpenses;
	private TimePeroid businessExpensesPer;


	/**
	 *  profit and price information
	 */
	private Float  grossProfit;
	private Float netProfit;
	private Boolean isPlantFixturesFittingsIncluded = Boolean.FALSE;
	private Boolean isEstimatedStockIncluded = Boolean.TRUE;
	private ListingSalePriceType listingSalePriceType;
	private Float totalBusinessSalePrice;
	

	/**
	 * Logo,gallery & video urls
	 */
	private  Long logoId;
	private Long documentId;
	private String videoURL;


	/**
	 * 	Business operational information
	 */
	// private String size;
	private Float size;
	private AreaMetrics metrics;
	private Integer yearOfEstablishment;
	private Integer noOfEmployees;
	private Integer noOfTradingHours;
	private Boolean isBusinessSupportingAndTrading = Boolean.FALSE;
	@Column(columnDefinition="TEXT")
	private String businessDescription;
	
	/**
	 * 	describe your business
	 */
	//1facebook 2.linkedin 3.google+ 4.twitter
	private LoginType businessLinkType;
	private String businessURL;

	
	
	
	public static Model.Finder<Long, BusinessListingOutLet> find = new Model.Finder<Long, BusinessListingOutLet>(BusinessListingOutLet.class);

	public Address getBusinessAddress() {
		return businessAddress;
	}

	public void setBusinessAddress(Address businessAddress) {
		this.businessAddress = businessAddress;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	

	public Boolean getIsCompanyActive() {
		return isCompanyActive;
	}

	public void setIsCompanyActive(Boolean isCompanyActive) {
		this.isCompanyActive = isCompanyActive;
	}

	public Float getBusinessTurnover() {
		return businessTurnover;
	}

	public void setBusinessTurnover(Float businessTurnover) {
		this.businessTurnover = businessTurnover;
	}

	public TimePeroid getBusinessTurnoverPer() {
		return businessTurnoverPer;
	}

	public void setBusinessTurnoverPer(TimePeroid businessTurnoverPer) {
		this.businessTurnoverPer = businessTurnoverPer;
	}

	public TimePeroid getBusinessExpensesPer() {
		return businessExpensesPer;
	}

	public void setBusinessExpensesPer(TimePeroid businessExpensesPer) {
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

	public ListingSalePriceType getListingSalePriceType() {
		return listingSalePriceType;
	}

	public void setListingSalePriceType(ListingSalePriceType listingSalePriceType) {
		this.listingSalePriceType = listingSalePriceType;
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

	public String getBusinessDescription() {
		return businessDescription;
	}

	public void setBusinessDescription(String businessDescription) {
		this.businessDescription = businessDescription;
	}

	public LoginType getBusinessLinkType() {
		return businessLinkType;
	}

	public void setBusinessLinkType(LoginType businessLinkType) {
		this.businessLinkType = businessLinkType;
	}

	public String getBusinessURL() {
		return businessURL;
	}

	public void setBusinessURL(String businessURL) {
		this.businessURL = businessURL;
	}

	public Float getSize() {
		return size;
	}

	public void setSize(Float size) {
		this.size = size;
	}

	public AreaMetrics getMetrics() {
		return metrics;
	}

	public void setMetrics(AreaMetrics metrics) {
		this.metrics = metrics;
	}

	
	

}
