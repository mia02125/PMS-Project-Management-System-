/**
 * 이미지 & 이력서 파일 선택 리스너
 * @param fileInfo
 * @returns
 */

function changeImgValue(fileInfo){
	 var fileName = fileInfo.files[0].name;
	 var fileDivision = fileName.contain
	 $("#imgNameText").text(fileName);
}
$('#form_main').each(function() {
	this.reset();
})
/**
 * 프로젝트 등급 표시
 */  
var det_projLevel = $('#ipt_proj_level').val();
$('#select_proj_level').val(det_projLevel);
/**
 *  사용자 등급 표시
 */ 
var det_userLevel = $('#ipt_user_level').val();
$('#select_user_level').val(det_projLevel);
/**
 * 사용자 직위 표시
 */  
var det_userPost = $('#ipt_user_post').val();
$('#select_user_post').val(det_userPost);
/**
 * 프로젝트 상세정보 selectBox 데이터값 넣기
 * @type {jQuery|string|undefined}
 */
var det_status = $('#ipt_proj_status').val();
$('#update_proj_status').val(det_status);
var det_proj_level = $('#ipt_proj_level').val();
$('#update_proj_level').val(det_proj_level);
var det_proj_status = $('#ipt_proj_status').val();
$('#update_proj_status').val(det_proj_status);
/**
 * 투입인원 상세정보 selectBox 데이터값 넣기
 * @type {jQuery|string|undefined}
 */
var det_division = $('#userDivision').val();
$('#update_user_division').val(det_division);
/**
 * 투입인원 상세정보 시작일자, 완료일자 데이터값 넣기 
 */
var det_inDate = $('#userInDate').val();
var det_outDate = $('#userOutDate').val();
$('#update_inputUser_inDate').val(det_inDate);
$('#update_inputUser_outDate').val(det_outDate);
/**
 * 이미지명 표시 
 */
var det_imgName = $('#imgNMText').val();
if(det_imgName != null && det_imgName != '') {
	$('#imgNameText').html(det_imgName);
} else {
	$('#imgNameText').html('파일이 없습니다.');
}
/**
 * 이력서명 표시 
 */
var det_careerName = $('#careerNMText').val();
if(det_careerName != null && det_careerName != '') {
	$('#careerNameText').html(det_careerName);
} else {
	$('#careerNameText').html('파일이 없습니다.');
}
/**
 * 이미지 표시 
 */
