package org.transgalactica.fwk.test.util;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DummyMethodInterceptor implements MethodInterceptor {

	public DummyMethodInterceptor() {
	}

	@Override
	public Object invoke(MethodInvocation m) throws Throwable {
		Object r = m.proceed();
		return r;
	}
}
