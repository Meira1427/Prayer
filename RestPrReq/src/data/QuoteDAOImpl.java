package data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;

import entities.Keyword;
import entities.PrayerRequest;
import entities.Quote;

@Transactional
@Repository
public class QuoteDAOImpl implements QuoteDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Set<Quote> indexQuote() {
		Set<Quote> quotes = new HashSet<>();
		String queryString = "Select q from Quote q";
		List<Quote> list = em.createQuery(queryString, Quote.class)
									.getResultList();
		for (int i = 0; i < list.size(); i++) {
			quotes.add(list.get(i));
		}
		return quotes;
	}

	@Override
	public Set<Keyword> indexKeyword() {
		Set<Keyword> keywords = new HashSet<>();
		String queryString = "Select k from Keyword k";
		List<Keyword> list = em.createQuery(queryString, Keyword.class)
									.getResultList();
		for (int i = 0; i < list.size(); i++) {
			keywords.add(list.get(i));
		}
		return keywords;
	}

	@Override
	public Set<Quote> quotesPerKeyword(String key) {
		Set<Quote> quotes = new HashSet<>();
		String queryString = "Select k from Keyword k where k.word = :key";
		Keyword word = em.createQuery(queryString, Keyword.class)
									.setParameter("key", key)
									.getResultList()
									.get(0);
		List<Quote> quoteList= word.getQuotes();
		for (int i = 0; i < quoteList.size(); i++) {
			System.out.println("in quotesPerKeyword: " + quoteList.get(i).getBook());
			quotes.add(quoteList.get(i));
		}
		return quotes;
	}

	@Override
	public Quote show(int id) {
		return em.find(Quote.class, id);
	}

	@Override
	public Quote create(String quoteJson) {
		ObjectMapper mapper = new ObjectMapper();
		Quote mappedQuote = null;
		try {
			mappedQuote = mapper.readValue(quoteJson, Quote.class);
			em.persist(mappedQuote);
			em.flush();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mappedQuote;
	}

	@Override
	public Quote update(int id, String quoteJson) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

}
