package reservation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;


public class BookingDao {
	//세션(DB처리를 위한 작업단위) 열기
	private SqlSessionFactory factory= Mybatis.getSqlSession();
	
	// 등록
	public int insertReservation(BookingDto bookDto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		int insertReservation= sqlSession.insert("bookDto", bookDto);
		if(insertReservation>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertReservation;
	}
	
	
	
	public List<BookingDto> selectReservation() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		//SQL문 사용
		List<BookingDto> ReservationList= sqlSession.selectList("selectReservationAll");
		sqlSession.close();
		return ReservationList;
	}
	


    }


