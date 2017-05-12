<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ c:taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html>
<head>
    <title>Test</title>
</head>
<body>
<form action="${ctx}/testController/testform" method="post">
    <table width="100%">
        <tr>
            <td>任职情况：</td>
            <td>
                <label>
                    <input name="representation" class="ace ace-checkbox-2" type="checkbox" value="0">
                    <span class="lbl">村民代表</span>
                </label>
            </td>
            <td>
                <label>
                    <input name="representation" class="ace ace-checkbox-2" type="checkbox" value="1">
                    <span class="lbl">村民小组长</span>
                </label>
            </td>
        </tr>
        <tr>
            <td rowspan="2"></td>
            <td colspan="2">
                <label>
                    <input name="representation" class="ace ace-checkbox-2" type="checkbox" value="2">
                    <span class="lbl">现任村“两委”干部（是否村主干）</span>
                </label>
            </td>
        </tr>
        <tr>
            <td colspan="2">
                <label>
                    <input name="representation" class="ace ace-checkbox-2" type="checkbox" value="3">
                    <span class="lbl">离任村“两委”干部（是否村主干）</span>
                </label>
            </td>
        </tr>
    </table>
    <input type="submit" value="submit" name="submit"/>
</form>
</body>
</html>
