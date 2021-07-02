package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.db.ConnectionFactory;
import kr.or.ddit.vo.MemberVO;

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

	@Override
	public int insertMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<MemberVO> selectMemberList() {

		StringBuffer sql = new StringBuffer();
		
		sql.append("SELECT             ");
		sql.append("    mem_id,        ");
		sql.append("    mem_pass,      ");
		sql.append("    mem_name,      ");
		sql.append("    mem_regno1,    ");
		sql.append("    mem_regno2,    ");
		sql.append("    mem_bir,       ");
		sql.append("    mem_zip,       ");
		sql.append("    mem_add1,      ");
		sql.append("    mem_add2,      ");
		sql.append("    mem_hometel,   ");
		sql.append("    mem_comtel,    ");
		sql.append("    mem_hp,        ");
		sql.append("    mem_mail,      ");
		sql.append("    mem_job,       ");
		sql.append("    mem_like,      ");
		sql.append("    mem_memorial,  ");
		sql.append("    mem_memorialday,");
		sql.append("    mem_mileage,   ");
		sql.append("    mem_delete     ");
		sql.append(" FROM               ");
		sql.append("    member        ");
		
		try(
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			ResultSet rs = pstmt.executeQuery();
			
			List<MemberVO> memberList = new ArrayList<MemberVO>();
			while(rs.next()) {
				MemberVO member = MemberVO.builder()
						.mem_id(rs.getString("mem_id"))
						.mem_pass(rs.getString("mem_pass"))
						.mem_name(rs.getString("mem_name"))
						.mem_regno1(rs.getString("mem_regno1"))
						.mem_regno2(rs.getString("mem_regno2"))
						.mem_bir(rs.getString("mem_bir"))
						.mem_zip(rs.getString("mem_zip"))
						.mem_add1(rs.getString("mem_add1"))
						.mem_add2(rs.getString("mem_add2"))
						.mem_hometel(rs.getString("mem_hometel"))
						.mem_comtel(rs.getString("mem_comtel"))
						.mem_hp(rs.getString("mem_hp"))
						.mem_mail(rs.getString("mem_mail"))
						.mem_job(rs.getString("mem_job"))
						.mem_like(rs.getString("mem_like"))
						.mem_memorial(rs.getString("mem_memorial"))
						.mem_memorialday(rs.getString("mem_memorialday"))
						.mem_mileage(rs.getInt ("mem_mileage"))
						.mem_delete(rs.getString("mem_delete"))
						.build();
				memberList.add(member);
				
			}
			
			
		return memberList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public MemberVO selectMemberDatail(String mem_id) {
		
		// vo 객체 생성
		
		
		// sql 작성
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT             ");
		sql.append("    mem_id,        ");
		sql.append("    mem_pass,      ");
		sql.append("    mem_name,      ");
		sql.append("    mem_regno1,    ");
		sql.append("    mem_regno2,    ");
		sql.append("    mem_bir,       ");
		sql.append("    mem_zip,       ");
		sql.append("    mem_add1,      ");
		sql.append("    mem_add2,      ");
		sql.append("    mem_hometel,   ");
		sql.append("    mem_comtel,    ");
		sql.append("    mem_hp,        ");
		sql.append("    mem_mail,      ");
		sql.append("    mem_job,       ");
		sql.append("    mem_like,      ");
		sql.append("    mem_memorial,  ");
		sql.append("    mem_memorialday,");
		sql.append("    mem_mileage,   ");
		sql.append("    mem_delete     ");
		sql.append(" FROM               ");
		sql.append("    member        ");
		sql.append("    where mem_id = ?");
		try(
			// connectin 연결
			Connection conn = ConnectionFactory.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		){
			pstmt.setString(1, mem_id);
			
			ResultSet rs = pstmt.executeQuery();
			MemberVO member = null;
			if(rs.next()) {
				
				member = MemberVO.builder()
						.mem_id(rs.getString("mem_id"))
						.mem_pass(rs.getString("mem_pass"))
						.mem_name(rs.getString("mem_name"))
						.mem_regno1(rs.getString("mem_regno1"))
						.mem_regno2(rs.getString("mem_regno2"))
						.mem_bir(rs.getString("mem_bir"))
						.mem_zip(rs.getString("mem_zip"))
						.mem_add1(rs.getString("mem_add1"))
						.mem_add2(rs.getString("mem_add2"))
						.mem_hometel(rs.getString("mem_hometel"))
						.mem_comtel(rs.getString("mem_comtel"))
						.mem_hp(rs.getString("mem_hp"))
						.mem_mail(rs.getString("mem_mail"))
						.mem_job(rs.getString("mem_job"))
						.mem_like(rs.getString("mem_like"))
						.mem_memorial(rs.getString("mem_memorial"))
						.mem_memorialday(rs.getString("mem_memorialday"))
						.mem_mileage(rs.getInt ("mem_mileage"))
						.mem_delete(rs.getString("mem_delete"))
						.build();
				
			}
			return member;
			
		}catch (SQLException e) {
			// myBatis의 예외 처리를 구현해보기 위해...MyBatisExcaption의 최상위에 Runtime
			throw new RuntimeException(e);
		}
		
	}

	@Override
	public int updateMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String mem_id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
