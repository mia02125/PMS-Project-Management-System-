<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%
response.setHeader("cache-control","no-cache");
response.setHeader("expires","0");
response.setHeader("pragma","no-cache");
%>

<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<meta name="description" content="">
	<meta name="author" content="">

	<title>PMS</title>
	
	<!--Chrome, Safari, IE -->
	<link rel="shortcut icon" href="/resources/images/favicon.ico" type="image/x-icon">
	<!-- Firefox, Opera  -->
	<link rel="icon" href="/resources/images/favicon.ico" type="image/x-icon">

	<link rel="stylesheet" type="text/css" href="/resources/vendor/bootstrap/css/bootstrap.min.css" />
	<link rel="stylesheet" type="text/css" href="/resources/css/setup.css" />
  	<link rel="stylesheet" type="text/css" href="/resources/css/details.css" />
  	<link rel="stylesheet" type="text/css" href="/resources/vendor/jquery/jquery-ui.min.css" />
  	<link rel="stylesheet" type="text/css" href="/resources/font/all.min.css" />
  	<link rel="stylesheet" type="text/css" href="/resources/css/custom.css" />
  	<link rel="stylesheet" type="text/css" href="/resources/css/custom_cjm.css" />
  	<link rel="stylesheet" type="text/css" href="/resources/css/btn_search.css" />

	<script type="text/javascript" src="/resources/vendor/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="/resources/vendor/jquery/jquery-ui.min.js"></script>
	<script type="text/javascript" src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
  	<script type="text/javascript" src="/resources/js/main.js"></script>

	<script src="/resources/js/comapp.js"></script>

	<style>
	    .cu-tb-td-lv2{background-color:rgb(248, 224, 193);}
	    .cu-tb-td-lv3{background-color:rgb(196, 246, 250);}
	    .cu-tb-td-lv4{background-color:rgb(209, 252, 209);}
 	    .cu-tb-td-lv5{background-color:rgb(208 214 236);}
	    .form-group.required .col-form-label:after {
	      	content:"*";
	      	color:red;
	    }
	  #loading { 
			width: 100%; 
			height: 100%; 
			top: 0px; 
			left: 0px; 
			position: fixed; 
			display: block; 
			opacity: 0.7; 
			background-color: #fff; 
			z-index: 99999; 
			text-align: center; 
		} 
		#loading_image { 
			position: absolute; 
			top: 50%; 
			left: 50%; 
			z-index: 99997; 
		}
	</style>

</head>

<body id="page-top">
	<div id="loading"><img id="loading_image" src="/resources/images/loading.gif" alt="loading"></div>
	<div class="wrap">
    <div class="content">
      <div class="left-warp">
        <input type="checkbox" id="menu_state">
        <label for="menu_state" class="left-button">
          <span class="icons l-button-arrow"></span>
        </label>
        <nav class="left-nav">
          <label for="menu_state">
            <div class="logo-bg">
              <h1 class="clearfix">
                <a href="/main/search" style="font-size: x-large">PMS<br><span>프로젝트&nbsp인력관리&nbsp시스템</span></a>
              </h1>
            </div>
          </label>
          <ul>
            <li class="nav-li">		  	  	
              <ul class="nav-sub">
                <li><a href="/main/proj"><i class="far fa-calendar-check"></i><span class="on">프로젝트 관리</span></a></li>
                <li><a href="/main/user"><i class="fas fa-user"></i><span class="on">사용자 관리</span></a></li>
              </ul>
            </li>
          </ul>
        </nav>
      </div>
      <div class="content-main">
        <div class="a-top">
          <div class="f-left">
             <h1>프로젝트 인력관리 시스템</h1>
          </div>
          <div class="f-right">
            <img src="/resources/images/people/img-profile-default.png" alt="관리자사진" class="img-thumbnail s40">
            <div class="dropdown">
              <button class="btn dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span value="님"><sec:authorize access="isAuthenticated()"><sec:authentication property="principal.usName"/></sec:authorize></span>
              </button>
              <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
<!--                 <a class="dropdown-item" href="#">개인정보</a> -->
<!--                 <a class="dropdown-item" href="#">설정</a> -->
                <a class="dropdown-item" href="/login/loginOut">로그아웃</a>
              </div>
            </div>
          </div>
        </div>

	       	<!-- Begin Page Content -->
			<tiles:insertAttribute name="body" />
			<!-- /.container-fluid -->

      </div>
    </div>
  </div>
</body>


<!-- Core plugin JavaScript-->
<script src="/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Cookie plugin JavaScript-->
<script src="/resources/vendor/jquery/jquery.cookie.js"></script>

<!-- Custom scripts for all pages-->
<script src="/resources/js/sb-admin-2.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/proj/proj.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/user/user.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/search/autoSearch.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/career/career.js"></script>

<script type="text/javascript">

//---------------------------------------------------------------------------------
// variable
//------------------------
	//페이지이동
	function mvPage(category , target) {
		if(category == 'inputedProj') {
			var dataList = {
					projCode : $(target).parent().attr('id')
				}
		} else if(category == 'proj') {
			var dataList = {
				projCode : $(target).parent().find('input').val()
			}
		} else if(category == 'user') {
			var dataList = {
				userCode : $(target).parent().find('input').val()
			}
		}
		$(document).on('click', target,function() {
			if(category == 'proj' || category == 'inputedProj') {
				$.ajax({
					url : '/main/proj/detail',
					type : 'GET',
					data : dataList,
					success : function(data) {
						location.href='/main/proj/detail?projCode=' + dataList['projCode'];
					}, 
					error : function(error) {
						console.log(error);
					}  
				})
			} else if(category == 'user') {
				$.ajax({
					url : '/main/user/detail',
					type : 'GET',
					data : dataList,
					success : function(data) {
						location.href='/main/user/detail?userCode=' + dataList['userCode'];
					}, 
					error : function(error) {
						console.log(error);
					}  
				})
			}
		});
	}
//---------------------------------------------------------------------------------
// function
//-------------------------

function fnFindClickMenu(){

	var url = location.href;
	if(!url) return;

	$('.nav-sub a').each(function(){
		var curl = $(this).attr('href');
		var checkCurl = url.split(curl)[1] || "";
		if (url.indexOf(curl) != -1) {
			// checkCurl : 조건 없앰 
			$(this).closest('li').addClass('active');
		}
	});

}



//---------------------------------------------------------------------------------
// ready
//-------------------------
	
	// 로딩 (리팩토링 필요 )
		$(document).ready(function() {
			$('#loading').hide(); 
		})
		.ajaxStart(function() {
			$('#loading').show();
		})
		.ajaxStop(function() {
			$('#loading').hide();
		})
		.submit(function() {
			$('#loading').show();
		});
//---------------------------------------------------------------------------------

</script>

</html>




