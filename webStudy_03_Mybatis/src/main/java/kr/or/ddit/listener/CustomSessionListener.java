package kr.or.ddit.listener;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.vo.MemberVO;

/**
 * Application Lifecycle Listener implementation class CustomSessionListener
 *
 */
@WebListener
public class CustomSessionListener implements HttpSessionListener, HttpSessionAttributeListener{
	
	
	private static final Logger logger = LoggerFactory.getLogger(ContextLoaderListener.class);
	
	
	
    public void sessionCreated(HttpSessionEvent se)  { 
    	ServletContext application = se.getSession().getServletContext();
    	
    	int userCount = (Integer) application.getAttribute("userCount");
    	userCount++;
    	application.setAttribute("userCount", new Integer(userCount));
    	
    	
    	int currentUserCount = (Integer) application.getAttribute("currentUserCount");
    	currentUserCount ++;
    	application.setAttribute("currentUserCount", new Integer(currentUserCount));
    }


    public void sessionDestroyed(HttpSessionEvent se)  { 
    	ServletContext application = se.getSession().getServletContext();
    	int currentUserCount = (Integer)application.getAttribute("currentUserCount");
    	if(currentUserCount >0) {
    		currentUserCount --;
    		application.setAttribute("currentUserCount", currentUserCount);
    	}
    	
    }


	@Override
	public void attributeAdded(HttpSessionBindingEvent event) {
		
	/*	ServletContext application = event.getSession().getServletContext();
		Map<String, MemberVO> userList = (Map<String, MemberVO>) application.getAttribute("currentUserList");
		
		String name = event.getName();
		if(!name.equals("authMember")) return;
		
		MemberVO authMember = (MemberVO) event.getValue();
		userList.put(authMember.getMemId(), authMember);*/
		
	}


	@Override
	public void attributeRemoved(HttpSessionBindingEvent event) {
		ServletContext application = event.getSession().getServletContext();
		Map<String, MemberVO> userList = (Map<String, MemberVO>) application.getAttribute("currentUserList");
		
		String name = event.getName();
		if(!name.equals("authMember")) return;
		
		MemberVO authMember = (MemberVO) event.getValue();
		userList.remove(authMember.getMemId(), authMember);
	}


	@Override
	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}


	
}
