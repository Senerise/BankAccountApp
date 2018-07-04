package com.fsene.kata.bankaccount.services;

import java.util.List;

import com.fsene.kata.bankaccount.entities.Operation;

/**
 * @author Falilou SENE
 *
 */
public interface OperationService {
	/**
	 * Renvoie l'historique des opérations d'un compte
	 * 
	 * @param accountNumber le numéro de compte dont on veux récupérer les
	 *                      opérations
	 * @return toutes les opérations associées au compte
	 */
	List<Operation> getAllOperationsByAccountNumber(String accountNumber);
}
