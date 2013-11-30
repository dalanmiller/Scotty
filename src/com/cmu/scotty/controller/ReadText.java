package com.cmu.scotty.controller;

import java.io.*;
import java.util.*;
import com.cmu.scotty.model.*;

public class ReadText {
	
	public ArrayList<Student> Read(String path) throws IOException,Exception{ 
		File file= new File(path);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString;
        ArrayList<String> content =new ArrayList<String>();
        while ((tempString = reader.readLine()) != null) {
        	content.add(tempString);}
        	reader.close();
		if (!content.get(1).contains("CLASS ROSTER")) throw new Exception();
		
		ArrayList<Student> students= new ArrayList<Student>();
		for(int i=11;i<content.size()-1;i++){
			if (content.get(i).trim()==null &&content.get(i+1).trim()==null) break;
			String []stu=content.get(i).split("  ");
			String andrewID=stu[stu.length-1];
			Student s=new Student(andrewID);
			students.add(s);
		}
		return students;
	}
	
}
