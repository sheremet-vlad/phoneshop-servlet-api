<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="product list">
    <jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
    <p>
        Welcome to Expert-Soft training!
    </p>
    <form>
        <input name="query" value="${param.query}">
        <button>Search</button>
    </form>
    <a href="ffbbtbeeeb">Ссылка ошибка</a>
    <table>
        <thead>
        <tr>
            <td>Image</td>
            <td>
                Description
                <tags:sortLink text="asc" sort="description" order="asc" query="${param.query}"/>
                <tags:sortLink text="dec" sort="description" order="dec" query="${param.query}"/>
            </td>
            <td class="price">
                Price
                <tags:sortLink text="asc" sort="price" order="asc" query="${param.query}"/>
                <tags:sortLink text="dec" sort="price" order="dec" query="${param.query}"/>
            </td>
        </tr>
        </thead>
        <c:forEach var="product" items="${products}">
            <tr>
                <td>
                    <a href="${pageContext.servletContext.contextPath}/products/${product.id}" methods="POST">
                        <img class="product-tile"
                             src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                    </a>
                </td>
                <td>${product.description}</td>
                <td class="price">
                    <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
                </td>
            </tr>
        </c:forEach>
    </table>
</tags:master>