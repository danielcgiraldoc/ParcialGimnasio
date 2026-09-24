package org.example.model;

import java.time.LocalDate;

public class Cliente extends Persona{

    private String correo;
    private int edad;
    private LocalDate fechaResgistro;

    /**
     * Metodo constructor del Cliente
     * @param nombre
     * @param id
     * @param telefono
     * @param correo
     * @param edad
     * @param fechaResgistro
     */
    public Cliente(Builder builder) {
        super(nombre, id, telefono);
        this.correo = correo;
        this.edad = edad;
        this.fechaResgistro = fechaResgistro;
    }

    public static class Builder extends Persona{
        private String correo;
        private int edad;
        private LocalDate fechaResgistro;

        /**
         * Metodo constructor para la clase Persona
         * @param nombre
         * @param id
         * @param telefono
         */
        public Builder(String nombre, String id, String telefono) {
            super(nombre, id, telefono);
        }

        public Builder nombre (String nombre){
            this.nombre
        }
    }
}
