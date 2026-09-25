package org.example.controller;

import org.example.model.Gimnasio;
import org.example.model.PlanBasico;
import org.example.model.ServicioAdicional;

import java.util.ArrayList;

public class ServicioController {

    private Gimnasio gimnasio;

    public ServicioController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public void registrarServicio(String codigo, String nombre, String descripcion,
                                  double valorServicio, boolean disponibilidad) {

        gimnasio.crearServicioAdicional(
                codigo,
                nombre,
                descripcion,
                valorServicio,
                disponibilidad
        );
    }

    public ArrayList<PlanBasico> getPlanes() {
        return gimnasio.getListPlanes();
    }

    public void asignarServicioAPlan(PlanBasico plan, ServicioAdicional servicio) {
        if (plan != null && servicio != null) {
            plan.solicitarServicioAdicional(servicio);
        }
    }
}