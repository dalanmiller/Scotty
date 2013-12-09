package com.cmu.scotty.exception;

/**
 * 
 * @author Leo, Rebacca, Ray, Tania, Daniel
 * 
 * This is an exception class.
 * If the photo folder path is wrong, throw this exception
 */
public class WrongPhotoException extends Exception {
	
	@Override
	public String getMessage() {
	    return " The Photo you entered is wrong !";

	}

}
