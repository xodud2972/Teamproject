package login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.AppMain;

public class MainForm extends JFrame{
//브렌치 테스트
	JPanel p_west;
	Image img=null;
	Toolkit kit;
	
	JPanel p_center;
	
	LoginForm login;
	JoinForm join;
	
	AppMain appMain;
	
	
	public MainForm() {
		p_west = new JPanel();
		p_center = new JPanel();
		login = new LoginForm();
		join = new JoinForm(this);
		appMain = new AppMain();
		
		
		
		
		login.login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginCheck();
				
			}
		});
		login.join.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showJoin();
			}
		});
		
		join.cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showLogin();				
			}
		});
		
		
		p_west.setPreferredSize(new Dimension(1000, 800));
		p_west.setBackground(Color.DARK_GRAY);
		ImagePanel panel = new ImagePanel(new ImageIcon(getClass().getClassLoader().getResource("img.jpg")).getImage());
		p_west.add(panel);
		pack();
		
		
		add(p_west,BorderLayout.WEST);
		p_center.add(login);
		p_center.add(join);
		add(p_center,BorderLayout.CENTER);
		
		this.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					loginCheck();
				}
			}
		});
		
		setSize(1400, 800);
		setLocationRelativeTo(null);
		setVisible(true);
		setTitle("Riding Mate");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	public void showJoin() {
		login.setVisible(false);
		join.setVisible(true);
		p_center.updateUI();
		
	}
	public void showLogin() {
		login.setVisible(true);
		join.setVisible(false);
		p_center.updateUI();
		
	}
	
	public void loginCheck() {
		
		String input_id = login.t_id.getText();
		String input_pass = new String(login.t_pass.getPassword());
		
		String db_id;
		String db_pass;
		
		LoginDao conn=new LoginDao();
		try {
			LoginDto memberDto = conn.loginCheck(input_id);
			db_id = memberDto.getId();
			db_pass = memberDto.getPass();
			if(input_id.equals(db_id) && input_pass.equals(db_pass)) {
				appMain.setVisible(true);
				appMain.setUser(memberDto);
				MainForm.this.setVisible(false);
			}else {
				failLogin();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
			failLogin();
		}
	}
	
	public void failLogin() {
		JOptionPane.showMessageDialog(appMain, "로그인 정보를 확인하세요.");
		login.t_id.setText("");
		login.t_pass.setText("");
	}
	
	
	public static void main(String[] args) {
		new MainForm();
	}
}