/**
 * 이력서 유효성 검사 
 */

// 사용자 상세정보 selectBox 데이터값 넣기
var det_career_post = $('#ipt_update_career_post').val();
$('#insert_career_post').val(det_career_post);
/**
 * [사용자] 이력서 신규 등록 및 수정 팝업 
 * @param tar
 * @returns
 */
function careerPopUp(target) {
	if(target == '#btn_career_insert_popUp') {
		$(target).on('click', function() {
			var tar = $('#career_update_popUp');
			tar.modal('show');
			tar.find('.modal-title').text('[사용자] 이력서 등록');
			$('#btn_career_update').attr('hidden','true');
			$('#btn_career_delete').attr('hidden','true');
		});
	} else if(target == '#btn_career_update_popUp') {
		$(target).on('click', function() {
			var tar = $('#career_update_popUp');
			tar.modal('show');
			tar.find('.modal-title').text('[사용자] 이력서 수정');
			$('#btn_career_insert').attr('hidden','true');
		});
	} else if(target == '.btn_inputed_user_career') {
		$(target).on('click', function() {
			var tar = $('#career_update_popUp');
			tar.modal('show');
			tar.find('.modal-title').text('[사용자] 이력서 보기');
			$('#btn_career_insert').attr('hidden','true');
			$('#btn_career_update').attr('hidden','true');
			$('#btn_career_delete').attr('hidden','true');
			$('.modal input').attr('readonly','readonly');
		});
	}
}
careerPopUp('#btn_career_insert_popUp');
careerPopUp('#btn_career_update_popUp');
careerPopUp('.btn_inputed_user_career');
/**
 * row 추가(자격증, 근무경력, Skill Inventory) 
 * @param target
 * @returns
 */
function addInputList(target) {
	if(target == '#btn_certificate_add') {
		$(document).on('click',target,function() {
			html = '';
			html += '<tr class="career_cert">';
			html += 	'<td><input type="text" class="form-control insert_cert_name number-text-01" name="resCertName"  maxlength="20" value="" /></td>';
			html += 	'<td><input type="text" class="datepicker form-control insert_cert_date number-text-01" name="resCertDate" onkeyup="inputDateNumber(this);" oninput="num_check(this);"  maxlength="20" value="" /></td>';
			html += 	'<td><input type="text" class="form-control insert_cert_place number-text-01" name="resCertAgency" maxlength="20" value="" /></td>';
			html +=		'<td><button type="button" onclick="deleteRow(this);" class="btn btn-type-04">삭제</button></td>';
			html += '</tr>';
			if($('tr[class="career_cert"]').length > 9) {
				alert('최대 10row 추가할 수 있습니다.');
				return false;
			} else {
				$('tbody[class=ipt_certificate]').append(html);
			}
		});
	} else if(target == '#btn_career_add') {
		$(document).on('click',target,function() {
			html = '';
			html += '<tr class="career_comp">';
			html += 	'<td><input type="text" class="form-control insert_comp_name number-text-01" maxlength="20" autocomplete="off" name="resCareerComp" value="" /></td>';
			html += 	'<td style="display: flex;">';
			html += 		'<input type="text" style="width: 43%" autocomplete="off" class="form-control datepicker insert_comp_stDate number-text-01" onkeyup="inputDateNumber(this);" maxlength="10" name="resCareerStDate" oninput="num_check(this);" value="" />';
			html += 		'&nbsp;&nbsp;~&nbsp;&nbsp;';
			html += 		'<input type="text" style="width: 43%" autocomplete="off" class="form-control datepicker insert_comp_fiDate number-text-01" onkeyup="inputDateNumber(this);" maxlength="10" name="resCareerFiDate" oninput="num_check(this);" value=""/>';
			html += 	'</td>';
			html += 	'<td><input type="text" class="form-control insert_comp_post number-text-01" maxlength="10" autocomplete="off" name="resCareerPost" value=""/></td>';
			html += 	'<td><input type="text" class="form-control insert_comp_job number-text-01" maxlength="20" autocomplete="off" name="resCareerContent" value=""/></td>';
			html +=		'<td><button type="button" onclick="deleteRow(this);" class="btn btn-type-04">삭제</button></td>';
			html += '</tr>';
			if($('tr[class="career_comp"]').length > 9) {
				alert('최대 10row 추가할 수 있습니다.');
				return false;
			} else {
				$('tbody[class=ipt_comp]').append(html);
			}
		});
	}
}
	addInputList('#btn_certificate_add');
	addInputList('#btn_career_add');
	addInputList('.btn_proj_add');
