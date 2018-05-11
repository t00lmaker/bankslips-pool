package com.luanpontes.bankslipspool.calc;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import com.luanpontes.bankslipspool.model.Bankslip;

public abstract class FineCalc {
	
	protected Long diffDaysDueDate(Bankslip bankslip, LocalDate payDate) {
		LocalDate dueDate = bankslip.getDueDate();
		return ChronoUnit.DAYS.between(dueDate, payDate);
	}
	
	public Integer calcValue(Bankslip bankslip, LocalDate payDate) {
		Long diffDaysDueDate = diffDaysDueDate(bankslip, payDate);
		if (diffDaysDueDate > 0) { 
			Float valueByDay = bankslip.getTotalInCents() * taxByDay();
			return (int) (diffDaysDueDate * valueByDay);
		}
		return 0; 
	}
	
	public abstract Boolean hasFine(Bankslip bankslip, LocalDate payDate);

	public abstract Float taxByDay();
	
	
}
