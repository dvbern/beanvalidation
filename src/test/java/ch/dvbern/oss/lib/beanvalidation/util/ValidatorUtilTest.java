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

package ch.dvbern.oss.lib.beanvalidation.util;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit tests of the ch.dvbern.lib.beanvalidation.impl.ValidatorUtil.
 */
@SuppressWarnings("JUnitTestMethodWithNoAssertions")
public class ValidatorUtilTest {

	private TestBean testBean = null;

	@BeforeEach
	public void before() {
		testBean = new TestBean();
	}

	@Test
	public void testAssertViolatedError() {
		Assertions.assertThrows(AssertionError.class, () -> ValidationTestHelper.assertNotViolated(testBean));
	}

	@Test
	public void testAssertViolated() {
		ValidationTestHelper.assertViolated(testBean);
	}

	@Test
	public void testAssertViolatedWithViolationAnnotation() {
		testBean.setNumber(0);
		ValidationTestHelper.assertViolated(Min.class, testBean);
	}

	@Test
	public void testAssertViolatedWithViolationAnnotationException() {
		testBean.setNumber(0);
		Assertions.assertThrows(AssertionError.class, () -> ValidationTestHelper.assertNotViolated(Min.class, testBean));
	}

	@Test
	public void testAssertViolatedWithViolationAnnotationAndProperty() {
		testBean.setNumber(0);
		ValidationTestHelper.assertViolated(Min.class, testBean, "number");
	}

	@Test
	public void testAssertViolatedWithViolationAnnotationAndPropertyException() {
		testBean.setNumber(0);
		Assertions.assertThrows(AssertionError.class, () ->
		ValidationTestHelper.assertNotViolated(Min.class, testBean, "number"));
	}

	@Test
	public void testAssertViolatedWithViolationAnnotationAndProperty2() {
		testBean.setNumber(0);
		ValidationTestHelper.assertNotViolated(Min.class, testBean, "name");
	}

	public static class TestBean {

		@NotNull
		private String name = null;

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
