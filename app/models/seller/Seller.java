package models.seller;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;
import models.Role;

@Entity
public class Seller extends BaseEntity{
	
	@OneToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	
	private Boolean allowBuyerToContact = Boolean.FALSE;
	
	
	// Package
	
	// Messages
	
	public Boolean getAllowBuyerToContact() {
		return allowBuyerToContact;
	}

	public void setAllowBuyerToContact(Boolean allowBuyerToContact) {
		this.allowBuyerToContact = allowBuyerToContact;
	}

	public static Model.Finder<Long, Seller> find = new Model.Finder<Long, Seller>(Seller.class);

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
