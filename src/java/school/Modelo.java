package school;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.logging.Level;
import java.util.logging.Logger;


public final class Modelo {
    
    private ArrayList<Alumno> arrayAlumnos ; // array de todos los alumnos. 
    private ArrayList<Curso> arrayCursos; // array de todos los cursos
    private ArrayList<Pago> arrayPagos; // array de todos los pagos
    private ArrayList<Prestamo> arrayPrestamos; // array de todos los prestamos
    private ArrayList<Recurso> arrayRecursos; // arrayde todos los recursos
    private ArrayList<Asistencia> arrayAsistencias; // array de todos las asistencias
    private int posAlumnos = 0; // posición actual del array de registro 
    private int posCursos = 0; // posición actual del array de registro 
    private int posPagos = 0; // posición actual del array de registro 
    private int posPrestamos = 0; // posición actual del array de registro 
    private int posRecursos = 0; // posición actual del array de recurso 
    private int posAsistencias = 0;
    private Connection db;
    private Statement statement;
    private String databaseName;
    
    // ----- CONSTRUCTOR -----
    public Modelo(){
        databaseName = "dptoAlumnos";
        arrayAlumnos = new ArrayList<Alumno>();
        arrayCursos = new ArrayList<Curso>();
        arrayPagos = new ArrayList<Pago>();
        arrayPrestamos = new ArrayList<Prestamo>();
        arrayRecursos = new ArrayList<Recurso>();
        arrayAsistencias = new ArrayList<Asistencia>();
    }
    
    // ----- CONEXION BASE DE DATOS -----
    public Connection conexionSQL(String ip , String usr , String psw , String bd){
        Connection db = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            db = DriverManager.getConnection("jdbc:mysql://" + ip + "/" + bd, usr, psw);
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return db;
    }

    public ArrayList<Alumno> getArrayAlumnos() {
        return arrayAlumnos;
    }
    
