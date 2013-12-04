import java.util.ArrayList;

import com.cmu.scotty.controller.ScottyController;
import com.cmu.scotty.model.Student;
import com.cmu.scotty.persistence.StudentDao;

/**
 * 
 */

/**
 * @author Tania
 *
 */
public class testMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ScottyController scottyCon = new ScottyController();
		
		ArrayList<Student> arrStudents = new ArrayList<Student>();
		
     	Student student1 = new Student();
     	student1.setAndrewID("44435");
     	student1.setFirstName("Tania");
     	student1.setLastName("Dasgupta");
     	student1.setCountry("India");
     	student1.setFullTime("Yes");
     	student1.setProgramTrack("MISM");
     	student1.setSemester("1st");
     	student1.setPhotoPath("jndj\\bsdfsd");
		
		Student student2 = new Student();
		student2.setAndrewID("0078");
		student2.setFirstName("Daniel");
		student2.setLastName("Miller");
		student2.setCountry("U.S");
		student2.setFullTime("Yes");
		student2.setProgramTrack("MISM");
		student2.setSemester("1st");
		student2.setPhotoPath("j\\ndj\\bsdfsd");
		
		arrStudents.add(student1);
		arrStudents.add(student2);
		
		
		
		try
		{
			//studentDao.dropTable();
			//scottyCon.importExcel(arrStudents);
			scottyCon.selectStudent("andrewId", "Daniel");
	
		}
		catch(Exception e)
		{
			System.out.println(" Error ");
		}
		
		
	}

}
