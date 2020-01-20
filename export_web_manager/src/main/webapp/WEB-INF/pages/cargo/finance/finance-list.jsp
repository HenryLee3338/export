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
    <!-- 页面meta /-->
    <script src="${ctx}/plugins/jQuery/jquery-2.2.3.min.js"></script>
</head>
<script>
    function deleteById() {
        //获取传入的o
        var idAndState = getCheckId()
        //获取选择的数量
        var size = $("input:checkbox:checked").length;
        //按逗号分隔
        if (idAndState){
            var str = idAndState.split(',');
            //取出不同位置的值
            var id = str[0];
            var state = str[1];
        }
        if(id) {
            if(confirm("你确认要删除此条记录吗？")) {
                location.href="${ctx}/cargo/invoice/delete.do?id="+id;
            }
        }else{
            alert("请勾选待处理的记录，且每次只能勾选一个")
        }
    }



    function submit() {
        //获取传入的o
        var idAndState = getCheckId()
        //获取选择的数量
        var size = $("input:checkbox:checked").length;
        //按逗号分隔
        if (idAndState){
            var str = idAndState.split(',');
            //取出不同位置的值
            var id = str[0];
            var state = str[1];
        }
        
        if (state == 2){
            if(id) {
                location.href="${ctx}/cargo/invoice/submit.do?id="+id;
            }else{
                alert("请勾选待处理的记录，且每次只能勾选一个")
            }
        } else {
            alert("非待上报状态，无法上报")
        }
    }



    <%--function cancel() {--%>
        <%--//获取传入的o--%>
        <%--var idAndState = getCheckId()--%>
        <%--//获取选择的数量--%>
        <%--var size = $("input:checkbox:checked").length;--%>
        <%--//按逗号分隔--%>
        <%--if (idAndState){--%>
            <%--var str = idAndState.split(',');--%>
            <%--//取出不同位置的值--%>
            <%--var id = str[0];--%>
            <%--var state = str[1];--%>
        <%--}--%>
        <%--if(id) {--%>
            <%--location.href="${ctx}/cargo/export/cancel.do?id="+id;--%>
        <%--}else{--%>
            <%--alert("请勾选待处理的记录，且每次只能勾选一个")--%>
        <%--}--%>
    <%--}--%>


    function find() {
        location.href="${ctx}/cargo/finance/list1.do";
    }

</script>
<body>
<div id="frameContent" class="content-wrapper" style="margin-left:0px;">
    <section class="content-header">
        <h1>
            发票管理
            <small>发票列表</small>
        </h1>
        <ol class="breadcrumb">
            <li><a href="all-admin-index.html"><i class="fa fa-dashboard"></i> 首页</a></li>
        </ol>
    </section>
    <!-- 内容头部 /-->

    <!-- 正文区域 -->
    <section class="content">

        <!-- .box-body -->
        <div class="box box-primary">
            <div class="box-header with-border">
                <h3 class="box-title">发票列表</h3>
            </div>

            <div class="box-body">

                <!-- 数据表格 -->
                <div class="table-box">

                    <!--工具栏-->
                    <div class="pull-left">
                        <div class="form-group form-inline">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default" title="查看财务表" onclick='find()'><i class="fa fa-file-o"></i> 查看财务表</button>
                                <%--<button type="button" class="btn btn-default" title="删除" onclick='deleteById()'><i class="fa fa-trash-o"></i> 删除</button>--%>
                                <button type="button" class="btn btn-default" title="上报财务" onclick='submit()'><i class="fa fa-file-o"></i> 上报财务</button>
                                <%--<button type="button" class="btn btn-default" title="取消" onclick='cancel()'><i class="fa fa-file-o"></i> 取消</button>--%>
                            </div>
                        </div>
                    </div>
                    <div class="box-tools pull-right">
                        <div class="has-feedback">
                            <input type="text" class="form-control input-sm" placeholder="搜索">
                            <span class="glyphicon glyphicon-search form-control-feedback"></span>
                        </div>
                    </div>
                    <!--工具栏/-->

                    <!--数据列表-->
                    <table id="dataList" class="table table-bordered table-striped table-hover dataTable">
                        <thead>
                        <tr>
                            <td><input type="checkbox" name="selid" onclick="checkAll('id',this)"></td>
                            <th class="sorting">发票ID</th>
                            <th class="sorting">提单号</th>
                            <th class="sorting">应收金额/实收金额</th>
                            <th class="sorting">剩余金额</th>
                            <th class="sorting">提示</th>
                            <th class="sorting">状态</th>
                            <%--<th class="sorting">创建人</th>--%>
                            <%--<th class="sorting">创建部门</th>--%>
                            <th class="text-center">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${page.list}" var="o" varStatus="status">
                            <tr class="odd" onmouseover="this.className='highlight'" onmouseout="this.className='odd'" >
                                <td><input type="checkbox" name="idAndState" value="${o.invoiceId},${o.state}"/></td>
                                <td>${o.invoiceId}</td>
                                <td>${o.tradeTerms}</td>
                                <td>${o.totalMoney}/${o.realMoney}</td>
                                <td>
                                    <%--这点还需要完善--%>
                                    <form action="${ctx}/cargo/invoice/change.do">
                                            ${o.syMoney}
                                                <c:if test="${o.state!=2}">
                                                    <input hidden type="text" name="invoiceId" value="${o.invoiceId}"/>
                                                    <input type="text" name="realMoney"/>
                                                    <input type="submit" value="提交"/>
                                                </c:if>
                                    </form>
                                </td>
                                <td>${o.blNo}</td>
                                <td>
                                    <c:if test="${o.state==0}">草稿</c:if>
                                    <c:if test="${o.state==1}"><font color="blue">收钱中</c:if>
                                    <c:if test="${o.state==2}"><font color="orange">待上报</font></c:if>
                                    <c:if test="${o.state==3}"><font color="green">已上报</font></c:if>

                                </td>
                                <%--<td>${o.createBy}</td>--%>
                                <%--<td>${o.createDept}</td>--%>
                                <td>
                                    <c:if test="${o.state==0}">
                                        <a href="${ctx}/cargo/invoice/toUpdate.do?id=${o.invoiceId}">[编辑]</a>
                                    </c:if>
                                </td>

                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
            <!-- /.box-body -->

            <!-- .box-footer-->
            <div class="box-footer">
                <jsp:include page="../../common/page.jsp">
                    <jsp:param value="cargo/contract/list.do" name="pageUrl"/>
                </jsp:include>
            </div>
            <!-- /.box-footer-->


        </div>

    </section>
</div>
</body>

</html>