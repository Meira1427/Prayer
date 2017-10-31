package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Keyword {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String word;
	
	@JsonIgnore
	@ManyToMany (fetch=FetchType.EAGER, cascade= {CascadeType.PERSIST, CascadeType.REMOVE})
	@JoinTable(name="quote_keyword",
	  joinColumns=@JoinColumn(name="keyword_id"),
	  inverseJoinColumns=@JoinColumn(name="quote_id"))
	private List<Quote> quotes;

	public int getId() {
		return id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public List<Quote> getQuotes() {
		return quotes;
	}

	public void setQuotes(List<Quote> quotes) {
		this.quotes = quotes;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Keyword [id=");
		builder.append(id);
		builder.append(", word=");
		builder.append(word);
		builder.append("]");
		return builder.toString();
	}

}
