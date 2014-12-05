package org.transgalactica.management.data.people.bo.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import org.transgalactica.fwk.domain.bean.BusinessIdentifier;
import org.transgalactica.management.data.materiel.bo.VaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.people.bo.EmployeEntity;
import org.transgalactica.management.data.referentiel.bo.EmployeType;

@Data
@EqualsAndHashCode(of = "matricule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "EMPLOYES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_employe")
public abstract class AbstractJpaEmployeEntity implements EmployeEntity {

	private static final long serialVersionUID = 1L;

	@BusinessIdentifier
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long matricule;

	@Column(name = "nom")
	private String nom;

	@Past
	@Column(name = "date_embauche")
	@Temporal(TemporalType.DATE)
	private Date dateEmbauche;

	@Enumerated(EnumType.STRING)
	@Column(name = "type_employe", nullable = false, insertable = false, updatable = false)
	private EmployeType type;

	@ManyToMany(targetEntity = JpaVaisseauEntity.class, cascade = CascadeType.ALL)
	@JoinTable(name = "AFFECTATION_EMPLOYES_VAISSEAUX", joinColumns = @JoinColumn(name = "id_employe", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_vaisseau", referencedColumnName = "id"))
	private Set<VaisseauEntity> vaisseaux = new HashSet<>();

	/*
	 * Methodes deleguées
	 */

	@Override
	public boolean addVaisseau(VaisseauEntity vaisseau) {
		return vaisseaux.add(vaisseau);
	}

	@Override
	public boolean removeVaisseau(VaisseauEntity vaisseau) {
		return vaisseaux.remove(vaisseau);
	}
}
