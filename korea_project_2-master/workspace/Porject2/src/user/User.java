package user;

import main.AppMain;
import main.Page;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class User extends Page{
	JPanel p_west;
	JPanel p_east;
	JPanel p_south;

	JPanel p_center;
	JPanel p_search;
	Choice ch_category;
	JTextField t_keyword;
	JButton bt_search;

	JTable table;
	JScrollPane scroll_table;

	Thread thread;


	String[] columns={"pk_user","id","pass","name","phone","email","regdate","level","birth","user_nickname"};
	String[][] records = {};
	public User(AppMain appMain) {
		super(appMain);
		p_south=new JPanel();
		p_west = new JPanel();
		p_east = new JPanel();
		p_center = new JPanel();
		p_search = new JPanel();

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
				updateTable();
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

		Dimension d = new Dimension(180, 30);
		setLayout(new BorderLayout());

		p_center.setLayout(new BorderLayout());
		ch_category.setPreferredSize(d);
		t_keyword.setPreferredSize(new Dimension(450, 30));

		//모양 잡아주기
		p_west.setPreferredSize(new Dimension(7,800));
		add(p_west, BorderLayout.WEST);
		p_east.setPreferredSize(new Dimension(7,800));
		add(p_east, BorderLayout.EAST);
		p_south.setPreferredSize(new Dimension(1160,42));
		add(p_south, BorderLayout.SOUTH);

		// 검색영역
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
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(ch_category.getSelectedItem());
				search();
			}
		});
	}

	private void search() {
		UserDao conn=new UserDao();

		UserDto searchDto=new UserDto();
		searchDto.setSearchcategory(ch_category.getSelectedItem());
		searchDto.setKeyword(t_keyword.getText());
		try {
			List<UserDto> list = conn.search(searchDto);
			refresh(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void updateTable(){
		UserDao conn = new UserDao();
		UserDto dto = new UserDto();

		dto.setPk_user((String) table.getValueAt(table.getSelectedRow(), 0));
		dto.setId((String) table.getValueAt(table.getSelectedRow(), 1));
		dto.setPass((String) table.getValueAt(table.getSelectedRow(), 2));
		dto.setName((String) table.getValueAt(table.getSelectedRow(), 3));
		dto.setPhone((String) table.getValueAt(table.getSelectedRow(), 4));
		dto.setEmail((String) table.getValueAt(table.getSelectedRow(), 5));
		dto.setRegdate((String) table.getValueAt(table.getSelectedRow(), 6));
		dto.setLevel((String) table.getValueAt(table.getSelectedRow(), 7));
		dto.setBirth((String) table.getValueAt(table.getSelectedRow(), 8));
		dto.setUser_nickname((String) table.getValueAt(table.getSelectedRow(), 9));

		int result = -1;
		try {
			result = conn.update(dto);
			if (result > 0) {
				JOptionPane.showMessageDialog(this.getAppMain(), "업데이트 완료");
			} else {
				JOptionPane.showMessageDialog(this.getAppMain(), "업데이트 실패");
			}
		} catch (NullPointerException e) {
			result = -2;
		} catch (Exception e) {
			e.printStackTrace();
			result = -1;
		}
		System.out.println(result);
	}

	public void getList() {
		UserDao conn = new UserDao();

		try {
			List<UserDto> list = conn.selectAll();
			refresh(list);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	public void refresh(List<UserDto> list){

		String[][] data = new String[list.size()][columns.length];

		int index = 0;
		while (index < list.size()) {
			data[index][0] = list.get(index).getPk_user();
			data[index][1] = list.get(index).getId();
			data[index][2] = list.get(index).getPass();
			data[index][3] = list.get(index).getName();
			data[index][4] = list.get(index).getPhone();
			data[index][5] = list.get(index).getEmail();
			data[index][6] = list.get(index).getRegdate();
			data[index][7] = list.get(index).getLevel();
			data[index][8] = list.get(index).getBirth();
			data[index][9] = list.get(index).getUser_nickname();
			index++;
		}

		records = data;

		table.updateUI();
	}
}
