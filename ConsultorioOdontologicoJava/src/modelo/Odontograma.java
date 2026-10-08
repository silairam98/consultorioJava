package modelo;

public class Odontograma {

    private int id;
    private int historialId;
    private String numeroDiente;
    private String estado;
    private String observacion;

    public Odontograma() {
    }

    public Odontograma(
            int historialId,
            String numeroDiente,
            String estado,
            String observacion) {

        this.historialId = historialId;
        this.numeroDiente = numeroDiente;
        this.estado = estado;
        this.observacion = observacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHistorialId() {
        return historialId;
    }

    public void setHistorialId(int historialId) {
        this.historialId = historialId;
    }

    public String getNumeroDiente() {
        return numeroDiente;
    }

    public void setNumeroDiente(String numeroDiente) {
        this.numeroDiente = numeroDiente;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
}