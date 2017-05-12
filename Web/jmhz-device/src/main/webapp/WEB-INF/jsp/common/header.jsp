<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="navbar navbar-default" id="navbar">
<script type="text/javascript">
    try {
        ace.settings.check('navbar', 'fixed')
    } catch (e) {
    }
</script>
    <script language="javascript">
        path = "${pageContext.request.contextPath}";
    </script>
<div class="navbar-container" id="navbar-container">
<span style="float: left;margin-top:5px;margin-left: 5px;">
    <img src="${ctx}/assets/images/logos.png" width="70px" height="70px"/>
</span>
<div class="navbar-header pull-left">
    <a class="navbar-brand" style="margin-left: 10px;">
        <span style="font-size: 40px;line-height: 60px;font-family:'楷体','Helvetica Neue Light', 'Lucida Grande', 'Calibri', 'Arial', 'sans-serif';">
            实验室设备管理系统
        </span>
    </a><!-- /.brand -->
</div>
<!-- /.navbar-header -->
<div class="navbar-header pull-right" role="navigation" style="height: 35px">
<ul class="nav ace-nav">

<li>
    <a data-toggle="dropdown" href="#" class="dropdown-toggle" style="background: #267eb8 ">
            <span class="user-info" style="top: 12px"><b>${user.username}</b></span>
        <i class="icon-caret-down"></i>
    </a>
    <ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
        <li>
            <a href="${ctx}/user/changePassword/${user.id}">
                <i class="icon-user"></i>
                修改密码
            </a>
        </li>
        <li class="divider"></li>
        <li>
            <a href="${ctx}/logout">
                <i class="icon-off red"></i>
                退出
            </a>
        </li>
    </ul>
</li>
</ul>
<!-- /.ace-nav -->
</div>
<!-- /.navbar-header -->
</div>
<!-- /.container -->
</div>