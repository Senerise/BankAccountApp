package com.fsene.kata.bankaccount.services;

import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Operation;
import com.fsene.kata.bankaccount.entities.OperationType;
import com.fsene.kata.bankaccount.exceptions.AccountNotFoundException;
import com.fsene.kata.bankaccount.exceptions.WithDrawableException;
import com.fsene.kata.bankaccount.repositories.AccountRepository;

/**
 * @author Falilou SENE
 * 
 * 
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

	@Autowired
	AccountRepository accountRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fsene.kata.bankaccount.services.AccountService#getAccountByNumber(java.
	 * lang.String)
	 */
	@Override
	public Account getAccountByNumber(String accountNumber) {
		// TODO Auto-generated method stub
		return accountRepository.findByNumber(accountNumber);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.fsene.kata.bankaccount.services.AccountService#updateAccount(java.lang.
	 * String, com.fsene.kata.bankaccount.entities.OperationType, long)
	 */
	@Transactional
	@Override
	public Account updateAccount(String accountNumber, OperationType deposit, long amount) {
		Account account = accountRepository.findByNumber(accountNumber);

		if (account == null) {
			throw new AccountNotFoundException(accountNumber);
		}
		if (amount <= 0) {
			throw new IllegalArgumentException("The amount to set must be greater than 0");
		}
		switch (deposit) {
		case DEPOSIT:
			return makeDeposit(account, amount);
		case WITHDRAWAL:
			return makeWithdrawal(account, amount);
		default:
			throw new IllegalArgumentException("Le type d'opération donné ne correspondant à aucun type.");
		}
	}

	/**
	 * Effectue une rentrée d'argent
	 * 
	 * @param account le compte client
	 * @param amount  le montant à retirer
	 * @return le compte avec son nouvel état
	 */
	private Account makeDeposit(Account account, long amount) {
		Operation operation = new Operation(new Date(), amount, account.getBalance(), OperationType.DEPOSIT);
		operation.setAccount(account);
		account.getOperations().add(operation);
		account.setBalance(account.getBalance() + amount);
		return accountRepository.save(account);
	}

	/**
	 * Effectue un retrait
	 * 
	 * @param account le compte client
	 * @param amount  le montant à retirer
	 * @return le compte avec son nouvel état
	 */
	private Account makeWithdrawal(Account account, long amount) {
		if (amount > account.getBalance()) {
			throw new WithDrawableException();
		}

		Operation operation = new Operation(new Date(), amount, account.getBalance(), OperationType.WITHDRAWAL);
		operation.setAccount(account);
		account.getOperations().add(operation);
		account.setBalance(account.getBalance() - amount);
		return accountRepository.save(account);

	}

	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

}
