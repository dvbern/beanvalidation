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

package ch.dvbern.lib.beanvalidation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import ch.dvbern.lib.beanvalidation.PasswortComplexity;

/**
 * Custom-Validator für ein Passwort_. Dieser Validator ersetzt einen entsprechenden Constraint mit
 * {@link javax.validation.constraints.Pattern}, da es geringfügige Unterschiede gibt in der Auswertung von
 * Regular Expressions und Java.
 */
public class PasswortComplexityValidator implements ConstraintValidator<PasswortComplexity, String> {

	public static final int MIN_COUNT_NUMBERS = 1;
	public static final int MIN_COUNT_SONDERZEICHEN = 1;

	public void initialize(final PasswortComplexity constraintAnnotation) {
		// nothing to do.
	}

	public boolean isValid(final String value, final ConstraintValidatorContext context) {
		final char[] chars = value.toCharArray();
		int countZahlen = 0;
		int countSonderzeichen = 0;
		for (final char aChar : chars) {
			if (Character.isDigit(aChar)) {
				countZahlen++;
			}
			if (!Character.isLetterOrDigit(aChar)) {
				countSonderzeichen++;
			}
		}
		return countZahlen >= MIN_COUNT_NUMBERS && countSonderzeichen >= MIN_COUNT_SONDERZEICHEN;
	}
}
