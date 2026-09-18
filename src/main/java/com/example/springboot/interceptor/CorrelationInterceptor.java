package com.example.springboot.interceptor;

import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CorrelationInterceptor implements HandlerInterceptor {

	public static final String CORRELATION_ID = "correlationId";
	public static final String CORRELATION_HEADER = "X-Correlation-ID";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
		String correlationId = UUID.randomUUID().toString();
		MDC.put(CORRELATION_ID, correlationId);
		response.setHeader(CORRELATION_HEADER, correlationId);
		return true;
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception exception) {
		MDC.remove(CORRELATION_ID);
	}
}