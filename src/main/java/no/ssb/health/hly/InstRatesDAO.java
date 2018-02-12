package no.ssb.health.hly;

import java.util.List;

public interface InstRatesDAO {
   public List<InstRates> getAllInstRates();
   public InstRates getInstRates(int ageGrp, int dataYear, int sex);
   public InstRates getInstRates(int id);
   public void updateInstRates(InstRates instR);
   public void deleteInstRates(InstRates instR);
}