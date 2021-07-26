package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.inject.Inject;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;

@Service
public class ProdServiceImpl implements ProdService {

	@Inject
	private ProdDAO prodDAO;
	
	private ServletContext application;
	@Value("#{appInfo.attatchPath}")
	File saveFolder;
	
	/**
	 * 상품의 이미지를 저장
	 * @param prod
	 */
	private void proccessProdImage(ProdVO prod) {
		
		MultipartFile prodImage = prod.getProdImage();
		
		// 트랜잭션 관리 여부 확인용 코드
		/*if(1==1) throw new RuntimeException("강제 발생 예외");*/
		
		try(
			InputStream is = prodImage.getInputStream();
		){
			String savename = prod.getProdImg();
			File saveFile = new File(saveFolder, savename);
			FileUtils.copyInputStreamToFile(is, saveFile);
			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// 차후에 spring을 이용하면, AOP 방법론에 따라 Platform transaction Maneger 를 이용하여 해결
	@Override
	@Transactional
	public ServiceResult createProd(ProdVO prod) {
		// ---> 트랜잭션 시작, sqlSession open
		
			prodDAO.insertProd(prod);
			//2진 데이터 저장
			proccessProdImage(prod);
			
			
			ServiceResult result = null;
			
			if(StringUtils.isBlank(prod.getProdId())){
				result = ServiceResult.FAIL;
			}else {
				result = ServiceResult.OK;
				// ---> 트랙잭션 종료 , commit
			}
			return result;
			
		
	}

	@Override
	public void retrieveProdList(pagingVO<ProdVO> pagingVO) {
		
		int totalRecord = prodDAO.selectTotalRecord(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		
		pagingVO.setDataList(prodDAO.selectProdList(pagingVO));
		
	}

	@Override
	public ProdVO retrieveProd(String prodId) {
		
		ProdVO prod = prodDAO.selectProd(prodId);
		
		if(prod == null) {
			throw new DataNotFoundException();
		}else {
			
			return prod;
		}
		
	}

	@Transactional
	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		
		prodDAO.selectProd(prod.getProdId());
		
		
			// 2진 데이터 저장
			proccessProdImage(prod);
			
			ServiceResult result = null;
		
				int resultCnt = prodDAO.updateProd(prod);
				if(resultCnt > 0) {
					result = ServiceResult.OK;
				}else {
					result = ServiceResult.FAIL;
				}
				
			
			
			return result;
		}
	
	

}
