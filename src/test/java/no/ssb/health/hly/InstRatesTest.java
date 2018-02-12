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





public class InstRatesTest {
	static InstRatesDAOFileImpl iRDI ;
	
	
	@BeforeClass
	public static void setUpBeforeClassFile() throws Exception {
		
	 
	 iRDI = new InstRatesDAOFileImpl(2010) ;
     iRDI.getInstRates(1) ;
	
	}
	
	@Test
	public void verifyDAOAllImpl() throws Exception {
		boolean isValid=true ;
		assertEquals(true,isValid) ;
	}
	
	
}
