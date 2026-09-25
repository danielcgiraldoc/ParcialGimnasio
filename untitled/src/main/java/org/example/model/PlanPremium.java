package org.example.model;

public class PlanPremium extends PlanBasico {

    private boolean areasDeportivas;
    private boolean clasesGrupales;

    public PlanPremium(Builder builder) {
        super(builder.codigo, builder.nombre, builder.descripcion, builder.duracionMeses, builder.valorMensual, builder.estado);
        this.areasDeportivas = builder.areasDeportivas;
        this.clasesGrupales = builder.clasesGrupales;
    }


    /**
     * Clase Builder del plan premium
     */
    public static class Builder {

        private final String codigo;
        private final String nombre;
        private final String descripcion;
        private final int duracionMeses;
        private final double valorMensual;
        private final Estado estado;
        private boolean areasDeportivas;
        private boolean clasesGrupales;

        public Builder(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.descripcion = descripcion;
            this.duracionMeses = duracionMeses;
            this.valorMensual = valorMensual;
            this.estado = estado;
        }

        public Builder areasDeportivas(boolean areasDeportivas) {
            this.areasDeportivas = areasDeportivas;
            return this;
        }

        public Builder clasesGrupales(boolean clasesGrupales) {
            this.clasesGrupales = clasesGrupales;
            return this;
        }

        public PlanPremium build() {
            return new PlanPremium(this);
        }
    }
}