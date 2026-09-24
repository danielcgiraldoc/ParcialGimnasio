package org.example.model;

import java.time.LocalDate;

public class Cliente extends Persona{

    private String correo;
    private int edad;
    private LocalDate fechaResgistro;

    /**
     * Constructor para el cliente
     * @param builder
     */
    public Cliente (Builder builder) {
        super(builder.nombre, builder.id, builder.telefono);
        this.correo = builder.correo;
        this.edad = builder.edad;
        this.fechaResgistro = builder.fechaResgistro;
    }


    /**
     * Clase Builder
     */
    public static class Builder {
        private String nombre;
        private String id;
        private String telefono;
        private String correo;
        private int edad;
        private LocalDate fechaResgistro;

        /**
         * Metodo constructor del nombre
         * @param nombre
         * @return
         */
        public Builder nombre (String nombre){
            this.nombre = nombre;
            return this;
        }
        /**
         * Metodo constructor del id
         * @param id
         * @return
         */
        public Builder id (String id){
            this.id = id;
            return this;
        }

        /**
         * Metodo constructor del telefono
         * @param telefono
         * @return
         */
        public Builder telefono (String telefono){
            this.telefono = telefono;
            return this;
        }

        /**
         * Metodo constructor del correo
         * @param correo
         * @return
         */
        public Builder corrreo (String correo){
            this.correo = correo ;
            return this;
        }

        /**
         * Metodo constructor de la edad
         * @param edad
         * @return
         */
        public Builder edad (int edad){
            this.edad = edad;
            return this;
        }

        /**
         * Metodo constructor de la fecha
         * @param fechaRegistro
         * @return
         */
        public Builder fechaRegistro (LocalDate fechaRegistro){
            this.fechaResgistro = fechaRegistro;
            return this;
        }

        /**
         * Metodo builder para cliente
         * @return
         */
        public Cliente builder () {
            return new Cliente(this);
        }
    }
}
