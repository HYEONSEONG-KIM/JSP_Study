package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

public class MemberDAOImpl implements MemberDAO {
	
	@Override
	public MemberVO selectMemberById(String mem_id) {
		String sql = "SELECT MEM_ID, MEM_PASS, MEM_NAME FROM MEMBER WHERE MEM_ID = ?";
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
		) {
			ps.setString(1, mem_id);
			ResultSet rs = ps.executeQuery();
			
			MemberVO vo = null;
			if(rs.next()) {
				vo = new MemberVO();
				vo.setMem_id(rs.getString("MEM_ID"));
				vo.setMem_pass(rs.getString("MEM_PASS"));
				vo.setMem_name(rs.getString("MEM_NAME"));
			}
			return vo;
			
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
