# PMS 
- 기존의 구축된 프레임워크를 분석 및 apache-tile, 로그 정책, application.yml, security 등 기존의 최소의 설정은 유지하여
  프로젝트 투입인력 관리시스템 구현 



## 목차 
#### 1. 업무 프로세스
#### 2. 시스템 프로세스  
#### 3. 개발순서 
#### 4. 개발환경
#### 5. DB ERD 
#### 6. 주요 기능 : Solr 검색엔진
#### 6. 구현이 어려웠던 부분


<br><br>

## 1. 업무 프로세스
![image](https://user-images.githubusercontent.com/44182639/116884569-d62cd680-ac61-11eb-8cde-7fc047ec53de.png)


## 2. 시스템 프로세스
![image](https://user-images.githubusercontent.com/44182639/116887302-20638700-ac65-11eb-93a2-fbddc71d39fb.png)


## 3. 개발 순서 
	1. 프로젝트 관리 시스템 조사 
	2. 개발 환경 설계
	3. 리눅스 환경 구축
	4. MariaDB 구축
	5. DB ERD 설계
	6. 화면 설계서 작성 
	7. 기능명세서 작성 
	8. 기존 프레임 워크 분석 ( 기초 세팅 ) 및 코드 분석 
	9. 기능 구현 * 기능 명세서 참고
	    - Solr 검색엔진 구현
	10. 브로셔 제작


## 4. 개발 환경
	- Spring Boot 2.3.2.RELEASE
	- Spring Security
	- Solr 7.7.0
	- Mybatis
	- MariaDB
	- OpenJDK 1.8.0.252

## 5. DB ERD
![image](https://user-images.githubusercontent.com/44182639/116889628-be585100-ac67-11eb-802e-81a07dc3b281.png)
<br>
<br>
<br>

## 6. 주요기능
[Solr 서버 구축 바로가기](https://www.notion.so/Apache-solr-7cc96270847e44c3ade1e83a6f96edbc)
![image](https://user-images.githubusercontent.com/44182639/117562882-2af6a400-b0dd-11eb-97a2-8aaf7e01201e.png)

 
<br>
<br>
<br>

## 7. 구현이 어려웠던 부분 

### 1. 이력서 보기
- 문제점 : 이력서 보기 시 자격증, 근무경력 데이터가 중복되어 입력
- 해결방법 : 팝업창 종료 시 해당 페이지 리다이엑트
1. 이력서 보기 
![image](https://user-images.githubusercontent.com/44182639/116882742-c14f4380-ac5f-11eb-9040-dfe89f15ff6c.png)

```javascript
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
```
### 2. 프로젝트 유효성 검사 
 - 문제점 : 특수문자, 띄어쓰기 등 데이터 입력 오류 빈번하게 발생
 - 해결방법 : 정규식을 직접 구현 및  유효성 검사 처리
```javascript
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

```

### 3. 해당 검색결과 클릭 시 해당 상세 정보 페이지로 이동 
- 해결방법 : ajax에서 제공하는 select를 사용하여 해당 검색결과 클릭 시 데이터를 이용해 프로젝트 코드가 포함되어있다면 프로젝트 상세정보
            사용자 코드가 포함되어있다면 사용자 상세정보로 이동 
```

	var _csrf = $('#csrfVal').attr('name');
	var csrfVal = $('#csrfVal').val();
	$('.search_txt').autocomplete({      // 자동완성기능 	
		source : function(request, response) { // 14열의 response 로직을 리턴한다.
			var _inputTxt = request.term || '';
			$.ajax({
				url : '/main/search/autocomplete',
				type : 'POST',
				data : Object.assign({_csrf : csrfVal, query : _inputTxt}),
				dataType : 'JSON', // jsonView 일 경우 굳이 명시하진않아도된다.
				success : function(data) {
					console.log(data);
					response(
						$.map(data.result, function(item) {
							return{
								label : item['id'],
								value : (item['c4i_NAME'] || ''),
								value1 : (item['c4i_CHAR1']  || ''),
								value2 : (item['c4i_CHAR2']  || ''),
								value3 : (item['c4i_CHAR3']  || '')
							}
						})
					)
				}
			});
		},
		focus : function() {
			return false; // 입력값 유지 
		},
		autoFocus:true, // 첫번째 ROW을 자동 focus로 둔다.
		select : function(event, ui) {
			console.log(ui.item.label);
			$.ajax({
				url : '/main/search/mvWebPage',
				type : 'POST',
				data : Object.assign({_csrf : csrfVal, code : ui.item.label}), 
				dataType : 'JSON',
				success : function(data) {
					console.log(data);
					if(data.jsonObj != '' && data.jsonObj != null) {
						var projCode = data.jsonObj['projCode'];
						location.href = '/main/proj/detail?projCode=' + projCode;
					} else {
						var userCode = data.code;
						location.href = '/main/user/detail?projCode=' + userCode;
					}
				}, error : function(error) {
					console.log(error);
				}
			})
		}
		// autocomplete('instance') : 자동 완성의 인스턴스 개체를 검사 
	}).autocomplete('instance')._renderItem = function(ul,item) { // 커스터마이징 
		return $('<li></li>')
		.append('<div style="font-size: 16px;">' +  item.value + '&nbsp;&nbsp;&nbsp;&nbsp;'+ '[' + '&nbsp;&nbsp;&nbsp;&nbsp;' + item.value1 +'&nbsp;&nbsp;&nbsp;&nbsp;' + item.value2+ '&nbsp;&nbsp;&nbsp;&nbsp;' + item.value3  + '&nbsp;&nbsp;&nbsp;&nbsp;' + ']' + '</div>')
		.appendTo(ul);
	}
}
```

### 4. 데이터 INSERT 
- 문제점 : POST방식으로 파라미터값을 서버에 넘길 때 오류 
- 해결방법 : Object.assign() 활용하여 csrf값과 data값을 서버에 넘김 
```
var dataList = {
        userCode : $(this).attr('id')
    }
    $.ajax({
    	url : '/main/proj/bringInputData',
		type : 'POST',
		data : Object.assign(dataList, { '_csrf' : csrfValue }),
		dataType : 'JSON',
		success : function (data) {
		  ... 
		}
	});
```
