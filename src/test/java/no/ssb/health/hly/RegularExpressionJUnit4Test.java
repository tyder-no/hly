package no.ssb.health.hly;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;



public class RegularExpressionJUnit4Test {

	
	private static String zipRegEx = "^\\d{5}([\\-]\\d{4})?$";
	 private static Pattern pattern;
	 static int value1, value2;
		
	   // assigning the values
	//@Override
	@Before
	   public void setUp(){
	      value1=3;
	      value2=3;
	   }

	 @BeforeClass
	 public static void setUpBeforeClass() throws Exception {
	  pattern = Pattern.compile(zipRegEx);
	 }

	 @Before
	 public void setUpBefore() throws Exception {
	  pattern = Pattern.compile(zipRegEx);
	 }

	 
	 @Test
	 public void verifyZipCodeNoMatch() throws Exception{		
	  Matcher mtcher = this.pattern.matcher("2211");
	
	  boolean notValid = mtcher.matches();		
	  assertFalse("Pattern did validate zip code", notValid);
	  
	  double result= value1 + value2;
      assertTrue(result == 6);
      value1 = value1 + value2;
      
	 }

	 @Test
	 public void testApp()
	    {
	        double result= value1 + value2;
	        assertTrue(result == 6);
	        value1 = value1 + value2;
	        System.out.println("testApp value1: "+value1);
	    }
	 
	 @Test(expected=IndexOutOfBoundsException.class)
	 public void verifyZipCodeGroupException() throws Exception{		
	  Matcher mtcher = this.pattern.matcher("22101-5051");
	  boolean isValid = mtcher.matches();			
	  mtcher.group(2);		
	 }
	
	 
	
	
}
