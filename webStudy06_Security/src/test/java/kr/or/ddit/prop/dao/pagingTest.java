package kr.or.ddit.prop.dao;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

public class pagingTest {

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		// DB에 몇개 레코드 갖고있는지
		int TotalRecored = 101;
		// 한화면에 몇개 출력할 건지
		int totalSize = 10;
		// 몇개의 페이지를 출력할건지
		int blockSize = 5; 
		
		int currentPage = 4;
		
		int totalPage = TotalRecored % totalSize == 0? TotalRecored/totalSize : TotalRecored/totalSize + 1;
		System.out.println("totalPage : " + totalPage);
		int startRow = (totalSize * currentPage) - totalSize + 1;
		int endRow = startRow + blockSize - 1;
		System.out.println("startRow : " + startRow);
		System.out.println("endRow : " + endRow);
		
		int startPage = blockSize * (currentPage - 1) / blockSize + 1;
		int endPage = startPage + blockSize - 1;
		System.out.println("startPage :" + startPage);
		System.out.println("endPage : " + endPage);
		
		int screenSize = 10;
		
	
	}

}
