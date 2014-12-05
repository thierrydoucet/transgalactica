package org.transgalactica.fwk.domain.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.stereotype.Component;

/**
 * Permet d'annoter une classe comme étant un composant "Rule".
 * 
 * @author Thierry
 * @author Fabien
 * @author Franck
 */
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Rule {

	String value() default "";
}
