<%--
  Created by IntelliJ IDEA.
  User: 22430
  Date: 2019/8/7
  Time: 15:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改用户信息</title>
</head>
<body>
<h1>修改用户信息</h1>
<form action="" method="post">
    <input type="hidden" name="id" value="${userinfo.id}"><br/>
    用户名<input type="text" name="username" value="${userinfo.username}"><br/>
    密码<input type="text" name="password" value="${userinfo.password}"><br/>
    邮箱<input type="text" name="email" value="${userinfo.email}"><br/>
    手机号<input type="text" name="phone" value="${userinfo.phone}"><br/>
    密保问题<input type="text" name="question" value="${userinfo.question}"><br/>
    密保答案<input type="text" name="answer" value="${userinfo.answer}"><br/>
    ip<input type="text" name="ip" value="${userinfo.ip}"><br/>
    <input type="submit" value="修改">
</form>
</body>
</html>
