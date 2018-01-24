package com.congruent.compulaw.web.editor;

import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

public class WebMvcContextConfiguration extends WebMvcConfigurationSupport {
	@Override
	@Bean
	public RequestMappingHandlerAdapter requestMappingHandlerAdapter() {
		RequestMappingHandlerAdapter adapter = super.requestMappingHandlerAdapter();
		ConfigurableWebBindingInitializer webBindingInitializer;
		webBindingInitializer = (ConfigurableWebBindingInitializer) adapter
				.getWebBindingInitializer();
		webBindingInitializer.setDirectFieldAccess(true);
		// Do other re-configuration
		return adapter;
	}

}
