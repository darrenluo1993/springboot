package pers.darren.springboot.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvc配置器
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Nov 24, 2019 1:41:22 PM
 */
@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer {

	private static final Logger logger = LoggerFactory.getLogger(WebMvcConfigurerImpl.class);

	/**
	 * 扩展HttpMessageConverter列表，调整ByteArrayHttpMessageConverter优先级
	 */
	@Override
	public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
		converters.add(0, new ByteArrayHttpMessageConverter());
		for (final HttpMessageConverter<?> httpMessageConverter : converters) {
			logger.info(httpMessageConverter.toString());
		}
	}
}