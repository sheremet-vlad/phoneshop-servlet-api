<%@ tag %>
<%@ attribute name="sort" required="true" type="java.lang.String" %>
<%@ attribute name="order" required="true" type="java.lang.String" %>
<%@ attribute name="query" required="false" type="java.lang.String" %>
<div id="searchResultTitle" class="search-result__title">
    <jsp:body />
    <span class="search-result__title--count">
        (${totalResult})
    </span>
</div>
