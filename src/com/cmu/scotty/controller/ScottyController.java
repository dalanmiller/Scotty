package com.cmu.scotty.controller;

import java.util.ArrayList;

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
		studentDao.insertStudents(arrStudents);
	}
	
	
	public ArrayList<Student> selectStudent(String columnName,String columnValue)
	{
		ArrayList<Student> arrStudent = studentDao.selectStudent(columnName, columnValue);
		return arrStudent;
	}

}
