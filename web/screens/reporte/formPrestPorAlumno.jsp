<%-- 
    Document   : formPrestPorAlumno
    Created on : Jun 18, 2015, 8:24:42 PM
    Author     : lucas
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
                <form method="get" action="ControladorReportes" class="form-horizontal">
                    <input type="hidden" name="report" value="PRESTPORALUMNO"/>
                    <fieldset>

                    <!-- Form Name -->
                    <legend>Prestamos por alumno</legend>

                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="mes">Nro Legajo</label>  
                      <div class="col-md-6">
                      <!--<input id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">-->
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
                        <button type="submit" class="btn btn-primary">Generar Reporte</button>
                      </div>
                    </div>
                </form>
            </div>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
