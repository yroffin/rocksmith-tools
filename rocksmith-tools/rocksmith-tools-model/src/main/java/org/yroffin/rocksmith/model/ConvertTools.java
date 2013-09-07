package org.yroffin.rocksmith.model;

import java.text.DecimalFormat;
import java.util.Locale;

public final class ConvertTools {
	public static String format(long element) {
		return format(element / 2000.);
	}

	public static String format(double element) {
		DecimalFormat df = (DecimalFormat) DecimalFormat
				.getNumberInstance(Locale.ENGLISH);
		df.setMaximumFractionDigits(3);
		df.setMinimumFractionDigits(3);
		df.setDecimalSeparatorAlwaysShown(true);
		return df.format(element);
	}
}
