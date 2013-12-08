package com.cmu.scotty.exception;

public class WrongTextException extends Exception{
	
	@Override
	public String getMessage() {
	    return " The Text file you entered is in wrong format !";

	}

}
