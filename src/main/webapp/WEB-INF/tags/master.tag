<%@attribute name="pageTitle" type="java.lang.String" required="true" %>
<%@attribute name="pageClass" type="java.lang.String" required="false" %>

<html>
<head>
    <title>${pageTitle}</title>
    <link href='http://fonts.googleapis.com/css?family=Lobster+Two' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/styles/main.css">
</head>
<body class="${pageClass}">
<div>
    <jsp:include page="/WEB-INF/pages/header.jsp"/>
</div>
<main>
    <jsp:doBody/>
</main>

<jsp:include page="/WEB-INF/pages/recentlyViewed.jsp"/>

<div>
    <jsp:include page="/WEB-INF/pages/footer.jsp"/>
</div>
</body>
</html>