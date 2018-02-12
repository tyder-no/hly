/**

For computing healthy life years. Magnar Lillegård's method

First impl. 20120720 taydersbond
20150925

*/
package no.ssb.health.hly;



public class CompHLYMain
{
   // private static final int FSTATYEAR = 2006 ; // First year published surv. data
   // private static final int LSTATYEAR = 2014 ; // Last year publ surv. data

    public static final int MAX_YEAR = 106 ; // Oldest age to compute for
    int compYr ;  // An instance of the class is initialized with the actual year to compute for
    String strFileName ;

    CompHLYKernel compKernel ;
    HandlerHLYData handlerHLY ;
    ArraysHLY dataPack ;
    
    double[] aliveDataM,aliveDataF,deadDataM,deadDataF,healthDataM,healthDataF,instDataM,instDataF, hlyDataM, hlyDataF ;  // [0..MAX_YEAR+1] arrays for input data

    public ArraysHLY getDataPack() { return dataPack ;  }  
    public void setDataPack(ArraysHLY dataPack) { this.dataPack  = dataPack ;  }  
      
    void unpackDataPack(ArraysHLY dataPack) {
		this.aliveDataM  =  dataPack.aliveDataM ; this.aliveDataF  =  dataPack.aliveDataF ; 
		this.deadDataM  =  dataPack.deadDataM ; this.deadDataF  =  dataPack.deadDataF ; 
		this.healthDataM  =  dataPack.healthDataM ; this.healthDataF  =  dataPack.healthDataF ; 
		this.instDataM  =  dataPack.instDataM ;   this.instDataF  =  dataPack.instDataF ; 
		this.hlyDataM  =  dataPack.hlyDataM ;    this.hlyDataF  =  dataPack.hlyDataF ; 
	}
    
    CompHLYMain(int compYr) {
        this.compYr = compYr ;
        this.handlerHLY = new HandlerHLYData(compYr) ;
        dataPack = handlerHLY.getDataPack() ; 
         unpackDataPack(dataPack) ;
        
    	this.compKernel = new  CompHLYKernel(dataPack) ;
        
    
    }
    
    
    
    public static void main(String[] args)  {   
    	int compYear=2010 ; int debug=0 ;

    	HandlerHLYData hndlHLY = new  HandlerHLYData(compYear) ;
    	//HandlerHLYData hndlHLY = new  HandlerHLYData(compYear) ;
    	ArraysHLY dataPack = hndlHLY.getDataPack() ;
    	if (debug==1)System.out.println(dataPack.aliveDataF[1]) ;
        } 
    
   
}
