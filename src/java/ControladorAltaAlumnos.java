

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
@WebServlet(urlPatterns = {"/ControladorAltaAlumnos"})
public class ControladorAltaAlumnos extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    HttpServletRequest request;
    HttpServletResponse response;
    
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
        
        Modelo m = new Modelo();
        
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
        
        int resul = m.qryAltaAlumno(nroLegajo, nombre, apellido, fechaNacimiento, nroDoc, calle, nroCalle, piso, dpto, codPostal, localidad, telFijo, telCel, eMail);
        
        String mensajeTitulo = "";
        String mensaje = "";
        //estado: define en la vistaMensaje si se muestra rojo (error) o verde (success)
        String estado = ""; //puede ser 'ERROR' o 'SUCCESS'
        if (resul == 1){
            mensajeTitulo = "Alumno Agregado!";
            mensaje = "El alumno ha sido agregado al sistema.";
            estado = "SUCCESS";
        }else{
            mensajeTitulo = "Error";
            mensaje = "Hubo un error al intentar agregar el alumno al sistema.";
            estado = "ERROR";
        }
        
        request.setAttribute("mensajeTitulo", mensajeTitulo);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estado", estado);
        
        RequestDispatcher vista = request.getRequestDispatcher("screens/vistaMensaje.jsp");
        vista.forward(request, response);
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
