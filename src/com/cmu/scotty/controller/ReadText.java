package com.cmu.scotty.controller;

import java.io.*;
import java.util.*;
import com.cmu.scotty.model.*;
import com.cmu.scotty.exception.*;

public class ReadText {
	
	public ArrayList<Student> read(String inputtext) throws IOException,WrongTextException{ 
		File file= new File(inputtext);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String tempString;
        ArrayList<String> content =new ArrayList<String>();
        while ((tempString = reader.readLine()) != null) {
        	content.add(tempString);}
        	reader.close();
		if (!content.get(1).contains("CLASS ROSTER")) throw new WrongTextException();
		
		ArrayList<Student> students= new ArrayList<Student>();
		for(int i=11;i<content.size()-1;i++){
			if (content.get(i).trim()==null &&content.get(i+1).trim()==null) break;
			if (content.get(i).equals("")) continue;
			
			String []stu=content.get(i).trim().split("  ");
			String andrewID=stu[stu.length-1].trim();
			Student s=new Student(andrewID);
			students.add(s);
		}
		return students;
	}
	
}
