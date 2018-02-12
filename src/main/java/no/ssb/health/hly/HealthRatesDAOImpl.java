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

public class HealthRatesDAOImpl implements HealthRatesDAO {
	
   //list is working as a database
   List<HealthRates> hlthRs;

   public HealthRatesDAOImpl(){
      hlthRs = new ArrayList<HealthRates>();
   
		
   }

  // @Override
   public void deleteHealthRates(HealthRates hlthR) {
      hlthRs.remove(hlthR.getId());
      System.out.println("HealthRates:  " + hlthR.getId() + ", deleted from database");
   }

   //retrive list of hlthRs from the database
 //  @Override
   public List<HealthRates> getAllHealthRates() {
      return hlthRs;
   }

 //  @Override
   public HealthRates getHealthRates(int id) {
      return hlthRs.get(id);
   }

  // @Override
   public HealthRates getHealthRates(int ageGrp, int dataYear, int sex) {
       int id=1 ;
      return hlthRs.get(id);
   }

  // @Override
   public void updateHealthRates(HealthRates hlthR) {
       //   hlthRs.get(hlthR.getRollNo()).setName(hlthR.getName());
       //System.out.println("HealthRates: Roll No " + hlthR.getRollNo() + ", updated in the database");
   }

 public static void main(String[] args) {

     HealthRatesDAOImpl hRDI = new HealthRatesDAOImpl() ;
      
     HealthRates hlthR1 = new HealthRates(1,2010,1,120,0.3);
     HealthRates hlthR2 = new HealthRates(2,2010,1,130,0.6);
     HealthRates hlthR3 = new HealthRates(3,2010,1,140,1.5);
     HealthRates hlthR4 = new HealthRates(4,2010,1,159,3.0);
     HealthRates hlthR5 = new HealthRates(5,2010,1,160,10.6);
  
      hRDI.hlthRs.add(hlthR1);
      hRDI.hlthRs.add(hlthR2);
      hRDI.hlthRs.add(hlthR3);
      hRDI.hlthRs.add(hlthR4);
      hRDI.hlthRs.add(hlthR5);
 
      for (int i=0; i<5; i++) {

	  HealthRates iI = hRDI.getHealthRates(i) ; 
	  System.out.println("Id:" + iI.getId() + "  Frac:" + iI.getFracHealthy()    ) ;
      }

     /*
      final int MAXRATES = 10 ;
      HealthRates[] hlthRates = new HealthRates[MAXRATES] ;

      for (int i=0; i<MAXRATES; i++) {
	  String sI = "grp" + Integer.toString(i) ;  double frc = (1+i*1.0/100) ;
	  hlthRates[i] = new HealthRates(i,"grp" + Integer.toString(i),2010,1,frc) ;
          System.out.println(sI+" "+ frc) ;
	  System.out.println("HealthRates " + i + " AgeGrp: " + hlthRates[i].getAgeGrpLab() +"  Id: "+ hlthRates[i].getId() + "  Frac:" + hlthRates[i].getFracHealthy()) ; 
      }
     */

     
   }
 


}