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

import ch.dvbern.oss.lib.beanvalidation.embeddables.Sozialversicherungsnummer;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertViolated;

/**
 * Testklasse für {@link ValidSozialversicherungsnummer}
 */
public class ValidSozialversicherungsnummerTest {

	@SuppressWarnings("JUnitTestMethodWithNoAssertions")
	@ParameterizedTest
	@NullSource
	@ValueSource(longs = { 7569217076985L, 7569217076985L })
	public void testValidSozialversNr(@Nullable Long avhNr) {
		Bean bean = new Bean();
		if (avhNr != null) {
			bean.setAhvNr(new Sozialversicherungsnummer(avhNr));
		}

		assertNotViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");
	}

	@SuppressWarnings("JUnitTestMethodWithNoAssertions")
	@ParameterizedTest
	@ValueSource(longs = { 1234567890123456789L, 0, 7569227076983L, 7569227076983L, 12345678901234L })
	public void testInvalidSozialversNr(@Nullable Long avhNr) {
		Bean bean = new Bean();
		if (avhNr != null) {
			bean.setAhvNr(new Sozialversicherungsnummer(avhNr));
		}

		assertViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");
	}

	public static class Bean {

		@Valid
		private Sozialversicherungsnummer ahvNr;

		public Sozialversicherungsnummer getAhvNr() {
			return ahvNr;
		}

		public void setAhvNr(Sozialversicherungsnummer ahvNr) {
			this.ahvNr = ahvNr;
		}
	}
}