var select_imgPath = $('#select_imgPath').val();
if(select_imgPath != null && select_imgPath != '') {
	$('.select_imgPath').attr('src',select_imgPath);
} else {
	$('.select_imgPath').attr('src','data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMIAAAEDCAMAAABQ/CumAAAAflBMVEX///+4uLi7u7v7+/vAwMD09PTFxcXp6enc3Nz4+PjIyMi6uro8PDw/Pz/w8PCzs7PPz8/l5eXV1dU4ODinp6dTU1NFRUVZWVlOTk7T09OIiIiXl5eurq50dHRKSkpmZmaUlJR4eHienp6Li4t+fn4sLCxoaGgWFhYmJiYyMjIE2RVEAAAE80lEQVR4nO3b6XqqOhQGYAiBhCHMBEQZHOo53v8NniTYVt2t7dkDyn6+90eLKO1aMWsBbbQsAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAIBFclj26BB+iZOlYUgfHcXP87MgSWie2vGjI/k5LvdI4nHXcknIHx3MT4g5DSlV8StpSJY2k5w4pzZJc5KbhzEhZFEzyWG5F5I0860V8fXjWJdzsJgUVPshNEyZjt21VQGwFZ3yWQYVv52Q3CNTwClV7TSxV8x5cFzfZdoP5bEVJ6YC/IxS1U4XM3981X7Iuf0EocnHJt70eCFim3pkav0sXKl8wmBR8WuUEqqnvBMHlJAgW1r8Sk5JptrpSmWynPZzjYVBtiLqdMaWGb9G1el4Oe3nQ1zNH99373l0iF/h5EL40ab97FOME+8LC0iBhvYdhC4hBS9mn7MXkUJw7/nlp+AghTkghWeAFJ4BUngGSOEZ/P0pLOQaybtzocrCRaRA7twuhMu4X/jC86eQ3Ltn05JnT8FyvvToCAEAAACe3YadN1jTWuzuS5+Sw8uiPW8fxLgrHpBD9gu/Mw72tRT9OQXe1ywtyO8J63/wh/r2ypmVP9wN8ORmRxpbvoo/ksN+J9fTvo3YW/vi7l33HxHX1e2/Z73TFLDDN/vtymweXq6XRZanMTwW8lhy1wqi3bRzLbtWDH864B+xprr9D+xGjPpb2gnRy87saqKrF7G+KkW1mxZqeNFm2ruSUtQPKAVW6RTSNraCTTBNqVKU6usoRL05TpMkjuTVQaNYB+J1kaEXvc6yfEfmvJ1zd9Pbn0mdwrrY1EUhajPWpY5pjGRi2WIqlFzUVwdLqer2ddIH0XrGuN+5gxhMdFzoFHbC3pRpI1K9y6Sw7phqMf001KnYXh6cRZ1lv6XATxcpsDwcu6qbI4VW1v3uIoVI98JGmqI1KVgqwYM8F6otDpcH86hVz6XnR347lbwV52VX90KIppwhg0zWvOt16WWN7kgmhVwO7xNJSeXAWlOeyVTgr+LouOunRhabI1yVL29rVc5yOJTzrEDZiZ21Fnr0WNVk5xTWOlCfvaWwFTR7MQOaRNfjehBCmtc4Rz3DskFNpUL09SHhsy2g2Yogqxv96+LhLYVK5FZad68pZM3gx8VhSmFzdbhfjip9XSednob7Qj3tlatZ18cc5LaPQhPNUa50jInqLNv8EKmNchrzoFCVUDcfpWDwf1Q1tGoAsr4+j3024yImXhXHcznudV2mRWl1TaNOTYEO2LSYbBvrAdazLfwohaSg6u2TuXoLz9PMHSpVUclmnr9Wvv9JsTypSLgs86iXzU7PheA0vr0uLfQ2//f2GsnSZ45cVYEIrErytVmJHg9VqWt67vMEG/Sc3pb+dptM88AdvfenTcOy6AcD26oUtpUMmOjiwXQ3VVdCqsY0R0+9YqL7dKlaePI+eaaNeFaXEUnVZR6JRv1Djs2a86Z5tk/KfNrl1UTiRxKVRNfEVl8yOZ06m7ey/eSA55NEYXbcRDR50YvqG30VtZe5K1VrtvzVo6P7Fla0bl2LwDXXUbGu4fIUeKoyvLZ4eXBw3zSe1ClEXn6wh4/+2Fcyio5L+bhPZjmlfbMvLeSwzh8Szm/DF7xMGgAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACM/wDCj0dZezasYAAAAABJRU5ErkJggg==');
}

/**
 * 파일 선택
 * @param
 * @returns
 */
$(document).on('click', '.btnImgFileCustom', function(e){
    e.preventDefault();
    var _fileInputId = $(this).val();
    $('#' + _fileInputId).click();
});

$(document).on('click', '.btnCareerFileCustom', function(e){
    e.preventDefault();
    var _fileInputId = $(this).val();
    $('#' + _fileInputId).click();
});

function textController(target, n) {
	$(target).val().substring(0, n);
}

/**
 * 파일 등록 시 썸네일 이미지 show
 * @returns
 */
$('#uploadImg').change(function() {
    if(this.files && this.files[0]) {
        var reader = new FileReader;
        reader.onload = function(data) {
            $('.select_imgPath').attr("src", data.target.result);
        }
        reader.readAsDataURL(this.files[0]);
    }
});
/**
 * [프로젝트] 팝업
 * @param target
 * @returns
 */
function projectPopUp(target) {
	if(target == '#btn_proj_new') {
		 $(target).on('click', function() {
		        var tar = $('#proj_new');
		        tar.modal('show');
		        tar.find('.modal-title').text('[프로젝트] 신규');
		        $('.insert_proj').val('');
		        $('.insert_proj').prop('selectedIndex', 0);
		 });	
	} else if(target == '#btn_proj_res'){ 
		$(target).on('click', function() {
	        var tar = $('#proj_update');
	        tar.modal('show');
	        tar.find('.modal-title').text('[프로젝트] 수정');
	        $('.insert_proj').val('');
	        $('.insert_proj').prop('selectedIndex', 0);
		});   
	}
}
projectPopUp('#btn_proj_new');
/**
 * [프로젝트 상세보기 - 투입인원 수정팝업창]투입인원의 투입정보 상세데이터 가져오기 
 * @param target
 * @returns
 */
