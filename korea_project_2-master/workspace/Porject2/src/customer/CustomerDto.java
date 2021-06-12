package customer;

public class CustomerDto {
    private String pk_customerservice, name,title,content,regdate,answered;
    private String customerCategory, customerKeyword;

    public String getPk_customerservice() {
        return pk_customerservice;
    }

    public void setPk_customerservice(String pk_customerservice) {
        this.pk_customerservice = pk_customerservice;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getAnswered() {
        return answered;
    }

    public void setAnswered(String answered) {
        this.answered = answered;
    }
    //	 검색
	public String getCustomerCategory() {
		return customerCategory;
	}

	public void setCustomerCategory(String customerCategory) {
		this.customerCategory = customerCategory;
	}

	public String getCustomerKeyword() {
		return customerKeyword;
	}

	public void setCustomerKeyword(String customerKeyword) {
		this.customerKeyword = customerKeyword;
	}
    
    
    
}
