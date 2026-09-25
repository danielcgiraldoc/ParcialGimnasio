package org.example.model.factories;

import org.example.model.Cliente;

public class ClienteFactory extends PeopleFactory<Cliente, Cliente.Builder> {

    /**
     * Metodo para crear el builder que crea al cliente
     * @return
     */
    public Cliente.Builder crearPersonaBuilder() {
        return new Cliente.Builder();
    }
}
