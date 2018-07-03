package models.tobuzpackage;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;
import models.Country;

@Entity
public class TobuzPackageServiceInfo extends BaseEntity{
	
	@ManyToOne
	private TobuzPackage tobuzPackage;
	
	@ManyToOne
	private TobuzPackageService tobuzPackageService;
	
	public TobuzPackageService getTobuzPackageService() {
		return tobuzPackageService;
	}


	public void setTobuzPackageService(TobuzPackageService tobuzPackageService) {
		this.tobuzPackageService = tobuzPackageService;
	}


	@ManyToOne
	private Country country;
	

	public static Model.Finder<Long, TobuzPackageServiceInfo> find = new Model.Finder<Long, TobuzPackageServiceInfo>(TobuzPackageServiceInfo.class);


	public TobuzPackage getTobuzPackage() {
		return tobuzPackage;
	}


	public void setTobuzPackage(TobuzPackage tobuzPackage) {
		this.tobuzPackage = tobuzPackage;
	}


	
	public Country getCountry() {
		return country;
	}


	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
