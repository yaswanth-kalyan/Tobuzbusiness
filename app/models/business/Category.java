package models.business;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;

import constants.ListingType;
import models.BaseEntity;

@Entity
public class Category extends BaseEntity{
	
	private String name;
	
	private Long imageId;
	
	private Boolean isCommercialCategory = Boolean.FALSE;
	
	private Integer sequence;
	
	private Boolean isFeaturedCategory = Boolean.FALSE;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="category")
	private List<SubCategory> subCategoryList = new ArrayList<SubCategory>();

	public static Model.Finder<Long, Category> find = new Model.Finder<Long, Category>(Category.class);

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Boolean getIsFeaturedCategory() {
		return isFeaturedCategory;
	}

	public void setIsFeaturedCategory(Boolean isFeaturedCategory) {
		this.isFeaturedCategory = isFeaturedCategory;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<SubCategory> getSubCategoryList() {
		return subCategoryList;
	}

	public void setSubCategoryList(List<SubCategory> subCategoryList) {
		this.subCategoryList = subCategoryList;
	}

	public Long getImageId() {
		return imageId;
	}

	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}

	public Boolean getIsCommercialCategory() {
		return isCommercialCategory;
	}

	public void setIsCommercialCategory(Boolean isCommercialCategory) {
		this.isCommercialCategory = isCommercialCategory;
	}
	
	public static List<Category> getCategoriesByBusinessType(ListingType listingType){
		if(listingType.equals(ListingType.COMMERCIAL)) {
			return  Category.find.where().eq("isCommercialCategory", Boolean.TRUE).eq("isActive", Boolean.TRUE).findList();
		}else {
			return  Category.find.where().eq("isCommercialCategory", Boolean.FALSE).eq("isActive", Boolean.TRUE).findList();	
		}
	}
	
}
