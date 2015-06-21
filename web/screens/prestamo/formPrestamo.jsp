<%-- 
    Document   : formPrestamo
    Created on : 21/06/2015,19:20:52
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
        <div class="col-md-6 col-md-offset-3">  
            <form method="post" action="ControladorPrestamo" class="form-horizontal">
                <c:choose>
                    <c:when test="${empty method}">
                        <input type="hidden" name="method" value="alta"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="method" value="${method}"/>
                    </c:otherwise>
                </c:choose>
                    
                <fieldset>

                <!-- Form Name -->
                <legend>Prestamos</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroLegajo">Nro Legajo</label>  
                  <div class="col-md-4">
                      <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${prestamo.nroLegajo}" id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codRecurso">Código de Recurso</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${prestamo.codRecurso}" id="codRecurso" name="codRecurso" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="fechaPrestamo">Fecha de Préstamo</label>
                  <div class="col-md-4">
                    <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${prestamo.fechaPrestamo}" id="fechaPrestamo" name="fechaPrestamo" type="text" placeholder="" class="form-control input-md datepicker">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="fechaPrevistaDevolucion">Fecha Prevista de Devolución</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${prestamo.fechaPrevistaDevolucion}" id="fechaPrevistaDevolucion" name="fechaPrevistaDevolucion" type="text" placeholder="" class="form-control input-md datepicker">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="fechaDevolucion">Fecha de Devolución</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${prestamo.fechaDevolucion}" id="fechaDevolucion" name="fechaDevolucion" type="text" placeholder="" class="form-control input-md datepicker">

                  </div>
                </div>

              
                </fieldset>
                <!-- Button -->
                <c:choose>
                <c:when test="${empty method}">
                <div class="form-group">
                  <label class="col-md-4 control-label" for="saveBtn"></label>
                  <div class="col-md-4">
                    <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Guardar</button>
                  </div>
                </div>
                </c:when>
                <c:when test="${method eq 'consulta'}">
                <div class="form-group">
                  <label class="col-md-4 control-label" for="saveBtn"></label>
                  <div class="col-md-4">
                      <a href="screens/prestamo/consultaPrestamo.jsp?method=consulta" class="btn btn-primary">Atras</a>
                  </div>
                </div>
                </c:when>
                <c:when test="${method eq 'modifAction'}">
                <div class="form-group">
                  <label class="col-md-4 control-label" for="saveBtn"></label>
                  <div class="col-md-4">
                      <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Modificar</button>
                  </div>
                </div>
                </c:when>
                <c:otherwise>
                <div class="form-group">
                  <label class="col-md-4 control-label" for="saveBtn"></label>
                  <div class="col-md-4">
                      <a href="screens/prestamo/consultaPrestamo.jsp?method=consulta" class="btn btn-primary">Atras</a>
                  </div>
                </div>
                </c:otherwise>
                </c:choose>
            </form>
        </div>
            
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
        <script>
            $(document).ready(function(){
                $('.datepicker').datepicker();
                $('.datepicker').datepicker( "option", "dateFormat", "yy-mm-dd" );
            });
        </script>
    </body>
</html>
