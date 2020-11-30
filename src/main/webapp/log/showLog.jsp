<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {

        $("#List").jqGrid({

            styleUI: "Bootstrap",
            url:"${path}/logController/findAllfy",
            datatype:"json",
            colNames:["ID","管理员名称","操作时间","操作代码快","是否成功"],
            colModel:[
                {name:"id"},
                {name:"name",align:'center',editable:true},
                {name:"times",align:'center',editable:true,formatter:'date',formatoptions:{newformat:'Y-m-d'}},
                {name:"options",align:'center',editable:true},
                {name:"status",align:'center',editable:true},
            ],
            editurl:"${path}/logController/edit",
            pager:"#pager",
            page:1,
            rowNum:5,
            rowList:[2,4,6,8],
            viewrecords:true,
            autowidth:true,
        }).navGrid("#pager",{edit:false,add:false,del:true},
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
<div class="panel panel-danger">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>日志信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">日志管理</a></li>
    </ul>

    <hr>

    <%--表单--%>
    <table id="List"></table>
    <%--分页工具栏--%>
    <div id="pager"></div>

</div>
