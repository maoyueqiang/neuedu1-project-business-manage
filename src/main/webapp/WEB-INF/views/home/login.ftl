<!DOCTYPE html>
<html>
<head>
    <title>卖家后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1">


    <!--可无视-->
    <link rel="stylesheet" href="/static/css/login/bootstrap.min.css">

    <!--主要样式-->
    <link type="text/css" href="/static/css/login/loginstyle.css" rel="stylesheet" />

    <script>
        if(self!=top)
        {
            parent.location.href='index.html';
        }
        function CheckLogin(obj){
            if(obj.username.value=='')
            {
                alert('请输入用户名');
                obj.username.focus();
                return false;
            }
            if(obj.password.value=='')
            {
                alert('请输入登录密码');
                obj.password.focus();
                return false;
            }
            return true;
        }
    </script>


</head>
<body>

<div class="container" align="center">
    <div class="col-md-6" style="margin-top:110px;">
        <div class="inset">
            <form name="login" id="login" method="post" action="" onSubmit="return CheckLogin(document.login);">
                <input type="hidden" name="enews" value="login">
                <div>
                    <h2>卖家后台管理系统</h2>
                    <span style="text-align: left;text-indent: 0.4em;"><label>用户名</label></span>
                    <span><input type="text" name="username" class="textbox" ></span>
                </div>
                <div>
                    <span style="text-align: left;text-indent: 0.4em;"><label>密码</label></span>
                    <span><input  name="password" type="password" class="password"></span>
                </div>
                <div class="sign">
                    <input type="reset"  class="submit" value="重置"/>
                    <input type="submit" value="登录" class="submit" />
                </div>
            </form>
        </div>
    </div>
</div>

<script>
    if(document.login.equestion.value==0)
    {
        showanswer.style.display='none';
    }
</script>

</body>
</html>