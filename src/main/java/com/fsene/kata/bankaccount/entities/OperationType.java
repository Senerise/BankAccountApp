package com.fsene.kata.bankaccount.entities;

import java.util.Arrays;

/**
 * @author Falilou SENE
 *
 */
public enum OperationType {

	DEPOSIT("DEPOSIT"), WITHDRAWAL("WITHDRAWAL");

	private String value;

	OperationType(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	/**
	 * COnvertit une chaine en Operation
	 * 
	 * @param value la chaine à convertir
	 * @return le type OperationType correspondant
	 */
	public static OperationType fromValue(String value) {
		for (OperationType operationType : values()) {
			if (operationType.value.equalsIgnoreCase(value)) {
				return operationType;
			}
		}
		throw new IllegalArgumentException(
				"Unknown enum type " + value + ", Allowed values are " + Arrays.toString(values()));
	}

}