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

import ch.dvbern.lib.beanvalidation.Same;

import static ch.dvbern.lib.beanvalidation.impl.ValidatorUtil.createViolationMessageForNestedProperty;
import static org.apache.commons.beanutils.PropertyUtils.getNestedProperty;

/**
 * {@link javax.validation.ConstraintValidator} welcher prüft ob 2 Properties auf einem Bean identisch sind.
 */
public class SameValidator implements ConstraintValidator<Same, Object> {

	private String property;
	private String verifyValue;
	private String messageTemplate;

	public void initialize(Same constraintAnnotation) {
		property = constraintAnnotation.property();
		verifyValue = constraintAnnotation.sameAs();
		messageTemplate = constraintAnnotation.message();
	}

	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean returnValue = true;
		try {
			Object first = getNestedProperty(value, property);
			Object second = getNestedProperty(value, verifyValue);
			returnValue = first == null && second == null || first != null && first.equals(second);
		} catch (Exception e) {
			throw new ValidationException("Cannot read property " + property + " and verification " + verifyValue, e);
		}
		if (!returnValue) {
			createViolationMessageForNestedProperty(verifyValue, messageTemplate, context);
		}
		return returnValue;
	}
}
