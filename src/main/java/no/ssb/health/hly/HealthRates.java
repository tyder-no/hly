//  HealthRates Rates of people in institutions, by age group
//
//
//
// CREATE TABLE selfReportedHealth (ageGrp INT, ageGrpLabel CHAR(10), spLabel CHAR(10), dataYear INT, sex INT, nHealthy DECIMAL(13,5), fracHealthy DECIMAL(13,5) ) ; 
//
package no.ssb.health.hly;

public class HealthRates {
  
    private static int idCount = 0 ;
    private int id ;
    private int ageGrp ;
    private int dataYear ;
    private int sex ;
    private double numHealthy ;
    private double fracHealthy ;
 

    HealthRates(int ageGrp, int dataYear, int sex, double numHealthy, double fracHealthy) {
	idCount++ ;
      this.id = idCount ; 
      this.ageGrp = ageGrp;
  
      this.dataYear = dataYear ;
      this.sex = sex ;
      this.numHealthy = numHealthy ;
      this.fracHealthy = fracHealthy ;

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

   public double getNumHealthy() {
      return  numHealthy;
   }

   public void setNumHealthy(double numHealthy) {
      this.numHealthy =  numHealthy;
   }


   public double getFracHealthy() {
      return  fracHealthy;
   }

   public void setFracHealthy(double fracHealthy) {
      this.fracHealthy =  fracHealthy;
   }


   public static void main(String[] args) {

      final int MAXRATES = 10 ;
      HealthRates[] healthRates = new HealthRates[MAXRATES] ;

      for (int i=0; i<MAXRATES; i++) {
	  String sI = "grp" + Integer.toString(i) ;  double frc = (1+i*1.0/100) ;
	  healthRates[i] = new HealthRates(i,2010,1,frc,1-frc) ;
          System.out.println(sI+" "+ frc) ;
	  System.out.println("HealthRates " + i + " AgeGrp: " + healthRates[i].getAgeGrp() +"  Id: "+ healthRates[i].getId() + "  Health:" + healthRates[i].getFracHealthy()) ; 
      }
     
   }
 


}