package Bug;

import java.time.LocalDate;

public class Bug {
    private String ID;
    private int prioridad;
    private int nivelSeguridad;
    private String estado;
    private int categoria;
    private LocalDate fecha;

    public Bug(String ID, int prioridad, int nivelseguridad, String estado, int categoria){
        this.ID=ID;
        this.prioridad=prioridad;
        this.nivelSeguridad=nivelseguridad;
        this.estado=estado;
        this.categoria=categoria;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getNivelSeguridad() {
        return nivelSeguridad;
    }

    public void setNivelSeguridad(int nivelSeguridad) {
        this.nivelSeguridad = nivelSeguridad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
