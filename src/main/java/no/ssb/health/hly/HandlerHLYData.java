/**

HandlerHLYData - fetches and prepares input data for HLY calculations 

20150922 taydersbond


*/
package no.ssb.health.hly;

import java.io.* ;
import java.util.* ;


// For simple handling of the whole HLY data package
class ArraysHLY {

	int debug=1 ; 
	double[] aliveDataM,aliveDataF,deadDataM,deadDataF,healthDataM,healthDataF,instDataM,instDataF, hlyDataM, hlyDataF ;  // [0..MAX_YEAR+1] arrays for input data

	public ArraysHLY () {		}
	
	public ArraysHLY (double[] aliveDataM,double[] aliveDataF,double[] deadDataM,double[] deadDataF,double[] healthDataM,double[] healthDataF,double[] instDataM,double[] instDataF,double[] hlyDataM,double[] hlyDataF)  {
		this.aliveDataM  =  aliveDataM ; this.aliveDataF  =  aliveDataF ; 
		this.deadDataM  =  deadDataM ; this.deadDataF  =  deadDataF ; 
		this.healthDataM  =  healthDataM ; this.healthDataF  =  healthDataF ; 
		this.instDataM  =  instDataM ;   this.instDataF  =  instDataF ; 
		this.hlyDataM  =  hlyDataM ;    this.hlyDataF  =  hlyDataF ; 
	}

	public ArraysHLY(ArraysHLY dataPack){
		this.aliveDataM  =  dataPack.aliveDataM ; this.aliveDataF  =  dataPack.aliveDataF ; 
		this.deadDataM  =  dataPack.deadDataM ; this.deadDataF  =  dataPack.deadDataF ; 
		this.healthDataM  =  dataPack.healthDataM ; this.healthDataF  =  dataPack.healthDataF ; 
		this.instDataM  =  dataPack.instDataM ;   this.instDataF  =  dataPack.instDataF ; 
		this.hlyDataM  =  dataPack.hlyDataM ;    this.hlyDataF  =  dataPack.hlyDataF ; 
	}

	void unpackDataPack(ArraysHLY dataPack) {
		this.aliveDataM  =  dataPack.aliveDataM ; this.aliveDataF  =  dataPack.aliveDataF ; 
		this.deadDataM  =  dataPack.deadDataM ; this.deadDataF  =  dataPack.deadDataF ; 
		this.healthDataM  =  dataPack.healthDataM ; this.healthDataF  =  dataPack.healthDataF ; 
		this.instDataM  =  dataPack.instDataM ;   this.instDataF  =  dataPack.instDataF ; 
		this.hlyDataM  =  dataPack.hlyDataM ;    this.hlyDataF  =  dataPack.hlyDataF ; 
	}

	ArraysHLY getDataPack() {
		ArraysHLY dataPack = new ArraysHLY() ;  
		dataPack.aliveDataM  =  this.aliveDataM ; dataPack.aliveDataF  =  this.aliveDataF ; 
		dataPack.deadDataM  =  this.deadDataM ; dataPack.deadDataF  =  this.deadDataF ; 
		dataPack.healthDataM  =  this.healthDataM ; dataPack.healthDataF  =  this.healthDataF ; 
		dataPack.instDataM  =  this.instDataM ;   dataPack.instDataF  =  this.instDataF ; 
		dataPack.hlyDataM  =  this.hlyDataM ;   dataPack.hlyDataF  =  this.hlyDataF ; 

		return dataPack ;
	}


}


public class HandlerHLYData
{
    private static final int FSTATYEAR = 2006 ; // First year published surv. data
  //  private static final int LSTATYEAR = 2014 ; // Last year publ surv. data
    private static final int LSTATYEAR = 2016 ; // Last year publ surv. data
    public static final int MAX_YEAR = 106 ; // Oldest age to compute for
    static int debug = 1 ;

    InstRatesDAO instRates ; 
    MortalityRatesDAO mortRates ;
    HealthRatesDAO hlthRates ;
    ArraysHLY dataPack ;

