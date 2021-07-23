package kr.or.ddit.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeBoardVO;
import kr.or.ddit.vo.pagingVO;

@Repository
public class FreeBoardDAOImpl implements FreeBoardDAO {

	@Inject
	//sqlSessionTemplate가 들어옴
	private SqlSession sqlSession;
	
	@Override
	public int insertBoard(FreeBoardVO board) {
		
		return sqlSession.insert("kr.or.ddit.board.dao.FreeBoardDAO.insertBoard", board);
	}

	@Override
	public int selectTotalRecord(pagingVO<FreeBoardVO> paging) {
		FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
		return mapper.selectTotalRecord(paging);
		
	}

	@Override
	public List<FreeBoardVO> selectBoardList(pagingVO<FreeBoardVO> paging) {
	
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoardList(paging);
		

	}

	@Override
	public FreeBoardVO selectBoard(int boNo) {
	
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			return mapper.selectBoard(boNo);
		
	}

	@Override
	public int updateBoard(FreeBoardVO board) {
		return sqlSession.update("kr.or.ddit.board.dao.FreeBoardDAO.updateBoard",board);
	}

	@Override
	public int deleteBoard(int boNo) {
		return sqlSession.delete("kr.or.ddit.board.dao.FreeBoardDAO.deleteBoard", boNo);
	}

	@Override
	public int incrementHit(int boNo) {
		return sqlSession.update("kr.or.ddit.board.dao.FreeBoardDAO.incrementHit",boNo);
	}

	@Override
	public int incrementRec(int boNo) {
		
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int result = mapper.incrementRec(boNo);
			return result;
			
	}

	@Override
	public int incrementRep(int boNo) {
	
			FreeBoardDAO mapper = sqlSession.getMapper(FreeBoardDAO.class);
			int result = mapper.incrementRep(boNo);
			return result;
		
	}

}
