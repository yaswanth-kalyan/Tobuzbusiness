package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;

@Entity
public class State extends BaseEntity{
	
	private String name;
	
	@ManyToOne
	private Country country;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "state")
	private List<City> cityList = new ArrayList<City>();
	
	public static Model.Finder<Long, State> find = new Model.Finder<Long, State>(State.class);

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

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
