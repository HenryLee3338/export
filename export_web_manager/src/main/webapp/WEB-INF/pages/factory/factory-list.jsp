<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
</head>
<script>
    function deleteById() {
        var id = getCheckId()
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/factory/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }

    function roleList() {
        var id = getCheckId()
        if(id) {
            location.href="${ctx}/system/user/roleList.do?id="+id;
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }
</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
<section class="content-header">
    <h1>
        基础信息
        <small>厂家信息</small>
    </h1>
    <ol class="breadcrumb">
        <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
    </ol>
</section>
<section class="content">
    <div class="box box-primary">
        <div class="box-header with-border">
            <h3 class="box-title">厂家列表</h3>
        </div>
        <div class="box-body">
            <div class="table-box">
                <div class="pull-left">
                    <div class="form-group form-inline">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default" title="新建" onclick='location.href="${ctx}/factory/toAdd.do"'><i class="fa fa-file-o"></i> 新建</button>
                            <button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>
                            <button type="button" class="btn btn-default" title="刷新" onclick="window.location.reload();"><i class="fa fa-refresh"></i> 刷新</button>

                        </div>
                    </div>
                </div>
                <div class="box-tools pull-right">
                    <div class="has-feedback">
                        <input type="text" class="form-control input-sm" placeholder="搜索">
                        <span class="glyphicon glyphicon-search form-control-feedback"></span>
                    </div>
                </div>
                <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                    <thead>
                    <tr>
                        <th class="" style="padding-right:0px;">

                        </th>
                        <th class="sorting">序号</th>
                        <th class="sorting">工厂名</th>
                        <th class="sorting">厂家类型</th>
                        <th class="sorting">联系人</th>
                        <th class="sorting">电话</th>
                        <th class="sorting">手机</th>
                        <th class="sorting">地址</th>
                        <th class="sorting">状态</th>
                        <th class="text-center">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.list}" var="item" varStatus="sta">
                    <tr>
                        <td><input name="ids" value="${item.id}" type="checkbox"></td>
                        <td>${sta.index+1}</td>
                        <td><a href="${ctx}/factory/toUpdate.do?id=${o.id}">${item.fullName}</a></td>
                        <td>${item.ctype }</td>        <%--//厂家类型：货物/附件--%>
                        <%--<td>${item.factoryName }</td>       //厂家简称--%>
                        <td>${item.contacts }</td>     <%-- //联系人--%>
                        <td>${item.phone }</td>        <%--/ /电话--%>
                        <td>${item.mobile }</td>       <%-- //手机--%>
                       <%-- <td>${item.fax }</td>        /	//传真--%>
                        <td>${item.address }</td>      <%-- //地址--%>
                        <%--<td>${item.inspector }</td>     //验货员，杰信代表
                        <td>${item.remark }</td>       	//说明
                        <td>${item.orderNo }</td>      //排序号--%>
                        <td>${item.state  ==0?'停用':'启用'}</td>   <%--   //状态：--%>
                        <th class="text-center">
                            <button type="button" class="btn bg-olive btn-xs" onclick='location.href="${ctx}/factory/toUpdate.do?id=${item.id}"'>编辑</button>
                        </th>
                    </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="box-footer">
            <jsp:include page="../common/page.jsp">
                <jsp:param value="${ctx}/factory/list.do" name="pageUrl"/>
            </jsp:include>
        </div>
    </div>

</section>
</div>
</body>

</html>