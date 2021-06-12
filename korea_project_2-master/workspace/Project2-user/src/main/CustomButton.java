package main;

import javax.swing.JButton;

public class CustomButton extends JButton{
	private int id;
	private int sub_id;
	
	public int getSub_id() {
		return sub_id;
	}

	public void setSub_id(int sub_id) {
		this.sub_id = sub_id;
	}

	public CustomButton(String top) {
		super(top);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
}
