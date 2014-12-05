package org.transgalactica.batch.salaire.rule.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.joda.time.Interval;
import org.joda.time.ReadableInterval;
import org.springframework.util.Assert;
import org.transgalactica.batch.salaire.rule.FicheSalaireRule;
import org.transgalactica.fwk.domain.stereotype.Rule;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.people.bo.MecanicienEntity;
import org.transgalactica.management.data.people.bo.PiloteEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

@Rule
public class BasicFicheSalaireRule implements FicheSalaireRule {

	private static final BigDecimal PILOTE_BASE_SALAIRE = new BigDecimal(8000);

	private static final BigDecimal MECANICIEN_BASE_SALAIRE = new BigDecimal(10000);

	private static final BigDecimal FACTEUR_ANCIENNETE = new BigDecimal(100);

	private static final BigDecimal FACTEUR_HEURE_VOL = BigDecimal.ONE;

	private static final BigDecimal FACTEUR_SPECIALITE = new BigDecimal(1000);

	protected BasicFicheSalaireRule() {
	}

	@Override
	public BigDecimal calculerSalaireBase(EmployeEntity employe) {
		if (employe.getType().equals(EmployeType.PILOTE)) {
			return PILOTE_BASE_SALAIRE;
		}
		if (employe.getType().equals(EmployeType.MECANICIEN)) {
			return MECANICIEN_BASE_SALAIRE;
		}
		throw new IllegalStateException("Unmanaged employe type;");
	}

	@Override
	public BigDecimal calculerPrimeAnciennete(EmployeEntity employe, Date dateCalcul) {
		Date dateEmbauche = employe.getDateEmbauche();
		Assert.isTrue(dateCalcul.after(dateEmbauche));
		ReadableInterval interval = new Interval(dateEmbauche.getTime(), dateCalcul.getTime());
		BigDecimal nbAnneesAnciennete = new BigDecimal(interval.toPeriod().getYears());
		return nbAnneesAnciennete.multiply(FACTEUR_ANCIENNETE);
	}

	@Override
	public BigDecimal calculerPrimeExperience(EmployeEntity employe) {
		if (employe.getType().equals(EmployeType.PILOTE)) {
			BigDecimal nbHeureDeVol = new BigDecimal(((PiloteEntity) employe).getNombreHeuresVol());
			return nbHeureDeVol.multiply(FACTEUR_HEURE_VOL);
		}
		if (employe.getType().equals(EmployeType.MECANICIEN)) {
			BigDecimal nbSpecialite = new BigDecimal(((MecanicienEntity) employe).getSpecialites().size());
			return nbSpecialite.multiply(FACTEUR_SPECIALITE);
		}
		throw new IllegalStateException("Unmanaged employe type;");
	}
}
