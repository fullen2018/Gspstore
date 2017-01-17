$(function() {
	initSelAjax();	
});



function initSelAjax(){
//ajax加载,收款方下拉菜单	 
   $("#supplierType").change(function(){
			var supplierType = $(this).val();
			$("#supplierName").val("");
			$("#bank_add").html("");
			$("#accountName_add").val("");
			$("#account_add").val("");
			$("#tourist").val("");
			$("#tourist_tr").css("display","none");
			if(supplierType=="02"){
				$("#tourist_tr").css("display","");
				initTourist();
				return;
			}else if(supplierType=="06"){
				$("#supplierId_add").html("<option value=''>--此项不用录入--</option>"); 
		    	$("#bank_span").html("<input name=\"bank\" type=\"text\" style=\"width: 155px;\"/>");
		    	$("#accountName_add").attr("readonly","");
		    	$("#account_add").attr("readonly","");
		    	$("#accountName_add").removeClass("inputread");
		    	$("#account_add").removeClass("inputread");
		    }else{
		    	$("#supplierId_add").html(""); 
		    	$("#bank_span").html("<select name=\"bank\" id=\"bank_add\" style=\"width: 155px;\" onchange=\"changeBank(this);\"></select>");
		    	$("#accountName_add").attr("readonly","readonly");
		    	$("#account_add").attr("readonly","readonly");
		    	$("#accountName_add").addClass("inputread");
		    	$("#account_add").addClass("inputread");
		    }
			if(supplierType==""){
			    return;
		    }
			$.ajax({
				url : 'supplieraccountAction!doSelectSupp.action?supplierType='+supplierType,
				dataType : 'text',
				cache:false,
				success : function(data) {
				    if(data=="[]"){
					    return;
				    }
					var myJSON = eval("("+data+")");
					var myOptions = '';
					myOptions += "<option value=''>请选择</option>";
					for (var i = 0; i < myJSON.length; i++) { 
					   var name = myJSON[i].suppliername;
					   if(name==null){name = ''}
		                myOptions += '<option value="' + myJSON[i].obid + '">' + name + '</option>'; 
		                }
		            $("#supplierId_add").html(myOptions); 
				}
			});
   });		 
		 
	//ajax加载,银行
	$("#supplierId_add").change(function(){
		var supplierId = $(this).val();
		$("#supplierName").val("");
		$("#bank_add").html("");
		$("#accountName_add").val("");
		$("#account_add").val("");
		if(supplierId==""){
		    return;
	    }
		$("#supplierName").val(getSelectedText());
		$.ajax({
			url : 'supplieraccountAction!doListAllBySupplierId.action?supplierId='+supplierId,
			dataType : 'text',
			cache:false,
			success : function(data) {
				if(data=="[]"){
				    return;
			    }
				var myJSON = eval("("+data+")");
				var myOptions = '';
				myOptions += "<option value=''>请选择</option>";
				for (var i = 0; i < myJSON.length; i++) { 
				   var name = myJSON[i].bankname;
				   if(name==null){name = ''}
	                myOptions += '<option value="' + myJSON[i].obid + '">' + name + '</option>'; 
	                }
	            $("#bank_add").html(myOptions); 
			}
		});
	});
	
}


function initTourist(){
	$.ajax({
		url : 'touristAction!doListTourist.action',
		dataType : 'text',
		cache:false,
		success : function(data) {
			if(data=="[]"){
			    return;
		    }
			var myJSON = eval("("+data+")");
			var myOptions = '';
			myOptions += "<option value=''>请选择</option>";
			for (var i = 0; i < myJSON.length; i++) { 
			   var name = myJSON[i].touristName;
			   if(name==null){name = ''}
                myOptions += '<option value="' + myJSON[i].obid + '">' + name + '</option>'; 
                }
            $("#tourist").html(myOptions); 
		}
	});
	
}

function changeTourist(obj){
	var obid = $(obj).val();
	$("#supplierId_add").html("");
	$("#supplierName").val("");
	$("#bank_add").html("");
	$("#accountName_add").val("");
	$("#account_add").val("");
	if(obid==""){
	    return;
    }
	$.ajax({
		url : 'supplieraccountAction!doSelectAgency.action?touristId='+obid,
		dataType : 'text',
		cache:false,
		success : function(data) {
				if(data=="[]"){
				    return;
			    }
				var myJSON = eval("("+data+")");
				var myOptions = '';
				myOptions += "<option value=''>请选择</option>";
				for (var i = 0; i < myJSON.length; i++) { 
				   var name = myJSON[i].suppliername;
				   if(name==null){name = ''}
		            myOptions += '<option value="' + myJSON[i].obid + '">' + name + '</option>'; 
		            }
		        $("#supplierId_add").html(myOptions); 
		}
	});
}

//ajax加载,获取银行账号
function changeBank(obj){
	//ajax加载,获取银行账号
		var obid = $(obj).val();
		$("#accountName_add").val("");
		$("#account_add").val("");
		if(obid==""){
		    return;
	    }
		$.ajax({
			url : 'supplieraccountAction!doListOneBySupplierId.action?obid='+obid,
			dataType : 'text',
			cache:false,
			success : function(data) {
				if(data=="[]"){
				    return;
			    }
				var res = eval("("+data+")");
				$("#accountName_add").val(res.accountName);
				$("#account_add").val(res.account);
			}
		});
}


function getSelectedText(){
	var obj=document.getElementById('supplierId_add');
	for(i=0;i<obj.length;i++){
	   if(obj[i].selected==true){
	    return obj[i].text;      //关键是通过option对象的innerText属性获取到选项文本
	   }
	}
}