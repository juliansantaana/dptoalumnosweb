

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(urlPatterns = {"/ControladorAlumno"})
public class ControladorAlumno extends HttpServlet {

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
        Alumno alum;
        RequestDispatcher vista;
        
        switch(method){
            case "alta":
                resul = setAltaAlumno(request, m);
                if (resul == 1){
                    mensajeTitulo = "Alumno Agregado!";
                    mensaje = "El alumno ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el alumno al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                break;
            
            case "modifAction":
                resul = setModificarAlumno(request, m);
                if (resul == 1){
                    mensajeTitulo = "Alumno Modificado!";
                    mensaje = "El alumno ha sido modificado.";
                    estado = "SUCCESS";
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el alumno al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
                
            case "modif":
                alum = this.getAlumnoByNroLegajoRequestParam();
                
                request.setAttribute("formEnabled", true);
                request.setAttribute("alumno", alum);
                request.setAttribute("method", "modifAction");

                vista = request.getRequestDispatcher("screens/alumno/formAlumno.jsp");
                vista.forward(request, response);
                break;
            
            case "consulta":
                alum = this.getAlumnoByNroLegajoRequestParam();
                
                request.setAttribute("formEnabled", false);
                request.setAttribute("alumno", alum);
                request.setAttribute("method", "consulta");

                vista = request.getRequestDispatcher("screens/alumno/formAlumno.jsp");
                vista.forward(request, response);
                                
                break;
        }
        
        request.setAttribute("mensajeTitulo", mensajeTitulo);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estado", estado);
        
        vista = request.getRequestDispatcher("screens/vistaMensaje.jsp");
        vista.forward(request, response);
    }
    
    private Alumno getAlumnoByNroLegajoRequestParam(){
        String nroLegajo = request.getParameter("nroLegajo");
        m.cargaArrayAlumno(nroLegajo, null, null, null);
        String dataAlumno = m.getAlumnos("");
        Alumno alum = m.getAlumno(0);
        
        return alum;
    }
    
    private int setAltaAlumno(HttpServletRequest request, Modelo m){
        String nroLegajo = request.getParameter("nroLegajo");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String nroDoc = request.getParameter("nroDocumento");
        String calle = request.getParameter("calle");
        String nroCalle = request.getParameter("nroCalle");
        String piso = request.getParameter("piso");
        String dpto = request.getParameter("dpto");
        String codPostal = request.getParameter("codPostal");
        String localidad = request.getParameter("localidad");
        String telFijo = request.getParameter("telFijo");
        String telCel = request.getParameter("telCel");
        String eMail = request.getParameter("email");

        return m.qryAltaAlumno(nroLegajo, nombre, apellido, fechaNacimiento, nroDoc, calle, nroCalle, piso, dpto, codPostal, localidad, telFijo, telCel, eMail);
    }
    
    private int setModificarAlumno(HttpServletRequest request, Modelo m){
        String nroLegajo = request.getParameter("nroLegajo");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String nroDoc = request.getParameter("nroDocumento");
        String calle = request.getParameter("calle");
        String nroCalle = request.getParameter("nroCalle");
        String piso = request.getParameter("piso");
        String dpto = request.getParameter("dpto");
        String codPostal = request.getParameter("codPostal");
        String localidad = request.getParameter("localidad");
        String telFijo = request.getParameter("telFijo");
        String telCel = request.getParameter("telCel");
        String eMail = request.getParameter("email");

        return m.qryModificarAlumno(nroLegajo, nombre, apellido, fechaNacimiento, nroDoc, calle, nroCalle, piso, dpto, codPostal, localidad, telFijo, telCel, eMail);
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
