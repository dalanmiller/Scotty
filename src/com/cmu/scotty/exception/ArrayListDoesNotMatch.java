package com.cmu.scotty.exception;

public class ArrayListDoesNotMatch extends Exception{
	
	@Override
	public String getMessage() {
	    return " The column and the value part does not match !";

	}
	

}
