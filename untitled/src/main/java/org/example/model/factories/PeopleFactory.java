package org.example.model.factories;

import org.example.model.Persona;

public abstract class PeopleFactory<TipoPersona extends Persona, TipoBuilder>{

    /**
     * Metodo para crear una persona
     * @return
     */
    public abstract TipoBuilder crearPersonaBuilder();


}
