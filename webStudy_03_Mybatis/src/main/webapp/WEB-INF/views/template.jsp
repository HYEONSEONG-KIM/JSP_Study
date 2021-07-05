<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Dashboard Template · Bootstrap v4.6</title>


	<jsp:include page="/includee/preScript.jsp"></jsp:include>

	<meta name="theme-color" content="#563d7c">


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
  </head>
  <body>
    
	<header>
		<jsp:include page="/includee/headerMenu.jsp"></jsp:include>
	</header>

<div class="container-fluid">
  <div class="row">
   <jsp:include page="/includee/leftMenu.jsp"></jsp:include>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
      	<%
			String contentsPage = (String)request.getAttribute("contentsPage");
			pageContext.include(contentsPage);
		%>
    </main>
  </div>
</div>

	<jsp:include page="/includee/footer.jsp"></jsp:include>
  </body>
</html>
    