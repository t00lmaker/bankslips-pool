package com.luanpontes.bankslipspool.auth;

import javax.servlet.http.HttpServletRequest;

/**
 * Servico para usado para obter informações sobre a requisicao 
 * passada.
 * 
 * @author Luan Pontes
 *
 */
public interface RequestInfoService {
	
	/**
	 * Recupera o ip do request.
	 * 
	 * @param request
	 * @return
	 */
	String getIp(HttpServletRequest request);

}
