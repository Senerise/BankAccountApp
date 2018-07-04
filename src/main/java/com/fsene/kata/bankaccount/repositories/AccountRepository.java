package com.fsene.kata.bankaccount.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fsene.kata.bankaccount.entities.Account;

/**
 * @author Falilou SENE
 *
 */
@Repository("accountRepository")
public interface AccountRepository extends JpaRepository<Account, Long> {
	/**
	 * Récupére un compte à partir de son numéro
	 * 
	 * @param number le numéro dont on veux retrouver le compte
	 * @return le compte comportant le numéro de compte en paramètre
	 */
	@Query(value = "SELECT * FROM ACCOUNT WHERE NUMBER LIKE ?1", nativeQuery = true)
	Account findByNumber(String number);
}
