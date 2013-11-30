package com.cmu.scotty.model;


public class Student {
	private String andrewID;
	private String firstName;
	private String lastName;
	private String programTrack;
	private String fullTime;
	private String country;
	private String semester;
	private String photoPath;
	
	public Student(String AndrewID,String FirstName,String LastName,
			String ProgramTrack,String FullTime,String Country, String Semester){
		this.andrewID=AndrewID;
		this.firstName=FirstName;
		this.lastName=LastName;
		this.programTrack=ProgramTrack;
		this.fullTime=FullTime;
		this.country=Country;
		this.semester=Semester;
		this.photoPath=null;
	}
	public Student(String AndrewID){
		this.andrewID=AndrewID;
		this.firstName=null;
		this.lastName=null;
		this.programTrack=null;
		this.fullTime=null;
		this.country=null;
		this.semester=null;
		this.photoPath=null;
	}
	
	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getAndrewID() {
		return andrewID;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getProgramTrack() {
		return programTrack;
	}

	public String getFullTime() {
		return fullTime;
	}

	public String getCountry() {
		return country;
	}

	public String getSemester() {
		return semester;
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
	
	
	
}
