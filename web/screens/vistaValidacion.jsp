<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='/templates/head.jsp'%>
    </head>
    <body>
        <%@include file='/templates/header_body.jsp'%>
        
        <div class="container">
            <div class="alert alert-danger" role="alert">
                Se encontraron errores al intentar validar los datos: <br />
                <ul>
                    <c:forEach items="${mensajes}" var="msj">
                        <li>${msj}</li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
