package entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Current {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name="pr_id")
	private PrayerRequest prayerReq;

	public int getId() {
		return id;
	}

	public PrayerRequest getPrayerReq() {
		return prayerReq;
	}

	public void setPrayerReq(PrayerRequest prayerReq) {
		this.prayerReq = prayerReq;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Current [id=");
		builder.append(id);
		builder.append(", prayerReq=");
		builder.append(prayerReq);
		builder.append("]");
		return builder.toString();
	}

}
