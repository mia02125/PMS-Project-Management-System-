/**
 * ============================================================= 공통선언
 */
var comapp = {};
comapp.docRoot = '';

comapp.date = {};
comapp.date.fullDate; 	// YYYYMMDD HH24:MI:SS
comapp.date.toDay; 		// YYYYMMDD
comapp.date.toMonth; 	// MM
comapp.date.toYear; 	// YYYY
comapp.date.toTime; 	// HH24
comapp.date.toMinute; 	// MI

/**
 * 
 * ======================
 * 종류별 선언
 */
comapp.util = {};
comapp.tag 	= {};


comapp.util.setTagValue1 = function(target, param, arrval){
	
	var obj	= {};
	
	var arr1 	= param.split('&');
	arr1.forEach(function(val) {
		var arr2 = val.split('=');
		for(var i in arrval) {
			if(arrval[i]==arr2[0]){
				obj[arr2[0]] = arr2[1];
			}
		}
	});	
    //console.log('obj-->', obj); 
	
	var tmp = '';
	for (var prop in obj) {
		tmp = '[name=' + prop + ']';
	    if( $(target).find(tmp).length == 0 ){
	    	$(target).append($('<input/>', {type:'hidden', name:prop, value:obj[prop]}));
	    } else {
	    	$(target).find(tmp).val(obj[prop]);
	    }
	}
}




/**
 * checkbox value 자동으로 값 넣기
 * 
 * @param target : tag
 * @param valueType : 1 => 'Y' , 'N' / 2=> true , false
 * 
 */
comapp.tag.checkboxAutoValue = function(target, valueType){
	
	var chkval;
	target.each(function(){
		var checkValue = function(valueType){
			if(!valueType){
				chkval = $(this).is(":checked") ? 'Y' : 'N';
			} else if(valueType=='1') {
				chkval = $(this).is(":checked") ? 'Y' : 'N';
			} else {
				chkval = $(this).is(":checked") ? true : false;
			}
			return chkval;
		}
		$(this).val(checkValue);
		
		$(this).change(function(){
			$(this).val(checkValue);
		});
	});
}



/**
 * ======================
 * 전송 유틸
 */

/**
 * submit
 * 
 * @param target : jquery select target
 * @param url		
 * @param isload : is loading (default:true)
 */
comapp.submit = function(obj) {

	var _target 	= obj.target;
	var _url 		= obj.url;
	var _method 	= obj.method;
	var _isload 	= obj.isload;
	
	comapp.showLoading(true);
//	if(_isload){
//		comapp.showLoading(true);
//	} else {
//		comapp.showLoading(false);
//	}
	if(_url){
		$(_target).attr('action', _url);
	}
	if(_method){
		$(_target).attr('method', _method);
	}
	$(_target).submit();
};



comapp.showLoading = function(bol){
	if(bol){
		$('.cu-div-loading').show();
	} else {
		$('.cu-div-loading').hide();
	}
}


comapp.eventListener = function(){
	
	$('input[type="text"]').keydown(function(e){
		if(e.keyCode === 13){
			e.preventDefault();
		}
	});
}

/**
 * ============================================================= 
 * document ready
 */
$(document).ready(function() {
	
	comapp.showLoading(false);

	comapp.eventListener();

	console.log('[comapp.js] ready.......');

});
