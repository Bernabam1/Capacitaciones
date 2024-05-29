package com.berna.app.aop.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GreetingPointcuts {
	@Pointcut("execution(* com.berna.app.aop.services.*.*(..))")
	public void greetingLoggerPointCut(){} // Esto lo puedo llevar a otra clase
}
