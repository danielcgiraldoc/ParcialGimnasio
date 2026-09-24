package org.example;

import org.example.model.*;
import org.example.model.factories.ClienteFactory;
import org.example.model.factories.EntrenadorFactory;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    static void main() {

        EntrenadorFactory entrenadorFactory = new EntrenadorFactory();
        Entrenador entrenador = entrenadorFactory.crearPersonaBuilder().nombre("Daniel").id("1092").build();
        ClienteFactory clienteFactory = new ClienteFactory();
        Cliente cliente = clienteFactory.crearPersonaBuilder().edad(19).build();
        System.out.println(cliente);
        System.out.println(entrenador);
    }
}
