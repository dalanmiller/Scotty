package com.cmu.scotty.controller;

import java.io.File;
import java.io.IOException;
import com.cmu.scotty.exception.WrongPhotoException;

/**
 * 
 * @author Leo, Rebacca, Ray, Tania, Daniel
 * 
 * This is a class for reading photo folder
 *
 */
public class ReadPhoto {
	/**
	 * This is a method to read the photo folder path
	 * @param inputphotos
	 * @return String
	 * @throws IOException
	 * @throws WrongPhotoException
	 */
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
