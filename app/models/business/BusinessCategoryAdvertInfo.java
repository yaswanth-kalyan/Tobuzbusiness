package models.business;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;

import models.BaseEntity;

@Entity
public class BusinessCategoryAdvertInfo extends BaseEntity{
	
	@ManyToOne
	private Category category;
	
	@ManyToOne
	private BusinessAdvert businessAdvert;
	
	public static Model.Finder<Long, BusinessCategoryAdvertInfo> find = new Model.Finder<Long, BusinessCategoryAdvertInfo>(BusinessCategoryAdvertInfo.class);

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public BusinessAdvert getBusinessAdvert() {
		return businessAdvert;
	}

	public void setBusinessAdvert(BusinessAdvert businessAdvert) {
		this.businessAdvert = businessAdvert;
	}
	
	public static BusinessCategoryAdvertInfo getBusinessAdvertCategoryInfos(BusinessAdvert businessAdvert,Category category){
		return BusinessCategoryAdvertInfo.find.where().and(Expr.eq("businessAdvert", businessAdvert),Expr.eq("category", category)).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	public static List<BusinessCategoryAdvertInfo> getBusinessAdvertBySCategoryInfos(Category category){
		return BusinessCategoryAdvertInfo.find.where().eq("category", category).eq("isActive", Boolean.TRUE).findList();
	}
	public static List<BusinessCategoryAdvertInfo> getCategoryByListingInfos(BusinessAdvert businessAdvert){
		return BusinessCategoryAdvertInfo.find.where().eq("businessAdvert", businessAdvert).eq("isActive", Boolean.TRUE).findList();
	}
	
	public static BusinessCategoryAdvertInfo createBusinessAdvertCategoryInfo(BusinessAdvert businessAdvert,Category category) {
		BusinessCategoryAdvertInfo businessAdvertCategoryInfo = getBusinessAdvertCategoryInfos(businessAdvert,category);
		if(businessAdvertCategoryInfo == null) {
			businessAdvertCategoryInfo = new BusinessCategoryAdvertInfo();
			businessAdvertCategoryInfo.setCategory(category);
			businessAdvertCategoryInfo.setBusinessAdvert(businessAdvert);
			businessAdvertCategoryInfo.setIsActive(Boolean.TRUE);
			businessAdvertCategoryInfo.save();
		}
		return businessAdvertCategoryInfo;
	}

}
