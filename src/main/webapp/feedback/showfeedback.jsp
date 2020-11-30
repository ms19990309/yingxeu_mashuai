<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>

    $(function () {

        $("#List").jqGrid({

            styleUI: "Bootstrap",
            url:"${path}/userController/findAllfy",
            datatype:"json",
            colNames:["ID","头像","名字","密码"],
            colModel:[
                {name:"id"},
                {name:"src",align:'center',editable:true},
                {name:"name",align:'center',editable:true},
                {name:"password",align:'center',editable:true},
            ],
            pager:"#pager",
            page:1,
            rowNum:5,
            rowList:[2,4,6,8],
            viewrecords:true,
            autowidth:true,
            //height:'auto',
            editurl:"",
            // caption:"员工表",
        }).navGrid("#pager",{edit:true,add:true,del:true},
            {},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
            },
            {
                closeAfterDelete: true, //不生效
                reloadAfterSubmit: true
            },
            {}
        );

    });

</script>


<%--设置面板--%>
<div class="panel panel-primary">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>

    <hr>

    <%--表单--%>
    <table id="List"></table>
    <%--分页工具栏--%>
    <div id="pager"></div>

</div>