function inputedPopUp(target) {
	$(target).on('click', function() {
		var csrfValue = $('#csrfValue').val();
		var dataList = {
				userCode : $(this).attr('id'),
				projCode : $('#projCodeInfo').val()
			}
		var tar = $('#inputData_update');
        tar.modal('show');
        tar.find('.modal-title').text('[프로젝트] 투입정보 수정');
        $.ajax({
        	url : '/main/proj/bringInputUserData',
    		type : 'POST',
    		data : Object.assign(dataList, { '_csrf' : csrfValue }),
    		dataType : 'JSON',
    		success : function (data) {
    			$('#inputUserCode').val(data['userCode']);
    			$('#update_inputUser_division').val(data['userDivision']);
    			$('#update_inputUser_job').val(data['userJob']);
    			$('#update_inputUser_inDate').val(data['userInDate']);
    			$('#update_inputUser_outDate').val(data['userOutDate']);
    			$('#update_inputUser_comp').val(data['userComp']);
    		}, error : function(error) {
				alert('서버 내 투입인원 상세데이터 불러오는 이벤트 오류가 발생하였습니다.\n담당자에게 문의바랍니다.');
			}
        });
	});
}
inputedPopUp('.inputed_user_btn_res');
/**
 * [프로젝트 상세보기] 투입인원의 투입정보 상세데이터 수정 
 * @returns
 */
$('#btn_inputData_update').on('click', function() {
	var csrfValue = $('#csrfValue').val();
	var dataList = {
			userCode : $('#inputUserCode').val(),
			projCode: $('#projCodeInfo').val(),
			userDivision : $('#update_inputUser_division').val(),
			userComp : $('#update_inputUser_comp').val(),
			userJob : $('#update_inputUser_job').val(),
			userInDate : $('#update_inputUser_inDate').val(),
			userOutDate : $('#update_inputUser_outDate').val()
	}
	inputUserValidation(dataList, '#update_inputUser');
	if(dataList['userDivision'] != null && dataList['userDivision'] != '' && dataList['userJob'] != null && dataList['userJob'] != '' && dataList['userInDate'] != null && dataList['userInDate'] != '' && dataList['userOutDate'] != null && dataList['userOutDate'] != '') {
        var splInDate = $('#update_inputUser_inDate').val().split('.');
        var splOutDate = $('#update_inputUser_outDate').val().split('.');
        var InDate = splInDate[0] + splInDate[1] + splInDate[2];
        var OutDate = splOutDate[0] + splOutDate[1] + splOutDate[2];
        if(InDate <= OutDate) {
			$.ajax({
				url : '/main/proj/updateInputUser',
				type : 'POST',
				data : Object.assign(dataList, { '_csrf' : csrfValue }),
				dataType : 'JSON',
				success : function (data) {
					switch (data) {
					case 0 :
						alert('이미 담당자가 존재합니다.');
						break;
					case 1 :
						alert('투입정보가 수정되었습니다.');
						location.href = '/main/proj/detail?projCode= ' + dataList['projCode'];
						break;
					}
				}, error : function(error) {
					alert('투입인원 수정 이벤트 오류가 발생하였습니다.\n담당자에게 문의바랍니다.');
				}
			});
        }
	}
});
/**
 * [프로젝트 상세보기] 투입인원 팝업
 * @param target
 */
function projUserPopUp(target) {
    $(target).on('click', function() {
        var tar = $('#proj_user_new');
        tar.modal('show');
        tar.find('.modal-title').text('[프로젝트] 투입인원 추가');
        $('.insert_inputUser').val('');
        $('.insert_inputUser').prop('selectedIndex', 0);
    });
}
projUserPopUp('#btn_proj_user_new');


/**
 * 투입인원 추가 유효성 검사
 * @returns
 */
