package models.tobuzpackage;

import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import constants.TobuzPackageType;
import models.BaseEntity;
import models.Country;
import models.business.BusinessListingOutLet;

public class DistressPackage extends BaseEntity{
	
	@ManyToOne
	private Country country;
	
	private TobuzPackageType tobuzPackageType;
	
	private Float distressCost;
	
	public static Model.Finder<Long, DistressPackage> find = new Model.Finder<Long, DistressPackage>(DistressPackage.class);

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public TobuzPackageType getTobuzPackageType() {
		return tobuzPackageType;
	}

	public void setTobuzPackageType(TobuzPackageType tobuzPackageType) {
		this.tobuzPackageType = tobuzPackageType;
	}

	public Float getDistressCost() {
		return distressCost;
	}

	public void setDistressCost(Float distressCost) {
		this.distressCost = distressCost;
	}
	
	

}
