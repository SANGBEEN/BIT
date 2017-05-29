package bit.com.last;

import java.sql.Date;

/**
 * @author bit-user
 *
 */
public class DvdDTO {
	private int d_num;
	private String director;
	private Date release_date;
	private String title;
	private String genre;
	private int price;
	private String rating;
	private String actor;
	private String enable;
	public DvdDTO(){
		
	}
	public DvdDTO(int d_num, String director, Date release_date, String title, String genre, int price, String rating, String actor,String enable) {
		super();
		this.d_num = d_num;
		this.director = director;
		this.release_date = release_date;
		this.title = title;
		this.genre = genre;
		this.price = price;
		this.rating = rating;
		this.actor = actor;
		this.enable = enable;
	}
	
	public String getEnable() {
		return enable;
	}

	public void setEnable(String enable) {
		this.enable = enable;
	}

	public String getActor() {
		return actor;
	}

	public void setActor(String actor) {
		this.actor = actor;
	}

	public int getD_num() {
		return d_num;
	}
	public void setD_num(int d_num) {
		this.d_num = d_num;
	}
	public String getDirector() {
		return director;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public Date getRelease_date() {
		return release_date;
	}
	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	@Override
	public String toString() {
		return String.format(
//				"%5s %-50s \t%-20s",d_num,title,director);}
				"|%5s|%-30s \t|%-10s \t|%-10s\t|%-10s \t|%6s \t|%-10s \t|%-28s \t|%-1s\t|",
				d_num,title, director, release_date, genre, price, rating, actor, enable);
	}
	
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("번호:").append(d_num).append("\t제목:").append(title).append("\t\t감독:").append(director).append("\t출시일자:")
//				.append(release_date).append("\t\t장르:").append(genre)
//				.append("\t대여료:").append(price).append("\t관람등급:").append(rating).append("  주연배우:").append(actor)
//				.append("\t대여가능:").append(enable);
//		return builder.toString();
//	}
//	
	
}
