<%-- 
    Document   : consultaAlumno
    Created on : 14/06/2015, 15:02:52
    Author     : Lucas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file='/templates/head.jsp'%>
    </head>
    <body>
        <%@include file='/templates/header_body.jsp'%>
        
        <div class="container">
        <div class="col-md-4 col-md-offset-0">  
            <form method="post" action="ControladorAlumno" class="form-horizontal">
                <input type="hidden" name="method" value="consulta"/>
                <fieldset>

                <!-- Form Name -->
                <legend>Alumnos</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroLegajo">Nro Legajo</label>  
                  <div class="col-md-4">
                  <input id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>
                
                </fieldset>
                <!-- Button -->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="saveBtn"></label>
                  <div class="col-md-4">
                    <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Consultar</button>
                  </div>
                </div>
            </form>
        </div>
        <div class="col-md-8"> 
            <fieldset>

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
            </fieldset>
        </div>
            
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
