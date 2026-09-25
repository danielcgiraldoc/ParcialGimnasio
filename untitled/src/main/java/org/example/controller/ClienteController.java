package org.example.controller;

import org.example.model.Cliente;
import org.example.model.Gimnasio;

import java.time.LocalDate;

public class ClienteController {

    private Gimnasio gimnasio;

    public ClienteController(Gimnasio gimnasio) {
        this.gimnasio = gimnasio;
    }

    public Gimnasio getGimnasio() {
        return gimnasio;
    }

    public boolean registrarCliente(String nombre, String id, String telefono, String correo, int edad, LocalDate fechaRegistro) {
        if (gimnasio.consultarCliente(id) != null) {
            return false;
        }
        gimnasio.crearCliente(nombre, id, telefono, correo, edad, fechaRegistro);
        return true;
    }

    public Cliente buscarCliente(String id) {
        return gimnasio.consultarCliente(id);
    }

    public boolean actualizarCliente(String id, String nombre, String telefono, String correo, int edad) {
        return gimnasio.actualizarCliente(id, nombre, telefono, correo, edad);
    }

    public boolean eliminarCliente(String id) {
        return gimnasio.eliminarCliente(id);
    }
}