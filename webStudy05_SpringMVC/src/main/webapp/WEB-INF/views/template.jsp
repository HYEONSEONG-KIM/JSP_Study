<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.80.0">
    <title>Dashboard Template · Bootstrap v4.6</title>


	<tiles:insertAttribute name="preScript"/>

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
    
    <c:if test="${not empty message }">
	<script type="text/javascript">
		alert("${message }");
	</script>
</c:if>
    
  </head>
  <body>
    
    
	<header>
		<tiles:insertAttribute name = "headerMenu"></tiles:insertAttribute>
	</header>

<div class="container-fluid">
  <div class="row">
   	<tiles:insertAttribute name = "leftMenu"></tiles:insertAttribute>

    <main role="main" class="col-md-9 ml-sm-auto col-lg-10 px-md-4">
		<tiles:insertAttribute name = "content"></tiles:insertAttribute>
    </main>
  </div>
</div>

	<tiles:insertAttribute name = "footer"></tiles:insertAttribute>
  </body>
</html>
    