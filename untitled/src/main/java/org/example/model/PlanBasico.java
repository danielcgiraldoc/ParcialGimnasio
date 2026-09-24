package org.example.model;

public class PlanBasico {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected int duracionMeses;
    protected double valorMensual;
    protected Estado estado;

    /**
     * Metodo constructor
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     */
    public PlanBasico(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.duracionMeses = duracionMeses;
        this.valorMensual = valorMensual;
        this.estado = estado;
    }
}