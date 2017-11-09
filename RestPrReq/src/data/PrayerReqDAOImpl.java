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
		System.out.println("************** in Create ***********************");
		ObjectMapper mapper = new ObjectMapper();
		PrayerRequest mappedPrayer = null;
		try {
			mappedPrayer = mapper.readValue(prayJson, PrayerRequest.class);
			mappedPrayer.setTimestamp(new java.sql.Date(new java.util.Date().getTime()));
			mappedPrayer.setIpAddress(ipAddress);
			em.persist(mappedPrayer);
			em.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
//		updateCurrentList(mappedPrayer); //put new prayerRequest in; delete oldest, see below
		return mappedPrayer;
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
//		String query = "DELETE FROM PrayerRequest p WHERE p.id = :id";
//		em.createQuery(query).setParameter("id", id).executeUpdate();
		return true;
	}

}
