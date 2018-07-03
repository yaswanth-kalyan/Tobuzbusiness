package models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.avaje.ebean.Model;

@Entity
public class NewsLetterSubscription extends BaseEntity{
	
	private String email;
	
	@ManyToOne
	private AppUser appUser;
	
	@ManyToOne
	private Role role;
	
	public static Model.Finder<Long, NewsLetterSubscription> find = new Model.Finder<Long, NewsLetterSubscription>(NewsLetterSubscription.class);

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	public List<NewsLetterSubscription> getAllNewsLetterSubbscriptions(){
		return NewsLetterSubscription.find.where().eq("isActive", Boolean.TRUE).findList();
	}
	
	public NewsLetterSubscription getNewsLetterSubbscriptionByEmail(String email){
		return NewsLetterSubscription.find.where().ieq("email", email).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	
	public NewsLetterSubscription createNewsLetterSubbscriptions(String email,AppUser appUser){
		NewsLetterSubscription newsLetterSubscription = getNewsLetterSubbscriptionByEmail(email);
		if( newsLetterSubscription == null) {
			newsLetterSubscription = new NewsLetterSubscription();
			newsLetterSubscription.setEmail(email);
			if(appUser != null) {
			newsLetterSubscription.setAppUser(appUser);
			newsLetterSubscription.setRole(appUser.getRole());
			}
			newsLetterSubscription.setIsActive(Boolean.TRUE);
			newsLetterSubscription.save();
		}else {
			if(appUser != null) {
				newsLetterSubscription.setAppUser(appUser);
				newsLetterSubscription.setRole(appUser.getRole());
				}
				newsLetterSubscription.setIsActive(Boolean.TRUE);
				newsLetterSubscription.update();
		}
		return newsLetterSubscription;
	}
	

}
