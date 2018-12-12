<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Cart">
    <jsp:useBean id="cart" type="com.es.phoneshop.model.cart.Cart" scope="request"/>

    <form method="post" action="${pageContext.servletContext.contextPath}/cart">
        <c:if test="${not empty cart.cartItems}">
            <button>Update cart</button>
        </c:if>

        <c:if test="${not empty param.message}">
            <p class="success">${param.message}</p>
        </c:if>

        <table>
            <thead>
            <tr>
                <td>Image</td>
                <td>Code</td>
                <td>Description</td>
                <td class="number">Price</td>
                <td class="number">Quantity</td>
                <td></td>
            </tr>
            </thead>
            <c:forEach var="item" items="${cart.cartItems}" varStatus="status">
                <tr>
                    <td>
                        <a href="${pageContext.servletContext.contextPath}/products/${item.product.id}">
                            <img class="product-tile"
                                 src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${item.product.imageUrl}">
                        </a>
                    </td>
                    <td>${item.product.code}</td>
                    <td>${item.product.description}</td>
                    <td class="price"><fmt:formatNumber value="${item.product.price}" type="currency"
                                                        currencySymbol="${item.product.currency.symbol}"/></td>
                    <td>
                        <input name="quantity"
                               value="${not empty errors[item.product.id] ? paramValues['quantity'][status.index] : item.quantity}">
                        <input type="hidden" name="productId" value="${item.product.id}"/>
                        <c:if test="${not empty errors[item.product.id]}">
                            <p class="error">${errors[item.product.id]}</p>
                        </c:if>
                    </td>
                    <td>
                        <button formaction="${pageContext.servletContext.contextPath}/cart/delete/${item.product.id}">
                            Delete
                        </button>
                    </td>

                </tr>
            </c:forEach>
        </table>
    </form>
</tags:master>
