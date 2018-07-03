package models;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.apache.commons.codec.binary.Base64;
import org.hibernate.validator.constraints.Email;

import com.avaje.ebean.ExpressionList;
import com.avaje.ebean.Model;

import beans.AppUserPayLoadBean;
import constants.ListingType;
import constants.LoginType;
import constants.UserRole;
import models.broker.Broker;
import models.business.BusinessAdvert;
import models.buyer.Buyer;
import models.franchiser.Franchiser;
import models.seller.Seller;
import models.tobuzpackage.TobuzPackage;
import models.tobuzpackage.UserTobuzServicePackageInfo;
import util.SessionProperty;
import util.StringUtils;

@Entity
public class AppUser extends BaseEntity{

	@Column(nullable=false)
	private String name;
	
	@Email
	@Column(unique=true,nullable=false)
	private String email;
	
	private String mobileNumber;
	
	private String countryCode;
	
	@ManyToOne
	private Country mobileDerivedCountry;
	
	private String password;
	
	private String salt;
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Role> roleList = new ArrayList<Role>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<AppUserLog> appUserLogList = new ArrayList<AppUserLog>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<UserTobuzServicePackageInfo> userPackages = new ArrayList<UserTobuzServicePackageInfo>();
	
	private UserRole userDefaultRole;
	
	@OneToOne
	private Address address;
	
	private LoginType loginType;
	
	@OneToOne
	private FileEntity profileImage;
	
	public static Model.Finder<Long, AppUser> find = new Model.Finder<Long, AppUser>(AppUser.class);

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public Country getMobileDerivedCountry() {
		return mobileDerivedCountry;
	}

