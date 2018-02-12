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

public class InstRatesDAOImpl implements InstRatesDAO {
	
   //list is working as a database
   List<InstRates> instRs;

   public InstRatesDAOImpl(){
      instRs = new ArrayList<InstRates>();
   
		
   }

   //@Override
   public void deleteInstRates(InstRates instR) {
      instRs.remove(instR.getId());
      System.out.println("InstRates:  " + instR.getId() + ", deleted from database");
   }

   //retrive list of instRs from the database
   //@Override
   public List<InstRates> getAllInstRates() {
      return instRs;
   }

   //@Override
   public InstRates getInstRates(int id) {
      return instRs.get(id);
   }

   //@Override
   public InstRates getInstRates(int ageGrp, int dataYear, int sex) {
       int id=1 ;
      return instRs.get(id);
   }

   //@Override
   public void updateInstRates(InstRates instR) {
       //   instRs.get(instR.getRollNo()).setName(instR.getName());
       //System.out.println("InstRates: Roll No " + instR.getRollNo() + ", updated in the database");
   }

 public static void main(String[] args) {

     InstRatesDAOImpl iRDI = new InstRatesDAOImpl() ;
      
      InstRates instR1 = new InstRates(1,"60-64",2010,1,0.3);
      InstRates instR2 = new InstRates(2,"65-69",2010,1,0.6);
      InstRates instR3 = new InstRates(3,"70-74",2010,1,1.5);
      InstRates instR4 = new InstRates(4,"75-79",2010,1,3.0);
      InstRates instR5 = new InstRates(5,"80+",2010,1,10.6);
  
      iRDI.instRs.add(instR1);
      iRDI.instRs.add(instR2);
      iRDI.instRs.add(instR3);
      iRDI.instRs.add(instR4);
      iRDI.instRs.add(instR5);
 
      for (int i=0; i<5; i++) {

	  InstRates iI = iRDI.getInstRates(i) ; 
	  System.out.println("Id:" + iI.getId() + "  Frac:" + iI.getFracInInst()    ) ;
      }

  

     
   }
 


}