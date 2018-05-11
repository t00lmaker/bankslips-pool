package com.luanpontes.bankslipspool.service

import java.time.LocalDate

import com.luanpontes.bankslipspool.model.Bankslip
import com.luanpontes.bankslipspool.service.impl.BankslipServiceImp

import spock.lang.Specification

class BankslipServiceImpSpec extends Specification {
	
	BankslipServiceImp bankslipServiceImp = new BankslipServiceImp() 
	
	def "Boleto pago antes da due_date, não deve ter multa" (){
		setup: 
			LocalDate today = LocalDate.now()
			LocalDate tomorow = today.plusDays(1)
			Bankslip bankslip = new Bankslip(dueDate: tomorow)
		when: 
			Boolean result = bankslipServiceImp.isFine(bankslip, today)	
		then:
			result == false 
		
	}

	def "Boleto pago até a due_date, não deve ter multa" (){
		setup: 
			LocalDate today = LocalDate.now()
			Bankslip bankslip = new Bankslip(dueDate: today)
		when: 
			Boolean result = bankslipServiceImp.isFine(bankslip, today)	
		then:
			result == false 				
	}
	
	def "Boleto pago após a due_date, deve ter multa" (){
		setup:
			LocalDate today = LocalDate.now()
			LocalDate tomorow = today.plusDays(1)
			Bankslip bankslip = new Bankslip(dueDate: today, )
		when:
			Boolean result = bankslipServiceImp.isFine(bankslip, tomorow)
		then:
			result
	}
	
	def "Boleto pago com sem atraso, sem multa" (){
		setup:
			Integer value = 1000
			LocalDate today = LocalDate.now()
			Bankslip bankslip = new Bankslip(dueDate: today, totalInCents: value)
			Integer fivePerCentValue = value * 0.005
		when:
			Integer result = bankslipServiceImp.calcFine(bankslip, today)
		then:
			result == 0
	}

	def "Boleto pago com atraso de até 10 dias, multa 0,5% por dia" (){
		setup:
			Integer value = 100
			LocalDate today = LocalDate.now()
			LocalDate tenDaysAfter = today.plusDays(10)
			Bankslip bankslip = new Bankslip(dueDate: today, totalInCents: value)
			Double fivePerCentValue = value * 0.005 
		when:
			Integer result = bankslipServiceImp.calcFine(bankslip, tenDaysAfter)
		then:
			result == (fivePerCentValue * 10)
	}
	
	def "Boleto pago com atraso de com pós 10 dias, multa 1% por dia" (){
		setup:
			Integer value = 100
			LocalDate today = LocalDate.now()
			LocalDate elevenDaysAfter = today.plusDays(11)
			Bankslip bankslip = new Bankslip(dueDate: today, totalInCents: value)
			Double onePerCentValue = value*0.01
		when:
			Integer result = bankslipServiceImp.calcFine(bankslip, elevenDaysAfter)
		then:
			result == (onePerCentValue*11)
	}

}
