package com.luanpontes.bankslipspool.service;

import com.luanpontes.bankslipspool.model.Bankslip;
import com.luanpontes.bankslipspool.model.StatusBankslip;

public interface BankslipStatusService {

	Boolean isValidChangeStatus(Bankslip banksplip, StatusBankslip status);
	
	Bankslip changeStatus(Bankslip banksplip, StatusBankslip status);
}