    int compYr  ;  // An instance of the class is initialized with the actual year to compute for
    String strFileName ;
    double[] aliveDataM,aliveDataF,deadDataM,deadDataF,healthDataM,healthDataF,instDataM,instDataF, hlyDataM, hlyDataF ;  // [0..MAX_YEAR+1] arrays for input data

/**
 * Simple generic properties loading routine. Called from constructors
 * Different properties files may be used for different data source setups
 *
 * @param propFnm
 * @return props
 */
    protected Properties readProperties(String propFnm) {
    	try {
    		Properties props = new Properties() ;
    		FileInputStream in = new FileInputStream(propFnm) ;
    		props.load(in) ;
    		in.close() ;
    		return props ;
    	}
    	catch (Exception e) {
    		
    		e.printStackTrace();
    	}
    	
    	return null ;
    	
    }
    
  
    /**
     * Basic constructor using only year as dataset identifier
     * 
     * @param compYear
     * @param dataSrc
     * @param propFnm
     */
    
    public HandlerHLYData(int compYear,int dataSrc,String propFnm) {

    	Properties dataProps ;

    	if ((compYear<FSTATYEAR)||(compYear>LSTATYEAR)) return ;
    	String compYearStr = Integer.toString(compYear) ;
    	dataProps=readProperties(propFnm) ;
    	if (dataProps==null ) return ;
    	if (dataSrc==1) {  // read from files
    		String dataDir = dataProps.getProperty("dataDirectory")  ;
    		// Beware hard-coding of institution data file name!!
    		String instDataFile =  dataProps.getProperty("instRates.2010")  ;
    		// Beware hard-coding of mortality data file names here!!
    		//String mortDataFile1 =  dataProps.getProperty("mortRates.m.2005.2015")  ; 
    		//String mortDataFile2 =  dataProps.getProperty("mortRates.f.2005.2015")  ; 
    		String mortDataFile1 =  dataProps.getProperty("mortRates.m.2005.2016")  ; 
    		String mortDataFile2 =  dataProps.getProperty("mortRates.f.2005.2016")  ; 
    
    		// One year files for health data 
    		String healthDataFile1 =  dataProps.getProperty("healthRates.m."+compYearStr)  ;
    		String healthDataFile2 =  dataProps.getProperty("healthRates.f."+compYearStr)  ;
    		instRates = new InstRatesDAOFileImpl(2010,dataDir+instDataFile) ;
    	//	mortRates = new MortalityRatesDAOFileImpl(2005,10,dataDir+mortDataFile1,dataDir+mortDataFile2) ;
    		mortRates = new MortalityRatesDAOFileImpl(2005,12,dataDir+mortDataFile1,dataDir+mortDataFile2) ;
    		
    		hlthRates = new HealthRatesDAOFileImpl(compYear,dataDir+healthDataFile1,dataDir+healthDataFile2) ; 


    	}
    	else if (dataSrc==2) { // read from database, not implemented in this pilot 

    	}
    	else { // default, use 2010-data

    		instRates = new InstRatesDAOFileImpl(compYear,"src/resources/data/inst_2010.scv") ;
    		mortRates = new MortalityRatesDAOFileImpl(2005,10,"src/resources/data/Dodelighet_2005-2015_1.csv","src/resources/data/Dodelighet_2005-2015_2.csv") ;
    		hlthRates = new HealthRatesDAOFileImpl(2010,"src/resources/data/hly_2010_eusilc2_1.scv","src/resources/data/hly_2010_eusilc2_1.scv") ; 
    	}

    	aliveDataM = new double [MAX_YEAR+1] ; aliveDataF = new double [MAX_YEAR+1] ; 
    	deadDataM = new double [MAX_YEAR+1] ; deadDataF = new double [MAX_YEAR+1] ;
    	healthDataM  = new double [MAX_YEAR+1] ; healthDataF  = new double [MAX_YEAR+1] ;
    	instDataM  = new double [MAX_YEAR+1]; instDataF = new double [MAX_YEAR+1] ;
    	hlyDataM  = new double [MAX_YEAR+1] ; hlyDataF  = new double [MAX_YEAR+1] ;  // [0..MAX_YEAR+1] 

    	dataPack = new ArraysHLY(aliveDataM,aliveDataF,deadDataM,deadDataF,healthDataM,healthDataF,instDataM,instDataF, hlyDataM, hlyDataF) ; 
    	mkInstArrays(2010)  ;
    	mkHealthArrays(compYear)  ;
    	mkMortalityArrays(compYear)  ;
    }

/**
 * Constructor used for computations for the different values of one background variable 
 * @param compYear
 * @param dataSrc
 * @param propFnm
 * @param varNm
 * @param varVal
 */
    public HandlerHLYData(int compYear,int dataSrc,String propFnm,String varNm,String varVal) {

    	Properties dataProps ;

    	if ((compYear<FSTATYEAR)||(compYear>LSTATYEAR)) return ;
    	String compYearStr = Integer.toString(compYear) ;
    	dataProps=readProperties(propFnm) ;
    	if (dataProps==null ) return ;
    	if (dataSrc==1) {  // read from files
    		String dataDir = dataProps.getProperty("dataDirectory")  ;
    		// Beware hard-coding of institution data file name!!
    		String instDataFile =  dataProps.getProperty("instRates.2010")  ;
    		// Beware hard-coding of mortality data file names here!!
    		String mortDataFile1 =  dataProps.getProperty("mortRates.m.2005.2015")  ; 
    		String mortDataFile2 =  dataProps.getProperty("mortRates.f.2005.2015")  ; 
    		// One year files for health data 
    		String healthDataFile1 =  dataProps.getProperty("healthRates.m."+varNm+"."+varVal+"."+compYearStr)  ;
    		String healthDataFile2 =  dataProps.getProperty("healthRates.f."+varNm+"."+varVal+"."+compYearStr)  ;
    		instRates = new InstRatesDAOFileImpl(2010,dataDir+instDataFile) ;
    		mortRates = new MortalityRatesDAOFileImpl(2005,10,dataDir+mortDataFile1,dataDir+mortDataFile2) ;
    		hlthRates = new HealthRatesDAOFileImpl(compYear,dataDir+healthDataFile1,dataDir+healthDataFile2) ; 


    	}
    	else if (dataSrc==2) { // read from database, not implemented in this pilot 

    	}
    	else { // default, use 2010-data

    		instRates = new InstRatesDAOFileImpl(compYear,"src/resources/data/inst_2010.scv") ;
    		mortRates = new MortalityRatesDAOFileImpl(2005,10,"src/resources/data/Dodelighet_2005-2015_1.csv","src/resources/data/Dodelighet_2005-2015_2.csv") ;
    		hlthRates = new HealthRatesDAOFileImpl(2010,"src/resources/data/hly_2010_eusilc2_1.scv","src/resources/data/hly_2010_eusilc2_1.scv") ; 
    	}

    	aliveDataM = new double [MAX_YEAR+1] ; aliveDataF = new double [MAX_YEAR+1] ; 
    	deadDataM = new double [MAX_YEAR+1] ; deadDataF = new double [MAX_YEAR+1] ;
    	healthDataM  = new double [MAX_YEAR+1] ; healthDataF  = new double [MAX_YEAR+1] ;
    	instDataM  = new double [MAX_YEAR+1]; instDataF = new double [MAX_YEAR+1] ;
    	hlyDataM  = new double [MAX_YEAR+1] ; hlyDataF  = new double [MAX_YEAR+1] ;  // [0..MAX_YEAR+1] 

    	dataPack = new ArraysHLY(aliveDataM,aliveDataF,deadDataM,deadDataF,healthDataM,healthDataF,instDataM,instDataF, hlyDataM, hlyDataF) ; 
    	mkInstArrays(2010)  ;
    	mkHealthArrays(compYear)  ;
    	mkMortalityArrays(compYear)  ;
    }

  
    
