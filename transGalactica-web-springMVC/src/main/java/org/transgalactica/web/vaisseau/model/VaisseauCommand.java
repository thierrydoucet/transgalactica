package org.transgalactica.web.vaisseau.model;

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
public class VaisseauCommand implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	@Size(max = 30)
	private String immatriculation;

	@NotBlank
	@Size(max = 30)
	private String modele;

	@NotNull
	@Min(0)
	private short nombreDePassagers;

	@NotNull
	@Min(0)
	private long capaciteDeFret;

	@NotNull
	@Min(0)
	private int vitesse;

	@NotNull
	@Min(0)
	private int autonomie;

	@Min(0)
	private Short multiplicateurHyperdrive;
}
