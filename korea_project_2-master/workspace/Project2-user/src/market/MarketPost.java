package market;

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

public class MarketPost extends Page{
   // 서쪽
   JPanel p_west;
   JButton bt_regist;
   JTextField t_title;
   JTextField t_user;
   JTextField t_price;
   JTextArea t_detail;
   JScrollPane scroll;
   JButton bt_web;
   JButton bt_file;
   Canvas can;
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
   
   Thread thread;
   // 캔버스의 사진
   Toolkit kit= Toolkit.getDefaultToolkit();
   Image image;
   JFileChooser chooser= new JFileChooser("D:\\Workspace\\KoreaIT_project_2\\workspace\\Porject2\\res");
   String filename; // 유저의 복사에 의해 생성된 파일명
   // 테이블
   String[] columns= {"pk_usermarket", "pk_user ", "title ", "content", "price ", "regdate"}; // 컬럼배열
   String[][] records= {};// 레코드 배열
    

   
   public MarketPost(AppMain appMain) {
      super(appMain);
      // -----------------------------------------------[생성]
      // 서쪽 관련
      p_west= new JPanel();
      bt_regist= new JButton("상품등록");
      t_title= new JTextField();
      t_user= new JTextField();
      t_price= new JTextField();
      t_detail= new JTextArea();
      scroll= new  JScrollPane(t_detail);
      bt_web= new JButton("웹에서 파일 찾기");
      bt_file= new JButton("내 컴퓨터에서 파일 찾기");
      
      can= new Canvas() { 
         public void paint(Graphics g) {
            g.drawImage(image, 0, 0, 180, 180, can);
         }
      };
      bt_edit= new JButton("수정");
      bt_del= new JButton("삭제");
      
      
      // 센터
      p_center= new JPanel();
      p_search= new JPanel();
      
      ch_category= new Choice();
      // 검색 카테고리 등록 (한글 깨짐)
      ch_category.add("Select");
      ch_category.add("Writer");
      ch_category.add("Content");
      
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
            //updateMarket(); // 수정
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
      can.setPreferredSize(new Dimension(180, 180));
      can.setBackground(Color.DARK_GRAY);
      
      t_title.setPreferredSize(d);
      t_user.setPreferredSize(d);
      t_price.setPreferredSize(d);

      
      // 센터
      p_center.setLayout(new BorderLayout());
      ch_category.setPreferredSize(d);
      t_keyword.setPreferredSize(new Dimension(450, 30));
      
      // -----------------------------------------------[조립]
      // 서쪽
      p_west.add(bt_regist);
      p_west.add(t_title);
      p_west.add(t_user);
      p_west.add(t_price);
      p_west.add(scroll);
      p_west.add(bt_web);
      p_west.add(bt_file);
      p_west.add(can);
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
          public void run() {
        	  selectMarketPostList();
          }
      };
      thread.start();
      // -----------------------------------------------[리스너]
      // 이미지- 웹
      bt_web.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			imgWeb();
		}
	});
      // 이미지- 내장
      bt_file.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			imgLocal();
  		}
  	});
      // 등록
      bt_regist.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				// 유효성 체크
				Integer.parseInt(t_price.getText());				
				if(JOptionPane.showConfirmDialog(MarketPost.this.getAppMain(), "등록 하시겠습니까?")== JOptionPane.OK_OPTION){
					insertMarketPost();
					selectMarketPostList();
				}
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(MarketPost.this.getAppMain(), "가격은 숫자만 입력 가능합니다.");
				t_price.setText("");
				t_price.requestFocus();
			}
			
			
		}
	});
      // 수정
      bt_edit.addActionListener(new ActionListener() {
  		public void actionPerformed(ActionEvent e) {
  			if(JOptionPane.showConfirmDialog(MarketPost.this.getAppMain(), "수정 하시겠습니까?")== JOptionPane.OK_OPTION){
  				updateMarketPost();
  			}
  		}
  	});
      // 삭제
      bt_del.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
	  			if(JOptionPane.showConfirmDialog(MarketPost.this.getAppMain(), "삭제 하시겠습니까?")== JOptionPane.OK_OPTION){
					
	  			}
    		}
    	});
      // 검색
      bt_search.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	});
      
      // 테이블 연결
      table.addMouseListener(new MouseAdapter() {
		public void mouseReleased(MouseEvent e) {
			updateTable();
		}
	});

      // 생성자 호출
   }
   
   // -------------------------------------------------------------------[이미지 등록]
   // 웹에서 사진 올리기
   public void imgWeb() {
	   String path= JOptionPane.showInputDialog(this.getAppMain(),"경로 입력");
	   URL url= null;
	   HttpURLConnection httpCon= null;
	   InputStream is= null;
	   FileOutputStream fos= null;
	   
	   try {
		url= new URL(path);
		httpCon= (HttpURLConnection)url.openConnection();
		httpCon.setRequestMethod("GET");
		
		is= httpCon.getInputStream();
		long time= System.currentTimeMillis();
		filename= time+"."+FileManager.getExtend(path,".");
		fos= new FileOutputStream("D:\\Workspace\\KoreaIT_project_2\\workspace\\Porject2\\res\\"+filename);
		
		int data= -1;
		while(true) {
			data= is.read();
			if(data== -1) break;
			fos.write(data);
		}
		JOptionPane.showMessageDialog(this.getAppMain(), "복사 완료");
	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		if(fos!=null) {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if(is!=null) {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}  
   }
   
   // 내장 폴더에서 사진 올리기
   public void imgLocal() {
	   FileInputStream fis=null;
	   FileOutputStream fos= null;
	   
	   if(chooser.showOpenDialog(this.getAppMain())== JFileChooser.APPROVE_OPTION) {
		   File file= chooser.getSelectedFile();
		   image= kit.getImage(file.getAbsolutePath());
		   can.repaint();
		   
		   try {
			fis= new FileInputStream(file);
			long time= System.currentTimeMillis();
			filename= time+"."+FileManager.getExtend(file.getAbsolutePath(),"\\");
			fos= new FileOutputStream("D:\\Workspace\\KoreaIT_project_2\\workspace\\Porject2\\res\\"+filename);
			
			int data= -1;
			byte[] buff= new byte[1024];
			while(true) {
				data= fis.read(buff);
				if(data== -1)break;
				fos.write(buff);
			}
			JOptionPane.showMessageDialog(this.getAppMain(), "복사 완료");
		   } catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(fos!= null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(fis!= null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		   
	   } 
   }
   // -------------------------------------------------------------------[메소드]
   // 상품 등록(사진 유/무 둘다 업로드 가능)
   public void insertMarketPost() {
	   MarketPostDto marketDto= new MarketPostDto();
	   marketDto.setPk_user(t_user.getText());
	   marketDto.setTitle(t_title.getText());
	   marketDto.setPrice(t_price.getText());
	   marketDto.setContent(t_detail.getText());
	   marketDto.setFilename(filename);
	   MarketPostDao marketDao= new MarketPostDao();
	   try {
		   int result= marketDao.insertMarketPost(marketDto);
		if(result>0) {
			JOptionPane.showMessageDialog(this.getAppMain(), "등록 완료");
		}else {
			JOptionPane.showMessageDialog(this.getAppMain(), "등록 실패");
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
   }
   // 수정
   public void updateMarketPost() {
	   MarketPostDto marketDto= new MarketPostDto();
	   String pk_usermarket=(String)table.getValueAt(table.getSelectedRow(), 0);
	   marketDto.setTitle(t_title.getText());
	   marketDto.setPk_usermarket(pk_usermarket);
	   marketDto.setPrice(t_price.getText());
	   marketDto.setContent(t_detail.getText());
	   marketDto.setFilename(filename);
	   MarketPostDao marketDao= new MarketPostDao();
	   image= kit.getImage("D:\\Workspace\\KoreaIT_project_2\\workspace\\Porject2\\res\\"+filename.toString());
	   can.repaint();
	   try {
			int result= marketDao.updateMarketPost(marketDto);
			if(result>0) {
				JOptionPane.showMessageDialog(this.getAppMain(), "수정 완료");
			}else {
				JOptionPane.showMessageDialog(this.getAppMain(), "수정 실패");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
   }
   
   // 삭제
   public void edit() {

	
   }
   // 목록 보기
   public void selectMarketPostList() {
	   MarketPostDao marketDao= new MarketPostDao();
	   try {
           List<MarketPostDto> selectMarketPostList = marketDao.selectMarketPostList();
           showtable(selectMarketPostList);
       } catch (Exception e) {
           e.printStackTrace();
       }
   }
   public void showtable(List<MarketPostDto> selectMarketPostList){

       String[][] data = new String[selectMarketPostList.size()][columns.length];

       int index = 0;
       while (index < selectMarketPostList.size()) {
           data[index][0] = selectMarketPostList.get(index).getPk_usermarket();
           data[index][1] = selectMarketPostList.get(index).getId();
           data[index][2] = selectMarketPostList.get(index).getTitle();
           data[index][3] = selectMarketPostList.get(index).getPrice();
           data[index][4] = selectMarketPostList.get(index).getContent();
           data[index][5] = selectMarketPostList.get(index).getRegdate();
           index++;
       }

       records = data;

       table.updateUI();
   }
   // 상세	보기
   private void updateTable(){
	   MarketPostDto marketDto= new MarketPostDto();
	   t_title.setText((String) table.getValueAt(table.getSelectedRow(), 2));
	   t_user.setText((String) table.getValueAt(table.getSelectedRow(), 1));
	   t_price.setText((String) table.getValueAt(table.getSelectedRow(), 3));
	   t_detail.setText((String) table.getValueAt(table.getSelectedRow(), 4));
	   image= kit.getImage("D:\\Workspace\\KoreaIT_project_2\\workspace\\Porject2\\res\\"+filename);
	   can.repaint();  
  }

   
   
}