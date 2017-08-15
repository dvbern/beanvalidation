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

import ch.dvbern.oss.datatypes.PostkontoNummer;
import ch.dvbern.oss.lib.beanvalidation.ValidPCNummer;
import ch.dvbern.oss.lib.beanvalidation.embeddables.PCNummer;

/**
 * Implementation des {@link javax.validation.ConstraintValidator}s für {@link ValidPCNummer}
 */
public class ValidPCNummerValidator extends AbstractUninitializableConstraintValidator<ValidPCNummer, PCNummer> {

	@Override
	public boolean isValid(final PCNummer value, final ConstraintValidatorContext context) {
		try {
			return value.getPcNummer() != null && new PostkontoNummer(value.getPcNummer()).isValid();
		} catch (Exception x) {
			return false;
		}
	}
}
