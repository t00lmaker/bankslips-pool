package com.luanpontes.bankslipspool.model;

import java.util.Arrays;
import java.util.List;

public enum StatusBankslip {
	
	CANCELED,
	PAID(CANCELED),
	PENDING(PAID, CANCELED) 
	;
	
	private List<StatusBankslip> statusPermited;
	
	StatusBankslip(StatusBankslip ... statusPermited){
		this.setStatusPermited(Arrays.asList(statusPermited));
	}

	public List<StatusBankslip> getStatusPermited() {
		return statusPermited;
	}

	public void setStatusPermited(List<StatusBankslip> statusPermited) {
		this.statusPermited = statusPermited;
	}
	
	public boolean isValid(StatusBankslip status) {
		return this.statusPermited.contains(status);
	}

}
