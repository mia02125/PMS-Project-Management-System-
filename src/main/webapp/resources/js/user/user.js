
/**
 * 사용자 상세정보 selectBox 데이터값 넣기
 * @type {jQuery|string|undefined}
 */
var det_post = $('#ipt_update_user_post').val();
$('#update_user_post').val(det_post);

var det_user_level = $('#ipt_update_user_level').val();
$('#update_user_level').val(det_user_level);
/**
 * 사용자 신규 등록 
 * @returns
 */
$('#btn_user_insert').on('click', function() {
	var csrfNM = $('input[id=_csrf]').attr('name');
	var csrfVal = $('input[id=_csrf]').val();
	var dataList = {
			userName : $('#input_user_name').val().trim(),
			userPost : $('#input_user_post').val(),
			userLevel : $('#input_user_level').val(),
			userBuseo : $('#input_user_buseo').val().trim(),
			userTel : $('#input_user_tel').val().trim(),
			userEmail : $('#input_user_email').val().trim(),
			_csrf : csrfVal
	}
	if(dataList['userName'] == '' || dataList['userName'] == null) {
		alert('이름을 입력하세요.');
		$('#input_user_name').val('');
		$('#input_user_name').focus();
		return false;
	} else if(dataList['userName'] != '' && dataList['userName'] != null) {
		if(dataList['userName'].length > 20) {
			alert('이름은 20자 이하로 제한되어 있습니다.');
			$('#input_user_name').focus();
			return false;
		} else {
			var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
    		if(special_pattern.test(dataList['userName'])) {
    			alert('이름은 특수문자는 사용할 수 없습니다.');
    			$('#input_user_name').focus();
    			return false;
    		}
		}
	}
	if(dataList['userPost'] == '' || dataList['userPost'] == null) {
		alert('직위을 선택하세요.');
		return false;
	}
	if(dataList['userBuseo'] != '' && dataList['userBuseo'] != null) {
		if(dataList['userBuseo'].length > 20) {
			alert('부서는 20자 이하로 제한되어있습니다.');
			$('#input_user_buseo').focus();
			return false;
		} else {
		}
	}
	if(dataList['userTel'] != '' && dataList['userTel'] != null){
		var homeRegExp = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;
		var phoneRegExp  = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		if(!(homeRegExp.test(dataList['userTel']) || phoneRegExp.test(dataList['userTel']))) {
			alert('연락처를 올바른 형식에 맞게 입력하세요. ex) 010-1234-4567, 031-123-2425');
			$('#input_user_tel').focus();
			return false;
		}
	}
	if(dataList['userEmail'] != '' && dataList['userEmail'] != null) {
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if(!regExp.test(dataList['userEmail'])) {
			alert('이메일을 올바른 형식에 맞게 입력하세요. ex) pms@com4in.co.kr');
			$('#input_user_email').focus();
			return false;
		}
	}
	if(dataList['userName'] != '' && dataList['userName'] != null && dataList['userPost'] != '' && dataList['userPost'] != null) {
		$.ajax({
			url : '/main/user/insertUser',
			type : 'POST',
			data : Object.assign(dataList, { '_csrf' : csrfVal }),
			success : function(data) {
				console.log(data);
				alert('사용자가 신규 등록되었습니다.')
				location.href = "/main/user";
			}, error : function(error) {
				console.log(error);
			}
		});
	}
});
/**
 * 사용자 수정
 * @returns
 */
