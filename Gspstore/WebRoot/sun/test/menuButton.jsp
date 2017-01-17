<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/inc.jsp"></jsp:include>
</head>

<body>  
    <div style="padding:5px;border:1px solid #ddd">  
        <a href="#" class="easyui-linkbutton" data-options="plain:true">Home</a>  
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm1',iconCls:'icon-edit'">Edit</a>  
        <a href="#" class="easyui-menubutton" data-options="menu:'#mm2',iconCls:'icon-help'">Help</a>  
    </div>  
    <div id="mm1" style="width:150px;">  
        <div data-options="iconCls:'icon-undo'">Undo</div>  
        <div data-options="iconCls:'icon-redo'">Redo</div>  
        <div class="menu-sep"></div>  
        <div>Cut</div>  
        <div>Copy</div>  
        <div>Paste</div>  
        <div class="menu-sep"></div>  
        <div>  
            <span>Toolbar</span>  
        </div>  
        <div data-options="iconCls:'icon-remove'">Delete</div>  
        <div>Select All</div>  
    </div>  
    <div id="mm2" style="width:100px;">  
        <div>Help</div>  
        <div>Update</div>  
        <div>About</div>  
    </div>  
</body>  

</html>