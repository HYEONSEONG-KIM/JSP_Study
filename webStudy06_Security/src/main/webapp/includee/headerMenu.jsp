<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<nav class="navbar navbar-dark sticky-top bg-dark flex-md-nowrap p-0 shadow">
  <a class="navbar-brand col-md-3 col-lg-2 mr-0 px-3" href="#">Company name</a>
  <button class="navbar-toggler position-absolute d-md-none collapsed" type="button" data-toggle="collapse" data-target="#sidebarMenu" aria-controls="sidebarMenu" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
  <ul class="nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">회원관리</a>
    </li>
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">상품관리</a>
    </li>
    
    <li class="nav-item text-nowrap">
      <input type="image" src = "${cPath}/resources/images/KoreanFlag.png"
      	class = "controlBtn" data-gopage="?language=ko"
      />
    </li>
    
    <li class="nav-item text-nowrap">
      <input type="image" src = "${cPath}/resources/images/AmericaFlag.png"
      	class = "controlBtn" data-gopage="?language=en"
      />
    </li>
    
  </ul>
  
  
  
  <ul class="navbar-nav px-3">
    <li class="nav-item text-nowrap">
      <a class="nav-link" href="#">Sign out</a>
    </li>
  </ul>
</nav>