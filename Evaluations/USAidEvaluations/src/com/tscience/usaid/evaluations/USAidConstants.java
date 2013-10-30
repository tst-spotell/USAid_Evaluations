/**
 * Copyright (c) 2013 Thermopylae Sciences and Technology. All rights reserved.
 */
package com.tscience.usaid.evaluations;

/**
 * This class defines the constants used by the application.
 * 
 * @author spotell at t-sciences.com
 *
 */
public class USAidConstants {
    
	// json results values
	public static final String COUNTRY_DIVIDER = "_qfvsq_";
	public static final String ZERO_TIME = "T00:00:00";
	
	public static final int USAID_COUNTRY_AFGHANISTAN = 1;
	public static final int USAID_COUNTRY_ALBANIA = 2;
	public static final int USAID_COUNTRY_ANGOLA = 3;
	public static final int USAID_COUNTRY_ARMENIA = 4;
	public static final int USAID_COUNTRY_BANGLADESH = 5;
	public static final int USAID_COUNTRY_BOSNIA = 6;
	public static final int USAID_COUNTRY_BRAZIL = 7;
	public static final int USAID_COUNTRY_BURMA = 8;
	public static final int USAID_COUNTRY_BURUNDI = 9;
	public static final int USAID_COUNTRY_CAMBODIA = 10;
	public static final int USAID_COUNTRY_CHAD = 11;
	public static final int USAID_COUNTRY_COLOMBIA = 12;
	public static final int USAID_COUNTRY_DOMINICAN_REPUBLIC = 13;
	public static final int USAID_COUNTRY_CONGO_DR = 14;
	public static final int USAID_COUNTRY_ECUADOR = 15;
	public static final int USAID_COUNTRY_EGYPT = 16;
	public static final int USAID_COUNTRY_EL_SALVADOR = 17;
	public static final int USAID_COUNTRY_ETHIOPIA = 18;
	public static final int USAID_COUNTRY_GAMBIA = 19;
	public static final int USAID_COUNTRY_GEORGIA = 20;
	public static final int USAID_COUNTRY_GHANA = 21;
	public static final int USAID_COUNTRY_GUATEMALA = 22;
	public static final int USAID_COUNTRY_HAITI = 23;
	public static final int USAID_COUNTRY_INDIA = 24;
	public static final int USAID_COUNTRY_INDONESIA = 25;
    public static final int USAID_COUNTRY_IRAQ = 26;
    public static final int USAID_COUNTRY_KAZAKHSTAN = 27;
    public static final int USAID_COUNTRY_KENYA = 28;
    public static final int USAID_COUNTRY_KOSOVO = 29;
    public static final int USAID_COUNTRY_KYRGYZSTAN = 30;
    public static final int USAID_COUNTRY_LEBANON = 31;
    public static final int USAID_COUNTRY_LIBERIA = 32;
    public static final int USAID_COUNTRY_LIBYA = 33;
    public static final int USAID_COUNTRY_MALI = 34;
    public static final int USAID_COUNTRY_MAURITANIA = 35;
    public static final int USAID_COUNTRY_MEXICO = 36;
    public static final int USAID_COUNTRY_MONTENEGRO = 37;
    public static final int USAID_COUNTRY_MOROCCO = 38;
    public static final int USAID_COUNTRY_MOZAMBIQUE = 39;
    public static final int USAID_COUNTRY_NEPAL = 40;
    public static final int USAID_COUNTRY_NICARAGUA = 41;
    public static final int USAID_COUNTRY_NIGER = 42;
    public static final int USAID_COUNTRY_NIGERIA = 43;
    public static final int USAID_COUNTRY_PAKISTAN = 44;
    public static final int USAID_COUNTRY_PARAGUAY = 45;
    public static final int USAID_COUNTRY_PERU = 46;
    public static final int USAID_COUNTRY_PHILIPPINES = 47;
    public static final int USAID_COUNTRY_RWANDA = 48;
    public static final int USAID_COUNTRY_SENEGAL = 49;
    public static final int USAID_COUNTRY_SERBIA = 50;
    public static final int USAID_COUNTRY_SOUTH_AFRICA = 51;
    public static final int USAID_COUNTRY_SOUTH_SUDAN = 52;
    public static final int USAID_COUNTRY_SRI_LANKA = 53;
    public static final int USAID_COUNTRY_SUDAN = 54;
    public static final int USAID_COUNTRY_TAJIKISTAN = 55;
    public static final int USAID_COUNTRY_TANZANIA = 56;
    public static final int USAID_COUNTRY_TIMOR_LESTE = 57;
    public static final int USAID_COUNTRY_TURKMENISTAN = 58;
    public static final int USAID_COUNTRY_UGANDA = 59;
    public static final int USAID_COUNTRY_UKRAINE = 60;
    public static final int USAID_COUNTRY_UZBEKISTAN = 61;
    public static final int USAID_COUNTRY_VIETNAM = 62;
    public static final int USAID_COUNTRY_WB_GAZA = 63;
    public static final int USAID_COUNTRY_YEMEN = 64;
    public static final int USAID_COUNTRY_ZIMBABWE = 65;
	
