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

package ch.dvbern.oss.lib.beanvalidation.impl;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidatorContext;

import ch.dvbern.oss.lib.beanvalidation.ValidEmail;

/**
 * Custom-Validator fuer E-Mail Adressen
 */
public class EmailValidator extends AbstractInitializedConstraintValidator<ValidEmail, String> {

	private static String REGEX_EMAIL = "\\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,12}\\b";
	private static Pattern PATTERN = Pattern.compile(REGEX_EMAIL, Pattern.CASE_INSENSITIVE);

	public boolean isValid(final String mail, final ConstraintValidatorContext context) {
		if (mail != null) {
			final Matcher m = PATTERN.matcher(mail);
			return m.matches();
		}
		// Null ist auch okay (bzw. wird gegebenenfalls mit @NotNull validiert)
		return true;
	}
}
