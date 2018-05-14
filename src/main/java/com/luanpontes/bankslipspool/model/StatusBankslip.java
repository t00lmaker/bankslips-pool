package com.luanpontes.bankslipspool.model;

import java.util.Arrays;
import java.util.List;

/**
 * Enum para o controle de estados do
 * attributo status de Bankslip.
 * 
 * 
 * @author Luan Pontes
 *
 */
public enum StatusBankslip {
	
	CANCELED,
	PAID,
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
	

}
