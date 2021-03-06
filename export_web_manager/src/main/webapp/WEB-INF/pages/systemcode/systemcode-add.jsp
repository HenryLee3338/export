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
            <small>货号信息</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="/"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="${ctx}/systemcode/list.do">货号列表</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!--订单信息-->
        <div class="panel panel-default">
            <div class="panel-heading">新增货号</div>
            <form id="editForm" action="${ctx}/systemcode/edit.do" method="post">
                   <%-- <div class="col-md-1 title">厂家名称</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="厂家名称" name="factoryName" value="${huohao.factoryName}">
                    </div>--%>
                    <input type="text" name="factoryName" id="factoryName" value="${huohao.factoryName}">
                    <div class="row data-type" style="margin: 0px">
                        <div class="col-md-1 title">厂家名称</div>
                        <div class="col-md-5 data">
                            <select class="form-control" name="factoryId" id="factoryInfo"
                                    onchange="document.getElementById('factoryName').value=this.options[this.selectedIndex].text">
                                <option value="">请选择</option>
                                <c:forEach items="${factoryList}" var="factory">
                                    <option value="${factory.id}">${factory.factoryName}</option>
                                </c:forEach>
                            </select>
                        </div>

                <div class="row data-type" style="margin: 0px">
                    <div class="col-md-1 title">货号</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="货号" name="huohaoName" value="${huohao.huohaoName}">
                    </div>


                    <div class="col-md-1 title">状态</div>
                    <div class="col-md-5 data">
                        <div class="form-group form-inline">
                            <div class="radio"><label><input type="radio" ${huohao.state==0?'checked':''} name="state" value="0">停用</label></div>
                            <div class="radio"><label><input type="radio" ${huohao.state==1?'checked':''} name="state" value="1">启用</label></div>
                        </div>
                    </div>

                    <div class="col-md-1 title">类型</div>
                    <div class="col-md-5 data">
                        <input type="text" class="form-control" placeholder="类型" name="ctype" value="${huohao.ctype}">
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