<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

    <script type="text/javascript" src="/resources/vendor/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="/resources/vendor/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script type="text/javascript" src="/resources/js/main.js"></script>


    <script src="/resources/js/rsa/jsbn.js"></script>
    <script src="/resources/js/rsa/prng4.js"></script>
    <script src="/resources/js/rsa/rng.js"></script>
    <script src="/resources/js/rsa/rsa.js"></script>

    <script src="/resources/js/comapp.js"></script>

</head>

<body class="bg-gradient-primary">

<div class="login-wrap flex-center">

    <div class="login-box">
        <div class="login-image">
        </div>
        <div class="login-right-wrap">
            <ul>
                <li><span class="small-text-01 mb10">Welcome to</span></li>
                <li>
                    <h1 class="h1-text-01 mb30"><strong style="font-size: 50px">PMS</strong><span class="small-text-01">???????????? ???????????? ?????????</span></h1>
                    
                </li>
                <li><input type="text" class="form-control box-type-01 w100p" placeholder="??????" id="inp_id" value="1234"/></li>
                <li><input type="password" class="form-control box-type-01 w100p" placeholder="????????????"  id="inp_pass" value="1234"/></li>
                <li>
                    <button type="button" class="btn btn-type-01 mt20" id="btn_login">Login</button>
                </li>
                <li><span class="small-text-02">????????? ????????????? ??????????????? ???????????????.</span></li>
            </ul>
            <div>
          <span class="small-text-02">
            ????????? ??????
            <a class="login-link" href="tel:1-555-531-3255!8335033#!#!9582#">T.0000-0000</a>
            <a class="login-link" href="mailto:sales@com4in.com">sales@com4in.com</a>
          </span>
            </div>
        </div>
    </div>
</div>

<form id="fmLogin" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    <input type="hidden" name="userId" value="${userId}">
    <input type="hidden" name="userPass" value="${userPass}">
</form>

<script type="text/javascript">

    /**
     * ????????? ????????? ????????? ??? ?????? ????????????
     * @param target
     * @param msg
     */
    function fnLoginFail(target, msg) {
        if(target == "userId"){
            $('#inp_id').focus().select();
        }else if(target == "userPass"){
            $('#inp_pass').focus().select();
        }
        alert(msg);
    }

    function fnLogin() {

        if($("#inp_id").val() == '') {
            alert("???????????? ???????????? ????????????.");
            $('#inp_id').focus().select();
            return;
        }
        if($("#inp_pass").val() == '') {
            alert("??????????????? ???????????? ????????????.");
            $('#inp_pass').focus().select();
            return;
        }

        var id 		= $("#inp_id");
        var pass 	= $("#inp_pass");

        $('#fmLogin').find('[name=userId]').val(id.val());
        $('#fmLogin').find('[name=userPass]').val(pass.val());

        id.val("");
        pass.val("");

        comapp.submit({
            target		: '#fmLogin',
            url			: '/login/login',
            method		: 'post'
        });
    }

    function fnEventListener() {

        $('#inp_id').keydown(function(e){
            if(e.keyCode==13){
                $('#inp_pass').focus();
            }
        });

        $('#inp_pass').keydown(function(e){
            if(e.keyCode==13){
                fnLogin();
            }
        });
    }

    //---------------------------------------------------------------------------------
    // ready
    //-------------------------
    $(document).ready(function() {
        if('${not empty ERROR_MSG}' == 'true'){
            fnLoginFail('${ERROR_TARGET}', '${ERROR_MSG}');
        }else {
            $('#inp_id').focus().select();
        }

        fnEventListener();

        $('#btn_login').click(function(){
            fnLogin();
        });
    });
    //---------------------------------------------------------------------------------

</script>

</body>

</html>




