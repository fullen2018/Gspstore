function dateFormatYMD(date){
	if(date!=null&&date.length>0){
		date = date.substring(0,10);
	}
	return date;
}

//该函数格式化金额(赵光培增加)
function numberFormat(str, step, splitor) {  
	 str = str.toString();  
	 var len = str.length;  
	if(len > step) {
	var l1 = len%step,   
	l2 = parseInt(len/step),  
	arr = [],  
	first = str.substr(0, l1);  
	if(first != '') {  
	 arr.push(first);  
	};  
	for(var i=0; i<l2 ; i++) {  
	 arr.push(str.substr(l1 + i*step, step));                                      
	};  
	str = arr.join(splitor);  
	};  
	return str;  
}  

//JS Object to String 
//不通用,因为拼成的JSON串格式不对
function obj2str(o){
   var r = [];
   if(typeof o == "string" || o == null) {
     return "@"+o+"@";
   }
   if(typeof o == "object"){
     if(!o.sort){
       r[0]="{"
       for(var i in o){
         r[r.length]="@"+i+"@";
         r[r.length]=":";
         r[r.length]=obj2str(o[i]);
         r[r.length]=",";
       }
       r[r.length-1]="}"
     }else{
       r[0]="["
       for(var i =0;i<o.length;i++){
         r[r.length]=obj2str(o[i]);
         r[r.length]=",";
       }
       r[r.length-1]="]"
     }
     return r.join("");
   }
   return o.toString();
}

//JS String  to Object
function strToObj(json){ 
         return eval("("+json+")"); 
}

/*********************************************
列表点击不选择
*********************************************/
function unSelected(id,rowIndex,rowData){
	var selected = $('#'+id).datagrid('getSelections');
	// 获取所有选中的行数组，如果里边有这个，不选中
	if(jQuery.inArray(rowData,selected)!=-1){
		$('#'+id).datagrid('unselectRow',rowIndex);
	} else {
		$('#'+id).datagrid('selectRow',rowIndex);
	}
	// 如果没有 则选中
}

function settilte(obj){
	obj.title = $(obj).text();
}

//消息提示
	function messageshow(head,msg){
		$.messager.show({
						title : head,
						msg : msg
		});
	}

//关闭弹出iframe	
function closePayoutDialog(){
		iframeDialog.dialog('close');
	}
	
	/**
 * 根据长度截取先使用字符串，超长部分追加...
 * @param str 对象字符串
 * @param len 目标字节长度
 * @return 处理结果字符串
 */
function cutString(str, len) {
	//length属性读出来的汉字长度为1
	if(str.length*2 <= len) {
		return str;
	}
	var strlen = 0;
	var s = "";
	for(var i = 0;i < str.length; i++) {
		s = s + str.charAt(i);
		if (str.charCodeAt(i) > 128) {
			strlen = strlen + 2;
			if(strlen >= len){
				return s.substring(0,s.length-1) + "...";
			}
		} else {
			strlen = strlen + 1;
			if(strlen >= len){
				return s.substring(0,s.length-2) + "...";
			}
		}
	}
	return s;
}

/**
	*字典表取数逻辑
	*paramLevel:类型
	*name:字段NAME
	*id:字段id
	*callback：回调函数名字
	*/
	function dictCombox(paramLevel,name,callback,id){
	  if(id!=null){
	  	$("#"+id+"").combobox({
				url : 'dictParamAction!dictCombobox.action?paramLevel='+paramLevel,
				valueField:'paramValue',
				textField:'paramName',
				mode : 'remote',
				panelHeight : 'auto',
				delay : 500,
				onSelect:function(record){
				        if(callback!=null){
				        	callback.apply(this, record)
				        	}
          	 		 	}
			});
	  }
	 if(name!=null){
	 	$("input[name='"+name+"']").combobox({
				url : 'dictParamAction!dictCombobox.action?paramLevel='+paramLevel,
				valueField:'paramValue',
				textField:'paramName',
				mode : 'remote',
				panelHeight : 'auto',
				delay : 500,
				onSelect:function(record){
						    if(callback!=null){
					        	callback.apply(this, [record])
				        	}
          	 		 	}
			});
	 }
	}
	
	
	/**
	* 获取字典参数名字
	* paramLevel：参数类型
	* paramLevel：参数值
	*/
	function dictGetName(paramLevel,paramValue){
	    var name = '';
		$.ajax({
			url : 'dictParamAction!dictGetComboboxName.action',
			data : {
				paramLevel : paramLevel,
				paramValue : paramValue
			},
			dataType : 'json',
			cache : false,
			async:false,
			success : function(response) {
				if(response){
					name =  response.paramName;
				}
			}
		});
		return name;
	}
	
	//通过key从json数组中获取对应的名字值
	function normalGetName(jsonArray,keyName,keyValue,nameName){
	   var retName = "";
	   if (jsonArray!=null&&jsonArray.length>0){
	       for (var obj in jsonArray){
	           if (jsonArray[obj][keyName]!=null&&jsonArray[obj][keyName]==keyValue){
	               retName = jsonArray[obj][nameName];
	               break;
	           }
	       }
	   }
	   return retName;
	}
	//通过key从json数组中获取对应的名字值
	function checkComboboxSelected(jsonArray,keyName,keyValue){
	   var retName = false;
	   if (jsonArray!=null&&jsonArray.length>0){
	       for (var obj in jsonArray){
	           if (jsonArray[obj][keyName]!=null&&jsonArray[obj][keyName]==keyValue){
	               retName = true;
	               break;
	           }
	       }
	   }
	   return retName;
	}
	
	String.prototype.replaceAll  = function(s1,s2){    
		  return this.replace(new RegExp(s1,"gm"),s2);    
	}