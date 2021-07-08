package kr.or.ddit.member.service;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.MemberVO;

public class AuthenticateServiceImpl implements AuthenticateService {

	private MemberDAO memberDAO = MemberDAOImpl.getInstacne();
	
	@Override
	public Object authenticate(MemberVO param) {
		Object resultValue = null;
		MemberVO savedMember = memberDAO.selectMemberById(param.getMemId());
		if(savedMember == null) {
			throw  new UserNotFoundExcpetion(String.format("%s회원은 없음", param.getMemId()));
		}
		
		String savedPass = savedMember.getMemPass();
		String inputPass = param.getMemPass();
		boolean valid = EncryptUtils.matches(inputPass, savedPass);
		if(valid) {
			resultValue = savedMember;
		}else {
			resultValue = ServiceResult.INVALIDPASSWORD;
		}
		return resultValue;
	}

}
