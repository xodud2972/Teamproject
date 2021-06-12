package company;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import reservation.BookingDto;
import util.Mybatis;

public class CompanyDao {

	//세션(DB처리를 위한 작업단위) 열기
	private SqlSessionFactory factory= Mybatis.getSqlSession();
	
	public List<CompanyDto> selectAll() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		List<CompanyDto> list= sqlSession.selectList("selectAllCompanydetail");
		sqlSession.close();
        return list;
	}
	
	
	       
	public List<CompanyDto> search(CompanyDto dto) throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		
		List<CompanyDto> searchCompanydetail= sqlSession.selectList("searchCompanydetail",dto);
		sqlSession.close();
		return searchCompanydetail;

	}
	
	// 삭제
	public int deleteCompany(CompanyDto dto) throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		int deleteBooking= sqlSession.insert("deleteCompany", dto);
		if(deleteBooking>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return deleteBooking;
	}
	
}
