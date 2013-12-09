package com.cmu.scotty.exception;

/**
 * 
 * @author Leo, Rebacca, Ray, Tania, Daniel
 * 
 * This is an exception class.
 * If the excel is in wrong format, throw this exception
 */
public class WrongExcelException extends Exception {

	@Override
	public String getMessage() {
	    return " The Excel you entered is in the wrong format !";

	}
	
}
