package models.buyer;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;
import models.Role;

@Entity
public class Buyer extends BaseEntity{
	
	@OneToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	private Boolean shareContactDetails = Boolean.FALSE;
	
	// Messages
	
	// Packages
	
	public static Model.Finder<Long, Buyer> find = new Model.Finder<Long, Buyer>(Buyer.class);

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Boolean getShareContactDetails() {
		return shareContactDetails;
	}

	public void setShareContactDetails(Boolean shareContactDetails) {
		this.shareContactDetails = shareContactDetails;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
