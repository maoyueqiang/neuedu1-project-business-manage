<%--
  Created by IntelliJ IDEA.
  User: 22430
  Date: 2019/8/7
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户列表</title>
</head>
<body>

<table>
    <tr>
        <th>用户id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱</th>
        <th>手机号</th>
        <th>密保问题</th>
        <th>密保答案</th>
        <th>用户角色</th>
        <th>创建时间</th>
        <th>更新时间</th>
        <th>ip</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${userlist}" var="user">
        <tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.password}</td>
            <td>${user.email}</td>
            <td>${user.phone}</td>
            <td>${user.question}</td>
            <td>${user.answer}</td>
            <td>${user.role}</td>
            <td>${user.createTime}</td>
            <td>${user.updateTime}</td>
            <td>${user.ip}</td>
            <td><a href="update/${user.id}">修改</a>
                <a href="delete/${user.id}">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>
<a href="insert">添加用户</a>

</body>
</html>
