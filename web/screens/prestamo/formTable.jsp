<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='/templates/head.jsp'%>
    </head>
    <body>
        <%@include file='/templates/header_body.jsp'%>
        
        <div class="container">
            <h2>Seleccionar Prestamo</h2>
            <table class="table">
                <thead>
                    <tr>
                        <th>Nro Legajo</th>
                        <th>Cod Recurso</th>
                        <th>Fecha Prestamo</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${prestamos}" var="prestamo">
                        <tr>
                            <td>${prestamo.nroLegajo}</td>
                            <td>${prestamo.codRecurso}</td>
                            <td>${prestamo.fechaPrestamo}</td>
                            <td><a href="ControladorPrestamo?method=${method}&id=${prestamo.id}" class="btn btn-primary">Seleccionar</a></td>
                        </tr>
                    </c:forEach>
                <tr>
                </tbody>
            </table>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
