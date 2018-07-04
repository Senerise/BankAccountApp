package com.fsene.kata.bankaccount.exceptions;

/**
 * @author Falilou SENE
 *
 */
public class WithDrawableException extends RuntimeException {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1218624880279675280L;

	public WithDrawableException() {
		super("your account balance is insufficient ! The transaction failed.");
	}
}
