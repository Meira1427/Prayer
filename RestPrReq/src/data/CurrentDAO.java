package data;

import entities.Current;
import entities.PrayerRequest;

public interface CurrentDAO {
	
	public Current create(String currentJson);
	public Current update(int id, String currentJson);
	public boolean delete(int id);
	public void updateCurrentList(PrayerRequest pr);

}
