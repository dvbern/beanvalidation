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

import ch.dvbern.lib.beanvalidation.impl.CompareToValidator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Compares a {@link Comparable} field with another property.
 *
 * The property to be compared must be specified by {@link #property()}. The validation always passes if this is
 * <code>null</code>. The second property ({@link #comparedTo()}) defines the property which the  first one is compared to.
 * If the second property is <code>null</code>, the validation will always fail.
 *
 * {@link #comparison()} defines how the two properties will be compared together. Default is {@link Comparison#EQUAL}.
 */
@Documented
@Constraint(validatedBy = CompareToValidator.class)
@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface CompareTo {
	String message() default "{ch.dvbern.beanvalidation.CompareTo.message}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	/**
	 * @return The property field
	 */
	String property();

	/**
	 * @return The sameAs field
	 */
	String comparedTo();

	Comparison comparison() default Comparison.EQUAL;

	public enum Comparison {
		EQUAL, LESS_OR_EQUAL, LESS, GREATER_OR_EQUAL, GREATER;
	}

	/**
	 * Defines several {@code @LessOrEqualTo} annotations on the same element.
	 */
	@Target({ TYPE, ANNOTATION_TYPE })
	@Retention(RUNTIME)
	@Documented
	public @interface List {
		CompareTo[] value();
	}
}