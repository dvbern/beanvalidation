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

package ch.dvbern.lib.beanvalidation.embeddables;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import ch.dvbern.lib.beanvalidation.ValidIBANNummer;

/**
 * IBAN
 */
@Embeddable
@ValidIBANNummer
public class IBAN {

	private static final int DB_IBAN_LENGTH = 34; // IBAN nummer kann maximal 34 Stellen umfassen
	@NotNull
	@Size(min = 1, max = DB_IBAN_LENGTH)
	@Column(length = DB_IBAN_LENGTH)
	private String iban;

	public IBAN() {
	}

	public IBAN(String iban) {
		this.iban = iban;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(final String iban) {
		this.iban = iban;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof IBAN)) {
			return false;
		}
		final IBAN iban1 = (IBAN) o;
		return !(iban != null ? !iban.equals(iban1.iban) : iban1.iban != null);
	}

	@Override
	public int hashCode() {
		return iban != null ? iban.hashCode() : 0;
	}

	@Override
	public String toString() {
		String retVal = null;
		if (iban != null) {
			try {
				retVal = new ch.dvbern.datatypes.IBAN(iban).toString();
			} catch (IllegalArgumentException x) {
				retVal = iban;
			}
		} else {
			retVal = "0";
		}
		return retVal;
	}

	public String extractClearingNumber() {
		return new ch.dvbern.datatypes.IBAN(iban).extractClearingNr();
	}

	/**
	 * Die Banken aus Nil+ haben die Clearing-Nummer nicht zwingend 5-stellig. Die aus der IBAN bezogene Clearing-Nummer ist aber immer
	 * 5-stellig, zum Vergleich muss sie daher mit führenden Nullen erweitert werden.
	 *
	 * @return die ClearingNummer
	 */
	public String extractClearingNumberWithoutLeadingZeros() {
		return Integer.valueOf(extractClearingNumber()).toString();
	}
}
