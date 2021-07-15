package kr.or.ddit.member.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ThreadUtils;

import kr.or.ddit.commons.UserNotFoundExcpetion;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.listener.ContextLoaderListener;
import kr.or.ddit.member.dao.MemberDAO;
import kr.or.ddit.member.dao.MemberDAOImpl;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.utils.EncryptUtils;
import kr.or.ddit.vo.MemberVO;
import kr.or.ddit.vo.ZipVO;
import kr.or.ddit.vo.pagingVO;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;
import net.coobird.thumbnailator.builders.BufferedImageBuilder;

public class MemberServiceImpl implements MemberService {

	MemberDAO memberDao = MemberDAOImpl.getInstacne();
	private AuthenticateService authService = new AuthenticateServiceImpl();
	
	// singleton
	private MemberServiceImpl() {}
	private static MemberServiceImpl self;
	public static MemberServiceImpl getInstance() {
		if(self == null) {
			self = new MemberServiceImpl();
		}
		return self;
	}
	
	private void proccessProdImage(MemberVO member) {
		
		MultipartFile memImage = member.getMemImage();
		
		if(memImage == null || memImage.isEmpty()) return;
		
		try/*(
			//InputStream is = memImage.getInputStream();
		)*/{
			File saveFolder = ContextLoaderListener.memberImages;
			String saveName = member.getMemPath();
			File saveFile = new File(saveFolder, saveName);
			
			//FileUtils.copyInputStreamToFile(is, saveFile);
			memImage.transferTo(saveFile);
			
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	

	
	
	
	
	@Override
	public ServiceResult creatMember(MemberVO member) {
		
		MemberVO checkMember = memberDao.selectMemberDatail(member.getMemId());
		
		if(checkMember != null) {
			return ServiceResult.PKDUPLICATED;
		}
		String plain = member.getMemPass();
		String encoded = EncryptUtils.encryptSha512Base64(plain);
		member.setMemPass(encoded);
		
		int result = memberDao.insertMember(member);
		
		proccessProdImage(member);
		
		if(result > 0) {
			return ServiceResult.OK;
		}else {
			return ServiceResult.FAIL;
		}
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
		
		Object authResult = authService.authenticate(member);
		ServiceResult result = null;
		
		if(authResult instanceof MemberVO) {
			int rowcnt = memberDao.updateMember(member);
			if(rowcnt > 0) {
				result = ServiceResult.OK;
			}else {
				result = ServiceResult.FAIL;
			}
		}else {
			result = (ServiceResult) authResult;
		}
		return result;
	}

	@Override
	public ServiceResult removeMember(MemberVO member) {
		
		Object authResult = authService.authenticate(member);
		
		ServiceResult result = null;
		
		if(authResult instanceof MemberVO) {
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

	@Override
	public List<ZipVO> retrieveZipList() {
		return memberDao.selectZip();
	}



}
