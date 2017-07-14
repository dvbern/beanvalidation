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

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;

/**
 * Basisklasse für einen {@link javax.validation.ConstraintValidator} welcher keine initialisierung benötigt.
 */
public abstract class AbstractInitializedConstraintValidator<A extends Annotation, T> implements ConstraintValidator<A, T> {

	public void initialize(final A constraintAnnotation) {
		// Nothing to do.
	}
}
