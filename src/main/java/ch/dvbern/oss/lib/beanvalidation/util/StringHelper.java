package ch.dvbern.oss.lib.beanvalidation.util;

/**
 * to avoid external dependencies we add our own StringHelper
 */
public class StringHelper {

	public StringHelper() {
		//util, no init
	}

	/**
	 *
	 * @param source inputstring
	 * @return inputstring withoug leading zeroes, returns same string if no leading zeroes
	 * exit, returns null if input string was null
	 */
	public static String trimLeadingZeros(String source) {
		if (source == null) {
			return null;
		}

		int length = source.length();
		if (length < 2) {
			return source;
		}

		int i;
		for (i = 0; i < length - 1; i++) {
			char c = source.charAt(i);
			if (c != '0') {
				break;
			}
		}

		if (i == 0) {
			return source;
		}

		return source.substring(i);
	}
}
