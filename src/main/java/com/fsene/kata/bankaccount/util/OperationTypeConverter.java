package com.fsene.kata.bankaccount.util;

import java.beans.PropertyEditorSupport;

import com.fsene.kata.bankaccount.entities.OperationType;

/**
 * @author @author Falilou SENE
 *
 */
public class OperationTypeConverter extends PropertyEditorSupport {


	   /**
	    * {@inheritDoc}
	    *
	    */
	/* (non-Javadoc)
	 * @see java.beans.PropertyEditorSupport#setAsText(java.lang.String)
	 */
	public void setAsText(final String textValue) throws IllegalArgumentException {
		setValue(OperationType.fromValue(textValue));
	}
}
