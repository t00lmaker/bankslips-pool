package com.luanpontes.bankslipspool.service

import com.luanpontes.bankslipspool.model.Bankslip
import static com.luanpontes.bankslipspool.model.StatusBankslip.*

import com.luanpontes.bankslipspool.exception.ChanceStatusNotValidException
import com.luanpontes.bankslipspool.service.impl.BankslipServiceImp
import com.luanpontes.bankslipspool.service.impl.BankslipStatusServiceImp
import spock.lang.Specification

class BankslipStatusServiceImpSpec extends Specification {
	
	BankslipStatusServiceImp bankslipStatusServiceImp
	
	def "Mudar status, PENDING >> PAID, mudanca permitida." (){
		expect:
			bankslipStatusServiceImp.isValidChangeStatus(new Bankslip(status: PENDING), PAID)
	}

	def "Mudar status, PENDING >> CANCELED, mudanca permitida." (){
		expect:
			bankslipStatusServiceImp.isValidChangeStatus(new Bankslip(status: PENDING), CANCELED)
	}
	
	def "Mudar status, CANCELED >> PENDING, mudanca permitida." (){
		expect:
			bankslipStatusServiceImp.isValidChangeStatus(new Bankslip(status: CANCELED), PENDING)
	}
	
	def "Mudar status, CANCELED >> PAID mudanca NãO permitida." (){
		expect:
			!bankslipStatusServiceImp.isValidChangeStatus(new Bankslip(status: CANCELED), PAID)
	}
	
	def "Mudar status, PAID >> CANCELED mudanca NãO permitida." (){
		expect:
			!bankslipStatusServiceImp.isValidChangeStatus(new Bankslip(status: PAID), CANCELED)
	}
	
	def "Mudar status, deve alterar o status do boleto para o status passado." (){
		given: 
			Bankslip bankslip = new Bankslip(status: PENDING)
		when:
			bankslipStatusServiceImp.changeStatus(bankslip, CANCELED)
		then:
			bankslip.status == CANCELED
	}

	def "Mudar status, quando situação nao permitida deve lança uma exception" (){
		given: 
			Bankslip bankslip = new Bankslip(status: PAID)
		when:
			bankslipStatusServiceImp.changeStatus(bankslip, CANCELED)
		then:
			def ex = thrown(ChanceStatusNotValidException)
	}
	
	

}
