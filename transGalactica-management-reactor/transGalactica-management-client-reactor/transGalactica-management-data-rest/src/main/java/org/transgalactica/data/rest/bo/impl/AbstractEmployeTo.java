package org.transgalactica.data.rest.bo.impl;

import java.util.List;

import org.transgalactica.data.rest.bo.EmployeTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

public class AbstractEmployeTo extends BasicEmployeSummaryTo implements EmployeTo {

	private static final long serialVersionUID = 1L;

	private List<VaisseauTo> vaisseaux;

	protected AbstractEmployeTo() {
	}

	@DataBean
	public static class BasicVaisseauTo implements EmployeTo.VaisseauTo {

		private static final long serialVersionUID = 1L;

		private String immatriculation;

		private String modele;

		private short nombreDePassagers;

		private long capaciteDeFret;

		private int vitesse;

		private int autonomie;

		protected BasicVaisseauTo() {
		}

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

	@Override
	public List<VaisseauTo> getVaisseaux() {
		return vaisseaux;
	}

	public void setVaisseaux(List<VaisseauTo> vaisseaux) {
		this.vaisseaux = vaisseaux;
	}
}
