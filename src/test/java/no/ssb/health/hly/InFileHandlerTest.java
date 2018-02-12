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
import java.util.* ;
import java.util.ArrayList;
import java.util.List;


	
 class MortalityRatesTest0 {
	private static String zipRegEx = "\\d{5}([\\-]\\d{4})?$" ;
	private static Pattern pattern ;
	public static MortalityRatesDAOImpl iRDI ;
	public static MortalityRatesDAOFileImpl mRDI ;
	
	//@BeforeClass
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
	
	//@BeforeClass
	public static void setUpBeforeClassFile() throws Exception {
		
	     mRDI = new MortalityRatesDAOFileImpl(2005,10,"src/resources/data/Dodelighet_2005-2015_1.csv","src/resources/data/Dodelighet_2005-2015_2.csv") ;
	    
	}	
	
	
	//@Before
	public void setUpBefore() throws Exception {
		pattern = Pattern.compile(zipRegEx) ;
	}
	
	//@Test
	public void verifyDAOImpl() throws Exception {
		boolean notValid=false ;
		assertFalse("Validated",notValid) ;
	}
	
	//@Test
	public void verifyDAOFileImpl() throws Exception {
		boolean isValid=true ;
		System.out.println( mRDI.getMortalityRates(1)) ;
		assertTrue("notValidated",isValid) ;
	}
	
	
	//@Test
	public void verifyDAOAllImpl() throws Exception {
		boolean isValid=true ;
		assertEquals(true,isValid) ;
	}
	
	
	
}


 public class InFileHandlerTest {
	 static InFileHandler mrt1H ;
	 static InFileHandler mrt2H ;
			 
	 static InFileHandler strH ;
	 static InFileHandler fct ;

	 @BeforeClass
	 public static void setUpBeforeClass() throws Exception {
		 int debug=0 ;
		 fct = new InFileHandler("src/resources/data/hly_2010_eusilc_1.scv",2,3) ;

		 double[][] iData = fct.getDblArr() ; 
		 System.out.println(fct.nRecs + "  " + fct.nFlds) ;
		 if (debug==1)
			 for (int i=0;i<fct.nRecs;i++) {
				 for (int j=0;j<fct.nFlds;j++)  System.out.print(iData[i][j] + "  ") ;
				 System.out.println() ;
			 } 

		 strH = new InFileHandler("src/resources/data/hly_2010_eusilc_1.scv",4) ;

		 List <String[]> sData = strH.getStrArr() ; 
		 System.out.println(strH.nRecs + "  " + strH.nFlds) ;
		 if (debug==1)
			 for (int i=0;i<strH.nRecs;i++) {
				 for (int j=0;j<strH.nFlds;j++)  System.out.print(sData.get(i)[j] + "  ") ;
				 System.out.println() ;
			 } 

		 mrt1H = new InFileHandler("src/resources/data/Dodelighet_2005-2015_1.csv",21) ;
		 System.out.println(mrt1H.nRecs + "  " + mrt1H.nFlds) ;
		 mrt2H = new InFileHandler("src/resources/data/Dodelighet_2005-2015_2.csv",21) ;
		 System.out.println(mrt2H.nRecs + "  " + mrt2H.nFlds) ;
		 List <String[]> s2Data = mrt1H.getStrArr() ; 
		 if (debug==2)
			 for (int i=0;i<mrt1H.nRecs;i++) {
				 for (int j=0;j<mrt1H.nFlds;j++)  System.out.print(s2Data.get(i)[j] + "  ") ;
				 System.out.println() ;
			 }  
		 
	 }



	@Test
	public void verifyDAOAllImpl() throws Exception {
		boolean isValid=true ;
		assertEquals(true,isValid) ;
	}
	

}
