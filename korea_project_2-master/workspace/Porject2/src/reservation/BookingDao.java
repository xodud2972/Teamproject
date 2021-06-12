package reservation;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;


public class BookingDao {
	//세션(DB처리를 위한 작업단위) 열기
	private SqlSessionFactory factory= Mybatis.getSqlSession();
	
	// 등록
	public int insertBooking(BookingDto bookingDto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		int insertBooking= sqlSession.insert("insertBooking", bookingDto);
		if(insertBooking>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertBooking;
	}
	
	
	
	public List<BookingDto> selectAll() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		List<BookingDto> list= sqlSession.selectList("selectAllBookingdetail");
		sqlSession.close();
        return list;
	}
	
	
	       
	public List<BookingDto> search(BookingDto dto) throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		
		List<BookingDto> searchBookingdetail= sqlSession.selectList("searchBookingdetail",dto);
		sqlSession.close();
		return searchBookingdetail;

	}
		
	// 수정
	
	public int updateBooking(BookingDto bookingDto) throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		int updateBooking= sqlSession.insert("updateBooking", bookingDto);
		if(updateBooking>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return updateBooking;
	}
		// 삭제
	public int deleteBooking(BookingDto bookingDto) throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		int deleteBooking= sqlSession.insert("deleteBooking", bookingDto);
		if(deleteBooking>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return deleteBooking;
	}
		// 검색
		


    }


