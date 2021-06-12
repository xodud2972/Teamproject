package company;

public class CompanyDto {
	private String addr;
	private String comp_id;
	private String comp_pass;
	private String introduce;
	private int pk_company;
	private String tel;
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getComp_id() {
		return comp_id;
	}
	public void setComp_id(String comp_id) {
		this.comp_id = comp_id;
	}
	public String getComp_pass() {
		return comp_pass;
	}
	public void setComp_pass(String comp_pass) {
		this.comp_pass = comp_pass;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public int getPk_company() {
		return pk_company;
	}
	public void setPk_company(int pk_company) {
		this.pk_company = pk_company;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}

}
