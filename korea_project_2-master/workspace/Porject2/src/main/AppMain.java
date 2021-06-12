package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import bike.Bike;
import company.CompanyMain;
import customer.CustomerCenter;
import customer.CustomerCenterAns;
import login.LoginDto;
import market.MarketLog;
import market.MarketPost;
import market.MarketReview;
import reservation.ReservationMain;
import reservation.ReservationUnanswered;
import user.Maintenance;
import user.Mileage;
import user.MyBike;
import user.User;

public class AppMain extends JFrame implements ActionListener{
	JPanel p_west;
	JPanel p_top;
	JPanel p_sub;
	JPanel p_center;
	
	String[] top_title= {"회원 관리","바이크 관리","중고장터 관리","예약관리","기업 관리","고객 센터"};
	CustomButton[] bt_top = new CustomButton[top_title.length];
	
	
	String[] sub_string = {"회원","바이크","연비","정비",
			"전체조회",
			"게시글","로그","리뷰",
			"게시글","미답변",
			"게시글",
			"전체조회"};
	CustomButton[] sub_btn = new CustomButton[sub_string.length];
	
	public Page[] pages = new Page[12];
	public CustomerCenterAns subCustomer;
	private LoginDto user = new LoginDto();
	
	
	public AppMain() {
		p_west = new JPanel();
		p_top = new JPanel();
		p_sub = new JPanel();
		for(int i=0;i<top_title.length ;i++) {
			bt_top[i] = new CustomButton(top_title[i]);
			bt_top[i].setId(i);
			bt_top[i].setVisible(true);
		}
		p_center = new JPanel();
		
		pages[0] = new User(this);
		pages[1] = new MyBike(this);
		pages[2] = new Mileage(this);
		pages[3] = new Maintenance(this);
		pages[4] = new Bike(this);
		pages[5] = new MarketPost(this);
		pages[6] = new MarketLog(this);
		pages[7] = new MarketReview(this);
		pages[8] = new ReservationMain(this);
		pages[9] = new ReservationUnanswered(this);
		pages[10] = new CompanyMain(this);
		pages[11] = new CustomerCenter(this);
		subCustomer=new CustomerCenterAns(this);

		
		
		for(int i=0;i<sub_string.length ;i++) {
			sub_btn[i] = new CustomButton(sub_string[i]);
			sub_btn[i].setVisible(false);
			sub_btn[i].setPreferredSize(new Dimension(110, 35));
			sub_btn[i].addActionListener(this);
			sub_btn[i].setSub_id(i);
			p_sub.add(sub_btn[i]);
		}
		//세상에 머지가 이렇게 힘들줄이야
		
		
		
		
		p_west.setPreferredSize(new Dimension(240, 800));
		p_west.setBackground(Color.BLACK);
		p_top.setPreferredSize(new Dimension(120, 800));
		p_top.setBackground(Color.DARK_GRAY);
		p_sub.setPreferredSize(new Dimension(110, 800));
		p_sub.setBackground(Color.GRAY);
		
		
		
		
		for (JButton bt: bt_top) {
			bt.setPreferredSize(new Dimension(110, 35));
			bt.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Object obj = e.getSource();
					CustomButton bt = (CustomButton)obj;
					if(bt.getId()==0) {
						createSubBtn(0,3);
					}else if(bt.getId()==1) {
						createSubBtn(4,4);						
					}else if(bt.getId()==2) {
						createSubBtn(5,7);						
					}else if(bt.getId()==3) {
						createSubBtn(8,9);						
					}else if(bt.getId()==4) {
						createSubBtn(10,10);						
					}else if(bt.getId()==5) {
						createSubBtn(11,11);						
					}
				}
			});
			p_top.add(bt);
		}
		p_west.add(p_top);
		p_west.add(p_sub);
		add(p_west,BorderLayout.WEST);
		for(Page p : pages) {
			p_center.add(p);
		}
		p_center.add(subCustomer);
		add(p_center);
		
		
		
		
		
		

		setSize(1400, 800);
		setLocationRelativeTo(null);
		setVisible(false);
		setTitle("Riding Mate");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void createSubBtn(int start, int end) {
		for(int i=0;i<sub_btn.length;i++) {
			sub_btn[i].setVisible(false);			
		}
		for(int i=start;i<=end;i++) {
			sub_btn[i].setVisible(true);
		}
		p_sub.updateUI();
	}
	
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		CustomButton bt = (CustomButton)obj;
		int n = bt.getSub_id();
		System.out.println(n);
		showHide(n);
	}
	
	
	public void showHide(int n) {
		for (int i = 0; i < pages.length; i++) {
			if(n==i) {
				pages[i].setVisible(true);
			}else {
				pages[i].setVisible(false);				
			}
		}
	}

	public LoginDto getUser() {
		return user;
	}

	public void setUser(LoginDto user) {
		this.user = user;
	}
	
	
	

}