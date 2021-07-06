package kr.or.ddit.prop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDaoImpl implements DataBasePropertyDAO {

	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList(DataBasePropertyVO param) {
		try(
				SqlSession sqlSession = sqlSessionFactory.openSession();
				){
			DataBasePropertyDAO mapper = sqlSession.getMapper(DataBasePropertyDAO.class);
			return mapper.selectDataBasePropertyList(param);
		}
	}

}
