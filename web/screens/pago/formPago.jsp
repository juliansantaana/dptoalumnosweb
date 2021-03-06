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
            <form method="post" action="ControladorPago" class="form-horizontal">
                <c:choose>
                    <c:when test="${empty method}">
                        <input type="hidden" name="method" value="alta"/>
                    </c:when>
                    <c:otherwise>
                        <input type="hidden" name="method" value="${method}"/>
                    </c:otherwise>
                </c:choose>
                <input type="hidden" name="id" value="${pago.id}"/>
                <fieldset>

                <!-- Form Name -->
                <legend>Pago</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="pagoNroLegajo">Nro de Legajo</label>  
                  <div class="col-md-4">
                      <c:choose>
                        <c:when test="${empty method}">
                            <!-- <input id="nroLegajo" name="pagoNroLegajo" type="text" placeholder="" class="form-control input-md" required=""> -->
                            <select name="pagoNroLegajo" id="nroLegajo" class="form-control" required="">
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
                            <input readonly="readonly" value="${pago.pagoNroLegajo}" id="pagoNroLegajo" name="pagoNroLegajo" type="text" placeholder="" class="form-control input-md" required="">
                        </c:otherwise>  
                      </c:choose>
                  </div>
                </div>
                
                <div class="form-group">
                  <label class="col-md-4 control-label" for="pagoCodCurso">Código de Curso</label>  
                  <div class="col-md-4">
                      <c:choose>
                        <c:when test="${empty method}">
                            <!-- <input value="${curso.cursoCod}" id="pagoCodCurso" name="pagoCodCurso" type="text" placeholder="" class="form-control input-md" required="">-->
                            <select name="pagoCodCurso" id="codCurso" class="form-control" required="">
<%   
    Modelo m1 = new Modelo();
    m1.cargaArrayCurso();
    
    for (int i=0; i< m1.getCantCursos(); i++){
%>
<option value="<%= m1.getCurso(i).getCursoCod() %>"><%= m1.getCurso(i).getCursoNombre()%></option>
<%
}
%>
    
                      </select>
                        </c:when>
                        <c:otherwise>
                            <input readonly="readonly" value="${pago.pagoCodCurso}" id="pagoCodCurso" name="pagoCodCurso" type="text" placeholder="" class="form-control input-md" required="">
                        </c:otherwise>  
                      </c:choose>
                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="pagoFecha">Fecha de Pago</label>
                  <div class="col-md-4">
                    <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${pago.pagoFecha}" id="pagoFecha" name="pagoFecha" type="text" placeholder="" class="form-control input-md datepicker">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="pagoImporte">Importe</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${pago.pagoImporte}" id="pagoImporte" name="pagoImporte" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>
                
                  <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="pagoComprobante">Comprobante</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${pago.pagoComprobante}" id="pagoComprobante" name="pagoComprobante" type="text" placeholder="" class="form-control input-md">

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
                      <a href="screens/pago/consultaPago.jsp?method=consulta" class="btn btn-primary">Atras</a>
                  </div>
                </div>
                </c:when>
                <c:when test="${method eq 'baja'}">
                <div class="form-group">
                  <label class="col-md-4 control-label" for="saveBtn"></label>
                  <div class="col-md-4">
                      <a href="screens/pago/consultaPago.jsp?method=baja" class="btn btn-primary">Eliminar</a>
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
                      <a href="screens/pago/consultaPago.jsp?method=consulta" class="btn btn-primary">Atras</a>
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
