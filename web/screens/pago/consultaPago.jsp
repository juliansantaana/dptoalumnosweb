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
                <form method="post" action="ControladorPago" class="form-horizontal">
                    <input type="hidden" name="method" value="${param.method}"/>
                    <fieldset>

                    <!-- Form Name -->
                    <legend>Curso</legend>

                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="pagoNroLegajo">Nro de Legajo</label>  
                      <div class="col-md-4">
                      <input id="nroLegajo" name="pagoNroLegajo" type="text" placeholder="" class="form-control input-md" required="">
                      </div>
                    </div>
                    
                    <!-- Text input-->
                    <div class="form-group">
                      <label class="col-md-4 control-label" for="pagoCodCurso">Código de Curso</label>  
                      <div class="col-md-4">
                      <input id="nroLegajo" name="pagoCodCurso" type="text" placeholder="" class="form-control input-md" required="">
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