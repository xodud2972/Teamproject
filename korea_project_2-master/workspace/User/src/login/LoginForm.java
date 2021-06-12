package login;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import util.MemberDao;
import util.MemberDto;

public class LoginForm extends JPanel{
	
	JPanel nul;
	JLabel id;
	JTextField t_id;
	JLabel pass;
	JPasswordField t_pass;
	
	JPanel nul2;
	JButton login;
	JButton join;
	
	public LoginForm() {
		nul = new JPanel();
		id = new JLabel("ID");
		t_id = new JTextField(20);
		pass = new JLabel("PASSWORD");
		t_pass = new JPasswordField(20);
		nul2 = new JPanel();
		
		login = new JButton("LOGIN");
		join = new JButton("JOIN");
		
		nul.setPreferredSize(new Dimension(380, 200));
		nul.setBackground(Color.gray);
		id.setPreferredSize(new Dimension(100, 30));
		pass.setPreferredSize(new Dimension(100, 30));
		login.setPreferredSize(new Dimension(130, 40));
		join.setPreferredSize(new Dimension(130, 40));
		nul2.setPreferredSize(new Dimension(380, 400));
		nul2.setBackground(Color.gray);
		
		add(nul);
		add(id);
		add(t_id);
		add(pass);
		add(t_pass);
		add(nul2);
		add(login);
		add(join);
		
	
		
		
		
		setVisible(true);
		setBackground(Color.gray);
		setPreferredSize(new Dimension(380, 800));
		setLayout(new FlowLayout());
	}
	
	
}
