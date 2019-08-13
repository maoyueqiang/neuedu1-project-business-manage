<html>
<#include  "common/header.ftl">

<body>
<div id="wrapper" class="toggled">

    <#--边栏sidebar-->
    <#include "common/nav.ftl">

    <#--主要内容content-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-bordered table-condensed">
                        <thead>
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
                        </thead>
                        <tbody>

                        <!--遍历集合，集合和每一项，类似于forEach中的items和var-->
                        <#list userlist as user>
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.username}</td>
                            <td>${user.password}</td>
                            <td>${user.email}</td>
                            <td>${user.phone}</td>
                            <td>${user.question}</td>
                            <td>${user.answer}</td>
                            <td>${user.role}</td>
                            <td>${user.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${user.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${user.ip}</td>
                            <td><a href="/business_manage/user/userinfo/update/${user.id}">修改</a>
                                <a href="/business_manage/user/userinfo/delete/${user.id}">删除</a>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

</div>
</body>
</html>