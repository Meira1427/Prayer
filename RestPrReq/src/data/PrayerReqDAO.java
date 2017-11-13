package data;

import java.util.Set;
import entities.PrayerRequest;

public interface PrayerReqDAO {
	
	public Set<PrayerRequest> indexAll();
	public Set<PrayerRequest> indexCurrent();
	public PrayerRequest show(int id);
	public PrayerRequest create(String prayJson, String ipAddress);
	public PrayerRequest update(int id, String prayJson);
	public boolean delete(int id);
	public boolean containsRejectedWord(PrayerRequest pr);

}
