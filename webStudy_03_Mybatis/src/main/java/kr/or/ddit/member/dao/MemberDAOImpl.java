package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.pagingVO;

public class MemberDAOImpl implements MemberDAO {
	
	// singleton
	private MemberDAOImpl() {
		super();
	}
	private static MemberDAOImpl self;
	public static MemberDAOImpl getInstacne() {
		if(self == null) {
			self = new MemberDAOImpl();
		}
		return self;
	}
	
	private SqlSessionFactory sqlSessionFactory =
			CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	
	@Override
	public MemberVO selectMemberById(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			// selectOne(해당 메서드의 인터페이스 qualified Name, 파라미터)
			return (MemberVO)sqlSession.selectOne("kr.or.ddit.member.dao.MemberDAO.selectMemberById", mem_id);
		}
	}

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int selectTotalRecord(pagingVO pagingVO) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			return mapper.selectTotalRecord(pagingVO);
		}
	}
	
	
	@Override
	public List<MemberVO> selectMemberList(pagingVO pagingVO) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession(); 
		){
			return sqlSession.selectList("kr.or.ddit.member.dao.MemberDAO.selectMemberList", pagingVO);
		}
		
			
		
	}

	@Override
	public MemberVO selectMemberDatail(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
//			proxy 객체 생성
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			System.out.println(mapper.getClass());
			return mapper.selectMemberDatail(mem_id);
		}
		
	}

	@Override
	public int updateMember(MemberVO member) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt =  mapper.updateMember(member);
			sqlSession.commit();
			return rowcnt;
		}
		
	}

	@Override
	public int deleteMember(String mem_id) {
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			// 프록시 직접 만들어지는게 아닌 대리객체의 의미
			MemberDAO mapper = sqlSession.getMapper(MemberDAO.class);
			int rowcnt = mapper.deleteMember(mem_id);
			sqlSession.commit();
			return rowcnt;
		}
		
	}



}