	public void setMobileDerivedCountry(Country mobileDerivedCountry) {
		this.mobileDerivedCountry = mobileDerivedCountry;
	}

	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password){
		final Random random = new SecureRandom();
		final byte[] saltArray = new byte[32];
		random.nextBytes(saltArray);
		final String randomSalt = Base64.encodeBase64String(saltArray);

		final String passwordWithSalt = password + randomSalt;
		MessageDigest sha256 = null;
		try {
			sha256 = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		final byte[] passBytes = passwordWithSalt.getBytes();
		final String hashedPasswordWithSalt = Base64.encodeBase64String(sha256
				.digest(passBytes));

		this.salt = randomSalt;
		this.password = hashedPasswordWithSalt;
	}
	
	public Boolean isPasswordMatched(String password) {
		// TODO Auto-generated method stub
			try {
				final String passwordWithSalt = password + this.getSalt();
				final MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
				final byte[] passBytes = passwordWithSalt.getBytes();
				final String hashedPassword = Base64.encodeBase64String(sha256.digest(passBytes));
				if (hashedPassword.compareTo(this.getPassword()) == 0) {
					return true;
				} else {
					return false;
				}
			} catch (final Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<AppUserLog> getAppUserLogList() {
		return appUserLogList;
	}

	public void setAppUserLogList(List<AppUserLog> appUserLogList) {
		this.appUserLogList = appUserLogList;
	}

	public List<UserTobuzServicePackageInfo> getUserPackages() {
		return UserTobuzServicePackageInfo.find.where().eq("user", this).eq("isActive", Boolean.TRUE).findList();
	}

	public void setUserPackages(List<UserTobuzServicePackageInfo> userPackages) {
		this.userPackages = userPackages;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	

	public UserRole getUserDefaultRole() {
		return userDefaultRole;
	}

	public void setUserDefaultRole(UserRole userDefaultRole) {
		this.userDefaultRole = userDefaultRole;
	}

	public LoginType getLoginType() {
		return loginType;
	}

	public void setLoginType(LoginType loginType) {
		this.loginType = loginType;
	}

	public FileEntity getProfileImage() {
		return profileImage;
	}

	public void setProfileImage(FileEntity profileImage) {
		this.profileImage = profileImage;
	}
	
	
	public String generateAuthToken() {
		String randomKey = StringUtils.getRandomAlphaNumericId(15);
		String key = randomKey+this.getEmail().concat(String.valueOf(System.currentTimeMillis()));
		byte[] authTokenBytes = java.util.Base64.getEncoder().encode(key.getBytes());
		String authToken = new String(authTokenBytes);
		return authToken;
	}
	
	public AppUserPayLoadBean toAppUserPayLoadBean() {
		AppUserPayLoadBean bean = new AppUserPayLoadBean();
		Role role = Role.find.where().eq("appUser", this).setMaxRows(1).findUnique();
		bean.setName(this.getName());
		bean.setCountryCode(this.getCountryCode());
		bean.setMobileNumber(this.getMobileNumber());
		bean.setAuthKey(SessionProperty.getUserSessionAuthKey());
		bean.setRole(role.getUserRole().toString());
		bean.setRoleId(role.getId());
		bean.setUserId(this.getId());
		if(this.getAddress() != null && this.getAddress().getCountry() != null) {
			bean.setAddressId(this.getAddress().getId());
			bean.setCountryId(this.getAddress().getCountry().getId());
			bean.setCountryName(this.getAddress().getCountry().getName());
			bean.setCityId(this.getAddress().getCity().getId());
			bean.setStateId(this.getAddress().getState().getId());
			bean.setStateName(this.getAddress().getState().getName());
			bean.setPincode(this.getAddress().getZipCode());
			bean.setAddressDescription(this.getAddress().getDetailedAddress());
			bean.isLocationUpdated = Boolean.TRUE;
		}
		if(this.getUserCurrentActivatedPackage() != null) {
			bean.isPackageUpdated = Boolean.TRUE;
		}
		return bean;
	}
	
	public Role getRole() {
		String roleId = SessionProperty.getUserRoleId();
		try {
		if(StringUtils.isNotEmpty(roleId)) {
			return Role.find.byId(Long.parseLong(roleId));
		}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return Role.find.where().eq("appUser", this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	
	public List<TobuzPackage> getUserActivatedPackages(ListingType listingType){
		List<TobuzPackage> packageList = new LinkedList<TobuzPackage>();
	ExpressionList<UserTobuzServicePackageInfo> packageMappingList = UserTobuzServicePackageInfo.find.where().eq("user", this).eq("role", this.getRole()).eq("isActive", Boolean.TRUE);
	if(listingType != null) {
		packageMappingList.eq("packageActiveFor", listingType);
	}
	for(UserTobuzServicePackageInfo serviceInfo : packageMappingList.findList()) {
		packageList.add(serviceInfo.getTobuzPackage());
	}
	return packageList;
	}
	
	public Role getAppUserDefaultRole() {
		return Role.find.where().eq("appUser", this).eq("userRole", this.getUserDefaultRole()).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	
	public Buyer getBuyer() {
		return Buyer.find.where().eq("user", this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	
	public Seller getSeller() {
		return Seller.find.where().eq("user", this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	
	public Franchiser getFranchisor() {
		return Franchiser.find.where().eq("user", this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	
	public Broker getBroker() {
		return Broker.find.where().eq("user", this).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
	}
	
	public Country getUserCountry() {
		if(this.getAddress() != null) {
			return this.getAddress().getCountry();
		}
		return null;
	}
	

	public TobuzPackage getUserCurrentActivatedPackage() {
		UserTobuzServicePackageInfo packageMapping = UserTobuzServicePackageInfo.find.where().eq("user", this).eq("role", this.getRole()).eq("isActive", Boolean.TRUE).setMaxRows(1).findUnique();
		if ( packageMapping != null) {
			return packageMapping.getTobuzPackage();
		}
		return null;
	}
	
	public List<FavouriteBusinessListing> getLoggedInUserFavouriteListingsAndAdverts(){
		return FavouriteBusinessListing.find.where().eq("isActive", Boolean.TRUE).eq("user", this).eq("role", this.getRole()).findList();
	}
	
	public List<BusinessAdvert> getLoggedInUserBusinessAdverts(){
		return BusinessAdvert.find.where().eq("isActive", Boolean.TRUE).eq("advertedByUser", this).eq("role", this.getRole()).findList();
	}

	
	public List<UserSearch> getLoggedInUserSearchList(){
		return UserSearch.getUserSearch(this);
	}
}
