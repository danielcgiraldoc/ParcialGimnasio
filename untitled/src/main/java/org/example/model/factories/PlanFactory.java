package org.example.model.factories;

import org.example.model.Estado;
import org.example.model.PlanBasico;

public abstract class PlanFactory<TipoPlan extends PlanBasico, TipoBuilder>{


    /**
     * Metodo / contrato para crear un plan
     * @return
     */
    public abstract TipoPlan crearPlan(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, Estado estado);

}
