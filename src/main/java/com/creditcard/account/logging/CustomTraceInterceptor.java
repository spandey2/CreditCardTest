package com.creditcard.account.logging;

import org.apache.commons.logging.Log;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * This class uses the AspectJ style to create logs of each entry/exit of a method.
 */
@Component
public class CustomTraceInterceptor extends CustomizableTraceInterceptor {

	/**
	 * The default message used for writing method entry messages.
	 * "Entering method '" + PLACEHOLDER_METHOD_NAME + "' of class [" + PLACEHOLDER_TARGET_CLASS_NAME + "]";
	 * The default message used for writing method exit messages.
	 * "Exiting method '" + PLACEHOLDER_METHOD_NAME + "' of class [" + PLACEHOLDER_TARGET_CLASS_NAME + "]";
	 * The default message used for writing exception messages.
	 * "Exception thrown in method '" + PLACEHOLDER_METHOD_NAME + "' of class [" + PLACEHOLDER_TARGET_CLASS_NAME + "]";
	 */
	@Bean
	public CustomizableTraceInterceptor customizableTraceInterceptor() {
		final CustomizableTraceInterceptor cti = new CustomTraceInterceptor();
		cti.setLoggerName("account-service-aop");
		cti.setEnterMessage("Entering method: '" + PLACEHOLDER_METHOD_NAME + "(" + PLACEHOLDER_ARGUMENTS + ")' of class [" + PLACEHOLDER_TARGET_CLASS_NAME + "]");
		cti.setExitMessage("Exiting method: '" + PLACEHOLDER_METHOD_NAME + "' of class [" + PLACEHOLDER_TARGET_CLASS_NAME + "] took " + PLACEHOLDER_INVOCATION_TIME + "ms.");
		cti.setExceptionMessage("Exception thrown in method: '" + PLACEHOLDER_METHOD_NAME + "' of class [" + PLACEHOLDER_TARGET_CLASS_NAME + "] gave: " + PLACEHOLDER_EXCEPTION);
		return cti;
	}

	/**
	 * This method is where to specify what packages should be under the Trace Interceptor.
	 *
	 * @return advisor that mixes pointcut and interceptor
	 */
	@Bean
	public Advisor traceAdvisor() {
		final AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();

		//pointcut.setExpression("execution(* com.creditcard.account.controller..*.*(..))");
		pointcut.setExpression("execution(* com.creditcard.account.controller..*.*(..)) || execution(* com.creditcard.account.domain..*.*(..)) || execution(* com.creditcard.account.repository..*.*(..)) || execution(* com.creditcard.account.service..*.*(..))");
		return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
	}

	/*
	 * Write in the console all the Java classes that has been called.
	 * @see org.springframework.aop.interceptor.CustomizableTraceInterceptor#writeToLog(org.apache.commons.logging.Log, java.lang.String, java.lang.Throwable).
	 */
	@Override
	protected void writeToLog(final Log logger, final String message, final Throwable ex) {
		if (ex != null) {
			super.defaultLogger.info(message, ex);
		} else {
			super.defaultLogger.info(message);
		}
	}
}
