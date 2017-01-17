<%@ page language="java" pageEncoding="UTF-8"%>
<script type="text/javascript" charset="utf-8">
	var loginAndRegDialog;
	var loginTabs;
	var loginInputForm;
	var loginDatagridForm;
	var loginComboboxForm;
	var loginDatagridName;
	var loginComboboxName;

	var regDialog;
	var regForm;

	$(function() {
		if($("#top",parent.document.body).attr("name")!= undefined){
   			$(parent.location).attr("href","login.jsp");	
   		}
		var formParam = {
			url : 'userAction!login.action',
			onSubmit: function(){
				var isValid = $(this).form('validate');
				if (isValid){
				$("#btn_login").linkbutton({text:'正在登录...'});
				}else{
				$("#btn_login").linkbutton({text:' 登 录 '});
				}
	        },
			success : function(data) {
				var json = $.parseJSON(data);
				if (json && json.success) {
					
					$.messager.show({
						title : '成功',
						msg : json.msg
					});
					var user = json.obj;
					window.location.href="index.jsp";
				} else {
					$("#btn_login").linkbutton({text:' 重新登录 '});
					$.messager.show({
						title : '失败',
						msg : json.msg
					});
				}
			}
		};
		loginInputForm = $('#loginInputForm').form(formParam);
		loginDatagridForm = $('#loginDatagridForm').form(formParam);
		loginComboboxForm = $('#loginComboboxForm').form(formParam);
		regForm = $('#regForm').form({
			url : 'userAction!reg.action',
			success : function(data) {
				var d = $.parseJSON(data);
				if (d.success) {
					regDialog.dialog('close');
					loginInputForm.find('input[name=cname]').val(regForm.find('input[name=cname]').val());
					loginInputForm.find('input[name=cpwd]').val(regForm.find('input[name=cpwd]').val());
					loginInputForm.submit();
				} else {
					regForm.find('input[name=cname]').focus();
				}
				$.messager.show({
					msg : d.msg,
					title : '提示'
				});
			}
		});
		$('form input').bind('keyup', function(event) {/* 增加回车提交功能 */
			if (event.keyCode == '13') {
				$(this).submit();
			}
		});

		loginAndRegDialog = $('#loginAndRegDialog').show().dialog({
			buttons : [ {
				text : ' 登  录 ',
				id : 'btn_login',
				handler : function() {
						var f = loginTabs.tabs('getSelected').find('form');
						f.submit();
				}
			} ]
		});

		regDialog = $('#regDialog').show().dialog({
			modal : true,
			title : '用户注册',
			closed : true,
			buttons : [ {
				text : '注册',
				handler : function() {
					regForm.submit();
				}
			} ],
			onOpen : function() {
				setTimeout(function() {
					regForm.find('input[name=cname]').focus();
				}, 1);
			},
			onClose : function() {
				loginTabs.tabs('select', 0);
			}
		});

		loginTabs = $('#loginTabs').tabs({
			onSelect : function(title) {
				if ('用户登录' == title) {
					window.setTimeout(function() {
						$('div.validatebox-tip').remove();
						loginInputForm.find('input[name=cname]').focus();
					}, 0);
				} else if ('表格方式' == title) {
					window.setTimeout(function() {
						if (loginDatagridName.combogrid('options').url == '') {
							loginDatagridName.combogrid({
								url : 'userAction!loginDatagrid.action'
							});
						}
						$('div.validatebox-tip').remove();
						loginDatagridName.combogrid('textbox').focus();
					}, 0);
				} else if ('补全方式' == title) {
					window.setTimeout(function() {
						if (loginComboboxName.combobox('options').url == '') {
							loginComboboxName.combobox({
								url : 'userAction!loginCombobox.action'
							});
						}
						$('div.validatebox-tip').remove();
						loginComboboxName.combobox('textbox').focus();
					}, 0);
				}
			}
		});

		loginDatagridName = $('#loginDatagridName').combogrid({
			loadMsg : '数据加载中....',
			panelWidth : 440,
			panelHeight : 180,
			required : true,
			fitColumns : true,
			value : '',
			idField : 'cname',
			textField : 'cname',
			mode : 'remote',
			url : '',
			pagination : true,
			pageSize : 5,
			pageList : [ 5, 10 ],
			columns : [ [ {
				field : 'cid',
				title : '编号',
				width : 60,
				hidden : true
			}, {
				field : 'cname',
				title : '登录名',
				width : 100,
				sortable : true
			}, {
				field : 'ccreatedatetime',
				title : '创建时间',
				width : 150,
				sortable : true
			}, {
				field : 'cmodifydatetime',
				title : '最后修改时间',
				width : 150,
				sortable : true
			} ] ],
			delay : 500
		});

		loginComboboxName = $('#loginComboboxName').combobox({
			required : true,
			url : '',
			textField : 'cname',
			valueField : 'cname',
			mode : 'remote',
			panelHeight : 'auto',
			delay : 500,
			value : ''
		});
	});
