package org.example.model;

public class Entrenador extends Persona{

    private Especialidad especialidad;
    private double valorSesion;

    /**
     * Metodo constuctor para la clase entrenador
     * @param nombre
     * @param id
     * @param telefono
     * @param especialidad
     * @param valorSesion
     */
    public Entrenador(String nombre, String id, String telefono, Especialidad especialidad, double valorSesion) {
        super(nombre, id, telefono);
        this.especialidad = especialidad;
        this.valorSesion = valorSesion;
    }

}