	/** Country array id. */
    public static final int[]  COUNTRY_ARRAY_ID  = new int[] {
        USAID_COUNTRY_AFGHANISTAN, // Afghanistan
        USAID_COUNTRY_ALBANIA, // Albania
        USAID_COUNTRY_ANGOLA, // Angola
        USAID_COUNTRY_ARMENIA, // Armenia
        USAID_COUNTRY_BANGLADESH, // Bangladesh
        USAID_COUNTRY_BOSNIA, // Bosnia and Hercegovina
        USAID_COUNTRY_BRAZIL, // Brazil
        USAID_COUNTRY_BURMA, // Burma
        USAID_COUNTRY_BURUNDI, // Burundi
        USAID_COUNTRY_CAMBODIA, // Cambodia
        USAID_COUNTRY_CHAD, // Chad
        USAID_COUNTRY_COLOMBIA, // Colombia
        USAID_COUNTRY_DOMINICAN_REPUBLIC, // Dominican Republic
        USAID_COUNTRY_CONGO_DR, // Congo DR
        USAID_COUNTRY_ECUADOR, // Ecuador
        USAID_COUNTRY_EGYPT, // Egypt
        USAID_COUNTRY_EL_SALVADOR, // El Salvador
        USAID_COUNTRY_ETHIOPIA, // Ethiopia
        USAID_COUNTRY_GAMBIA, // Gambia
        USAID_COUNTRY_GEORGIA, // Georgia
        USAID_COUNTRY_GHANA, // Ghana
        USAID_COUNTRY_GUATEMALA, // Guatemala
        USAID_COUNTRY_HAITI, // Haiti
        USAID_COUNTRY_INDIA, // India
        USAID_COUNTRY_INDONESIA, // Indonesia
        USAID_COUNTRY_IRAQ, // Iraq
        USAID_COUNTRY_KAZAKHSTAN, // Kazakhstan
        USAID_COUNTRY_KENYA, // Kenya
        USAID_COUNTRY_KOSOVO, // Kosovo
        USAID_COUNTRY_KYRGYZSTAN, // Kyrgyzstan
        USAID_COUNTRY_LEBANON, // Lebanon
        USAID_COUNTRY_LIBERIA, // Liberia
        USAID_COUNTRY_LIBYA, // Libya
        USAID_COUNTRY_MALI, // Mali
        USAID_COUNTRY_MAURITANIA, // Mauritania
        USAID_COUNTRY_MEXICO, // Mexico
        USAID_COUNTRY_MONTENEGRO, // Montenegro
        USAID_COUNTRY_MOROCCO, // Morocco
        USAID_COUNTRY_MOZAMBIQUE, // Mozambique
        USAID_COUNTRY_NEPAL, // Nepal
        USAID_COUNTRY_NICARAGUA, // Nicaragua
        USAID_COUNTRY_NIGER, // Niger
        USAID_COUNTRY_NIGERIA, // Nigeria
        USAID_COUNTRY_PAKISTAN, // Pakistan
        USAID_COUNTRY_PARAGUAY, // Paraguay
        USAID_COUNTRY_PERU, // Peru
        USAID_COUNTRY_PHILIPPINES, // Philippines
        USAID_COUNTRY_RWANDA, // Rwanda
        USAID_COUNTRY_SENEGAL, // Senegal
        USAID_COUNTRY_SERBIA, // Serbia
        USAID_COUNTRY_SOUTH_AFRICA, // South Africa
        USAID_COUNTRY_SOUTH_SUDAN, // South Sudan
        USAID_COUNTRY_SRI_LANKA, // Sri Lanka
        USAID_COUNTRY_SUDAN, // Sudan
        USAID_COUNTRY_TAJIKISTAN, // Tajikistan
        USAID_COUNTRY_TANZANIA, // Tanzania
        USAID_COUNTRY_TIMOR_LESTE, // Timor-Leste
        USAID_COUNTRY_TURKMENISTAN, // Turkmenistan
        USAID_COUNTRY_UGANDA, // Uganda
        USAID_COUNTRY_UKRAINE, // Ukraine
        USAID_COUNTRY_UZBEKISTAN, // Uzbekistan
        USAID_COUNTRY_VIETNAM, // Vietnam
        USAID_COUNTRY_WB_GAZA, // WB Gaza
        USAID_COUNTRY_YEMEN, // Yemen
        USAID_COUNTRY_ZIMBABWE // Zimbabwe
    };
	
	// sector image values
    public static final int USAID_SECTOR_OTHER = 0;  // undefined sector
	public static final int USAID_SECTOR_AGRICULTURE = 1;  // Agriculture and Food Security
	public static final int USAID_SECTOR_DEMOCRACY = 2;  // Democracy, Human Rights and Governance
	public static final int USAID_SECTOR_ECONOMIC = 3;  // Economic Growth and Trade
	public static final int USAID_SECTOR_EDUCATION = 4;  // Education
	public static final int USAID_SECTOR_ENVIRONMENT = 5;  // Environment and Global Climate Change
	public static final int USAID_SECTOR_GENDER = 6;  // Gender Equality and Women's Empowerment
	public static final int USAID_SECTOR_HEALTH = 7;  // Global Health
	public static final int USAID_SECTOR_SCIENCE = 8;  // Science, Technology and Innovation
	public static final int USAID_SECTOR_WATER = 9;  // Water and Sanitation
	public static final int USAID_SECTOR_CRISIS = 10;  // Working in Crises and Conflict
	
