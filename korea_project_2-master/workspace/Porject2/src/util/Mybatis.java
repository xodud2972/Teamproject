package util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.Reader;

public class Mybatis {
    //DB의 SQL명령을 실행시킬 때 필요한 메소드를 갖고 있다.
    //Mapper의 내용을 읽을 수 있다.
    public static SqlSessionFactory sqlSession;

    //프로그램이 실행될 때 동작한다.
    //mybatis와 DB가 연결된다.
    //resource에서 DB정보를 가져와 BUILD한다.
    static{
        String resource = "mybatis/mybatis.xml";
        try {
            Reader reader = Resources.getResourceAsReader(resource);
            sqlSession = new SqlSessionFactoryBuilder().build(reader);
            System.out.println(sqlSession);
            reader.close();
        } catch (Exception e) {
            System.out.println("SqlMapConfig 오류 : " + e);
        }
    }

    public static SqlSessionFactory getSqlSession(){
        return sqlSession;
    }
}