package reservation;

public class BookingDto {
	// alt + shift + s + r  ,   alt + a  ,    alt + r   ,  o  
	private String book_date;
	private String memo;
	private int pk_booking;
	private int pk_company;
	private int pk_mybike;
	private String pk_user;
	private int pk_wanted;
	private String price;
	private String regdate;
	public String getBook_date() {
		return book_date;
	}
	public void setBook_date(String book_date) {
		this.book_date = book_date;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getPk_booking() {
		return pk_booking;
	}
	public void setPk_booking(int pk_booking) {
		this.pk_booking = pk_booking;
	}
	public int getPk_company() {
		return pk_company;
	}
	public void setPk_company(int pk_company) {
		this.pk_company = pk_company;
	}
	public int getPk_mybike() {
		return pk_mybike;
	}
	public void setPk_mybike(int pk_mybike) {
		this.pk_mybike = pk_mybike;
	}
	public String getPk_user() {
		return pk_user;
	}
	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}
	public int getPk_wanted() {
		return pk_wanted;
	}
	public void setPk_wanted(int pk_wanted) {
		this.pk_wanted = pk_wanted;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getRegdate() {
		return regdate;
	}
	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
}
