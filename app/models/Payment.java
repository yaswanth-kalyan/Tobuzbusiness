package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import models.business.BusinessListing;
import models.tobuzpackage.UserTobuzServicePackageInfo;

@Entity
public class Payment extends BaseEntity{
	
	@ManyToOne
	private AppUser appUser;
	
	private Float paidAmount;
	
	private String transactionId;
	
	private String transactionStatus;
	
	@Column(columnDefinition="TEXT")
	private String transactionDescription;
	
	private Date transactionDoneOn;
	
	@ManyToOne
	private BusinessListing businessListing;
	
	@ManyToOne
	private UserTobuzServicePackageInfo userTobuzServicePackageInfo;
	
	private Boolean isDistressSalePayment = Boolean.FALSE;
	
	private Boolean isPckageSalePayment = Boolean.FALSE;
	
	public static Model.Finder<Long, Payment> find = new Model.Finder<Long, Payment>(Payment.class);

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Float getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(Float paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public String getTransactionDescription() {
		return transactionDescription;
	}

	public void setTransactionDescription(String transactionDescription) {
		this.transactionDescription = transactionDescription;
	}

	public Date getTransactionDoneOn() {
		return transactionDoneOn;
	}

	public void setTransactionDoneOn(Date transactionDoneOn) {
		this.transactionDoneOn = transactionDoneOn;
	}

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	public UserTobuzServicePackageInfo getUserTobuzServicePackageInfo() {
		return userTobuzServicePackageInfo;
	}

	public void setUserTobuzServicePackageInfo(UserTobuzServicePackageInfo userTobuzServicePackageInfo) {
		this.userTobuzServicePackageInfo = userTobuzServicePackageInfo;
	}

	public Boolean getIsDistressSalePayment() {
		return isDistressSalePayment;
	}

	public void setIsDistressSalePayment(Boolean isDistressSalePayment) {
		this.isDistressSalePayment = isDistressSalePayment;
	}

	public Boolean getIsPckageSalePayment() {
		return isPckageSalePayment;
	}

	public void setIsPckageSalePayment(Boolean isPckageSalePayment) {
		this.isPckageSalePayment = isPckageSalePayment;
	}
	
	

}
