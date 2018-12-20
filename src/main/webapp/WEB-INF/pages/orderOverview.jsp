<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Order overview" isShowCart="false">
    <jsp:useBean id="order" type="com.es.phoneshop.model.order.Order" scope="request"/>
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
        <c:forEach var="item" items="${order.cartItems}" varStatus="status">
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
        <tr>
            <td></td>
            <td></td>
            <td class="price">Total price:</td>
            <td><fmt:formatNumber value="${order.totalPrice}" type="currency"
                                  currencySymbol="${order.cartItems.get(0).product.currency.symbol}"/></td>
        </tr>
    </table>
    <p>Name: ${order.name}</p>
    <p>Last name: ${order.lastName}</p>
    <p>Delivery address: ${order.deliveryAddress}</p>
    <p>Phone: ${order.phone}</p>
    <p>Delivery mode: courier</p>
    <p>Delivery date: tomorrow</p>
    <p>Payment method: money to courier</p>
    <p>Delivery costs: 3</p>
</tags:master>
