package com.luanpontes.bankslipspool.calc

import java.time.LocalDate

import com.luanpontes.bankslipspool.model.Bankslip

import spock.lang.Specification

class TenDaysFineCalcSpec extends Specification{
	
	TenDaysFineCalc calc = new TenDaysFineCalc()
	
	def "taxByDay - A taxa de juros para até dez dias de atraso é de %5" (){ 
		expect: 
			calc.taxByDay() == 0.005f
	}
	
	def "hasFine - Nao deve ser aplicado multa quando nao houver atraso"(){
		given: 
			def bankslip = new Bankslip(totalInCents: 100, dueDate: LocalDate.now())
		expect: 
			!calc.hasFine(bankslip, LocalDate.now())
	}

	def "hasFine - Não deve ser aplicado esse calculo de multa quando o atraso for maior que 10 dias"(){
		given: 
			def bankslip = new Bankslip(totalInCents: 100, dueDate: LocalDate.now())
			expect: 
				!calc.hasFine(bankslip, LocalDate.now().plusDays(15))
	}
	
	def "hasFine - Deve ser aplicado esse calculo de multa quando houve atraso e for menor que 10 dias"(){
		given:
			def bankslip = new Bankslip(totalInCents: 100, dueDate: LocalDate.now())
			expect:
				calc.hasFine(bankslip, LocalDate.now().plusDays(7))
	}
	
	def "hasFine - Deve ser aplicado esse calculo de multa quando o atraso for de 10 dias"(){
		given:
			def bankslip = new Bankslip(totalInCents: 100, dueDate: LocalDate.now())
			expect:
				calc.hasFine(bankslip, LocalDate.now().plusDays(10))
	}
	
			
	def "calcValue - O valor da multa final deve ser de 0,5% por dia." (){
		setup:
			Integer value = 100
			LocalDate today = LocalDate.now()
			LocalDate fourDaysAfter = today.plusDays(4)
			Bankslip bankslip = new Bankslip(dueDate: today, totalInCents: value)
			Double onePerCentValue = value*0.005
		when:
			Integer result = calc.calcValue(bankslip, fourDaysAfter)
		then:
			result == (onePerCentValue*4)
	}

}
