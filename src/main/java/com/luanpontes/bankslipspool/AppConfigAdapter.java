package com.luanpontes.bankslipspool;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.luanpontes.bankslipspool.auth.TokenValidatorInterceptor;


@Configuration
public class AppConfigAdapter extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new TokenValidatorInterceptor())
			.addPathPatterns( "/**" )
			.excludePathPatterns("/rest/login");
	}

}

