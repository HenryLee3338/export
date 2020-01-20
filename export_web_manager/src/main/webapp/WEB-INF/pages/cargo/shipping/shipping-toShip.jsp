<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ include file="../../base.jsp"%>
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
</head>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <!-- 内容头部 -->
    <section class="content-header">
        <h1>
            委托管理
            <small>新增委托单</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
            <li><a href="all-order-manage-list.html">货运管理</a></li>
            <li class="active">新增委托单</li>
        </ol>
    </section>
    <!-- 正文区域 -->
    <section class="content">
        <form action="${ctx}/cargo/shipping/edit.do" method="post">
        <div class="panel panel-default">
            <div class="panel-heading">对【${id}】委托</div>
            <input type="text" name="exportIds" value="${id}">

            <div class="row data-type"  style="margin: 0px">
                <div hidden   class="col-md-2 title">id</div>
                <div hidden class="col-md-4 data">
                    <input type="text"  class="form-control" placeholder="shippingOrderId" name="shippingOrderId" value="${id}"/>
                </div>

            <div class="row data-type" style="margin: 0px">
                <div class="col-md-2 title">货运类型</div>
                <div class="col-md-4 data">
                    <input type="text" class="form-control" placeholder="货运类型" name="orderType" value="${shipping.orderType}"/>
                </div>

                <div class="col-md-2 title">运输公司</div>
                <div class="col-md-4 data">
                    <input type="text" class="form-control" placeholder="运输公司" name="shipper" value="${shipping.shipper}"/>
                </div>

                <div class="col-md-2 title">收件人邮箱</div>
                <div class="col-md-4 data">
                    <input type="text" class="form-control" placeholder="收件人邮箱" name="notifyParty" value="${shipping.notifyParty}"/>
                </div>

                <div class="col-md-2 title">收货人</div>
                <div class="col-md-4 data">
                    <input type="text" name="consignee" class="form-control" placeholder="收货人" value="${packing.consignee}">
                </div>

                <div class="col-md-2 title">装运港</div>
                <div class="col-md-4 data">
                    <input type="text"  class="form-control" placeholder="装运港" name="portOfLoading" value="${shipping.portOfLoading}"/>
                </div>

                <div class="col-md-2 title">转运港</div>
                <div class="col-md-4 data">
                    <input type="text" name="portOfTrans" class="form-control" placeholder="转运港" value="${shipping.portOfTrans}"/>
                </div>

                <div class="col-md-2 title">卸货港</div>
                <div class="col-md-4 data">
                    <input type="text" name="portOfDischarge" class="form-control" placeholder="卸货港" value="${packing.portOfDischarge}">
                </div>



            </div>
        </div>
        <!--工具栏-->
        <div class="box-tools text-center">
            <button type="submit"  class="btn bg-maroon">保存</button>
            <button type="button" class="btn bg-default" onclick="history.back(-1);">返回</button>
        </div>
        </form>
        <!--工具栏/-->
    </section>
</div>
<!-- 内容区域 /-->
</body>

</html>