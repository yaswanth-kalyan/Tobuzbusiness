package models.business;

import javax.persistence.Entity;

import com.avaje.ebean.Model;

import models.BaseEntity;

@Entity
public class BusinessFeature extends BaseEntity{
	
	private String name;
	
	public static Model.Finder<Long, BusinessFeature> find = new Model.Finder<Long, BusinessFeature>(BusinessFeature.class);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
