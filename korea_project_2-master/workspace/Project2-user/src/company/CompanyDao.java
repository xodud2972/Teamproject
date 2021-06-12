package company;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import reservation.BookingDto;
import util.Mybatis;

public class CompanyDao {

	//세션(DB처리를 위한 작업단위) 열기
	private SqlSessionFactory factory= Mybatis.getSqlSession();
	
	// 등록
	public int insertCompany(CompanyDto companyDto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		int insertCompany= sqlSession.insert("companyDto", companyDto);
		if(insertCompany>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		sqlSession.close();
		return insertCompany;
	}
	
	
	
	public List<CompanyDto> selectCompany() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		//SQL문 사용
		List<CompanyDto> CompanyList= sqlSession.selectList("selectCompanyAll");
		sqlSession.close();
		return CompanyList;
	}
	
}
