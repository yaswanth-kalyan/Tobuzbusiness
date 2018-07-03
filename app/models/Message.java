package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import models.business.BusinessAdvert;
import models.business.BusinessListing;

@Entity
public class Message extends BaseEntity{
	
	@ManyToOne
	private AppUser fromUser;
	
	@ManyToOne
	private Role fromUserRole;
	
	@ManyToOne
	private AppUser toAppUser;
	
	@ManyToOne
	private Role toUserRole;
	
	@Column(columnDefinition="TEXT")
	private String subject;
	
	@Column(columnDefinition="TEXT")
	private String bodytext;
	
	private Boolean isReceiverVerified = Boolean.FALSE;
	
	@ManyToOne
	private BusinessListing businessListing;
	
	@ManyToOne
	private BusinessAdvert businessAdvert;
	
	private Date messageSentOn;
	
	public static Model.Finder<Long, Message> find = new Model.Finder<Long, Message>(Message.class);

	public AppUser getFromUser() {
		return fromUser;
	}

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	public Date getMessageSentOn() {
		return messageSentOn;
	}

	public void setMessageSentOn(Date messageSentOn) {
		this.messageSentOn = messageSentOn;
	}

	public void setFromUser(AppUser fromUser) {
		this.fromUser = fromUser;
	}

	public Role getFromUserRole() {
		return fromUserRole;
	}

	public void setFromUserRole(Role fromUserRole) {
		this.fromUserRole = fromUserRole;
	}

	public AppUser getToAppUser() {
		return toAppUser;
	}

	public void setToAppUser(AppUser toAppUser) {
		this.toAppUser = toAppUser;
	}

	public Role getToUserRole() {
		return toUserRole;
	}

	public void setToUserRole(Role toUserRole) {
		this.toUserRole = toUserRole;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodytext() {
		return bodytext;
	}

	public void setBodytext(String bodytext) {
		this.bodytext = bodytext;
	}

	public Boolean getIsReceiverVerified() {
		return isReceiverVerified;
	}

	public void setIsReceiverVerified(Boolean isReceiverVerified) {
		this.isReceiverVerified = isReceiverVerified;
	}

}
