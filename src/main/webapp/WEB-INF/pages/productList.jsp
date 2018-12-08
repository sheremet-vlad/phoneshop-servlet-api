<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<tags:master pageTitle="product list">
<jsp:useBean id="products" type="java.util.ArrayList" scope="request"/>
    <main>
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
              <a href="<c:url value="">
                          <c:param name="sort" value="description"/>
                          <c:param name="order" value="asc"/>
                          <c:param name="query" value="${param.query}"/>
                          </c:url>">asc
              </a>
              <a href="<c:url value="">
                          <c:param name="sort" value="description"/>
                          <c:param name="order" value="dec"/>
                          <c:param name="query" value="${param.query}"/>
                          </c:url>">dec
              </a>
            </td>
            <td class="price">Price
              <a href="<c:url value="">
                          <c:param name="sort" value="price"/>
                          <c:param name="order" value="asc"/>
                          <c:param name="query" value="${param.query}"/>
                          </c:url>">asc
              </a>
              <a href="<c:url value="">
                          <c:param name="sort" value="price"/>
                          <c:param name="order" value="dec"/>
                          <c:param name="query" value="${param.query}"/>
                          </c:url>">dec
              </a>
            </td>
          </tr>
        </thead>
        <c:forEach var="product" items="${products}">
          <tr>
            <td>
              <a href="${pageContext.servletContext.contextPath}/products/${product.id}" methods="POST">
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
</tags:master>