package org.example.model.factories;

import org.example.model.Entrenador;

public class EntrenadorFactory extends PeopleFactory<Entrenador, Entrenador.Builder> {

    /**
     * Metodo para crear el builder que crea al entrenador
     * @return
     */
    @Override
    public Entrenador.Builder crearPersonaBuilder() {
        return new Entrenador.Builder();
    }
}
