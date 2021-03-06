package com.luanpontes.bankslipspool.service.impl;

import org.springframework.stereotype.Service;

import com.luanpontes.bankslipspool.exception.ChanceStatusNotValidException;
import com.luanpontes.bankslipspool.model.Bankslip;
import com.luanpontes.bankslipspool.model.StatusBankslip;
import com.luanpontes.bankslipspool.service.BankslipStatusService;

@Service
public class BankslipStatusServiceImp implements BankslipStatusService {

	@Override
	public Boolean isValidChangeStatus(Bankslip banksplip, StatusBankslip status) {
		return banksplip.getStatus().getStatusPermited().contains(status);
	}

	@Override
	public Bankslip changeStatus(Bankslip banksplip, StatusBankslip status) {
		if(isValidChangeStatus(banksplip, status))
			banksplip.setStatus(status);
		else
			throw new ChanceStatusNotValidException("Not allowed to change "+banksplip.getStatus()+" to "+status);
		
		return banksplip;
	}

}
