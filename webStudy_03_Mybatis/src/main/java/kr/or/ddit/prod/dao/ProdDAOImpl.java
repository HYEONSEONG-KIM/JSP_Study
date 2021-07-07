package kr.or.ddit.prod.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;

public class ProdDAOImpl implements ProdDAO {
	
	private ProdDAOImpl() {};
	private static ProdDAOImpl self;
	public static ProdDAOImpl getInstance() {
		if(self == null) {
			self = new ProdDAOImpl();
		}
		return self;
	}
	
	
	private SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	@Override
	public ProdVO selectProd(String prodId) {

		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectProd(prodId);
		}
	}

}
