package company;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import main.AppMain;
import main.Page;
import market.FileManager;
import reservation.BookingDao;
import reservation.BookingDto;
import reservation.ReservationUnanswered;

public class CompanyMain extends Page{
	   // 서쪽
	   JPanel p_west;
	   JTextField pk_company; 
	   JTextField comp_id;
	   JTextField comp_pass; 
	   JTextField tel; 
	   JTextField addr; 
	   JTextField introduce; 
	   Thread thread;

	   // 센터
	   JPanel p_center; 
	   JPanel p_search; 
	   Choice ch_category; 
	   JTextField t_keyword; 
	   JButton bt_search; 
	   JButton bt_del;
	   
	   JTable table;
	   JScrollPane scroll_table;

	   // 테이블
	   String[] columns= {"pk_company", "comp_id ", "comp_pass", "tel", "addr","introduce"}; // 컬럼배열
	   String[][] records= {};// 레코드 배열

	   
	   public CompanyMain(AppMain appMain) {
	      super(appMain);
	      // -----------------------------------------------[생성]
	      // 서쪽 관련
	      p_west= new JPanel();
	      pk_company= new JTextField();
	      comp_id= new JTextField();
	      comp_pass= new JTextField();
	      tel= new JTextField();
	      addr= new JTextField();
	      introduce= new  JTextField();
	      
	      // 센터
	      p_center= new JPanel();
	      p_search= new JPanel();
	      ch_category= new Choice();
	      
	      // 검색 카테고리 등록
	      ch_category.add("select");
	      ch_category.add("pk_company");
	      ch_category.add("comp_id");
	      ch_category.add("comp_pass");
	      ch_category.add("tel");
	      ch_category.add("addr");
	      ch_category.add("introduce");
	      
	      t_keyword= new JTextField();
	      bt_search= new JButton("검색");
	      bt_del= new JButton("삭제");
	      
	      table= new JTable(new AbstractTableModel() {
	         public int getRowCount() {
	            return records.length;
	         }
	         public int getColumnCount() {
	            return columns.length;
	         }
	         // 컬럼 제목
	         public String getColumnName(int col) {
	            return columns[col];
	         }
	         // 각 셀에 들어갈 데이터를 이차원 배열로부터 구함
	         public Object getValueAt(int row, int col) {
	            return records[row][col];
	         }
	         // JTable의 각 셀의 값을 지정. 셀을 편집한 후 엔터치는 순간 아래의 메소드 호출
	         public void setValueAt(Object val, int row, int col) {
	            records[row][col]=(String)val; 
	         }
	         
	         public boolean isCellEditable(int row, int col) {
	            if(col==0) { // 첫번쩨 열인 product_id만 읽기전용으로 셋팅
	               return false;
	            }else {
	               return true;
	            }
	         }
	      });
	      scroll_table= new JScrollPane(table);	      
	      // -----------------------------------------------[스타일, 레이아웃]
	      // 공통크기
	      Dimension d= new Dimension(180, 30); 
	      setLayout(new BorderLayout());
	      
	      // 센터
	      p_center.setLayout(new BorderLayout());
	      bt_search.setPreferredSize(new Dimension(100,30));
	      bt_del.setPreferredSize(new Dimension(100,30));
	      ch_category.setPreferredSize(d);
	      t_keyword.setPreferredSize(new Dimension(450, 30));
	      
	      // -----------------------------------------------[조립]
	      // 센터
	      p_search.add(ch_category);
	      p_search.add(t_keyword);
	      p_search.add(bt_search);
	      p_search.add(bt_del);
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
	      
	      // 테이블 연결
	      table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				updateTable();
			}
		});
	     
      // 삭제
      bt_del.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
 	  			if(JOptionPane.showConfirmDialog(CompanyMain.this.getAppMain(), "삭제 하시겠습니까?")== JOptionPane.OK_OPTION){
 					delete();
 					getList();
 	  			}
    		}
    	});
      
      //검색
      bt_search.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            if(ch_category.getSelectedIndex()==0 && t_keyword.getText().length()==0) {
         	   getList();
            }else {
         	   int index = ch_category.getSelectedIndex();
         	   if(index!=0) {
         		   search(index);      		   
         	   }
            }
         	
         }
      });
	   }
	   
	   // 삭제
	   public void delete() {
		   CompanyDto Dto= new CompanyDto();
		   Dto.setPk_company(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),0)));
		   CompanyDao Dao= new CompanyDao();

		   try {
			int result = Dao.deleteCompany(Dto);
			if(result>0) {
				JOptionPane.showMessageDialog(this.getAppMain(), "삭제 완료");
				getList();
			}else {
				JOptionPane.showMessageDialog(this.getAppMain(), "삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }

	   public void search(int index) {
		   CompanyDto Dto= new CompanyDto();
		   CompanyDao Dao= new CompanyDao();
	       String searchcategory;
	       
	       if(index==1) {
	    	   searchcategory="comp_id";
	       }else if(index==2) {
	    	   searchcategory="comp_pass";
		   }else if(index==3) {
			   searchcategory="tel";
			}else if(index==4) {
				searchcategory="addr";
			}else{
				searchcategory="introduce";
			}	   
	       Dto.setSearchcategory(ch_category.getSelectedItem());
	       Dto.setKeyword(t_keyword.getText());
	       try {
	           List<CompanyDto> searchCompanydetail = Dao.search(Dto);
	           refresh(searchCompanydetail);
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }
	   public void getList() {
	       CompanyDao conn = new CompanyDao();
	       try {
	           List<CompanyDto> list = conn.selectAll();
	           refresh(list);
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }
	   public void refresh(List<CompanyDto> getList) {
		   String[][]data = new String[getList.size()][columns.length];
		   int index = 0;
		   while(index < getList.size()) {
			   data[index][0] = Integer.toString(getList.get(index).getPk_company());
			   data[index][1] = getList.get(index).getComp_id();
			   data[index][2] = getList.get(index).getComp_pass(); 
			   data[index][3] = getList.get(index).getTel(); 
			   data[index][4] = getList.get(index).getAddr();
			   data[index][5] = getList.get(index).getIntroduce();
			   index++;
		   }
		   records = data;
		   table.updateUI();
	   }

	   // 상세	보기
	   private void updateTable(){
		   CompanyDto dto= new CompanyDto();
		   comp_id.setText((String) table.getValueAt(table.getSelectedRow(), 1));
		   comp_pass.setText((String) table.getValueAt(table.getSelectedRow(), 2));
		   tel.setText((String) table.getValueAt(table.getSelectedRow(), 3));
		   addr.setText((String) table.getValueAt(table.getSelectedRow(), 4));
		   introduce.setText((String) table.getValueAt(table.getSelectedRow(), 5));
	  }
  }
