package no.ssb.health.hly;

import java.util.ArrayList;
import java.util.List;

//  Constructor: InstRates(int ageGrp, String ageGrpLab, int dataYear, int sex, double fracInInst)

/*
60-64;0.3;0.3;
65-69;0.6;0.5;
70-74;1.5;1.4;
75-79;3.0;3.5;
80+;10.6;16.3;
*/

// Fetches from data file 

public class InstRatesDAOFileImpl extends InstRatesDAOImpl {
	
    int debug = 0 ;
	static String iDatFnm = "src/resources/data/inst_2010.scv" ;
   //list is working as a database
   List<InstRates> instRs;

   
   public InstRatesDAOFileImpl(int year){
	   this(year,iDatFnm) ;
   }

   public InstRatesDAOFileImpl(int year,String iDatFnm){
	   InstRatesDAOFileImpl.iDatFnm = iDatFnm ;
	   instRs = new ArrayList<InstRates>();
	   // Using InFileHandler to read and parse scv file 
	   InFileHandler iF = new InFileHandler(iDatFnm,3) ;
	   List<String[]> instData = iF.getStrArr();
	   if (debug==1) System.out.println("InstRates,nrecs: " + iF.nRecs) ;
	   int nRecs = iF.nRecs ;
	   // Creating the list of inst data
	   for (int i=0 ; i<nRecs;i++) {
		   InstRates instRM = new InstRates(i+1,instData.get(i)[0],year,1,Converter.getDoubleValue(instData.get(i)[1])) ;
		   InstRates instRF = new InstRates(i+1,instData.get(i)[0],year,2,Converter.getDoubleValue(instData.get(i)[2]))  ;
		   instRs.add(instRM) ; instRs.add(instRF) ; 
		   if (debug==1) System.out.println("InstRates size:" + instRs.size()) ; 
	   }
	   if (debug==1) {
		   for (int i=0 ; i<instRs.size();i++)
			   System.out.println("InstRates " + i +  "  Year" +  instRs.get(i).getDataYear() +" AgeGrp: " + instRs.get(i).getAgeGrpLab() +  "  " + instRs.get(i).getAgeGrp()+"  Sex: "+ instRs.get(i).getSex() + "  Frac:" + instRs.get(i).getFracInInst()) ; 		
	   }
   }

   
   @Override
   public void deleteInstRates(InstRates instR) {
      instRs.remove(instR.getId());
      System.out.println("InstRates:  " + instR.getId() + ", deleted from database");
   }

   
   @Override
   public InstRates getInstRates(int id) {
       for (int i=0 ; i<instRs.size();i++) {
	   if  (instRs.get(i).getId()==id) return instRs.get(i);
       }
       return null ; 
   }

   @Override
       public InstRates getInstRates(int ageGrp, int dataYear, int sex) {
       for (int i=0 ; i<instRs.size();i++) {
	   if  ((instRs.get(i).getAgeGrp()==ageGrp) &&(instRs.get(i).getDataYear()==dataYear)&& (instRs.get(i).getSex()==sex)) return instRs.get(i);
       }
       return null ; 
      
   }

   @Override
   public void updateInstRates(InstRates instR) {
       //   instRs.get(instR.getRollNo()).setName(instR.getName());
       //System.out.println("InstRates: Roll No " + instR.getRollNo() + ", updated in the database");
   }

 public static void main(String[] args) {

     InstRatesDAOFileImpl iRDI = new InstRatesDAOFileImpl(2010) ;
     iRDI.getInstRates(1) ;
     
   }
 


}