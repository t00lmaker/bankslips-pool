package com.luanpontes.bankslipspool.service.impl;

import static com.luanpontes.bankslipspool.auth.ClaimsToken.AUDIENCE;
import static com.luanpontes.bankslipspool.auth.ClaimsToken.EXPIRES_AT;
import static com.luanpontes.bankslipspool.auth.ClaimsToken.ISSUER;
import static com.luanpontes.bankslipspool.auth.ClaimsToken.SUBJECT;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Verification;
import com.luanpontes.bankslipspool.auth.ClaimsToken;
import com.luanpontes.bankslipspool.service.TokenService;

/**
 * Geração de tokens de autenticação utilizando 
 * o padrao jwt.
 * 
 * @author Luan Pontes
 *
 */
@Service
public class JwtTokenService implements TokenService {
	
	
	private Algorithm algorithm; 
	
	{
		try {
			algorithm = Algorithm.HMAC256(keySalt());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String keySalt() {
		return "Sera1a453ds2rDXCVFddfdg";
	}

	@Override
	public String generate(Map<ClaimsToken, String> claims) {
		return JWT.create()
				.withIssuer(claims.get(ISSUER))
				.withAudience(claims.get(AUDIENCE))
				.withExpiresAt(createExpirestAt(claims.get(EXPIRES_AT)))
				.withSubject(claims.get(SUBJECT))
				.sign(algorithm);
	}

	@Override
	public boolean isValid(String token, String audience) {
		try {
			Verification verification = JWT.require(algorithm);
			verification.withAudience(audience);
			verification.build().verify(token);
			return true;
		} catch (JWTVerificationException e){
			e.printStackTrace();
			return false;
		}
	}
	
	private Date createExpirestAt(String time) {
		long minutes = Long.valueOf(time);
		LocalDateTime now = LocalDateTime.now();
		LocalDateTime nowPlus10m = now.plusMinutes(minutes);
		return Date.from(nowPlus10m.atZone(ZoneId.systemDefault()).toInstant());
	}
	
	
}
