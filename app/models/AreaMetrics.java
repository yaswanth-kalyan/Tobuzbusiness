package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class AreaMetrics extends BaseEntity{
	
	@JsonIgnore
	@ManyToOne
	private Country country;
	
	private String metric;
	
	public static Model.Finder<Long, AreaMetrics> find = new Model.Finder<Long, AreaMetrics>(AreaMetrics.class);

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public String getMetric() {
		return metric;
	}

	public void setMetric(String metric) {
		this.metric = metric;
	}
	
	
	

}