</script>
<div id="loginAndRegDialog" modal="true" title="欢迎使用 JEECG(基于代码生成器的J2EE智能开发框架)" closable="false" style="width:350px;height:230px;display: none;overflow: hidden;">
	<div id="loginTabs" fit="true" border="false">
		<div title="用户登录" style="overflow: hidden;">
			<div class="info">
				<div class="tip icon-tip"></div>
				<div>请输入用户帐号/密码<br/></div>
			</div>
			<div align="center">
				<form id="loginInputForm" method="post">
					<table class="tableForm" style="margin: 5px;">
						<tr>
							<th>登陆名</th>
							<td><input name="cname" class="easyui-validatebox" value="admin" required="true" missingMessage="请填写登录名称"  />
							</td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" value="admin" required="true" missingMessage="请填写登录密码"  />
							</td>
						</tr>
						<tr><td></td></tr>
					</table>
				</form>
			</div>
			<div align="center"><a href="init.jsp">恢复到初始化数据库状态</a></div>
		</div>
		<!--<div title="表格方式" style="overflow: hidden; display:none;">
			<div class="info">
				<div class="tip icon-tip"></div>
				<div>可模糊检索用户名</div>
			</div>
			<div align="center">
				<form id="loginDatagridForm" method="post">
					<table class="tableForm">
						<tr>
							<th>登录名</th>
							<td><select id="loginDatagridName" name="cname" style="display: none;width:200px;" required="true" missingMessage="请检索登录用户"></select></td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" required="true" missingMessage="请填写登录密码" value="" /></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div title="补全方式" style="overflow: hidden;display: none;">
			<div class="info">
				<div class="tip icon-tip"></div>
				<div>可自动补全用户名</div>
			</div>
			<div align="center">
				<form id="loginComboboxForm" method="post">
					<table class="tableForm">
						<tr>
							<th style="width: 50px;">用户名</th>
							<td><select id="loginComboboxName" name="cname" style="display: none;width:200px;"></select>
							</td>
						</tr>
						<tr>
							<th>密码</th>
							<td><input name="cpwd" type="password" class="easyui-validatebox" required="true" value="" />
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
	--></div>
</div>

<div id="regDialog" style="width:350px;display: none;padding: 5px;" align="center">
	<form id="regForm" method="post">
		<table class="tableForm">
			<tr>
				<th>登录名</th>
				<td><input name="cname" class="easyui-validatebox" required="true" missingMessage="请填写登录名称" /></td>
			</tr>
			<tr>
				<th>密码</th>
				<td><input name="cpwd" type="password" class="easyui-validatebox" required="true" missingMessage="请填写密码" /></td>
			</tr>
			<tr>
				<th>重复密码</th>
				<td><input name="rePassword" type="password" class="easyui-validatebox" required="true" missingMessage="请再次填写密码" validType="eqPassword['#regForm input[name=cpwd]']" /></td>
			</tr>
		</table>
	</form>
</div>