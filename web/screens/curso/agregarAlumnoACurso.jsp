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
        
        <div class="container">
            <div class="col-md-4 col-md-offset-4">  
                <form method="post" action="ControladorCurso" class="form-horizontal">
                    <input type="hidden" name="method" value="agregarAlumno"/>
                    <fieldset>

                    <!-- Form Name -->
                    <legend>Agregar Alumno a Curso</legend>

                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="codCurso">Código de Curso</label>  
                      <div class="col-md-4">
                        <input id="codCurso" name="codCurso" type="text" placeholder="" class="form-control input-md" required="">
                      </div>
                    </div>
                    
                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="codCurso">Nro Legajo</label>  
                      <div class="col-md-4">
                        <input id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">
                      </div>
                    </div>

                    </fieldset>
                    <!-- Button -->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="saveBtn"></label>
                      <div class="col-md-4">
                        <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Agregar</button>
                      </div>
                    </div>
                </form>
            </div>
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
