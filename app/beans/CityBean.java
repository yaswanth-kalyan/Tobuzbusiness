package beans;

import models.City;

public class CityBean {
	
	public Long cityId;
	
	public String name;
	
	public Long stateId;
	
	public String stateName;
	
	public Long countryId;

	public String countryName;
	
	public Boolean isUserDefaultCity = Boolean.FALSE;
	
	public static CityBean toCityBean(City city) {
		if(city != null) {
			CityBean bean = new CityBean();
			bean.cityId = city.getId();
			bean.name = city.getName();
			if(city.getState() != null) {
				bean.stateId = city.getState().getId();
				bean.stateName = city.getState().getName();
			}
			if(city.getCountry() != null) {
				bean.countryId = city.getCountry().getId();
				bean.countryName = city.getCountry().getName();
			}
			return bean;		
		}
		return null;
	}
}
