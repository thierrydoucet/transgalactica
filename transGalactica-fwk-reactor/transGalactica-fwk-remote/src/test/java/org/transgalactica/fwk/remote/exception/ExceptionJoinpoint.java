package org.transgalactica.fwk.remote.exception;

import java.lang.reflect.AccessibleObject;

import org.aopalliance.intercept.Joinpoint;

public class ExceptionJoinpoint implements Joinpoint {

	private boolean invoked = false;

	private Throwable toThrowOnProceed = null;

	public ExceptionJoinpoint() {
	}

	public ExceptionJoinpoint(Throwable toThrowOnProceed) {
		this.toThrowOnProceed = toThrowOnProceed;
	}

	@Override
	public AccessibleObject getStaticPart() {
		return null;
	}

	@Override
	public Object getThis() {
		return null;
	}

	@Override
	public Object proceed() throws Throwable {
		this.invoked = true;
		if (this.toThrowOnProceed != null) {
			throw this.toThrowOnProceed;
		}
		return null;
	}

	public boolean isInvoked() {
		return invoked;
	}
}
