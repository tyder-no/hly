
package no.ssb.health.hly;

import java.util.List;

public interface MortalityRatesDAO {
   public List<MortalityRates> getAllMortalityRates();
   public MortalityRates getMortalityRates(int ageGrp, int dataYear, int sex);
   public MortalityRates getMortalityRates(int id);
   public void updateMortalityRates(MortalityRates mortR);
   public void deleteMortalityRates(MortalityRates mortR);
}
