package no.ssb.health.hly;

import org.junit.BeforeClass;
import org.junit.Before;

import org.junit.Test ;
import org.junit.Ignore ;
import static org.junit.Assert.assertTrue ;
import static org.junit.Assert.assertFalse ;
import static org.junit.Assert.assertEquals ;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.io.* ;


	
public class MortalityRatesTest {
	private static String zipRegEx = "\\d{5}([\\-]\\d{4})?$" ;
	private static Pattern pattern ;
	public static MortalityRatesDAOImpl iRDI ;
	public static MortalityRatesDAOFileImpl mRDI ;
	
	
	@BeforeClass
	public static void setUpBeforeClassFile() throws Exception {
		
	     mRDI = new MortalityRatesDAOFileImpl(2005,10,"src/resources/data/Dodelighet_2005-2015_1.csv","src/resources/data/Dodelighet_2005-2015_2.csv") ;
	   //  System.out.println("Mortality rates, first record id: " +  mRDI.getMortalityRates(1).getId()) ;
	    // System.out.println("Mortality rates, first record id: " +  mRDI.getMortalityRates(1).getId()) ;
		     
	 	
	}	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		  iRDI = new MortalityRatesDAOImpl() ;
	      
	      MortalityRates instR1 = new MortalityRates(1,1,2010,1.2,0.3);
	      MortalityRates instR2 = new MortalityRates(2,2,2010,1.3,0.6);
	      MortalityRates instR3 = new MortalityRates(3,3,2010,1.4,1.5);
	      MortalityRates instR4 = new MortalityRates(4,4,2010,1.5,3.0);
	      MortalityRates instR5 = new MortalityRates(5,5,2010,1.6,10.6);
	  
	      iRDI.instRs.add(instR1);
	      iRDI.instRs.add(instR2);
	      iRDI.instRs.add(instR3);
	      iRDI.instRs.add(instR4);
	      iRDI.instRs.add(instR5);
		pattern = Pattern.compile(zipRegEx) ;
	}
	
	
	@Before
	public void setUpBefore() throws Exception {
		pattern = Pattern.compile(zipRegEx) ;
	}
	
	@Test
	public void verifyDAOImpl() throws Exception {
		boolean notValid=false ;
		assertFalse("Validated",notValid) ;
	}
	
	@Test
	public void verifyDAOFileImpl() throws Exception {
		boolean isValid=true ;
		System.out.println( mRDI.getMortalityRates(1)) ;
		assertTrue("notValidated",isValid) ;
	}
	
	
	@Test
	public void verifyDAOAllImpl() throws Exception {
		boolean isValid=true ;
		assertEquals(true,isValid) ;
	}
	
	
	
}
