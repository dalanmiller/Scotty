package com.cmu.scotty.exception;

/**
 * 
 * @author Leo, Rebacca, Ray, Tania, Daniel
 * 
 * This is an exception class.
 * If the text file is in wrong format, throw this exception
 */
public class WrongTextException extends Exception{
	
	@Override
	public String getMessage() {
	    return " The Text file you entered is in wrong format !";

	}

}
