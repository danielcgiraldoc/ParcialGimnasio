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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
