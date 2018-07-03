package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

@Entity
public class CompanyType extends BaseEntity{
	
	private String name;
	
	@ManyToOne
	private Country country;

	public static Model.Finder<Long, CompanyType> find = new Model.Finder<Long, CompanyType>(CompanyType.class);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	
	
}
