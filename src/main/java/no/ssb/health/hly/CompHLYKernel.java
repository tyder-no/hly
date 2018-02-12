/**

For computing healthy life years. Magnar Lillegård's method

First impl. 20120720 taydersbond
20150920

*/
package no.ssb.health.hly;






public class CompHLYKernel
{
   
    public static final int MAX_YEAR = 106 ; // Oldest age to compute for
    int compYr ;  // An instance of the class is initialized with the actual year to compute for
    String strFileName ;


    double[] aliveDataM,aliveDataF,deadDataM,deadDataF,healthDataM,healthDataF,instDataM,instDataF, hlyDataM, hlyDataF ;  // [0..MAX_YEAR+1] arrays for input data
    ArraysHLY dataPack ;
    // Data for each gender, plus all
 //   SurvData sDataM, sDataF ;   //  Objects with survival data, from mortality tables
 //   HealthData hDataM, hDataF ; // Objects with health data from EU-SILC etc
 //   InstData iData ;   // Object with institution occupancy rate data

   

    private static int debug=0 ;

 
    public ArraysHLY getDataPack() { return dataPack ;  }  
    public void setDataPack(ArraysHLY dataPack) { this.dataPack  = dataPack ;  }  
    
    void unpackDataPack(ArraysHLY dataPack) {
		this.aliveDataM  =  dataPack.aliveDataM ; this.aliveDataF  =  dataPack.aliveDataF ; 
		this.deadDataM  =  dataPack.deadDataM ; this.deadDataF  =  dataPack.deadDataF ; 
		this.healthDataM  =  dataPack.healthDataM ; this.healthDataF  =  dataPack.healthDataF ; 
		this.instDataM  =  dataPack.instDataM ;   this.instDataF  =  dataPack.instDataF ; 
		this.hlyDataM  =  dataPack.hlyDataM ;    this.hlyDataF  =  dataPack.hlyDataF ; 
	}
  
    
 // Computing expected life years
  
    public double compELY1Yr(int xYr,double[] aliveD,double[] deadD,double[] healthyD,double[] instD )  {
        double pAcc=0 ; int fYr ; double dpAcc ;
             
        if (xYr<0) xYr = 0 ;    if (xYr==0) fYr = 1 ; else fYr = xYr ;
        if (debug>0) System.out.println("From age: " + xYr) ;  
        for (int i=fYr;i<=MAX_YEAR;i++) {
	   dpAcc = (aliveD[i] - 0.5*deadD[i]) ; 
           pAcc += dpAcc ;  
           if (debug>1) System.out.println(i + ": " + dpAcc) ;
        }  
        if (xYr==0) return ( (pAcc + (aliveD[0]-0.8*deadD[0]))/aliveD[0]) ;
	    else return( pAcc/aliveD[xYr]) ;
    }


    // Implementing Magnar Lillegårds algorithm for computing healthy life years
  
    public double compHLY1Yr(int xYr,double[] aliveD,double[] deadD,double[] healthyD,double[] instD )  {
    	double pAcc=0 ; int fYr ; double dpAcc ;

    	if (xYr<0) xYr = 0 ;    if (xYr==0) fYr = 1 ; else fYr = xYr ;
    	if (debug>0) System.out.println("From age: " + xYr) ;  
    	for (int i=fYr;i<=MAX_YEAR;i++) {
    		dpAcc = (aliveD[i] - 0.5*deadD[i])*(100-instD[i])*healthyD[i]/10000 ; 
    		pAcc += dpAcc ;  
    		if (debug>1) System.out.println(i + ": " + dpAcc) ;
    	}  
    	if (xYr==0) return ( (pAcc + (aliveD[0]-0.8*deadD[0])*healthyD[0]/100)/aliveD[0] ) ;
    	else return( pAcc/aliveD[xYr]) ;
    }



    public double [] compHLYAllYrs(double[] aliveD,double[] deadD,double[] healthyD,double[] instD )  {
    	double [] compHLYRes = new double[MAX_YEAR+1] ;

    	for (int i=0;i<=MAX_YEAR;i++) {
    		compHLYRes[i] = compHLY1Yr(i,aliveD,deadD,healthyD,instD) ;  

    	}
    	return(compHLYRes) ;  

    }

    public double [] compHLYAllYrs(int sex )  {
    	double [] compHLYRes = new double[MAX_YEAR+1] ;

    	if (sex==1)          
    		for (int i=0;i<=MAX_YEAR;i++) {
    			compHLYRes[i] = compHLY1Yr(i,aliveDataM,deadDataM,healthDataM,instDataM) ;  
    		}
    	else
    		for (int i=0;i<=MAX_YEAR;i++) {
    			compHLYRes[i] = compHLY1Yr(i,aliveDataF,deadDataF,healthDataF,instDataF) ;  
    		}
    	return(compHLYRes) ;  

    }

    // CompHLYKernel(MortalityRatesDAO mortRates, InstRatesDAO instRates, HealthRatesDAO hlthRates) {
    CompHLYKernel(double[] aliveDataM,double[] aliveDataF,double[] deadDataM,double[] deadDataF,double[] healthDataM,double[] healthDataF,double[]instDataM,double[] instDataF) {
 
        this.aliveDataM = aliveDataM ;   this.aliveDataF = aliveDataF ;
        this.deadDataM = deadDataM ;   this.deadDataF = deadDataF ;
        this.healthDataM = healthDataM ;   this.healthDataF = healthDataF ;

    } 

    CompHLYKernel(ArraysHLY dataPack) {
    	
    	this.dataPack = dataPack ;
    	unpackDataPack(dataPack) ;
    }


}




