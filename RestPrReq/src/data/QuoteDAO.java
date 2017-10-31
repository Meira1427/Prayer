package data;

import java.util.Set;

import entities.Keyword;
import entities.Quote;

public interface QuoteDAO {
	
	public Set<Quote> indexQuote();
	public Set<Keyword> indexKeyword();
	public Set<Quote> quotesPerKeyword(String key);
	public Quote show(int id);
	public Quote create(String quoteJson);
	public Quote update(int id, String quoteJson);
	public boolean delete(int id);

}
