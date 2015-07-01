

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
@WebServlet(urlPatterns = {"/ControladorPago"})
public class ControladorPago extends HttpServlet {

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
        Pago pago;
        RequestDispatcher vista;
        ArrayList<String> mensajes = new ArrayList<String>();
        
        switch(method){
            case "alta":
                resul = setAltaPago(request, m, mensajes);
                if (resul == 1){
                    mensajeTitulo = "Pago Agregado!";
                    mensaje = "El pago ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el pago al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                break;
            
            case "modifAction":
                resul = setModificarPago(request, m, mensajes);
                if (resul == 1){
                    mensajeTitulo = "Pago Modificado!";
                    mensaje = "El pago ha sido modificado.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el pago al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
                
            case "modif":
                pago = this.getPagoByNroLegajoRequestParam();
                
                request.setAttribute("formEnabled", true);
                request.setAttribute("pago", pago);
                request.setAttribute("method", "modifAction");

                vista = request.getRequestDispatcher("screens/pago/formPago.jsp");
                vista.forward(request, response);
                break;
            
            case "consulta":
                pago = this.getPagoByNroLegajoRequestParam();
                
                request.setAttribute("formEnabled", false);
                request.setAttribute("pago", pago);
                request.setAttribute("method", "consulta");

                vista = request.getRequestDispatcher("screens/pago/formPago.jsp");
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
    
    private Pago getPagoByNroLegajoRequestParam(){
        String pagoNroLegajo = request.getParameter("pagoNroLegajo");
        String pagoCodCurso = request.getParameter("pagoCodCurso");
        m.cargaArrayPago();
        Pago pago = m.getPagoWithCode(pagoNroLegajo, pagoCodCurso);
        
        return pago;
    }
    
    private int setAltaPago(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String pagoNroLegajo = request.getParameter("pagoNroLegajo");
        String pagoCodCurso = request.getParameter("pagoCodCurso");
        String pagoFecha = request.getParameter("pagoFecha");
        String pagoImporte = request.getParameter("pagoImporte");
        String pagoComprobante = request.getParameter("pagoComprobante");
        
        mensajes.addAll(validar(pagoNroLegajo, pagoCodCurso, pagoFecha, pagoImporte, pagoComprobante));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryAltaPago(pagoNroLegajo, pagoCodCurso, pagoFecha, pagoImporte, pagoComprobante);
        }
    }
    
    private ArrayList<String> validar(String pagoNroLegajo, String pagoCodCurso, String pagoFecha, String pagoImporte, String pagoComprobante){
        
        boolean validacion = true;
        ArrayList<String> mensajes = new ArrayList<String>();
        
        if(pagoNroLegajo.isEmpty()){ 
            validacion = false;
            mensajes.add("Nro Legajo : Campo Vacío.");
        }else{
            if(pagoNroLegajo.length() > 10){
                validacion = false;
                mensajes.add("Nro Legajo : Cant de dígitos necesarios = 4");
            }else{
                if(!isNumeric(pagoNroLegajo)){
                    validacion = false;
                    mensajes.add("Nro Legajo : Inserte solo dígitos numéricos.");
                }
            }
        }
        if(pagoCodCurso.isEmpty()){ 
            validacion = false;
            mensajes.add("Cod Curso : Campo Vacío.");
        }else{
            if(pagoCodCurso.length() > 8){
                validacion = false;
                mensajes.add("Cod Curso : Cant de dígitos necesarios = 4");
            }else{
                if(!isNumeric(pagoCodCurso)){
                    validacion = false;
                    mensajes.add("Cod Curso : Inserte solo dígitos numéricos.");
                }
            }
        }
        if(pagoFecha.isEmpty()){ 
            validacion = false;
            mensajes.add("Fecha : Campo Vacío.");
        }else{
            if(!validateDate(pagoFecha)){
                validacion = false;
                mensajes.add("Fecha : Formato Incorrecto.");
            }
        }
        if(pagoImporte.isEmpty()){
            validacion = false;
        }
        if(pagoComprobante.isEmpty()){ 
            validacion = false;
            mensajes.add("Comprobante : Campo Vacío.");
        }else{
        }

        return mensajes;
    }
    
    
    private int setModificarPago(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String pagoNroLegajo = request.getParameter("pagoNroLegajo");
        String pagoCodCurso = request.getParameter("pagoCodCurso");
        String pagoFecha = request.getParameter("pagoFecha");
        String pagoImporte = request.getParameter("pagoImporte");
        String pagoComprobante = request.getParameter("pagoComprobante");

        mensajes.addAll(validar(pagoNroLegajo, pagoCodCurso, pagoFecha, pagoImporte, pagoComprobante));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryModificarPago(pagoNroLegajo, pagoCodCurso, pagoFecha, pagoImporte, pagoComprobante);
        }
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
