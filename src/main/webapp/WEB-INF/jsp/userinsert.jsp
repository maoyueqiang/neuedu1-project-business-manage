<%--
  Created by IntelliJ IDEA.
  User: 22430
  Date: 2019/8/7
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>添加用户</title>
</head>
<body>
<h1>添加用户</h1>
<form action="" method="post">
    <input type="hidden" name="id" value=""><br/>
    用户名<input type="text" name="username" value=""><br/>
    密码<input type="text" name="password" value=""><br/>
    邮箱<input type="text" name="email" value=""><br/>
    手机号<input type="text" name="phone" value=""><br/>
    密保问题<input type="text" name="question" value=""><br/>
    密保答案<input type="text" name="answer" value=""><br/>
    ip<input type="text" name="ip" value=""><br/>
    <input type="submit" value="添加">
</form>
</body>
</html>
