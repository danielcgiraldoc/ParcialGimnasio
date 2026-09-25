package org.example.model;

import java.time.LocalDate;

public class PlanPersonalizado extends PlanBasico{

    private int cantidadSesiones;
    private EspecialidadRequerida especialidadRequerida;
    private String objetivo;
    private Entrenador theEntrenador;

    /**
     * Constructor para plan personalizado
     * @param builder
     */
    public PlanPersonalizado(Builder builder) {
        super(builder.codigo, builder.nombre, builder.descripcion, builder.duracionMeses, builder.valorMensual, builder.estado);
        this.cantidadSesiones = builder.cantidadSesiones;
        this.especialidadRequerida = builder.especialidadRequerida;
        this.objetivo = builder.objetivo;
    }


    /**
     * Clase builder del plan personalizado
     */
    public static class Builder {

        private final String codigo;
        private final String nombre;
        private final String descripcion;
        private final int duracionMeses;
        private final double valorMensual;
        private final Estado estado;
        private int cantidadSesiones;
        private EspecialidadRequerida especialidadRequerida;
        private String objetivo;

        /**
         * Obliga a tener estos campos llenos
         * @param codigo
         * @param nombre
         * @param descripcion
         * @param duracionMeses
         * @param valorMensual
         * @param estado
         */
        public Builder (String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, Estado estado){
            this.codigo = codigo;
            this.nombre = codigo;
            this.descripcion = descripcion;
            this.duracionMeses= duracionMeses;
            this.valorMensual = valorMensual;
            this.estado = estado;

        }
        public Builder cantidadSesiones (int cantidadSesiones) {
            this.cantidadSesiones = cantidadSesiones;
            return this;
        }
        public Builder especialidadRequerida (EspecialidadRequerida especialidadRequerida) {
            this.especialidadRequerida = especialidadRequerida;
            return this;
        }
        public Builder objetivo (String  objetivo) {
            this.objetivo = objetivo;
            return this;
        }
        public PlanPersonalizado build () {
            return new PlanPersonalizado(this);
        }
    }

}
