<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    $(function () {

        $("#List").jqGrid({

            styleUI: "Bootstrap",
            url:"${path}/videoController/findAllfy",
            datatype:"json",
            colNames:["ID","名称","视频","上传时间","描述","类别id","用户id","状态"],
            colModel:[
                {name:"id",index:'id',align:'center'},
                {name:"title",align:'center',editable:true,},
                {name:"videoPath",editable:true,index:'name asc, invdate', width:200,align:"center",edittype:"file",
                    formatter:function(cellvalue, options, rowObject){
                        return "<video controls='controls' src="+cellvalue+" width='300px' height='200px'>";
                    }
                },
                {name:"uploadTime",align:'center',formatter:'date',formatoptions:{newformat:'Y-m-d'}},
                {name:"brief",align:'center',editable:true,},
                {name:"categoryId",align:'center',editable:true},
                {name:"userId",align:'center',editable:true},
                {name:"state",align:'center',
                    formatter:function (value,options,rows) {

                        if(value==0) return "<button class='btn btn-success' onclick='update(\""+rows.id+"\",\""+value+"\")'>未审核</button>";
                        if(value==1) return "<button class='btn btn-danger' onclick='update(\""+rows.id+"\",\""+value+"\")'>审核通过</button>";
                        if(value==2) return "<button class='btn btn-danger' onclick='update(\""+rows.id+"\",\""+value+"\")'>审核不通过</button>";
                    }
                },
            ],
            editurl:"${path}/videoController/edit",
            pager:"#pager",
            page:1,
            rowNum:5,
            rowList:[2,4,6,8],
            viewrecords:true,
            autowidth:true,
        }).navGrid("#pager",
            {},
            {
                closeAfterEdit: true,  //关闭面板
                reloadAfterSubmit: true,
            },
            {
                closeAfterAdd: true,
                reloadAfterSubmit: true,
                afterSubmit:function(data){
                    //1.数据入库  返回id
                    //2.文件上传  根据id修改数据

                    //data.responseText=uuid
                    $.ajaxFileUpload({
                        url: "${path}/videoController/headUpload",
                        type: "post",
                        data: {"id": data.responseText},
                        fileElementId: "videoPath",
                        success:function(){
                            //刷新页面
                            $("#List").trigger("reloadGrid");
                        }
                    });
                    return "hello";
                }
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
<div class="panel panel-warning">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>视频信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">视频管理</a></li>
    </ul>

    <%--表单--%>
    <table id="List"></table>
    <%--分页工具栏--%>
    <div id="pager"></div>

</div>
