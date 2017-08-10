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

import ch.dvbern.oss.lib.beanvalidation.embeddables.PCNummer;
import org.junit.Test;

import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertViolated;

/**
 * Testklasse für {@link ValidPCNummer}
 */
public class ValidPCNummerTest {

	@Test
	public void testValidSozialversNr() {
		Bean bean = new Bean();
		bean.setPcNummer(new PCNummer(Long.valueOf(301045969L)));
		assertViolated(ValidPCNummer.class, bean, "pcNummer");

		bean = new Bean();
		bean.setPcNummer(new PCNummer());
		assertViolated(ValidPCNummer.class, bean, "pcNummer");

		bean.setPcNummer(new PCNummer(new Long(10150006)));
		assertNotViolated(ValidPCNummer.class, bean, "pcNummer");

		bean.setPcNummer(new PCNummer(Long.valueOf(7569227076983L)));
		assertViolated(ValidPCNummer.class, bean, "pcNummer");

		bean.setPcNummer(new PCNummer(Long.valueOf(301045968L)));
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
