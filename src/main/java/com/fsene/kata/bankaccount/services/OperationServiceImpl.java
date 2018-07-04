package com.fsene.kata.bankaccount.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Operation;
import com.fsene.kata.bankaccount.repositories.AccountRepository;

/**
 * @author Falilou SENE
 *
 */
@Service("operationService")
public class OperationServiceImpl implements OperationService {
	
	
	@Autowired
	AccountRepository accountRepository;


	/* (non-Javadoc)
	 * @see com.fsene.kata.bankaccount.services.OperationService#getAllOperationsByAccountNumber(java.lang.String)
	 */
	@Override
	public List<Operation> getAllOperationsByAccountNumber(String accountNumber) {
		Account account=accountRepository.findByNumber(accountNumber);
		if(account!=null) {
			return account.getOperations();
		}
		return null;
	}

}
