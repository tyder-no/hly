package no.ssb.health.hly;

import java.util.ArrayList;
import java.util.List;

//  
/*
"0 ";100000;284;100000;262;100000;290;100000;214;100000;245;100000;246;100000;168;100000;214;100000;270;100000;221
"1 ";99716;35;99738;25;99710;28;99786;21;99755;14;99754;40;99832;23;99786;30;99730;27;99779;7
"2 ";99681;4;99713;14;99682;28;99766;21;99741;14;99715;0;99809;7;99756;13;99703;7;99772;10
"3 ";99677;14;99699;7;99654;7;99745;7;99728;17;99715;10;99802;17;99743;6;99697;13;99762;3
"4 ";99663;7;99692;14;99647;7;99738;10;99711;14;99705;7;99786;10;99737;10;99684;6;99759;13
"5 ";99656;17;99678;7;99640;11;99728;10;99697;10;99698;10;99776;7;99727;7;99678;10;99746;6
"6 ";99639;10;99671;17;99630;3;99717;7;99687;7;99688;3;99769;7;99720;3;99668;3;99740;3
"7 ";99629;13;99654;14;99626;10;99710;7;99680;14;99684;14;99762;3;99717;7;99664;7;99737;3
"8 ";99615;3;99641;10;99616;10;99703;7;99666;0;99670;3;99759;7;99710;10;99658;7;99733;10
"9 ";99612;0;99631;20;99606;7;99697;13;99666;17;99667;17;99752;0;99700;14;99651;7;99723;3
"10 ";99612;10;99611;3;99599;13;99683;7;99649;7;99650;3;99752;17;99686;14;99644;7;99720;0

*/

//  Constructor: MortalityRates(int ageGrp, int dataYear, int sex, double nDead, double nAlive)


// Fetches from data file 

public class MortalityRatesDAOFileImpl extends MortalityRatesDAOImpl {
	
    int debug = 0 ;
   //list is working as a database
   List<MortalityRates> mortRs;


   private void mRdF(int frstyear, int nyears, String fnm1,int sex)  {

	   String iDatFnm = fnm1 ;
	   // Using InFileHandler to read and parse scv file 
	   InFileHandler iF = new InFileHandler(iDatFnm,2*nyears+1) ;
	   List<String[]> mortData = iF.getStrArr();
	   if (debug==1) System.out.println("MortalityRates,nrecs: " + iF.nRecs) ;
	   int nRecs = iF.nRecs ;
	   // Creating the list of mort data
	   for (int i=0 ; i<nRecs;i++) {

		   for (int j=0 ; j<nyears;j++) {
			   MortalityRates mortR = new MortalityRates(i,frstyear+j,sex,Converter.getDoubleValue(mortData.get(i)[2*j+1]),Converter.getDoubleValue(mortData.get(i)[2*j+2])) ;
			   mortRs.add(mortR) ; 
			 //  if (debug==1) System.out.println(i + " Year: " + mortR.getDataYear() + " Age: " + mortR.getAgeGrp() + " nDead:  " +  mortR.getNDead() ) ; 
		   }

	   }


   }

    public MortalityRatesDAOFileImpl(int frstyear, int nyears,String fnm1,String fnm2){
	mortRs = new ArrayList<MortalityRates>();


	mRdF(frstyear,nyears,fnm1,1)  ; // Data for men
  	mRdF(frstyear,nyears,fnm2,2)  ; // Data for women
         
        if (debug==1) System.out.println("MortalityRates size:" + mortRs.size()) ; 
	
       	if (debug==1) {
	    for (int i=0 ; i<200;i++)
	    System.out.println("MRates " + i + " Id: "+ mortRs.get(i).getId() + " Year " +  mortRs.get(i).getDataYear() +" AgeGrp: " + mortRs.get(i).getAgeGrp() +"  Sex: "+ mortRs.get(i).getSex() + "  Dead:" + mortRs.get(i).getNDead()) ; 		
	}
	
    }

   @Override
   public void deleteMortalityRates(MortalityRates mortR) {
      mortRs.remove(mortR.getId());
      System.out.println("MortalityRates:  " + mortR.getId() + ", deleted from database");
   }

   
   @Override
   public MortalityRates getMortalityRates(int id) {
       for (int i=0 ; i<mortRs.size();i++) {
	   if  (mortRs.get(i).getId()==id) return mortRs.get(i);
       }
       return null ; 
   }

   @Override
       public MortalityRates getMortalityRates(int ageGrp, int dataYear, int sex) {
       for (int i=0 ; i<mortRs.size();i++) {
	   if  ((mortRs.get(i).getAgeGrp()==ageGrp) &&(mortRs.get(i).getDataYear()==dataYear)&& (mortRs.get(i).getSex()==sex)) return mortRs.get(i);
       }
       return null ; 
      
   }

   @Override
   public void updateMortalityRates(MortalityRates mortR) {
       //   mortRs.get(mortR.getRollNo()).setName(mortR.getName());
       //System.out.println("MortalityRates: Roll No " + mortR.getRollNo() + ", updated in the database");
   }

 public static void main(String[] args) {

     MortalityRatesDAOFileImpl mRDI = new MortalityRatesDAOFileImpl(2005,10,"data/Dodelighet_2005-2015_1.csv","data/Dodelighet_2005-2015_2.csv") ;
     mRDI.getMortalityRates(1) ;
     
   }
 


}