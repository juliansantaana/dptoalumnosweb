package school;

 

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        ArrayList<String> mensajes = new ArrayList<String>();
        
        switch(method){
            case "alta":
                resul = setAltaPrestamo(request, m, mensajes);
                if (resul == 1){
                    mensajeTitulo = "Préstamo Agregado!";
                    mensaje = "El préstamo ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el préstamo al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                break;
            
            case "modifAction":
                resul = setModificarPrestamo(request, m, mensajes);
                if (resul == 1){
                    mensajeTitulo = "Préstamo Modificado!";
                    mensaje = "El préstamo ha sido modificado.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
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
    
    private Prestamo getPrestamoByNroLegajoRequestParam(){
        String nroLegajo = request.getParameter("nroLegajo");
        String codRecurso = request.getParameter("codRecurso");
        m.cargaArrayPrestamo();
        Prestamo prestamo = m.getPrestamoWithCode(nroLegajo, codRecurso);
        
        return prestamo;
    }
    
    private int setAltaPrestamo(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String nroLegajo = request.getParameter("nroLegajo");
        String codRecurso = request.getParameter("codRecurso");
        String fechaPrestamo = request.getParameter("fechaPrestamo");
        String fechaPrevistaDevolucion = request.getParameter("fechaPrevistaDevolucion");
        String fechaDevolucion = request.getParameter("fechaDevolucion");

        mensajes.addAll(validar(nroLegajo, codRecurso, fechaPrestamo, fechaPrevistaDevolucion, fechaDevolucion));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryAltaPrestamo(nroLegajo, codRecurso, fechaPrestamo, fechaPrevistaDevolucion, fechaDevolucion);
        }
    }
    
    private int setModificarPrestamo(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String nroLegajo = request.getParameter("nroLegajo");
        String codRecurso = request.getParameter("codRecurso");
        String fechaPrestamo = request.getParameter("fechaPrestamo");
        String fechaPrevistaDevolucion = request.getParameter("fechaPrevistaDevolucion");
        String fechaDevolucion = request.getParameter("fechaDevolucion");

        mensajes.addAll(validar(nroLegajo, codRecurso, fechaPrestamo, fechaPrevistaDevolucion, fechaDevolucion));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryModificarPrestamo(nroLegajo, codRecurso, fechaPrestamo, fechaPrevistaDevolucion, fechaDevolucion);
        }
    }

    private ArrayList<String> validar(String nroLegajo, String codRecurso, String fechaPrestamo, String fechaPrevistaDevolucion, String fechaDevolucion){
        
        boolean validacion = true;
        ArrayList<String> mensajes = new ArrayList<String>();

        if(nroLegajo.isEmpty()){ 
                validacion = false;
                mensajes.add("Nro Legajo : Campo Vacío.");
        }else{
            if(nroLegajo.length() > 10){
                validacion = false;
                mensajes.add("Nro Legajo : Cant de dígitos necesarios = 4");
            }else{
                if(!isNumeric(nroLegajo)){
                    validacion = false;
                    mensajes.add("Nro Legajo : Inserte solo dígitos numéricos.");
                }
            }
        }
        if(codRecurso.isEmpty()){ 
                validacion = false;
                mensajes.add("Cod Recurso : Campo Vacío.");
        }else{
            
        }
        if(fechaPrestamo.isEmpty()){ 
                validacion = false;
                mensajes.add("Fecha Prestamo : Campo Vacío.");
        }else{
            if(!validateDate(fechaPrestamo)){
                validacion = false;
                mensajes.add("Fecha Prestamo: Formato Incorrecto.");
            }
        }
        if(fechaPrevistaDevolucion.isEmpty()){ 
                validacion = false;
                mensajes.add("Fecha Prevista Devolución : Campo Vacío.");
        }else{
            if(!validateDate(fechaPrevistaDevolucion)){
                validacion = false;
                mensajes.add("Fecha Prevista Devolución: Formato Incorrecto.");
            }
        }
        
        if (!fechaDevolucion.isEmpty()){
            if(!validateDate(fechaDevolucion)){
                validacion = false;
                mensajes.add("Fecha Devolucion: Formato Incorrecto.");
            }
        }else{
            fechaDevolucion = null;
        }
        
        //if(fechaDevolucion.isEmpty()){
        //    validacion = false;
        //}

        return mensajes;

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
