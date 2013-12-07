package com.cmu.scotty.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import jxl.read.biff.BiffException;

import com.cmu.scotty.exception.ArrayListDoesNotMatch;
import com.cmu.scotty.exception.WrongExcelException;
import com.cmu.scotty.exception.WrongTextException;
import com.cmu.scotty.model.Student;
import com.cmu.scotty.persistence.StudentDao;



public class ScottyController {
	
	public static final String studentTable = "STUDENT";
	
	public StudentDao studentDao = new StudentDao();
	
	public ReadExcel readExcel = new ReadExcel();
	
	public ReadText readText = new ReadText();
	
	/*public void importExcel(ArrayList<Student> arrStudents)
	{		
		studentDao.dropTable();
		studentDao.createTable();
        //readExcel.read(inputexcel);
		
	}*/
	
	
	public void readExcel (String excelPath) throws BiffException, IOException, WrongExcelException, SQLException, Exception
	{
		ArrayList<Student> arrStudents = readExcel.read(excelPath);	
		
		ArrayList<Student> arrStuNotInDb = fetchStuPresentInDb(arrStudents);
		
		if(arrStuNotInDb!=null)
		{
			boolean chkTbleStatus =  studentDao.checkTable();
			if(chkTbleStatus==false)
			{
				studentDao.createTable();
			}
			else
			{
				// Insert or Update
				studentDao.insertStudents(arrStuNotInDb);
			}		
		}			
	}
	
	public ArrayList<Student> fetchStuPresentInDb(ArrayList<Student> studentsNotInDb) throws SQLException, Exception
	{
		ArrayList<String> arrPrAndId = selectAndrewIds();
		ArrayList<Student> arrStuNotPrDb = new ArrayList();
		Student student = new Student();
		
		String studentId = null;
		
		Iterator iteratorStu = studentsNotInDb.iterator();
		
		while(iteratorStu.hasNext())
		{
			student = (Student)iteratorStu.next();
			if(!arrPrAndId.contains(student.getAndrewID()))
			{
				arrStuNotPrDb.add(student);
			}
		}
		
		return arrStuNotPrDb;
	}
	
	public void insertText (String textPath) throws IOException,WrongTextException, SQLException,Exception
	{
		ArrayList<Student> arrStudents = readText(textPath);	
		//arrStudents = readText.read(textPath);
		
		ArrayList<Student> arrStuNotInDb = fetchStuPresentInDb(arrStudents);
		
		if(arrStuNotInDb!=null)
		{
			boolean chkTbleStatus =  studentDao.checkTable();
			if(chkTbleStatus==false)
			{
				studentDao.createTable();
			}
			else
			{
				// Insert
				studentDao.insertStudents(arrStuNotInDb);
			}
		}
	    
	}
	
	
	public ArrayList<Student> readText (String textPath) throws IOException,WrongTextException
	{
		ArrayList<Student> arrStudents = readText.read(textPath);
		return arrStudents;
	}
	
		
	public ArrayList<Student> selectStudent(String columnName,String columnValue) throws SQLException, Exception
	{
		ArrayList<Student> arrStudent = studentDao.selectStudent(columnName, columnValue);
		return arrStudent;
	}
	
	
	public ArrayList<Student> selectStudent(ArrayList<String> columnNames,ArrayList<String> columnValues) throws SQLException, Exception
	{
		ArrayList<Student> arrStudent = studentDao.selectStudent(columnNames, columnValues);
		return arrStudent;
	}
	
	public ArrayList<Student> selectStudentOnAndrewIds(ArrayList<String> andrewIds) throws SQLException, Exception
	{
		ArrayList<Student> arrStudent = studentDao.selectStudents(andrewIds);
		return arrStudent;
	}
	
	public ArrayList<String> selectAndrewIds() throws SQLException, Exception
	{
		ArrayList<String> arrAndrewIds = studentDao.selectAndrewIds();
		return arrAndrewIds;
	}

	public ArrayList<String> selectCountries() throws SQLException, Exception
	{
		ArrayList<String> arrCountries = studentDao.selectCountries();
		return arrCountries;
	}
}
