package org.transgalactica.web.hangar.model.impl;

import static lombok.AccessLevel.PROTECTED;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.hibernate.validator.constraints.NotBlank;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
@Getter
@Setter
@NoArgsConstructor(access = PROTECTED)
public class HangarCommand implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 30)
	private String localisation;

	@NotNull
	@Min(1)
	private Integer nombreEmplacements;
}
