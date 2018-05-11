package com.luanpontes.bankslipspool.calc;

import java.time.LocalDate;

import com.luanpontes.bankslipspool.model.Bankslip;

public class TenDaysFineCalc extends FineCalc {

	@Override
	public Boolean hasFine(Bankslip bankslip, LocalDate payDate) {
		Long diffDaysDueDate = diffDaysDueDate(bankslip, payDate);
		return diffDaysDueDate > 0 && diffDaysDueDate <= 10 ;
	}

	@Override
	public Float taxByDay() {
		return 0.005f;
	}
	
}
