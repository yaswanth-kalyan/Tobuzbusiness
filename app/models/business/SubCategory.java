package models.business;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonIgnore;

import models.BaseEntity;

@Entity
public class SubCategory extends BaseEntity{
	
	private String name;
	
	@JsonIgnore
	@ManyToOne
	private Category category;
	
	public static Model.Finder<Long, SubCategory> find = new Model.Finder<Long, SubCategory>(SubCategory.class);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}
