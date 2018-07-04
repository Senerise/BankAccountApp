package com.fsene.kata.bankaccount.entities;

import java.util.Date;

import javax.persistence.*;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Falilou SENE
 *
 */
@Entity
public class Operation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Date date;

	private Long amount;

	/**
	 * Solde courant avant retrait ou depot
	 */
	private long currentBalance;

	private OperationType operationType;
	@JsonIgnore
	@ManyToOne
	private Account account;

	public Operation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Operation(Date date, Long amount, long currentBalance, OperationType operationType) {
		super();
		this.date = date;
		this.amount = amount;
		this.currentBalance = currentBalance;
		this.operationType = operationType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public OperationType getOperationType() {
		return operationType;
	}

	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public long getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(long currentBalance) {
		this.currentBalance = currentBalance;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

}
