package org.example.controller;

import org.example.model.Especialidad;
import org.example.model.Entrenador;
import org.example.model.Gimnasio;
import org.example.model.PlanBasico;
import org.example.model.ServicioAdicional;

import java.util.ArrayList;

public class EntrenadorController {

    private Gimnasio gimnasio;

    public EntrenadorController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public boolean registrarEntrenador(String nombre, String id, String telefono, Especialidad especialidad, double valorSesion) {

        if (gimnasio.consultarEntrenador(id) != null) {
            return false;
        }

        gimnasio.crearEntrenador(nombre, id, telefono, especialidad, valorSesion);

        return true;
    }

    public Entrenador buscarEntrenador(String id) {
        return gimnasio.consultarEntrenador(id);
    }

    public boolean actualizarEntrenador(String id, String nombre, String telefono, Especialidad especialidad, double valorSesion) {

        return gimnasio.actualizarEntrenador(id, nombre, telefono, especialidad, valorSesion
        );
    }

    public boolean eliminarEntrenador(String id) {
        return gimnasio.eliminarEntrenador(id);
    }

    public ArrayList<PlanBasico> getPlanes() {
        return gimnasio.getListPlanes();
    }

    public ArrayList<ServicioAdicional> getServicios() {
        return gimnasio.getListServiciosAdicionales();
    }

    public void asignarPlan(Entrenador entrenador, PlanBasico plan) {
        if (plan != null) {
            entrenador.asignarPlan(plan);
        }
    }

    public boolean asignarServicio(Entrenador entrenador, ServicioAdicional servicio) {

        if (entrenador == null || servicio == null) {
            return false;
        }

        return gimnasio.asignarServicioAEntrenador(entrenador.getId(), servicio.getCodigo()
        );
    }
}
