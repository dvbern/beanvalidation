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

package ch.dvbern.oss.lib.beanvalidation.embeddables;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

import ch.dvbern.datatypes.SozialversicherungsNummer;
import ch.dvbern.oss.lib.beanvalidation.ValidSozialversicherungsnummer;

/**
 * ValidSozialversicherungsnummer
 */
@Embeddable
@ValidSozialversicherungsnummer
public class Sozialversicherungsnummer {

	private Long ahvNummer;

	public Sozialversicherungsnummer() {
	}

	public Sozialversicherungsnummer(Long ahvNummer) {
		this.ahvNummer = ahvNummer;
	}

	@NotNull
	public Long getAhvNummer() {
		return ahvNummer;
	}

	public void setAhvNummer(Long ahvNummer) {
		this.ahvNummer = ahvNummer;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof Sozialversicherungsnummer)) {
			return false;
		}

		Sozialversicherungsnummer that = (Sozialversicherungsnummer) o;

		if (ahvNummer != null ? !ahvNummer.equals(that.ahvNummer) : that.ahvNummer != null) {
			return false;
		}

		return true;
	}

	@Override
	public int hashCode() {
		return ahvNummer != null ? ahvNummer.hashCode() : 0;
	}

	@Override
	public String toString() {
		String retVal = null;
		if (ahvNummer != null) {
			try {
				retVal = new SozialversicherungsNummer(ahvNummer).toString();
			} catch (IllegalArgumentException x) {
				retVal = ahvNummer.toString();
			}
		} else {
			retVal = "0";
		}
		return retVal;
	}
}
