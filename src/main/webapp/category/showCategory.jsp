<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>


<script>

    $(function(){
        pageInit();
    });

    function pageInit(){
        $("#cateTable").jqGrid({
            url : "${path}/categoryController/findAllfy",
            datatype : "json",
            rowNum : 5,
            rowList : [2,4,6,8],
            pager : '#catePage',
            sortname : 'id',
            viewrecords : true,
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : ["ID","名称","级别"],
            colModel : [
                {name:"id",align:'center'},
                {name:"name",align:'center',editable:true},
                {name:"grade",align:'center'},
            ],
            editurl:"${path}/categoryController/edit",
            subGrid : true,  //开启子表格
            // subgrid_id:是在创建表数据时创建的div标签的ID
            //row_id是该行的ID
            subGridRowExpanded : function(subgrid_id, row_id) {
                addSubGrid(subgrid_id, row_id);
            }
        });
        $("#cateTable").jqGrid('navGrid', '#catePage',
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
                reloadAfterSubmit: true,
                afterSubmit:function (response) {
                    if(response.responseJSON.status){
                        alert(response.responseJSON.status)
                    }
                    return "true";
                }
            },
            {}
        );
    }


    //开启子表格的样式
    function addSubGrid(subgridId, rowid){

        var subgridTableTd= subgridId + "Table";
        var pagerId= subgridId+"Page";


        $("#"+subgridId).html("" +
            "<table id='"+subgridTableTd+"' />" +
            "<div id='"+pagerId+"' />"
        );



        $("#" + subgridTableTd).jqGrid({
            url : "${path}/categoryController/findAllfys?id=" + rowid,
            datatype : "json",
            rowNum : 5,
            rowList : [2,4,6,8],
            pager : "#"+pagerId,
            sortname : 'id',
            sortorder : "asc",
            styleUI:"Bootstrap",
            autowidth:true,
            height:"auto",
            colNames : ["ID","名称","级别","父类ID"],
            colModel : [
                {name:"id",align:'center'},
                {name:"name",align:'center',editable:true},
                {name:"grade",align:'center'},
                {name:"parentclass",align:'center'},
            ],
            editurl:"${path}/categoryController/edits?ids=" + rowid,
        });

        $("#" + subgridTableTd).jqGrid('navGrid',"#" + pagerId,
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
    }


</script>


<%--设置面板--%>
<div class="panel panel-success">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>类别信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">类别管理</a></li>
    </ul>

    <%--表单--%>
    <table id="cateTable" />

    <%--分页工具栏--%>
    <div id="catePage" />

</div>

<%--
    删除要有提示信息
--%>
