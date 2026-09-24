package org.example.model;

public abstract class Persona {
    protected String nombre;
    protected String id;
    protected String telefono;


    /**
     * Metodo constructor para la clase Persona
     * @param nombre
     * @param id
     * @param telefono
     */
    public Persona(String nombre, String id, String telefono) {
        this.nombre = nombre;
        this.id = id;
        this.telefono = telefono;
    }

    public abstract void entrenar();


}
