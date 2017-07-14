/*
 * Copyright 2017 DV Bern AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * limitations under the License.
 */

package ch.dvbern.lib.beanvalidation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import ch.dvbern.lib.beanvalidation.impl.SameValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Stellt sicher dass ein beliebiges Property denselben Wert hat wie ein anderes, z.B. für
 * die Eingabe eines Passwortes welches bestätigt werden muss.
 */
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(value = RUNTIME)
@Constraint(validatedBy = SameValidator.class)
@Documented
public @interface Same {

	String message() default "{ch.dvbern.beanvalidation.Verify.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return The property field
	 */
	String property();

	/**
	 * @return The sameAs field
	 */
	String sameAs();

	/**
	 * Defines several <code>@FieldMatch</code> annotations on the same element
	 *
	 * @see Same
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	@interface List {
		Same[] value();
	}

}
