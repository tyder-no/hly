package no.ssb.health.hly;

import java.util.List;

public interface HealthRatesDAO {
   public List<HealthRates> getAllHealthRates();
   public HealthRates getHealthRates(int ageGrp, int dataYear, int sex);
   public HealthRates getHealthRates(int id);
   public void updateHealthRates(HealthRates hlthR);
   public void deleteHealthRates(HealthRates hlthR);
}