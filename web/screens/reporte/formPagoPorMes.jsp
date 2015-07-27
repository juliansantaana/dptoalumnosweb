<%-- 
    Document   : formPrestPorMes
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
        
        <div class="container">
            <div class="col-md-4 col-md-offset-4">  
                <form method="get" action="ControladorReportes" class="form-horizontal">
                    <input type="hidden" name="report" value="PAGOSPORMES"/>
                    <fieldset>

                    <!-- Form Name -->
                    <legend>Pagos por Mes </legend>

                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="mes">Mes</label>  
                      <div class="col-md-4">
                      <input id="codCurso" name="mes" type="text" placeholder="" class="form-control input-md" required="">

                      </div>
                    </div>
                    
                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="anio">Año</label>  
                      <div class="col-md-4">
                      <input id="anio" name="anio" type="text" placeholder="" class="form-control input-md" required="">

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