$('#btn_user_update').on('click', function() {
	var csrfVal = $('#csrfValue').val();
	var userCode = $('#update_user_code').val();
	var dataList = {
			USERCODE : $('#update_user_code').val(),
			userName : $('#update_user_name').val().trim(),
			userPost : $('#update_user_post').val(),
			userLevel : $('#update_user_level').val(),
			userBuseo : $('#update_user_buseo').val().trim(),
			userTel : $('#update_user_tel').val().trim(),
			userEmail : $('#update_user_email').val().trim()
	}
	if(dataList['userName'] == '' || dataList['userName'] == null) {
		alert('이름을 입력하세요.');
		$('#update_user_name').val('');
		$('#update_user_name').focus();
		return false;
	} else if(dataList['userName'] != '' && dataList['userName'] != null) {
		if(dataList['userName'].length > 20) {
			alert('이름은 20자 이하로 제한되어 있습니다.');
			$('#update_user_name').focus();
			return false;
		} else {
			var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
    		if(special_pattern.test(dataList['userName'])) {
    			alert('이름은 특수문자는 사용할 수 없습니다.');
    			$('#update_user_name').focus();
    			return false;
    		}
		}
	}
	if(dataList['userPost'] == '' || dataList['userPost'] == null) {
		alert('직위을 선택하세요.');
		return false;
	}
	if(dataList['userBuseo'] != '' && dataList['userBuseo'] != null) {
		if(dataList['userBuseo'].length > 20) {
			alert('부서는 20자 이하로 제한되어있습니다.');
			$('#update_user_buseo').focus();
			return false;
		}
	}
	if(dataList['userTel'] != '' && dataList['userTel'] != null){
		var homeRegExp = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;
		var phoneRegExp  = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		if(!(homeRegExp.test(dataList['userTel']) || phoneRegExp.test(dataList['userTel']))) {
			alert('연락처를 올바른 형식에 맞게 입력하세요. ex) 010-1234-4567, 031-123-2425');
			$('#update_user_tel').focus();
			return false;
		}
	}
	if(dataList['userEmail'] != '' && dataList['userEmail'] != null) {
		var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
		if(!regExp.test(dataList['userEmail'])) {
			alert('이메일을 올바른 형식에 맞게 입력하세요. ex) pms@com4in.co.kr');
			$('#update_user_email').focus();
			return false;
		}
	}
	if(dataList['userName'] != '' && dataList['userName'] != null && dataList['userPost'] != '' && dataList['userPost'] != null) {
		//	사용자 수정 
	} else {
	}
});	


/**
 * [사용자] 신규 등록 및 수정 팝업
 * @param target
 * @returns
 */
function userPopUp(target) {
	if(target == '#btn_user_new') {
	    $(target).on('click', function() {
	        if(target == '#btn_user_new') {
	            var tar = $('#user_new');
	            tar.modal('show');
	            tar.find('.modal-title').text('[사용자]신규');
	            $('.insert_user').val('');
	            $('.insert_user').prop('selectedIndex', 0);
	            $('.insert_user').attr('src', "");
	        }
	    });
	} else if(target == '#btn_user_res') {
		$(target).on('click', function() {
	        if(target == '#btn_user_new') {
	            var tar = $('#user_new');
	            tar.modal('show');
	            tar.find('.modal-title').text('[사용자]수정');
	            $('.insert_user').val('');
	            $('.insert_user').prop('selectedIndex', 0);
	            $('.insert_user').attr('src', "");
	        }
	    });
	}
}
/**
 * [사용자] 신규 팝업
 */
userPopUp('#btn_user_new');
/**
 * [사용자] 사용자 연락처 유효성 검사(html : onkeyup 속성 이용) 
 * @param obj
 * @returns
 */
function inputPhoneNumber(obj) {
	    var number = obj.value.replace(/[^0-9]/g, '');
	    var phone = "";
	    if (number.length < 4) {
	        return number
	    } else if (number.length < 7) {
	        phone += number.substr(0, 3);
	        phone += "-";
	        phone += number.substr(3) 
	    } else if (number.length < 10) {
	    	phone += number.substr(0, 2);
	        phone += "-";
	        phone += number.substr(2, 3);
	        phone += "-";
	        phone += number.substr(5)
	    } else if (number.length < 11) {
	        phone += number.substr(0, 3);
	        phone += "-";
	        phone += number.substr(3, 3);
	        phone += "-";
	        phone += number.substr(6)
	    } else {
	        phone += number.substr(0, 3);
	        phone += "-";
	        phone += number.substr(3, 4);
	        phone += "-";
	        phone += number.substr(7)
	    }
	    obj.value = phone;
}
/**
 * 이미지 업로드
 */
function uploadFile(target)  {
    var formData = new FormData();
    var csrfValue = $('#csrfValue').val();
    formData.append('Mtfile',$('#uploadImg')[0].files[0]);
    formData.append('userCode',target);
    $.ajax({
        url : 'main/file/insertImg',
        type : 'POST',
        data : Object.assign(formData, {'_csrf' : csrfValue }),
        enctype : 'multipart/form-data',
        processData : false,
        contentType : false,
        success : function(data) {
            console.log("이미지 업로드 성공");
            console.log(data);
        }, error : function(error) {
            console.log("이미지 업로드 실패");
        }
    });
}
