package pers.darren.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;

@Configuration
public class WebConfiguration {

	private static final Logger logger = LoggerFactory.getLogger(WebConfiguration.class);

	@Bean
	public HttpMessageConverters setHttpMessageConverters() {
		final HttpMessageConverters converters = new HttpMessageConverters(new ByteArrayHttpMessageConverter());
		for (final HttpMessageConverter<?> httpMessageConverter : converters.getConverters()) {
			logger.info(httpMessageConverter.toString());
		}
		return converters;
	}
}