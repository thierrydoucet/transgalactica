package org.transgalactica.management.data.materiel.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.transaction.annotation.Transactional;
import org.transgalactica.management.data.materiel.TestConfig;
import org.transgalactica.management.data.materiel.bo.VaisseauSearchCriteria;
import org.transgalactica.management.data.materiel.bo.VaisseauSummary;
import org.transgalactica.management.data.materiel.dao.impl.QueryDSLVaisseauDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
@TransactionConfiguration
@Transactional(readOnly = true)
public class QueryDSLVaisseauDaoTest {

	@Autowired
	private BeanFactory beanFactory;

	@Autowired
	private EntityManager em;

	private QueryDSLVaisseauDao vaisseauDao;

	@Before
	public void createQueryDSLVaisseauDao() {
		vaisseauDao = BeanUtils.instantiateClass(QueryDSLVaisseauDao.class);
		ReflectionTestUtils.setField(vaisseauDao, "em", em);
	}

	@Test
	public void testFindByCriteria() {
		VaisseauSearchCriteria criteres = beanFactory.getBean(VaisseauSearchCriteria.class);
		criteres.setImmatriculation("Faucon Millenium");
		criteres.setIntergalactique(true);
		criteres.setModele("cargo YT-1300");

		List<VaisseauSummary> vaisseaux = vaisseauDao.findByCriteria(criteres);

		assertNotNull(vaisseaux);
		assertEquals(1, vaisseaux.size());

		assertEquals("Faucon Millenium", vaisseaux.get(0).getImmatriculationVaisseau());
		assertEquals("cargo YT-1300", vaisseaux.get(0).getModeleVaisseau());
		assertEquals("Alderaan", vaisseaux.get(0).getLocalisationHangar());
	}

	@Test
	public void testFindByCriteria_SansHangars() {
		VaisseauSearchCriteria criteres = beanFactory.getBean(VaisseauSearchCriteria.class);
		criteres.setImmatriculation("%");

		List<VaisseauSummary> vaisseaux = vaisseauDao.findByCriteria(criteres);

		assertNotNull(vaisseaux);
		assertEquals(6, vaisseaux.size());
	}
}