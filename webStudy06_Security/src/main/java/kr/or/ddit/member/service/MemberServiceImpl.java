package kr.or.ddit.member.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.pagingVO;

@Service
public class MemberServiceImpl implements MemberService {

	
	@Inject
	private MemberDAO memberDAO;
	
	@Resource(name = "authManager")
	private AuthenticationManager authManager;
	
	// 암호화
	@Inject
	private PasswordEncoder passwordEncoder;
	
	@Value("#{appInfo.memberImageUrl}")
	private String memImageUrl; 
	
	@Inject
	private WebApplicationContext container;
	
	private File saveFolder;
	
	@PostConstruct
	public void init() {
		saveFolder = new File(container.getServletContext().getRealPath(memImageUrl));
		if(!saveFolder.exists()) saveFolder.mkdirs();
	}
	
	private void processMemImage(MemberVO member) {
		MultipartFile memImage = member.getMemImage();
		if(memImage==null) return;
		// 트랜잭션 관리 여부 확인용 코드
//		if(1==1) throw new RuntimeException("강제 발생 예외");
		
		try(
			InputStream is = memImage.getInputStream();	
		){
			String savename = UUID.randomUUID().toString();
			File saveFile = new File(saveFolder, savename);
			FileUtils.copyInputStreamToFile(is, saveFile);
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	@Transactional
	@Override
	public ServiceResult createMember(MemberVO member) {
		ServiceResult result = null;
		if(memberDAO.selectMemberDetail(member.getMemId())==null) {
			String plain = member.getMemPass();
			String encoded = passwordEncoder.encode(plain);
			member.setMemPass(encoded);
			int rowcnt = memberDAO.insertMember(member);
			processMemImage(member);
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}// rowcnt>0 end
		}else {
			result = ServiceResult.PKDUPLICATED;
		}
		return result;
	}
	
	@Override
	public int retrieveMemberCount(pagingVO pagingVO) {
		return memberDAO.selectTotalRecord(pagingVO);
	}

	@Override
	public List<MemberVO> retrieveMemberList(pagingVO pagingVO) {
		return memberDAO.selectMemberList(pagingVO);
	}

	@Override
	public MemberVO retrieveMember(String mem_id) throws UserNotFoundExcpetion{
		MemberVO member = memberDAO.selectMemberDetail(mem_id);
		if(member==null)
			throw new UserNotFoundExcpetion(String.format("%s 회원 없음.", mem_id));
		return member;
	}

	@Transactional
	@Override
	
	public ServiceResult modifyMember(MemberVO member) {
		
		ServiceResult result = null;
		try {
		// 인증을 위한 코드, 인증 실패시 exception발생
		authManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass()));
		
		
			int rowcnt = memberDAO.updateMember(member);
			processMemImage(member);
			if(rowcnt > 0 ) {
				result = ServiceResult.OK;
				// 인증된 이후에 변경된 계정 정보
				Authentication newAuthentication = 
						authManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass()));
					//바꿔치기(?)
					SecurityContextHolder.getContext().setAuthentication(newAuthentication);
			}else {
				result = ServiceResult.FAIL;
			}
		}catch (Exception e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		return result;
	}

	@Transactional
	@Override
	public ServiceResult removeMember(MemberVO member) {
		
		ServiceResult result = null;
		try {
		
		authManager.authenticate(new UsernamePasswordAuthenticationToken(member.getMemId(), member.getMemPass()));
		
		
			int rowcnt = memberDAO.deleteMember(member.getMemId());
			if(rowcnt>0) {
				result = ServiceResult.OK;
			}else {
				result  = ServiceResult.FAIL;
			}
		}catch (Exception e) {
			result = ServiceResult.INVALIDPASSWORD;
		}
		
		return result;
	}

}









