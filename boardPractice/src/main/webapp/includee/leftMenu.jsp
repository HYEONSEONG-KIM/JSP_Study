<%-- <%@page import="kr.or.ddit.vo.MenuVO"%>
<%@page import="java.util.List"%>
<%@page import="kr.or.ddit.vo.ServiceInfoVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


 <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">
        <%
			ServiceInfoVO infoVO = (ServiceInfoVO) application.getAttribute("serviceInfo");
			List<MenuVO> menuList = infoVO.getMenuList();
			for( MenuVO menu : menuList){
				String href = menu.getHref();
				if(href == null || href.isEmpty()){
					href = request.getContextPath() + "?service=" + menu.getCode();
				}else{
					href = request.getContextPath() + href;
				}

%>

          <li class="nav-item">
            <a class="nav-link active" href="<%=href%>">
              <%=menu.getText() %>
            </a>
          </li>
<% 
			}
%>
         
          
        </ul>
      </div>
    </nav> --%>