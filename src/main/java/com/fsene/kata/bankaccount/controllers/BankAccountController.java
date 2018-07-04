package com.fsene.kata.bankaccount.controllers;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Client;
import com.fsene.kata.bankaccount.entities.Operation;
import com.fsene.kata.bankaccount.entities.OperationType;
import com.fsene.kata.bankaccount.repositories.AccountRepository;
import com.fsene.kata.bankaccount.services.AccountService;
import com.fsene.kata.bankaccount.services.OperationService;
import com.fsene.kata.bankaccount.util.OperationTypeConverter;

/**
 * @author Falilou SENE
 *
 */
@RestController
public class BankAccountController {

	@Autowired
	private AccountService accountService;
	@Autowired
	private OperationService operationService;

	@RequestMapping(method = RequestMethod.POST, path = "/account/{accountNumber}/operations/{operationType}/{amount}")
	public ResponseEntity<Account> updateAccount(@PathVariable("accountNumber") String accountNumber,
			@PathVariable("operationType") OperationType operationType, @PathVariable("amount") long amount) {

		Account account = accountService.updateAccount(accountNumber, operationType, amount);
		return new ResponseEntity<Account>(account, HttpStatus.OK);
	}

	@RequestMapping(value = "/account/{accountNumber}", method = RequestMethod.GET)
	public ResponseEntity<Account> getAccount(@PathVariable("accountNumber") String accountNumber) {

		Account account = accountService.getAccountByNumber(accountNumber);
		if (account != null) {
			return new ResponseEntity<>(account, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/account/{accountNumber}/operations", method = RequestMethod.GET)
	public ResponseEntity<Collection<Operation>> getAccountHistory(
			@PathVariable("accountNumber") String accountNumber) {
		List<Operation> operations = operationService.getAllOperationsByAccountNumber(accountNumber);
		if (operations != null) {
			return new ResponseEntity<>(operations, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@InitBinder
	public void initBinder(final WebDataBinder webdataBinder) {
		webdataBinder.registerCustomEditor(OperationType.class, new OperationTypeConverter());
	}
}
