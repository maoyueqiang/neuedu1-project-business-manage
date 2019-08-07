<%--
  Created by IntelliJ IDEA.
  User: 22430
  Date: 2019/8/6
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改类别</title>
</head>
<body>
<h1>修改类别</h1>
<form action="" method="post">
    <input type="hidden" name="id" value="${category.id}"><br/>
    类别名称<input type="text" name="name" value="${category.name}"><br/>
    所属父类<input type="text" name="parentId" value="${category.parentId}"><br/>
    类别状态<input type="text" name="status" value="${category.status}"><br/>
    <input type="submit" value="修改">
</form>


</body>
</html>
