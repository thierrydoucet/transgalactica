package org.transgalactica.fwk.remote.exception;

import java.lang.reflect.AccessibleObject;

import org.aopalliance.intercept.Joinpoint;

public class DummyJoinpoint implements Joinpoint {

	private boolean invoked = false;
	
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
		return null;
	}

	public boolean isInvoked() {
		return invoked;
	}
}
