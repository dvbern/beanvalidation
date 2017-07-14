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

import javax.validation.ConstraintValidatorContext;

/**
 *
 */
final class ValidatorUtil {

	/**
	 * No Instantiation
	 */
	private ValidatorUtil() {
	}

	static void createViolationMessageForNestedProperty(final String nestedProperty, final String messageTemplate, final
	ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();

		String[] splitted = nestedProperty.split("\\.");
		ConstraintValidatorContext.ConstraintViolationBuilder builder = context.buildConstraintViolationWithTemplate(messageTemplate);
		ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderDefinedContext nodeDefinedContext = null;
		ConstraintValidatorContext.ConstraintViolationBuilder.NodeBuilderCustomizableContext nodeCustomizableContext = null;
		for (String s : splitted) {
			if (nodeDefinedContext == null) {
				nodeDefinedContext = builder.addNode(s);
			} else if (nodeCustomizableContext == null) {
				nodeCustomizableContext = nodeDefinedContext.addNode(s);
			} else {
				nodeCustomizableContext = nodeCustomizableContext.addNode(s);
			}
		}
		if (nodeDefinedContext == null) {
			builder.addConstraintViolation();
		} else if (nodeCustomizableContext == null) {
			nodeDefinedContext.addConstraintViolation();
		} else {
			nodeCustomizableContext.addConstraintViolation();
		}
	}
}
