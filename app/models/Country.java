package models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.avaje.ebean.Model;

import util.SessionProperty;
import util.StringUtils;

@Entity
public class Country extends BaseEntity{
	
	private String name;
	
	private String dialingCode;
	
	private String isoCode;
	
	private String inetCode;
	
	private String currencyCode;
	
//	private String htmlCurrenyCode;
//	
//	public String getHtmlCurrenyCode() {
//		return htmlCurrenyCode;
//	}
//
//	public void setHtmlCurrenyCode(String htmlCurrenyCode) {
//		this.htmlCurrenyCode = htmlCurrenyCode;
//	}


	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<State> stateList = new ArrayList<State>();
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "country")
	private List<City> cityList = new ArrayList<City>();
	
	private String flagName;
	
	private String timeZone;
	
	private Integer timeOffsetInMins;
	
	private Integer sequence;
	
	

	public Integer getTimeOffsetInMins() {
		return timeOffsetInMins;
	}

	public void setTimeOffsetInMins(Integer timeOffsetInMins) {
		this.timeOffsetInMins = timeOffsetInMins;
	}


	public static Model.Finder<Long, Country> find = new Model.Finder<Long, Country>(Country.class);

	public String getInetCode() {
		return inetCode;
	}

	public void setInetCode(String inetCode) {
		this.inetCode = inetCode;
	}

	public String getName() {
		return name;
	}

	public String getIsoCode() {
		return isoCode;
	}

	public String getFlagName() {
		return flagName;
	}

	public void setFlagName(String flagName) {
		this.flagName = flagName;
	}

	public String getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}

	public void setIsoCode(String isoCode) {
		this.isoCode = isoCode;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getDialingCode() {
		return dialingCode;
	}

	public void setDialingCode(String dialingCode) {
		this.dialingCode = dialingCode;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	public List<City> getCityList() {
		return cityList;
	}

	public void setCityList(List<City> cityList) {
		this.cityList = cityList;
	}

	public static List<Country> countryList(){
		List<Country> countryList = Country.find.where().eq("isActive",Boolean.TRUE).orderBy("sequence asc").findList();
		return countryList;
		
	}
	
	
	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public static Country getUserLocaleCountry() {
		if(StringUtils.isNotEmpty(SessionProperty.getCountryISO())) {
		return Country.find.where().eq("isoCode", SessionProperty.getCountryISO()).setMaxRows(1).findUnique();
		}
		return null;
	}
	

}
