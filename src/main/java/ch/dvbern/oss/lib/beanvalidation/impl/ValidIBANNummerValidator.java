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

import javax.validation.ConstraintValidatorContext;

import ch.dvbern.oss.lib.beanvalidation.ValidIBANNummer;
import ch.dvbern.oss.lib.beanvalidation.embeddables.IBAN;

/**
 * Implementation des {@link javax.validation.ConstraintValidator}s für {@link ValidIBANNummer}
 */
public class ValidIBANNummerValidator extends AbstractUninitializableConstraintValidator<ValidIBANNummer, IBAN> {

	@Override
	public boolean isValid(final IBAN value, final ConstraintValidatorContext context) {
		try {
			return value.getIban() != null && new ch.dvbern.oss.datatypes.IBAN(value.getIban()).isValid();
		} catch (IllegalArgumentException x) {
			return false;
		}
	}
}
