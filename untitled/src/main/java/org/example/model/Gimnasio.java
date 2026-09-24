package org.example.model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public final class Gimnasio {

private static Gimnasio instance;
    private String nombre;
    private String nit;
    private String correo;
    private String paginaWeb;
    private PeopleFactory fabricaPersonas;
    private PlanFactory fabricaPlanes;
    private ArrayList<Cliente> listClientes;
    private ArrayList<Entrenador> listEntrenadores;
    private ArrayList<ServicioAdicional> listServiciosAdicionales;

    /**
     * Metodo contructor que llena los datos del gimnasio unico
     */
    public Gimnasio() {
        this.nombre = "Smart Gym";
        this.nit = "109245";
        this.correo = "smartgym@gmail.com";
        this.paginaWeb = "https://smartgym.com";
        this.fabricaPersonas = new PeopleFactory();
        this.fabricaPlanes = new PlanFactory();
        this.listClientes = new ArrayList<>();
        this.listEntrenadores = new ArrayList<>();
        this.listServiciosAdicionales = new ArrayList<>();
    }

    /**
     * Metodo de instancia unica para gimnasio
     * @return
     */
    public static Gimnasio getInstance(){
        if (instance == null){
            instance = new Gimnasio();
        }
        return instance;
    }




}
