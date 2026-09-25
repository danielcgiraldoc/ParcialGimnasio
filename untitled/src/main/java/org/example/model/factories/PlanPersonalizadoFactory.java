package org.example.model.factories;

import org.example.model.Estado;
import org.example.model.PlanPersonalizado;

public class PlanPersonalizadoFactory {


    /**
     * Metodo para crear el plan personalizado
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @return
     */
    public PlanPersonalizado.Builder crearPlanBuilder(String codigo, String nombre, String descripcion,
                                                      int duracionMeses, double valorMensual, Estado estado) {
        return new PlanPersonalizado.Builder(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
    }
}