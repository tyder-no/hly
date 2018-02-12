/**



 */
package no.ssb.health.hly;

import java.io.* ;
import java.util.* ;
import java.util.ArrayList;
import java.util.List;



class Converter {

// Converts string to int
   static int getIntValue(String s) 
   {  try
      {  return(Integer.parseInt(s.trim())) ; 
      }
      catch(NumberFormatException e) 
      { return(0) ;
      }
   }

// Converts string to double
   static double getDoubleValue(String s) 
   {  try
      {  return(Double.valueOf(s.trim()).doubleValue()) ; 
      }
      catch(NumberFormatException e) 
      { return(0.0) ;
      }
   }

    // Simplistic filehandler for input files in the HLY project
    // Data files are semicolon-separated, and have a number of unused columns,
    // the first data column being #fFld, the total number being nFlds  

}

class StringArray {

    public static final int MAX_FIELDS = 256 ;
    public static String [] getStringArray() { return new String[MAX_FIELDS] ; } 

}


public class InFileHandler
{
    int nFlds = 0 ;  int dimVect = 0 ; int fFld = 0 ; int nRecs ; int isText=0 ;
    public static final int MAX_ROWS = 5000 ;  
    String strFileName ;
    static String fldDelim=";" ; // delimiter for description
    int debug=0 ;
    double [][] dblArr ;
    List<String[]> strArr ;

  
    double[][] getDblArr() 
    {  if (isText==1) return null ;
        double[][] dblRet = new double[nRecs][nFlds] ;
	for (int i=0;i<nRecs;i++)
          for (int j=0;j<nFlds;j++)
           dblRet[i][j] = dblArr[i][j] ;
       return (dblRet) ;

    }

   List<String[]> getStrArr() 
    {  if (isText==0) return null ;
        return strArr ;

    }


    // Constructor for reading number tables

    public InFileHandler(String strFNm, int nInFlds, int fInFld)
    {  BufferedReader inf  ;  nRecs = 0 ;  isText=0 ;
	nFlds = nInFlds ; fFld = fInFld ; 
	double [][] tmpArr = new double[MAX_ROWS][nFlds] ;

	try {
	    inf = new BufferedReader(new FileReader(strFNm)) ;
	    strFileName = strFNm ;
	
	    String dLine ; String junk ;
	    try {
		while ((dLine=inf.readLine()) != null) {
		    StringTokenizer flds = new StringTokenizer(dLine,fldDelim) ; 
		    for (int i=1;i<fFld;i++) { 
		    	junk = flds.nextToken() ; 
		    	if (debug==1)System.out.println(junk) ;
		    }
		    for (int i=0;i<nFlds;i++) tmpArr[nRecs][i] =  Converter.getDoubleValue(flds.nextToken().trim()) ; 
		    nRecs++ ;
		}
		inf.close() ;
            // System.out.println( " nRecs: " + nRecs + "Last line:  " + tmpArr[nRecs-1][nFlds-2] + " " + tmpArr[nRecs-1][nFlds-1] ) ; 
	    }
	    catch (Exception e)   {
		e.printStackTrace() ;
	    }


	    dblArr = new double[nRecs][nFlds] ;
 	    for (int i=0;i<nRecs;i++)
		for (int j=0;j<nFlds;j++)   dblArr[i][j] =  tmpArr[i][j] ;
            // System.out.println( " nRecs: " + nRecs + "Last line:  " + dblArr[nRecs-1][nFlds-2] + " " + dblArr[nRecs-1][nFlds-1] ) ; 

        }
	catch(FileNotFoundException e) {
	    e.printStackTrace() ;
	}                            
    }


    // Constructor for text or unconverted fields 

    public InFileHandler(String strFNm,int nInFlds)
    {   int debug=0 ;
    	BufferedReader inf  ;  nRecs = 0 ;  isText=1 ; nFlds = nInFlds ;
          strArr = new ArrayList<String[]>();


          try {
        	  inf = new BufferedReader(new FileReader(strFNm)) ;
        	  strFileName = strFNm ;

        	  String dLine ;  String[] lneFld ;
        	  try {
        		  while ((dLine=inf.readLine()) != null && (dLine.length()>0)) {
        			  nRecs++ ;
        			  lneFld = StringArray.getStringArray() ; 
        			  StringTokenizer flds = new StringTokenizer(dLine,fldDelim) ;
        			  if (debug==1) {
        				 StringTokenizer cflds = new StringTokenizer(dLine,fldDelim) ;
        				 System.out.println( " nRecs: " + nRecs + "   " + dLine) ;
        				 for (int i=0;i<nInFlds;i++) System.out.print(cflds.nextToken()+ "  ") ; 
        				 System.out.println() ; 
        			  }
        			  for (int i=0;i<nInFlds;i++) lneFld[i] = flds.nextToken() ; 
        			  strArr.add(lneFld) ;
        		  }
        		  inf.close() ;
        		  // System.out.println( " nRecs: " + nRecs + "Last line:  " + tmpArr[nRecs-1][nFlds-2] + " " + tmpArr[nRecs-1][nFlds-1] ) ; 
        	  }
        	  catch (Exception e)   {
        		  e.printStackTrace() ;
        	  }

        }
	catch(FileNotFoundException e) {
	    e.printStackTrace() ;
	}                            
    }



    public static void main(String[] args) throws FileNotFoundException
    {
    	int itest=1 ;

    	if (itest>0) {

    		InFileHandler fct = new InFileHandler("data/hly_2010_eusilc_1.scv",2,3) ;

    		double[][] iData = fct.getDblArr() ; 
    		System.out.println(fct.nRecs + "  " + fct.nFlds) ;
    		for (int i=0;i<fct.nRecs;i++) {
    			for (int j=0;j<fct.nFlds;j++)  System.out.print(iData[i][j] + "  ") ;
    			System.out.println() ;
    		} 

    		InFileHandler strH = new InFileHandler("data/hly_2010_eusilc_1.scv",4) ;

    		List <String[]> sData = strH.getStrArr() ; 
    		System.out.println(strH.nRecs + "  " + strH.nFlds) ;
    		for (int i=0;i<strH.nRecs;i++) {
    			for (int j=0;j<strH.nFlds;j++)  System.out.print(sData.get(i)[j] + "  ") ;
    			System.out.println() ;
    		} 



    	}
    }




}
