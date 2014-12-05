package org.transgalactica.fwk.domain.bean;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Permet d'annoter un champ ou une méthode comme faisant partie de la clé métier de l'objet métier.
 * 
 * @author Thierry
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Documented
public @interface BusinessIdentifier {

	String name() default "";
}
