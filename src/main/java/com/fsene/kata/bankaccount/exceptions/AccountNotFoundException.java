package com.fsene.kata.bankaccount.exceptions;

/**
 * @author Falilou SENE
 *
 */
public class AccountNotFoundException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8590692175288013242L;

	public AccountNotFoundException(String accountNumber) {
		super("could not find account '" + accountNumber + "'.");
	}
}
