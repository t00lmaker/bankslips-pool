package com.luanpontes.bankslipspool.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.luanpontes.bankslipspool.calc.AfterTenDaysFineCalc;
import com.luanpontes.bankslipspool.calc.FineCalc;
import com.luanpontes.bankslipspool.calc.TenDaysFineCalc;
import com.luanpontes.bankslipspool.model.Bankslip;
import com.luanpontes.bankslipspool.service.BankslipService;

@Service
public class BankslipServiceImp implements BankslipService {

	@Override
	public Boolean isFine(Bankslip bankslip, LocalDate payDate) {
		return fineCalcs()
				.stream()
				.filter(calc -> calc.hasFine(bankslip, payDate))
				.findFirst()
				.isPresent();
	}

	@Override
	public Integer calcFine(Bankslip bankslip, LocalDate payDate) {
		Optional<FineCalc> findFirst = fineCalcs().stream().filter(calc -> calc.hasFine(bankslip, payDate)).findFirst();
		return findFirst.isPresent() ? findFirst.get().calcValue(bankslip, payDate) : 0;
	}

	@Override
	public List<FineCalc> fineCalcs() {
		return Arrays.asList(new TenDaysFineCalc(), new AfterTenDaysFineCalc());
	}

}
