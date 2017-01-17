<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
body {
	font-family: helvetica, tahoma, verdana, sans-serif;
	padding: 10px;
	font-size: 13px;
	margin: 0;
}

* {
	margin: 0;
	padding: 0;
	border: 0;
}
.easyui-tree li{
	margin-top: 6px;
}

</style>
<script type="text/javascript" charset="utf-8">
	var tree;
	var menuPanel;
	$(function() {
		menuPanel = $('#menuPanel').panel({
			tools : [ {
				iconCls : 'icon-reload',
				handler : function() {
					tree.tree('reload');
				}
			}, {
				iconCls : 'icon-redo',
				handler : function() {
					var node = tree.tree('getSelected');
					if (node) {
						tree.tree('expandAll', node.target);
					} else {
						tree.tree('expandAll');
					}
				}
			}, {
				iconCls : 'icon-undo',
				handler : function() {
					var node = tree.tree('getSelected');
					if (node) {
						tree.tree('collapseAll', node.target);
					} else {
						tree.tree('collapseAll');
					}
				}
			} ]
		});

		tree = $('#menu').tree({
			url : 'menuAction!ctrlTree.action',
			lines : true,
			onClick : function(node) {
				//author:王正 date:20130118 for点击父菜单会出现Tab页，建议去掉。
				var nodeurl = node.attributes["url"].replace(' ','');
				if (nodeurl != '' && nodeurl !='#')
				addTab(node);
			},
			onDblClick : function(node) {
				if (node.state == 'closed') {
					$(this).tree('expand', node.target);
				} else {
					$(this).tree('collapse', node.target);
				}
			}
		});
	});
	
</script>
<div class="easyui-accordion" style="border:left" data-options="fit:true,border:false">
	<c:forEach var="item" items="${sessionInfo.auths}">
		<c:if test="${item.cpid=='0'}">
			<div title="${item.cname}" iconCls="${item.ciconCls}">
			<ul class="easyui-tree tree" data-options="url:'menuAction!treeRecursive.action?id=${item.cid}',onClick : function(node) {
				var nodeurl = node.attributes['url'].replace(' ','');
				if (nodeurl != '' && nodeurl !='#')
				addTab(node);
			}">  
    		</ul> 
			</div>
		</c:if>
	</c:forEach>
</div>
	