/**
 * skill inventory row 제거 
 * @param target
 * @returns
 */
function deleteList(target) {
	$(document).on('click',target, function() {
		$(target).parent().remove();
	});	
}
/**
 * 근무 경력 및 자격증 row 제거
 * @param target
 * @returns
 */
function deleteRow(target) {
	$(document).on('click',target, function() {
		$(target).parent().parent().remove();
	});
}

/**
 * 숫자만 입력
 * @param target
 * @returns
 */
function num_check(target) {
	var num_check =/[^0-9][.]/g;
	if(num_check.test(target.value)) {
		$(target).val($(target).val().replace(num_check, ''));
		return false;
	}
}
function num_check2(target) {
	var num_check =/[^0-4]/g;
	if(num_check.test(target.value)) {
		alert('주민등록번호 올바른 형식에 맞게 작성하세요.');
		$(target).val($(target).val().replace(num_check, ''));
		return false;
	}
}

/**
 * 자격증 취득일자 유효성 검사 
 */
function cert_Check(target) {
	if(target.value != '') {
		const date_pattern = /^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/;
		if(!(date_pattern.test(target.value))) {
			alert('일자를 올바른 형식에 맞게 입력하세요. ex) 1900.01.01 ~ 2099.12.31');
			target.value = '';
			target.focus();
			return false;
		}
	}
}
/**
 * 이력서 등록
 * @returns
 */
