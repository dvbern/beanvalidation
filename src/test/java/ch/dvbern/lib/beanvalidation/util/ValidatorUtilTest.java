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

package ch.dvbern.lib.beanvalidation.util;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.junit.Before;
import org.junit.Test;

import static ch.dvbern.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.lib.beanvalidation.util.ValidationTestHelper.assertViolated;

/**
 * Unit tests of the ch.dvbern.lib.beanvalidation.impl.ValidatorUtil.
 */
public class ValidatorUtilTest {

	private TestBean testBean;

	@Before
	public void before() {
		testBean = new TestBean();
	}

	@Test(expected = AssertionError.class)
	public void testAssertViolatedError() {
		assertNotViolated(testBean);
	}

	@Test
	public void testAssertViolated() {
		assertViolated(testBean);
	}

	@Test
	public void testAssertViolatedWithViolationAnnotation() {
		testBean.setNumber(0);
		assertViolated(Min.class, testBean);
	}

	@Test(expected = AssertionError.class)
	public void testAssertViolatedWithViolationAnnotationException() {
		testBean.setNumber(0);
		assertNotViolated(Min.class, testBean);
	}

	@Test
	public void testAssertViolatedWithViolationAnnotationAndProperty() {
		testBean.setNumber(0);
		assertViolated(Min.class, testBean, "number");
	}

	@Test(expected = AssertionError.class)
	public void testAssertViolatedWithViolationAnnotationAndPropertyException() {
		testBean.setNumber(0);
		assertNotViolated(Min.class, testBean, "number");
	}

	@Test
	public void testAssertViolatedWithViolationAnnotationAndProperty2() {
		testBean.setNumber(0);
		assertNotViolated(Min.class, testBean, "name");
	}

	public static class TestBean {

		@NotNull
		private String name;

		@Max(value = 10)
		@Min(value = 1)
		private int number;

		public String getName() {
			return name;
		}

		public void setName(final String name) {
			this.name = name;
		}

		public int getNumber() {
			return number;
		}

		public void setNumber(final int number) {
			this.number = number;
		}
	}
}
