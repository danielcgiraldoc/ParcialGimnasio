package org.example.model;

import java.time.LocalDate;

public class Cliente extends Persona{

    private String correo;
    private int edad;
    private LocalDate fechaResgistro;
    private PlanBasico thePlan;



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
     * Metodo entrenar del entrendor
     */
    @Override
    public void entrenar() {
        System.out.println("El cliente está entrenando");
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
        public Cliente build () {
            return new Cliente(this);
        }
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "correo='" + correo + '\'' +
                ", edad=" + edad +
                ", fechaResgistro=" + fechaResgistro +
                ", nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
