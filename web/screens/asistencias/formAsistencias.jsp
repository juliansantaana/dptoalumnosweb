<%-- 
    Document   : altaAlumnos
    Created on : Jun 17, 2015, 20:38:42 PM
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
        <div class="col-md-6 col-md-offset-3">  
            <form method="post" action="ControladorAsistencias" class="form-horizontal">
                <input type="hidden" name="method" value="buscarListado"/>
                <fieldset>

                <!-- Form Name -->
                <legend>Asistencias</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codAsistencias">Código de Curso</label>  
                  <div class="col-md-4">
                    <!-- <input id="codCurso" name="codCurso" type="text" placeholder="" class="form-control input-md" required="">-->
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
                  
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codAsistencias">Nro. Clase</label>  
                  <div class="col-md-4">
                    <input id="nroClase" name="nroClase" type="text" placeholder="" class="form-control input-md" required="">
                  </div>
                </div>
                  
                </fieldset>
                <!-- Button -->
                
                <div class="form-group">
                  <label class="col-md-4 control-label" for="searchBtn"></label>
                  <div class="col-md-4">
                    <button type="submit" id="searchBtn" name="searchBtn" class="btn btn-primary">Buscar Listado</button>
                  </div>
                </div>
            </form>
        </div>
            
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
