package com.fsene.kata.bankaccount.repositories.test;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Client;
import com.fsene.kata.bankaccount.repositories.AccountRepository;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author Falilou SENE
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class AccountRepositoryTest {
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private TestEntityManager testEntityManager;
	private Account account;
	private Client client;

	@Before
	public void init() {
		client = new Client("Jean", "Dujardin");
		account = new Account("125422221", 200L);
		account.setClient(client);
		client.setAccount(account);

		testEntityManager.persist(client);
		testEntityManager.persist(account);

	}
	@Test
	public void findByAccountNumberTest() {
		Account resultAccount= accountRepository.findByNumber("125422221");
		   assertThat(resultAccount)
           .isNotNull()
           .isEqualToComparingFieldByField(account);
	}

}
