/**
 * 검색창 JS 
 */
function autoComplete() {
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
function undefinedToStr(str, defaultStr){
    if(typeof str == 'undefined' || str == null || str == '')
        str = defaultStr;    
    return str;
}
/**
 * 클릭 시 자동완성 
 * @returns
 */
$(document).on('click','.search_txt',function() {
	autoComplete();
});
/**
 * 검색 엔터
 * @returns
 */ 
function enterKey() {
	if(window.event.keyCode == 13) {
		$('#btn_find_search').click();
	}
}
/**
 * 검색 클릭 시 데이터 더
 * @returns
 */
$('#btn_find_search').on('click', function() {
	console.log('버튼 클릭');
});
/**
 * 데이터 import
 * @returns
 */
$('#btn_update_search').on('click', function() {
	var _csrf = $('#csrfVal').attr('name');
	var csrfVal = $('#csrfVal').val();
	$.ajax({
		url : '/main/search/dataImport',
		type : 'POST',
		data : {_csrf : csrfVal},
		dataType : 'JSON',
		success : function(data) {
			console.log(data);
		}
	})
});

