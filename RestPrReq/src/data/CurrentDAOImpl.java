package data;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Current;
import entities.PrayerRequest;

@Transactional
@Repository
public class CurrentDAOImpl implements CurrentDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	private int maxCurrent = 20;

	@Override
	public Current create(String currentJson) {
		ObjectMapper mapper = new ObjectMapper();
		Current mappedCurrent = null;
		try {
			mappedCurrent = mapper.readValue(currentJson, Current.class);
			em.persist(mappedCurrent);
			em.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mappedCurrent;
	}

	@Override
	public Current update(int id, String currentJson) {
		Current managed = em.find(Current.class, id);
		if(managed==null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		Current mapped = null;
		try {
			mapped = mapper.readValue(currentJson, Current.class);
			if(mapped.getPrayerReq() != null) {
				managed.setPrayerReq(mapped.getPrayerReq());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return managed;
	}

	@Override
	public boolean delete(int id) {
		Current managed = em.find(Current.class, id);
		if(managed==null) {
			return false;
		}
		em.remove(managed);
		return true;
	}

	@Override
	public void updateCurrentList(PrayerRequest pr) {
		for(int i=1; i<maxCurrent; i++) {
			Current cur = em.find(Current.class, i+1);
			Current managed = em.find(Current.class, i);
			PrayerRequest hold = cur.getPrayerReq();
			managed.setPrayerReq(hold);
		}
		Current lastOne = em.find(Current.class, maxCurrent);
		lastOne.setPrayerReq(pr);
	}

}
