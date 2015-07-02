

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author juliansantaana
 */
@WebServlet(urlPatterns = {"/ControladorCurso"})
public class ControladorCurso extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    HttpServletRequest request;
    HttpServletResponse response;
    Modelo m = new Modelo();
    
    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        this.request = request;
        this.response = response;
        
        String mensajeTitulo = "";
        String mensaje = "";
        //estado: define en la vistaMensaje si se muestra rojo (error) o verde (success)
        String estado = ""; //puede ser 'ERROR' o 'SUCCESS'
        int resul = 0;
        
        String method = request.getParameter("method");
        Curso curso;
        RequestDispatcher vista;
        ArrayList<String> mensajes = new ArrayList<String>();
        
        switch(method){
            case "alta":
                resul = setAltaCurso(request, m, mensajes);
                
                if (resul == 1){
                    mensajeTitulo = "Curso Agregado!";
                    mensaje = "El curso ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el curso al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                resul = setBajaCurso(request, m);
                if (resul == 1){
                    mensajeTitulo = "Curso Eliminado!";
                    mensaje = "El curso ha sido dado de baja.";
                    estado = "SUCCESS";
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar dar de baja el curso del sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "modifAction":
                resul = setModificarCurso(request, m, mensajes);
                
                if (resul == 1){
                    mensajeTitulo = "Curso Modificado!";
                    mensaje = "El curso ha sido modificado.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar modificar el curso al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
                
            case "modif":
                curso = this.getCursoByNroCursoRequestParam();
                
                request.setAttribute("formEnabled", true);
                request.setAttribute("curso", curso);
                request.setAttribute("method", "modifAction");

                vista = request.getRequestDispatcher("screens/curso/formCurso.jsp");
                vista.forward(request, response);
                break;
            
            case "consulta":
                curso = this.getCursoByNroCursoRequestParam();
                
                request.setAttribute("formEnabled", false);
                request.setAttribute("curso", curso);
                request.setAttribute("method", "consulta");

                vista = request.getRequestDispatcher("screens/curso/formCurso.jsp");
                vista.forward(request, response);
                                
                break;
            
        }
        
        request.setAttribute("mensajeTitulo", mensajeTitulo);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estado", estado);
        
        vista = request.getRequestDispatcher("screens/vistaMensaje.jsp");
        vista.forward(request, response);
    }
    
    // ----- funciones de validacion -----
    public static boolean isNumeric(String str){
        for (char c : str.toCharArray())
        {
            if (!Character.isDigit(c)) return false;
        }
        return true;
    }
    
    public boolean isAlpha(String name) {
        //return name.matches("[a-zA-Z ]+");
        return name.matches("[a-zA-Zäáàëéèíìöóòúùñç  .]+");
    }
    
    public boolean validateDate(String date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            sdf.parse(date);
            return true;
        }
        catch(ParseException ex) {
            return false;
        }
    }
    
    private Curso getCursoByNroCursoRequestParam(){
        String codCurso = request.getParameter("codCurso");
        m.cargaArrayCurso();
        return m.getCursoWithCode(codCurso);
    }
    
    
    private int setAltaCurso(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String codCurso = request.getParameter("codCurso");
        String cursoNombre = request.getParameter("cursoNombre");
        String cursoProf = request.getParameter("cursoProf");
        String cursoCantClases = request.getParameter("cursoCantClases");

        mensajes.addAll(validar(codCurso, cursoNombre, cursoProf, cursoCantClases));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryAltaCurso(codCurso, cursoNombre, cursoProf, cursoCantClases);
        }
    }
    
    private int setModificarCurso(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String codCurso = request.getParameter("codCurso");
        String cursoNombre = request.getParameter("cursoNombre");
        String cursoProf = request.getParameter("cursoProf");
        String cursoCantClases = request.getParameter("cursoCantClases");
        
        mensajes.addAll(validar(codCurso, cursoNombre, cursoProf, cursoCantClases));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryModificarCurso(codCurso, cursoNombre, cursoProf, cursoCantClases);
        }
    }

    private ArrayList<String> validar(String codCurso, String cursoNombre, String cursoProf, String cursoCantClases){
        
        boolean validacion = true;
        ArrayList<String> mensajes = new ArrayList<String>();

        if(codCurso.isEmpty()){ 
            validacion = false;
            mensajes.add("Cod Curso : Campo Vacío.");
        }else{
            if(codCurso.length() > 8){
                validacion = false;
                mensajes.add("Cod Curso : Cant de dígitos necesarios = 4");
            }else{
                if(!isNumeric(codCurso)){
                    validacion = false;
                    mensajes.add("Cod Curso : Inserte solo dígitos numéricos.");
                }
            }
        }
        if(cursoNombre.isEmpty()){ 
            validacion = false;
            mensajes.add("Nombre : Campo Vacío.");
        }else{
            if(cursoNombre.length()> 50){
                validacion = false;
                mensajes.add("Nombre : Máxima cant de dígitos = 50.");
            }
        }
        if(cursoProf.isEmpty()){ 
            validacion = false;
            mensajes.add("Profesor : Campo Vacío.");
        }else{
            if(cursoProf.length()> 50){
                validacion = false;
                mensajes.add("Profesor : Máxima cant de dígitos = 50.");
            }else{
                if(!isAlpha(cursoProf)){
                    validacion = false;
                    mensajes.add("Profesor : Ingrese solo letras.");
                }
            }
        }
        
        if(cursoCantClases.isEmpty()){
            mensajes.add("Cantidad de Clases : Campo Vacío.");
        }else{
            if (Integer.parseInt(cursoCantClases) > 32){
                validacion = false;
                mensajes.add("Maximo numero de clases permitido: 32");
            }
        }

        return mensajes;

    }
    
    private int setBajaCurso(HttpServletRequest request, Modelo m){
        String codCurso = request.getParameter("codCurso");
       
        return m.qryEliminarCurso(codCurso);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
