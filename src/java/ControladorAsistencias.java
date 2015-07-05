

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.Console;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
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
@WebServlet(urlPatterns = {"/ControladorAsistencias"})
public class ControladorAsistencias extends HttpServlet {

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
        RequestDispatcher vista;
        
        String codCurso = request.getParameter("codCurso");
        String nroClase = request.getParameter("nroClase");
        
        switch(method){
            case "buscarListado":
                ArrayList<Asistencia> asistencias = null;
                asistencias = getAsistenciasByCodCursoNroClase();
                
                request.setAttribute("codCurso", codCurso);
                request.setAttribute("nroClase", nroClase);
                request.setAttribute("asistencias", asistencias);
                
                vista = request.getRequestDispatcher("screens/asistencias/listaAsistencias.jsp");
                vista.forward(request, response);
                break;
            
            case "guardarListado":
                resul = setGuardarListado(request, m);
                if (resul == 1){
                    mensajeTitulo = "Listado de Asistencias Guardado!";
                    mensaje = "El listado de asistencias ha sido guardado.";
                    estado = "SUCCESS";
                }else{
                    mensajeTitulo = "Error";
                    mensaje = "Hubo un error al intentar guardar el listado de asistencias. Intente nuevamente. ";
                    estado = "ERROR";
                }
                break;
        }
        
        request.setAttribute("mensajeTitulo", mensajeTitulo);
        request.setAttribute("mensaje", mensaje);
        request.setAttribute("estado", estado);
        
        vista = request.getRequestDispatcher("screens/vistaMensaje.jsp");
        vista.forward(request, response);
    }
    
    private ArrayList<Asistencia> getAsistenciasByCodCursoNroClase(){
        String codCurso = request.getParameter("codCurso");
        String nroClase = request.getParameter("nroClase");

        m.cargaArrayCurso();
        Curso a = m.getCursoWithCode(codCurso);
        if (a != null){
            if (Integer.valueOf(nroClase) > 0 && Integer.valueOf(nroClase) < (Integer.valueOf(a.getCursoCantClases()) + 1)){
                m.cargaArrayAsistencia(codCurso, Integer.valueOf(nroClase), 0);
                m.setPosAsistencias(0);
                
                return m.getArrayAsistencias();
            }else{
                //v.limpiarInputsAsistencia();
                //v.showErrorMsg("Nro de clase no permitido");
            }
        }else{
            //v.limpiarInputsAsistencia();
            //v.showErrorMsg("Cod curso inexistente");
        }
        
        return new ArrayList<Asistencia>();
    }
    
    
    private int setGuardarListado(HttpServletRequest request, Modelo m){
        String codCurso = request.getParameter("codCurso");
        String nroClase = request.getParameter("nroClase");
        
        ArrayList<String> alreadyChecked = new ArrayList<String>();
        Enumeration<String> parameters =  request.getParameterNames();
        while(parameters.hasMoreElements()) {
            String paramName = parameters.nextElement();
            if (paramName.startsWith("asist_")){
                String nroLegajo = paramName.replace("asist_", "");
                String asistencia = request.getParameter(paramName);
                boolean asist = false;
                asist = (asistencia.toUpperCase().equalsIgnoreCase("ON")) ? true : false;
                m.qryUpsertAsistencia(nroLegajo, codCurso , nroClase , asist);
                
                alreadyChecked.add(nroLegajo);
            }
        }
        
        parameters =  request.getParameterNames();
        while(parameters.hasMoreElements()) {
            String paramName = parameters.nextElement();
            if (paramName.startsWith("asistFallback_")){
                String nroLegajo = paramName.replace("asistFallback_", "");
                if (!alreadyChecked.contains(nroLegajo)){
                    String asistencia = request.getParameter(paramName);
                    boolean asist = false;
                    asist = (asistencia.toUpperCase().equalsIgnoreCase("ON")) ? true : false;
                    m.qryUpsertAsistencia(nroLegajo, codCurso , nroClase , asist);
                }
            }
        }
        
        return 1;
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
