$(function() {
	$(".text2input").dblclick( function () { text2input(this);});
	$(".text2input").mouseover(function(){
	  $(this).css("background-color","#F4F9F5");
	});
	$(".text2input").mouseout(function(){
	  $(this).css("background-color","#ffffff");
	});
});

function text2input(obj){
	var value = $(obj).html()=="&nbsp;"?"":$(obj).html();
	var inputstr = "<input name=\"input2text\" id=\"input2text\" type=\"text\"  style=\"width: 100%;\"/>";
	$(obj).html(inputstr);
	$("#input2text").val(value);
	$("#input2text").focus();
	$("#input2text").blur( function () { input2text($("#input2text")); } );
}

function input2text(obj){
	var value = $(obj).val()==""?"&nbsp;":$(obj).val();
	$(obj).parent(".text2input").html(value);
}