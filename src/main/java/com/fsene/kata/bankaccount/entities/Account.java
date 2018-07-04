package com.fsene.kata.bankaccount.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Falilou SENE
 *
 */
@Entity
public class Account {
	@Id
	@GeneratedValue
	private Long id;

	@NotNull
	private String number;

	private long balance;

	@JsonIgnore
	@OneToOne//(fetch = FetchType.LAZY)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@OneToMany(cascade = CascadeType.ALL,
			/* fetch = FetchType.LAZY, */
			mappedBy = "account")
	private List<Operation> operations = new ArrayList<Operation>();

	public Account(String number, long balance) {
		super();
		this.number = number;
		this.balance = balance;
	}

	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

}
