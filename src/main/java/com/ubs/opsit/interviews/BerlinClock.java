package com.ubs.opsit.interviews;

import java.util.Arrays;

import org.apache.commons.lang.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BerlinClock implements TimeConverter {
	
	private static final Logger LOG = LoggerFactory.getLogger(BerlinClock.class);

	@Override
	public String convertTime(String aTime) {
		// 00:00:00
		int[] array = Arrays.stream(aTime.split(":")).mapToInt(Integer::parseInt).toArray();

		int hours = array[0];
		int minutes = array[1];
		int seconds = array[2];
		
		validateRange(24, hours, "hours");
		validateRange(60, minutes, "minutes");
		validateRange(60, seconds, "seconds");

		int hr5div = hours / 5;
		int hr5mod = hours % 5;
		int min5div = minutes / 5;
		int min5mod = minutes % 5;
		
		StringBuilder berlinTime = new StringBuilder();

		if (seconds % 2 == 0) {
			berlinTime.append("Y");
		} else {
			berlinTime.append("O");
		}
		berlinTime.append("\n");
		convertFirstRow(berlinTime, hr5div);
		
		berlinTime.append("\n");
		convertSecondRow(berlinTime, hr5mod);
		
		berlinTime.append("\n");
		convertThirdRow(berlinTime, min5div);
		
		berlinTime.append("\n");
		convertFourthRow(berlinTime, min5mod);
		return berlinTime.toString();
	}

	private void convertFirstRow(StringBuilder berlinTime, int hr5div) {
		
		LOG.debug("Convert first row and append in String builder");
		for (int i = 1; i <= 4; i++) {
			if (i <= hr5div) {
				berlinTime.append("R");
			} else {
				berlinTime.append("O");
			}
		}
	}
	
	private void convertSecondRow(StringBuilder berlinTime, int hr5mod) {
		
		LOG.debug("Convert 2nd row and append in String builder");
		for (int j = 1; j <= 4; j++) {
			if (j <= hr5mod) {
				berlinTime.append("R");
			}
			else {
				berlinTime.append("O");
			}
		}
	}
	
	private void convertThirdRow(StringBuilder berlinTime, int min5div) {
		
		LOG.debug("Convert 3rd row and append in String builder");
		for (int k = 1; k <= 12; k++) {
			if (k <= min5div && k%3!=0) {
				berlinTime.append("Y");
			} else if (k <= min5div && k%3==0) {
				berlinTime.append("R");
			}
			else {
				berlinTime.append("O");
			}
		}
	}
	
	private void convertFourthRow(StringBuilder berlinTime, int min5mod) {
		
		LOG.debug("Convert 4th row and append in String builder");
		for (int l = 1; l <= 4; l++) {
			if (l <= min5mod) {
				berlinTime.append("Y");
			}
			else {
				berlinTime.append("O");
			}
		}
	}
	
	private static void validateRange(int maxExcl, int arg, String argName){
		Validate.isTrue(arg >= 0, "Argument `" + argName + "` should be more than zero");
		Validate.isTrue(arg <= maxExcl, "Argument `" + argName + "` should be less than " + maxExcl);
	}
}
