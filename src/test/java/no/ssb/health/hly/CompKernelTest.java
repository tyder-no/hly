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
import java.math.RoundingMode;
import java.text.DecimalFormat;



public class CompKernelTest {
    /* This comment is added to the master branch only  */
    
	static int compYear=2006 ;
	static HandlerHLYData hndlHLY ;
	static ArraysHLY dataPack ;
	static CompHLYKernel compKernel ;

	//@BeforeClass
	public static void setUpBeforeClassFile() throws Exception {
        int debug=0 ;
		//hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2015.properties") ;
		hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2017.properties") ;
		
		dataPack = hndlHLY.getDataPack() ;
		if (debug==1)System.out.println(dataPack.aliveDataF[10]) ;
		compKernel = new CompHLYKernel(dataPack) ;
	}

	@Before
	public  void setUpBeforeFile() throws Exception {
 		//hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2015.properties") ;
 		hndlHLY = new  HandlerHLYData(compYear,1,"src/resources/hlyfiles_2017.properties") ;
		
		dataPack = hndlHLY.getDataPack() ;
    	compKernel = new CompHLYKernel(dataPack) ;
	}

	
	public void compHLYAllYrs() throws Exception {
		DecimalFormat df = new DecimalFormat("#.0");
		df.setRoundingMode(RoundingMode.CEILING);
		double [] allHLY1 = compKernel.compHLYAllYrs(1) ;
		double [] allHLY2 = compKernel.compHLYAllYrs(2) ;

	    System.out.println("CompYear: " + compYear ) ; 
		for (int i=0;i<90;i=i+10)
		   System.out.println("1 - " + i + ": "+ df.format(allHLY1[i])) ;
		for (int i=0;i<90;i=i+10)
			   System.out.println("2 - " + i + ": "+ df.format(allHLY2[i])) ;
		
	}
	
	public void compHLY1Yr(int cYr) throws Exception {
		//hndlHLY = new  HandlerHLYData(cYr,1,"src/resources/hlyfiles_2015.properties") ;
		hndlHLY = new  HandlerHLYData(cYr,1,"src/resources/hlyfiles_2017.properties") ;
			
		dataPack = hndlHLY.getDataPack() ;
    	compKernel = new CompHLYKernel(dataPack) ;
	
		DecimalFormat df = new DecimalFormat("#.0");
		df.setRoundingMode(RoundingMode.CEILING);
		double [] allHLY1 = compKernel.compHLYAllYrs(1) ;
		double [] allHLY2 = compKernel.compHLYAllYrs(2) ;

	    System.out.print( cYr + ";" + "1;"  ) ; 
	    for (int i=0;i<90;i=i+10) System.out.print(df.format(allHLY1[i])+";") ;
	    System.out.println() ;
		//System.out.println(df.format(allHLY1[0])+";"+df.format(allHLY1[50])+";"+df.format(allHLY1[65])+";") ;
	    System.out.print( cYr + ";" + "2;"  ) ; 
	    for (int i=0;i<90;i=i+10) System.out.print(df.format(allHLY2[i])+";") ;
	    System.out.println() ;
		//System.out.println(df.format(allHLY2[0])+";"+df.format(allHLY2[50])+";"+df.format(allHLY2[65])+";") ;
			
		
		
	}
	
	@Ignore
	@Test
	public void checkCompHLY1() throws Exception {
		boolean isValid=true ;
		compHLYAllYrs() ;
		compYear = 2007 ;
		assertEquals(true,isValid) ;
	}
	

	@Ignore
	@Test
	public void checkCompHLY2() throws Exception {
		boolean isValid=true ;
		compHLYAllYrs() ;
		compYear = 2008 ;
		assertEquals(true,isValid) ;
	}
	
	@Ignore
	@Test
	public void checkCompHLY3() throws Exception {
		boolean isValid=true ;
		compHLY1Yr(2015) ;
	//	for (int cYr=2006;cYr<2015;cYr++) compHLY1Yr(cYr) ;
	//	for (int cYr=2006;cYr<2017;cYr++) compHLY1Yr(cYr) ;
		
		assertEquals(true,isValid) ;
	}
	
	
	@Test
	public void checkCompHLY4() throws Exception {
		boolean isValid=true ;
	//	for (int cYr=2006;cYr<2015;cYr++) compHLY1Yr(cYr) ;
		for (int cYr=2006;cYr<2017;cYr++) compHLY1Yr(cYr) ;
		
		assertEquals(true,isValid) ;
	}
	
	
}
