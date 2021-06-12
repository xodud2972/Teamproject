package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bike.BikeMain;
import login.LoginDto;
import market.MarketMain;
import mileage.MileageMain;


public class AppMain extends JFrame{
	JPanel panel_west;
	JPanel panel_center;
	
	private LoginDto user = new LoginDto();
	
	
	
	
	
	String[] menu = {"바이크 등록","주유 기록","중고장터"};
	CustomButton[] bt_menu = new CustomButton[menu.length];
	
	
	
	Page[] page = new Page[3];
	
	
	
	public AppMain() {
		panel_west = new JPanel();
		panel_center = new JPanel();
		
		for(int i=0;i<menu.length ;i++) {
			bt_menu[i] = new CustomButton(menu[i]);
			bt_menu[i].setId(i);
			bt_menu[i].setVisible(true);
		}
		
		page[0]= new BikeMain(this);
		page[1]= new MileageMain(this);
		page[2]= new MarketMain(this);
		
		
		panel_west.setPreferredSize(new Dimension(240,800));
		panel_west.setBackground(Color.black);
		panel_center.setPreferredSize(new Dimension(1160,800));
		
		
		add(panel_west,BorderLayout.WEST);
		for (JButton bt: bt_menu) {
			bt.setPreferredSize(new Dimension(230, 35));
			bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					CustomButton bt = (CustomButton)obj;
					showPage(bt.getId());
				}
			});
			panel_west.add(bt);
		}
		for(int i=0;i<page.length;i++) {
			panel_center.add(page[i]);
		}
		
		add(panel_center);
		
		

		setSize(1400, 800);
		setLocationRelativeTo(null);
		setVisible(false);
		setTitle("Riding Mate");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	
	

	
	
	public LoginDto getUser() {
		return user;
	}

	public void setUser(LoginDto user) {
		this.user = user;
	}
	
	
	public void showPage(int id) {
		for(int i=0;i<page.length;i++) {
			if(id==i) {
				page[i].setVisible(true);
			}else {
				page[i].setVisible(false);
			}
		}
	}
	
	
	

}