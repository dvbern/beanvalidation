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
import org.junit.jupiter.api.Test;

/**
 * Testklasse für {@link PasswortComplexity}
 */
public class PasswordComplexityTest {

	@Test
	public void testComplexityValidation() {
		Bean bean = new Bean();
		bean.setPassword("");
		ValidationTestHelper.assertViolated(PasswortComplexity.class, bean, "password");

		bean.setPassword("test");
		ValidationTestHelper.assertViolated(PasswortComplexity.class, bean, "password");

		bean.setPassword("test1");
		ValidationTestHelper.assertViolated(PasswortComplexity.class, bean, "password");

		bean.setPassword("test1?");
		ValidationTestHelper.assertNotViolated(PasswortComplexity.class, bean, "password");
	}

	public static class Bean {

		@PasswortComplexity
		private String password;

		public String getPassword() {
			return password;
		}

		public void setPassword(final String password) {
			this.password = password;
		}
	}
}
