package kr.or.ddit.member.service;

import java.util.List;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.pagingVO;

public class MemberServiceImpl implements MemberService {

	MemberDAO memberDao = MemberDAOImpl.getInstacne();
	
	// singleton
	private MemberServiceImpl() {}
	private static MemberServiceImpl self;
	public static MemberServiceImpl getInstance() {
		if(self == null) {
			self = new MemberServiceImpl();
		}
		return self;
	}
	
	@Override
	public ServiceResult creatMember(MemberVO member) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int retrieveMemberCount(pagingVO pagingvo) {

		return memberDao.selectTotalRecord(pagingvo);
	}

	@Override
	public List<MemberVO> retrieveMemberList(pagingVO pagingVO) {
		List<MemberVO> memberList = memberDao.selectMemberList(pagingVO);
		
		return memberList;
	}

	@Override
	public MemberVO retrieveMember(String mem_id) {
		// 존재하지 않으면 usernot... 발생
		MemberVO member = memberDao.selectMemberDatail(mem_id);
		
		if(member == null) {
			throw new UserNotFoundExcpetion(String.format("%s 유저를 찾을수 없습니다", mem_id));
		}else {
			return member;
		}
		
	}

	@Override
	public ServiceResult modifyMember(MemberVO member) {
		MemberVO savedMember = retrieveMember(member.getMemId());
		String savedPass = savedMember.getMemPass();
		String inputPass = member.getMemPass();
		ServiceResult result = null;
		
		if(savedPass.equals(inputPass)) {
			int rowcnt = memberDao.updateMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		MemberVO savedMember = retrieveMember(member.getMemId());
		String savedPass = savedMember.getMemPass();
		String inputPass = member.getMemPass();
		ServiceResult result = null;
		
		if(savedPass.equals(inputPass)) {
			int rowcnt =memberDao.deleteMember(member.getMemId());
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
			
		}else {
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}



}