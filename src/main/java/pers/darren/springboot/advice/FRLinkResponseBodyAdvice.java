package pers.darren.springboot.advice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 修改获取FineReport链接接口的返回参数，在链接后追加数字签名
 *
 * @CreatedBy Darren Luo
 * @CreatedTime Jan 15, 2020 4:04:21 PM
 */
@RestControllerAdvice
public final class FRLinkResponseBodyAdvice implements ResponseBodyAdvice<Object> {

	private static final List<String> fineReportURIList;

	static {
		fineReportURIList = new ArrayList<>();
		fineReportURIList.add("/example/hello");
		fineReportURIList.add("/example/aboutMe");
	}

	@Override
	public boolean supports(final MethodParameter returnType,
			final Class<? extends HttpMessageConverter<?>> converterType) {
		return true;
	}

	@Override
	public Object beforeBodyWrite(Object body, final MethodParameter returnType, final MediaType selectedContentType,
			final Class<? extends HttpMessageConverter<?>> selectedConverterType, final ServerHttpRequest request,
			final ServerHttpResponse response) {
		final ServletServerHttpRequest servletServerHttpRequest = (ServletServerHttpRequest) request;
		final HttpServletRequest httpServletRequest = servletServerHttpRequest.getServletRequest();
		// 若当前请求的接口是返回FineReport链接的接口，则在链接后追加数字签名
		final String requestURI = httpServletRequest.getRequestURI();
		if (fineReportURIList.contains(requestURI) && body instanceof String) {
			body = body + " Is Me.";
		}
		return body;
	}
}