package models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

import constants.UserRole;

@Entity
public class Role extends BaseEntity{
	
	private UserRole userRole;
	
	@ManyToOne
	private AppUser appUser;

	public static Model.Finder<Long, Role> find = new Model.Finder<Long, Role>(Role.class);

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}
	
	
}
