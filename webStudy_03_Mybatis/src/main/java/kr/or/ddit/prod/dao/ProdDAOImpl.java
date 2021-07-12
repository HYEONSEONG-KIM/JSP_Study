package kr.or.ddit.prod.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

public class ProdDAOImpl implements ProdDAO {
	
	/*private ProdDAOImpl() {};
	private static ProdDAOImpl self;
	public static ProdDAOImpl getInstance() {
		if(self == null) {
			self = new ProdDAOImpl();
		}
		return self;
	}*/
	
	
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
	
	@Override
	public int insertProd(ProdVO prod) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			int result = mapper.insertProd(prod);
			sqlSession.commit();
			return result;
		}
	}
	
	@Override
	public int selectTotalRecord(pagingVO<ProdVO> paging) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
			return mapper.selectTotalRecord(paging);
		}
		
	}
	@Override
	public List<ProdVO> selectProdList(pagingVO<ProdVO> paging) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
				return mapper.selectProdList(paging);
			}
	}
	
	@Override
	public int updateProd(ProdVO prod) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
			){
				ProdDAO mapper = sqlSession.getMapper(ProdDAO.class);
				int result = mapper.updateProd(prod);
				sqlSession.commit();
				return result;
			}
	}

}
