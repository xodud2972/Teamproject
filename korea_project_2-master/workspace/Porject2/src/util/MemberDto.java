package util;

public class MemberDto {
    private String comment_id;
    private String board_id;
    private String user_num;
    private String comment_content;
    private String regdate;
	public final String getComment_id() {
		return comment_id;
	}
	public final void setComment_id(String comment_id) {
		this.comment_id = comment_id;
	}
	public final String getBoard_id() {
		return board_id;
	}
	public final void setBoard_id(String board_id) {
		this.board_id = board_id;
	}
	public final String getUser_num() {
		return user_num;
	}
	public final void setUser_num(String user_num) {
		this.user_num = user_num;
	}
	public final String getComment_content() {
		return comment_content;
	}
	public final void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	public final String getRegdate() {
		return regdate;
	}
	public final void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	

    
}
