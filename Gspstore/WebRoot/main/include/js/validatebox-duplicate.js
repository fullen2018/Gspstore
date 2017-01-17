$.extend($.fn.validatebox.defaults.rules, {
    duplicate_hotelname: {
        validator: function (value, param) {
            return duplicate_hotelname(value,param);
        },
        message: '酒店中文全称,在系统中已经存在!'
    }
});


//[酒店管理]酒店中文全称
var duplicate_hotelname = function(value,param){
	var returnflag = ajax_check('HotelEntity','hotelname',value,param);
	return returnflag;
}


//共通方法
var ajax_check = function(tableName,fieldName,fieldVlaue,param){
	//获取编辑页面的数据主键
	var obid = null;
	if(param!=null){
		 var obid_id = param[0];
		 obid = $("#"+obid_id+"").val();
	}else{
		 obid = $("input[name='obid']").val();
	}
	
	$.ajaxSetup({ async: false});//同步ajax 
	var check_flag;
	$.ajax({
		url : 'duplicateCheckAction!doDuplicateCheck.action',
		async:false,
		data : {
			tableName : tableName,
			fieldName : fieldName,
			fieldVlaue: fieldVlaue,
			rowObid   : obid
		},
		dataType : 'json',
		success : function(response) {
			check_flag = response.success;
		}
	});
	$.ajaxSetup({ async: false});//同步ajax 
	return check_flag;
}