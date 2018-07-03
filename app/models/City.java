package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

@Entity
public class City extends BaseEntity{

	private String name;
	
	@ManyToOne
	private State state;
	
	@ManyToOne
	private Country country;
	
	public static Model.Finder<Long, City> find = new Model.Finder<Long, City>(City.class);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
	public static List<City> cityList(){
		List<City> cityList = City.find.where().eq("isActive",Boolean.TRUE).orderBy("name asc").findList();
		return cityList;
		
	}
	
	
}
