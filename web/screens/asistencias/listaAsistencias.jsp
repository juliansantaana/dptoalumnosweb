<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='/templates/head.jsp'%>
    </head>
    <body>
        <%@include file='/templates/header_body.jsp'%>
        
        <div class="container">
            <form method="post" action="ControladorAsistencias">
                <input type="hidden" name="method" value="guardarListado"/>
                <input type="hidden" name="codCurso" value="${codCurso}"/>
                <input type="hidden" name="nroClase" value="${nroClase}"/>
                
                <table class="table table-condensed">
                    <thead>
                        <tr>
                            <th>Nombre</td>
                            <th>Presente</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${asistencias}" varStatus="loop" var="ast">
                            <tr>
                                <td>${ast.nombreApellido}</td>
                                <td><input name="asist_${ast.nroLegajo}" type="checkbox" 
                                <c:if test="${ast.asistencia eq 1}">checked="checked"</c:if>
                                /></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-primary pull-right">Guardar</button>
            </form>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
