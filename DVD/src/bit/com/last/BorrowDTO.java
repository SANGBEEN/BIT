package bit.com.last;

import java.sql.Date;

public class BorrowDTO {
	private int c_num;
	private int d_num;
	private int price;
	private Date start_date;
	private Date end_date;
	public BorrowDTO(int c_num, int d_num, int price, Date start_date, Date end_date) {
		super();
		this.c_num = c_num;
		this.d_num = d_num;
		this.price = price;
		this.start_date = start_date;
		this.end_date = end_date;
	}
	public int getC_num() {
		return c_num;
	}
	public void setC_num(int c_num) {
		this.c_num = c_num;
	}
	public int getD_num() {
		return d_num;
	}
	public void setD_num(int d_num) {
		this.d_num = d_num;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	@Override
	public String toString() {
		return String.format("|%6s|%6s|%5s|%-10s\t|%-10s|",
				c_num, d_num,price, start_date, end_date);
	}
//	@Override
//	public String toString() {
//		StringBuilder builder = new StringBuilder();
//		builder.append("BorrowDTO [c_num=").append(c_num).append(", d_num=").append(d_num).append(", price=")
//				.append(price).append(", start_date=").append(start_date).append(", end_date=").append(end_date)
//				.append("]");
//		return builder.toString();
//	}
	
}
