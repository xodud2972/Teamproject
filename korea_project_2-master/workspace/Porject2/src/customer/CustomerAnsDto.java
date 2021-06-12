package customer;

public class CustomerAnsDto {
    private String pk_customerans,pk_customerservice,pk_admin,content,regdate;
    
    public String getPk_customerans() {
        return pk_customerans;
    }

    public void setPk_customerans(String pk_customerans) {
        this.pk_customerans = pk_customerans;
    }

    public String getPk_customerservice() {
        return pk_customerservice;
    }

    public void setPk_customerservice(String pk_customerservice) {
        this.pk_customerservice = pk_customerservice;
    }

    public String getPk_admin() {
        return pk_admin;
    }

    public void setPk_admin(String pk_admin) {
        this.pk_admin = pk_admin;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }
    
}
