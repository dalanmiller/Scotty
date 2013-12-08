import java.util.ArrayList;

import com.cmu.scotty.controller.PdfCreator;
import com.cmu.scotty.controller.ReadExcel;
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
     	student1.setAndrewID("999");
     	student1.setFirstName("Tania");
     	student1.setLastName("Dasgupta");
     	student1.setCountry("India");
     	student1.setFullTime("Yes");
     	student1.setProgramTrack("MISM");
     	student1.setSemester("1st");
     	student1.setPhotoPath("jndj\\bsdfsd");
		
		Student student2 = new Student();
		student2.setAndrewID("278");
		student2.setFirstName("Daniel");
		student2.setLastName("Miller");
		//student2.setCountry("U.S");
		//student2.setFullTime("Yes");
		student2.setProgramTrack("MISM");
		student2.setSemester("1st");
		student2.setPhotoPath("j\\ndj\\bsdfsd");
		
		arrStudents.add(student1);
		arrStudents.add(student2);
		
		ArrayList<Student> studentD = new ArrayList<Student>();
		ArrayList<String> ctrArr = new ArrayList<String>();
		ArrayList<String> stuArr = new ArrayList<String>();
		
		ArrayList<String> columnNameArr = new ArrayList<String>();
		columnNameArr.add("PROGRAMTRACK");
		//columnNameArr.add("COUNTRY");
	
		ArrayList<String> columnValueArr = new ArrayList<String>();
		columnValueArr.add("MISM");
		//columnValueArr.add("India");
		
		StudentDao studentDao = new StudentDao();
		
		try
		{
			//studentDao.dropTable();		    
		   // studentDao.insertStudent(student2);
			
			boolean tableExists = studentDao.checkTable();
			System.out.print(tableExists);
			
			//studentDao.updateStudent(student2);
			
			
			
			//studentDao.createTable();
			//studentDao.insertStudents(arrStudents);
			
			//studentD = scottyCon.selectStudent("andrewId", "abmd3");
			//ctrArr = scottyCon.selectCountries();
			
			stuArr = scottyCon.selectAndrewIds();
			ArrayList<Student> stuArrNotInDb  = scottyCon.selectStudentOnAndrewIds(stuArr);
			
			//ArrayList<Student> stuArrNotInDb  = scottyCon.fetchStuPresentInDb(arrStudents);
			
			System.out.print("Hi");
			
			System.out.println(stuArrNotInDb);
						
			//tableExists = studentDao.checkTable();
			
			//System.out.print(tableExists);
			
			//studentDao.selectStudent(columnNameArr, columnValueArr);
			
			//studentDao.dropTable();
			//scottyCon.importExcel(arrStudents);
			//studentD = scottyCon.selectStudent("andrewId", "0078");
			
			//System.out.println(studentD);
			
			//ArrayList<Student> arrStu = new ArrayList<Student>();
			
			//ReadExcel readExcel = new ReadExcel();
			//arrStu = readExcel.read("testimport\\Testimport\\testExcelSheet.xls");
			
			//PdfCreator pdfC = new PdfCreator(arrStu);
			//pdfC.setExportLocation(path);
			
			//pdfC.printTable3();
	
		}
		
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
	}

}
