package kr.or.ddit.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

public class FreeBoardDAOImpl implements FreeBoardDAO {

	private FreeBoardDAOImpl() {}
	private static FreeBoardDAOImpl self;
	public static FreeBoardDAOImpl getInstance() {
		if(self == null) {
			self = new FreeBoardDAOImpl();
		}
		return self;
	}
	
	SqlSessionFactory sqlSessionFactory = 
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public int insertBoard(FreeBoardVO board, SqlSession sqlSession) {
		
		return sqlSession.insert("kr.or.ddit.board.dao.FreeBoardDAO.insertBoard", board);
	}

	@Override
	public int selectTotalRecord(pagingVO<FreeBoardVO> paging) {

		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectTotalRecord(paging);
		}
	}

	@Override
	public List<FreeBoardVO> selectBoardList(pagingVO<FreeBoardVO> paging) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoardList(paging);
		}

	}

	@Override
	public FreeBoardVO selectBoard(int boNo) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoard(boNo);
		}
	}

	@Override
	public int updateBoard(FreeBoardVO board, SqlSession sqlSession) {
		return sqlSession.update("kr.or.ddit.board.dao.FreeBoardDAO.updateBoard",board);
	}

	@Override
	public int deleteBoard(int boNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int incrementHit(int boNo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int result = mapper.incrementHit(boNo);
			sqlSession.commit();
			return result;
		}
	}

	@Override
	public int incrementRec(int boNo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int result = mapper.incrementRec(boNo);
			sqlSession.commit();
			return result;
			}
	}

	@Override
	public int incrementRep(int boNo) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int result = mapper.incrementRep(boNo);
			sqlSession.commit();
			return result;
		}
	}

}