$(document).on('click','#btn_inputUser_insert', function() {
	var csrfValue = $('#csrfValue').val();
    var dataList = {
    		projCode : $('#projCodeInfo').val(),
    		userCode : $('#fixed_user_code').val(),
    		userComp : $('#input_inputUser_comp').val().trim(),
    		userDivision : $('#input_inputUser_division').val().trim(),
    		userJob : $('#input_inputUser_job').val().trim(),
    		userInDate : $('#input_inputUser_inDate').val(),
    		userOutDate : $('#input_inputUser_outDate').val()
    } 
    // 투입인원 추가 시 유효성 검사 
    inputUserValidation(dataList, '#input_inputUser');
    if(dataList['userDivision'] != null && dataList['userDivision'] != '' && dataList['userJob'] != null && dataList['userJob'] != '' && dataList['userInDate'] != null && dataList['userInDate'] != '' && dataList['userOutDate'] != null && dataList['userOutDate'] != '') {
        var splInDate = $('#input_inputUser_inDate').val().split('.');
        var splOutDate = $('#input_inputUser_outDate').val().split('.');
        var InDate = splInDate[0] + splInDate[1] + splInDate[2];
        var OutDate = splOutDate[0] + splOutDate[1] + splOutDate[2];
        var _InputUserCode = $('#fixed_user_code').val();
        /**
         * 투입인원 클릭 유효성 검사 
         * @returns
         */
        if(_InputUserCode != null && _InputUserCode != '') {
        	if(InDate <= OutDate) {
        		$.ajax({
        			url : '/main/proj/insertInputUser',
        			type : 'POST',
        			data : Object.assign(dataList, { '_csrf' : csrfValue }),
        			success : function(data) {
        				if(data.userDivision == '담당자') {
        					alert('이미 담당자가 존재합니다.');
        					return false;
        				} else {
        					if(data.userCode == dataList['userCode']) {
        						alert('이미 투입된 인원입니다.');
        						return false;
        					} else {	        					
        						alert('인원이 추가되었습니다.');	
        						location.href = '/main/proj/detail?projCode=' + dataList['projCode'];
        					}
        				}
        			}, error : function(error) {
        				console.log(error);
        			}
        		});
        	} else {
        		alert('완료일시를 정확하게 입력하세요.');
	            $('#proj_inDate').val('');
	            return false;
	        }
        } else {
        	alert('사용자를 지정하세요');
			return false;
        }
    } else {
        return false;
    }
    
});
/**
 * [프로젝트] 프로젝트 신규 등록 유효성 검사
 * @param target
 * @returns
 */
$('#btn_proj_insert').on('click', function() {
	var csrfValue = $('#csrfValue').val();
    var dataList = {
   		_csrf : csrfValue,
   		projName : $('#input_proj_name').val().trim(),
        projBudget : $('#input_proj_budget').val().trim(),
        projStatus : '미진행',
        projType : $('#input_proj_type').val().trim(),
        projLevel : $('#input_proj_level').val().trim(),	
        projInDate : $('#input_proj_inDate').val().trim(),
        projOutDate : $('#input_proj_outDate').val().trim()   
    }        
    // 프로젝트 등록 및 수정 시 유효성 검사
    projValidation(dataList, '#input_proj');
    if(dataList['projName'] != '' && dataList['projName'] != null && dataList['projBudget'] != '' && dataList['projBudget'] != null && dataList['projInDate'] != '' && dataList['projInDate'] != null && dataList['projOutDate'] != '' && dataList['projOutDate'] != null) {
    	/**
    	 * [프로젝트]날짜 유효성 검사 : 종료날짜가 시작날짜보다 이른지 확인
    	 * @returns
    	 */
    	var splInDate = dataList['projInDate'].split('.');
    	var splOutDate = dataList['projOutDate'].split('.');
    	var INDATE = splInDate[0] + splInDate[1] + splInDate[2];
    	var OUTDATE = splOutDate[0] + splOutDate[1] + splOutDate[2];
    	if(OUTDATE >= INDATE) {
    		$.ajax({
    			url : '/main/proj/insertProj',
    			type : 'POST',
    			data : Object.assign(dataList, { '_csrf' : csrfValue }),
    			success : function (data) {
    				alert('프로젝트가 신규등록되었습니다.');
    				location.href = '/main/proj';
    			}, error : function (error) {
    				console.log(error);
    			}
    		});
    	} else {
    		alert('완료일시를 정확하게 입력하세요.');
    		$('#input_proj_outDate').val('');
    		return;
    	}
    }
});
/**
 * [프로젝트] 프로젝트 수정 
 * @returns
 */
