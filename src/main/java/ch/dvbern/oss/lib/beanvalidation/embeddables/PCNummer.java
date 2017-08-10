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

import ch.dvbern.datatypes.PostkontoNummer;
import ch.dvbern.oss.lib.beanvalidation.ValidPCNummer;

/**
 * Postkonto Nummer
 */
@Embeddable
@ValidPCNummer
public class PCNummer {

	@NotNull
	private Long pcNummer;

	public PCNummer() {
	}

	public PCNummer(Long pcNummer) {

		this.pcNummer = pcNummer;
	}

	public Long getPcNummer() {
		return pcNummer;
	}

	public void setPcNummer(final Long pcNummer) {
		this.pcNummer = pcNummer;
	}

	@Override
	public String toString() {
		String retVal;
		if (pcNummer != null) {
			try {
				retVal = new PostkontoNummer(pcNummer).toString();
			} catch (IllegalArgumentException x) {
				retVal = pcNummer.toString();
			}
		} else {
			retVal = "0";
		}
		return retVal;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof PCNummer)) {
			return false;
		}
		final PCNummer pcNummer1 = (PCNummer) o;
		return !(pcNummer != null ? !pcNummer.equals(pcNummer1.pcNummer) : pcNummer1.pcNummer != null);
	}

	@Override
	public int hashCode() {
		return pcNummer != null ? pcNummer.hashCode() : 0;
	}
}