    public HandlerHLYData(int compYear) {
        this(compYear,0,"src/resources/hlyfiles_2015.properties") ;  
    }

    void mkInstArrays(int year) {
    	final int[] INST_GROUPS = {61,66,71,76,81,MAX_YEAR+1} ;
    	double instRM, 	instRF ;
    	for (int i=0;i<INST_GROUPS[0];i++) {
    		instDataM[i] = 0 ; instDataF[i] = 0 ;
    	}
    	for (int agrp = 1 ; agrp < INST_GROUPS.length ; agrp++)
    		for (int i=INST_GROUPS[agrp-1];i<INST_GROUPS[agrp];i++) {
    			instRM = instRates.getInstRates(agrp, year, 1).getFracInInst() ;  instRF = instRates.getInstRates(agrp, year, 2).getFracInInst() ;   
    			instDataM[i] = instRM ; instDataF[i] = instRF ;

    		}

    }

    void mkHealthArrays(int year) {
    	final int[] HEALTH_GROUPS = {16+1,20+1,25+1,30+1,35+1,40+1,45+1,50+1,55+1,60+1,65+1,70+1,75+1,80+1,MAX_YEAR+1} ;
    	double hlthRM, 	hlthRF ;
    	hlthRM = hlthRates.getHealthRates(1, year, 1).getFracHealthy() ;   hlthRF = hlthRates.getHealthRates(1, year, 2).getFracHealthy() ;
    	for (int i=0;i<HEALTH_GROUPS[0];i++) {
    		healthDataM[i] = 1/2+hlthRM/2 ; healthDataF[i] =  1/2+hlthRF/2 ;
    	}
    	for (int agrp = 1; agrp<HEALTH_GROUPS.length;  agrp++)
    		for (int i=HEALTH_GROUPS[agrp-1];i<HEALTH_GROUPS[agrp];i++) {
    			hlthRM = hlthRates.getHealthRates(agrp, year, 1).getFracHealthy() ;  hlthRF = hlthRates.getHealthRates(agrp, year, 2).getFracHealthy() ;
    			healthDataM[i] = hlthRM ; healthDataF[i] = hlthRF ;

    		}

    }


