<%@page pageEncoding="UTF-8" isELIgnored="false"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>

<script>
    function update(id,status){
        b = confirm("确定修改");
        if(b==true){
            $.ajax({
                url:"${path}/userController/status",
                data:{'id':id,'status':status},
                datatype:"json",
                success:function (res) {
                    $('#List').jqGrid('clearGridData');
                    $('#List').trigger('reloadGrid');
                }
            })
        }

    }


    $(function () {

        $("#List").jqGrid({

            styleUI: "Bootstrap",
            url:"${path}/userController/findAllfy",
            datatype:"json",
            colNames:["ID","头像","名字","密码","状态","手机号","注册时间"],
            colModel:[
                {name:"id"},
                {name:"src",align:'center',editable:true,
                     formatter:function (value,options,rows) {
                           return "<img src='${path}/user/users/"+value+"' style='width:60px;height:60px'>";
                     }
                },
                {name:"name",align:'center',editable:true},
                {name:"password",align:'center',editable:true},
                {name:"status",align:'center',editable:true,
                    formatter:function (value,options,rows) {

                           if(value==0) return "<button class='btn btn-success' onclick='update(\""+rows.id+"\",\""+value+"\")'>冻结</button>";
                           else return "<button class='btn btn-danger' onclick='update(\""+rows.id+"\",\""+value+"\")'>解除冻结</button>";
                    }
                },
                {name:"phone",align:'center',editable:true},
                {name:"mins",align:'center',editable:true,formatter:'date',formatoptions:{newformat:'Y-m-d'}},
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
        }).navGrid("#pager",{edit:false,add:false,del:false},
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



<script>
    $(function(){
        //给按钮加点击事件
        $("#aliyun").click(function(){
            //先获取输入框输入的手机号
            var phone = $("#phone").val();
            // alert(phone);
            //发送ajax请求
            $.post("${path}/userController/sendyzm",{"phoneNumbers":phone},function(res){
                console.log(res);
                if(res.status == "200"){
                    alert(res.message);
                }else{
                    alert(res.message);
                }
            },"JSON");
        });
    });
</script>


<script>
    $(function(){
        //给按钮加点击事件
        $("#dc").click(function(){
            //发送ajax请求
            $.post("${path}/userController/findAllDC",{},function(res){
                console.log(res);
                if(res.status == "200"){
                    alert(res.message);
                }else{
                    alert(res.message);
                }
            },"JSON");
        });
    });
</script>


<%--设置面板--%>
<div class="panel panel-info">

    <%--面板头--%>
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <%--标签页--%>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#home" aria-controls="home" role="tab" data-toggle="tab">用户管理</a></li>
    </ul>

    <hr>

    <div>
        <div class="pull-left">
            <button class="btn btn-info" id="dc">导出用户信息</button>
            <button class="btn btn-success">导入用户</button>
            <button class="btn btn-danger">测试按钮</button>
        </div>

        <div class="pull-right col-sm-6">
            <form>
                <div class="col-md-6 col-md-offset-3" style="padding: 5px;">
                    <input type="text" class="form-control" id="phone" name="phoneNumbers" placeholder="请输入手机号..." required minlength="11">
                </div>

                <div class="col-md-3 pull-right" style="padding: 5px;">
                    <button type="button" id="aliyun" class="btn btn-info btn-block">发送验证码</button>
                </div>
            </form>
        </div>
    </div>

    <hr>
    <hr>

    <%--表单--%>
    <table id="List"></table>
    <%--分页工具栏--%>
    <div id="pager"></div>

</div>
