<!-- Fixed navbar -->
<nav class="navbar navbar-default navbar-fixed-top">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Departamento de Alumnos</a>
    </div>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">Inicio</a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Alumnos <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="screens/alumno/formAlumno.jsp">Alta</a></li>
            <li><a href="screens/alumno/consultaAlumno.jsp?method=modif">Modificar</a></li>
            <li><a href="screens/alumno/consultaAlumno.jsp?method=consulta">Consulta</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Cursos <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="screens/curso/formCurso.jsp">Alta</a></li>
            <li><a href="screens/curso/consultaCurso.jsp?method=modif">Modificar</a></li>
            <li><a href="screens/curso/consultaCurso.jsp?method=consulta">Consulta</a></li>
            <li><a href="screens/curso/consultaCurso.jsp?method=baja">Eliminar</a></li>
          </ul>
        </li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Recursos <span class="caret"></span></a>
          <ul class="dropdown-menu" role="menu">
            <li><a href="screens/recurso/formRecurso.jsp">Alta</a></li>
            <li><a href="screens/recurso/consultaRecurso.jsp?method=modif">Modificar</a></li>
            <li><a href="screens/recurso/consultaRecurso.jsp?method=consulta">Consulta</a></li>
          </ul>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <!--<li class="active"><a href="./">Fixed top <span class="sr-only">(current)</span></a></li>-->
      </ul>
    </div><!--/.nav-collapse -->
  </div>
</nav>