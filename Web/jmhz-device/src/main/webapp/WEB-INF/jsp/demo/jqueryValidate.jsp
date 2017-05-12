<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title></title>
    <script src="${ctx}/assets/js/jquery-2.0.3.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/js/jquery.validate.min.js" type="text/javascript"></script>
    <script src="${ctx}/assets/js/test.js" type="text/javascript"></script>

</head>
<body>
<div>
<form id="signupForm" name="signupForm" action="">
用户名：<input type="text" name="name" id="name" value="${ctx}"/>
    </br>
密码：<input type="text"name="tel" id="tel" />
    </br>
<input type="submit" value="submit" />

</form>

</div>

</body>

</html>
