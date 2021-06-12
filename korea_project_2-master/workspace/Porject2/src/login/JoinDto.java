package login;

public class JoinDto {
	private String id;
	private String pass;
	private String name;
	private String phone;
	private String email;
	private String birth;
	private String user_nickname;
	
	
	public JoinDto() {
		// TODO Auto-generated constructor stub
	}
	//insert문을 위한 생성자
	public JoinDto(String id, String pass, String name, String phone, String email, String birth,
			String user_nickname) {
		super();
		this.id = id;
		this.pass = pass;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.birth = birth;
		this.user_nickname = user_nickname;
	}

	public final String getId() {
		return id;
	}

	public final void setId(String id) {
		this.id = id;
	}

	public final String getPass() {
		return pass;
	}

	public final void setPass(String pass) {
		this.pass = pass;
	}

	public final String getName() {
		return name;
	}

	public final void setName(String name) {
		this.name = name;
	}

	public final String getPhone() {
		return phone;
	}

	public final void setPhone(String phone) {
		this.phone = phone;
	}

	public final String getEmail() {
		return email;
	}

	public final void setEmail(String email) {
		this.email = email;
	}

	public final String getBirth() {
		return birth;
	}

	public final void setBirth(String birth) {
		this.birth = birth;
	}

	public final String getUser_nickname() {
		return user_nickname;
	}

	public final void setUser_nickname(String user_nickname) {
		this.user_nickname = user_nickname;
	}
	
	
	
	
	
	
	
	
}
