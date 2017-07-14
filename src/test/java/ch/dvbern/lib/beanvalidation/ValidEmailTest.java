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

import org.junit.Test;

import static ch.dvbern.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.lib.beanvalidation.util.ValidationTestHelper.assertViolated;

/**
 * Testklasse für {@link ch.dvbern.lib.beanvalidation.ValidEmail}
 */
public class ValidEmailTest {

	@Test
	public void testValidEmail() {
		Bean bean = new Bean();
		bean.setEmail("invalidemail123");
		assertViolated(ValidEmail.class, bean, "email");

		bean = new Bean();
		bean.setEmail("invalid@nothing");
		assertViolated(ValidEmail.class, bean, "email");

		bean.setEmail("test@example.com");
		assertNotViolated(ValidEmail.class, bean, "email");

		bean.setEmail("test@example.accountants");
		assertNotViolated(ValidEmail.class, bean, "email");

		bean.setEmail("test@example.c");
		assertViolated(ValidEmail.class, bean, "email");
	}

	public static class Bean {

		@ValidEmail
		private String email;

		public String getEmail() {
			return email;
		}

		public void setEmail(final String email) {
			this.email = email;
		}
	}
}
