package models;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Expr;
import com.avaje.ebean.Model;

import models.business.BusinessAdvert;
import models.business.BusinessCategoryAdvertInfo;
import models.business.Category;

@Entity
public class UserSearch extends BaseEntity{
	
	@ManyToOne
	private AppUser appUser;
	
	@ManyToOne
	private Role role;
	
	private String searchKey;
	
	private Date searchedOn;
	
	public static Model.Finder<Long, UserSearch> find = new Model.Finder<Long, UserSearch>(UserSearch.class);

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public Date getSearchedOn() {
		return searchedOn;
	}

	public void setSearchedOn(Date searchedOn) {
		this.searchedOn = searchedOn;
	}
	
	public static List<UserSearch> getUserSearch(AppUser appUser){
		return UserSearch.find.where().eq("appUser", appUser).eq("role",appUser.getRole()).eq("isActive", Boolean.TRUE).findList();
	}
	
	public static UserSearch createUserSearch(AppUser appUser,String searchKey) {
			UserSearch userSearch = new UserSearch();
			userSearch.setAppUser(appUser);
			userSearch.setRole(appUser.getRole());
			userSearch.setIsActive(Boolean.TRUE);
			userSearch.setSearchKey(searchKey);
			userSearch.save();
		return userSearch;
	}

}
