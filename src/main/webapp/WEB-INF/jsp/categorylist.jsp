<%--
  Created by IntelliJ IDEA.
  User: 22430
  Date: 2019/8/6
  Time: 22:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>类别列表</title>

    <table>
        <tr>
            <th>类别Id</th>
            <th>类别名称</th>
            <th>所属父类</th>
            <th>类别状态</th>
            <th>创建时间</th>
            <th>修改时间</th>
            <td>操作</td>
        </tr>
        <c:forEach items="${categorylist}" var="category">
        <tr>
            <td>${category.id}</td>
            <td>${category.name}</td>
            <td>${category.parentId}</td>
            <td>${category.status}</td>
            <td>${category.createTime}</td>
            <td>${category.updateTime}</td>
            <td><a href="update/${category.id}">修改</a>
                <a href="">删除</a>
            </td>
        </tr>
        </c:forEach>
    </table>

</head>
<body>

</body>
</html>