	public static final String USAID_BUNDLE_DATA_OBJECT = "usaid_data";
	public static final String LESS_THAN_SIGN = "<";
	
	public static final int USAID_REGION_OTHER = 0;
	public static final int USAID_REGION_AFGHANISTAN = 1;
    public static final int USAID_REGION_PAKISTAN = 2;
    public static final int USAID_REGION_ASIA = 3;
    public static final int USAID_REGION_EUROPE = 4;
    public static final int USAID_REGION_LATIN_AMERICA = 5;
    public static final int USAID_REGION_MIDDLE_EAST = 6;
    public static final int USAID_REGION_AFRICA = 7;
	
	/** Region array id. */
	public static final int[]  REGION_ARRAY_ID  = new int[] {
	    USAID_REGION_AFGHANISTAN, // Afghanistan
	    USAID_REGION_EUROPE, // Albania
	    USAID_REGION_AFRICA, // Angola
	    USAID_REGION_EUROPE, // Armenia
	    USAID_REGION_ASIA, // Bangladesh
	    USAID_REGION_EUROPE, // Bosnia and Hercegovina
	    USAID_REGION_LATIN_AMERICA, // Brazil
	    USAID_REGION_ASIA, // Burma
	    USAID_REGION_AFRICA, // Burundi
	    USAID_REGION_ASIA, // Cambodia
	    USAID_REGION_AFRICA, // Chad
	    USAID_REGION_LATIN_AMERICA, // Colombia
	    USAID_REGION_LATIN_AMERICA, // Dominican Republic
	    USAID_REGION_AFRICA, // Congo DR
	    USAID_REGION_LATIN_AMERICA, // Ecuador
	    USAID_REGION_MIDDLE_EAST, // Egypt
	    USAID_REGION_LATIN_AMERICA, // El Salvador
	    USAID_REGION_AFRICA, // Ethiopia
	    USAID_REGION_AFRICA, // Gambia
	    USAID_REGION_EUROPE, // Georgia
	    USAID_REGION_AFRICA, // Ghana
	    USAID_REGION_LATIN_AMERICA, // Guatemala
	    USAID_REGION_LATIN_AMERICA, // Haiti
	    USAID_REGION_ASIA, // India
	    USAID_REGION_ASIA, // Indonesia
	    USAID_REGION_MIDDLE_EAST, // Iraq
	    USAID_REGION_ASIA, // Kazakhstan
	    USAID_REGION_AFRICA, // Kenya
	    USAID_REGION_EUROPE, // Kosovo
	    USAID_REGION_ASIA, // Kyrgyzstan
	    USAID_REGION_MIDDLE_EAST, // Lebanon
	    USAID_REGION_AFRICA, // Liberia
	    USAID_REGION_MIDDLE_EAST, // Libya
	    USAID_REGION_AFRICA, // Mali
	    USAID_REGION_AFRICA, // Mauritania
	    USAID_REGION_LATIN_AMERICA, // Mexico
	    USAID_REGION_EUROPE, // Montenegro
	    USAID_REGION_MIDDLE_EAST, // Morocco
	    USAID_REGION_AFRICA, // Mozambique
	    USAID_REGION_ASIA, // Nepal
	    USAID_REGION_LATIN_AMERICA, // Nicaragua
	    USAID_REGION_AFRICA, // Niger
	    USAID_REGION_AFRICA, // Nigeria
	    USAID_REGION_PAKISTAN, // Pakistan
	    USAID_REGION_LATIN_AMERICA, // Paraguay
	    USAID_REGION_LATIN_AMERICA, // Peru
	    USAID_REGION_ASIA, // Philippines
	    USAID_REGION_AFRICA, // Rwanda
	    USAID_REGION_AFRICA, // Senegal
	    USAID_REGION_EUROPE, // Serbia
	    USAID_REGION_AFRICA, // South Africa
	    USAID_REGION_AFRICA, // South Sudan
	    USAID_REGION_ASIA, // Sri Lanka
	    USAID_REGION_AFRICA, // Sudan
	    USAID_REGION_ASIA, // Tajikistan
	    USAID_REGION_AFRICA, // Tanzania
	    USAID_REGION_ASIA, // Timor-Leste
	    USAID_REGION_ASIA, // Turkmenistan
	    USAID_REGION_AFRICA, // Uganda
	    USAID_REGION_EUROPE, // Ukraine
	    USAID_REGION_ASIA, // Uzbekistan
	    USAID_REGION_ASIA, // Vietnam
	    USAID_REGION_MIDDLE_EAST, // WB Gaza
	    USAID_REGION_MIDDLE_EAST, // Yemen
	    USAID_REGION_AFRICA // Zimbabwe
	};
	

} // end USAidConstants
