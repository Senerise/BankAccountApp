package com.fsene.kata.bankaccount.services.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Client;
import com.fsene.kata.bankaccount.entities.OperationType;
import com.fsene.kata.bankaccount.repositories.AccountRepository;
import com.fsene.kata.bankaccount.services.AccountServiceImpl;

/**
 * @author Falilou SENE
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {
	@Spy
	@InjectMocks
	private AccountServiceImpl accountService;
	@Mock
	private AccountRepository accountRepository;
	@Mock
	private TestEntityManager testEntityManager;
	private Account account;
	private Client client;

	@Before
	public void init() {
		client = new Client("Jean", "Dujardin");
		account = new Account("125422221", 200L);
		account.setClient(client);

		testEntityManager.persist(client);
		testEntityManager.persist(account);

	}

	@Test
	public void getAccountByNumberTest() {
		doReturn(account).when(accountRepository).findByNumber("125422221");
		Account resultAccount = accountService.getAccountByNumber("125422221");
		assertThat(resultAccount).isNotNull().isEqualToComparingFieldByField(account);
	}

	@Test
	public void makeWithdrawal() {
		doReturn(account).when(accountRepository).findByNumber("125422221");
		long oldBalance = account.getBalance();
		accountService.updateAccount("125422221", OperationType.WITHDRAWAL, 100L);
		assertThat(account.getBalance()).isNotNull().isEqualTo(oldBalance - 100L);
	}

	@Test
	public void makeADeposit() {
		doReturn(account).when(accountRepository).findByNumber("125422221");
		long oldBalance = account.getBalance();
		accountService.updateAccount("125422221", OperationType.DEPOSIT, 100L);
		assertThat(account.getBalance()).isNotNull().isEqualTo(oldBalance + 100L);
	}

}
