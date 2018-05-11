package com.luanpontes.bankslipspool.service

import com.luanpontes.bankslipspool.model.Bankslip
import com.luanpontes.bankslipspool.service.impl.BankslipServiceImp
import spock.lang.Specification

class BankslipServiceImpSpec extends Specification {
	
	BankslipServiceImp bankslipServiceImp
	
	def "Boleto pago antes da due_date, não deve ter muilta" (){
		setup: 
			Date today = new Date()
		    Date tomorow = today+1
			Bankslip bankslip = new Bankslip(dueDate: tomorow)
		when: 
			Boolean result = bankslipServiceImp.isFine(bankslip, today)	
		then:
			result == false 
		
	}

	def "Boleto pago até a due_date, não deve ter muilta" (){
		setup: 
			Date today = new Date()
			Bankslip bankslip = new Bankslip(dueDate: today)
		when: 
			Boolean result = bankslipServiceImp.isFine(bankslip, today)	
		then:
			result == false 				
	}
	
	def "Boleto pago após a due_date, deve ter muilta" (){
		setup:
			Date today = new Date()
			Date tomorow = today+1
			Bankslip bankslip = new Bankslip(dueDate: today)
		when:
			Boolean result = bankslipServiceImp.isFine(bankslip, tomorow)
		then:
			result
	}
	
	def "Boleto pago com sem atraso, sem multa" (){
		setup:
			Integer value = 1000
			Date today = new Date()
			Bankslip bankslip = new Bankslip(dueDate: today, totalInCents: value)
			Integer fivePerCentValue = value * 0.005
		when:
			Integer result = bankslipServiceImp.calcFine(bankslip, today)
		then:
			result == 0
	}

	def "Boleto pago com atraso de até 10 dias, multa 0,5%" (){
		setup:
			Integer value = 1000
			Date today = new Date()
			Date tenDaysAfter = today+10
			Bankslip bankslip = new Bankslip(dueDate: today, totalInCents: value)
			Integer fivePerCentValue = value * 0.005 
		when:
			Integer result = bankslipServiceImp.calcFine(bankslip, tenDaysAfter)
		then:
			result == (fivePerCentValue * 10)
	}
	
	def "Boleto pago com atraso de com pós 10 dias, multa 1%" (){
		setup:
			Integer value = 1000
			Date today = new Date()
			Date elevenDaysAfter = today+11
			Bankslip bankslip = new Bankslip(dueDate: today, totalInCents: value)
			Integer onePerCentValue = value*0.01
		when:
			Integer result = bankslipServiceImp.calcFine(bankslip, elevenDaysAfter)
		then:
			result == (onePerCentValue*11)
	}

}
