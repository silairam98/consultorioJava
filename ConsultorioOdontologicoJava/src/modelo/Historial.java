package modelo;

import java.sql.Date;

public class Historial {

    private int id;
    private int pacienteId;
    private Date fecha;
    private String consulta;
    private String tratamiento;
    private String observaciones;

    public Historial() {
    }

    public Historial(int pacienteId, Date fecha, String consulta,
                     String tratamiento, String observaciones) {

        this.pacienteId = pacienteId;
        this.fecha = fecha;
        this.consulta = consulta;
        this.tratamiento = tratamiento;
        this.observaciones = observaciones;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(int pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConsulta() {
        return consulta;
    }

    public void setConsulta(String consulta) {
        this.consulta = consulta;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}