<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>

<tags:master pageTitle="${product.description}" isShowCart="true">
        <table>
        <tr>
            <td>
                <img class="product-tile"
                     src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
            </td>
            <td>
                <h1>${product.description}</h1>
                <p>Code: ${product.code}</p>
                <p>Price: <fmt:formatNumber value="${product.price}" type="currency"
                                            currencySymbol="${product.currency.symbol}"/></p>
                <p>Stock: ${product.stock}</p>
                <form method="post" action="${pageContext.servletContext.contextPath}/products/${product.id}">
                    Quantity: <input name="quantity" value="${not empty param.quantity ? param.quantity : 1}"
                                     class="number">
                    <button>Add to cart</button>
                    <c:if test="${not empty param.message}">
                        <p class="success">${param.message}</p>
                    </c:if>
                    <c:if test="${not empty quantityError}">
                        <p class="error">${quantityError}</p>
                    </c:if>
                </form>
            </td>
        </tr>
    </table>
    <br><br><br>
    <form method="post">
        <input name="reviewName" placeholder="name">
        <input name="reviewMark" placeholder="mark 1..5">
        <input name="reviewComment" placeholder="comment">
        <button formaction="${pageContext.servletContext.contextPath}/products/addReview/${product.id}">Review</button>
    </form>
    <br><br><br>

    <%--<c:if test="${not empty rewies}">
        <c:forEach var="review" items="rewies">
            <br><br>
            <p>Name: ${review.name}</p>
            <p>Mark: ${review.mark}</p>
            <p>Comments: ${review.comments}</p>
            <br><br>
        </c:forEach>
    </c:if>--%>

</tags:master>