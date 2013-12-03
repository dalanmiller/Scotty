package com.cmu.scotty.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.cmu.scotty.model.*;
import com.cmu.scotty.exception.*;

public class ReadExcel {

	public ArrayList<Student> read(String inputexcel) throws IOException, BiffException,wrongexcelexception  {
		File inputWorkbook = new File(inputexcel);
	    Workbook w;
	    w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    ArrayList<Student> students =new ArrayList<Student>();
	    if(!(sheet.getCell(0,0).equals("First Name")&& sheet.getCell(1,0).equals("Last Name")
	    		&&sheet.getCell(2,0).equals("Andrew ID")&&sheet.getCell(4,0).equals("Program Track")
	    		&&sheet.getCell(5,0).equals("Part-time/Full Time")&&sheet.getCell(6,0).equals("Country")
	    		&&sheet.getCell(7,0).equals("Semester"))) throw new wrongexcelexception();
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	String FirstName= sheet.getCell(0, i).getContents();
	    	String LastName= sheet.getCell(1, i).getContents();
	    	String AndrewID= sheet.getCell(2, i).getContents();
	    	String ProgramTrack= sheet.getCell(3, i).getContents();
	    	String FullTime= sheet.getCell(4, i).getContents();
	    	String Country= sheet.getCell(5, i).getContents();
	    	String Semester= sheet.getCell(6, i).getContents();
	    	Student stu=new Student( AndrewID, FirstName, LastName, ProgramTrack, FullTime, Country,  Semester);
	    	students.add(stu);
	    	}
	    return students;
	    }

}