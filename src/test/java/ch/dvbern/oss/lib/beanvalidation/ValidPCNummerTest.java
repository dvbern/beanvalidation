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

package ch.dvbern.oss.lib.beanvalidation;

import javax.annotation.Nullable;
import javax.validation.Valid;

import ch.dvbern.oss.lib.beanvalidation.embeddables.PCNummer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertViolated;

/**
 * Testklasse für {@link ValidPCNummer}
 */
public class ValidPCNummerTest {

	@ParameterizedTest
	@NullSource
	@ValueSource(longs = { 301045969L, 7569227076983L })
	public void testInvalidSozialversNr(@Nullable Long value) {
		Bean bean = new Bean();
		bean.setPcNummer(new PCNummer(value));
		assertViolated(ValidPCNummer.class, bean, "pcNummer");
	}

	@ParameterizedTest
	@ValueSource(longs = 301045968L)
	public void testValidSozialversNr(@Nullable Long value) {
		Bean bean = new Bean();
		bean.setPcNummer(new PCNummer(value));
		assertNotViolated(ValidPCNummer.class, bean, "pcNummer");
	}

	public static class Bean {

		@Valid
		private PCNummer pcNummer;

		public PCNummer getPcNummer() {
			return pcNummer;
		}

		public void setPcNummer(PCNummer pcNummer) {
			this.pcNummer = pcNummer;
		}
	}
}
