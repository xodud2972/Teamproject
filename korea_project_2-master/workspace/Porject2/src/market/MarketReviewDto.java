package market;

public class MarketReviewDto {
	private String pk_user_review;
	private String pk_usermarket;
	private String seller;
	private String buyer;
	private String content;
	private String rev_date;
	private String score;
	// ------------------------------------------------------[게시글 숨김 기능]
	private int hide;
	// ------------------------------------------------------[검색]
	private String reviewCategory;
	private String reviewKeyword;
	// --------------------------------------------------------[일반]
	// pk_user_review
	public String getPk_user_review() {
		return pk_user_review;
	}
	public void setPk_user_review(String pk_user_review) {
		this.pk_user_review = pk_user_review;
	}
	// pk_usermarket
	public String getPk_usermarket() {
		return pk_usermarket;
	}
	public void setPk_usermarket(String pk_usermarket) {
		this.pk_usermarket = pk_usermarket;
	}
	// seller
	public String getSeller() {
		return seller;
	}
	public void setSeller(String seller) {
		this.seller = seller;
	}
	// buyer
	public String getBuyer() {
		return buyer;
	}
	public void setBuyer(String buyer) {
		this.buyer = buyer;
	}
	// content
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	// rev_date
	public String getRev_date() {
		return rev_date;
	}
	public void setRev_date(String rev_date) {
		this.rev_date = rev_date;
	}
	// Score
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	// ------------------------------------------------------[게시글 숨김 기능]
	public int isHide() {
		return hide;
	}
	public void setHide(int hide) {
		this.hide = hide;
	}
	
	// ------------------------------------------------------[검색]
	// reviewCategory
    public String getReviewCategory() {
        return reviewCategory;
    }
	public void setReviewCategory(String reviewCategory) {
        this.reviewCategory = reviewCategory;
    }
	// reviewKeyword
    public String getReviewKeyword() {
        return reviewKeyword;
    }
    public void setReviewKeyword(String reviewKeyword) {
        this.reviewKeyword = reviewKeyword;
    }
	
}
