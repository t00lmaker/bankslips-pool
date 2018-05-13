package com.luanpontes.bankslipspool.auth;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.luanpontes.bankslipspool.service.RequestInfoService;
import com.luanpontes.bankslipspool.service.TokenService;

/**
 * Interceptor responsavel por validar o token 
 * de acesso. 
 * 
 * @author Luan Pontes
 *
 */
public class TokenValidatorInterceptor implements HandlerInterceptor {

	@Autowired
	TokenService tokenService;
	
	@Autowired
	RequestInfoService requestInfoService;

	@Override
	public boolean preHandle( HttpServletRequest request, HttpServletResponse response, Object handler ) throws Exception {
		if (!request.getRequestURI().equals("/rest/login")) {
			String accessToken = request.getHeader("access-token");
			if(accessToken != null) {
				if(tokenService.isValid(accessToken, requestInfoService.getIp(request))) {
					return true;
				}else {
					return reutrnError(response, 401, "invalide token.");
				}
			}
			return reutrnError(response, 400, "access-token not found");
		}else {
			return true;
		}
	}

	@Override
	public void postHandle( HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView ) throws Exception {}

	@Override
	public void afterCompletion( HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex ) throws Exception {
	}
	
	private boolean reutrnError(HttpServletResponse response, int code, String msg) {
		try {
			response.getWriter().write(msg);
			response.setStatus(code);
		} catch (IOException e) {
			e.printStackTrace();
		}
        return false;
	}
}
