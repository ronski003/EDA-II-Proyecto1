package Bug;

import java.time.LocalDate;
/**
 * @author Equipo 10
 * Clase Bug
 * Bug
 */
public class Bug {
    private int ID;
    private int prioridad;
    private int nivelSeguridad;
    private String estado;
    private String categoria;
    private LocalDate fecha;
    /**
     * Contructor de la clase bug
     * @param ID recibe el parametro id del bug
     * @param prioridad recibe el parametro de prioridad
     * @param nivelseguridad recibe el parametro de nivel de seguridad del bug
     * @param estado recibe el estado del bug como parametro
     * @param categoria recibe el parametro de la categoria del bug
     * @param fecha recibe el parametro de la fecha en la que sucedio el bug
     */
    public Bug(int ID, int prioridad, int nivelseguridad, String estado, String categoria,LocalDate fecha) {
        this.ID=ID;
        this.prioridad=prioridad;
        this.nivelSeguridad=nivelseguridad;
        this.estado=estado;
        this.categoria=categoria;
        this.fecha=fecha;
    }
    /**
     * metodo getter de id
     * @return regresa el valor del id
     */
    public int getID() {
        return ID;
    }
    /**
     * metodo setter de id
     * @param recibe el nuevo valor del id  y cambia el valor anterior del id
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * metodo getter de la prioridad
     * @return regresa el valor del prioridad
     */
    public int getPrioridad() {
        return prioridad;
    }
    /**
     * metodo setter de la prioridad
     * @param recibe el nuevo valor de la prioridad  y cambia el valor anterior de la prioridad
     */
    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }
    /**
     * metodo getter del nivelSeguridad
     * @return regresa el valor del nivelSeguridad
     */
    public int getNivelSeguridad() {
        return nivelSeguridad;
    }
    /**
     * metodo setter del nivelSeguridad
     * @param recibe el nuevo valor del nivelSeguridad  y cambia el valor anterior del nivelSeguridad
     */
    public void setNivelSeguridad(int nivelSeguridad) {
        this.nivelSeguridad = nivelSeguridad;
    }
    /**
     * metodo getter de estado
     * @return regresa el valor del estado
     */
    public String getEstado() {
        return estado;
    }
    /**
     * metodo getter del estado
     * @param recibe el nuevo valor del estado  y cambia el valor anterior del estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    /**
     * metodo getter de categoria
     * @return regresa el valor de categoria
     */
    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    /**
     * metodo getter de  fecha
     * @return regresa el valor de la fecha
     */
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

}
