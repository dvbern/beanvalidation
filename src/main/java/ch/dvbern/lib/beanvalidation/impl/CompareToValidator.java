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

package ch.dvbern.lib.beanvalidation.impl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import ch.dvbern.lib.beanvalidation.CompareTo;

import static ch.dvbern.lib.beanvalidation.impl.ValidatorUtil.createViolationMessageForNestedProperty;
import static org.apache.commons.beanutils.PropertyUtils.getNestedProperty;

/**
 *
 */
public class CompareToValidator implements ConstraintValidator<CompareTo, Object> {

	private String property = null;
	private String otherProperty = null;
	private String messageTemplate;
	private CompareTo.Comparison comparison;

	public void initialize(CompareTo constraintAnnotation) {
		property = constraintAnnotation.property();
		otherProperty = constraintAnnotation.comparedTo();
		messageTemplate = constraintAnnotation.message();
		comparison = constraintAnnotation.comparison();
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean returnValue = false;
		try {
			Comparable first = (Comparable) getNestedProperty(value, property);
			Object second = getNestedProperty(value, otherProperty);
			if (first == null) {
				returnValue = true;
			} else if (second != null) {
				returnValue = doComparison(first, second, comparison);
			}
			// Wenn first != null und second == null: Prüfung würde NPE bewirken, einfach false zurückgeben.
		} catch (Exception e) {
			throw new ValidationException("Cannot read property " + property + " and comparison property " + otherProperty);
		}
		if (!returnValue) {
			createViolationMessageForNestedProperty(property, messageTemplate, context);
		}
		return returnValue;
	}

	@SuppressWarnings({ "unchecked" })
	private boolean doComparison(Comparable value, Object compare, CompareTo.Comparison comparison) {
		boolean returnValue = false;
		switch (comparison) {
		case EQUAL:
			returnValue = value.compareTo(compare) == 0;
			break;
		case GREATER:
			returnValue = value.compareTo(compare) > 0;
			break;
		case GREATER_OR_EQUAL:
			returnValue = value.compareTo(compare) >= 0;
			break;
		case LESS:
			returnValue = value.compareTo(compare) < 0;
			break;
		case LESS_OR_EQUAL:
			returnValue = value.compareTo(compare) <= 0;
			break;
		default:
			throw new IllegalStateException("Unsupported case: " + comparison);
		}
		return returnValue;
	}
}
