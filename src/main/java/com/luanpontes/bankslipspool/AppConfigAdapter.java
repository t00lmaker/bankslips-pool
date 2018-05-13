package com.luanpontes.bankslipspool;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.luanpontes.bankslipspool.auth.TokenValidatorInterceptor;


@Configuration
public class AppConfigAdapter extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		super.addInterceptors(registry);
		registry.addInterceptor(tokenValidatorInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/rest/login");
	}
	
	@Bean
	public TokenValidatorInterceptor tokenValidatorInterceptor() {
	    return new TokenValidatorInterceptor();
	}

}

