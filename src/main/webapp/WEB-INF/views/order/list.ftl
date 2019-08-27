<html>
<#include "common/header.ftl">

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
                            <th>订单id</th>
                            <th>用户id</th>
                            <th>订单编号</th>
                            <th>地址id</th>
                            <th>金额</th>
                            <th>支付类型</th>
                            <th>邮费</th>
                            <th>订单状态</th>
                            <th>支付时间</th>
                            <th>发货时间</th>
                            <th>交易完成时间</th>
                            <th>交易关闭时间</th>
                            <th>订单创建时间</th>
                            <th>订单更新时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>

                        <#list ordertList as order>
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.userId}</td>
                            <td>${order.orderNo}</td>
                            <td>${order.shippingId}</td>
                            <td>${order.payment}</td>
                            <td>${order.paymentType}</td>
                            <td>${order.postage}</td>
                            <td>${order.status}</td>
                            <td>${order.paymentTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${order.sendTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${order.endTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${order.closeTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${order.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${order.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                            <td>
                                <a href="/business_manage/user/order/details/${order.orderNo}">详情</a>
                            </td>
                            <td>
                                <#if orderDTO.getOrderStatusEnum().message == "新订单">
                                    <a href="/sell/seller/order/cancel?orderId=${orderDTO.orderId}">取消</a>
                                </#if>
                            </td>
                        </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>

            <#--分页-->
                <#if pages lte 10>
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/business_manage/user/order/find/${currentPage - 1}/${size}">上一页</a></li>
                        </#if>

                        <#list 1..pages as index>
                            <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                            </#if>
                        </#list>

                        <#if currentPage gte pages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/business_manage/user/order/find/${currentPage + 1}/${size}">下一页</a></li>
                        </#if>
                        </ul>
                    </div>
                <#else>
                    <div class="col-md-12 column">
                        <ul class="pagination pull-right">
                        <#if currentPage lte 1>
                            <li class="disabled"><a href="#">上一页</a></li>
                        <#else>
                            <li><a href="/business_manage/user/order/find/${currentPage - 1}/${size}">上一页</a></li>
                        </#if>

                        <#if currentPage lt 7>
                            <#list 1..7 as index>
                                <#if currentPage == index>
                                <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                                </#if>
                            </#list>
                            <li class="disabled"><a href="#">…</a></li>
                            <#list (pages-2)..pages as index>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                            </#list>
                        <#elseif currentPage gt (pages-6)>
                            <#list 1..3 as index>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                            </#list>
                            <li class="disabled"><a href="#">…</a></li>
                            <#list (pages-6)..pages as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                                </#if>
                            </#list>
                        <#else>
                            <#list 1..3 as index>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                            </#list>
                            <li class="disabled"><a href="#">…</a></li>
                            <#list (currentPage-2)..(currentPage+2) as index>
                                <#if currentPage == index>
                                    <li class="disabled"><a href="#">${index}</a></li>
                                <#else>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                                </#if>
                            </#list>
                            <li class="disabled"><a href="#">…</a></li>
                            <#list (pages-2)..pages as index>
                                <li><a href="/business_manage/user/order/find/${index}/${size}">${index}</a></li>
                            </#list>
                        </#if>

                        <#if currentPage gte pages>
                            <li class="disabled"><a href="#">下一页</a></li>
                        <#else>
                            <li><a href="/business_manage/user/order/find/${currentPage + 1}/${size}">下一页</a></li>
                        </#if>
                        </ul>
                    </div>


                </#if>
            </div>
        </div>
    </div>

</div>

<#--弹窗-->
<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单
            </div>
            <div class="modal-footer">
                <button onclick="javascript:document.getElementById('notice').pause()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button onclick="location.reload()" type="button" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>
    </div>
</div>

<#--播放音乐-->
<#--<audio id="notice" loop="loop">-->
<#--<source src="/sell/mp3/song.mp3" type="audio/mpeg" />-->
<#--</audio>-->

<#--<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>-->
<#--<script src="https://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>-->
<#--<script>-->
<#--var websocket = null;-->
<#--if('WebSocket' in window) {-->
<#--websocket = new WebSocket('ws://sell.natapp4.cc/sell/webSocket');-->
<#--}else {-->
<#--alert('该浏览器不支持websocket!');-->
<#--}-->

<#--websocket.onopen = function (event) {-->
<#--console.log('建立连接');-->
<#--}-->

<#--websocket.onclose = function (event) {-->
<#--console.log('连接关闭');-->
<#--}-->

<#--websocket.onmessage = function (event) {-->
<#--console.log('收到消息:' + event.data)-->
<#--//弹窗提醒, 播放音乐-->
<#--$('#myModal').modal('show');-->

<#--document.getElementById('notice').play();-->
<#--}-->

<#--websocket.onerror = function () {-->
<#--alert('websocket通信发生错误！');-->
<#--}-->

<#--window.onbeforeunload = function () {-->
<#--websocket.close();-->
<#--}-->

<#--</script>-->

</body>
</html>