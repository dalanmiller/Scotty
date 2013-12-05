package com.cmu.scotty.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import com.cmu.scotty.exception.WrongPhotoException;
import com.cmu.scotty.exception.WrongPhotoException;
import com.cmu.scotty.model.Student;

public class ReadPhoto {
	public String readPhoto(String inputphotos)throws IOException, WrongPhotoException{
		File Dir=new File(inputphotos);
		File[] files=Dir.listFiles();
		if (files.length==0) throw new WrongPhotoException();
		int countphotos=0;
		for (int i=0;i<files.length;i++){
			String filename=files[i].getName();
			if (filename.endsWith(".jpg")) {
				countphotos++;				
			}
		}
		if (countphotos==0) return null;
		
		return inputphotos;
	}
}
