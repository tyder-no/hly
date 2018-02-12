package no.ssb.health.hly;

import java.util.ArrayList;
import java.util.List;

//  Constructor: MortalityRates(int ageGrp, int dataYear, int sex, double nDead, double nAlive)

/*
60-64;0.3;0.3;
65-69;0.6;0.5;
70-74;1.5;1.4;
75-79;3.0;3.5;
80+;10.6;16.3;
*/

public class MortalityRatesDAOImpl implements MortalityRatesDAO {
	
   //list is working as a database
   List<MortalityRates> instRs;

   public MortalityRatesDAOImpl(){
      instRs = new ArrayList<MortalityRates>();
   
		
   }

   // @Override
   public void deleteMortalityRates(MortalityRates instR) {
      instRs.remove(instR.getId());
      System.out.println("MortalityRates:  " + instR.getId() + ", deleted from database");
   }

   //retrive list of instRs from the database
   // @Override
   public List<MortalityRates> getAllMortalityRates() {
      return instRs;
   }

   // @Override
   public MortalityRates getMortalityRates(int id) {
      return instRs.get(id);
   }

   // @Override
   public MortalityRates getMortalityRates(int ageGrp, int dataYear, int sex) {
       int id=1 ;
      return instRs.get(id);
   }

   // @Override
   public void updateMortalityRates(MortalityRates instR) {
       //   instRs.get(instR.getRollNo()).setName(instR.getName());
       //System.out.println("MortalityRates: Roll No " + instR.getRollNo() + ", updated in the database");
   }

 public static void main(String[] args) {

     MortalityRatesDAOImpl iRDI = new MortalityRatesDAOImpl() ;
      
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
 
      for (int i=0; i<5; i++) {

	  MortalityRates iI = iRDI.getMortalityRates(i) ; 
	  System.out.println("Id:" + iI.getId() + "  nDead:" + iI.getNDead()    ) ;
      }

     /*
      final int MAXRATES = 10 ;
      MortalityRates[] instRates = new MortalityRates[MAXRATES] ;

      for (int i=0; i<MAXRATES; i++) {
	  String sI = "grp" + Integer.toString(i) ;  double frc = (1+i*1.0/100) ;
	  instRates[i] = new MortalityRates(i,"grp" + Integer.toString(i),2010,1,frc) ;
          System.out.println(sI+" "+ frc) ;
	  System.out.println("MortalityRates " + i + " AgeGrp: " + instRates[i].getAgeGrpLab() +"  Id: "+ instRates[i].getId() + "  Frac:" + instRates[i].getFracInInst()) ; 
      }
     */

     
   }
 


}