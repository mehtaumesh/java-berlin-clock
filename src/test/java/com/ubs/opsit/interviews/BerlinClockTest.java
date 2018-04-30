package com.ubs.opsit.interviews;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class BerlinClockTest {
	
	@Test
    public void thisTestShouldPassIfYouHaveEverythingIsSetupCorrectly() {
		
		String expectedClk = "O\n" +
				"RRRR\n" +
				"RRRO\n" +
				"YYRYYRYYRYYO\n" +
				"YYYY";
        String actualStr = new BerlinClock().convertTime("23:59:59");
        assertThat(actualStr, is(expectedClk));
    }
	
	@Test(expected = IllegalArgumentException.class)
    public void thisTestShouldPassIfValidationWorks() {
		
		new BerlinClock().convertTime("27:59:59");
	}
}
