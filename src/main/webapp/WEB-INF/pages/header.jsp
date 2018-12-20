<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header>

    <a href="${pageContext.servletContext.contextPath}">
        <img src="${pageContext.servletContext.contextPath}/images/logo.svg"/>
        PhoneShop
    </a>

    <c:if test="${param.isShowCart == true}">
        <div class="cart-in-header">
            <a href="${pageContext.servletContext.contextPath}/cart">Cart: </a>
            ${cart.totalPrice}
        </div>
    </c:if>


    <div class="clearfix"></div>
</header>
