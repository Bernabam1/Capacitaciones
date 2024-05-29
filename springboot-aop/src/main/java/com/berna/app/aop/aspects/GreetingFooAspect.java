package com.berna.app.aop.aspects;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(1)
@Component
@Aspect
public class GreetingFooAspect {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Before("execution(* com.berna.app.aop.services.GreetingService.*(..))")
	public void LoggerBefore(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().getName();
		String args = Arrays.toString(joinPoint.getArgs());
		logger.info("Before (FooAspect: método "+method+" con los parámetros "+args);
	}
}
