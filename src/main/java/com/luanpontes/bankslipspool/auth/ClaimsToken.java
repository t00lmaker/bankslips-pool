package com.luanpontes.bankslipspool.auth;

/**
 * Chave par as informações que devem ser
 * contidas no token de acesso.
 * 
 * @author Luan Pontes
 *
 */
public enum ClaimsToken {
	
	/* Espeficica o emissor do token.*/
	ISSUER,  
	
	/* Para quem  o token foi gerado. */
	AUDIENCE,
	
	/* Quando o token expira. */
	EXPIRES_AT, 

	/* Objetivo especifico do token, caso tenha.*/
	SUBJECT;

}
