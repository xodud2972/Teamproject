package customer;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import util.Mybatis;

import java.util.List;

public class CustomerDao {
    private SqlSessionFactory factory = Mybatis.getSqlSession();


    public List<CustomerDto> selectAll() throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<CustomerDto> list = sqlSession.selectList("selectAllCustomerservice");
        sqlSession.close();
        return list;
    }
    public List<CustomerAnsDto> searchAnsDetail(CustomerAnsDto dto) throws Exception {
        SqlSession sqlSession = factory.openSession();
        List<CustomerAnsDto> list = sqlSession.selectList("searchAnsDetail",dto);
        sqlSession.close();
        return list;
    }

    public int update(CustomerDto dto) throws Exception{
        SqlSession sqlSession = factory.openSession();
        int result = -1;
        result = sqlSession.update("updateUser", dto);
        sqlSession.close();
        return result;
    }
    
	public List<CustomerDto> searchCustomer(CustomerDto Customerdto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		List<CustomerDto> searchCustomer= sqlSession.selectList("searchCustomer",Customerdto);
		sqlSession.close();
		return searchCustomer;
	}


}
