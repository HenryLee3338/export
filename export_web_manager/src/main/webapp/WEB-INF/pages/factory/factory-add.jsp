<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../base.jsp"%>
<!DOCTYPE html>
<html>

<head>
    <!-- 页面meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版</title>
    <meta name="description" content="AdminLTE2定制版">
    <meta name="keywords" content="AdminLTE2定制版">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <!-- 页面meta /-->
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            基础信息
            <small>厂家信息</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/factory/list.do">厂家列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">厂家信息</div>
            <form id="editForm" action="${ctx}/factory/edit.do" method="post">
                <%--<input type="text" id="deptName" name="deptName" value="${user.deptName}">--%>
                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-1 title">厂家全称</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="厂家全称" name="fullName" value="${factory.fullName}">
                    </div>

                   <%-- <div class="col-md-1 title">所在部门</div>
                    <div class="col-md-5 data">
                        <select class="form-control" onchange="document.getElementById('deptName').value=this.options[this.selectedIndex].text" name="deptId">
                            <option value="">请选择</option>
                            <c:forEach items="${deptList}" var="item">
                                <option ${user.deptId == item.id ?'selected':''} value="${item.id}">${item.deptName}</option>
                            </c:forEach>
                        </select>
                    </div>--%>

                    <div class="col-md-1 title">厂家类型</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="厂家类型" name="ctype" value="${factory.ctype}">
                    </div>

                    <div class="col-md-1 title">厂家简称</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="厂家简称" name="factoryName" value="${factory.factoryName}">
                    </div>

                    <div class="col-md-1 title">联系人</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="联系人" name="contacts" value="${factory.contacts}">
                    </div>

                    <div class="col-md-1 title">状态</div>
                    <div class="col-md-5 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${user.state==0?'checked':''} name="state" value="0">停用</label></div>
                            <div class="radio"><label><input type="radio" ${user.state==1?'checked':''} name="state" value="1">启用</label></div>
                        </div>
                    </div>

                    <%--<div class="col-md-1 title">入职时间</div>
                    <div class="col-md-5 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="入职时间"  name="joinDate" class="form-control pull-right"
                                   value="${user.joinDate}" id="datepicker">
                        </div>
                    </div>--%>

                    <%--<div class="col-md-1 title">等级</div>
                    <div class="col-md-5 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${user.degree==1?'checked':''} name="degree" value="1">系统管理员</label></div>
                            <div class="radio"><label><input type="radio" ${user.degree==2?'checked':''} name="degree" value="2">管理下属部门和人员</label></div>
                            <div class="radio"><label><input type="radio" ${user.degree==3?'checked':''} name="degree" value="3">管理本部门</label></div>
                            <div class="radio"><label><input type="radio" ${user.degree==4?'checked':''} name="degree" value="4">普通员工</label></div>
                        </div>
                    </div>
--%>
                    <%--<div class="col-md-1 title">性别</div>
                    <div class="col-md-5 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${user.gender==0?'checked':''} name="gender" value="0">男</label></div>
                            <div class="radio"><label><input type="radio" ${user.gender==1?'checked':''} name="gender" value="1">女</label></div>
                        </div>
                    </div>--%>

                    <div class="col-md-1 title">手机</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="手机" name="mobile" value="${factory.mobile}">
                    </div>
                    <div class="col-md-1 title">电话</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="电话" name="phone" value="${factory.phone}">
                    </div>

                    <div class="col-md-1 title">传真</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="传真" name="fax" value="${factory.fax}">
                    </div><div class="col-md-1 title">地址</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="地址" name="address" value="${factory.address}">
                    </div>
                    <div class="col-md-1 title">验货员</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="验货员" name="inspector" value="${factory.inspector}">
                    </div>

                    <%--<div class="col-md-1 title">出生年月</div>
                    <div class="col-md-5 data">
                        <div class="input-group date">
                            <div class="input-group-addon">
                                <i class="fa fa-calendar"></i>
                            </div>
                            <input type="text" placeholder="出生年月" class="form-control pull-right" name="birthday"
                                   value="${user.birthday}" id="datepicker1">
                        </div>
                    </div>--%>
                    <div class="col-md-1 title">排序号</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="排序号" name="orderNo" value="${factory.orderNo}">
                    </div>
                    <div class="col-md-1 title">说明</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="说明" name="remark" value="${factory.remark}">
                    </div>
                </div>
            </form>
        </div>
        <!--订单信息/-->

        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="button" onclick='document.getElementById("editForm").submit()' class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        <!--工具栏/-->

    </section>
    <!-- 正文区域 /-->

</div>
<!-- 内容区域 /-->
</body>
<script src="${ctx}/plugins/datepicker/bootstrap-datepicker.js"></script>
<script src="${ctx}/plugins/datepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<link rel="stylesheet" href="${ctx}/css/style.css">
<script>
    $('#datepicker').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
    $('#datepicker1').datepicker({
        autoclose: true,
        format: 'yyyy-mm-dd'
    });
</script>
</html>