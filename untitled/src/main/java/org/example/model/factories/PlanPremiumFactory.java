package org.example.model.factories;

import org.example.model.Estado;
import org.example.model.PlanPremium;

public class PlanPremiumFactory {

    /**
     * Metodo para crear el plan premium
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @return
     */
    public PlanPremium.Builder crearPlanBuilder(String codigo, String nombre, String descripcion,
                                                int duracionMeses, double valorMensual, Estado estado) {
        return new PlanPremium.Builder(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
    }
}