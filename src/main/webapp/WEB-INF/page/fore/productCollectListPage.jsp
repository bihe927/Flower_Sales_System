<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="include/header.jsp" %>
<script src="${pageContext.request.contextPath}/res/js/fore/fore_productList.js"></script>
<link href="${pageContext.request.contextPath}/res/css/fore/fore_productList.css" rel="stylesheet">
<body>
<title><c:choose><c:when test="${requestScope.searchValue != null}">${requestScope.searchValue}</c:when>
    <c:otherwise><c:choose><c:when
            test="${requestScope.productList != null && fn:length(requestScope.productList)>0}">${requestScope.productList[0].product_category.category_name}</c:when><c:otherwise>没找到相关商品</c:otherwise></c:choose></c:otherwise></c:choose>-爱花居-</title>
<nav>
    <%@ include file="include/navigator.jsp" %>
    <div class="header">
        <div id="mallLogo">
            <a href="${pageContext.request.contextPath}"><img
                    src="${pageContext.request.contextPath}/res/images/fore/WebsiteImage/logoA.png"></a>
        </div>
        <div class="shopSearchHeader">
            <form action="${pageContext.request.contextPath}/product" method="get">
                <div class="shopSearchInput">
                    <input type="text" class="searchInput" name="product_name" placeholder="搜索 商品/种类/花语"
                           value="${requestScope.searchValue}" maxlength="50">
                    <input type="submit" value="搜 索" class="searchBtn">
                </div>
            </form>
            <ul>
                <c:forEach items="${requestScope.categoryList}" var="category" varStatus="i">
                    <li>
                        <a href="${pageContext.request.contextPath}/product?category_id=${category.category_id}">${category.category_name}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>
    </div>
</nav>
<div class="context">
    <c:choose>
        <c:when test="${requestScope.productList != null && fn:length(requestScope.productList)>0}">
            <div class="context_menu"></div>
            <div class="context_main rounded">
                <c:forEach items="${requestScope.productList}" var="product">
                    <div class="context_productStyle rounded">
                        <div class="context_product">
                            <a href="${pageContext.request.contextPath}/product/${product.product_id}"
                               target="_blank"><img class="context_product_imgMain"
                                                    src="${pageContext.request.contextPath}/res/images/item/productSinglePicture/${product.singleProductImageList[0].productImage_src}"/></a>
                            <p class="context_product_price"><span>¥</span>${product.product_sale_price} <a href="/tmall/deleteHeart/${product.product_id}" target="_blank" style="float: right; font-size: 13px" >取消收藏</a></p>
                            <p class="context_product_name"><a href="/tmall/product/${product.product_id}"
                                                               target="_blank">${product.product_name}</a></p>
                            <p class="context_product_shop"><span>贤趣${product.product_category.category_name}旗舰店</span>
                            </p>
                            <p class="context_product_status"></p>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:when>
        <c:otherwise>
            <div class="error">
                <h2>喵~您收藏的商品为空 再去看看吧</h2>
                <h3>建议您：</h3>
                <ol>
                    <li>看看输入的文字是否有误</li>
                    <li>调整关键词，如“全铜花洒套件”改成“花洒”或“花洒 套件”</li>
                    <li>
                        <form action="${pageContext.request.contextPath}/product" method="get">
                            <input title="查询产品" type="text" class="errorInput" name="product_name"
                                   value="${requestScope.searchValue}">
                            <input type="submit" value="去淘宝搜索" class="errorBtn">
                        </form>
                    </li>
                </ol>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<%@ include file="include/footer_two.jsp" %>
<%@ include file="include/footer.jsp" %>
</body>