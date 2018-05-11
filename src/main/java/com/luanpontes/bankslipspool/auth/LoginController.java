package com.luanpontes.bankslipspool.auth;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class LoginController {
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	RequestInfoService requestInfoService;
	
	private final int USER_HASH = 3599307;
	private final int PASSWORD_HASH = 322136988;

	/* "passUser99" e "user" */
	@PostMapping("/login")
	public  ResponseEntity<String> login(@RequestBody String login, @RequestBody  String password, HttpServletRequest request) {
		if(login == null || password == null)
			return new ResponseEntity<String>("Params login and password are required", HttpStatus.BAD_REQUEST);
		else if(USER_HASH == login.hashCode() && PASSWORD_HASH == password.hashCode()) {
			Map<ClaimsToken, String> claims = new HashMap<>();
			claims.put(ClaimsToken.SUBJECT, "Login Controller");
			claims.put(ClaimsToken.AUDIENCE, requestInfoService.getIp(request));
			claims.put(ClaimsToken.ISSUER, "Cliente unico");
			claims.put(ClaimsToken.EXPIRES_AT, "10");
			return new ResponseEntity<String>(tokenService.generate(claims), HttpStatus.OK);
		}else
			return new ResponseEntity<String>("Invalid login", HttpStatus.UNAUTHORIZED);
		
	}
	
	
}
