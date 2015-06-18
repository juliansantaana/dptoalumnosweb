

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
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
        
        switch(method){
            case "alta":
                resul = setAltaCurso(request, m);
                if (resul == 1){
                    mensajeTitulo = "Curso Agregado!";
                    mensaje = "El curso ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el curso al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                break;
            
            case "modifAction":
                resul = setModificarCurso(request, m);
                if (resul == 1){
                    mensajeTitulo = "Curso Modificado!";
                    mensaje = "El curso ha sido modificado.";
                    estado = "SUCCESS";
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
    
    private Curso getCursoByNroCursoRequestParam(){
        String codCurso = request.getParameter("codCurso");
        m.cargaArrayCurso();
        return m.getCursoWithCode(codCurso);
    }
    
    
    private int setAltaCurso(HttpServletRequest request, Modelo m){
        String codCurso = request.getParameter("codCurso");
        String cursoNombre = request.getParameter("cursoNombre");
        String cursoProf = request.getParameter("cursoProf");
        String cursoCantClases = request.getParameter("cursoCantClases");
        return m.qryAltaCurso(codCurso, cursoNombre, cursoProf, cursoCantClases);
    }
    
    private int setModificarCurso(HttpServletRequest request, Modelo m){
        String codCurso = request.getParameter("codCurso");
        String cursoNombre = request.getParameter("cursoNombre");
        String cursoProf = request.getParameter("cursoProf");
        String cursoCantClases = request.getParameter("cursoCantClases");
       
       

        return m.qryModificarCurso(codCurso, cursoNombre, cursoProf, cursoCantClases);
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
