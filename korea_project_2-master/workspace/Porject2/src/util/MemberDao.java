package util;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import login.LoginDto;

import java.util.List;

public class MemberDao {
    // MyBatis와 java를 연결해서 SQL을 실행하고 그 결과를 얻는 역할
    private SqlSessionFactory factory = Mybatis.getSqlSession();


    public List<MemberDto> selectMember() throws Exception {
        //세션(DB처리를 위한 작업단위) 열기
        SqlSession sqlSession = factory.openSession();

        //SQL문 사용
        List<MemberDto> list = sqlSession.selectList("selectDataAll");
        sqlSession.close();
        return list;
    }
    public MemberDto searchMember(String user_id) throws Exception {
        //세션(DB처리를 위한 작업단위) 열기
        SqlSession sqlSession = factory.openSession();

        //SQL문 사용
        MemberDto dto = sqlSession.selectOne("selectOne",user_id);
        sqlSession.close();
        return dto;
    }
    
    public LoginDto loginCheck(String id) throws Exception {
    	SqlSession sqlSession = factory.openSession();
    	LoginDto logindto = sqlSession.selectOne("loginCheck",id);
    	sqlSession.close();
    	
    	return logindto;
    }
    
    
}