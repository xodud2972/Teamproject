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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import main.AppMain;
import main.Page;

public class ReservationMain extends Page {
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

   JButton bt_edit;
   JButton bt_del;
   
   // 센터
   JPanel p_center; 
   JPanel p_search; // 검색 컴포넌트 올려두는 패널
   Choice ch_category; // 검색 카테고리
   JTextField t_keyword; // 검색어입력
   JButton bt_search; 
   
   Thread thread;
   
   JTable table;
   JScrollPane scroll_table;
   String filename; // 유저의 복사에 의해 생성된 파일명
   // 테이블
   String[] columns= {"pk_booking","user","regdate", "price", "memo", "book_date"}; // 컬럼배열
   String[][] records= {};// 레코드 배열
   

   
   public ReservationMain(AppMain appMain) {
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
      

      bt_edit= new JButton("상품 수정");
      bt_del= new JButton("상품 삭제");
      
      
      // 센터
      p_center= new JPanel();
      p_search= new JPanel();
      
      ch_category= new Choice();
      // 검색 카테고리 등록
      ch_category.add("Select");
      ch_category.add("user");
      ch_category.add("regdate");
      ch_category.add("price");
      ch_category.add("memo");
      ch_category.add("bookdate");
      
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
      scroll.setPreferredSize(new Dimension(180, 180));  
      w_scroll.setPreferredSize(new Dimension(180, 180));  

      //t_bike.setPreferredSize(new Dimension(180,50));
      t_price.setPreferredSize(new Dimension(180,50));
      t_user.setPreferredSize(new Dimension(180,50));
      // 센터
      p_center.setLayout(new BorderLayout());
      ch_category.setPreferredSize(d);
      t_keyword.setPreferredSize(new Dimension(450, 30));
      
      
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
      

     //검색
     bt_search.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
           search();
        }
     });


   }
 

   // 검색 컬럼 
   public void search() {
       BookingDao bookingDao=new BookingDao();

       BookingDto bookinghDto=new BookingDto();
       bookinghDto.setSearchcategory(ch_category.getSelectedItem());
       bookinghDto.setKeyword(t_keyword.getText());
      
       try {
           List<BookingDto> list = bookingDao.search(bookinghDto);
           refresh(list);
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



