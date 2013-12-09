package com.cmu.scotty.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import com.cmu.scotty.model.*;
import com.cmu.scotty.exception.*;
/**
 * 
 * @author Leo, Rebacca, Ray, Tania, Daniel
 * 
 * This is a class for reading excel files
 *
 */
public class ReadExcel {

	/**
	 * This is a method to read excel file path.
	 * The method read every cells in the sheet and return an ArrayList of Student
	 * @param inputexcel
	 * @return ArrayList<Student>
	 * @throws IOException
	 * @throws BiffException
	 * @throws WrongExcelException
	 */
	public ArrayList<Student> read(String inputexcel) throws IOException, BiffException,WrongExcelException  {
	//	if (inputexcel.endsWith(".xls"))
		File inputWorkbook = new File(inputexcel);
	    Workbook w;
	    w = Workbook.getWorkbook(inputWorkbook);
	    Sheet sheet = w.getSheet(0);
	    ArrayList<Student> students =new ArrayList<Student>();

	    if(!(sheet.getCell(0,0).getContents().equals("First Name")&& sheet.getCell(1,0).getContents().equals("Last Name")
	    		&&sheet.getCell(2,0).getContents().equals("Andrew ID")&&sheet.getCell(3,0).getContents().equals("Program Track")
	    		&&sheet.getCell(4,0).getContents().equals("Part-time/Full Time")&&sheet.getCell(5,0).getContents().equals("Country")
	    		&&sheet.getCell(6,0).getContents().equals("Semester"))) throw new WrongExcelException();
	    for (int i = 1; i < sheet.getRows(); i++) {
	    	String firstName = sheet.getCell(0, i).getContents();
	    	String lastName = sheet.getCell(1, i).getContents();
	    	String andrewID = sheet.getCell(2, i).getContents();
	    	String programTrack = sheet.getCell(3, i).getContents();
	    	String fullTime = sheet.getCell(4, i).getContents();
	    	String country = sheet.getCell(5, i).getContents();
	    	String semester = sheet.getCell(6, i).getContents();
	    	//String photoPath = sheet.getCell(7, i).getContents();
	    	Student stu = new Student( andrewID, firstName, lastName, programTrack, fullTime, country,  semester, null);
	    	students.add(stu);
	    	}
	    return students;
	    }

}
