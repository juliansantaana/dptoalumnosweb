package school;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
        ArrayList<String> mensajes = new ArrayList<String>();
        
        switch(method){
            case "alta":
                resul = setAltaAlumno(request, m, mensajes);
                if (resul == 1){
                    mensajeTitulo = "Alumno Agregado!";
                    mensaje = "El alumno ha sido agregado al sistema.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar agregar el alumno al sistema. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
            
            case "baja":
                break;
            
            case "modifAction":
                resul = setModificarAlumno(request, m, mensajes);
                if (resul == 1){
                    mensajeTitulo = "Alumno Modificado!";
                    mensaje = "El alumno ha sido modificado.";
                    estado = "SUCCESS";
                }else if (resul == 2){
                    request.setAttribute("mensajes", mensajes);
                    vista = request.getRequestDispatcher("screens/vistaValidacion.jsp");
                    vista.forward(request, response);
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
    
    private int setAltaAlumno(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
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
        
        mensajes.addAll(validar(nroLegajo, nombre, apellido, fechaNacimiento, nroDoc, calle, nroCalle, piso, dpto, codPostal, localidad, telFijo, telCel, eMail));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryAltaAlumno(nroLegajo, nombre, apellido, fechaNacimiento, nroDoc, calle, nroCalle, piso, dpto, codPostal, localidad, telFijo, telCel, eMail);
        }
    }
    
    private ArrayList<String> validar(String nroLegajo , String nombre , String apellido , String fechaNacimiento , String nroDoc , String calle , String nroCalle , String piso , String dpto , String codPostal , String localidad , String telFijo , String telCel , String eMail){
        boolean validacion = true;
        ArrayList<String> mensajes = new ArrayList<String>();
        
        if(nroLegajo.isEmpty()){ 
            validacion = false;
            mensajes.add("Nro. Legajo : Campo Vacío.");
        }else{
            if(nroLegajo.length() > 10){
                validacion = false;
                mensajes.add("Nro Legajo : Cant de dígitos necesarios = 4");
            }else{
                if(!HelpersValidacion.isNumeric(nroLegajo)){
                    validacion = false;
                    mensajes.add("Nro Legajo : Inserte solo dígitos numéricos.");
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
            }else{
                if(!HelpersValidacion.isAlpha(nombre)){
                    validacion = false;
                    mensajes.add("Nombre : Ingrese solo letras.");
                }
            }
        }
        if(apellido.isEmpty()){ 
            validacion = false;
            mensajes.add("Apellido : Campo Vacío.");
        }else{
            if(apellido.length()> 50){
                validacion = false;
                mensajes.add("Apellido : Máxima cant de dígitos = 50");
            }else{
                if(!HelpersValidacion.isAlpha(apellido)){
                    validacion = false;
                    mensajes.add("Apellido : Ingrese solo letras.");
                }
            }
        }
        if(fechaNacimiento.isEmpty()){ 
            validacion = false;
            mensajes.add("Fecha Nacimiento : Campo Vacío.");
        }else{
            if(!HelpersValidacion.validateDate(fechaNacimiento)){
                validacion = false;
                mensajes.add("Fecha Nacimiento: Formato Incorrecto.");
            }
        }
        if(nroDoc.isEmpty()){ 
            validacion = false;
            mensajes.add("Nro Doc : Campo Vacío");
        }else{
            if(nroDoc.length() < 8){
                validacion = false;
                mensajes.add("Nro Doc : Cant de dígitos necesarios = 8");
            }else{
                if(!HelpersValidacion.isNumeric(nroDoc.replace(".", ""))){
                    validacion = false;
                    mensajes.add("Nro Doc : Ingrese solo nros.");
                }
            }
        }
        if(calle.isEmpty()){ 
            validacion = false;
            mensajes.add("Calle : Campo vacío");
        }else{
            if(calle.length() > 50){
                validacion = false;
                mensajes.add("Calle : Máxima cant de caracteres = 50");
            }
        }
        if(nroCalle.isEmpty()){ 
            validacion = false;
            mensajes.add("Nro Calle : Campo vacío");
        }else{
            if(!HelpersValidacion.isNumeric(nroCalle)){
                validacion = false;
                mensajes.add("Nro Calle : Ingrese solo nros.");
            }
        }
        /*if(HelpersValidacion.isAlpha(piso)){
            validacion = false;
            mensajes.add("Piso : Ingrese solo nros.");                    
        }*/

        if(!HelpersValidacion.isAlpha(dpto) && !HelpersValidacion.isNumeric(dpto)){
            validacion = false;
            mensajes.add("Departamento : Ingrese solo nros o solo letras.");                    
        }

        if(codPostal.isEmpty()){ 
            validacion = false;
            mensajes.add("Cod Postal : Campo vacío.");               
        }else{
            if(HelpersValidacion.isAlpha(codPostal)){
                validacion = false;
                mensajes.add("Cod Postal : Ingrese solo nros.");                    
            }
        }
        if(localidad.isEmpty()){ 
            validacion = false;
            mensajes.add("Localidad : Campo vacío.");         
        }else{
            if(localidad.length() > 50){
                validacion = false;
                mensajes.add("Localidad : Máxima cant de caracteres = 50");
            }
        }
        if(telFijo.isEmpty()){ 
            validacion = false;
            mensajes.add("Tel Fijo : Campo vacío.");         
        }else{
            if(!HelpersValidacion.isNumeric(telFijo)){
                validacion = false;
                mensajes.add("Tel Fijo : Ingrese solo nros. ");
            }
        }
        if(telCel.isEmpty()){ 
            validacion = false;
            mensajes.add("Tel Cel : Campo vacío.");         
        }else{
            if(!HelpersValidacion.isNumeric(telCel)){
                validacion = false;
                mensajes.add("Tel Cel : Ingrese solo nros.");
            }
        }
        if(eMail.isEmpty()){ 
            validacion = false;
            mensajes.add("E-Mail : Campo vacío.");         
        }else{
            String emailreg = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            Boolean b = eMail.matches(emailreg);
            if(!b){
                validacion = false;
                mensajes.add("E-Mail : Ingrese un E-Mail válido");
            }
        }
        
        return mensajes;
    }
    
    private int setModificarAlumno(HttpServletRequest request, Modelo m, ArrayList<String> mensajes){
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

        mensajes.addAll(validar(nroLegajo, nombre, apellido, fechaNacimiento, nroDoc, calle, nroCalle, piso, dpto, codPostal, localidad, telFijo, telCel, eMail));
        
        if (mensajes.size() > 0){
            return 2;
        }else{
            return m.qryModificarAlumno(nroLegajo, nombre, apellido, fechaNacimiento, nroDoc, calle, nroCalle, piso, dpto, codPostal, localidad, telFijo, telCel, eMail);
        }
    }
    
    public static ArrayList<Alumno> GetAlumnos(){
        Modelo mod = new Modelo();
        mod.cargaArrayAlumno(null, null, null, null);
        return mod.getArrayAlumnos();
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
