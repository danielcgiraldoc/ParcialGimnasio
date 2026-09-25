package org.example.controller;

import org.example.model.EspecialidadRequerida;
import org.example.model.Estado;
import org.example.model.Gimnasio;
import org.example.model.PlanBasico;

public class PlanController {

    private Gimnasio gimnasio;

    public PlanController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public boolean registrarPlanBasico(String codigo, String nombre, String descripcion,
                                       int duracionMeses, double valorMensual, Estado estado) {

        if (gimnasio.consultarPlan(codigo) != null) {
            return false;
        }

        gimnasio.crearPlanBasico(
                codigo,
                nombre,
                descripcion,
                duracionMeses,
                valorMensual,
                estado
        );

        return true;
    }

    public boolean registrarPlanPersonalizado(String codigo, String nombre, String descripcion,
                                              int duracionMeses, double valorMensual,
                                              Estado estado, int cantidadSesiones,
                                              EspecialidadRequerida especialidadRequerida,
                                              String objetivo) {

        if (gimnasio.consultarPlan(codigo) != null) {
            return false;
        }

        gimnasio.crearPlanPersonalizado(
                codigo,
                nombre,
                descripcion,
                duracionMeses,
                valorMensual,
                estado,
                cantidadSesiones,
                especialidadRequerida,
                objetivo
        );

        return true;
    }

    public boolean registrarPlanPremium(String codigo, String nombre, String descripcion,
                                        int duracionMeses, double valorMensual,
                                        Estado estado, boolean areasDeportivas,
                                        boolean clasesGrupales, double valorAdicional) {

        if (gimnasio.consultarPlan(codigo) != null) {
            return false;
        }

        gimnasio.crearPlanPremium(
                codigo,
                nombre,
                descripcion,
                duracionMeses,
                valorMensual,
                estado,
                areasDeportivas,
                clasesGrupales,
                valorAdicional
        );

        return true;
    }

    public PlanBasico buscarPlan(String codigo) {
        return gimnasio.consultarPlan(codigo);
    }

    public boolean actualizarPlan(String codigo, String nombre, String descripcion,
                                  double valorMensual, Estado estado) {

        return gimnasio.actualizarPlan(
                codigo,
                nombre,
                descripcion,
                valorMensual,
                estado
        );
    }

    public boolean eliminarPlan(String codigo) {
        return gimnasio.eliminarPlan(codigo);
    }

    public boolean asignarPlanACliente(String idCliente, String codigoPlan) {
        return gimnasio.asignarPlanACliente(idCliente, codigoPlan);
    }
}