package com.luanpontes.bankslipspool.service

import spock.lang.Specification

class BankslipServiceImpSpec extends Specification {
	
	def "Caso o boleto seja de data due de anterior a 10 dias" (){
		expect:
		 2 == 2
	}
	
	def "Caso o boleto seja de data due de posterior a 10 dias" (){
		expect: 
		1 == 2
	}

}
