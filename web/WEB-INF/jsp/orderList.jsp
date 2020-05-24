<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>订单列表</title>
    <link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/cart.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.js"></script>
    <script type="text/javascript">

        function pay(oid) {

            window.location.href = "${pageContext.request.contextPath}/order/toOrderPage.action?oid=" + oid;

        }

        function ack(oid) {

            window.location.href = "${pageContext.request.contextPath}/order/ackOrder.action?oid=" + oid;

        }

    </script>
</head>
<body>

<%@ include file="menu.jsp" %>

<div class="container cart">

    <div class="span24">

        <div class="step step1" style="text-align: left;padding-left: 10px">
            <span style="font-size: 30px;font-family: 'Times New Roman'; color: red">我的订单</span>
        </div>

        <table>
            <tbody>
            <c:forEach items="${pageBean.list}" var="order">
                <tr>
                    <th colspan="5" style="font-size: 18px">
                        <font style="font-weight: bolder;color: black">订单编号：</font>${order.oid}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <font style="font-weight: bolder;color: black">订单状态：</font>
                        <c:choose>
                            <c:when test="${order.state eq 1}">
                                <input class="button_ok" type="button" style="width: 80px;height: 30px"
                                       onclick="pay(${order.oid})"
                                       value="未付款"/>
                            </c:when>
                            <c:when test="${order.state eq 2}">
                                <font style="color: red">已付款,等待发货</font>
                            </c:when>
                            <c:when test="${order.state eq 3}">
                                <input class="button_ok" type="button" style="width: 80px;height: 30px"
                                       onclick="ack(${order.oid})"
                                       value="确认收货"/>
                            </c:when>
                            <c:otherwise>
                                <font style="color: green">交易完成</font>
                            </c:otherwise>
                        </c:choose>

                    </th>
                </tr>
                <tr>
                    <th>图片</th>
                    <th>商品</th>
                    <th>价格</th>
                    <th>数量</th>
                    <th>小计</th>
                </tr>
                <c:forEach items="${order.orderitems}" var="orderItem">
                    <tr>
                        <td width="60">
                            <input type="hidden" name="id" value="22"/>
                            <img src="${pageContext.request.contextPath}/${orderItem.product.image}"/>
                        </td>
                        <td>
                            <a target="_blank">${orderItem.product.pname}</a>
                        </td>
                        <td>
                            ￥${orderItem.product.shopPrice}
                        </td>
                        <td class="quantity" width="60">
                                ${orderItem.count}
                        </td>
                        <td width="140">
                            <span class="subtotal">￥${orderItem.subtotal}</span>
                        </td>
                    </tr>
                </c:forEach>
                <tr>
                    <th style="text-align: right;padding-right: 40px;font-weight: bolder;color: red;font-size: 18px"
                        colspan="5">
                        总计：￥${order.total}</th>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div style="text-align: right">
            <font style="font-family:宋体;font-size: 18px;color: cornflowerblue">
                第${pageBean.pageNumber}页/共${pageBean.totalPage}页

                <c:choose>
                    <c:when test="${pageBean.pageNumber eq 1 }">
                        首页 上一页
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath }/order/orderList.action?cid=${cid}"
                           style="text-decoration: none">首页</a>
                        <a href="${pageContext.request.contextPath }/order/orderList.action?pageNumber=${pageBean.pageNumber-1 }"
                           style="text-decoration: none">上一页</a>
                    </c:otherwise>
                </c:choose>

                <%--
                页码列表，最多显示10个页码！当前页在5位置上，例如：1 2 3 4 (5) 6 7 8 9 10
                 --%>
                <c:choose>
                    <c:when test="${pageBean.totalPage <= 6 }">
                        <c:set var="begin" value="1"/>
                        <c:set var="end" value="${pageBean.totalPage}"/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="begin" value="${pageBean.pageNumber - 3}"/>
                        <c:set var="end" value="${pageBean.pageNumber + 2 }"/>
                        <c:choose>
                            <c:when test="${begin < 1 }">
                                <c:set var="begin" value="1"/>
                                <c:set var="end" value="6"/>
                            </c:when>
                            <c:when test="${end > pageBean.totalPage }">
                                <c:set var="begin" value="${pageBean.totalPage-5 }"/>
                                <c:set var="end" value="${pageBean.totalPage }"/>
                            </c:when>
                        </c:choose>
                    </c:otherwise>
                </c:choose>
                <c:forEach begin="${begin }" end="${end }" var="i">
                    <c:choose>
                        <c:when test="${i == pageBean.pageNumber }">${i }</c:when>
                        <c:otherwise>
                            <a href="${pageContext.request.contextPath }/order/orderList.action?pageNumber=${i}"
                               style="text-decoration: none">[${i}]</a>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>


                <c:choose>
                    <c:when test="${pageBean.pageNumber eq pageBean.totalPage }">
                        下一页 尾页
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath }/order/orderList.action?pageNumber=${pageBean.pageNumber+1 }"
                           style="text-decoration: none">下一页</a>
                        <a href="${pageContext.request.contextPath }/order/orderList.action?pageNumber=${pageBean.totalPage}"
                           style="text-decoration: none">尾页</a>
                    </c:otherwise>
                </c:choose>
            </font>
        </div>

    </div>

</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="${pageContext.request.contextPath}/image/r_renleipic_01/footer.jpg" alt="我们的优势" title="我们的优势"
                 height="52" width="950">
        </div>
    </div>
    <div class="span24">
        <ul class="bottomNav">
            <li>
                <a href="#">关于我们</a>
                |
            </li>
            <li>
                <a href="#">联系我们</a>
                |
            </li>
            <li>
                <a href="#">诚聘英才</a>
                |
            </li>
            <li>
                <a href="#">法律声明</a>
                |
            </li>
            <li>
                <a>友情链接</a>
                |
            </li>
            <li>
                <a target="_blank">支付方式</a>
                |
            </li>
            <li>
                <a target="_blank">配送方式</a>
                |
            </li>
            <li>
                <a>SHOP++官网</a>
                |
            </li>
            <li>
                <a>SHOP++论坛</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright © 2005-2015 网上商城 版权所有</div>
    </div>
</div>
</body>
</html>