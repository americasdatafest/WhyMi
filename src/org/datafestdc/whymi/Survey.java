package org.datafestdc.whymi;

public class Survey {
	
	private String id;
	private String surveyLocation;
	private String gender;
	private String age;
	private String country;
	private String primaryReason;
	private String secondaryReason;
	
	public Survey(String id, String surveyLocation, String gender, String age,
			String country, String primaryReason, String secondaryReason) {
		super();
		this.id = id;
		this.surveyLocation = surveyLocation;
		this.gender = gender;
		this.age = age;
		this.country = country;
		this.primaryReason = primaryReason;
		this.secondaryReason = secondaryReason;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSurveyLocation() {
		return surveyLocation;
	}
	public void setSurveyLocation(String surveyLocation) {
		this.surveyLocation = surveyLocation;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPrimaryReason() {
		return primaryReason;
	}
	public void setPrimaryReason(String primaryReason) {
		this.primaryReason = primaryReason;
	}
	public String getSecondaryReason() {
		return secondaryReason;
	}
	public void setSecondaryReason(String secondaryReason) {
		this.secondaryReason = secondaryReason;
	}
}
