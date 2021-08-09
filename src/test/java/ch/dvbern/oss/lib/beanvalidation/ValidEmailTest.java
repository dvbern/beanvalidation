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

import ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Testklasse für {@link ValidEmail}
 */
public class ValidEmailTest {

	@SuppressWarnings("JUnitTestMethodWithNoAssertions")
	@ParameterizedTest
	@ValueSource(strings = { "test@example.com", "test@example.accountants" })
	public void testValidEmail(String email) {
		Bean bean = new Bean();
		bean.setEmail(email);

		ValidationTestHelper.assertNotViolated(ValidEmail.class, bean, "email");
	}

	@SuppressWarnings("JUnitTestMethodWithNoAssertions")
	@ParameterizedTest
	@ValueSource(strings = { "invalidemail123", "invalid@nothing", "test@example.c" })
	public void testInvalidEmail(String email) {
		Bean bean = new Bean();
		bean.setEmail(email);

		ValidationTestHelper.assertViolated(ValidEmail.class, bean, "email");
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