$('#btn_proj_update').on('click', function() {
	var csrfValue = $('#csrfValue').val();
	var dataList = {
			_csrf : csrfValue,
			projCode : $('#projCodeInfo').val(),
			projName : $('#update_proj_name').val().trim(),
			projType : $('#update_proj_type').val().trim(),
			projLevel : $('#update_proj_level').val(),
			projBudget : $('#update_proj_budget').val().trim(),
			projStatus : $('#update_proj_status').val(),
			projInDate : $('#update_proj_inDate').val(),
			projOutDate : $('#update_proj_outDate').val()
	}
	projValidation(dataList, '#update_proj');
	if(dataList['projName'] != '' && dataList['projName'] != null) {
		/**
		 * [프로젝트]날짜 유효성 검사  : 종료날짜가 시작날짜보다 이른지 확인
		 * @returns
		 */
		var INDATE = dataList['projInDate'].split('.');
		var OUTDATE = dataList['projOutDate'].split('.');
		var INDATE1 = INDATE[0] + INDATE[1] + INDATE[2];
		var OUTDATE1 = OUTDATE[0] + OUTDATE[1] + OUTDATE[2];
		if(OUTDATE1 >= INDATE1) {			
			$.ajax({
				url : '/main/proj/updateProj',
				type : 'POST',
				data : Object.assign(dataList, { '_csrf' : csrfValue }),
				success : function (data) {
					if(data.alert == 'insert') {
						alert('프로젝트가 신규등록되었습니다.');
						location.href = '/main/proj';
					} else if(data.alert == 'update') {
						alert('프로젝트가 수정되었습니다.');
						location.href = '/main/proj/detail?projCode=' + dataList['projCode'];
					} else {
						alert(data.alert);
					}

				}, error : function(error) {
					console.log(error);
				}
			});
		} else {
			alert('완료일자가 시작일자보다 빠릅니다.');
			return false;
		}
	} else {
		return false;
	}
});


/**
 * [프로젝트]프로젝트 예산 유효성 검사  :  자동콤마 생성
 * @returns
 */
$('input[name=projBudget]').bind('keyup', function() {
    inputNumberFormat(this);

    //입력한 문자열 전달
    function inputNumberFormat(obj) {
        obj.value = comma(uncomma(obj.value));
    }
    //콤마찍기
    function comma(str) {
        str = String(str);
        return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
    }
    //콤마풀기
    function uncomma(str) {
        str = String(str);
        return str.replace(/[^\d]+/g, '');
    }
    //숫자만 리턴(저장할때)
    function cf_getNumberOnly (str) {
        var len      = str.length;
        var sReturn  = "";
        for (var i=0; i<len; i++){
            if ( (str.charAt(i) >= "0") && (str.charAt(i) <= "9") ){
                sReturn += str.charAt(i);
            }
        }
        return sReturn;
    }
});

/**
 * [프로젝트 & 사용자]clear : 입력값 초기화
 * @returns
 */
/**
 * 프로젝트 현황
 * @returns
 */ 
$('#btn_proj_clear').on('click', function() {
    $('.select_proj').val('');
    $('.select_proj').prop('selectedIndex','0');
});
/**
 * 사용자 현황
 * @returns
 */  
$('#btn_user_clear').on('click', function() {
    $('.select_user').val('');
    $('.select_user').prop('selectedIndex','0');
});
/**
 * 투입인원 현황 팝업창
 * @returns
 */  
$('#btn_clear_user').on('click', function () {
    $('.select_inputUser').val('');
    $('.select_inputUser').prop('selectedIndex','0');
});
/**
 * [프로젝트 & 사용자] 수정 버튼 클릭 이벤트
 */
