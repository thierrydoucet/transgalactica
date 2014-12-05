package org.transgalactica.web.hangar.model.impl;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class HangarCommand implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 30)
	private String localisation;

	@NotNull
	@Min(1)
	private Integer nombreEmplacements;

	protected HangarCommand() {
	}

	public String getLocalisation() {
		return localisation;
	}

	public void setLocalisation(String localisation) {
		this.localisation = localisation;
	}

	public Integer getNombreEmplacements() {
		return nombreEmplacements;
	}

	public void setNombreEmplacements(Integer nombreEmplacements) {
		this.nombreEmplacements = nombreEmplacements;
	}
}
