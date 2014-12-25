package org.transgalactica.fwk.domain.bean;

import java.io.Serializable;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * Utilitaire de manipulation des attributs ou méthodes annotées avec
 * {@link BusinessIdentifier}.
 * 
 * @author Thierry
 */
public abstract class BusinessIdentifierUtils {

	protected BusinessIdentifierUtils() {
	}

	/**
	 * Recherche les valeurs des champs ou méthodes annotées
	 * {@link BusinessIdentifier}.
	 * 
	 * @return Une {@link SortedMap} contenant les valeurs sous forme de couple
	 * :
	 * <ul>
	 * <li>clé : {@link BusinessIdentifier#name()} ou si vide, le nom du champ
	 * ou de la méthode;
	 * <li>valeur : la valeur du champs, ou retournée par l'appel de la méthode.
	 * <ul>
	 */
	public static SortedMap<String, Serializable> getBusinessIdentifiersValues(final Serializable bo) {
		if (bo == null) {
			return null;
		}
		final SortedMap<String, Serializable> values = new TreeMap<String, Serializable>();

		// process fields
		ReflectionUtils.doWithFields(bo.getClass(), (field) -> {
			ReflectionUtils.makeAccessible(field);
			Serializable value = (Serializable) ReflectionUtils.getField(field, bo);
			BusinessIdentifier b = field.getAnnotation(BusinessIdentifier.class);
			values.put(StringUtils.isNotEmpty(b.name()) ? b.name() : field.getName(), value);
		}, new BusinessIdentifierFieldFilter());

		// process methods
		ReflectionUtils.doWithMethods(bo.getClass(), (method) -> {
			Assert.isTrue(!method.getReturnType().equals(Void.TYPE), String.format("%s has no returntype", method));
			Assert.isTrue(method.getParameterTypes().length == 0, String.format("%s has parameters", method));
			ReflectionUtils.makeAccessible(method);
			Serializable value = (Serializable) ReflectionUtils.invokeMethod(method, bo);
			BusinessIdentifier b = method.getAnnotation(BusinessIdentifier.class);
			values.put(StringUtils.isNotEmpty(b.name()) ? b.name() : method.getName(), value);
		}, new BusinessIdentifierMethodFilter());

		return values;
	}
}
