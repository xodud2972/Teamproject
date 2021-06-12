package bike;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.Mybatis;

import java.util.List;

public class BikeDao {
    private SqlSessionFactory factory = Mybatis.getSqlSession();


    public List<BikeDto> selectAll() throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<BikeDto> list = sqlSession.selectList("selectAllBikedetail");
        sqlSession.close();
        return list;
    }

    public int update(BikeDto dto) throws Exception{
        SqlSession sqlSession = factory.openSession();
        int result = -1;
        result = sqlSession.update("updateBikedetail", dto);
        sqlSession.close();
        return result;
    }
    public List<BikeDto> search(BikeDto dto) throws Exception{
        SqlSession sqlSession = factory.openSession();

        List<BikeDto> bikeDtoList=sqlSession.selectList("searchBikedetail", dto);
        sqlSession.close();
        return bikeDtoList;
    }

}
