package org.example.model;

import java.util.ArrayList;

public class PlanBasico {
    protected String codigo;
    protected String nombre;
    protected String descripcion;
    protected int duracionMeses;
    protected double valorMensual;
    protected Estado estado;
    protected Cliente theCliente;
    protected ArrayList<ServicioAdicional> listServiciosAdicionales;

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
        this.listServiciosAdicionales = new ArrayList<>();
    }

    /**
     * Metodo para calcular valor total
     */
    public double calcularValorTotal(){
        double resultado = valorMensual * duracionMeses;
        if (listServiciosAdicionales != null){
        for (ServicioAdicional s : listServiciosAdicionales){
            resultado += s.getValorServicio();
        }
        }
return valorMensual;
    }


    /**
     * Solicitar un servicio adicional
     * @param servicioAdicional
     */
    public void solicitarServicioAdicional(ServicioAdicional servicioAdicional){
        if (servicioAdicional.getDisponibilidad()){
        listServiciosAdicionales.add(servicioAdicional);
        }
    }


    @Override
    public String toString() {
        return "PlanBasico{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", duracionMeses=" + duracionMeses +
                ", valorMensual=" + valorMensual +
                ", estado=" + estado +
                ", theCliente=" + theCliente +
                ", listServiciosAdicionales=" + listServiciosAdicionales +
                '}';
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getDuracionMeses() {
        return duracionMeses;
    }

    public void setDuracionMeses(int duracionMeses) {
        this.duracionMeses = duracionMeses;
    }

    public double getValorMensual() {
        return valorMensual;
    }

    public void setValorMensual(double valorMensual) {
        this.valorMensual = valorMensual;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public Cliente getTheCliente() {
        return theCliente;
    }

    public void setTheCliente(Cliente theCliente) {
        this.theCliente = theCliente;
    }

    public ArrayList<ServicioAdicional> getListServiciosAdicionales() {
        return listServiciosAdicionales;
    }

    public void setListServiciosAdicionales(ArrayList<ServicioAdicional> listServiciosAdicionales) {
        this.listServiciosAdicionales = listServiciosAdicionales;
    }
}