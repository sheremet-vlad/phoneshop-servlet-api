<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Cart" isShowCart="false">
    <jsp:useBean id="cart" type="com.es.phoneshop.model.cart.Cart" scope="request"/>
    <form method="post" action="${pageContext.servletContext.contextPath}/checkout">
        <table>
            <thead>
            <tr>
                <td>Image</td>
                <td>Code</td>
                <td>Description</td>
                <td class="number">Price</td>
                <td class="number">Quantity</td>
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
                        <p class="price">${item.quantity}</p>
                    </td>
                </tr>
            </c:forEach>
        </table>
        <br><br>
        <input name="name" placeholder="Name"/>
        <br><br>
        <input name="deliveryAddress" placeholder="Delivery address">
        <br><br>
        <input name="phone" placeholder="Phone">
        <button>Place order</button>
    </form>
</tags:master>
