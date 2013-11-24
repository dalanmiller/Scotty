
public class Student {
	private String AndrewID;
	private String FirstName;
	private String LastName;
	private String ProgramTrack;
	private String FullTime;
	private String Country;
	private String Semester;
	
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
