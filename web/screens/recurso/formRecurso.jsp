<%-- 
    Document   : formRecurso
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
        <div class="col-md-6 col-md-offset-3">  
            <form method="post" action="ControladorRecurso" class="form-horizontal">
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
                <legend>Recursos</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codRecurso">Código de Recurso</label>  
                  <div class="col-md-4">
                      <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${recurso.codRecurso}" id="codRecurso" name="codRecurso" type="text" placeholder="" class="form-control input-md" required="" readonly>

                  </div>
                </div>
                      
                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="categoria">Categoría</label>  
                  <div class="col-md-4">
                  <select name="categoria" id="categoria" <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> class="form-control input-md">
                      <option value="CD" <c:if test="${recurso.categoria eq 'CD'}"> selected="selected" </c:if>>CD</option>
                      <option value="DVD" <c:if test="${recurso.categoria eq 'DVD'}"> selected="selected" </c:if>>DVD</option>
                      <option value="Libro" <c:if test="${recurso.categoria eq 'Libro'}"> selected="selected" </c:if>>Libro</option>
                  </select>
                  </div>
                </div>  

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nombre">Nombre</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${recurso.nombre}" id="nombre" name="nombre" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="autor">Autor</label>
                  <div class="col-md-4">
                    <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${recurso.autor}" id="autor" name="autor" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="anio">Año</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${recurso.anio}" id="anio" name="anio" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="cant">Cantidad</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${recurso.cant}" id="cant" name="cant" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="foto">Foto</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${recurso.foto}" id="foto" name="foto" type="text" placeholder="" class="form-control input-md">

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
                      <a href="screens/alumno/consultaRecurso.jsp?method=consulta" class="btn btn-primary">Atras</a>
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
                      <a href="screens/alumno/consultaRecurso.jsp?method=consulta" class="btn btn-primary">Atras</a>
                  </div>
                </div>
                </c:otherwise>
                </c:choose>
            </form>
        </div>
            
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
