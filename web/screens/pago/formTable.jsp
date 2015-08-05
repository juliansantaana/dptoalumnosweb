<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='/templates/head.jsp'%>
    </head>
    <body>
        <%@include file='/templates/header_body.jsp'%>
        
        <div class="container">
            <h2>Seleccionar Pago</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Nro Legajo</th>
                        <th>Cod Recurso</th>
                        <th>Fecha Pago</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${pagos}" var="pago">
                        <tr>
                            <td>${pago.pagoNroLegajo}</td>
                            <td>${pago.pagoCodCurso}</td>
                            <td>${pago.pagoFecha}</td>
                            <td><a href="ControladorPago?method=${method}&id=${pago.id}" class="btn btn-primary">Seleccionar</a></td>
                        </tr>
                    </c:forEach>
                <tr>
                </tbody>
            </table>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
