
package com.cmu.scotty.model;

import java.lang.StringBuilder;


public class Student {
	private String andrewID;
	private String firstName;
	private String lastName;
	private String programTrack;
	private String fullTime;
	private String country;
	private String semester;
	private String photoPath;
	
	public Student(String andrewID,String firstName,String lastName,
			String programTrack,String fullTime,String country, String semester, String photoPath){
		this.andrewID=andrewID;
		this.firstName=firstName;
		this.lastName=lastName;
		this.programTrack=programTrack;
		this.fullTime=fullTime;
		this.country=country;
		this.semester=semester;
		this.photoPath=photoPath;
	}
	
	public Student(String andrewID){
		this.andrewID=andrewID;
		this.firstName=null;
		this.lastName=null;
		this.programTrack=null;
		this.fullTime=null;
		this.country=null;
		this.semester=null;
		this.photoPath=null;
	}
	
	
	public String getAndrewID() {
		return andrewID;
	}

	public void setAndrewID(String andrewID) {
		this.andrewID = andrewID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getProgramTrack() {
		return programTrack;
	}

	public void setProgramTrack(String programTrack) {
		this.programTrack = programTrack;
	}

	public String getFullTime() {
		return fullTime;
	}

	public void setFullTime(String fullTime) {
		this.fullTime = fullTime;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getSemester() {
		return semester;
	}

	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	
	public Student(){
		this.andrewID=null;
		this.firstName=null;
		this.lastName=null;
		this.programTrack=null;
		this.fullTime=null;
		this.country=null;
		this.semester=null;
		this.photoPath=null;
	}
	
	@Override
	public String toString(){		
		return String.format("%6s | %6s, %6s | %6s | %6s", andrewID, lastName, firstName, programTrack, country);
	}
	
	public Object[] toRow(){
		return new Object[]{ andrewID, firstName, lastName, programTrack, country };
	}
	
	
}
