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

import javax.validation.Valid;

import ch.dvbern.oss.lib.beanvalidation.embeddables.Sozialversicherungsnummer;
import org.junit.Test;

import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertViolated;

/**
 * Testklasse für {@link ValidSozialversicherungsnummer}
 */
public class ValidSozialversicherungsnummerTest {

	@Test
	public void testValidSozialversNr() {
		ValidIBANTest.Bean withoutAHV = new ValidIBANTest.Bean();
		assertNotViolated(ValidSozialversicherungsnummer.class, withoutAHV, "ahvNr");

		Bean bean = new Bean();
		bean.setAhvNr(new Sozialversicherungsnummer(new Long(1234567890123456789L)));

		assertViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");

		bean = new Bean();
		bean.setAhvNr(new Sozialversicherungsnummer(new Long(0)));
		assertViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");

		bean.setAhvNr(new Sozialversicherungsnummer(new Long(7569217076985L)));
		assertNotViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");

		bean.setAhvNr(new Sozialversicherungsnummer(Long.valueOf(7569227076983L)));
		assertViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");

		bean.setAhvNr(new Sozialversicherungsnummer(new Long(12345678901234L)));
		assertViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");

		bean.setAhvNr(new Sozialversicherungsnummer(Long.valueOf(7569217076985L)));
		assertNotViolated(ValidSozialversicherungsnummer.class, bean, "ahvNr");
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
