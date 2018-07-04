package com.fsene.kata.bankaccount.services;

import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.OperationType;

/**
 * @author Falilou SENE
 *
 */
public interface AccountService {

	/**
	 * Retourne le compte client
	 * 
	 * @param accountNumber le numéro du compte
	 * @return le compte associé au numéro
	 */
	Account getAccountByNumber(String accountNumber);

	/**
	 * Met à jour le compte client avec un retrait ou une rentrée d'argent
	 * 
	 * @param accountNumber
	 * @param operationType le type d'opération à effectuer
	 * @param amount        le montant à rentrer ou retirer
	 * @return le compte avec son nouvel état
	 */
	Account updateAccount(String accountNumber, OperationType operationType, long amount);

}
