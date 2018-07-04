package com.fsene.kata.bankaccount.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsene.kata.bankaccount.entities.Client;

/**
 * @author Falilou SENE
 *
 */
@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {
}
