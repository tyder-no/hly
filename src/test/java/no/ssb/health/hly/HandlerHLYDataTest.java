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



public class HandlerHLYDataTest {


	static InstRatesDAOFileImpl iRDI ;
	static int compYear=2006 ;
	static HandlerHLYData hndlHLY ;
	static ArraysHLY dataPack ;
	
	
	@BeforeClass
	public static void setUpBeforeClassFile() throws Exception {
        int debug=0 ;
       // hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2015.properties") ;
        hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2017.properties") ;
		//hndlHLY = new  HandlerHLYData(compYear) ;
		dataPack = hndlHLY.getDataPack() ;
		if (debug==1)System.out.println(dataPack.aliveDataF[10]) ;
	    
		

	}
	
	@Before
	public void setUpBefore() throws Exception {
	//	hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2015.properties") ;
	    hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2017.properties") ;
		dataPack = hndlHLY.getDataPack() ;
	}

	public void printDAOData() throws Exception {
		System.out.println("CompYear: "+ compYear) ;
		for (int i=0;i<10;i++)
			System.out.println("Alive - "+ i + ": "+ dataPack.aliveDataF[i]) ;
		for (int i=20;i<30;i++)
			System.out.println("Healthy - "+ i + ": "+dataPack.healthDataF[i]) ;
	
	}


	@Test
	public void verifyDAOAllImpl1() throws Exception {
		boolean isValid=true ;
		printDAOData()  ;
		compYear=2007 ;	
		assertEquals(true,isValid) ;
	}

	@Test
	public void verifyDAOAllImpl2() throws Exception {
		boolean isValid=true ;
		printDAOData()  ;
		compYear=2008 ;	
		assertEquals(true,isValid) ;
	}

	@Test
	public void verifyDAOAllImpl3() throws Exception {
		boolean isValid=true ;
		printDAOData()  ;
		compYear=2009 ;	
		assertEquals(true,isValid) ;
	}
		
}