$(document).ready(function () {
	var projCodeInfo = $('#projCodeInfo').val();
	var dataList = {
			projName : $('#update_proj_name').val(),
			projBudget : $('#update_proj_budget').val(),
			projInDate : $('#update_proj_inDate').val(),
			projOutDate : $('#update_proj_outDate').val(),
			userName : $('#update_user_name').val(),
			userPost : $('#update_user_post').val()
	}
    // 프로젝트 수정 
    $('.btn_proj_res').attr('hidden', false);
    $('.btn_proj_res').on('click', function () {
        $('.update_proj').attr('disabled', false);
        $('.btn_proj_update').attr('hidden', false);
        $('.btn_proj_res').attr('hidden', true);
    });    	
    // 사용자 수정 
    $('.btn_user_res').attr('hidden', false);
    $('.btnCareerFileCustom').attr('hidden', true);
    $('.btnImgFileCustom').attr('hidden', true);
    $('.update_user').attr('disabled', true);
    $('.btn_user_res').on('click', function() {
        $('.btn_user_res').attr('hidden', true);
        $('.btnCareerFileCustom').attr('hidden', false);
        $('.btnImgFileCustom').attr('hidden', false);
        $('.btn_user_update').attr('hidden', false);
        $('.update_user').attr('disabled', false);
    });
    // 투입인원 수정 
    $('.btn_inputUser_res').attr('hidden', false);
    $('.btn_inputUser_res').on('click', function() {
        $('.btn_inputUser_res').attr('hidden', true);
        $('.btn_inputUser_update').attr('hidden', false);
        $('.update_inputUser').attr('disabled', false);
    })
    $('#btn_inputUser_update').on('click', function() {
        $('.btn_inputUser_res').attr('hidden', false);
        $('.btn_inputUser_update').attr('hidden', true);
        $('.update_inputUser').attr('disabled', true);
    })
});
/**
 * 전체 체크박스 제어
 */
function allCheck() {
    $('.allCheckBox').on('click', function() {
        if($('.allCheckBox').prop('checked')) {
            $('input[class=OneCheckBox]').prop("checked",true);
        } else {
            $('input[class=OneCheckBox]').prop("checked",false);
        }
    });
}
allCheck();

/**
 * 프로젝트 / 사용자 삭제 시 유효성 검사 
 */
function deleteUserProj(target) {
	$(target).on('click', function() {
		var count = $('input[class=OneCheckBox]:checked').length;
		var arr = new Array();
		$('input[class=OneCheckBox]:checked').each(function() {
			arr.push($(this).attr('id'));
		});
		if(arr.length != 0) {
			var result = confirm(count + "개의 목록을 삭제하시겠습니까?");
			if(result == true) {
				deleteUserProjImpl(target, arr);
			}
		} else {
			alert('제거할 대상이 없습니다.');
		}

    });
}
/**
 * 프로젝트 & 사용자 삭제 ajax 
 * @param target
 * @param arr
 * @returns
 */
function deleteUserProjImpl(target, arr) {
	// 프로젝트 삭제
    if(target == '#btn_proj_del') {
    	var csrfValue = $('#csrfValue').val();
    	var dataList = {
    			projCode : arr
    	}
    	$.ajax({
    		url : '/main/proj/deleteProj',
    		type : 'POST',
    		data : Object.assign(dataList, {'_csrf' : csrfValue }),
    		success : function (data) {
    			alert(data.alert);
    			document.location = '/main/proj';
    		}, error : function(error) {
				console.log(error);
			}
    	});
    } else if(target == '#btn_user_del') {
        // 사용자 삭제
    	var csrfValue = $('#csrfInfo').val();
    	var dataList = {
    			userCode : arr
    	}
    	$.ajax({
    		url : '/main/user/deleteUser',
    		type : 'POST',
    		data : Object.assign(dataList, {'_csrf' : csrfValue }),
    		success : function(data) {
				alert(data.alert);
				location.href = '/main/user';
			}, error : function(error) {
				console.log(error);
			}
    	});
    } else if(target == '#btn_proj_user_del') {
    	var csrfValue = $('#csrfValue').val();
    	var dataList = {
    			projCode : $('#projCodeInfo').val(),
    			userCode : arr
    	}
    	$.ajax({
    		url : '/main/proj/deleteInputUser',
    		type : 'POST',
    		data : Object.assign(dataList, {'_csrf' : csrfValue}),
    		success : function(data) {
				alert(data.alert);
				location.href = '/main/proj/detail?projCode=' + dataList['projCode'];
			}, error : function(error) {
				alert('투입인원 삭제 이벤트 오류가 발생하였습니다.\n담당자에게 문의바랍니다.');
			}
    	});
    }
}
deleteUserProj('#btn_user_del');
deleteUserProj('#btn_proj_del');
deleteUserProj('#btn_proj_user_del');


/**
 * [투입인원추가팝업창]해당 투입팝업창에서 투입인원 데이터값 불러오기
 * @returns
 */
