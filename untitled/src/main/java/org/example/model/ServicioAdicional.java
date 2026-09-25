package org.example.model;

public class ServicioAdicional {
    private String codigo;
    private String nombre;
    private String descripcion;
    private double valorServicio;
    private boolean disponibilidad;
    private Entrenador theEntrenador;

    /**
     * Constructor Servivio adicional
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorServicio
     * @param disponibilidad
     */
    public ServicioAdicional(String codigo, String nombre, String descripcion, double valorServicio, boolean disponibilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorServicio = valorServicio;
        this.disponibilidad = disponibilidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getValorServicio() {
        return valorServicio;
    }

    public void setValorServicio(double valorServicio) {
        this.valorServicio = valorServicio;
    }

    public boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public Entrenador getTheEntrenador() {
        return theEntrenador;
    }

    public void setTheEntrenador(Entrenador theEntrenador) {
        this.theEntrenador = theEntrenador;
    }
}
