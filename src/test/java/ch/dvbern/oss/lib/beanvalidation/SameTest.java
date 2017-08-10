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

import java.util.Set;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidationException;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ch.dvbern.oss.lib.beanvalidation.impl.SameValidator;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Testklasse für {@link Same} und {@link SameValidator}
 */
public class SameTest {

	private Validator validator;

	@Before
	public void setUp() {
		Configuration config = Validation.byDefaultProvider().configure();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void testNotSame() {
		Bean bean = createBean("foo", "bar", "baz");
		Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
		assertFalse(violations.isEmpty());
		assertEquals(2, violations.size());
	}

	@Test
	public void testSame() {
		Bean bean = createBean("foo", "foo", "foo");
		Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
		assertTrue(violations.isEmpty());
	}

	@Test
	public void testNull() {
		Bean bean = createBean("foo", null, null);
		Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
		assertFalse(violations.isEmpty());
	}

	@Test
	public void testNullFirst() {
		Bean bean = createBean(null, "foo", null);
		Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
		assertFalse(violations.isEmpty());
	}

	@Test(expected = ValidationException.class)
	public void testNestedNull() {
		Bean bean = createBean(null, "foo", null);
		bean.setNested(null);
		Set<ConstraintViolation<Bean>> violations = validator.validate(bean);
	}

	private Bean createBean(String name, String selbigerName, String stringValue) {
		Bean bean = new Bean();
		bean.setName(name);
		bean.setSelbigerName(selbigerName);
		bean.getNested().setStringValue(stringValue);
		return bean;
	}

	@Same.List({
			@Same(property = "name", sameAs = "selbigerName"),
			@Same(property = "name", sameAs = "nested.stringValue", message = "fooo")
	})
	public static class Bean {

		String name;
		String selbigerName;
		NestedBean nested = new NestedBean();

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getSelbigerName() {
			return selbigerName;
		}

		public void setSelbigerName(String selbigerName) {
			this.selbigerName = selbigerName;
		}

		public NestedBean getNested() {
			return nested;
		}

		public void setNested(NestedBean nested) {
			this.nested = nested;
		}
	}

	public static class NestedBean {
		String stringValue;

		public String getStringValue() {
			return stringValue;
		}

		public void setStringValue(String stringValue) {
			this.stringValue = stringValue;
		}
	}
}