$(document).on('click','.userList', function() {
	var csrfValue = $('#csrfValue').val();
	var dataList = {
        userCode : $(this).attr('id')
    }
    $.ajax({
    	url : '/main/proj/bringInputData',
		type : 'POST',
		data : Object.assign(dataList, { '_csrf' : csrfValue }),
		dataType : 'JSON',
		success : function (data) {
			$('#fixed_user_code').val(data['userCode']);
			$('#fixed_user_name').val(data['userName']);
			$('#fixed_user_post').val(data['userPost']);
			$('#fixed_user_level').val(data['userLevel']);
			$('#fixed_user_buseo').val(data['userBuseo']);
			$('#fixed_user_tel').val(data['userTel']);
			$('#fixed_user_email').val(data['userEmail']);	
			$('#input_inputUser_division').val('');
			$('#input_inputUser_job').val('');
			$('#input_inputUser_inDate').val('');
			$('#input_inputUser_outDate').val('');
		}, error : function(error) {
			console.log(error);
		}
    });
    console.log(dataList);
    // ajax 를 이용해 DB에서 해당 row 데이터를 가져와서 밑에 input에 뿌리기
}); 
function enterKey() {
	if(window.event.keyCode == 13) {
		$('#btn_find').click();
		$('#btn_user_find').click();
	}
}
/**
 * 투입인원 검색
 * @returns
 */
$('#btn_find_user').on('click', function() {
	var csrfVal = $('#csrfValue').val();
	var dataList = {
			userName : $('#select_inputUser_name').val(),
			userPost : $('#select_inputUser_post').val(),
			userLevel : $('#select_inputUser_level').val()
	} 
	$.ajax({
		url : '/main/proj/findInputUser',
		type : 'POST',
		data : Object.assign(dataList, { '_csrf' : csrfVal }),
		success : function(data) {
			var tbody = $('#pop_tbody2');
			var str = '';
			if(data.length > 0){
				$.each(data, function(i) {
					str += '<tr class="userList"  id="' + data[i].userCode + '">';
					str +=		'<input class="selectUserCode" id="selected_user_code" value=' + data[i].userCode + '>';
					str += 		'<td id="selected_user_post" class="t-left">' + data[i].userPost+ '</td> ';
					str += 		'<td id="selected_user_name" class="t-left">' + data[i].userName + '</td>';
					str += 		'<td id="selected_user_buseo" class="t-left">'+ data[i].userBuseo +'</td>';
					str += 		'<td id="selected_user_tel" class="t-left">'+ data[i].userTel +'</td>';
					str += 		'<td id="selected_user_email" class="t-left">'+ data[i].userEmail  +'</td>';
					str += '</tr>'	
				});
			} else if(data.length <= 0) {
				str += '<tr class="userList">';
				str += 		'<td colspan="5" id="selected_user_post" class="t-center">검색된 결과가 없습니다.</td> ';
				str += '</tr>'
			}
				tbody.html(str);
		}, error : function(error) {
		}
	});
});




/**
 * 프로젝트 등록 및 수정 시 유효성 검사 
 * 2021-01-04 리팩토링 :  projValidation 추가   
 */
