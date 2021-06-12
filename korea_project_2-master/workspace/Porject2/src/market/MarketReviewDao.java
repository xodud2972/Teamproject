package market;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import util.Mybatis;


public class MarketReviewDao {
	private SqlSessionFactory factory= Mybatis.getSqlSession();
	
	// 목록 출력
    public List<MarketReviewDto> selectReviewList() throws Exception {
    	SqlSession sqlSession= factory.openSession();
        List<MarketReviewDto> selectReviewList = sqlSession.selectList("selectReviewList");
        sqlSession.close();
        return selectReviewList;
    }
	
	// 상세보기
	public List<MarketReviewDto> selectReviewOne() throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		
		List<MarketReviewDto> selectReviewOne= sqlSession.selectList("selectReviewOne");
		sqlSession.close();
		return selectReviewOne;
	}
	
	// 삭제
	public int deleteReview(MarketReviewDto reviewDto) throws Exception{
		
		SqlSession sqlSession= factory.openSession();
		int deleteReview= sqlSession.delete("deleteReview", reviewDto);
		if(deleteReview>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return deleteReview;
	}

	// 검색
	public List<MarketReviewDto> selectReviewSearch(MarketReviewDto reviewDto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		List<MarketReviewDto> selectReviewSearch= sqlSession.selectList("selectReviewSearch",reviewDto);
		sqlSession.close();
		return selectReviewSearch;
	}
	
	// 글 숨기기
	public int updateDisplayState(MarketReviewDto reviewDto) throws Exception{
		SqlSession sqlSession= factory.openSession();
		int updateDisplayState= sqlSession.insert("updateDisplayState", reviewDto);
		if(updateDisplayState>0) {
			sqlSession.commit();
		}else {
			sqlSession.rollback();
		}
		return updateDisplayState;
	}

}
