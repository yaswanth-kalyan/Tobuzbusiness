package models.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;

import models.BaseEntity;

@Entity
public class BusinessAdvertSubcategoryInfo extends BaseEntity{
	
	@ManyToOne
	private BusinessAdvert businessAdvert;
	
	@ManyToOne
	private SubCategory subCategory;
	
	@ManyToOne
	private Category category;
	
	public static Model.Finder<Long, BusinessAdvertSubcategoryInfo> find = new Model.Finder<Long, BusinessAdvertSubcategoryInfo>(BusinessAdvertSubcategoryInfo.class);

	public BusinessAdvert getBusinessAdvert() {
		return businessAdvert;
	}

	public void setBusinessAdvert(BusinessAdvert businessAdvert) {
		this.businessAdvert = businessAdvert;
	}

	public SubCategory getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(SubCategory subCategory) {
		this.subCategory = subCategory;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	public static BusinessAdvertSubcategoryInfo getBusinessAdvertSubCategoryInfos(BusinessAdvert businessAdvert,SubCategory subCategory){
		return BusinessAdvertSubcategoryInfo.find.where().and(Expr.eq("businessAdvert", businessAdvert),Expr.eq("subCategory", subCategory)).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	public static List<BusinessAdvertSubcategoryInfo> getBusinessAdvertBySubCategoryInfos(SubCategory subCategory){
		return BusinessAdvertSubcategoryInfo.find.where().eq("subCategory", subCategory).eq("isActive", Boolean.TRUE).findList();
	}
	public static List<BusinessAdvertSubcategoryInfo> getSubCategoryByListingInfos(BusinessAdvert businessAdvert){
		return BusinessAdvertSubcategoryInfo.find.where().eq("businessAdvert", businessAdvert).eq("isActive", Boolean.TRUE).findList();
	}
	
	public static BusinessAdvertSubcategoryInfo createBusinessAdvertSubCategoryInfo(BusinessAdvert businessAdvert,SubCategory subCategory) {
		BusinessAdvertSubcategoryInfo businessAdvertCategoryInfo = getBusinessAdvertSubCategoryInfos(businessAdvert,subCategory);
		if(businessAdvertCategoryInfo == null) {
			businessAdvertCategoryInfo = new BusinessAdvertSubcategoryInfo();
			businessAdvertCategoryInfo.setCategory(subCategory.getCategory());
			businessAdvertCategoryInfo.setSubCategory(subCategory);;
			businessAdvertCategoryInfo.setBusinessAdvert(businessAdvert);
			businessAdvertCategoryInfo.setIsActive(Boolean.TRUE);
			businessAdvertCategoryInfo.save();
		}
		return businessAdvertCategoryInfo;
	}

}
