<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='/templates/head.jsp'%>
    </head>
    <body>
        <%@include file='/templates/header_body.jsp'%>
        
        <div class="container">
            <h2>${title}<h2>
            <c:forEach items="${tables}" var="table">
                <h4>${table.getName()}</h4>
                <table class="table">
                    <thead>
                        <tr>
                            <c:forEach items="${table.getColumns()}" var="column">
                                <th>${column.getName()}</th>
                            </c:forEach>   
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${table.getRows()}" var="row">
                            <tr>
                                <c:forEach items="${row.getValues()}" var="value">
                                    <td>${value}</td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    <tr>
                    </tbody>
                </table>
            </c:forEach>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
