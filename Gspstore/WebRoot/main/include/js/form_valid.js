function focusField(fieldobj)
{
	if(fieldobj==null)
	{
		return;
	}

		setFieldVisible(fieldobj);
		fieldobj.focus();
}
function setFieldVisible(obj)
{
	if(obj == null) return;
	var tab = obj.parentElement;
	while((tab.tagName != "DIV" || tab.id.substr(0,3)!="Tag") && tab.tagName != "BODY")
		tab = tab.parentElement;
	if(tab.tagName == "BODY") return;
	
	var maxtab=0;
	for(var i=1;i<10;i++)
	{
		var objtd = document.getElementById("td" + i);
		if(objtd!=null) 
			maxtab=i;
		else
			break;
	}
	if(maxtab>1)
	{
		ShowTag(tab.id.substr(3),maxtab);
	}
}
function trim(str){
	var reg_exp = /(^\s*)|(\s*$)/g;
	var s = str.replace(reg_exp,'');
	return s;
}

function check(f)
{
	for (elementIndex = 0;elementIndex < f.elements.length;elementIndex++)
	{
	           //alert(f.elements.length);
		try
		{
			if(!checkElement(f.elements[elementIndex]))
			{
				$(this).focus();
				return false;
			}
		}
		catch(e){
		    //alert("catch check(f)"+e);
			return false;
		}

	}
	return true;
}
function checkElement(field)
{
//alert(field.alt);

    var title = field.title;
    var value = field.value;
    value = trim(value);
    var alt = field.alt;
    var val_len = value.length;
    if(alt == null || title == null) return true;
    if(alt == '' || title == '') return true;

   if(alt.indexOf("notnull") >= 0 && trim(value) ==""&& !field.disabled)
    {
       alert(title+"不能为空!");
       focusField(field);
       return false;
   }
   
   if(alt.indexOf("combobox") >= 0 && trim(value) ==""&& !field.disabled)
    {
       var name = field.getAttribute("comboname");
       var combobox = document.getElementsByName(name);
       var com = combobox[0];
       var com_value = com.value;
       if(trim(com_value) ==""){
        alert(title+"不能为空!");
       	focusField(field);
      	 return false;
       }
   }
   
      	if(alt.indexOf("size12") >= 0 && trim(value)!="")
    {
       if(value.length>12){
     	  alert(title+"长度不能超过12个字符!");
     	  focusField(field);
     	  return false;
       }
   }
   
   	if(alt.indexOf("size24") >= 0 && trim(value)!="")
    {
       if(value.length>12){
     	  alert(title+"长度不能超过24个字符!");
     	  focusField(field);
     	  return false;
       }
   }
   
   	if(alt.indexOf("size255") >= 0 && trim(value)!="")
    {
       if(value.length>12){
     	  alert(title+"长度不能超过255个字符!");
     	  focusField(field);
     	  return false;
       }
   }
   
   
  /*此数据项填写的内容必须为数字*/
    if( alt.indexOf("number") >= 0 )
   {
    var objExp=/^\d+$/;
	var objMatch=value.match(objExp);
	if (objMatch == null)
	   {
		alert(title+"必须是整数");
		 focusField(field);
		return false;
	  }
	}  
 // end
    
  /*此数据项填写的内容必须为邮箱*/
    if(alt.indexOf("mail") >= 0)
   {
    var objExp=/^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;

	var objMatch=value.match(objExp);
	if (objMatch == null)
	   {
		alert(title+"应该是一个邮箱");
		 focusField(field);
		return false;
	  }
	}  
 // end
 
 /*此数据项填写的内容只能为数字和英文字母*/
    if(alt.indexOf("charnum") >= 0)
   {
    var objExp=/^[a-zA-Z0-9]*$/g;

	var objMatch=value.match(objExp);
	if (objMatch == null)
	   {
		alert(title+"只能是数字和字母");
		 focusField(field);
		return false;
	  }
	}  
 // end
 
  /*此数据项填写的内容只能为英文字母*/
    if(alt.indexOf("charonly") >= 0)
   {
    var objExp=/^([A-Za-z])*$/;

	var objMatch=value.match(objExp);
	if (objMatch == null)
	   {
		alert(title+"只能是字母");
		 focusField(field);
		return false;
	  }
	}  
 // end
 
 /*此数据项填写的内容只能手机号*/
    if(alt.indexOf("mobilphone") >= 0)
   {
    var objExp=/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/;

	var objMatch=value.match(objExp);
	if (objMatch == null)
	   {
		alert(title+"只能为正确手机号码");
		 focusField(field);
		return false;
	  }
	}  
 // end
 
   /*此数据项填写的内容只能为日期*/
   if(alt.indexOf("dateonly") >= 0)
   {
   	var iaMonthDays = [31,28,31,30,31,30,31,31,30,31,30,31];
	var year, month, day;

	var a = value.substring(0,4);
	var b = value.substring(4,6);
	var c = value.substring(6,8);

	year = parseFloat(a);
	month = parseFloat(b);
	day=parseFloat(c);

	if (year < 1000 || year > 4000) return false;
	if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) iaMonthDays[1]=29;
	if (month < 1 || month > 12) return false;
	if (day < 1 || day > iaMonthDays[month - 1]) return false;

    }

	if( alt.indexOf("float") >= 0 )
	{
		if( value.search("^-?\\d+(\\.\\d+)?$")== -1 ){
			alert(title + "只能输入数字!");
			focusField(field);
			return false;
		}
	}

	if( alt.indexOf("percent") >= 0 )
	{
		if( value.search("^\\%\\d+$")== -1 ){
			alert(title + "只能输入整数和百分号(%)!");
			focusField(field);
			return false;
		}
	}
	return true;
	
}
