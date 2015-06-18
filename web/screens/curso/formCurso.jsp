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
        
        <div class="container">
        <div class="col-md-6 col-md-offset-3">  
            <form method="post" action="ControladorCurso" class="form-horizontal">
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
                <legend>Curso</legend>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="codCurso">Código de Curso</label>  
                  <div class="col-md-4">
                      <input readonly="true" value="${curso.cursoCod}" id="codCurso" name="codCurso" type="text" placeholder="" class="form-control input-md" required="">
                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="cursoNombre">Denominacion</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${curso.cursoNombre}" id="cursoNombre" name="cursoNombre" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Password input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="cursoProf">Profesor a Cargo</label>
                  <div class="col-md-4">
                    <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${curso.cursoProf}" id="cursoProf" name="cursoProf" type="text" placeholder="" class="form-control input-md">

                  </div>
                </div>

                <!-- Text input-->
                <div class="form-group">
                  <label class="col-md-4 control-label" for="cursoCantClases">Cantidad de Clases</label>  
                  <div class="col-md-4">
                  <input <c:if test="${formEnabled eq 'false'}"> disabled="true" </c:if> value="${curso.cursoCantClases}" id="cursoCantClases" name="cursoCantClases" type="text" placeholder="" class="form-control input-md">

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
                      <a href="screens/curso/consultaCurso.jsp?method=consulta" class="btn btn-primary">Atras</a>
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
                      <a href="screens/curso/consultaCurso.jsp?method=consulta" class="btn btn-primary">Atras</a>
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
