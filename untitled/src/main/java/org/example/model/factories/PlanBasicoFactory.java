package org.example.model.factories;

import org.example.model.Estado;
import org.example.model.PlanBasico;

public class PlanBasicoFactory {

    /**
     * Metodo para crear plan basico
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @return
     */
    public PlanBasico crearPlan(String codigo, String nombre, String descripcion,
                                int duracionMeses, double valorMensual, Estado estado) {
        return new PlanBasico(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
    }
}