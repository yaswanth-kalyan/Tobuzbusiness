package models.business;

import javax.persistence.Entity;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;

@Entity
public class BusinessType extends BaseEntity{
	
	private String name;
	
	public static Model.Finder<Long, BusinessType> find = new Model.Finder<Long, BusinessType>(BusinessType.class);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
