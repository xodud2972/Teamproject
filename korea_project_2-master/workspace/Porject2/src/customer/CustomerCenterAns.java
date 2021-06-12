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

public class CustomerCenterAns extends Page{
	CustomerCenter customerCenter;



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

	JTable comTable;
	JScrollPane comScroll_table;

	Thread thread;

	String[] columns = {"no", "title", "content", "pk_user", "regdate", "answered"};

	String[] ComColumns = {"no", "내용", "등록일"};
	String[][] ComRecords = {};

	public void setCustomerCenter(CustomerCenter customerCenter) {
		this.customerCenter = customerCenter;
	}

	public CustomerCenterAns(AppMain appMain) {
		super(appMain);
		CustomerCenter customerCenter = null;
		p_west = new JPanel();
		p_east = new JPanel();
		p_center = new JPanel();

		p_search = new JPanel();
		//답변입력영역
		p_south=new JPanel();
		p_south_left=new JPanel();
		p_south_center=new JPanel();
		p_south_right=new JPanel();
		t_target= new JTextField();
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

		comTable = new JTable(new AbstractTableModel() {
			public int getRowCount() {
				return ComRecords.length;
			}

			public int getColumnCount() {
				return ComColumns.length;
			}

			public String getColumnName(int col) {
				return ComColumns[col];
			}

			public Object getValueAt(int row, int col) {
				return ComRecords[row][col];
			}

			public void setValueAt(Object val, int row, int col) {
				ComRecords[row][col] = (String) val;
			}

			public boolean isCellEditable(int row, int col) {
				if (col == 0) {
					return false;
				} else {
					return true;
				}
			}
		});

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
		p_center.add(comScroll_table);
		add(p_center);

		thread = new Thread() {
			@Override
			public void run() {
//				getList2();
			}
		};
		thread.start();


		//이벤트리스너
		bt_search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.pages[11].setVisible(true);
				appMain.subCustomer.setVisible(false);
				CustomerCenter ct= (CustomerCenter)appMain.pages[11];
				ct.t_keyword.setText(t_keyword.getText());
			}
		});
		
		bt_viewAns.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.pages[11].setVisible(false);
				appMain.subCustomer.setVisible(true);
				
				getList2();

			}
		});
		bt_rollbackTable.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				appMain.pages[11].setVisible(true);
				appMain.subCustomer.setVisible(false);
				CustomerCenter ct= (CustomerCenter)appMain.pages[11];
				ct.t_ans.setText(t_ans.getText());
			}
		});
	}

	public void getList2() {
		CustomerDao conn = new CustomerDao();
		CustomerAnsDto dto=new CustomerAnsDto();
		dto.setPk_customerservice(t_target.getText());
		try {
			List<CustomerAnsDto> list=conn.searchAnsDetail(dto);
			String[][] data = new String[list.size()][columns.length];

			int index = 0;
			while (index < list.size()) {
				data[index][0] = list.get(index).getPk_customerans();
				data[index][1] = list.get(index).getContent();
				data[index][2] = list.get(index).getRegdate();
				index++;
			}

			ComRecords = data;

			comTable.updateUI();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}