package market;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import main.AppMain;
import main.Page;

public class MarketReview extends Page{
	   // 서쪽
	   JPanel p_west;
	   JTextField t_seller;
	   JTextField t_buyer;
	   JTextArea t_content;
	   JScrollPane scroll;
	   JButton bt_del;
	   
	   // 라디오박스
	   JRadioButton radio_hide;
	   JRadioButton radio_show;
	   ButtonGroup radioGroup;
	   // 센터
	   JPanel p_center;
	   JPanel p_search;
	   Choice ch_category; 
	   JTextField t_keyword; 
	   JButton bt_search; 
	   
	   // 테이블
	   JTable table;
	   JScrollPane scroll_table;	   
	   Thread thread;
	   String[] columns= {"No","Bought product","Content", "Seller", "Buyer ", "Score", "Reservation Date", "Hide"}; 
	   String[][] records= {};
	   int flag=1;
	public MarketReview(AppMain appMain) {
		super(appMain);
	      // -----------------------------------------------[생성]
	      // 서쪽 관련
	      p_west= new JPanel();
	      t_seller= new JTextField();
	      t_buyer= new JTextField();
	      t_content= new JTextArea();
	      scroll= new  JScrollPane(t_content);
	      bt_del= new JButton("삭제");
	      
	      // 라디오 박스
	      radioGroup= new ButtonGroup();
	      radio_hide= new JRadioButton("숨기기");
	      radio_show= new JRadioButton("보이기");
	      radioGroup.add(radio_hide);
	      radioGroup.add(radio_show);
	      // 센터
	      p_center= new JPanel();
	      p_search= new JPanel();
	      
	      ch_category= new Choice();
	      // 검색 카테고리 등록 (한글 깨짐)
	      ch_category.add("Select");
	      ch_category.add("Writer");
	      ch_category.add("Content");
	      ch_category.add("Score");
	      
	      t_keyword= new JTextField();
	      bt_search= new JButton("검색");
	      
	      table= new JTable(new AbstractTableModel() {
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
	            records[row][col]=(String)val; 
	         }
	         
	         public boolean isCellEditable(int row, int col) {
	            if(col==0) { 
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
	      
	      t_seller.setPreferredSize(d);
	      t_buyer.setPreferredSize(d);

	      
	      // 센터
	      p_center.setLayout(new BorderLayout());
	      ch_category.setPreferredSize(d);
	      t_keyword.setPreferredSize(new Dimension(450, 30));
	      
	      // -----------------------------------------------[조립]
	      // 서쪽
	      p_west.add(t_seller);
	      p_west.add(t_buyer);
	      p_west.add(scroll);
	      p_west.add(radio_hide);
	      p_west.add(radio_show);
	      p_west.add(bt_del);
	      add(p_west, BorderLayout.WEST);
	      
	      // 센터
	      p_search.add(ch_category);
	      p_search.add(t_keyword);
	      p_search.add(bt_search);
	      p_center.add(p_search, BorderLayout.NORTH);
	      p_center.add(scroll_table);
	      add(p_center);
	      
	      // -----------------------------------------------[쓰레드]
	      thread = new Thread() {
	          public void run() {
	        	  reviewList();
	          }
	      };
	      thread.start();
	      
	 
	      // -----------------------------------------------[리스너]
	      
	      // 숨기기
	      radio_hide.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			if(e.getSource()==radio_hide){
	  				flag=0;
	  				hideReview();
	  			}	
	  		}
	  	});
	      //보이기
	      radio_show.addActionListener(new ActionListener() {
	  		public void actionPerformed(ActionEvent e) {
	  			if(e.getSource()==radio_show) {
	  				flag=1;
	  				hideReview();
	  			}
	  		}
	  	});
	      // 삭제
	      bt_del.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
		  			if(JOptionPane.showConfirmDialog(MarketReview.this.getAppMain(), "삭제 하시겠습니까?")== JOptionPane.OK_OPTION){
		  				deleteReview();
		  			}
	    		}
	    	});
	      // 검색
	      bt_search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 검색 안 하면 모든 데이터가 나오게
				if(ch_category.getSelectedIndex()==0 && t_keyword.getText().length()==0) {
					reviewList();
				}else {
					// 검색 하면 해당 검색 결과만 나오게
					int index= ch_category.getSelectedIndex();
					if (index !=0) {
						reviewSearch(index);						
					}
				}
			}
		});
	      
	      // 테이블 연결
	      table.addMouseListener(new MouseAdapter() {
			public void mouseReleased(MouseEvent e) {
				detailTable();
			}
		});

	   }
	// -----------------------------------------------[메소드]
		
		// 삭제
	   public void deleteReview() {
		   MarketReviewDto reviewDto= new MarketReviewDto();
		   reviewDto.setPk_user_review((String)table.getValueAt(table.getSelectedRow(), 0));
		   reviewDto.setContent(t_content.getText());
		   reviewDto.setSeller(t_seller.getText());
		   reviewDto.setBuyer(t_buyer.getText());
		   MarketReviewDao reviewDao= new MarketReviewDao();
		   try {
			int result = reviewDao.deleteReview(reviewDto);
			if(result>0) {
				JOptionPane.showMessageDialog(this.getAppMain(), "삭제 완료");
				reviewList();
			}else {
				JOptionPane.showMessageDialog(this.getAppMain(), "삭제 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	   
	   // 목록 보기
	   public void reviewList() {
		   
		   MarketReviewDao reviewDao= new MarketReviewDao();
		   try {
	           List<MarketReviewDto> selectReviewList = reviewDao.selectReviewList();
	           showtable(selectReviewList);
	       } catch (Exception e) {
	           e.printStackTrace();
	       }
	   }
	   public void showtable(List<MarketReviewDto> selectReviewList){
	       String[][] data = new String[selectReviewList.size()][columns.length];
	       int index = 0;
	       while (index < selectReviewList.size()) {
	           data[index][0] = selectReviewList.get(index).getPk_user_review();
	           data[index][1] = selectReviewList.get(index).getPk_usermarket();
	           data[index][2] = selectReviewList.get(index).getContent();
	           data[index][3] = selectReviewList.get(index).getSeller();
	           data[index][4] = selectReviewList.get(index).getBuyer();
	           data[index][5] = selectReviewList.get(index).getScore();
	           data[index][6] = selectReviewList.get(index).getRev_date();
	           data[index][7] = Integer.toString(selectReviewList.get(index).isHide());
	           index++;
	       }

	       records = data;

	       table.updateUI();
	   }
	   // 상세	보기
	   private void detailTable(){
		   t_content.setText((String) table.getValueAt(table.getSelectedRow(), 1));
		   t_seller.setText((String) table.getValueAt(table.getSelectedRow(), 2));
		   t_buyer.setText((String) table.getValueAt(table.getSelectedRow(), 3));
	  }
	   
	   // 검색
	   public void reviewSearch(int index) {
		   MarketReviewDao reviewDao= new MarketReviewDao();
		   MarketReviewDto reviewDto= new MarketReviewDto();
		   String reviewCategory;
		   
		   if(index==1) {
			   reviewCategory="buyer";
		   }else if(index==2) {
			   reviewCategory="content";
		   }else{
			   reviewCategory="score";
		   }
		   
		   reviewDto.setReviewCategory(reviewCategory);
		   reviewDto.setReviewKeyword(t_keyword.getText());
		   try {
			List<MarketReviewDto> selectReviewSearch= reviewDao.selectReviewSearch(reviewDto);
			showtable(selectReviewSearch);
		} catch (Exception e) {
			e.printStackTrace();
		}
	   }
	   // 숨기기/보이기
	   public void hideReview() {
		   MarketReviewDto reviewDto= new MarketReviewDto();  
		   reviewDto.setPk_user_review((String)table.getValueAt(table.getSelectedRow(), 0));
		   reviewDto.setHide(flag);
		   MarketReviewDao reviewDao= new MarketReviewDao();
		   try {
				int result=reviewDao.updateDisplayState(reviewDto);
				if(result>0) {
					JOptionPane.showMessageDialog(this.getAppMain(), "설정 완료");
					reviewList();
				}else {
					JOptionPane.showMessageDialog(this.getAppMain(), "설정 실패");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}	
	   }
}
