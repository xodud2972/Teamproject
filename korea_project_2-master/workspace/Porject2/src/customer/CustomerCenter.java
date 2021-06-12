package customer;

import main.AppMain;
import main.Page;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomerCenter extends Page{
	AppMain appMain;
	CustomerCenterAns customerCenterAns;
	JPanel p_west,p_east;
	JPanel p_south,p_south_left,p_south_center,p_south_right;

	JPanel p_center;
	JPanel p_center_sub1,p_center_sub2;
	JPanel p_search;
	Choice ch_category;
	JTextField t_keyword;
	JButton bt_search;

	JTextField t_target;
	JButton bt_viewAns;
	JButton bt_rollbackTable;
	JTextArea t_ans;
	JButton bt_sendAns;

	JTable table;
	JTable comTable;
	JScrollPane scroll_table;
	JScrollPane comScroll_table;

	Thread thread;

	String[] columns = {"no", "title", "content", "pk_user", "regdate", "answered"};
	String[][] records = {};

	String[] ComColumns = {"no", "제목", "내용", "등록일"};
	String[][] ComRecords = {};

	public CustomerCenter(AppMain appMain) {
		super(appMain);
		this.appMain=appMain;

		p_west = new JPanel();
		p_east = new JPanel();
		p_center = new JPanel();

		p_search = new JPanel();
		//답변입력영역
		p_south=new JPanel();
		p_south_left=new JPanel();
		p_south_center=new JPanel();
		p_south_right=new JPanel();
		t_target=new JTextField();
		bt_rollbackTable=new JButton("질문목록");
		bt_viewAns=new JButton("답변목록");
		t_ans=new JTextArea();
		bt_sendAns=new JButton("답변등록");


		ch_category = new Choice();
		for(String string_category : columns){
			ch_category.add(string_category);
		}


		t_keyword = new JTextField();
		bt_search = new JButton("검색");

		table = new JTable(new AbstractTableModel() {
			public int getRowCount() {
				return records.length;
			}

			public int getColumnCount() {
				return columns.length;
			}

			public String getColumnName(int col) {
				return columns[col];
			}

			public Object getValueAt(int row, int col) {
				return records[row][col];
			}

			public void setValueAt(Object val, int row, int col) {
				records[row][col] = (String) val;
			}

			public boolean isCellEditable(int row, int col) {
				if (col == 0) {
					return false;
				} else {
					return true;
				}
			}
		});


		scroll_table = new JScrollPane(table);
		comScroll_table = new JScrollPane(comTable);



		Dimension d = new Dimension(180, 30);
		setLayout(new BorderLayout());

		p_center.setLayout(new BorderLayout());
		ch_category.setPreferredSize(d);
		t_keyword.setPreferredSize(new Dimension(450, 30));
		t_target.setPreferredSize(new Dimension( 180,30));

		//모양 잡아주기
		p_west.setPreferredSize(new Dimension(7,800));
		add(p_west, BorderLayout.WEST);
		p_east.setPreferredSize(new Dimension(7,800));
		add(p_east, BorderLayout.EAST);
		t_ans.setPreferredSize(new Dimension(800,60));
		bt_sendAns.setPreferredSize(new Dimension(100,60));

		t_target.setDisabledTextColor(Color.BLACK);
		t_target.setHorizontalAlignment(JTextField.CENTER);
		t_target.setEnabled(false);
		t_target.setText("섹-시한 강신혁");

		p_south_left.add(t_target);
		p_south_left.add(bt_rollbackTable);
		p_south_left.add(bt_viewAns);
		p_south_left.setPreferredSize(new Dimension(200,130));
		p_south.add(p_south_left,BorderLayout.WEST);
		p_south_center.add(t_ans);
		p_south_center.setPreferredSize(new Dimension(800,130));
		p_south.add(p_south_center,BorderLayout.CENTER);
		p_south_right.add(bt_sendAns);
		p_south_right.setPreferredSize(new Dimension(110,130));
		p_south.add(p_south_right,BorderLayout.EAST);
		p_south.setPreferredSize(new Dimension(1160,130));
		add(p_south, BorderLayout.SOUTH);

		// 검색영역
		p_center_sub1=new JPanel();
		p_center_sub2=new JPanel();
		p_search.add(ch_category);
		p_search.add(t_keyword);
		p_search.add(bt_search);
		p_center.add(p_search, BorderLayout.NORTH);
		p_center.add(scroll_table);
		add(p_center);

		thread = new Thread() {
			@Override
			public void run() {
				getList();
			}
		};
		thread.start();


		//이벤트리스너
		bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					int index= ch_category.getSelectedIndex();
					if (index >=0) {
						search(index);						
					}
			}
		});
		bt_viewAns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (t_target.getText().equals("섹-시한 강신혁")) {
					JOptionPane.showMessageDialog(appMain,"조회할 문의를 선택하세요");
				}else {
					appMain.pages[11].setVisible(false);
					appMain.subCustomer.setVisible(true);
					appMain.subCustomer.getList2();
					appMain.subCustomer.t_ans.setText(t_ans.getText());
				}
			}
		});
		bt_rollbackTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.pages[11].setVisible(true);
				appMain.subCustomer.setVisible(false);
			}
		});
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				t_target.setText((String) table.getValueAt(table.getSelectedRow(), 0));
				appMain.subCustomer.t_target.setText((String) table.getValueAt(table.getSelectedRow(), 0));
			}
		});
	}

	private CustomerCenter getCustomerCenter(){
		return this;
	}
	private void getList() {
		CustomerDao conn = new CustomerDao();
		try {
			java.util.List<CustomerDto> list= conn.selectAll();
			refresh(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void search(int index) {
			CustomerDao customerDao= new CustomerDao();
			CustomerDto customerDto= new CustomerDto();
		   String customerCategory;
		   if(index==0) {
			   // 문제의 부분
			   customerCategory="pk_customerservice";
		   }else if(index==1) {
			   customerCategory="title";
		   }else if(index==2) {
			   customerCategory="content";
		   }else if(index==3) {
			   customerCategory="pk_user";
		   }else if(index==4) {
			   customerCategory="regdate";
		   }else {
			   customerCategory="answered";
		   }
		   
		   customerDto.setCustomerCategory(customerCategory);
		   customerDto.setCustomerKeyword(t_keyword.getText());
		   try {
			List<CustomerDto> searchCustomer= customerDao.searchCustomer(customerDto);
			refresh(searchCustomer);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }

	private void refresh(List<CustomerDto> list){
		String[][] data = new String[list.size()][columns.length];

		int index = 0;
		while (index < list.size()) {
			data[index][0] = list.get(index).getPk_customerservice();
			data[index][1] = list.get(index).getTitle();
			data[index][2] = list.get(index).getContent();
			data[index][3] = list.get(index).getName();
			data[index][4] = list.get(index).getRegdate();
			if(list.get(index).getAnswered().equals("0")){
				data[index][5] = "미 답변";
			}else if(list.get(index).getAnswered().equals("1")){
				data[index][5] = "답변 완료";
			}else{
				data[index][5] = "재 답변 요청";
			}
			index++;
		}

		records = data;

		table.updateUI();
	}
}