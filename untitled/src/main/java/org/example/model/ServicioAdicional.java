package org.example.model;

public class ServicioAdicional {
    private String codigo;
    private String nombre;
    private String descripcion;
    private String valorServicio;
    private boolean disponibilidad;

    /**
     * Constructor Servivio adicional
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorServicio
     * @param disponibilidad
     */
    public ServicioAdicional(String codigo, String nombre, String descripcion, String valorServicio, boolean disponibilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valorServicio = valorServicio;
        this.disponibilidad = disponibilidad;
    }
}
