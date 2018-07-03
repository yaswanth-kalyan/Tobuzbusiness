package models.tobuzpackage;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import constants.UserRole;
import models.BaseEntity;
import models.Country;

@Entity
public class TobuzPackageService extends BaseEntity{
	
	private String title;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	@ManyToOne
	private Country country;

	private UserRole userRole;
	
	public static Model.Finder<Long, TobuzPackageService> find = new Model.Finder<Long, TobuzPackageService>(TobuzPackageService.class);

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	public static List<TobuzPackageService> getPackageServicesByCountryAndUserRole(Country country,UserRole userRole){
		return TobuzPackageService.find.where().eq("country", country).eq("userRole", userRole).eq("isActive", Boolean.TRUE).orderBy().asc("id").findList();
	}
	
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}


}
