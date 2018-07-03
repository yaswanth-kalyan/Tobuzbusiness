package models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import models.business.BusinessAdvert;
import models.business.BusinessListing;

@Entity
public class FavouriteBusinessListing extends BaseEntity{
	
	@ManyToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	@ManyToOne
	private BusinessListing businessListing;
	
	@ManyToOne
	private BusinessAdvert businessAdvert;
	
	private Date addedOn;
	
	public static Model.Finder<Long, FavouriteBusinessListing> find = new Model.Finder<Long, FavouriteBusinessListing>(FavouriteBusinessListing.class);

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
	}

	public Date getAddedOn() {
		return addedOn;
	}

	public void setAddedOn(Date addedOn) {
		this.addedOn = addedOn;
	}

	public BusinessAdvert getBusinessAdvert() {
		return businessAdvert;
	}

	public void setBusinessAdvert(BusinessAdvert businessAdvert) {
		this.businessAdvert = businessAdvert;
	}
	
	

}
