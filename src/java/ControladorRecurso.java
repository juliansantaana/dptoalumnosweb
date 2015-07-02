

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
@WebServlet(urlPatterns = {"/ControladorRecurso"})
public class ControladorRecurso extends HttpServlet {

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
        Recurso recurso;
        RequestDispatcher vista;
        ArrayList<String> mensajes = new ArrayList<String>();
        
        switch(method){
            case "alta":
                resul = setAltaRecurso(request, m, mensajes);

                if (resul == 1){
                    mensajeTitulo = "Recurso Agregado!";
                    mensaje = "El recurso ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el recurso al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                break;
            
            case "modifAction":
                resul = setModificarRecurso(request, m, mensajes);

                if (resul == 1){
                    mensajeTitulo = "Recurso Modificado!";
                    mensaje = "El recurso ha sido modificado.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el recurso al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
                
            case "modif":
                recurso = this.getRecursoByNroRecursoRequestParam();
                
                request.setAttribute("formEnabled", true);
                request.setAttribute("recurso", recurso);
                request.setAttribute("method", "modifAction");

                vista = request.getRequestDispatcher("screens/recurso/formRecurso.jsp");
                vista.forward(request, response);
                break;
            
            case "consulta":
                recurso = this.getRecursoByNroRecursoRequestParam();
                
                request.setAttribute("formEnabled", false);
                request.setAttribute("recurso", recurso);
                request.setAttribute("method", "consulta");

                vista = request.getRequestDispatcher("screens/recurso/formRecurso.jsp");
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
        
    private Recurso getRecursoByNroRecursoRequestParam(){
        String codRecurso = request.getParameter("codRecurso");
        m.cargaArrayRecurso();
        return m.getRecursoWithCode(codRecurso);
    }
    
    private int setAltaRecurso(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String codRecurso = request.getParameter("codRecurso");
        String categoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        String autor = request.getParameter("autor");
        String anio = request.getParameter("anio");
        String cant = request.getParameter("cant");
        String foto = request.getParameter("foto");

        mensajes.addAll(validar(codRecurso, nombre, anio, categoria, autor, cant));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryAltaRecurso(codRecurso, nombre, anio, categoria, autor, cant);
        }
    }
    
    private int setModificarRecurso(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
        String codRecurso = request.getParameter("codRecurso");
        String categoria = request.getParameter("categoria");
        String nombre = request.getParameter("nombre");
        String autor = request.getParameter("autor");
        String anio = request.getParameter("anio");
        String cant = request.getParameter("cant");
        String foto = request.getParameter("foto");

        mensajes.addAll(validar(codRecurso, nombre, anio, categoria, autor, cant));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryModificarRecurso(codRecurso, nombre, anio, categoria, autor, cant);
        }
    }

    private ArrayList<String> validar(String codRecurso, String nombre, String anio, String categoria, String autor, String cant){
        
        boolean validacion = true;
        ArrayList<String> mensajes = new ArrayList<String>();

        if(codRecurso.isEmpty()){ 
                validacion = false;
                mensajes.add("Cod Recurso : Campo Vacío.");
            }else{
                
            }
            if(categoria.isEmpty()){ 
                validacion = false;
                mensajes.add("Categoría : Campo Vacío.");
            }else{
                if(categoria.length()> 50){
                    validacion = false;
                    mensajes.add("Categoría : Máxima cant de dígitos = 50.");
                }else{
                    if(!isAlpha(categoria)){
                        validacion = false;
                        mensajes.add("Categoría : Ingrese solo letras.");
                    }
                }
            }
            if(nombre.isEmpty()){ 
                validacion = false;
                mensajes.add("Nombre : Campo Vacío.");
            }else{
                if(nombre.length()> 50){
                    validacion = false;
                    mensajes.add("Nombre : Máxima cant de dígitos = 50.");
                }
            }
            if(autor.isEmpty()){ 
                validacion = false;
                mensajes.add("Autor : Campo Vacío.");
            }else{
                if(autor.length()> 50){
                    validacion = false;
                    mensajes.add("Autor : Máxima cant de dígitos = 50.");
                }else{
                    if(!isAlpha(autor)){
                        validacion = false;
                        mensajes.add("Autor : Ingrese solo letras.");
                    }
                }
            }
            if(isAlpha(anio)){
                validacion = false;
                mensajes.add("Año : Ingrese solo nros.");                    
            }else{
                if(anio.length() > 4){
                    validacion = false;
                    mensajes.add("Año : Ingrese un año válido");
                }
            }
            if(cant.isEmpty()){
                validacion = false;
                mensajes.add("Cantidad : Campo vacío.");
            }else{
                if(isAlpha(cant)){
                    validacion = false;
                    mensajes.add("Cantidad : Ingrese solo nros.");                    
                }
            }

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
