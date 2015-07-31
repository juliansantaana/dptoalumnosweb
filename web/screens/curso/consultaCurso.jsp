<%-- 
    Document   : consultaCurso
    Created on : 17/06/2015, 20:02:52
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
        <%@page import="school.Modelo" %>
        
        <div class="container">
            <div class="col-md-4 col-md-offset-4">  
                <form method="post" action="ControladorCurso" class="form-horizontal">
                    <input type="hidden" name="method" value="${param.method}"/>
                    <fieldset>

                    <!-- Form Name -->
                    <legend>Curso</legend>

                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-6 control-label" for="codCurso">Código de Curso</label>  
                      <div class="col-md-6">
                      <!--<input id="nroLegajo" name="codCurso" type="text" placeholder="" class="form-control input-md" required="">-->
                      <select name="codCurso" id="codCurso" class="form-control" required="">
<%   
    Modelo m = new Modelo();
    m.cargaArrayCurso();
    
    for (int i=0; i< m.getCantCursos(); i++){
%>
<option value="<%= m.getCurso(i).getCursoCod() %>"><%= m.getCurso(i).getCursoNombre()%></option>
<%
}
%>
    
                      </select>
                      </div>
                    </div>

                    </fieldset>
                    <!-- Button -->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="saveBtn"></label>
                      <div class="col-md-4">
                        <c:choose>
                        <c:when test="${param.method eq 'consulta'}">
                        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Consultar</button>
                        </c:when>
                        <c:when test="${param.method eq 'baja'}">
                        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Dar de Baja</button>
                        </c:when>
                        <c:when test="${param.method eq 'modif'}">
                        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Modificar</button>
                        </c:when>
                        </c:choose>
                      </div>
                    </div>
                </form>
            </div>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
