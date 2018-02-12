package no.ssb.health.hly;

import java.util.ArrayList;
import java.util.List;


//CREATE TABLE selfReportedHealth (ageGrp INT, ageGrpLabel CHAR(10), spLabel CHAR(10), dataYear INT, sex INT, nHealthy DECIMAL(13,5), fracHealthy DECIMAL(13,5) ) ; 
// Constructor: HealthRates(int ageGrp, int dataYear, int sex, double numHealthy, double fracHealthy

/*
16-19 år;1;96944.280751;95.294256432
20-24 år;1;126456.53113;93.272755587
25-29 år;1;123040.82431;87.549145854
30-34 år;1;137496.52789;88.796184026
35-39 år;1;173767.84977;90.58927314
40-44 år;1;172616.11032;89.967298707
45-49 år;1;158785.86757;85.429794525
50-54 år;1;140341.60399;85.72742908
*/



// Fetches from data file 

public class HealthRatesDAOFileImpl extends HealthRatesDAOImpl {
	
    int debug = 0 ;
   //list is working as a database
   List<HealthRates> hlthRs;


  private void mRdF(int year, String fnm1,int sex)  {

        String iDatFnm = fnm1 ;
	// Using InFileHandler to read and parse scv file 
	InFileHandler iF = new InFileHandler(iDatFnm,4) ;
	List<String[]> hlthData = iF.getStrArr();
	if (debug==1) System.out.println("HealthRates,nrecs: " + iF.nRecs) ;
	int nRecs = iF.nRecs ;
	// Creating the list of hlth data
	for (int i=0 ; i< nRecs;i++) {

	    HealthRates hlthR = new HealthRates(i+1,year,sex,Converter.getDoubleValue(hlthData.get(i)[2]),Converter.getDoubleValue(hlthData.get(i)[3])) ;
		hlthRs.add(hlthR) ; 
                 if (debug==1) System.out.println(i + " Year: " + hlthR.getDataYear() + " Age: " + hlthR.getAgeGrp() +  " Sex: " + hlthR.getSex() + " FracHealthy:  " +  hlthR.getFracHealthy() ) ; 
	}


    }

  public HealthRatesDAOFileImpl(int year,String fnm1,String fnm2){
	  hlthRs = new ArrayList<HealthRates>();


	  mRdF(year,fnm1,1)  ; // Data for men
	  mRdF(year,fnm2,2)  ; // Data for women

	  if (debug==1) System.out.println("HealthRates size:" + hlthRs.size() + "Id #0: "+ hlthRs.get(0).getId()) ; 

  }


   @Override
   public void deleteHealthRates(HealthRates hlthR) {
      hlthRs.remove(hlthR.getId());
      System.out.println("HealthRates:  " + hlthR.getId() + ", deleted from database");
   }

   
   @Override
   public HealthRates getHealthRates(int id) {
       for (int i=0 ; i<hlthRs.size();i++) {
	   if  (hlthRs.get(i).getId()==id ) return hlthRs.get(i);
       }
       return null ; 
   }

   @Override
       public HealthRates getHealthRates(int ageGrp, int dataYear, int sex) {
       for (int i=0 ; i<hlthRs.size();i++) {
	   if  ((hlthRs.get(i).getAgeGrp()==ageGrp) &&(hlthRs.get(i).getDataYear()==dataYear)&& (hlthRs.get(i).getSex()==sex)) return hlthRs.get(i);
       }
       return null ; 
      
   }

   @Override
   public void updateHealthRates(HealthRates hlthR) {
       //   hlthRs.get(hlthR.getRollNo()).setName(hlthR.getName());
       //System.out.println("HealthRates: Roll No " + hlthR.getRollNo() + ", updated in the database");
   }

 public static void main(String[] args) {

     HealthRatesDAOFileImpl hRDI = new HealthRatesDAOFileImpl(2010,"data/hly_2010_eusilc2_1.scv","data/hly_2010_eusilc2_1.scv") ;
     hRDI.getHealthRates(1) ;
     
   }
 


}




