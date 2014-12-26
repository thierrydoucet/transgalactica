package org.transgalactica.management.ws.logistics.data;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
@Setter
@EqualsAndHashCode(of = "numero")
@NoArgsConstructor
public class BasicHangarDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long numero;

	private String localisation;

	private int nombreEmplacements;

	@XmlElement(name = "numero", nillable = false, required = true)
	public Long getNumero() {
		return numero;
	}

	@XmlElement(name = "localisation", nillable = false)
	public String getLocalisation() {
		return localisation;
	}

	@XmlElement(name = "nombreEmplacements")
	public int getNombreEmplacements() {
		return nombreEmplacements;
	}
}
