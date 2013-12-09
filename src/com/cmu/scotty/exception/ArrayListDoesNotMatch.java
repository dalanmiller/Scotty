package com.cmu.scotty.exception;
/**
 * 
 * @author Leo, Rebacca, Ray, Tania, Daniel
 * 
 * This is an exception class.
 * If ArrayList does not match, throw this exception
 */
public class ArrayListDoesNotMatch extends Exception{
	
	@Override
	public String getMessage() {
	    return " The column and the value part does not match !";

	}
	

}
