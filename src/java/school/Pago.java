package school;

public class Pago {
    private int id;
    private String pagoNroLegajo;
    private String pagoCodCurso;
    private String pagoFecha;
    private float pagoImporte;
    private String pagoComprobante;

    public Pago() {
        pagoNroLegajo = "";
        pagoCodCurso = "";
        pagoFecha = "";
        pagoImporte = 0;
        pagoComprobante = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPagoNroLegajo(String pagoNroLegajo) {
        this.pagoNroLegajo = pagoNroLegajo;
    }

    public void setPagoCodCurso(String pagoCodCurso) {
        this.pagoCodCurso = pagoCodCurso;
    }

    public void setPagoFecha(String pagoFecha) {
        this.pagoFecha = pagoFecha;
    }

    public void setPagoImporte(float pagoImporte) {
        this.pagoImporte = pagoImporte;
    }

    public void setPagoComprobante(String pagoComprobante) {
        this.pagoComprobante = pagoComprobante;
    }

    public String getPagoNroLegajo() {
        return pagoNroLegajo;
    }

    public String getPagoCodCurso() {
        return pagoCodCurso;
    }

    public String getPagoFecha() {
        return pagoFecha;
    }

    public float getPagoImporte() {
        return pagoImporte;
    }

    public String getPagoComprobante() {
        return pagoComprobante;
    }
    
   
}
