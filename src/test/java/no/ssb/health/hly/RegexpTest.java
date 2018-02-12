package no.ssb.health.hly;

import org.junit.BeforeClass;
import org.junit.Test ;
import org.junit.Ignore ;
import static org.junit.Assert.assertTrue ;
import static org.junit.Assert.assertFalse ;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.* ;


public class RegexpTest {
	
	private static String zipRegEx = "\\d{5}([\\-]\\d{4})?$" ;
	private static Pattern pattern ;	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		pattern = Pattern.compile(zipRegEx) ;
	}
	
	@Test
	public void verifyZipCodeNoMatch() throws Exception {
		Matcher mtcher=this.pattern.matcher("2211") ;
		boolean notValid = mtcher.matches();
		assertFalse("Validated",notValid) ;
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	 public void verifyZipCodeGroupException() throws Exception{		
	  Matcher mtcher = this.pattern.matcher("22101-5051");
	  boolean isValid = mtcher.matches();			
	  mtcher.group(2);	
	}

	@Test(timeout=1)
	public void verifyFastZipCodeMatch() throws Exception{		
	 Pattern pattern = Pattern.compile("^\\d{5}([\\-]\\d{4})?$"); 
	 Matcher mtcher = pattern.matcher("22011");
	 boolean isValid = mtcher.matches();		
	 assertTrue("Pattern did not validate zip code", isValid);
	}
	
	//@Ignore("this regular expression isn't working yet")
	@Ignore
	@Test
	public void verifyZipCodeMatch() throws Exception{		
	 Pattern pattern = Pattern.compile("^\\d{5}([\\-]\\d{4})"); 
	 Matcher mtcher = pattern.matcher("22011");
	 boolean isValid = mtcher.matches();		
	 assertTrue("Pattern did not validate zip code", isValid);
	}
}
