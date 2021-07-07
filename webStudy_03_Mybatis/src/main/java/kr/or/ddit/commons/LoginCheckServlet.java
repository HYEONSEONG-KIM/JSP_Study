package kr.or.ddit.commons;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import kr.or.ddit.member.service.AuthenticateService;
import kr.or.ddit.member.service.AuthenticateServiceImpl;
import kr.or.ddit.vo.MemberVO;

@WebServlet("/login/loginCheck.do")
public class LoginCheckServlet extends HttpServlet {

	private AuthenticateService service = new AuthenticateServiceImpl();
	
	private boolean validate(MemberVO param, Map<String, String> errors) {
		boolean valid = true;
		if(StringUtils.isBlank(param.getMemId())) {
			valid = false;
			errors.put("mem_id","아이디는 필수 입력");
		}
		
		if(StringUtils.isBlank(param.getMemPass())) {
			valid = false;
			errors.put("mem_pass","비밀번호는 필수 입력");
		}/*else {
			Pattern regexPtrn = 
					Pattern.compile("^((?=.*[a-z]+)(?=.*[A-Z]+)(?=.*[0-9]+)(?=.*[!@#\\$%\\^\\&\\*]+).{4,8})$");
			Matcher matcher = regexPtrn.matcher(param.getMem_pass());
			if(!matcher.find()) {
				valid = false;
				errors.put("mem_pass","비밀번호는 영대소문자 숫자 특수문자를 포함한 4~8글자 이내");
			}else {
				System.out.println(matcher.group(1));
			}
		}*/
		return valid;
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//1. 파라미터 확보
		String id = req.getParameter("mem_id");
		String pass = req.getParameter("mem_pass");
		
		//2. 검증
		HttpSession session = req.getSession();
		Map<String, String> errors = new HashMap<String, String>();
		
		MemberVO param = new MemberVO(id,pass);
		
		boolean vaild = validate(param, errors);
		String goPage = null;
		boolean redirect = false;
		
		if(!vaild) {
			// 필수 파라미터 누락 여부 확인(400)
			/*resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;*/
			goPage = "/login/loginForm.jsp";
			
			redirect = true;
			
		}else {
			//3. 인증
			try {
				String encrypted = sha512Encrypting(pass);
				param.setMemPass(encrypted);
				Object resultValue = service.authenticate(param);
				
				if(resultValue instanceof MemberVO) {
					//	1) 성공 : welcome page로 이동
						goPage = "/";
						redirect = true;
						// 로그인 성공하면, 웰컴페이지로 이동해서 성공한 이름을 보여줄것
						session.setAttribute("authMember", resultValue);
				}else {
					//	2) 실패 : login form page로 이동
					goPage = "/login/loginForm.jsp";
					redirect = true;
					session.setAttribute("message", "비밀번호 오류");
					session.setAttribute("failId", id);
					
				}
			}catch(UserNotFoundExcpetion e) {
				goPage = "/login/loginForm.jsp";
				redirect = true;
				session.setAttribute("message", e.getMessage());
			}
			
		
		}
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		}else {
			RequestDispatcher rd = req.getRequestDispatcher(goPage);
			rd.forward(req, resp);
		}
	
		
	}
	
	private String sha512Encrypting(String plain) {
		
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] input = plain.getBytes();
			byte[] encrypted = md.digest(input);
			
			String encoded = Base64.getEncoder().encodeToString(encrypted);
			
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}

	
}











