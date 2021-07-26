package kr.or.ddit.member.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.MemberVO;

@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	@Inject
	private MemberDAO memberDAO;
	
	@Override
	public Object authenticate(MemberVO param) {
		Object resultValue = null;
		System.out.println(memberDAO);
		MemberVO savedMember = memberDAO.selectMemberById(param.getMemId());
		if(savedMember==null) {
			throw new UserNotFoundExcpetion(String.format("%s 회원은 없음.", param.getMemId()));
		}
		String savedPass = savedMember.getMemPass();
		String inputPass = param.getMemPass();
		boolean valid =  EncryptUtils.matches(inputPass, savedPass);
		if(valid) {
			resultValue = savedMember;
		}else{
			resultValue = ServiceResult.INVALIDPASSWORD;
		}
		return resultValue;
	}

}






