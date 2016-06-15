package org.axp.proxy.dynamic;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation that specifies the minimum access level required to execute a specific method.
 */
@Target( ElementType.METHOD )
@Retention( RetentionPolicy.RUNTIME )
public @interface AccessCheck {
	int value();
}
