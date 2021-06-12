package market;

public class MarketPostDto {
	private String pk_usermarket;
	private String pk_user;
	private String id;
	private String title;
	private String price;
	private String content;
	private String filename;
	private String regdate;
	// ------------------------------------------------------[검색]
	private String category;
	private String keyword;

	// pk_usermarket
	public String getPk_usermarket() {
		return pk_usermarket;
	}
	public void setPk_usermarket(String pk_usermarket) {
		this.pk_usermarket = pk_usermarket;
	}
	// pk_user
	public String getPk_user() {
		return pk_user;
	}
	public void setPk_user(String pk_user) {
		this.pk_user = pk_user;
	}
	// ID
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	// title
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	// price
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	// content
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	// filename
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	// regdate
	public String getRegdate() {
        return regdate;
    }
    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
    // ------------------------------------------------------[검색]
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
	
}
