package util;

import models.Country;

public class CountryUtil {
	
	public static void insertCountry() {
	final Country country2 = new Country();
	country2.setName("India");
	country2.setDialingCode("91");
	country2.setIsoCode("IN");
	country2.setCurrencyCode("INR");
	country2.setTimeZone("Asia/Kolkata");
	country2.setTimeOffsetInMins(330);
	country2.setIsActive(Boolean.TRUE);
	country2.setFlagName("india.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	country2.save();
	

	final Country country67 = new Country();
	country67.setName("Pakistan");
	country67.setDialingCode("92");
	country67.setIsoCode("PAK");
	country67.setTimeOffsetInMins(300);
	country67.setTimeZone("Asia/Karachi");
	country67.setCurrencyCode("PKR");
	country67.setIsActive(Boolean.TRUE);
	country67.setFlagName("pakistan.png");
	// country2.setHtmlCurrenyCode("&#8377;");
	country67.save();
	
	final Country countryName18 = new Country();
	countryName18.setName("Bangladesh");
	countryName18.setIsoCode("BGD");
	countryName18.setDialingCode("880");
	countryName18.setTimeOffsetInMins(360);
	countryName18.setTimeZone("Asia/Dhaka");
	countryName18.setCurrencyCode("BDT");
	countryName18.setIsActive(Boolean.TRUE);
	countryName18.setFlagName("bangladesh.png");
	// country2.setHtmlCurrenyCode("&#8377;");
	countryName18.save();
	
	
	final Country countryName13 = new Country();
	countryName13.setName("Australia");
	countryName13.setIsoCode("AUS");
	countryName13.setDialingCode("61");
	countryName13.setTimeOffsetInMins(525);
	countryName13.setTimeZone("Australia/Sydney");
	countryName13.setCurrencyCode("AUD");
	countryName13.setIsActive(Boolean.TRUE);
	countryName13.setFlagName("australia.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	countryName13.save();

	final Country cont9 = new Country();
	cont9.setName("Sri Lanka");
	cont9.setIsoCode("LKA");
	cont9.setDialingCode("94");
	cont9.setTimeOffsetInMins(330);
	cont9.setTimeZone("Asia/Colombo");
	cont9.setCurrencyCode("LKR");
	cont9.setIsActive(Boolean.TRUE);
	cont9.setFlagName("srilanka.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	cont9.save();
	
	final Country country99 = new Country();
	country99.setName("Singapore");
	country99.setIsoCode("SGP");
	country99.setDialingCode("65");
	country99.setTimeOffsetInMins(480);
	country99.setTimeZone("Asia/Singapore");
	country99.setCurrencyCode("SGD");
	country99.setIsActive(Boolean.TRUE);
	country99.setFlagName("singapore.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	country99.save();
	
	final Country cont33 = new Country();
	cont33.setName("UAE");
	cont33.setIsoCode("ARE");
	cont33.setDialingCode("971");
	cont33.setTimeOffsetInMins(240);
	cont33.setTimeZone("Asia/Dubai");
	cont33.setCurrencyCode("AED");
	cont33.setIsActive(Boolean.TRUE);
	cont33.setFlagName("uae.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	cont33.save();
	

	final Country country58 = new Country();
	country58.setName("New Zealand");
	country58.setIsoCode("NZL");
	country58.setDialingCode("64");
	country58.setTimeOffsetInMins(780);
	country58.setTimeZone("Pacific/Auckland");
	country58.setCurrencyCode("NZD");
	country58.setIsActive(Boolean.TRUE);
	country58.setFlagName("newzeland.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	country58.save();
	

	final Country cont20 = new Country();
	cont20.setName("Thailand");
	cont20.setIsoCode("THA");
	cont20.setDialingCode("66");
	cont20.setTimeOffsetInMins(420);
	cont20.setTimeZone("Asia/Bangkok");
	cont20.setCurrencyCode("THB");
	cont20.setIsActive(Boolean.TRUE);
	cont20.setFlagName("thailand.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	cont20.save();
	
	final Country country94 = new Country();
	country94.setName("Saudi Arabia");
	country94.setIsoCode("SAU");
	country94.setDialingCode("966");
	country94.setTimeOffsetInMins(180);
	country94.setTimeZone("Asia/Riyadh");
	country94.setCurrencyCode("SAR");
	country94.setIsActive(Boolean.TRUE);
	country94.setFlagName("saudi_arabia.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	country94.save();
	
	final Country countryName17 = new Country();
	countryName17.setName("Bahrain");
	countryName17.setIsoCode("BHR");
	countryName17.setDialingCode("973");
	countryName17.setTimeOffsetInMins(180);
	countryName17.setTimeZone("Asia/Bahrain");
	countryName17.setCurrencyCode("BHD");
	countryName17.setIsActive(Boolean.TRUE);
	countryName17.setFlagName("bahrain.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	countryName17.save();
	

	final Country country66 = new Country();
	country66.setName("Oman");
	country66.setIsoCode("OMN");
	country66.setDialingCode("968");
	country66.setTimeOffsetInMins(240);
	country66.setTimeZone("Asia/Muscat");
	country66.setCurrencyCode("OMR");
	country66.setIsActive(Boolean.TRUE);
	country66.setFlagName("oman.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	country66.save();
	

	final Country country68 = new Country();
	country68.setName("England");
	country68.setIsoCode("UK");
	country68.setDialingCode("44");
	country68.setTimeOffsetInMins(60);
	country68.setTimeZone("GMT");
	country68.setCurrencyCode("GBP");
	country68.setIsActive(Boolean.TRUE);
	country68.setFlagName("england.png");
//	country2.setHtmlCurrenyCode("&#8377;");
	country68.save();
	
	}

}
