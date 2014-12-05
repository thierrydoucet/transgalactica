package org.transgalactica.management.data.materiel.bo;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.transgalactica.fwk.test.domain.bo.AbstractBoGenericTest;
import org.transgalactica.management.data.materiel.bo.impl.JpaHangarEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauEntity;
import org.transgalactica.management.data.materiel.bo.impl.JpaVaisseauIntergalactiqueEntity;

@RunWith(Parameterized.class)
public class EntityTest extends AbstractBoGenericTest {

	public EntityTest(Class<Object> clazzOfBo, BusinessIdentifierSetterVisitor<Object> visitor) {
		super(clazzOfBo, visitor);
	}

	@Parameters
	public static Collection<Object[]> getBeans() {
		Collection<Object[]> beans = new ArrayList<Object[]>();
		beans.add(new Object[] { JpaHangarEntity.class, new BusinessIdentifierSetterVisitor<JpaHangarEntity>() {
			public void setBusinessIdentifierFirstValue(JpaHangarEntity bo) {
				bo.setNumero(1L);
			}

			public void setBusinessIdentifierSecondValue(JpaHangarEntity bo) {
				bo.setNumero(2L);
			}
		} });
		BusinessIdentifierSetterVisitor<JpaVaisseauEntity> immatriculationVisitor = new BusinessIdentifierSetterVisitor<JpaVaisseauEntity>() {
			public void setBusinessIdentifierFirstValue(JpaVaisseauEntity bo) {
				bo.setImmatriculation("immatriculation 1");
			}

			public void setBusinessIdentifierSecondValue(JpaVaisseauEntity bo) {
				bo.setImmatriculation("immatriculation 2");
			}
		};
		beans.add(new Object[] { JpaVaisseauEntity.class, immatriculationVisitor });
		beans.add(new Object[] { JpaVaisseauIntergalactiqueEntity.class, immatriculationVisitor });
		return beans;
	}
}
