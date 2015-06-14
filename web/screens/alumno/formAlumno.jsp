<%-- 
    Document   : altaAlumnos
    Created on : Jun 10, 2015, 8:24:42 PM
    Author     : juliansantaana
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
            <form method="post" action="ControladorAlumno" class="form-horizontal">
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
                <legend>Alumnos</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroLegajo">Nro Legajo</label>  
                  <div class="col-md-4">
                      <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.nroLegajo}" id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nombre">Nombre</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.nombre}" id="nombre" name="nombre" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="apellido">Apellido</label>
                  <div class="col-md-4">
                    <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.apellido}" id="apellido" name="apellido" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="fechaNacimiento">Fecha de Nacimiento</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.fechaNacimiento}" id="fechaNacimiento" name="fechaNacimiento" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroDocumento">Nro Documento</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.nroDoc}" id="nroDocumento" name="nroDocumento" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="calle">Calle</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.calle}" id="calle" name="calle" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroCalle">Nro</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.nroCalle}" id="nroCalle" name="nroCalle" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="piso">Piso</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.piso}" id="piso" name="piso" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="dpto">Depto</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.departamento}" id="dpto" name="dpto" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codPostal">Cod Postal</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.codPostal}" id="codPostal" name="codPostal" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="localidad">Localidad</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.localidad}" id="localidad" name="localidad" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="telFijo">Tel Fijo</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.telFijo}" id="telFijo" name="telFijo" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="telCel">Tel Cel</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.telCel}" id="telCel" name="telCel" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="email">Email</label>  
                  <div class="col-md-6">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${alumno.eMail}" id="email" name="email" type="text" placeholder="" class="form-control input-md">

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
                      <a href="screens/alumno/consultaAlumno.jsp?method=consulta" class="btn btn-primary">Atras</a>
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
                      <a href="screens/alumno/consultaAlumno.jsp?method=consulta" class="btn btn-primary">Atras</a>
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
