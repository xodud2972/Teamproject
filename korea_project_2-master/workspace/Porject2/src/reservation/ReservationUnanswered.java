package reservation;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import main.AppMain;
import main.Page;
import market.MarketPostDto;

public class ReservationUnanswered extends Page {
   // 서쪽
   JPanel p_west;
   JButton bt_regist;
   JTextField t_booking; // 유저 이름 
   JTextField t_user; // 유저 이름 
   JTextField regdate; //  유저 바이크
   JTextField t_price; // 가격 // 원하는 개수
   JTextArea t_memo; // 필요한 물건 및 예약내용
   JTextArea book_date; // 필요한 물건 및 예약내용
   JScrollPane scroll;
   JScrollPane w_scroll;

   Thread thread;
   
   JButton bt_edit;
   JButton bt_del;
   
   // 센터
   JPanel p_center; 
   JPanel p_search; // 검색 컴포넌트 올려두는 패널
   Choice ch_category; // 검색 카테고리
   JTextField t_keyword; // 검색어입력
   JButton bt_search; 
   
   JTable table;
   JScrollPane scroll_table;
   
   // 테이블
   String[] columns= {"pk_booking","user","regdate", "price", "memo", "book_date"}; // 컬럼배열
   String[][] records= {};// 레코드 배열
   


   
   public ReservationUnanswered(AppMain appMain) {
      super(appMain);
      // -----------------------------------------------[생성]
      // 서쪽 관련
      p_west= new JPanel();
      bt_regist= new JButton("답변 등록");
      t_user= new JTextField();
      regdate= new JTextField();
      t_price= new JTextField();
      t_memo= new JTextArea();
      book_date= new JTextArea();
      scroll= new  JScrollPane(t_memo);
      w_scroll = new  JScrollPane(book_date);

      bt_edit= new JButton("답변 수정");
      bt_del= new JButton("답변 삭제");
      
      // 센터
      p_center= new JPanel();
      p_search= new JPanel();
      
      ch_category= new Choice();
      // 검색 카테고리 등록
      ch_category.add("Select");
      ch_category.add("pk_user");
      ch_category.add("regdate");
      ch_category.add("price");
      ch_category.add("memo");
      ch_category.add("book_date");
     
      t_keyword= new JTextField();
      bt_search= new JButton("검색");
      
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
            updateBooking();
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
      
      // 서쪽
      p_west.setPreferredSize(new Dimension(200, 700));
      scroll.setPreferredSize(new Dimension(180, 140));  
      w_scroll.setPreferredSize(new Dimension(180, 140));  

      bt_regist.setPreferredSize(new Dimension(180,50));
      bt_edit.setPreferredSize(new Dimension(180,50));
      bt_del.setPreferredSize(new Dimension(180,50));
      
      t_user.setPreferredSize(new Dimension(180,140));
      t_price.setPreferredSize(new Dimension(180,140));
      // 센터
      p_center.setLayout(new BorderLayout());
      ch_category.setPreferredSize(d);
      t_keyword.setPreferredSize(new Dimension(450, 30));
      
      // -----------------------------------------------[조립]
      
      // 서쪽
      p_west.add(bt_regist);
      p_west.add(t_user);
      p_west.add(t_price);
      p_west.add(scroll);
      p_west.add(w_scroll);

      p_west.add(bt_edit);
      p_west.add(bt_del);
      add(p_west, BorderLayout.WEST);
      
      // 센터
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
      // -----------------------------------------------[리스너]
      // 테이블 연결
      table.addMouseListener(new MouseAdapter() {
		public void mouseReleased(MouseEvent e) {
			updateTable();
		}
	});
      
    // 등록
     bt_regist.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
    	  try {
				// 유효성 체크
				Integer.parseInt(t_price.getText());				
				if(JOptionPane.showConfirmDialog(ReservationUnanswered.this.getAppMain(), "등록 하시겠습니까?")== JOptionPane.OK_OPTION){
					insertBookingPost();
					getList();
				}
			}catch(NumberFormatException e1){
				
			}
      }
   });
     
     // 수정
     bt_edit.addActionListener(new ActionListener() {
 		public void actionPerformed(ActionEvent e) {
 			if(JOptionPane.showConfirmDialog(ReservationUnanswered.this.getAppMain(), "수정 하시겠습니까?")== JOptionPane.OK_OPTION){
 				updateBooking();
 				getList();
 			}
 		}
 	});
     
     // 삭제
     bt_del.addActionListener(new ActionListener() {
   		public void actionPerformed(ActionEvent e) {
  			if(JOptionPane.showConfirmDialog(ReservationUnanswered.this.getAppMain(), "삭제 하시겠습니까?")== JOptionPane.OK_OPTION){
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

   // 상품 등록
   public void insertBookingPost() {
	   BookingDto bookingDto= new BookingDto();
	   BookingDao bookingDao= new BookingDao();
	   
	   //bookingDto.setPk_booking(Integer.parseInt(t_booking.getText()));
	   bookingDto.setPk_user(Integer.getInteger(t_user.getText()));
	   //bookingDto.setRegdate(regdate.getText());
	   bookingDto.setPrice(t_price.getText());
	   bookingDto.setMemo(t_memo.getText());
	   bookingDto.setBook_date(book_date.getText());
	   
	   try {
		int result= bookingDao.insertBooking(bookingDto);
		if(result>0) {
			JOptionPane.showMessageDialog(this.getAppMain(), "등록 완료");
			table.updateUI();
		}else {
			JOptionPane.showMessageDialog(this.getAppMain(), "등록 실패");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
   
   // 삭제
   public void delete() {
	   BookingDto bookingDto= new BookingDto();
	   bookingDto.setPk_booking(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),0)));
	   BookingDao bookingDao= new BookingDao();

	   try {
		int result = bookingDao.deleteBooking(bookingDto);
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
   
   
   // 상세	보기
   private void updateTable(){
	   BookingDto dto= new BookingDto();
	   t_user.setText((String) table.getValueAt(table.getSelectedRow(), 1));
	   regdate.setText((String) table.getValueAt(table.getSelectedRow(), 2));
	   t_price.setText((String) table.getValueAt(table.getSelectedRow(), 3));
	   t_memo.setText((String) table.getValueAt(table.getSelectedRow(), 4));
	   book_date.setText((String) table.getValueAt(table.getSelectedRow(), 5));
  }
   
   public void updateBooking() {
      BookingDto dto= new BookingDto();	  
	  dto.setPk_booking(Integer.parseInt((String) table.getValueAt(table.getSelectedRow(),0)));
	  //dto.setPk_user(Integer.parseInt(t_user.getText()));
	  dto.setRegdate(regdate.getText());
	  dto.setPrice(t_price.getText());
	  dto.setMemo(t_memo.getText());
	  dto.setBook_date(book_date.getText());
	  BookingDao conn= new BookingDao();

	  System.out.println(dto.getPk_booking());
	  System.out.println(dto.getPk_user());
	  System.out.println(dto.getRegdate());
	  System.out.println(dto.getPrice());
	  System.out.println(dto.getMemo());
	  System.out.println(dto.getBook_date());
	  
	   try {
			int result= conn.updateBooking(dto);
			if(result>0) {
				JOptionPane.showMessageDialog(this.getAppMain(), "수정 완료");
			}else {
				JOptionPane.showMessageDialog(this.getAppMain(), "수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
   }
   
   public void search(int index) {
       BookingDao bookingDao=new BookingDao();

       BookingDto bookinghDto=new BookingDto();
       String searchcategory;
       
       if(index==1) {
    	   searchcategory="pk_user";
       }else if(index==2) {
    	   searchcategory="regdate";
	   }else if(index==3) {
		   searchcategory="price";
		}else if(index==4) {
			searchcategory="memo";
		}else{
			searchcategory="book_date";
		}	   
       bookinghDto.setSearchcategory(ch_category.getSelectedItem());
       bookinghDto.setKeyword(t_keyword.getText());

       try {
           List<BookingDto> searchBookingdetail = bookingDao.search(bookinghDto);
           refresh(searchBookingdetail);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public void getList() {
       BookingDao conn = new BookingDao();
       try {
           List<BookingDto> list = conn.selectAll();
           refresh(list);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   public void refresh(List<BookingDto> getList) {
	   String[][]data = new String[getList.size()][columns.length];
	   
	   int index = 0;
	   while(index < getList.size()) {
		   data[index][0] = Integer.toString(getList.get(index).getPk_booking());
		   data[index][1] = Integer.toString(getList.get(index).getPk_user());
		   data[index][2] = getList.get(index).getRegdate(); 
		   data[index][3] = getList.get(index).getPrice();
		   data[index][4] = getList.get(index).getMemo();
		   data[index][5] = getList.get(index).getBook_date();
		   index++;
	   }
	   records = data;
	   table.updateUI();
   }
  }


