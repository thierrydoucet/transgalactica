package org.transgalactica.management.rest.logistics.data.impl;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import org.transgalactica.fwk.domain.stereotype.DataBean;
import org.transgalactica.management.rest.logistics.data.HangarDetailDto;

@DataBean("org.transgalactica.management.rest.logistics.data.HangarDetailDto")
@XmlRootElement(name = "hangarDetail")
@XmlType(name = "HangarDetailDto")
public class JaxbHangarDetailDto extends JaxbHangarDto implements HangarDetailDto {

	private static final long serialVersionUID = 1L;

	private Set<HangarDetailDto.VaisseauDto> vaisseaux;

	@DataBean
	@XmlType(name = "HangarVaisseau")
	public static class BasicVaisseauDto implements HangarDetailDto.VaisseauDto {

		private static final long serialVersionUID = 1L;

		private String immatriculation;

		private String modele;

		private short nombreDePassagers;

		private long capaciteDeFret;

		private int vitesse;

		private int autonomie;

		protected BasicVaisseauDto() {
		}

		/*
		 * Accesseurs
		 */

		@Override
		public String getImmatriculation() {
			return immatriculation;
		}

		public void setImmatriculation(String immatriculation) {
			this.immatriculation = immatriculation;
		}

		@Override
		public String getModele() {
			return modele;
		}

		public void setModele(String modele) {
			this.modele = modele;
		}

		@Override
		public short getNombreDePassagers() {
			return nombreDePassagers;
		}

		public void setNombreDePassagers(short nombreDePassagers) {
			this.nombreDePassagers = nombreDePassagers;
		}

		@Override
		public long getCapaciteDeFret() {
			return capaciteDeFret;
		}

		public void setCapaciteDeFret(long capaciteDeFret) {
			this.capaciteDeFret = capaciteDeFret;
		}

		@Override
		public int getVitesse() {
			return vitesse;
		}

		public void setVitesse(int vitesse) {
			this.vitesse = vitesse;
		}

		@Override
		public int getAutonomie() {
			return autonomie;
		}

		public void setAutonomie(int autonomie) {
			this.autonomie = autonomie;
		}
	}

	protected JaxbHangarDetailDto() {
	}

	/*
	 * Accesseurs
	 */

	@Override
	@XmlElement(type = JaxbHangarDetailDto.BasicVaisseauDto.class)
	public Set<HangarDetailDto.VaisseauDto> getVaisseaux() {
		return vaisseaux;
	}

	public void setVaisseaux(Set<HangarDetailDto.VaisseauDto> vaisseaux) {
		this.vaisseaux = vaisseaux;
	}
}
