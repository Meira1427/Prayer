package entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Quote {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="book_id")
	private Book book;
	
	private String text;
	
	private int chapter;
	
	private String verses;
	
	@OneToOne
	@JoinColumn(name="version_id")
	private Version version;
	
	@JsonBackReference(value="keywordAndQuote")
	@ManyToMany(mappedBy="quotes")
	private List<Keyword> keywords;

	public int getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getChapter() {
		return chapter;
	}

	public void setChapter(int chapter) {
		this.chapter = chapter;
	}

	public String getVerses() {
		return verses;
	}

	public void setVerses(String verses) {
		this.verses = verses;
	}

	public Version getVersion() {
		return version;
	}

	public void setVersion(Version version) {
		this.version = version;
	}

	public List<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(List<Keyword> keywords) {
		this.keywords = keywords;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Quote [id=");
		builder.append(id);
		builder.append(", book=");
		builder.append(book);
		builder.append(", text=");
		builder.append(text);
		builder.append(", chapter=");
		builder.append(chapter);
		builder.append(", verses=");
		builder.append(verses);
		builder.append(", version=");
		builder.append(version);
		builder.append("]");
		return builder.toString();
	}

}
