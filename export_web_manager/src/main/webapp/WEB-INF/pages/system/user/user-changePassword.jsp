<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html>
<html>
<html>

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>数据 - AdminLTE2定制版 | Log in</title>
    <meta content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no" name="viewport">
    <link rel="stylesheet" href="../../plugins/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="../../plugins/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="../../plugins/ionicons/css/ionicons.min.css">
    <link rel="stylesheet" href="../../plugins/adminLTE/css/AdminLTE.css">
    <link rel="stylesheet" href="../../plugins/iCheck/square/blue.css">

</head>



<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="all-admin-index.html">SaaS外贸出口云平台</a>
    </div>
    <!-- /.login-logo -->
    <div class="login-box-body">
        <p class="login-box-msg">密码修改</p>
        <form action="${pageContext.request.contextPath}/system/user/passwordUpdate.do" method="post">

            <div class="form-group has-feedback">
                <p>原密码</p>
                <input type="password" name="password" class="form-control"value="${password}">

            </div>
            <div class="form-group has-feedback">
                <p>新密码</p>
                <input type="password" name="password1"  class="form-control" placeholder="密码">

            </div>
            <div class="form-group has-feedback">
                <p>确认密码</p>
                <input type="password" name="password2"  class="form-control" placeholder="密码">

            </div>



            <div class="row">
                <div class="col-xs-8">
                    <div class="checkbox icheck" >
                        <label style="color: red" class="" >${error}</label>
                    </div>
                </div>
                <div class="col-xs-4">
                    <button type="submit" class="btn btn-primary btn-block btn-flat">确认</button>
                </div>
            </div>
        </form>

        <div id="login_container"></div>
    </div>
</div>
<script src="../plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="../plugins/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>