function projValidation(dataList, target) {
	if(dataList['projName'] == '' || dataList['projName'] == null) {
		alert('프로젝트명을 입력하세요.');
		$(target + '_name').val('');
		$(target + '_name').focus();
		return false;
	} else if(dataList['projName'] != '' && dataList['projName'] != null) {
		if(dataList['projName'].length > 50) {
			alert('프로젝트명은 50자로 제한되어있습니다.');
			$(target + '_name').val('');
			$(target + '_name').focus();
			return false;
		} else {
			var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
			if(special_pattern.test(dataList['projName'])) {
				alert('프로젝트명은 특수문자를 사용할 수 없습니다.');
				$(target + '_name').focus();
				return false;
			}
		}
	}
	if(dataList['projType'] != '' && dataList['projType'] != null) {
		if(dataList['projType'].length > 10) {
			alert('유형은 10자로 제한되어있습니다.');
			$(target + '#_type').val('');
			$(target + '#_type').focus();
			return false;
		} else {
			var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
			if(special_pattern.test(dataList['projType'])) {
				alert('유형은 특수문자를 사용할 수 없습니다.');
				$(target + '#_type').focus();
				return false;
			}
		}
	}
	if(dataList['projBudget'] == '' || dataList['projBudget'] == null ) {
		alert('예산을 입력하세요');
		$(target + '_budget').val('');
		$(target + '_budget').focus();
		return false;
	} else if(dataList['projBudget'] != '' && dataList['projBudget'] != null ) {	
		var budgetLength = dataList['projBudget'].length;
		if(budgetLength < 1 || budgetLength > 15) {
			alert('1 ~ 10,000,000,000원 이하로 입력하세요.');
			$(target + '_budget').val('');
			$(target + '_budget').focus();
			return false;
		}     	
	} 
	if(dataList['projInDate'] == '' || dataList['projInDate'] == null) {
		alert('시작일자를 입력하세요.');
		$(target + '_inDate').val('');
		$(target + '_inDate').focus();
		return false;
	} else if(dataList['projInDate'] != '' && dataList['projInDate'] != null) {	
		var date_pattern = /^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/;
		if(!date_pattern.test(dataList['projInDate'])) {
			alert('시작일자를 정확하게 입력하세요. ex) 1900.01.01 ~ 2099.12.31');
			$(target + '_inDate').val('');
			$(target + '_inDate').focus();
			return false;
		}
	} 
	if(dataList['projOutDate'] == '' || dataList['projOutDate'] == null) {
		alert('완료일자를 입력하세요.');
		$(target + '_outDate').val('');
		$(target + '_outDate').focus();
		return false;
	} else if(dataList['projOutDate'] != '' && dataList['projOutDate'] != null) {
		var date_pattern = /^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/;
		if(!date_pattern.test(dataList['projOutDate'])) {
			alert('완료일자를 정확하게 입력하세요. ex) 1900.01.01 ~ 2099.12.31');
			$(target + '_outDate').val('');
			$(target + '_outDate').focus();
			return false;
		}
	}
}

/**
 * 투입인원 추가 시 유효성 검사
 * 2021-01-04 리팩토링 :  inputUserValidation 추가 
 */
function inputUserValidation(dataList, target) {
	if(dataList['userDivision'] == null || dataList['userDivision'] == '') {
		alert('구분을 선택하세요.');
		return false;
	}
	if(dataList['userComp'] == null || dataList['userComp'] == '') {
		alert('소속을 입력하세요.');
		return false;
	} else if(dataList['userComp'] != null && dataList['userComp'] != '') {
		if(dataList['userComp'].length > 20) {
			alert('소속은 20자 이하로 제한되어있습니다.');
			$(target + '_comp').focus();
			return false;
		}
	}
	if(dataList['userJob'] == null || dataList['userJob'] == '') {
		alert('직책을 입력하세요.');
		$('#input_inputUser_job').val('');
		$('#input_inputUser_job').focus();
		return false;
	} else if(dataList['userJob'] != null && dataList['userJob'] != '') {
		if(dataList['userJob'].length > 20) {
			alert('직책은 20자 이하로 제한되어 있습니다.');
			$(target + '_job').focus();
			return false;
		} else {
			var special_pattern = /[`~!@#$%^&*|\\\'\";:\/?]/gi;
			if(special_pattern.test(dataList['userJob'])) {
				alert('직책은 특수문자는 사용할 수 없습니다.');
				$(target + '_job').focus();
				return false;
			}
		}
	}
	if(dataList['userInDate'] == '' || dataList['userInDate'] == null) {
		alert('시작일자를 입력하세요.');
		$(target + '_inDate').val('');
		$(target + '_inDate').focus();
		return false;
	} else if(dataList['userInDate'] != '' && dataList['userInDate'] != null) {	
		var date_pattern = /^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/;
		if(!date_pattern.test(dataList['userInDate'])) {
			alert('시작일자를 정확하게 입력하세요. ex) 1900.01.01 ~ 2099.12.31');
			$(target + '_inDate').val('');
			$(target + '_inDate').focus();
			return false;
		}
	} 
	if(dataList['userOutDate'] == '' || dataList['userOutDate'] == null) {
		alert('완료일자를 입력하세요.');
		$(target + '_outDate').val('');
		$(target + '_outDate').focus();
		return false;
	} else if(dataList['userOutDate'] != '' && dataList['userOutDate'] != null) {
		var date_pattern = /^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/;
		if(!date_pattern.test(dataList['userOutDate'])) {
	    	alert('완료일자를 정확하게 입력하세요. ex) 1900.01.01 ~ 2099.12.31');
	    	$(target + '_outDate').val('');
	    	$(target + '_outDate').focus();
	    	return false;
		}
	}
}
