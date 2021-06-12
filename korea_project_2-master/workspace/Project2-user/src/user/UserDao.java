package user;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.Mybatis;

import java.util.List;

public class UserDao {
    private SqlSessionFactory factory = Mybatis.getSqlSession();


    public List<UserDto> selectAll() throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<UserDto> list = sqlSession.selectList("selectAllUser");
        sqlSession.close();
        return list;
    }

    public int update(UserDto dto) throws Exception{
        SqlSession sqlSession = factory.openSession();
        int result = -1;
        result = sqlSession.update("updateUser", dto);
        sqlSession.close();
        return result;
    }
    public List<UserDto> search(UserDto dto) throws Exception{
        SqlSession sqlSession = factory.openSession();

        List<UserDto> bikeDtoList=sqlSession.selectList("searchUser", dto);
        sqlSession.close();
        return bikeDtoList;
    }

}
