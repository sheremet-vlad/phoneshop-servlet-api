<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="product" type="com.es.phoneshop.model.product.Product" scope="request"/>
<html>
    <head>
        <title>Product ${product.description}</title>
        <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
    </head>
    <body class="product-list">
        <div>
            <jsp:include page="/WEB-INF/pages/header.jsp"/>
        </div>
        <main>
            <table>
                <tr>
                    <td>
                        <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
                    </td>
                    <td>${product.description}</td>
                    <td class="price">
                        <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
                    </td>
                </tr>
            </table>
        </main>
        <div>
            <jsp:include page="/WEB-INF/pages/footer.jsp"/>
        </div>
    </body>
</html>