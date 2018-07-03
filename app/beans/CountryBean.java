package beans;

import models.Country;

public class CountryBean {
	
	public String dailingCode;
	
	public String isoCode;
	
	public String currencyCode;
	
	public String name;
	
	public Long id;
	
	public Boolean isUserDefaultCountry = Boolean.FALSE;
	
	public static CountryBean toCountryBean(Country country) {
		CountryBean bean = new CountryBean();
		bean.id = country.getId();
		bean.dailingCode = country.getDialingCode();
		bean.isoCode = country.getIsoCode();
		bean.currencyCode = country.getCurrencyCode();
		bean.name = country.getName();
		return bean;
	}

}
