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
                    <td>
                        <h1>${product.description}</h1>
                        <p>Code: ${product.code}</p>
                        <p>Price: <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/></p>
                        <p>Stock: ${product.stock}</p>
                        <form>
                            <button>Submit</button>
                        </form>
                    </td>
                </tr>
            </table>
        </main>
        <div>
            <jsp:include page="/WEB-INF/pages/footer.jsp"/>
        </div>
    </body>
</html>