    public Statement statementSQL(Connection db){
        Statement statement = null;
        try {
            statement = db.createStatement();
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return statement;
    }
    
    private void openDBConnection(){
        db = conexionSQL("localhost" , "root" , "" , databaseName);
        statement = statementSQL(db);
    }
    
    private void closeDBConnection(){
        try {
            if (statement != null) {
                statement.close();
            }
            if (db != null) {
                db.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ResultSet executeQuery(String strSql){
        try {
            return statement.executeQuery(strSql);
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public int executeUpdate(String strSql){
        try {
            return statement.executeUpdate(strSql);
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    // ----- LEVANTA DATOS -----
    public void cargaArrayAlumno(String nroLegajo , String modo , String nroClase , String nroCurso){ // cargo en un array todos los registros de la tabla alumno.
        String qry = "SELECT * FROM alumnos";
        
        if (nroLegajo != null) qry += " WHERE nroLegajo = " + nroLegajo;
        if(modo != null && nroClase != null && nroCurso != null){
            if(modo == "asistencia"){
                qry = "SELECT * FROM alumnos INNER JOIN asistencias ON alumnos.nroLegajo = asistencias.nroLegajo ";
                qry+= "WHERE asistencias.nroClase = '"+nroClase+"' AND asistencias.nroCurso = '"+nroCurso+"';";
            }
        }
        ResultSet rs = null;
        this.arrayAlumnos.clear(); // borro el array para despues cargarlo denuevo . 
        this.posAlumnos = 0; //reseteo la posicion a 0 por si hay menos o mas alumnos
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                Alumno a = new Alumno();
                a.setNroLegajo(rs.getString(1));
                a.setNombre(rs.getString(2));
                a.setApellido(rs.getString(3));
                a.setFechaNacimiento(rs.getString(4));
                a.setNroDoc(rs.getString(5));
                a.setCalle(rs.getString(6));
                a.setNroCalle(rs.getString(7));
                a.setPiso(rs.getString(8)); 
                a.setDepartamento(rs.getString(9)); 
                a.setCodPostal(rs.getString(10));
                a.setLocalidad(rs.getString(11));
                a.setTelFijo(rs.getString(12));
                a.setTelCel(rs.getString(13));
                a.seteMail(rs.getString(14));
                
                arrayAlumnos.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
    }

    public void cargaArrayCurso(){ // cargo en un array todos los registros de la tabla Cursos.
        String qry = "SELECT * FROM cursos";
        ResultSet rs = null;
        this.arrayCursos.clear(); // borro el array para despues cargarlo denuevo . 
        this.posCursos = 0;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                Curso a = new Curso();
                a.setCursoCod(rs.getString(1));                
                a.setCursoNombre(rs.getString(2));
                a.setCursoProf(rs.getString(3));
                a.setCursoCantClases(rs.getString(4));
                arrayCursos.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
    }
    
    public void cargaArrayPago(){ // cargo en un array todos los registros de la tabla Pagos.
        String qry = "SELECT * FROM pagos";
        ResultSet rs = null;
        this.arrayPagos.clear(); // borro el array para despues cargarlo denuevo . 
        this.posPagos = 0;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                Pago a = new Pago();
                a.setPagoNroLegajo(rs.getString(1));
                a.setPagoCodCurso(rs.getString(2));
                a.setPagoFecha(rs.getString(3));
                a.setPagoImporte(rs.getFloat(4));
                a.setPagoComprobante(rs.getString(5));
              
                arrayPagos.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
    }
    
    public void cargaArrayPrestamo(){ // cargo en un array todos los registros de la tabla Prestamos.
        String qry = "SELECT * FROM prestamos";
        System.out.println(qry);
        ResultSet rs = null;
        this.arrayPrestamos.clear(); // borro el array para despues cargarlo denuevo . 
        this.posPrestamos = 0;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                Prestamo a = new Prestamo();
                a.setNroLegajo(rs.getString(1));
                a.setCodRecurso(rs.getString(2));
                a.setFechaPrestamo(rs.getString(3));
                a.setFechaDevolucion(rs.getString(4));
                a.setFechaPrevistaDevolucion(rs.getString(5));
                
                arrayPrestamos.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
    }
    
    public void cargaArrayRecurso(){ // cargo en un array todos los registros de la tabla Prestamos.
        String qry = "SELECT * FROM recursos";
        ResultSet rs = null;
        this.arrayRecursos.clear(); // borro el array para despues cargarlo denuevo . 
        this.posRecursos = 0;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                Recurso a = new Recurso();
                a.setCodRecurso(rs.getString(1));
                a.setCategoria(rs.getString(2));
                a.setNombre(rs.getString(3));
                a.setAutor(rs.getString(4));
                a.setAnio(rs.getString(5));
                a.setCant(rs.getString(6));
                a.setFoto(rs.getString(7));
                
                arrayRecursos.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
    }
    
    public int qryCantAsistCurso(String codCurso){ //calcula cuantas clases tiene cargadas asistencia un curso
        String qry;
        qry = "SELECT COUNT(nroClase) FROM asistencias WHERE codCurso = "+codCurso+" GROUP BY codCurso;";
        ResultSet rs = null;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
        return -1;
    }

    public void cargaArrayAsistencia(String codCurso , Integer nroClase , int mode){ // cargo en un array los datos requeridos de la tabla Asistencia.
        // mode = 0 -> Levanta una sola fecha para volcar en los inputs
        // mode = 1 -> Levanta todas las fechas para realizar el informe de asistencias
        String qry;
        if(mode == 0){
            //qry = "SELECT nroLegajo , codCurso , nroClase , asistencia , nombre , apellido FROM asistencias INNER JOIN alumnos ON asistencias.nroLegajo = alumno.nroLegajo WHERE codCurso = '"+codCurso+"' and nroClase = "+Integer.toString(nroClase)+" ORDER BY apellido;";    
        
            qry = "SELECT alumnos.nroLegajo, rel_alumnos_cursos.codCurso, IFNULL(asistencias.nroClase, " + nroClase.toString() + ") AS nroClase, IFNULL(asistencias.asistencia, 0) AS asistencia, alumnos.nombre, alumnos.apellido FROM alumnos"
                   + " INNER JOIN rel_alumnos_cursos ON alumnos.nroLegajo = rel_alumnos_cursos.nroLegajo" 
                   + " LEFT JOIN asistencias ON asistencias.nroLegajo = alumnos.nroLegajo AND asistencias.codCurso = rel_alumnos_cursos.codCurso AND asistencias.nroClase = " + nroClase.toString()
                   + " WHERE rel_alumnos_cursos.codCurso = " + codCurso
                   //+ " AND (asistencias.nroClase = " + nroClase.toString() + " OR asistencias.nroClase IS NULL)"
                   + " ORDER BY alumnos.nombre, alumnos.apellido";
            
        }else{
            qry = "SELECT nroLegajo , codCurso , nroClase , asistencia , nombre , apellido "
            + "FROM asistencias INNER JOIN alumno ON asistencias.nroLegajo = alumnos.nroLegajo "
            + "WHERE codCurso = '"+codCurso+"' ORDER BY apellido , nroClase;"; 
        }
        ResultSet rs = null;
        this.arrayAsistencias.clear(); // borro el array para despues cargarlo denuevo . 
        this.posAsistencias = 0;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                Asistencia a = new Asistencia();
                a.setNroLegajo(rs.getString(1));
                a.setCodCurso(rs.getString(2));
                a.setNroClase(rs.getInt(3));
                a.setAsistencia(rs.getInt(4));
                a.setNombreApellido(rs.getString(5)+" "+rs.getString(6));
                
                arrayAsistencias.add(a);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
    }
    
    public Dictionary<Integer, String> getAlumnosAsistencias(){
        String qry;
        qry = "SELECT nroLegajo, nombre , apellido FROM alumnos "
                + " ORDER BY nroLegajo";
        ResultSet rs = null;
        Dictionary<Integer, String> arrayAlumnosCurso = null;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                arrayAlumnosCurso.put(Integer.parseInt(rs.getString(1)), rs.getString(2) + " " + rs.getString(3));
            }
            return arrayAlumnosCurso;
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
        return null;
    }
    
    // ----- MUESTRA DATOS -----
    public String getAlumnos(String modo){ // levanto un alumno del array list y genero un string por registro para mostrar los datos.
        String rs = "";
        for(int i = 0 ; i < arrayAlumnos.size() ; i++){
            rs += arrayAlumnos.get(i).getNroLegajo() + " - ";
            rs += arrayAlumnos.get(i).getNombre() + " ";
            rs += arrayAlumnos.get(i).getApellido() + "\n";
            if(modo != "asistencias"){
                rs += arrayAlumnos.get(i).getFechaNacimiento();
                rs += arrayAlumnos.get(i).getNroDoc();
                rs += arrayAlumnos.get(i).getCalle();
                rs += arrayAlumnos.get(i).getNroCalle();
                rs += arrayAlumnos.get(i).getPiso();
                rs += arrayAlumnos.get(i).getDepartamento();
                rs += arrayAlumnos.get(i).getCodPostal();
                rs += arrayAlumnos.get(i).getLocalidad();
                rs += arrayAlumnos.get(i).getTelFijo();
                rs += arrayAlumnos.get(i).getTelCel();
                rs += arrayAlumnos.get(i).geteMail() + "\n";
            }
        }
        return rs;
    }
    
    public String getCursos(){ // levanto un alumno del array list y genero un string por registro para mostrar los datos.
        String rs = "";
        for(int i = 0 ; i < arrayCursos.size() ; i++){
            rs += arrayCursos.get(i).getCursoCod();
            rs += arrayCursos.get(i).getCursoNombre();
            rs += arrayCursos.get(i).getCursoProf() + "\n";
        }
        return rs;
    }
    
    public String getPagos(){ // levanto un alumno del array list y genero un string por registro para mostrar los datos.
        String rs = "";
        for(int i = 0 ; i < arrayPagos.size() ; i++){
            rs += arrayPagos.get(i).getPagoCodCurso();
            rs += arrayPagos.get(i).getPagoNroLegajo();
            rs += arrayPagos.get(i).getPagoFecha();
            rs += arrayPagos.get(i).getPagoComprobante() + "\n";
        }
        return rs;
    }
    
    public String getPrestamos(){ // levanto un alumno del array list y genero un string por registro para mostrar los datos.
        String rs = "";
        for(int i = 0 ; i < arrayPagos.size() ; i++){
            rs += arrayPagos.get(i).getPagoCodCurso();
            rs += arrayPagos.get(i).getPagoNroLegajo();
            rs += arrayPagos.get(i).getPagoFecha();
            rs += arrayPagos.get(i).getPagoComprobante() + "\n";
        }
        return rs;
    }
    
    public String getAsistencias(){ // levanto un alumno del array list y genero un string por registro para mostrar los datos.
        String rs = "";
        for(int i = 0 ; i < arrayAsistencias.size() ; i++){
            rs += arrayAsistencias.get(i).getNroLegajo();
            rs += arrayAsistencias.get(i).getCodCurso();
            rs += arrayAsistencias.get(i).getNroClase();
            rs += arrayAsistencias.get(i).getAsistencia() + "\n";
        }
        return rs;
    }
    
    // ------ CONSULTAS -------
    
    public int alumnoIsAvailable(String nroLegajo , String codCurso , String nroClase){
        // me fijo si existe el alumno
        String qry = "SELECT * FROM alumnos WHERE nroLegajo = '"+nroLegajo+"';";
        ResultSet rs = null;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                // si existe el alumno me fijo si ya no se ingreso un registro de asistencia para ese alumno
                qry = "SELECT * FROM alumnos INNER JOIN asistencias ON alumnos.nroLegajo = asistencias.nroLegajo WHERE nroLegajo = '"+nroLegajo+"' AND asistencias.codCurso = '"+codCurso+"' AND asistencias.nroClase = '"+nroClase+"';";
                rs = null;
                this.openDBConnection();
                try {
                    rs = this.executeQuery(qry);
                } catch (Exception ex) {
                    Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    if (rs != null) {
                        rs.close();
                        return 1 ; // el nroLegajo ya tiene ingresado un registro de asistencia
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
                    return 2; // no existe registro de asistencias -> se puede cargar registros de asistencia 
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
            return 0; // no existe el alumno 
        }
        this.closeDBConnection();
        return -1;// error 
    
    }
    
    public int qryUpsertAsistencia(String nroLegajo, String codCurso, String nroClase, boolean asistencia){
        String qry = "SELECT * FROM asistencias "
                + "WHERE nroLegajo = " + nroLegajo + " AND nroClase =" + nroClase + " AND codCurso = " + codCurso;
        openDBConnection();
        ResultSet rs = executeQuery(qry);
        try {
            if (rs.last()){
                closeDBConnection();
                return qryModificarAsistencia(nroLegajo, codCurso, nroClase, asistencia);
            }else{
                closeDBConnection();
                return qryAltaAsistencia(nroLegajo, codCurso, nroClase, asistencia);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return 0;
    }
   
    public int qryAltaAlumno(String nroLegajo , String nombre , String apellido , String fechaNacimiento , String nroDoc , String calle , String nroCalle , String piso , String dpto , String codPostal , String localidad , String telFijo , String telCel , String eMail){
        String qry;
        qry = "INSERT INTO alumnos (nroLegajo , nombre , apellido , fechaNacimiento , nroDoc , calle , nro , piso , depto , codPostal , localidad , telFijo , telCel , eMail) ";
        qry+= "VALUES ("+nroLegajo+",'"+nombre+"','"+apellido+"','"+fechaNacimiento+"','"+nroDoc+"','"+calle+"','"+nroCalle+"','"+piso+"','"+dpto+"','"+codPostal+"','"+localidad+"','"+telFijo+"','"+telCel+"','"+eMail+"');";
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryAltaCurso(String codCurso , String nombre , String prof, String cantClases){
        String qry; // revisar los campos de la tabla
        qry = "INSERT INTO cursos (codCurso , nombre , prof, cantClases)";
        qry+= "VALUES ("+ codCurso +" , '"+ nombre +"' , '"+ prof +"', " + cantClases + ");";
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryAltaRecurso(String codRecurso , String nombre , String anio , String categoria , String autor , String cant){
        String qry; // revisar los campos de la tabla 
        qry = "INSERT INTO recursos (codRec , nombre , anio , categoria , autor , cant)";
        qry+= "VALUES ('"+codRecurso+"' , '"+nombre+"' , '"+anio+"' , '"+categoria+"' , '"+autor+"' , '"+cant+"');";
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }   
    
    public int qryAltaPago(String nroLegajo , String codCurso , String fecha , String importe , String comprobante){
        String qry; // revisar los campos de la tabla 
        qry = "INSERT INTO pagos (nroLegajo , codCurso , fecha , importe , comprobante) ";
        qry+= "VALUES ("+nroLegajo+" , "+codCurso+" , '"+fecha+"' , "+importe+" , '"+comprobante+"' );";
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryAltaPrestamo(String nroLegajo , String codRecurso , String fechaPrestamo , String fechaPrevistaDevolucion , String fechaDevolucion ){
        String qry; // revisar los campos de la tabla 
        if (fechaDevolucion != null){
            qry = "INSERT INTO prestamos (nroLegajo , codRecurso , fechaPres , fechaDevo , fechaPrevDevo) ";
            qry+= "VALUES ("+nroLegajo+" , '"+codRecurso+"' , '"+fechaPrestamo+"' , '"+fechaDevolucion+"' , '"+fechaPrevistaDevolucion+"');";
        }else{
            qry = "INSERT INTO prestamos (nroLegajo , codRecurso , fechaPres , fechaPrevDevo) ";
            qry+= "VALUES ("+nroLegajo+" , '"+codRecurso+"' , '"+fechaPrestamo+"' , '"+fechaPrevistaDevolucion+"');";
        }
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryAltaAsistencia(String nroLegajo , String codCurso , String nroClase , boolean asistencia){
        String qry; // revisar los campos de la tabla 
        qry = "INSERT INTO asistencias (nroLegajo , codCurso , nroClase , asistencia) ";
        qry+= "VALUES ("+nroLegajo+" , "+codCurso+" , '"+nroClase+"' , '"+ ((asistencia == true) ? 1 : 0) +"');";
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryModificarAsistencia(String nroLegajo , String codCurso , String nroClase , boolean asistencia){
         String qry;
         qry = "UPDATE asistencias SET "
                 + "nroLegajo = " + "'"+nroLegajo+"' "
                 + ", codCurso = " + "'"+codCurso+"' "
                 + ", nroClase = " + "'"+nroClase+"' "
                 + ", asistencia = " +asistencia+" ";
         qry += "WHERE nroLegajo = " + nroLegajo +" AND codCurso = " + codCurso + " AND nroClase = " + nroClase;
         
         openDBConnection();
         int q = executeUpdate(qry);
         closeDBConnection();
         return q;
     }
    
    public int qryModificarAlumno(String nroLegajo , String nombre , String apellido , String fechaNacimiento , String nroDoc , String calle , String nroCalle , String piso , String dpto , String codPostal , String localidad , String telFijo , String telCel , String eMail){
        String qry;
        qry = "UPDATE alumnos SET "
                + "nombre = " + "'"+nombre+"'"
                + ", apellido = '" + apellido + "' "
                + ", fechaNacimiento = " + "'"+fechaNacimiento+"'"
                + ", nroDoc = "+ "'"+nroDoc+"'"
                + ", calle = "+ "'"+calle+"'"
                + ", nro = "+ "'"+nroCalle+"'"
                + ", piso = "+ "'"+piso+"'"
                + ", depto = "+ "'"+dpto+"'"
                + ", codPostal = "+ "'"+codPostal+"'"
                + ", localidad = "+ "'"+localidad+"'"
                + ", telFijo = "+ "'"+telFijo+"'"
                + ", telCel = "+ "'"+telCel+"'"
                + ", eMail = "+ "'"+eMail+"'"
                ;
        qry+= " WHERE nroLegajo = " + nroLegajo;
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryModificarCurso(String codCurso , String nombre , String prof, String cantClases){
        String qry;
        qry = "UPDATE cursos SET "
                + "nombre = " + "'"+nombre+"' "
                + ", prof = " + "'"+prof+"' "
                + ", cantClases = " +cantClases;
        
                qry += " WHERE codCurso = " + codCurso;
        
                System.out.println(qry);
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryAgregarAlumnoACurso(String codCurso, String nroLegajo){
        String qry;
        qry = "SELECT * FROM alumnos WHERE nroLegajo = " + nroLegajo;
        openDBConnection();
        ResultSet rs = executeQuery(qry);
        try {
            if (rs.next() == true){
                qry = "INSERT INTO rel_alumnos_cursos(nroLegajo, codCurso) VALUES(" + nroLegajo + ", " + codCurso + ")";
                int res = executeUpdate(qry);
                return 1;
            }else{
                return 2;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        closeDBConnection();
        return 0;
    }
    
    public int qryEliminarCurso(String codCurso){
        String qry;
        qry = "DELETE FROM cursos ";
        qry += "WHERE codCurso = " + codCurso;
        
        System.out.println(qry);
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryModificarRecurso(String codRecurso , String nombre , String anio , String categoria , String autor , String cant){
        String qry;
        qry = "UPDATE recursos SET "
                + "categoria = " + "'"+categoria+"' "
                + ", nombre = " + "'"+nombre+"' "
                + ", autor = " + "'"+autor+"' "
                + ", anio = " + "'"+anio+"' "
                + ", cant = " + "'"+cant+"' ";
        qry += "WHERE codRec = '" + codRecurso + "'";
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryModificarPrestamo(int id, String nroLegajo , String codRec , String fechaPres , String fechaPrevDevo , String fechaDevo){
        String qry;
        qry = "UPDATE prestamos SET "
                + "nroLegajo = " + "'"+nroLegajo+"' "
                + ", codRecurso = " + "'"+codRec+"' "
                + ", fechaPres = " + "'"+fechaPres+"' "
                + ", fechaPrevDevo = " + "'"+fechaPrevDevo+"' "
                + ((fechaDevo != null) ? ", fechaDevo = " + "'"+fechaDevo+"' " : "");
        qry += "WHERE id = " + id;
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryModificarPago(int id, String nroLegajo , String codCurso , String fecha , String importe , String comprobante){
        String qry;
        qry = "UPDATE pagos SET "
                + "nroLegajo = " + "'"+nroLegajo+"' "
                + ", codCurso = " + "'"+codCurso+"' "
                + ", fecha = " + "'"+fecha+"' "
                + ", importe = " +importe+" "
                + ", comprobante = " + "'"+comprobante+"' ";
        qry += "WHERE id = " + id;
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int qryEliminarPago(int id ){
        String qry;
        qry = "DELETE FROM pagos ";
        qry += "WHERE id = " + id;
        
        openDBConnection();
        int q = executeUpdate(qry);
        closeDBConnection();
        return q;
    }
    
    public int generateAlumnosRegularesPorCurso(Integer codCurso){
        String qry = "SELECT alumnos.nroLegajo, alumnos.nombre, alumnos.apellido FROM alumnos"
                + " INNER JOIN rel_alumnos_cursos ON rel_alumnos_cursos.nroLegajo = alumnos.nroLegajo AND rel_alumnos_cursos.codCurso = " + codCurso.toString()
                + " where alumnos.nroLegajo NOT IN "
                + "(SELECT alumnos.nroLegajo FROM alumnos" +
        " INNER JOIN rel_alumnos_cursos ON rel_alumnos_cursos.nroLegajo = alumnos.nroLegajo AND rel_alumnos_cursos.codCurso = " + codCurso.toString() +
        " LEFT JOIN asistencias ON asistencias.nroLegajo = rel_alumnos_cursos.nroLegajo AND asistencias.codCurso = rel_alumnos_cursos.codCurso" +
        " WHERE asistencias.asistencia = 0"+
        " GROUP BY alumnos.nroLegajo"+
        " HAVING COUNT(*) > ((SELECT cantClases FROM cursos WHERE cursos.codCurso = " + codCurso.toString() + ") - "
                + "(((SELECT cantClases FROM cursos WHERE cursos.codCurso = "+ codCurso.toString() +") * " + "(SELECT valor FROM config WHERE config = 'PORCENTAJE_REGULARIDAD' LIMIT 1)" + ") / 100)))";
        
        ResultSet rs = null;
        
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("alumnosRegularesPorCurso_curso_" + codCurso.toString() + ".txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.println("Alumnos regulares || Curso " + codCurso.toString() + " || Clases totales: " + this.getCursoWithCode(codCurso.toString()).getCursoCantClases());
        writer.println("------------------------------");
        
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                writer.println("Nro legajo: " + rs.getString(1));
                writer.println("Nombre: " + rs.getString(2));
                writer.println("Apellido: " + rs.getString(3));
                //writer.println("Asistencias: " + rs.getString(4));
                writer.println("------------------------------");
                //rs.getString();
               
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.close();
        this.closeDBConnection();
        return 1;
    }
    
    public ReportTable generateAlumnosRegularesPorCursoReportTable(Integer codCurso){
        ReportTable t;
        t = new ReportTable("");
        
        String qry = "SELECT alumnos.nroLegajo, alumnos.nombre, alumnos.apellido FROM alumnos"
                + " INNER JOIN rel_alumnos_cursos ON rel_alumnos_cursos.nroLegajo = alumnos.nroLegajo AND rel_alumnos_cursos.codCurso = " + codCurso.toString()
                + " where alumnos.nroLegajo NOT IN "
                + "(SELECT alumnos.nroLegajo FROM alumnos" +
        " INNER JOIN rel_alumnos_cursos ON rel_alumnos_cursos.nroLegajo = alumnos.nroLegajo AND rel_alumnos_cursos.codCurso = " + codCurso.toString() +
        " LEFT JOIN asistencias ON asistencias.nroLegajo = rel_alumnos_cursos.nroLegajo AND asistencias.codCurso = rel_alumnos_cursos.codCurso" +
        " WHERE asistencias.asistencia = 0"+
        " GROUP BY alumnos.nroLegajo"+
        " HAVING COUNT(*) > ((SELECT cantClases FROM cursos WHERE cursos.codCurso = " + codCurso.toString() + ") - "
                + "(((SELECT cantClases FROM cursos WHERE cursos.codCurso = "+ codCurso.toString() +") * " + "(SELECT valor FROM config WHERE config = 'PORCENTAJE_REGULARIDAD' LIMIT 1)" + ") / 100)))";
        
        ResultSet rs = null;
        
        t.getColumns().add(new ReportTableColumn("Nro. Legajo"));
        t.getColumns().add(new ReportTableColumn("Nombre"));
        t.getColumns().add(new ReportTableColumn("Apellido"));
        
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                ReportTableRow r;
                r = new ReportTableRow();
                r.getValues().add(rs.getString(1));
                r.getValues().add(rs.getString(2));
                r.getValues().add(rs.getString(3));
                
                t.getRows().add(r);
               
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeDBConnection();
        return t;
    }
    
    public int generatePrestamosNoDevueltos(){
        String qry = "SELECT alumnos.nroLegajo, alumnos.Nombre, alumnos.Apellido "
                + "FROM prestamos JOIN alumnos ON prestamos.nroLegajo = alumnos.nroLegajo "
                + "WHERE fechaDevo IS NULL or fechaDevo = ''";
        ResultSet rs = null;
        
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("prestamosNoDevueltos.txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.println("Alumnos con prestamos no devueltos");
        writer.println("------------------------------");
        
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                writer.println("Nro legajo: " + rs.getString(1));
                writer.println("Nombre: " + rs.getString(2));
                writer.println("Apellido: " + rs.getString(3));
                writer.println("------------------------------");
                //rs.getString();
               
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.close();
        this.closeDBConnection();
        return 1;
    }
    
    public ReportTable generatePrestamosNoDevueltosReportTable(){
        ReportTable t;
        t = new ReportTable("");
        
        String qry = "SELECT alumnos.nroLegajo, prestamos.codRecurso, alumnos.Nombre, alumnos.Apellido "
                + "FROM prestamos JOIN alumnos ON prestamos.nroLegajo = alumnos.nroLegajo "
                + "WHERE fechaDevo IS NULL or fechaDevo = ''";
        ResultSet rs = null;
        
        t.getColumns().add(new ReportTableColumn("Nro. Legajo"));
        t.getColumns().add(new ReportTableColumn("Cod. Recurso"));
        t.getColumns().add(new ReportTableColumn("Nombre"));
        t.getColumns().add(new ReportTableColumn("Apellido"));
        
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                ReportTableRow r;
                r = new ReportTableRow();
                r.getValues().add(rs.getString(1));
                r.getValues().add(rs.getString(2));
                r.getValues().add(rs.getString(3));
                r.getValues().add(rs.getString(4));
                
                t.getRows().add(r);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.closeDBConnection();
        return t;
    }
    
    public ReportTable generatePagosPorMes(int monthNumber, int yearNumber){
        ReportTable t;
        t = new ReportTable("");

        String qry = "SELECT alumnos.nroLegajo, alumnos.Nombre, alumnos.Apellido, "
                + " cursos.codCurso, cursos.Nombre, pagos.fecha, pagos.importe, pagos.comprobante "
                + "FROM pagos JOIN alumnos ON alumnos.nroLegajo = pagos.nroLegajo "
                + "JOIN cursos ON cursos.codCurso = pagos.codCurso "
                + " WHERE MONTH(pagos.fecha) = " + monthNumber + " AND "
                + " YEAR(pagos.fecha) = " + yearNumber;

        ResultSet rs = null;

        t.getColumns().add(new ReportTableColumn("Nro. Legajo"));
        t.getColumns().add(new ReportTableColumn("Nombre"));
        t.getColumns().add(new ReportTableColumn("Apellido"));
        t.getColumns().add(new ReportTableColumn("Código de Curso"));
        t.getColumns().add(new ReportTableColumn("Fecha"));
        t.getColumns().add(new ReportTableColumn("Importe"));
        t.getColumns().add(new ReportTableColumn("Comprobante"));

        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                ReportTableRow r;
                r = new ReportTableRow();
                r.getValues().add(rs.getString(1));
                r.getValues().add(rs.getString(2));
                r.getValues().add(rs.getString(3));
                r.getValues().add(rs.getString(4));
                r.getValues().add(rs.getString(5));
                r.getValues().add(rs.getString(6));
                r.getValues().add(rs.getString(7));

                t.getRows().add(r);
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.closeDBConnection();
        return t;
    }
    
    public ReportTable generatePrestamosPorAlumnos(int nroLegajo){
    ReportTable t;
    t = new ReportTable("");
    
    String qry = "SELECT alumnos.nroLegajo, alumnos.Nombre, alumnos.Apellido, prestamos.codRecurso, prestamos.fechaPres"
                + ", prestamos.fechaPrevDevo, prestamos.fechaDevo "
                + " FROM prestamos JOIN alumnos ON prestamos.nroLegajo = alumnos.nroLegajo";

    if (nroLegajo != -1) qry += " WHERE alumnos.nroLegajo = " + nroLegajo;

    ResultSet rs = null;
    
    t.getColumns().add(new ReportTableColumn("Nro. Legajo"));
    t.getColumns().add(new ReportTableColumn("Nombre"));
    t.getColumns().add(new ReportTableColumn("Apellido"));
    t.getColumns().add(new ReportTableColumn("Código de Curso"));
    t.getColumns().add(new ReportTableColumn("Fecha de Préstamo"));
    t.getColumns().add(new ReportTableColumn("Fecha Prevista de Devolución"));
    t.getColumns().add(new ReportTableColumn("Fecha de Devolución"));
    
    this.openDBConnection();
    try {
        rs = this.executeQuery(qry);
        while (rs.next()) {
            ReportTableRow r;
            r = new ReportTableRow();
            r.getValues().add(rs.getString(1));
            r.getValues().add(rs.getString(2));
            r.getValues().add(rs.getString(3));
            r.getValues().add(rs.getString(4));
            r.getValues().add(rs.getString(5));
            r.getValues().add(rs.getString(6));
            r.getValues().add(rs.getString(7));
            
            t.getRows().add(r);
        }
    } catch (Exception ex) {
        Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
    }
    try {
        if (rs != null) {
            rs.close();
        }
    } catch (Exception ex) {
        Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    this.closeDBConnection();
    return t;
}
    
    public int generatePrestamosPorAlumno(int nroLegajo){
        String qry = "SELECT alumnos.nroLegajo, alumnos.Nombre, alumnos.Apellido, prestamos.codRecurso, prestamos.fechaPres"
                + ", prestamos.fechaPrevDevo, prestamos.fechaDevo "
                + " FROM prestamos JOIN alumnos ON prestamos.nroLegajo = alumnos.nroLegajo";
        
        if (nroLegajo != -1) qry += " WHERE alumnos.nroLegajo = " + nroLegajo;
        
        ResultSet rs = null;
        
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("prestamosPorAlumno_"+ ((nroLegajo != -1) ? "legajo_" + nroLegajo : "todos") +".txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.println("Prestamos por alumno");
        writer.println("------------------------------");
        
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            
            String lastLegajo = "";
            
            while (rs.next()) {
                
                if (!lastLegajo.equals(rs.getString(1))){
                    writer.println("Nro legajo: " + rs.getString(1));
                    writer.println("Nombre: " + rs.getString(2));
                    writer.println("Apellido: " + rs.getString(3));
                    writer.println("------------------------------");
                    lastLegajo = rs.getString(1);
                }
                
                writer.println("---Prestamo---");
                writer.println("Cod Recurso: " + rs.getString(4));
                writer.println("Fecha Prestado: " + rs.getString(5));
                writer.println("Fecha Prevista de Devolución: " + rs.getString(6));
                writer.println("Fecha de devolución: " + rs.getString(5));
                writer.println("------------------------------");
            }
            
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.close();
        this.closeDBConnection();
        return 1;
    }

/*
    public int generatePagosPorMes(int monthNumber, int yearNumber){
        String qry = "SELECT alumnos.nroLegajo, alumnos.Nombre, alumnos.Apellido, "
                + " cursos.codCurso, cursos.Nombre, pagos.fecha, pagos.importe, pagos.comprobante "
                + "FROM pagos JOIN alumnos ON alumnos.nroLegajo = pagos.nroLegajo "
                + "JOIN cursos ON cursos.codCurso = pagos.codCurso "
                + " WHERE MONTH(pagos.fecha) = " + monthNumber + " AND "
                + " YEAR(pagos.fecha) = " + yearNumber;
        ResultSet rs = null;
        
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("pagosPorMes_mes_" + monthNumber + "_" + yearNumber + ".txt", "UTF-8");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.println("Pagos del mes: " + monthNumber);
        writer.println("------------------------------");
        
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                writer.println("--- Pago ---");
                writer.println("Nro Legajo: " + rs.getString(1));
                writer.println("Nombre y Apellido: " + rs.getString(2) + " " + rs.getString(3));
                writer.println("Cod Curso: " + rs.getString(4));
                writer.println("Nombre curso: " + rs.getString(5));
                writer.println("Fecha de pago: " + rs.getString(6));
                writer.println("Importe: " + rs.getString(7));
                writer.println("Comprobante: " + rs.getString(8));
                writer.println("------------------------------");
                //rs.getString();
               
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        writer.close();
        this.closeDBConnection();
        return 1;
    }
*/
    
    public ArrayList<String> getAlumnosCurso(String codCurso){ //calcula cuantas clases tiene cargadas asistencia un curso
        String qry;
        qry = "SELECT nombre , apellido FROM alumnos INNER JOIN cursos ON cursos.nroLegajo = alumnos.nroLegajo WHERE cursos.codCurso = "+codCurso;
        ResultSet rs = null;
        ArrayList<String> arrayAlumnosCurso = null;
        this.openDBConnection();
        try {
            rs = this.executeQuery(qry);
            while (rs.next()) {
                arrayAlumnosCurso.add(rs.getString(1)+" "+rs.getString(2));
            }
            return arrayAlumnosCurso;
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(Modelo.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.closeDBConnection();
        return null;
    }

    // ----- Getters -----
      // Levanta objetos de array
    public Alumno getAlumno(int i) {
        if (i < arrayAlumnos.size()){
            return arrayAlumnos.get(i);
        }else return new Alumno();
    }
    
    public Prestamo getPrestamo(int i) { 
        if (i < arrayPrestamos.size())
            return arrayPrestamos.get(i);
        else return new Prestamo();
    }
    
    public Recurso getRecurso(int i) {
        if (i < arrayRecursos.size())
            return arrayRecursos.get(i);
        else return new Recurso();
    }
    
    public Curso getCurso(int i) {
        if (i < arrayCursos.size())
            return arrayCursos.get(i);
        else return new Curso();
    }
    
    public Curso getCursoWithCode(String codCurso){
        for (int i = 0; i < getCantCursos(); i++){
            if (getCurso(i).getCursoCod().equals(codCurso)){
                return getCurso(i);
            }
        }
        return null;
    }
    
    public Recurso getRecursoWithCode(String codRecurso){
        for (int i = 0; i < getCantRecursos(); i++){
            if (getRecurso(i).getCodRecurso().equals(codRecurso)){
                return getRecurso(i);
            }
        }
        return null;
    }
    
    public Prestamo getPrestamoWithCode(String nroLegajo, String codRecurso){
        for (int i = 0; i < getCantPrestamos(); i++){
            if (getPrestamo(i).getCodRecurso().equals(codRecurso) && getPrestamo(i).getNroLegajo().equals(nroLegajo)){
                System.out.println("Encuentra");
                return getPrestamo(i);
            }
        }
        return null;
    }
    
    public Prestamo getPrestamoWithId(int id ){
        for (int i = 0; i < getCantPrestamos(); i++){
            if (getPrestamo(i).getId() == id){
                System.out.println("Encuentra");
                return getPrestamo(i);
            }
        }
        return null;
    }
    
    public ArrayList<Prestamo> getPrestamosWithCode(String nroLegajo, String codRecurso){
        
        ArrayList<Prestamo> arr_pres = new ArrayList<Prestamo>();
        
        for (int i = 0; i < getCantPrestamos(); i++){
            if (getPrestamo(i).getCodRecurso().equals(codRecurso) && getPrestamo(i).getNroLegajo().equals(nroLegajo)){
                System.out.println("Encuentra");
                arr_pres.add(getPrestamo(i));
            }
        }
        return arr_pres;
    }
    
    public Pago getPagoWithCode(String pagoNroLegajo, String pagoCodCurso){
        for (int i = 0; i < getCantPagos(); i++){
            if (getPago(i).getPagoNroLegajo().equals(pagoNroLegajo) && getPago(i).getPagoCodCurso().equals(pagoCodCurso)){
                System.out.println("Encuentra");
                return getPago(i);
            }
        }
        return null;
    }
    
    public Pago getPagoWithId(int id){
        for (int i = 0; i < getCantPagos(); i++){
            if (getPago(i).getId() == id){
                System.out.println("Encuentra");
                return getPago(i);
            }
        }
        return null;
    }
    
    public ArrayList<Pago> getPagosWithCode(String pagoNroLegajo, String pagoCodCurso){
        
        ArrayList<Pago> arr_pago = new ArrayList<Pago>();
        
        for (int i = 0; i < getCantPagos(); i++){
            if (getPago(i).getPagoNroLegajo().equals(pagoNroLegajo) && getPago(i).getPagoCodCurso().equals(pagoCodCurso)){
                System.out.println("Encuentra");
                arr_pago.add(getPago(i));
            }
        }
        return arr_pago;
    }
    
    public Pago getPago(int i) { 
        if (i < arrayPagos.size())
            return arrayPagos.get(i);
        else return new Pago();
    }

    public ArrayList<Asistencia> getArrayAsistencias() {
        return arrayAsistencias;
    }   
    
    // Devuelve tamaño de array 
    public int getCantAlumnos() {
        return arrayAlumnos.size();
    }
    
    public int getCantPrestamos() {
        return arrayPrestamos.size();
    }
    
    public int getCantRecursos() {
        return arrayRecursos.size();
    }
    
    public int getCantCursos() {
        return arrayCursos.size();
    }
    
    public int getCantPagos() {
        return arrayPagos.size();
    }
    
    public int getCantAsistencias(){
        return arrayAsistencias.size();
    }
    
      // Devuelve posicion actual del array de registros 
    public int getPosAlumnos() {
        return posAlumnos;
    }

    public int getPosCursos() {
        return posCursos;
    }

    public int getPosPagos() {
        return posPagos;
    }

    public int getPosPrestamos() {
        return posPrestamos;
    }

    public int getPosRecursos() {
        return posRecursos;
    }
    
    public int getPosAsistencias(){
        return posAsistencias;
    }
    
    
    
    
    // ----- Setters -----
      // Setea posición actual del array de registros 
    public void setPosAlumnos(int posAlumnos) {
        this.posAlumnos = posAlumnos;
    }
    
    public void setPosPrestamos(int posPrestamos) {
        this.posPrestamos = posPrestamos;
    }
    
    public void setPosRecursos(int posRecursos) {
        this.posRecursos = posRecursos;
    }
    
    public void setPosCursos(int posCursos) {
        this.posCursos = posCursos;
    }
    
    public void setPosPagos(int posPagos) {
        this.posPagos = posPagos;
    }
    
    public void setPosAsistencias(int posAsistencias){
        this.posAsistencias = posAsistencias;
    }
    
}