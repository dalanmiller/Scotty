package com.cmu.scotty.exception;

public class WrongExcelException extends Exception {

	@Override
	public String getMessage() {
	    return " The Excel you entered is in the wrong format !";

	}
	
}
