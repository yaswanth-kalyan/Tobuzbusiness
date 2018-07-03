package models.franchiser;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;
import models.Role;

@Entity
public class Franchiser extends BaseEntity{
	
	@OneToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	private Boolean allowFranchisorContactAccess = Boolean.FALSE;
	
	public static Model.Finder<Long, Franchiser> find = new Model.Finder<Long, Franchiser>(Franchiser.class);

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Boolean getAllowFranchisorContactAccess() {
		return allowFranchisorContactAccess;
	}

	public void setAllowFranchisorContactAccess(Boolean allowFranchisorContactAccess) {
		this.allowFranchisorContactAccess = allowFranchisorContactAccess;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