function InsertUpdateCareer(target) {
	$(document).on('click',target ,function() {
		var dataList = {
				SSN1 : $('#insert_career_SSN1').val(), // 주민번호(앞자리)
				SSN2 : $('#insert_career_SSN2').val(), // 주민번호(뒷자리)
				careerDate : $('#insert_career_careerDate').val(), // 근무경력기간
				addr : $('#insert_career_addr').val(), // 주소 
				eduNM : $('#insert_edu_name').val(), // 대학명
				eduStDate : $('#insert_edu_admission').val(), // 입학일자 
				eduFiDate : $('#insert_edu_graduation').val(),  // 졸업일자
				eduMajor : $('#insert_edu_major').val(), // 학교전공 
				userTel : $('#insert_career_tel').val(), // 사용자 연락처 
				compStDate : $('.insert_comp_stDate'), 
				compFiDate : $('.insert_comp_fiDate'),
				certDate : $('.insert_cert_date')
		}
		if(dataList['SSN1'] == '' || dataList['SSN2'] == '' || dataList['careerDate'] == '' || dataList['addr']  == '' || dataList['eduNM'] == '' || dataList['eduStDate'] == '' || dataList['eduFiDate']  == '' && dataList['eduMajor'] == '' || dataList['userTel'] == '') {
			alert('신상 정보는 필수 입력해야합니다.');
			return false;
		}
		/**
		 * 최종학력 기간 
		 */
		if(dataList['eduStDate'] != '' && dataList['eduFiDate'] != '') {
			if(dataList['eduStDate'] > dataList['eduFiDate'])  {
				alert('입학연도와 졸업연도를 다시 확인하세요.');
				return false;
			}
			// 일자 유효성 검사 
			const date_pattern = /^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/;
			if(!(date_pattern.test(dataList['eduStDate']) || date_pattern.test(dataList['eduFiDate']))) {
				alert('일자를 올바른 형식에 맞게 입력하세요. ex) 1900.01.01 ~ 2099.12.31');
				return false;
			}
		}
		/**
		 * 근무경력 근무기간 유효성 검사  
		 */
		$.each(dataList['compStDate'], function(idx) {
			var compName = $('.insert_comp_name')[idx].value;
			var compStDate = dataList['compStDate'][idx].value;
			var compFiDate = dataList['compFiDate'][idx].value;
			if(compName != '' || (compStDate != '' && compFiDate != '')) {
				// 일자 유효성 검사 #1
				if(compStDate  > compFiDate)  {
					alert('근무기간을 다시 확인하세요.');
					return false;
				} 
				// 일자 유효성 검사  #2
				const date_pattern = /^(19|20)\d{2}.(0[1-9]|1[012]).(0[1-9]|[12][0-9]|3[0-1])$/;
				if(!(date_pattern.test(compStDate) || date_pattern.test(compFiDate))) {
					alert('일자를 올바른 형식에 맞게 입력하세요. ex) 1900.01.01 ~ 2099.12.31');
					return false;
				}
			}
		});
		/**
		 * 연락처 유효성 검사 
		 */
		var homeRegExp = /^(0(2|3[1-3]|4[1-4]|5[1-5]|6[1-4]))-(\d{3,4})-(\d{4})$/;
		var phoneRegExp  = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
		if($('#insert_career_tel').val() != '' && $('#insert_career_tel').val() != null) {
			if(!(homeRegExp.test($('#insert_career_tel').val()) || phoneRegExp.test($('#insert_career_tel').val()))) {
				alert('연락처를 올바른 형식에 맞게 입력하세요. ex) 010-1234-4567, 031-123-2425');
				return false;
			}	
		} 
		/**
		 * 주민번호 유효성 검사
		 */
		if($('#insert_career_SSN1').val() != '' && $('#insert_career_SSN1').val() != null) {
			
			var num_check2 = /^(?:[0-9]{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[1,2][0-9]|3[0,1]))$/;
			
			if(!(num_check2.test($('#insert_career_SSN1').val()))) {
				alert('주민번호를 올바른 형식에 맞게 입력하세요. ex) 950717 - 1******')
				$('#insert_career_SSN1').focus();
				return false;
			}
		}
		/**
		 * 이메일 유효성 검사 
		 * @param target
		 * @returns
		 */
		if($('#insert_career_email').val() != '' && $('#insert_career_email').val() != null) {
			var regExp = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
			if(!regExp.test($('#insert_career_email').val())) {
				alert('이메일을 올바른 형식에 맞게 입력하세요. ex) pms@com4in.co.kr');
				return false;
			}
		}
		/**
		 * 달력 유효성 검사 
		 */
		if($('.datepicker').val() != '' && $('.datepicker').val() != null) {
			var reg = /[^0-9]/g;
			if(!reg.test($('.datepicker').val())) {
				alert('일자를 올바른 형식에 맞게 입력하세요.');
				return false;
			}
		}
	});
}
InsertUpdateCareer('#btn_career_update');
InsertUpdateCareer('#btn_career_insert');
/**
 * 삭제 버튼 클릭 시 해당 row만 삭제 (자격증)
 * @returns
 */
$('.delete_certOne').on('click', function() {
	var csrfVal = $('#csrfValue').val();
	var dataList = {
		resCertCode : $(this).parent().parent().find('.insert_cert_code').val()
	}
	$.ajax({
		url : '/main/user/career/deleteOne',
		type : 'POST',
		data : Object.assign(dataList, { '_csrf' : csrfVal }),
		success : function(data) {
			alert('해당 데이터 삭제 완료');
		}, error : function(error) {
			console.log(error);
		}
	});
});
/**
 * 삭제 버튼 클릭 시 해당 row만 삭제 (근무경력)
 * @returns
 */
$('.delete_compOne').on('click', function() {
	var csrfVal = $('#csrfValue').val();
	var dataList = {
		resCareerCode : $(this).parent().parent().find('.insert_career_code').val(),
	}
	$.ajax({
		url : '/main/user/career/deleteOne',
		type : 'POST',
		data : Object.assign(dataList, { '_csrf' : csrfVal }),
		success : function(data) {
			alert('해당 데이터 삭제 완료');
		}, error : function(error) {
			console.log(error);
		}
	});
});
/**
 * 이력서 삭제 
 * @returns
 */
