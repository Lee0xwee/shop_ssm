<%@ page contentType="text/html;charset=UTF-8" pageEncoding="utf-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>商品列表</title>
    <link href="${pageContext.request.contextPath}/css/slider.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/product.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<%@ include file="menu.jsp" %>
<div class="container productList">
    <div class="span6">
        <div class="hotProductCategory">

            <c:forEach items="${cList}" var="c">
                <dl>

                    <dt>
                        <a href="${pageContext.request.contextPath}/category/findByCid.action?cid=${c.cid}">${c.cname}</a>
                    </dt>
                    <c:forEach items="${c.categoryseconds}" var="cs">
                        <dd>
                            <a href="${pageContext.request.contextPath}/category/findByCsid.action?csid=${cs.csid}">${cs.csname}</a>
                        </dd>
                    </c:forEach>
                </dl>
            </c:forEach>
        </div>
    </div>
    <div class="span18 last">

        <form id="productForm" action="${pageContext.request.contextPath}/image/蔬菜 - Powered By Mango Team.htm"
              method="get">
            <input type="hidden" id="brandId" name="brandId" value="">
            <input type="hidden" id="promotionId" name="promotionId" value="">
            <input type="hidden" id="orderType" name="orderType" value="">
            <input type="hidden" id="pageNumber" name="pageNumber" value="1">
            <input type="hidden" id="pageSize" name="pageSize" value="20">

            <div id="result" class="result table clearfix">
                <ul>
                    <c:forEach items="${pageBean.list}" var="product">
                        <li>
                            <a href="${pageContext.request.contextPath}/product/findByPid.action?pid=${product.pid}">
                                <img src="${pageContext.request.contextPath}/${product.image}"
                                     width="170" height="170" style="display: inline-block;">

                                <span style='color:green'>${product.pname}</span>
                                <span class="price">商城价： ￥${product.shopPrice}/份</span>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <div style="text-align: right">
                <c:choose>
                    <c:when test="${cid!=null}">
                        <font style="font-family:宋体;font-size: 18px;color: cornflowerblue">
                            第${pageBean.pageNumber}页/共${pageBean.totalPage}页

                            <c:choose>
                                <c:when test="${pageBean.pageNumber eq 1 }">
                                    首页 上一页
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath }/category/findByCid.action?cid=${cid}"
                                       style="text-decoration: none">首页</a>
                                    <a href="${pageContext.request.contextPath }/category/findByCid.action?cid=${cid}&pageNumber=${pageBean.pageNumber-1 }"
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
                                        <a href="${pageContext.request.contextPath }/category/findByCid.action?cid=${cid}&pageNumber=${i}"
                                           style="text-decoration: none">[${i}]</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>


                            <c:choose>
                                <c:when test="${pageBean.pageNumber eq pageBean.totalPage }">
                                    下一页 尾页
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath }/category/findByCid.action?cid=${cid}&pageNumber=${pageBean.pageNumber+1 }"
                                       style="text-decoration: none">下一页</a>
                                    <a href="${pageContext.request.contextPath }/category/findByCid.action?cid=${cid}&pageNumber=${pageBean.totalPage}"
                                       style="text-decoration: none">尾页</a>
                                </c:otherwise>
                            </c:choose>
                        </font>
                    </c:when>

                    <c:when test="${csid!=null}">
                        <font style="font-family:宋体;font-size: 18px;color: cornflowerblue">
                            第${pageBean.pageNumber}页/共${pageBean.totalPage}页

                            <c:choose>
                                <c:when test="${pageBean.pageNumber eq 1 }">
                                    首页 上一页
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath }/category/findByCsid.action?csid=${csid}"
                                       style="text-decoration: none">首页</a>
                                    <a href="${pageContext.request.contextPath }/category/findByCsid.action?csid=${csid}&pageNumber=${pageBean.pageNumber-1 }"
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
                                        <a href="${pageContext.request.contextPath }/category/findByCsid.action?csid=${csid}&pageNumber=${i}"
                                           style="text-decoration: none">[${i}]</a>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>


                            <c:choose>
                                <c:when test="${pageBean.pageNumber eq pageBean.totalPage }">
                                    下一页 尾页
                                </c:when>
                                <c:otherwise>
                                    <a href="${pageContext.request.contextPath }/category/findByCsid.action?csid=${csid}&pageNumber=${pageBean.pageNumber+1 }"
                                       style="text-decoration: none">下一页</a>
                                    <a href="${pageContext.request.contextPath }/category/findByCsid.action?csid=${csid}&pageNumber=${pageBean.totalPage}"
                                       style="text-decoration: none">尾页</a>
                                </c:otherwise>
                            </c:choose>
                        </font>
                    </c:when>
                </c:choose>
            </div>
        </form>
    </div>
</div>
<div class="container footer">
    <div class="span24">
        <div class="footerAd">
            <img src="${pageContext.request.contextPath}/image/footer.jpg" width="950" height="52" alt="我们的优势"
                 title="我们的优势">
        </div>
    </div>
    <div class="span24">
        <ul class="bottomNav">
            <li>
                <a>关于我们</a>
                |
            </li>
            <li>
                <a>联系我们</a>
                |
            </li>
            <li>
                <a>诚聘英才</a>
                |
            </li>
            <li>
                <a>法律声明</a>
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
                <a>官网</a>
                |
            </li>
            <li>
                <a>论坛</a>

            </li>
        </ul>
    </div>
    <div class="span24">
        <div class="copyright">Copyright©2005-2015 网上商城 版权所有</div>
    </div>
</div>
</body>
</html>
