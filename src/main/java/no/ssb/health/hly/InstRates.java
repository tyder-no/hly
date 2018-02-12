//  InstRates Rates of people in institutions, by age group
//
//
package no.ssb.health.hly;

//CREATE TABLE instRates (ageGrp INT, ageGrpLab CHAR(10), dataYear INT, sex INT, fracInInst DECIMAL(10,2) ) ; 
//


public class InstRates {
  
    private static int idCount = 0 ;
    private int id ;
    private int ageGrp ;
    private int dataYear ;
    private int sex ;
    private double fracInInst ;
    private String ageGrpLab ;

    InstRates(int ageGrp, String ageGrpLab, int dataYear, int sex, double fracInInst) {
	idCount++ ;
      this.id = idCount ; 
      this.ageGrp = ageGrp;
      this.ageGrpLab = ageGrpLab;
      this.dataYear = dataYear ;
      this.sex = sex ;
      this.fracInInst = fracInInst ;

   }

   public String getAgeGrpLab() {
      return ageGrpLab ;
   }

   public void setAgeGrpLab(String ageGrpLab) {
       this.ageGrpLab = ageGrpLab ;
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

   public double getFracInInst() {
      return  fracInInst;
   }

   public void setFracInInst(double fracInInst) {
      this.fracInInst =  fracInInst;
   }


   public static void main(String[] args) {

      final int MAXRATES = 10 ;
      InstRates[] instRates = new InstRates[MAXRATES] ;

      for (int i=0; i<MAXRATES; i++) {
	  String sI = "grp" + Integer.toString(i) ;  double frc = (1+i*1.0/100) ;
	  instRates[i] = new InstRates(i,"grp" + Integer.toString(i),2010,1,frc) ;
          System.out.println(sI+" "+ frc) ;
	  System.out.println("InstRates " + i + " AgeGrp: " + instRates[i].getAgeGrpLab() +"  Id: "+ instRates[i].getId() + "  Frac:" + instRates[i].getFracInInst()) ; 
      }
     
   }
 


}