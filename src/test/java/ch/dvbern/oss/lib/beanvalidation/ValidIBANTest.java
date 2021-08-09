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

import ch.dvbern.oss.lib.beanvalidation.embeddables.IBAN;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertViolated;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * Testklasse für {@link ValidIBANNummer}
 */
public class ValidIBANTest {

	@SuppressWarnings("JUnitTestMethodWithNoAssertions")
	@ParameterizedTest
	@NullSource
	@ValueSource(strings = { "CH3909000000306638172", "CH39 0900 0000 3066 3817 2", "NL53ABNA0205986478" })
	public void testValidIBAN(@Nullable IBAN iban) {
		Bean bean = new Bean();
		bean.setIban(iban);
		assertNotViolated(ValidIBANNummer.class, bean, "iban");
	}

	@SuppressWarnings("JUnitTestMethodWithNoAssertions")
	@ParameterizedTest
	@ValueSource(strings = { "1234567890123456789", "CH123456", "CHE3909000000306638172", "" })
	public void testInvalidIBAN(@Nullable IBAN iban) {
		Bean bean = new Bean();
		bean.setIban(iban);
		assertViolated(ValidIBANNummer.class, bean, "iban");
	}

	@Test
	public void testClearingNumberExtraction() {

		Bean bean = new Bean();
		bean.setIban(new IBAN("NL53ABNA0205986478"));
		assertNotViolated(ValidIBANNummer.class, bean, "iban");
		bean.getIban().extractClearingNumber();
		bean.getIban().extractClearingNumberWithoutLeadingZeros();
		assertEquals(bean.getIban().extractClearingNumberWithoutLeadingZeros(), bean.getIban()
				.extractClearingNumber());

		bean.setIban(new IBAN("CH39 0900 0000 3066 3817 2"));
		assertNotViolated(ValidIBANNummer.class, bean, "iban");
		String leadingZeroes = bean.getIban().extractClearingNumber();
		String noLeadingZeroes = bean.getIban().extractClearingNumberWithoutLeadingZeros();
		assertEquals("09000", leadingZeroes);
		assertEquals("9000", noLeadingZeroes);

		bean = new Bean();
		bean.setIban(new IBAN("CH123456"));
		assertViolated(ValidIBANNummer.class, bean, "iban");

		try {
			bean.getIban().extractClearingNumber();
			fail("Invalid Iban should trigger exception when calling extractClearingNumber");
		} catch (IllegalArgumentException e) {
			//expected
		}

		try {
			bean.getIban().extractClearingNumberWithoutLeadingZeros();
			fail("Invalid Iban should trigger exception when calling extractClearingNumber");
		} catch (IllegalArgumentException e) {
			//expected
		}

	}

	public static class Bean {

		@Valid
		private IBAN iban;

		public IBAN getIban() {
			return iban;
		}

		public void setIban(IBAN iban) {
			this.iban = iban;
		}
	}
}