    void mkMortalityArrays(int year) {

    	double deadRM, 	deadRF, aliveRM, aliveRF ;
    	for (int i=0;i<MAX_YEAR+1;i++) {

    		deadRM = mortRates.getMortalityRates(i, year, 1).getNDead() ; deadRF = mortRates.getMortalityRates(i, year, 2).getNDead() ;
    		aliveRM = mortRates.getMortalityRates(i, year, 1).getNAlive() ; aliveRF = mortRates.getMortalityRates(i, year, 2).getNAlive() ;

    		deadDataM[i] = deadRM ; deadDataF[i] = deadRF ;
    		aliveDataM[i] = aliveRM ; aliveDataF[i] = aliveRF ;


    	}

    }

    InstRatesDAO  getInstRates() { return instRates ; }
    MortalityRatesDAO getMortRates() { return mortRates ; }
    HealthRatesDAO getHlthRates() { return hlthRates ; }

    ArraysHLY getDataPack() { return dataPack ;  }
    
    void setDataPack(ArraysHLY dataPack) { this.dataPack=dataPack ;  }
  
    void setInstRates(InstRatesDAO instRates) { this.instRates = instRates; }
    void setMortRates(MortalityRatesDAO mortRates) { this.mortRates =mortRates ; }
    void setHlthRates(HealthRatesDAO hlthRates) { this.hlthRates=hlthRates ; }

    
    void unpackDataPack() {
    	this.aliveDataM  =  dataPack.aliveDataM ; this.aliveDataF  =  dataPack.aliveDataF ; 
    	this.deadDataM  =  dataPack.deadDataM ; this.deadDataF  =  dataPack.deadDataF ; 
    	this.healthDataM  =  dataPack.healthDataM ; this.healthDataF  =  dataPack.healthDataF ; 
    	this.instDataM  =  dataPack.instDataM ;   this.instDataF  =  dataPack.instDataF ; 
    	this.hlyDataM  =  dataPack.hlyDataM ;    this.hlyDataF  =  dataPack.hlyDataF ; 

    }

    void packDataPack() {
    	dataPack.aliveDataM  =  this.aliveDataM ; dataPack.aliveDataF  =  this.aliveDataF ; 
    	dataPack.deadDataM  =  this.deadDataM ; dataPack.deadDataF  =  this.deadDataF ; 
    	dataPack.healthDataM  =  this.healthDataM ; dataPack.healthDataF  =  this.healthDataF ; 
    	dataPack.instDataM  =  this.instDataM ;   dataPack.instDataF  =  this.instDataF ; 
    	dataPack.hlyDataM  =  this.hlyDataM ;   dataPack.hlyDataF  =  this.hlyDataF ; 


    }

    public static void main(String[] args) throws FileNotFoundException  {   
    	int compYear=2010 ; 

    	HandlerHLYData hndlHLY = new  HandlerHLYData(compYear) ;
    	ArraysHLY dataPack = hndlHLY.getDataPack() ;
    	if (debug==1)System.out.println(dataPack.aliveDataF) ;
    }




}

