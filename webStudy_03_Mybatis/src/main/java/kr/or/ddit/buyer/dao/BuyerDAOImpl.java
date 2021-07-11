package kr.or.ddit.buyer.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.BuyerVO;
import kr.or.ddit.vo.pagingVO;

public class BuyerDAOImpl implements BuyerDAO {

	private BuyerDAOImpl() {}
	private static BuyerDAOImpl self = null;
	public static BuyerDAOImpl getInstance() {
		if(self == null) {
			self = new BuyerDAOImpl();
		}
		return self;
	}
	
	SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public int buyerTotalRecord(pagingVO<BuyerVO> paging) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.buyerTotalRecord(paging);
		}
	}

	@Override
	public List<BuyerVO> selectBuyerList(pagingVO<BuyerVO> paging) {
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
			return mapper.selectBuyerList(paging);
		}
		
	}

	@Override
	public BuyerVO selectBuyer(String buyerId) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
					){
				BuyerDAO mapper = sqlSession.getMapper(BuyerDAO.class);
				return mapper.selectBuyer(buyerId);
			}
	
	}

	@Override
	public int insertBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBuyer(BuyerVO buyer) {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
