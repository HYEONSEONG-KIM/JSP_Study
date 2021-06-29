package kr.or.ddit.prop.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.DataBasePropertyVO;

public class DataBasePropertyDaoImpl implements DataBasePropertyDAO {

	@Override
	public List<DataBasePropertyVO> selectDataBasePropertyList() {
		try(
				Connection conn = ConnectionFactory.getConnection();
		// 		out.print(conn);
		// 	4. 쿼리 객체 생성
				Statement stmt =  conn.createStatement();
			){
				StringBuffer sql = new StringBuffer();
				sql.append("SELECT PROPERTY_NAME, PROPERTY_VALUE, DESCRIPTION FROM DATABASE_PROPERTIES");
		// 	5. 쿼리 실행
				ResultSet rs = stmt.executeQuery(sql.toString());
				
				
				List<DataBasePropertyVO> propList = new ArrayList<>();
				
				while(rs.next()){
					DataBasePropertyVO vo = new DataBasePropertyVO();
					propList.add(vo);
					
					vo.setProperty_name(rs.getString("PROPERTY_NAME"));
					vo.setProperty_value(rs.getString("PROPERTY_VALUE"));
					vo.setDescription(rs.getString("DESCRIPTION"));
				}
				return propList;
			}catch(SQLException e) {
				throw new RuntimeException(e);
			}
	}

}
