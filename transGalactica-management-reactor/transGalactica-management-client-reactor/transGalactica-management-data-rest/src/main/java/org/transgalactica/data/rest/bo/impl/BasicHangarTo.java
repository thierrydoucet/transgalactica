package org.transgalactica.data.rest.bo.impl;

import java.util.List;

import org.transgalactica.data.rest.bo.HangarTo;
import org.transgalactica.fwk.domain.stereotype.DataBean;

@DataBean
public class BasicHangarTo extends BasicHangarSummaryTo implements HangarTo {

	private static final long serialVersionUID = 1L;

	private List<HangarTo.VaisseauTo> vaisseaux;

	@DataBean
	public static class BasicVaisseauTo implements HangarTo.VaisseauTo {

		private static final long serialVersionUID = 1L;

		private String immatriculation;

		private String modele;

		private short nombreDePassagers;

		private long capaciteDeFret;

		private int vitesse;

		private int autonomie;

		protected BasicVaisseauTo() {
		}

		/*
		 * Accesseurs
		 */

		/**
		 * @see org.transgalactica.data.rest.bo.impl.BasicVaisseauTo#getImmatriculation()
		 */
		@Override
		public String getImmatriculation() {
			return immatriculation;
		}

		public void setImmatriculation(String immatriculation) {
			this.immatriculation = immatriculation;
		}

		/**
		 * @see org.transgalactica.data.rest.bo.impl.BasicVaisseauTo#getModele()
		 */
		@Override
		public String getModele() {
			return modele;
		}

		public void setModele(String modele) {
			this.modele = modele;
		}

		/**
		 * @see org.transgalactica.data.rest.bo.impl.BasicVaisseauTo#getNombreDePassagers()
		 */
		@Override
		public short getNombreDePassagers() {
			return nombreDePassagers;
		}

		public void setNombreDePassagers(short nombreDePassagers) {
			this.nombreDePassagers = nombreDePassagers;
		}

		/**
		 * @see org.transgalactica.data.rest.bo.impl.BasicVaisseauTo#getCapaciteDeFret()
		 */
		@Override
		public long getCapaciteDeFret() {
			return capaciteDeFret;
		}

		public void setCapaciteDeFret(long capaciteDeFret) {
			this.capaciteDeFret = capaciteDeFret;
		}

		/**
		 * @see org.transgalactica.data.rest.bo.impl.BasicVaisseauTo#getVitesse()
		 */
		@Override
		public int getVitesse() {
			return vitesse;
		}

		public void setVitesse(int vitesse) {
			this.vitesse = vitesse;
		}

		/**
		 * @see org.transgalactica.data.rest.bo.impl.BasicVaisseauTo#getAutonomie()
		 */
		@Override
		public int getAutonomie() {
			return autonomie;
		}

		public void setAutonomie(int autonomie) {
			this.autonomie = autonomie;
		}
	}

	protected BasicHangarTo() {
	}

	/*
	 * Accesseurs
	 */

	/**
	 * @see org.transgalactica.data.rest.bo.HangarTo#getVaisseaux()
	 */
	@Override
	public List<BasicHangarTo.VaisseauTo> getVaisseaux() {
		return vaisseaux;
	}

	public void setVaisseaux(List<BasicHangarTo.VaisseauTo> vaisseaux) {
		this.vaisseaux = vaisseaux;
	}
}
