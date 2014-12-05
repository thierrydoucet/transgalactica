package org.transgalactica.fwk.test.domaine.bo;

import java.io.Serializable;

public interface IDummyBo extends Serializable {

	Long getBusinessIdentifier();

	void setBusinessIdentifier(Long businessIdentifier);

	String getDummyAttribut();

	void setDummyAttribut(String dummyAttribut);
}
