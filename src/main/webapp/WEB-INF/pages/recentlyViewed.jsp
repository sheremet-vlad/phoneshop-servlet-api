<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="viewed-product">
    Recently viewed
    <table>
        <tr>
            <c:forEach var="viewedProduct" items="${viewedProducts}">
                <td>
                    <p><img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${viewedProduct.imageUrl}"></p>
                    <a href="${pageContext.servletContext.contextPath}/products/${viewedProduct.id}">${viewedProduct.description}</a>
                    <p><fmt:formatNumber value="${viewedProduct.price}" type="currency" currencySymbol="${viewedProduct.currency.symbol}"/></p>
                </td>
            </c:forEach>
        </tr>
    </table>
</div>

