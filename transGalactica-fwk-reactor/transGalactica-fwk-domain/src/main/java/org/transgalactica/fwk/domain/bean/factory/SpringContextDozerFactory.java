package org.transgalactica.fwk.domain.bean.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Factory Dozer s'appuyant sur le context spring pour la creation des beans.
 * 
 * @author Thierry
 */
public class SpringContextDozerFactory implements org.dozer.BeanFactory, ApplicationContextAware {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private BeanFactory beanFactory;

	public SpringContextDozerFactory() {
	}

	public SpringContextDozerFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

	/**
	 * @see org.dozer.BeanFactory#createBean(java.lang.Object, java.lang.Class,
	 * java.lang.String)
	 */
	@Override
	public Object createBean(Object source, Class<?> sourceClass, String targetBeanId) {
		if (beanFactory.containsBean(targetBeanId)) {
			logger.debug("Getting bean with Id: {}", targetBeanId);
			return beanFactory.getBean(targetBeanId);
		}
		try {
			logger.debug("No bean with Id: {}, getting it by class: {}", targetBeanId, targetBeanId);
			Class<?> clazz = Class.forName(targetBeanId);
			return beanFactory.getBean(clazz);
		}
		catch (ClassNotFoundException e) {
			throw new IllegalArgumentException("Can't find bean neither by id: " + targetBeanId + ", or by class: "
					+ targetBeanId, e);
		}
	}

	/*
	 * IOC
	 */

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.beanFactory = applicationContext;
	}
}
