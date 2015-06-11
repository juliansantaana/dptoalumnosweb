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
            <form method="post" action="ControladorAltaAlumnos" class="form-horizontal">
                <fieldset>

                <!-- Form Name -->
                <legend>Alumnos</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroLegajo">Nro Legajo</label>  
                  <div class="col-md-4">
                  <input id="nroLegajo" name="nroLegajo" type="text" placeholder="" class="form-control input-md" required="">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nombre">Nombre</label>  
                  <div class="col-md-4">
                  <input id="nombre" name="nombre" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="apellido">Apellido</label>
                  <div class="col-md-4">
                    <input id="apellido" name="apellido" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="fechaNacimiento">Fecha de Nacimiento</label>  
                  <div class="col-md-4">
                  <input id="fechaNacimiento" name="fechaNacimiento" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroDocumento">Nro Documento</label>  
                  <div class="col-md-4">
                  <input id="nroDocumento" name="nroDocumento" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="calle">Calle</label>  
                  <div class="col-md-4">
                  <input id="calle" name="calle" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="nroCalle">Nro</label>  
                  <div class="col-md-4">
                  <input id="nroCalle" name="nroCalle" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="piso">Piso</label>  
                  <div class="col-md-4">
                  <input id="piso" name="piso" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="dpto">Depto</label>  
                  <div class="col-md-4">
                  <input id="dpto" name="dpto" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codPostal">Cod Postal</label>  
                  <div class="col-md-4">
                  <input id="codPostal" name="codPostal" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="localidad">Localidad</label>  
                  <div class="col-md-4">
                  <input id="localidad" name="localidad" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="telFijo">Tel Fijo</label>  
                  <div class="col-md-4">
                  <input id="telFijo" name="telFijo" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="telCel">Tel Cel</label>  
                  <div class="col-md-4">
                  <input id="telCel" name="telCel" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="email">Email</label>  
                  <div class="col-md-6">
                  <input id="email" name="email" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>
                
                
                </fieldset>
                <!-- Button -->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="saveBtn"></label>
                  <div class="col-md-4">
                    <button type="submit" id="saveBtn" name="saveBtn" class="btn btn-primary">Guardar</button>
                  </div>
                </div>
            </form>
        </div>
            
        </div>
        
        <%@include file='/templates/footer_body.jsp'%>
    </body>
</html>
