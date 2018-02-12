package no.ssb.health.hly;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Arrays;
import java.util.Collection;
 
import org.junit.Test;

import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runner.RunWith;
import static org.junit.Assert.assertEquals;


import org.junit.BeforeClass;
import org.junit.Before;


import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;



import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class ParametricRegularExpressionTest {

	private static String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
	private static Pattern pattern;

	private String phrase;
	private boolean match;
	
	@Parameters
	public static Collection regExValues() {
	 return Arrays.asList(new Object[][] {
	  {"22101", true },
	  {"221x1", false },
	  {"22101-5150", true },
	  {"221015150", false }});
	}	

	public ParametricRegularExpressionTest(String phrase, boolean match) {
		 this.phrase = phrase;
		 this.match = match;
		}
	
	@Before
	public void setUpBefore() throws Exception {
		  pattern = Pattern.compile(zipRegEx);
		 }

	
	
	@Test
	public void verifyGoodZipCode() throws Exception{		
	 Matcher mtcher = this.pattern.matcher(phrase);
	 boolean isValid = mtcher.matches();
	 System.out.println("phrase: "+phrase+"    match: "+match );
	 assertEquals("Pattern did not validate zip code", isValid, match);
	}
	
	
	
}
