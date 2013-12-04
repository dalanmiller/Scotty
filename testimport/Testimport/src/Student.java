
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
		this.AndrewID=AndrewID;
		this.FirstName=FirstName;
		this.LastName=LastName;
		this.ProgramTrack=ProgramTrack;
		this.FullTime=FullTime;
		this.Country=Country;
		this.Semester=Semester;
	}
	
	public Student(){
		this.AndrewID=null;
		this.FirstName=null;
		this.LastName=null;
		this.ProgramTrack=null;
		this.FullTime=null;
		this.Country=null;
		this.Semester=null;
	}
	
	
}
