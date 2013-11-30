package com.cmu.scotty.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.cmu.scotty.model.*;

public class ReadExcel {

	public ArrayList<Student> read(String inputexcel) throws IOException, BiffException  {
		File inputWorkbook = new File(inputexcel);
	    Workbook w;
	    w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    ArrayList<Student> students =new ArrayList<Student>();
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	String FirstName= sheet.getCell(0, i).getContents();
	    	String LastName= sheet.getCell(1, i).getContents();
	    	String AndrewID= sheet.getCell(2, i).getContents();
	    	String ProgramTrack= sheet.getCell(3, i).getContents();
	    	String FullTime= sheet.getCell(2, i).getContents();
	    	String Country= sheet.getCell(5, i).getContents();
	    	String Semester= sheet.getCell(6, i).getContents();
	    	Student stu=new Student( AndrewID, FirstName, LastName, ProgramTrack, FullTime, Country,  Semester);
	    	students.add(stu);
	    	}
	    return students;
	    }

}
