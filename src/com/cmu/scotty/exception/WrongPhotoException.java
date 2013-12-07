package com.cmu.scotty.exception;

public class WrongPhotoException extends Exception {
	
	@Override
	public String getMessage() {
	    return " The Photo you entered is wrong !";

	}

}
