package com.luanpontes.bankslipspool.service;

import java.time.LocalDate;
import java.util.List;

import com.luanpontes.bankslipspool.calc.FineCalc;
import com.luanpontes.bankslipspool.model.Bankslip;

public interface BankslipService {
	
	List<FineCalc> fineCalcs();
	
	Boolean isFine(Bankslip bankslip, LocalDate duetDate);
	
	Integer calcFine(Bankslip bankslip, LocalDate duetDate);

}
