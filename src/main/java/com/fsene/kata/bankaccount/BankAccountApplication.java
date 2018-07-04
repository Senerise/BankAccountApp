package com.fsene.kata.bankaccount;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.fsene.kata.bankaccount.entities.Account;
import com.fsene.kata.bankaccount.entities.Client;
import com.fsene.kata.bankaccount.entities.Operation;
import com.fsene.kata.bankaccount.entities.OperationType;
import com.fsene.kata.bankaccount.repositories.AccountRepository;
import com.fsene.kata.bankaccount.repositories.ClientRepository;

/**
 * @author Falilou SENE
 *
 */
@SpringBootApplication
public class BankAccountApplication {

	private static final Logger logger = LoggerFactory.getLogger(BankAccountApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BankAccountApplication.class, args);
	}

	/**
	 * Peuple la base avec quelques données
	 * 
	 * @param clientRepository
	 * @param accountRepository
	 * @return CommandLineRunner
	 */
	@Bean
	public CommandLineRunner setup(ClientRepository clientRepository, AccountRepository accountRepository) {
		return (args) -> {

			Client gustavo = new Client("Eldo", "Pica");

			clientRepository.save(gustavo);

			Account account = new Account("1001", 2000);

			account.setClient(gustavo);

			Operation operation = new Operation(new Date(), 350L, 12540L, OperationType.DEPOSIT);
			operation.setAccount(account);
			account.getOperations().add(operation);
			accountRepository.save(account);

			logger.info("The sample data has been generated");
		};
	}
}
