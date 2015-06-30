

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
@WebServlet(urlPatterns = {"/ControladorPrestamo"})
public class ControladorPrestamo extends HttpServlet {

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
        Prestamo prestamo;
        RequestDispatcher vista;
        
        switch(method){
            case "alta":
                resul = setAltaPrestamo(request, m);
                if (resul == 1){
                    mensajeTitulo = "Préstamo Agregado!";
                    mensaje = "El préstamo ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el préstamo al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                break;
            
            case "modifAction":
                resul = setModificarPrestamo(request, m);
                if (resul == 1){
                    mensajeTitulo = "Préstamo Modificado!";
                    mensaje = "El préstamo ha sido modificado.";
                    estado = "SUCCESS";
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el préstamo al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
                
            case "modif":
                prestamo = this.getPrestamoByNroLegajoRequestParam();
                
                request.setAttribute("formEnabled", true);
                request.setAttribute("prestamo", prestamo);
                request.setAttribute("method", "modifAction");

                vista = request.getRequestDispatcher("screens/prestamo/formPrestamo.jsp");
                vista.forward(request, response);
                break;
            
            case "consulta":
                prestamo = this.getPrestamoByNroLegajoRequestParam();
                
                request.setAttribute("formEnabled", false);
                request.setAttribute("prestamo", prestamo);
                request.setAttribute("method", "consulta");

                vista = request.getRequestDispatcher("screens/prestamo/formPrestamo.jsp");
                vista.forward(request, response);    
                break;
        }
        
        request.setAttribute("mensajeTitulo", mensajeTitulo);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estado", estado);
        
        vista = request.getRequestDispatcher("screens/vistaMensaje.jsp");
        vista.forward(request, response);
    }
    
    private Prestamo getPrestamoByNroLegajoRequestParam(){
        String nroLegajo = request.getParameter("nroLegajo");
        String codRecurso = request.getParameter("codRecurso");
        m.cargaArrayPrestamo();
        Prestamo prestamo = m.getPrestamoWithCode(nroLegajo, codRecurso);
        
        return prestamo;
    }
    
    private int setAltaPrestamo(HttpServletRequest request, Modelo m){
        String nroLegajo = request.getParameter("nroLegajo");
        String codRecurso = request.getParameter("codRecurso");
        String fechaPrestamo = request.getParameter("fechaPrestamo");
        String fechaPrevistaDevolucion = request.getParameter("fechaPrevistaDevolucion");
        String fechaDevolucion = request.getParameter("fechaDevolucion");
        
        return m.qryAltaPrestamo(nroLegajo, codRecurso, fechaPrestamo, fechaPrevistaDevolucion, fechaDevolucion);
    }
    
    private int setModificarPrestamo(HttpServletRequest request, Modelo m){
        String nroLegajo = request.getParameter("nroLegajo");
        String codRecurso = request.getParameter("codRecurso");
        String fechaPrestamo = request.getParameter("fechaPrestamo");
        String fechaPrevistaDevolucion = request.getParameter("fechaPrevistaDevolucion");
        String fechaDevolucion = request.getParameter("fechaDevolucion");
        
        return m.qryModificarPrestamo(nroLegajo, codRecurso, fechaPrestamo, fechaPrestamo, fechaDevolucion);
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
