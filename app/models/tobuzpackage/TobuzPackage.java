package models.tobuzpackage;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;

import constants.TobuzPackageType;
import constants.UserRole;
import models.AppUser;
import models.BaseEntity;
import models.Country;

@Entity
public class TobuzPackage extends BaseEntity{
	
	// Silver/Gold/Free etc
	private TobuzPackageType tobuzPackageType;
	
	@Column(columnDefinition="TEXT")
	private String description;
	
	private Float cost;
	
	@ManyToOne
	private Country country;
	
	private Integer expiryPeriodInMonths;
	
	private Boolean isFreePackage = Boolean.FALSE;
	
	private Integer sequence;
	
	
	public static Model.Finder<Long, TobuzPackage> find = new Model.Finder<Long, TobuzPackage>(TobuzPackage.class);


	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public TobuzPackageType getTobuzPackageType() {
		return tobuzPackageType;
	}

	public void setTobuzPackageType(TobuzPackageType tobuzPackageType) {
		this.tobuzPackageType = tobuzPackageType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Integer getExpiryPeriodInMonths() {
		return expiryPeriodInMonths;
	}

	public void setExpiryPeriodInMonths(Integer expiryPeriodInMonths) {
		this.expiryPeriodInMonths = expiryPeriodInMonths;
	}

	public Boolean getIsFreePackage() {
		return isFreePackage;
	}

	public void setIsFreePackage(Boolean isFreePackage) {
		this.isFreePackage = isFreePackage;
	}
	
	public static List<TobuzPackage> getPackagesByCountryAndRole(Country country,AppUser appUser){
		ExpressionList<TobuzPackage> packages = TobuzPackage.find.where().eq("country", country);
		if(appUser != null && appUser.getRole().getUserRole().equals(UserRole.BUYER)) {
			packages.in("tobuzPackageType", TobuzPackageType.getBuyerPackages());
		}
		return packages.eq("isActive", Boolean.TRUE).orderBy().asc("sequence").findList();
	}
	
	public static TobuzPackage getFreePackageByCountry(Country country){
		TobuzPackage tPackage = TobuzPackage.find.where().eq("country", country).eq("isActive", Boolean.TRUE).eq("tobuzPackageType", TobuzPackageType.FREE).setMaxRows(1).findUnique();
		return tPackage;
	}

}
