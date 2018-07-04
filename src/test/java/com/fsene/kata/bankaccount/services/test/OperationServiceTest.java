package com.fsene.kata.bankaccount.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Client;
import com.fsene.kata.bankaccount.entities.Operation;
import com.fsene.kata.bankaccount.entities.OperationType;
import com.fsene.kata.bankaccount.repositories.AccountRepository;
import com.fsene.kata.bankaccount.services.OperationServiceImpl;

/**
 * @author Falilou SENE
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class OperationServiceTest {

	@Spy
	@InjectMocks
	private OperationServiceImpl operationService;

	private Client client;
	private Operation depot;
	private Operation retrait;
	private Account account;

	@Mock
	private AccountRepository accountRepository;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		client = new Client("Jean", "Dujardin");
		account = new Account("125422221", 200L);
		account.setClient(client);

		depot = new Operation(new Date(100L), 350L, 200L, OperationType.DEPOSIT);
		depot.setAccount(account);
		retrait = new Operation(new Date(100L), 50L, 200L, OperationType.WITHDRAWAL);
		depot.setAccount(account);

		account.getOperations().add(depot);
		account.getOperations().add(retrait);

		accountRepository.save(account);
	}

	@Test
	public void getAllOperationsByAccountNumberTest() {

		doReturn(account).when(accountRepository).findByNumber("125422221");

		List<Operation> operations = operationService.getAllOperationsByAccountNumber("125422221");

		assertThat(operations.size()).isEqualTo(2);
		assertThat(operations).isNotEmpty().containsSequence(depot, retrait);
	}

}
