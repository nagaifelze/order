package com.ibm.order.service;

import com.ibm.order.model.ErrorInfo;

public class OrderValidationException extends Exception {
	ErrorInfo errorInfo;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public OrderValidationException(ErrorInfo pErrorInfo) {
		super(pErrorInfo.getErrorMsg());
		errorInfo = pErrorInfo;
	}

	public ErrorInfo getErrorInfo() {
		return errorInfo;
	}

	public void setErrorInfo(ErrorInfo errorInfo) {
		this.errorInfo = errorInfo;
	}
}
