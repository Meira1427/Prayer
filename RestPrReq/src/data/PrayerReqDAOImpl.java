package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
		return requests;
	}

	@Override
	public Set<PrayerRequest> indexCurrent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrayerRequest show(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrayerRequest create(String prayJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PrayerRequest update(int id, String prayJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
