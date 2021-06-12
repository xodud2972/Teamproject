package main;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{
	private AppMain appMain;

	public AppMain getAppMain() {
		return appMain;
	}

	public void setAppMain(AppMain appMain) {
		this.appMain = appMain;
		
	}
	public Page(AppMain appMain) {
		this.appMain = appMain;
		setPreferredSize(new Dimension(1160, 800));
		setVisible(false);
	}
	
}
