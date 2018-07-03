package models.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;

import models.BaseEntity;

@Entity
public class BusinessListingSubcategoryInfo extends BaseEntity{
	
	
	@ManyToOne
	private BusinessListing businessListing;
	
	@ManyToOne
	private SubCategory subCategory;
	
	@ManyToOne
	private Category category;
	
	public static Model.Finder<Long, BusinessListingSubcategoryInfo> find = new Model.Finder<Long, BusinessListingSubcategoryInfo>(BusinessListingSubcategoryInfo.class);

	public BusinessListing getBusinessListing() {
		return businessListing;
	}

	public void setBusinessListing(BusinessListing businessListing) {
		this.businessListing = businessListing;
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

	public static BusinessListingSubcategoryInfo getBusinessListngSubCategoryInfos(BusinessListing businessListing,SubCategory subCategory){
		return BusinessListingSubcategoryInfo.find.where().and(Expr.eq("businessListing", businessListing),Expr.eq("subCategory", subCategory)).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	public static List<BusinessListingSubcategoryInfo> getBusinessListngBySubCategoryInfos(SubCategory subCategory){
		return BusinessListingSubcategoryInfo.find.where().eq("subCategory", subCategory).eq("isActive", Boolean.TRUE).findList();
	}
	public static List<BusinessListingSubcategoryInfo> getSubCategoryByListingInfos(BusinessListing businessListing){
		return BusinessListingSubcategoryInfo.find.where().eq("businessListing", businessListing).eq("isActive", Boolean.TRUE).findList();
	}
	
	public static BusinessListingSubcategoryInfo createBusinessListingSubcategoryInfo(BusinessListing businessListing,SubCategory subCategory) {
		BusinessListingSubcategoryInfo subCategoryInfo = getBusinessListngSubCategoryInfos(businessListing,subCategory);
		if(subCategoryInfo == null) {
			subCategoryInfo = new BusinessListingSubcategoryInfo();
			subCategoryInfo.setSubCategory(subCategory);
			subCategoryInfo.setBusinessListing(businessListing);
			subCategoryInfo.setIsActive(Boolean.TRUE);
			subCategoryInfo.save();
		}
		return subCategoryInfo;
	}
	
}
