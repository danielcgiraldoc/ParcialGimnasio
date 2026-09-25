package org.example.model;

import javax.swing.border.EtchedBorder;
import java.time.LocalDate;
import java.util.ArrayList;

public class Entrenador extends Persona{

    private Especialidad especialidad;
    private double valorSesion;
    private ArrayList<PlanBasico> listPlanes;
    private ArrayList<ServicioAdicional> listServiciosAdicionales;

    /**
     * Metodo constructor Entrenador
     * @param builder
     */
    public Entrenador(Builder builder) {
        super(builder.nombre, builder.id, builder.telefono);
        this.especialidad = builder.especialidad;
        this.valorSesion = builder.valorSesion;
        this.listPlanes = new ArrayList<>();
    }

    /**
     * metodo para asignar planes de entrenamiento al entrenador
     * @param plan
     */
    public void asignarPlan(PlanBasico plan){
        listPlanes.add(plan);
    }
    /**
     * Metodo entrenar del entrendor
     */
    @Override
    public void entrenar() {
        System.out.println("El entrenador está activo");
    }


    /**
     * Clase builder
     */
    public static class Builder{
        private String nombre;
        private String id;
        private String telefono;
        private Especialidad especialidad;
        private double valorSesion;

    /**
     * Metodo constructor del nombre
     * @param nombre
     * @return
     */
    public Entrenador.Builder nombre (String nombre){
        this.nombre = nombre;
        return this;
    }
    /**
     * Metodo constructor del id
     * @param id
     * @return
     */
    public Entrenador.Builder id (String id){
        this.id = id;
        return this;
    }

    /**
     * Metodo constructor del telefono
     * @param telefono
     * @return
     */
    public Entrenador.Builder telefono (String telefono){
        this.telefono = telefono;
        return this;
    }

    /**
     * Metodo constructor de la especialidad
     * @param especialidad
     * @return
     */
    public Entrenador.Builder especialidad (Especialidad especialidad){
        this.especialidad = especialidad ;
        return this;
    }

    /**
     * Metodo constructor valor de la sesion
     * @param valorSesion
     * @return
     */
    public Entrenador.Builder valorSesion (double valorSesion){
        this.valorSesion = valorSesion;
        return this;
    }
    /**
     * Metodo builder para entrenador
     * @return
     */
    public Entrenador build () {
        return new Entrenador(this);
    }


}

    @Override
    public String toString() {
        return "Entrenador{" +
                "especialidad=" + especialidad +
                ", valorSesion=" + valorSesion +
                ", nombre='" + nombre + '\'' +
                ", id='" + id + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
