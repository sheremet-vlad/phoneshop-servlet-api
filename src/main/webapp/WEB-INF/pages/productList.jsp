<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
<html>
  <head>
    <title>Product List</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
  </head>
  <body class="product-list">
  <div>
      <jsp:include page="/WEB-INF/pages/header.jsp"/>
  </div>
    <main>
      <p>
        Welcome to Expert-Soft training!
      </p>
      <form>
        <input name="query" value="${param.query}">
        <button>Search</button>
      </form>
      <table>
        <thead>
          <tr>
            <td>Image</td>
            <td>Description
                <a href="${pageContext.servletContext.contextPath}/products?sort=description&order=asc&query=${param.query}">acs</a>
                <a href="${pageContext.servletContext.contextPath}/products?sort=description&order=dec&query=${param.query}">dec</a>
            </td>
            <td class="price">Price
                <a href="${pageContext.servletContext.contextPath}/products?sort=price&order=asc&query=${param.query}">acs</a>
                <a href="${pageContext.servletContext.contextPath}/products?sort=price&order=dec&query=${param.query}">dec</a>
            </td>
          </tr>
        </thead>
        <c:forEach var="product" items="${products}">
          <tr>
            <td>
              <a href="${pageContext.servletContext.contextPath}/products/${product.id}">
                  <img class="product-tile" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${product.imageUrl}">
              </a>
            </td>
            <td>${product.description}</td>
            <td class="price">
              <fmt:formatNumber value="${product.price}" type="currency" currencySymbol="${product.currency.symbol}"/>
            </td>
          </tr>
        </c:forEach>
      </table>
    </main>

    <div>
        <jsp:include page="/WEB-INF/pages/footer.jsp"/>
    </div>
  </body>
</html>