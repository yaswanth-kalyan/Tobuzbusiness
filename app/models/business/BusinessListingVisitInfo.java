package models.business;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;
import models.Role;

@Entity
public class BusinessListingVisitInfo extends BaseEntity{
	
	@ManyToOne
	private AppUser userVisited;
	
	@ManyToOne
	private Role role;
	
	@ManyToOne
	private BusinessListing businessListing;
	
	public static Model.Finder<Long, BusinessListingVisitInfo> find = new Model.Finder<Long, BusinessListingVisitInfo>(BusinessListingVisitInfo.class);

	public AppUser getUserVisited() {
		return userVisited;
	}

	public void setUserVisited(AppUser userVisited) {
		this.userVisited = userVisited;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}
	
	

}
