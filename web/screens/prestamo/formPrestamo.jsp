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
        <%@page import="school.Modelo" %>
        
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
                      <c:choose>
                        <c:when test="${empty method}">
                            <!-- <input value="${prestamo.nroLegajo}" id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required=""> -->
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
                        </c:when>
                        <c:otherwise>
                            <input readonly="readonly" value="${prestamo.nroLegajo}" id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">
                        </c:otherwise>
                      </c:choose>
                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codRecurso">Código de Recurso</label>  
                  <div class="col-md-4">
                      <c:choose>
                        <c:when test="${empty method}">
                            <!-- <input value="${prestamo.codRecurso}" id="codRecurso" name="codRecurso" type="text" placeholder="" class="form-control input-md"> -->
                            <select name="codRecurso" id="codRecurso" class="form-control" required="">
<%   
    Modelo m1 = new Modelo();
    m1.cargaArrayRecurso();
    
    for (int i=0; i< m1.getCantRecursos(); i++){
%>
<option value="<%= m1.getRecurso(i).getCodRecurso() %>"><%= m1.getRecurso(i).getNombre() %></option>
<%
}
%>
    
                            </select>
                        </c:when>
                        <c:otherwise>
                            <input readonly="readonly" value="${prestamo.codRecurso}" id="codRecurso" name="codRecurso" type="text" placeholder="" class="form-control input-md">
                        </c:otherwise>
                      </c:choose>
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
                $( ".datepicker" ).datepicker({
                    dateFormat : 'yy-mm-dd'
                });
            });
        </script>
    </body>
</html>
