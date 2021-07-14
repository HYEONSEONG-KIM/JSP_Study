package kr.or.ddit.prod.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import kr.or.ddit.commons.exception.DataNotFoundException;
import kr.or.ddit.db.mybatis.CustomSqlSessionFactoryBuilder;
import kr.or.ddit.enumtype.ServiceResult;
import kr.or.ddit.listener.ContextLoaderListener;
import kr.or.ddit.multipart.MultipartFile;
import kr.or.ddit.prod.dao.ProdDAO;
import kr.or.ddit.prod.dao.ProdDAOImpl;
import kr.or.ddit.vo.ProdVO;
import kr.or.ddit.vo.pagingVO;
import net.coobird.thumbnailator.Thumbnails;

public class ProdServiceImpl implements ProdService {

	private ProdDAO prodDAO = new ProdDAOImpl();
	
	
	/**
	 * 상품의 이미지를 저장
	 * @param prod
	 */
	private void proccessProdImage(ProdVO prod) {
		
		
		MultipartFile prodImage = prod.getProdImage();
		if(prodImage == null) return;
		// 트랜잭션 관리 여부 확인용 코드
		/*if(1==1) throw new RuntimeException("강제 발생 예외");*/
		
		try(
			InputStream is = prodImage.getInputStream();
		){
			File saveFolder = ContextLoaderListener.prodImages;
			String savename = prod.getProdImg();
			File saveFile = new File(saveFolder, savename);
			FileUtils.copyInputStreamToFile(is, saveFile);
			
			
		}catch(IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private SqlSessionFactory sqlSessionFactory = CustomSqlSessionFactoryBuilder.getSqlSessionFactory();
	
	// 차후에 spring을 이용하면, AOP 방법론에 따라 Platform transaction Maneger 를 이용하여 해결
	@Override
	public ServiceResult createProd(ProdVO prod) {
		// ---> 트랜잭션 시작, sqlSession open
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			prodDAO.insertProd(prod, sqlSession);
			//2진 데이터 저장
			proccessProdImage(prod);
			
			
			ServiceResult result = null;
			
			if(StringUtils.isBlank(prod.getProdId())){
				result = ServiceResult.FAIL;
			}else {
				result = ServiceResult.OK;
				// ---> 트랙잭션 종료 , commit
				sqlSession.commit();
			}
			return result;
			
		}
		
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

	@Override
	public ServiceResult modifyProd(ProdVO prod) {
		
		prodDAO.selectProd(prod.getProdId());
		
		try(
			SqlSession sqlSession = sqlSessionFactory.openSession();
		){
			// 2진 데이터 저장
			proccessProdImage(prod);
			
			ServiceResult result = null;
		
				int resultCnt = prodDAO.updateProd(prod, sqlSession);
				if(resultCnt > 0) {
					result = ServiceResult.OK;
					sqlSession.commit();
				}else {
					result = ServiceResult.FAIL;
				}
				
			
			
			return result;
		}
	
	}

}
