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
    
        <%@include file='/templates/header_body.jsp'%>
        <%@page import="school.Modelo" %>
        
        <div class="container">
            <div class="col-md-4 col-md-offset-4">  
                <form method="post" action="ControladorAlumno" class="form-horizontal">
                    <input type="hidden" name="method" value="${param.method}"/>
                    <fieldset>

                    <!-- Form Name -->
                    <legend>Alumnos</legend>

                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-6 control-label" for="nroLegajo">Nro Legajo</label>  
                      <div class="col-md-6">
                      <!--
                          <input id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">
                      -->
                      <select name="nroLegajo" id="nroLegajo" class="form-control" required="">
<%   
    Modelo m = new Modelo();
    m.cargaArrayAlumno(null, null, null, null);
    
    for (int i=0; i< m.getArrayAlumnos().size(); i++){
%>
    <option value="<%= m.getAlumno(i).getNroLegajo() %>"><%= m.getAlumno(i).getApellido() + ", "  + m.getAlumno(i).getNombre()%></option>
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
    
</html>