$('#btn_career_delete').on('click', function() {
	var csrfVal = $('#csrfValue').val();
	var dataList = {
			resCode : $('#ipt_update_career_code').val(),
			userCode : $('#update_user_code').val()
	}
	$.ajax({
		url : '/main/user/career/deleteCareer',
		type : 'POST',
		data : Object.assign(dataList, { '_csrf' : csrfVal }),
		success : function(data) {
			alert('삭제 완료되었습니다.');
			location.href = '/main/user/detail?userCode=' + dataList['userCode'];
		}, error : function(error) {
			console.log(error);
		}
	})
});
/**
 * 왼쪽공백 제거 
 * @param target
 * @returns
 */
function convLtrim(target) {
	let text = $(target).val();
	// Ltrim 정규식
	//String.prototype.Object 로직 변경 시 ltrim 인스턴스에 영향 
	String.prototype.ltrim = function() { 
		return this.replace(/^[ ]*/g, '');
	}
	return $(target).val(text.ltrim());
}
/**
 * 이력서 보기  
 * @returns
 */
$(document).on('click','.btn_inputData_pop',function() {
	var csrfVal = $('#csrfValue').val();
	var dataList = {
		userCode : $(this).attr('id'),
		checkUserCode : $('#checkUserCode').val()
	}
	$.ajax({
		url : '/main/proj/selectCareerDetail',
		type : 'POST',
		data : Object.assign(dataList, { '_csrf' : csrfVal }),
		success : function(data) {
			$('#checkUserCode').val(data.userVO['userCode']);
			$('#insert_career_name').val(data.userInfo['userName']);
			$('#insert_career_email').val(data.userInfo['userEmail']);
			$('#insert_career_tel').val(data.userInfo['userTel']);
			$('#insert_career_post').val(data.userInfo['userPost']);
			$('#insert_career_comp').val(data.userInfo['userLevel']);
			$('#insert_career_buseo').val(data.userInfo['userBuseo']);
			if(data.userInfo.career != null) {
				$('#insert_career_SSN1').val(data.userInfo.career['resSsn1']);
				$('#insert_career_SSN2').val(data.userInfo.career['resSsn2']);
				$('#insert_career_addr').val(data.userInfo.career['resAddr']);
				$('#insert_career_careerDate').val(data.userInfo.career['resCareerDays']);
				$('#insert_edu_name').val(data.userInfo.career['resEduName']);
				$('#insert_edu_admission').val(data.userInfo.career['resEduStDate']);
				$('#insert_edu_graduation').val(data.userInfo.career['resEduFiDate']);
				$('#insert_edu_major').val(data.userInfo.career['resEduMajor']);
			}
			// 근무 경력 
			if(data.compVOs != undefined) {
				$.each(data.compVOs, function(index, element) {
					html = '';
					html += '<tr class="career_comp">';
					html += 	'<td><input type="text" class="form-control insert_comp_name number-text-01" maxlength="20" name="resCareerComp" readonly="readonly" value="' + element.compVOs.careerComp + '" /></td>';
					html += 	'<td style="display: flex;">';
					html += 		'<input type="text" style="width: 43%" class="form-control datepicker insert_comp_stDate number-text-01" maxlength="10" readonly="readonly" name="resCareerStDate" value="' + element.compVOs.careerStDate + '" />';
					html += 		'&nbsp;&nbsp;~&nbsp;&nbsp;';
					html += 		'<input type="text" style="width: 43%" class="form-control datepicker insert_comp_fiDate number-text-01" maxlength="10" readonly="readonly" name="resCareerFiDate" value="' + element.compVOs.careerFiDate + '"/>';
					html += 	'</td>';
					html += 	'<td><input type="text" class="form-control insert_comp_post number-text-01" maxlength="10" readonly="readonly" name="resCareerPost" value="' + element.compVOs.careerPost + '"/></td>';
					html += 	'<td><input type="text" class="form-control insert_comp_job number-text-01" maxlength="20" readonly="readonly" name="resCareerContent" value="' + element.compVOs.careerContent + '"/></td>';
					html += '</tr>';
					$('.ipt_comp').append(html);
				});
			} else {
				html = '';
				html += '<tr class="career_comp">';
				html += 	'<td><input type="text" class="form-control insert_comp_name number-text-01" maxlength="20" name="resCareerComp" readonly="readonly" value="" /></td>';
				html += 	'<td style="display: flex;">';
				html += 		'<input type="text" style="width: 43%" class="form-control datepicker insert_comp_stDate number-text-01" maxlength="10" readonly="readonly" name="resCareerStDate" value="" />';
				html += 		'&nbsp;&nbsp;~&nbsp;&nbsp;';
				html += 		'<input type="text" style="width: 43%" class="form-control datepicker insert_comp_fiDate number-text-01" maxlength="10" readonly="readonly" name="resCareerFiDate" value=""/>';
				html += 	'</td>';
				html += 	'<td><input type="text" class="form-control insert_comp_post number-text-01" maxlength="10" readonly="readonly" name="resCareerPost" value=""/></td>';
				html += 	'<td><input type="text" class="form-control insert_comp_job number-text-01" maxlength="20" readonly="readonly" name="resCareerContent" value=""/></td>';
				html += '</tr>';
				$('.ipt_comp').append(html);
			}
			if(data.certVOs != undefined) {
				$.each(data.certVOs, function(index, element) {
					html = '';
					html += '<tr class="career_cert">';
					html += 	'<td><input type="text" class="form-control insert_cert_name number-text-01" name="resCertName" readonly="readonly"  maxlength="20" value="'+ element.certVOs.certName +'" /></td>';
					html += 	'<td><input type="text" class="datepicker form-control insert_cert_date number-text-01" name="resCertDate" readonly="readonly"  maxlength="20" value="' + element.certVOs.certDate + '" /></td>';
					html += 	'<td><input type="text" class="form-control insert_cert_place number-text-01" name="resCertAgency" readonly="readonly"  maxlength="20" value="' + element.certVOs.certAgency + '" /></td>';
					html += '</tr>';
					$('.ipt_certificate').append(html);
				});
			} else {
				html = '';
				html += '<tr class="career_cert">';
				html += 	'<td><input type="text" class="form-control insert_cert_name number-text-01" name="resCertName" readonly="readonly"  maxlength="20" value="" /></td>';
				html += 	'<td><input type="text" class="datepicker form-control insert_cert_date number-text-01" name="resCertDate" readonly="readonly"  maxlength="20" value="" /></td>';
				html += 	'<td><input type="text" class="form-control insert_cert_place number-text-01" name="resCertAgency" readonly="readonly"  maxlength="20" value="" /></td>';
				html += '</tr>';
				$('.ipt_certificate').append(html);
			}
			if(data.projVOs != undefined) {
				$.each(data.projVOs, function(index, element) {
					html = '';
					html += '<table class="table table-bordered table-condensed number-text-01" style="display: inline-table;">';
					html += '<colgroup>';
					html += '	<col width="30%" />';
					html += '	<col width="25%" />';
					html += '	<col width="20%" />';
					html += '	<col width="20%" />';
					html += '</colgroup>';
					html += '<thead>';
					html += '	<tr>';
					html += '		<th rowspan="2" class="text-center career_category">프로젝트명</th>';
					html += '		<th rowspan="2" class="text-center career_category">참여 기간</th>';
					html += '		<th rowspan="2" class="text-center career_category">회사명</th>';
					html += '		<th rowspan="2" class="text-center career_category">수행업무</th>';
					html += '	</tr>';
					html += '</thead>';
					html += '<tbody class="ipt_proj">';
					html += '	<tr>';
					html += '		<td><input type="text" class="form-control insert_career_proj_name number-text-01" readonly="readonly" maxlength="50" value="' + element.projVOs.projName + '" /></td>';
					html += '		<td style="display: flex;">';
					html += '			<input type="text" style="width: 50%" class="form-control datepicker insert_career_proj_stDate number-text-01" readonly="readonly"  maxlength="10" value="' + element.projVOs.userInDate + '" />';
					html += '			&nbsp;&nbsp;~&nbsp;&nbsp;';
					html += '			<input type="text" style="width: 50%" class="form-control datepicker insert_career_proj_fiDate number-text-01" readonly="readonly"  maxlength="10" value="' + element.projVOs.userOutDate + '" />';
					html += '		</td>';
					html += '		<td><input type="text" class="form-control insert_career_proj_comp number-text-01" readonly="readonly" maxlength="20" value="' + element.projVOs.userComp + '" /></td>';
					html += '		<td><input type="text" class="form-control insert_career_proj_job number-text-01" readonly="readonly" maxlength="20" value="' + element.projVOs.userJob + '" /></td>';
					html += '	</tr>';
					html += '</tbody>';
					html += '</table>';
					html += '<table class="table table-bordered table-condensed number-text-01" style="display: inline-table;">';
					html += '<colgroup>';
					html += '	<col width="8%" />';
					html += '	<col width="8%" />';
					html += '	<col width="8%" />';
					html += '	<col width="8%" />';
					html += '	<col width="8%" />';
					html += '</colgroup>';
					html += '<thead>';
					html += '	<tr>';
					html += '		<th colspan="6" class="text-center career_category">개발 환경</th>';
					html += '	</tr>';
					html += '	<tr>';
					html += '		<th class="text-center career_category">OS</th>';
					html += '		<th class="text-center career_category">언어</th>';
					html += '		<th class="text-center career_category">DBMS</th>';
					html += '		<th class="text-center career_category">TOOL</th>';
					html += '		<th class="text-center career_category">통신</th>';
					html += '	</tr>';
					html += '</thead>';
					html += '<tbody class="ipt_developer" id="tbodySkill">';
					html += '	<tr>';
					html += '		<td><input type="text" class="form-control insert_developer_os number-text-01" maxlength="15" name="detProjOs" readonly="readonly" value="' + element.projVOs.detProjOs + '" /></td>';
					html += '		<td><input type="text" class="form-control insert_developer_lang number-text-01" maxlength="15" name="detProjLang" readonly="readonly" value="' + element.projVOs.detProjLang + '" /></td>';
					html += '		<td><input type="text" class="form-control insert_developer_dbms number-text-01" maxlength="15" name="detProjDb" readonly="readonly" value="' + element.projVOs.detProjDb + '" /></td>';
					html += '		<td><input type="text" class="form-control insert_developer_tool number-text-01" maxlength="15" name="detProjTool" readonly="readonly" value="' + element.projVOs.detProjTool + '" /></td>';
					html += '		<td><input type="text" class="form-control insert_developer_commu number-text-01" maxlength="15" name="detProjNet" readonly="readonly" value="' + element.projVOs.detProjNet + '" /></td>';
					html += '	</tr>';
					html += '	<tr>';
					html += '		<td colspan="1" class="text-center career_category">기타</th>';
					html += '		<td colspan="7"><input type="text" class="form-control insert_developer_other number-text-01" maxlength="80" name="detProjOther" readonly="readonly" value="' + element.projVOs.detProjOther + '" /></td>';
					html += '	</tr>';
					html += '</tbody>';
					html += '</table>';
					$('.ipt_career_proj').append(html);
				});
			} else { 
				html = '';
				html += '<table class="table table-bordered table-condensed number-text-01" style="display: inline-table;">';
				html += '<colgroup>';
				html += '	<col width="30%" />';
				html += '	<col width="25%" />';
				html += '	<col width="20%" />';
				html += '	<col width="20%" />';
				html += '</colgroup>';
				html += '<thead>';
				html += '	<tr>';
				html += '		<th rowspan="2" class="text-center career_category">프로젝트명</th>';
				html += '		<th rowspan="2" class="text-center career_category">참여 기간</th>';
				html += '		<th rowspan="2" class="text-center career_category">회사명</th>';
				html += '		<th rowspan="2" class="text-center career_category">수행업무</th>';
				html += '	</tr>';
				html += '</thead>';
				html += '<tbody class="ipt_proj">';
				html += '	<tr>';
				html += '		<td><input type="text" class="form-control insert_career_proj_name number-text-01" readonly="readonly" maxlength="50" value="" /></td>';
				html += '		<td style="display: flex;">';
				html += '			<input type="text" style="width: 50%" class="form-control datepicker insert_career_proj_stDate number-text-01" readonly="readonly"  maxlength="10" value="" />';
				html += '			&nbsp;&nbsp;~&nbsp;&nbsp;';
				html += '			<input type="text" style="width: 50%" class="form-control datepicker insert_career_proj_fiDate number-text-01" readonly="readonly"  maxlength="10" value="" />';
				html += '		</td>';
				html += '		<td><input type="text" class="form-control insert_career_proj_comp number-text-01" readonly="readonly" maxlength="20" value="" /></td>';
				html += '		<td><input type="text" class="form-control insert_career_proj_job number-text-01" readonly="readonly" maxlength="20" value="" /></td>';
				html += '	</tr>';
				html += '</tbody>';
				html += '</table>';
				html += '<table class="table table-bordered table-condensed number-text-01" style="display: inline-table;">';
				html += '<colgroup>';
				html += '	<col width="8%" />';
				html += '	<col width="8%" />';
				html += '	<col width="8%" />';
				html += '	<col width="8%" />';
				html += '	<col width="8%" />';
				html += '</colgroup>';
				html += '<thead>';
				html += '	<tr>';
				html += '		<th colspan="6" class="text-center career_category">개발 환경</th>';
				html += '	</tr>';
				html += '	<tr>';
				html += '		<th class="text-center career_category">OS</th>';
				html += '		<th class="text-center career_category">언어</th>';
				html += '		<th class="text-center career_category">DBMS</th>';
				html += '		<th class="text-center career_category">TOOL</th>';
				html += '		<th class="text-center career_category">통신</th>';
				html += '	</tr>';
				html += '</thead>';
				html += '<tbody class="ipt_developer" id="tbodySkill">';
				html += '	<tr>';
				html += '		<td><input type="text" class="form-control insert_developer_os number-text-01" maxlength="15" name="detProjOs" readonly="readonly" value="" /></td>';
				html += '		<td><input type="text" class="form-control insert_developer_lang number-text-01" maxlength="15" name="detProjLang" readonly="readonly" value="" /></td>';
				html += '		<td><input type="text" class="form-control insert_developer_dbms number-text-01" maxlength="15" name="detProjDb" readonly="readonly" value="" /></td>';
				html += '		<td><input type="text" class="form-control insert_developer_tool number-text-01" maxlength="15" name="detProjTool" readonly="readonly" value="" /></td>';
				html += '		<td><input type="text" class="form-control insert_developer_commu number-text-01" maxlength="15" name="detProjNet" readonly="readonly" value="" /></td>';
				html += '	</tr>';
				html += '	<tr>';
				html += '		<td colspan="1" class="text-center career_category">기타</th>';
				html += '		<td colspan="7"><input type="text" class="form-control insert_developer_other number-text-01" maxlength="80" name="detProjOther" readonly="readonly" value="" /></td>';
				html += '	</tr>';
				html += '</tbody>';
				html += '</table>';
				$('.ipt_career_proj').append(html);
			}
		}, error : function(error) {
		}
	})
});

/**
 * 날짜 자동 콤마 생성
 * @param obj
 * @returns
 */
function inputDateNumber(obj, alertText) {
	 var number = obj.value.replace(/[^0-9]/g, '');
	    var phone = "";
	    if (number.length < 5) {
	        return number
	    } else if (number.length < 9) {
	    	phone += number.substr(0, 4);
	        phone += ".";
	        phone += number.substr(4, 2);
	        phone += ".";
	        phone += number.substr(6, 2);
	    }
	    obj.value = phone
}

/**
 * 주민등록번호 유효성 검사
 * @returns
 */  
function SSNFocus() {
	var SSN1 = $('#insert_career_SSN1').val();
	var SSN2 = $('#insert_career_SSN2');
	if(SSN1.length > 5) {
		SSN2.focus();
	}
}
/**
 * 팝업창 창닫기
 * @returns
 */
function cancelModal() {
	$('#career_update_popUp').modal('hide');
	var userId = $('#projCodeInfo').val();
	location.href = '/main/proj/detail?projCode=' + userId;
}
