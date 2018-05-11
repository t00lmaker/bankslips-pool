package com.luanpontes.bankslipspool.service;

import java.util.Date;

import com.luanpontes.bankslipspool.model.Bankslip;

public interface BankslipService {
	
	Boolean isFine(Bankslip bankslip, Date duetDate);
	
	Integer calcFine(Bankslip bankslip);

}
