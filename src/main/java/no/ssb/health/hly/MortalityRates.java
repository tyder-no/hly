//  MortalityRates Rates of survival/mortality, by age group
//
// CREATE TABLE mortalityTable (ageGrp INT, ageGrpLabel CHAR(10),  dataYear INT, sex INT, nDead DECIMAL(10,2), nAlive DECIMAL(10,2) ) ;
//
//
package no.ssb.health.hly;

public class MortalityRates {
  
    private static int idCount = 0 ;
    private int id ;
    private int ageGrp ;
    private int dataYear ;
    private int sex ;
    private double nDead ;
    private double nAlive ;

    MortalityRates(int ageGrp, int dataYear, int sex, double nAlive, double nDead) {
	idCount++ ;
      this.id = idCount ; 
      this.ageGrp = ageGrp;
      this.dataYear = dataYear ;
      this.sex = sex ;
      this.nDead = nDead ;
      this.nAlive = nAlive ;

   }

   

   public int getId() {
      return id ;
   }
    // This may break integrity
    //   public void setId(int id) {      this.id = id ;   }

   public int getAgeGrp() {
      return ageGrp ;
   }

   public void setAgeGrp(int ageGrp) {
      this.ageGrp = ageGrp ;
   }

   public int getSex() {
      return sex ;
   }

   public void setSex(int sex) {
      this.sex = sex ;
   }

   public int getDataYear() {
      return dataYear ;
   }

   public void setDataYear(int dataYear) {
      this.dataYear = dataYear ;
   }

   public double getNDead() {
      return  nDead;
   }

   public void setNDead(double nDead) {
      this.nDead =  nDead;
   }

  public double getNAlive() {
      return  nAlive;
   }

   public void setNAlive(double nAlive) {
      this.nAlive =  nAlive;
   }


   public static void main(String[] args) {

      final int MAXRATES = 10 ;
      MortalityRates[] mortRates = new MortalityRates[MAXRATES] ;

      for (int i=0; i<MAXRATES; i++) {
	  String sI = "grp" + Integer.toString(i) ;  double dfrc = (1+i*1.0/100) ;   double afrc = (1+i*1.0/100) ;
	  mortRates[i] = new MortalityRates(i,i,2010,dfrc,afrc) ;
          System.out.println(sI+" "+ dfrc) ;
	  System.out.println("MortalityRates " + i + " AgeGrp: " + mortRates[i].getAgeGrp() +"  Id: "+ mortRates[i].getId() + "  nDead:" + mortRates[i].getNDead()) ; 
      }
     
   }
 


}