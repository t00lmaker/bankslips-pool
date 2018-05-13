package com.luanpontes.bankslipspool.service;

import java.util.Map;

import com.luanpontes.bankslipspool.auth.ClaimsToken;

/**
 * Servico para geração de Token para altenticação da aplicação.
 * Esse serviço trabalha com stateless tokens, ou seja,  a atutenticação
 * deve conter todas as informações necessárias para validar o usuário e identifica-lo. 
 * 
 * @author Luan Pontes
 *
 */
public interface TokenService {
	
	
	/**
	 * Chave privada para ser utlizada na geração do Token.
	 * @return
	 */
	String keySalt();
	
	

	/**
	 * Gera o token a partir das informações passadas por meio 
	 * do TokenClaims passados.
	 *  
	 * @param claims
	 * @return
	 */
	String generate(Map<ClaimsToken, String> claims);
	

	/**
	 * Valida o token passado. 
	 * 
	 * @param token
	 * @return
	 */
	boolean isValid(String token, String audience);
	
	
}
