<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Demo</title>
</head>
<body>
<h4>示例jsp页面</h4>
<ol>
    <li><a href="${ctx}/demo/appendResource">appendResource.jsp</a></li>
    <li><a href="${ctx}/demo/blank">blank.jsp</a></li>
    <li><a href="${ctx}/demo/jqgrid">jqgrid.jsp</a></li>
    <li><a href="${ctx}/demo/jqueryValidate">jqueryValidate.jsp</a></li>
    <li><a href="${ctx}/demo/jspTemplate">jsp-template.jsp</a></li>
    <li><a href="${ctx}/demo/login">login.jsp</a></li>
    <li><a href="${ctx}/demo/mqcard">mqcard.jsp</a></li>
    <li><a href="${ctx}/demo/mqgroupcard">mqgroupcard.jsp</a></li>
    <li><a href="${ctx}/demo/mqgroupstatus">mqgroupstatus.jsp</a></li>
    <li><a href="${ctx}/demo/mqstatus">mqstatus.jsp</a></li>
    <li><a href="${ctx}/demo/test">test.jsp</a></li>
    <li><a href="${ctx}/demo/zTree">zTree.jsp</a></li>
</ol>
</body>
</html>
