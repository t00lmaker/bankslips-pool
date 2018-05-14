package com.luanpontes.bankslipspool.calc;

import java.time.LocalDate;

import com.luanpontes.bankslipspool.model.Bankslip;

/**
 * Responsavel por guardar as regras 
 * para a aplicação de multas apos o atraso de 
 * 10 dias do boleto.
 * 
 * 
 * @author Luan Pontes
 *
 */
public class AfterTenDaysFineCalc extends FineCalc {

	@Override
	public Boolean hasFine(Bankslip bankslip, LocalDate payDate) {
		Long diffDaysDueDate = diffDaysDueDate(bankslip, payDate);
		return diffDaysDueDate > 10 ;
	}

	@Override
	public Float taxByDay() {
		return 0.01f;
	}

	
}
