package login;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;

public class JoinDao {
	 private SqlSessionFactory factory = Mybatis.getSqlSession();

	    
    public int insertJoin(JoinDto joinDto) throws Exception {
    	SqlSession sqlSession = factory.openSession();
    	//삽입, 삭제, 갱신은 return 값 int
    	int insertJoin = sqlSession.insert("JoinDao",joinDto);
    	//	반환값					insert문 	xmlID     Dto명
    	if(insertJoin>0) {
    		sqlSession.commit();
    	}else {
    		sqlSession.rollback();
    	}
    	sqlSession.close();
    	return insertJoin;
    }
}
