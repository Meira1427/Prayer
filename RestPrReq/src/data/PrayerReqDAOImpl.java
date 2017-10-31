package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Current;
import entities.PrayerRequest;

@Transactional
@Repository
public class PrayerReqDAOImpl implements PrayerReqDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	private int maxCurrent = 5;

	@Override
	public Set<PrayerRequest> indexAll() {
		Set<PrayerRequest> requests = new HashSet<>();
		String queryString = "Select p from PrayerRequest p";
		List<PrayerRequest> list = em.createQuery(queryString, PrayerRequest.class)
									.getResultList();
		for (int i = 0; i < list.size(); i++) {
			requests.add(list.get(i));
		}
		return requests;
	}

	@Override
	public Set<PrayerRequest> indexCurrent() {
		Set<PrayerRequest> requests = new HashSet<>();
		String queryString = "Select c.prayerReq from Current c";
		List<PrayerRequest> list = em.createQuery(queryString, PrayerRequest.class)
									.getResultList();
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			requests.add(list.get(i));
		}
		return requests;
	}

	@Override
	public PrayerRequest show(int id) {
		PrayerRequest prayer = new PrayerRequest();
		String queryString = "Select p from PrayerRequest p where p.id = :id";
		List<PrayerRequest> list = em.createQuery(queryString, PrayerRequest.class)
									.setParameter("id", id)
									.getResultList();
		if(list.size() > 0) {
			prayer= list.get(0);
		}
		return prayer;
	}

	@Override
	public PrayerRequest create(String prayJson, String ipAddress) {
		ObjectMapper mapper = new ObjectMapper();
		PrayerRequest mappedPrayer = null;
		try {
			mappedPrayer = mapper.readValue(prayJson, PrayerRequest.class);
			em.persist(mappedPrayer);
			em.flush();
			mappedPrayer.setIpAddress(ipAddress);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		updateCurrentList(mappedPrayer); //put new prayerRequest in; delete oldest, see below
		return mappedPrayer;
	}
	
	//Note that maxCurrent is the total max size of the current list of prayerRequests
	//Use variable so that we can change from 5 to 50 as interest grows
	//this function reaches out to #2 and puts its id into #1, #3 into #2 etc
	//putting the newest prayerRequest into the maximum value spot in "current"
	private void updateCurrentList(PrayerRequest mappedPrayer) {
		String getCurr = "Select c from current c where id=:id";
		for(int i = 1; i < maxCurrent; i++) {
			Current current = em.createQuery(getCurr, Current.class)
								.setParameter("id", i+1)
								.getResultList()
								.get(0);
			int currentId = current.getPrayerReq().getId();
			em.createQuery("Update current c set c.pr_id = :new where id = :id")
								.setParameter("new", currentId)
								.setParameter("id", i)
								.executeUpdate();
		}
		int newNum = mappedPrayer.getId();
		em.createQuery("Update current c set c.pr_id = :new where id = :id)")
								.setParameter("new", newNum)
								.setParameter("id", maxCurrent)
								.executeUpdate();
	}

	@Override
	public PrayerRequest update(int id, String prayJson) {
		PrayerRequest managed = em.find(PrayerRequest.class, id);
		if(managed==null) {
			return null;
		}
		ObjectMapper mapper = new ObjectMapper();
		PrayerRequest mapped = null;
		try {
			mapped = mapper.readValue(prayJson, PrayerRequest.class);
			if(mapped.getIpAddress() != null && mapped.getIpAddress() !="") {
				managed.setIpAddress(mapped.getIpAddress());
			}
			if(mapped.getName() != null && mapped.getName() !="") {
				managed.setName(mapped.getName());
			}
			if(mapped.getRequest() != null && mapped.getRequest() !="") {
				managed.setRequest(mapped.getRequest());
			}
			if(mapped.getTimestamp() != null) {
				managed.setTimestamp(mapped.getTimestamp());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return managed;
	}

	@Override
	public boolean delete(int id) {
		PrayerRequest managed = em.find(PrayerRequest.class, id);
		if(managed==null) {
			return false;
		}
		em.remove(managed);
		//if em.remove doesn't work, you can use this:
//		String query = "DELETE FROM PrayerRequest p WHERE t.id = :id";
//		em.createQuery(query).setParameter("id", id).executeUpdate();
		return true;
	}

}
