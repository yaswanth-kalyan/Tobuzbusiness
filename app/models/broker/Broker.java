package models.broker;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.avaje.ebean.Model;

import models.AppUser;
import models.BaseEntity;
import models.Role;

@Entity
public class Broker  extends BaseEntity{
	
	@OneToOne
	private AppUser user;
	
	@ManyToOne
	private Role role;
	
	private Boolean shareBrokerContactToBuyer = Boolean.FALSE;
	
	//Packages
	
	public Boolean getShareBrokerContactToBuyer() {
		return shareBrokerContactToBuyer;
	}

	public void setShareBrokerContactToBuyer(Boolean shareBrokerContactToBuyer) {
		this.shareBrokerContactToBuyer = shareBrokerContactToBuyer;
	}

	//Messages
	public static Model.Finder<Long, Broker> find = new Model.Finder<Long, Broker>(Broker.class);

	public AppUser getUser() {
		return user;
	}

	public void setUser(AppUser user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
