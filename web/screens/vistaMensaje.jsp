<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='/templates/head.jsp'%>
    </head>
    <body>
        <%@include file='/templates/header_body.jsp'%>
        
        <div class="container">
            <c:choose>
            <c:when test="${empty mensajeTitulo}">
            <h2>Mensaje: </h2>
            </c:when>
            <c:otherwise>
            <h2>${mensajeTitulo}</h2>
            </c:otherwise>
            </c:choose>
            
            <c:choose>
                <c:when test="${estado eq 'SUCCESS'}">
                    <div class="alert alert-success" role="alert">${mensaje}</div>
                </c:when>
                <c:when test="${estado eq 'ERROR'}">
                    <div class="alert alert-danger" role="alert">${mensaje}</div>
                </c:when>
                <c:otherwise>
                    <div class="alert alert-info" role="alert">${mensaje}</div>
                </c:otherwise>
            </c:choose>
            
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
