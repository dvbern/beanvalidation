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

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;

import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertNotViolated;
import static ch.dvbern.oss.lib.beanvalidation.util.ValidationTestHelper.assertViolated;

/**
 * Testklasse für {@link CompareTo}
 */
public class CompareToTest {

	@Test
	public void testEqual() {
		Bean bean = new Bean();
		bean.setEqual1(1);
		bean.setEqual2(2);
		assertViolated(CompareTo.class, bean, "equal1");
		bean.setEqual2(bean.getEqual1());
		assertNotViolated(CompareTo.class, bean, "equal1");
	}

	@Test
	public void testLess() {
		Bean bean = new Bean();
		bean.setLess1(BigDecimal.ZERO);
		bean.setLess2(BigDecimal.TEN);
		assertNotViolated(CompareTo.class, bean, "less1");
		bean.setLess2(BigDecimal.ZERO);
		assertViolated(CompareTo.class, bean, "less1");
	}

	@Test
	public void testLessOrEqual() {
		Bean bean = new Bean();
		long currentTimeMilis = System.currentTimeMillis();
		bean.setLessOrEqual1(new Date(currentTimeMilis));
		bean.setLessOrEqual2(new Date(currentTimeMilis));
		assertNotViolated(CompareTo.class, bean, "lessOrEqual1");
		bean.setLessOrEqual2(new Date(currentTimeMilis + 10));
		assertNotViolated(CompareTo.class, bean, "lessOrEqual1");
		bean.setLessOrEqual2(new Date(currentTimeMilis - 10));
		assertViolated(CompareTo.class, bean, "lessOrEqual1");
	}

	@Test
	public void testGreater() {
		Bean bean = new Bean();
		bean.setGreater1("BBB");
		bean.setGreater2("AAA");
		assertNotViolated(CompareTo.class, bean, "greater1");
		bean.setGreater2("CCC");
		assertViolated(CompareTo.class, bean, "greater1");
	}

	@Test
	public void testGreaterOrEqual() {
		Bean bean = new Bean();
		long currentTimeMilis = System.currentTimeMillis();
		bean.setGreaterOrEqual1(1.0);
		bean.setGreaterOrEqual2(0.5);
		assertNotViolated(CompareTo.class, bean, "greaterOrEqual1");
		bean.setGreaterOrEqual2(bean.getGreaterOrEqual1());
		assertNotViolated(CompareTo.class, bean, "greaterOrEqual1");
		bean.setGreaterOrEqual2(2.0);
		assertViolated(CompareTo.class, bean, "greaterOrEqual1");
	}

	@CompareTo.List({
			@CompareTo(property = "equal1", comparedTo = "equal2"),
			@CompareTo(property = "less1", comparedTo = "less2", comparison = CompareTo.Comparison.LESS),
			@CompareTo(property = "lessOrEqual1", comparedTo = "lessOrEqual2", comparison = CompareTo.Comparison.LESS_OR_EQUAL),
			@CompareTo(property = "greater1", comparedTo = "greater2", comparison = CompareTo.Comparison.GREATER),
			@CompareTo(property = "greaterOrEqual1", comparedTo = "greaterOrEqual2", comparison = CompareTo.Comparison.GREATER_OR_EQUAL)

	})
	public static class Bean {

		private Integer equal1;
		private Integer equal2;

		private BigDecimal less1;
		private BigDecimal less2;

		private Date lessOrEqual1;
		private Date lessOrEqual2;

		private String greater1;
		private String greater2;

		private Double greaterOrEqual1;
		private Double greaterOrEqual2;

		public Integer getEqual1() {
			return equal1;
		}

		public void setEqual1(Integer equal1) {
			this.equal1 = equal1;
		}

		public Integer getEqual2() {
			return equal2;
		}

		public void setEqual2(Integer equal2) {
			this.equal2 = equal2;
		}

		public BigDecimal getLess1() {
			return less1;
		}

		public void setLess1(BigDecimal less1) {
			this.less1 = less1;
		}

		public BigDecimal getLess2() {
			return less2;
		}

		public void setLess2(BigDecimal less2) {
			this.less2 = less2;
		}

		public Date getLessOrEqual1() {
			return lessOrEqual1;
		}

		public void setLessOrEqual1(Date lessOrEqual1) {
			this.lessOrEqual1 = lessOrEqual1;
		}

		public Date getLessOrEqual2() {
			return lessOrEqual2;
		}

		public void setLessOrEqual2(Date lessOrEqual2) {
			this.lessOrEqual2 = lessOrEqual2;
		}

		public String getGreater1() {
			return greater1;
		}

		public void setGreater1(String greater1) {
			this.greater1 = greater1;
		}

		public String getGreater2() {
			return greater2;
		}

		public void setGreater2(String greater2) {
			this.greater2 = greater2;
		}

		public Double getGreaterOrEqual1() {
			return greaterOrEqual1;
		}

		public void setGreaterOrEqual1(Double greaterOrEqual1) {
			this.greaterOrEqual1 = greaterOrEqual1;
		}

		public Double getGreaterOrEqual2() {
			return greaterOrEqual2;
		}

		public void setGreaterOrEqual2(Double greaterOrEqual2) {
			this.greaterOrEqual2 = greaterOrEqual2;
		}
	}
}
