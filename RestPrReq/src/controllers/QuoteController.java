package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import data.QuoteDAO;
import entities.Keyword;
import entities.Quote;


@RestController
public class QuoteController {
	
	@Autowired
	QuoteDAO quoteDao;
	
	@RequestMapping(path="ping", method=RequestMethod.GET)
	public String ping() {
		return "pong";
	}
	
	@RequestMapping(path="quotes", method=RequestMethod.GET)
	public Collection<Quote> allQuotes() {
		return quoteDao.indexQuote();
	}
	
	@RequestMapping(path="quotes/keywords/{key}", method=RequestMethod.GET)
	public Collection<Quote> quotesByKeyword(@PathVariable("key") String key) {
		return quoteDao.quotesPerKeyword(key);
	}
	
	@RequestMapping(path="keywords", method=RequestMethod.GET)
	public Collection<Keyword> allKeywords() {
		return quoteDao.indexKeyword();
	}
	
	@RequestMapping(path="quotes/{id}", method=RequestMethod.GET)
	public Quote showQuote(@PathVariable int id) {
		return quoteDao.show(id);
	}

}
