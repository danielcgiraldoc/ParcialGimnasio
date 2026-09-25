package org.example.model;

import org.example.model.factories.*;

import java.util.ArrayList;

public final class Gimnasio {

private static Gimnasio instance;
    private String nombre;
    private String nit;
    private String correo;
    private String paginaWeb;
    private ClienteFactory fabricaClientes;
    private EntrenadorFactory fabricaEntrenadores;
    private PlanBasicoFactory fabricaPlanBasico;
    private PlanPersonalizadoFactory fabricaPlanPersonalizado;
    private PlanPremiumFactory fabricaPlanPremium;
    private ArrayList<Cliente> listClientes;
    private ArrayList<Entrenador> listEntrenadores;
    private ArrayList<PlanBasico> listPlanes;
    private ArrayList<ServicioAdicional> listServiciosAdicionales;

    /**
     * Metodo contructor que llena los datos del gimnasio unico
     */
    public Gimnasio() {
        this.nombre = "Smart Gym";
        this.nit = "109245";
        this.correo = "smartgym@gmail.com";
        this.paginaWeb = "https://smartgym.com";
        this.fabricaClientes = new ClienteFactory();
        this.fabricaEntrenadores = new EntrenadorFactory();
        this.fabricaPlanBasico = new PlanBasicoFactory();
        this.fabricaPlanPersonalizado = new PlanPersonalizadoFactory();
        this.fabricaPlanPremium = new PlanPremiumFactory();
        this.listClientes = new ArrayList<>();
        this.listEntrenadores = new ArrayList<>();
        this.listPlanes = new ArrayList<>();
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
