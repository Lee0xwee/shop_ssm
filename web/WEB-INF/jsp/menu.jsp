<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<div class="container header">
    <div class="span5">
        <div class="logo">
            <a href="./网上商城/index.htm">
                <img src="${pageContext.request.contextPath}/image/r_renleipic_01/logo.gif" alt="传智播客"/>
            </a>
        </div>
    </div>
    <div class="span9">
        <div class="headerAd">
            <img src="${pageContext.request.contextPath}/image/header.jpg" width="320" height="50" alt="正品保障"
                 title="正品保障"/>
        </div>
    </div>
    <div class="span10 last">
        <div class="topNav clearfix">
            <ul>
                <c:choose>
                    <c:when test="${sessionScope.sessionUser!=null}">
                        <li id="headerLogin" class="headerLogin" style="display: list-item;">
                            <span>${sessionScope.sessionUser.name}</span> |
                        </li>
                        <li id="headerRegister" class="headerRegister" style="display: list-item;">
                            <a href="${pageContext.request.contextPath}/order/orderList.action">我的订单</a>|
                        </li>
                        <li id="headerRegister" class="headerRegister" style="display: list-item;">
                            <a href="${pageContext.request.contextPath}/user/logout.action">退出</a>|
                        </li>
                    </c:when>

                    <c:otherwise>
                        <li id="headerLogin" class="headerLogin" style="display: list-item;">
                            <a href="${pageContext.request.contextPath}/user/toLoginPage.action">登录</a>|
                        </li>
                        <li id="headerRegister" class="headerRegister" style="display: list-item;">
                            <a href="${pageContext.request.contextPath}/user/toRegistPage.action">注册</a>|
                        </li>
                    </c:otherwise>
                </c:choose>
                <li>
                    <a>会员中心</a>
                    |
                </li>
                <li>
                    <a>购物指南</a>
                    |
                </li>
                <li>
                    <a>关于我们</a>

                </li>
            </ul>
        </div>
        <div class="cart">
            <a href="${pageContext.request.contextPath}/cart/toCartPage.action">购物车</a>
        </div>
        <div class="phone">
            客服热线:
            <strong>96008/53277764</strong>
        </div>
    </div>
    <div class="span24">
        <ul class="mainNav">
            <li>
                <a href="${pageContext.request.contextPath}/index.action">首页</a>
                |
            </li>
            <c:forEach items="${sessionScope.cList}" var="category">
                <li>
                    <a href="${pageContext.request.contextPath}/category/findByCid.action?cid=${category.cid}">${category.cname}</a>
                    |
                </li>
            </c:forEach>


        </ul>
    </div>


</div>