package com.cmu.scotty.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jxl.read.biff.BiffException;

import com.cmu.scotty.exception.WrongExcelException;
import com.cmu.scotty.model.Student;
import com.cmu.scotty.persistence.StudentDao;



public class ScottyController {
	
	public static final String studentTable = "STUDENT";
	
	public StudentDao studentDao = new StudentDao();
	
	public ReadExcel readExcel = new ReadExcel();
	
	public void importExcel(ArrayList<Student> arrStudents)
	{		
		studentDao.dropTable();
		studentDao.createTable();
        //readExcel.read(inputexcel);
		
	}
	
	public void readExcel (String excelPath) throws BiffException, IOException, WrongExcelException, SQLException
	{
		ArrayList<Student> arrStudents = new ArrayList<Student>();	
		arrStudents = readExcel.read(excelPath);
	    boolean chkTbleStatus =  studentDao.checkTable();
		if(chkTbleStatus==false)
		{
			studentDao.createTable();
		}
		else
		{
			// Insert or Update
			studentDao.insertStudents(arrStudents);
		}		
		studentDao.insertStudents(arrStudents);
		
	}
		
	public ArrayList<Student> selectStudent(String columnName,String columnValue)
	{
		ArrayList<Student> arrStudent = studentDao.selectStudent(columnName, columnValue);
		return arrStudent;
	}

	public ArrayList<String> selectCountries()
	{
		ArrayList<String> arrCountries = studentDao.selectCountries();
		return arrCountries;
	}
}
