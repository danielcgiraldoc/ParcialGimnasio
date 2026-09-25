package org.example.model;

import org.example.model.factories.*;

import java.time.LocalDate;
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

    /**
     * Consultar los ingresos por fecha (un dia)
     * @param fecha
     * @return
     */
    public double consultarIngresosFecha(LocalDate fecha) {
        double totalIngresos = 0;
        for (Cliente cliente : listClientes) {
            PlanBasico plan = cliente.getThePlan();
            if (plan != null
                    && plan.getEstado() == Estado.ACTIVO
                    && cliente.getFechaResgistro() != null
                    && cliente.getFechaResgistro().equals(fecha)) {
                totalIngresos += plan.calcularValorTotal();
            }
        }
        return totalIngresos;
    }

    /**
     * Crear cliente
     * @param nombre
     * @param id
     * @param telefono
     * @param correo
     * @param edad
     * @param fechaRegistro
     * @return
     */
    public Cliente crearCliente(String nombre, String id, String telefono, String correo, int edad, LocalDate fechaRegistro) {
        Cliente cliente = fabricaClientes.crearPersonaBuilder()
                .nombre(nombre)
                .id(id)
                .telefono(telefono)
                .correo(correo)
                .edad(edad)
                .fechaRegistro(fechaRegistro)
                .build();
        listClientes.add(cliente);
        return cliente;
    }

    /**
     * Consultar cliente por telefono
     * @param id
     * @return
     */
    public Cliente consultarCliente(String id) {
        for (Cliente cliente : listClientes) {
            if (cliente.getId().equals(id)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Consultar cliente por teléfono
     * @param telefono
     * @return
     */
    public Cliente consultarClientePorTelefono(String telefono) {
        for (Cliente cliente : listClientes) {
            if (cliente.getTelefono().equals(telefono)) {
                return cliente;
            }
        }
        return null;
    }

    /**
     * Verificar si un numero de telefono del cliente es un numero perfecto
     * @param telefono
     * @return
     */
    public boolean verificarNumeroPerfecto(String telefono) {
        long numero = Long.parseLong(telefono);
        if (numero <= 0) {
            return false;
        }
        long suma = 0;
        for (long i = 1; i < numero; i++) {
            if (numero % i == 0) {
                suma += i;
            }
        }
        return suma == numero;
    }

    /**
     * Actualizar cliente
     * @param id
     * @param nombre
     * @param telefono
     * @param correo
     * @param edad
     * @return
     */

    public boolean actualizarCliente(String id, String nombre, String telefono, String correo, int edad) {
        Cliente cliente = consultarCliente(id);
        if (cliente == null) {
            return false;
        }
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setEdad(edad);
        return true;
    }

    /**
     * Eliminar cliente
     * @param id
     * @return
     */
    public boolean eliminarCliente(String id) {
        Cliente cliente = consultarCliente(id);
        if (cliente == null) {
            return false;
        }
        listClientes.remove(cliente);
        return true;
    }

    /**
     * Crear entrenador
     * @param nombre
     * @param id
     * @param telefono
     * @param especialidad
     * @param valorSesion
     * @return
     */
    public Entrenador crearEntrenador(String nombre, String id, String telefono, Especialidad especialidad, double valorSesion) {
        Entrenador entrenador = fabricaEntrenadores.crearPersonaBuilder()
                .nombre(nombre)
                .id(id)
                .telefono(telefono)
                .especialidad(especialidad)
                .valorSesion(valorSesion)
                .build();
        listEntrenadores.add(entrenador);
        return entrenador;
    }

    /**
     * Consultar entrenador por id
     * @param id
     * @return
     */
    public Entrenador consultarEntrenador(String id) {
        for (Entrenador entrenador : listEntrenadores) {
            if (entrenador.getId().equals(id)) {
                return entrenador;
            }
        }
        return null;
    }

    /**
     * Actualizar entrenador
     * @param id
     * @param nombre
     * @param telefono
     * @param especialidad
     * @param valorSesion
     * @return
     */
    public boolean actualizarEntrenador(String id, String nombre, String telefono, Especialidad especialidad, double valorSesion) {
        Entrenador entrenador = consultarEntrenador(id);
        if (entrenador == null) {
            return false;
        }
        entrenador.setNombre(nombre);
        entrenador.setTelefono(telefono);
        entrenador.setEspecialidad(especialidad);
        entrenador.setValorSesion(valorSesion);
        return true;
    }

    /**
     * Eliminar entrenador
     * @param id
     * @return
     */
    public boolean eliminarEntrenador(String id) {
        Entrenador entrenador = consultarEntrenador(id);
        if (entrenador == null) {
            return false;
        }
        listEntrenadores.remove(entrenador);
        return true;
    }

    /**
     * Crear plan basico
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @return
     */
    public PlanBasico crearPlanBasico(String codigo, String nombre, String descripcion, int duracionMeses, double valorMensual, Estado estado) {
        PlanBasico plan = fabricaPlanBasico.crearPlan(codigo, nombre, descripcion, duracionMeses, valorMensual, estado);
        listPlanes.add(plan);
        return plan;
    }

    /**
     * Crear plan personalizado
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @param cantidadSesiones
     * @param especialidadRequerida
     * @param objetivo
     * @return
     */
    public PlanPersonalizado crearPlanPersonalizado(String codigo, String nombre, String descripcion, int duracionMeses,
                                                    double valorMensual, Estado estado, int cantidadSesiones,
                                                    EspecialidadRequerida especialidadRequerida, String objetivo) {
        PlanPersonalizado plan = fabricaPlanPersonalizado.crearPlanBuilder(codigo, nombre, descripcion, duracionMeses, valorMensual, estado)
                .cantidadSesiones(cantidadSesiones)
                .especialidadRequerida(especialidadRequerida)
                .objetivo(objetivo)
                .build();
        listPlanes.add(plan);
        return plan;
    }

    /**
     * Crear plan premium
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param duracionMeses
     * @param valorMensual
     * @param estado
     * @param areasDeportivas
     * @param clasesGrupales
     * @return
     */
    public PlanPremium crearPlanPremium(String codigo, String nombre, String descripcion, int duracionMeses,
                                        double valorMensual, Estado estado, boolean areasDeportivas, boolean clasesGrupales, double valorAdicional) {
        PlanPremium plan = fabricaPlanPremium.crearPlanBuilder(codigo, nombre, descripcion, duracionMeses, valorMensual, estado)
                .areasDeportivas(areasDeportivas)
                .clasesGrupales(clasesGrupales)
                .valorAdicional(valorAdicional)
                .build();
        listPlanes.add(plan);
        return plan;
    }

    /**
     * Consultar plan
     * @param codigo
     * @return
     */
    public PlanBasico consultarPlan(String codigo) {
        for (PlanBasico plan : listPlanes) {
            if (plan.getCodigo().equals(codigo)) {
                return plan;
            }
        }
        return null;
    }

    /**
     * Actualizar plan
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorMensual
     * @param estado
     * @return
     */

    public boolean actualizarPlan(String codigo, String nombre, String descripcion, double valorMensual, Estado estado) {
        PlanBasico plan = consultarPlan(codigo);
        if (plan == null) {
            return false;
        }
        plan.setNombre(nombre);
        plan.setDescripcion(descripcion);
        plan.setValorMensual(valorMensual);
        plan.setEstado(estado);
        return true;
    }

    /**
     * Eliminar plan
     * @param codigo
     * @return
     */
    public boolean eliminarPlan(String codigo) {
        PlanBasico plan = consultarPlan(codigo);
        if (plan == null) {
            return false;
        }
        listPlanes.remove(plan);
        return true;
    }

    /**
     * Asignar un plan a un cliente
     * @param idCliente
     * @param codigoPlan
     * @return
     */
    public boolean asignarPlanACliente(String idCliente, String codigoPlan) {
        Cliente cliente = consultarCliente(idCliente);
        PlanBasico plan = consultarPlan(codigoPlan);
        if (cliente == null || plan == null) {
            return false;
        }
        cliente.setThePlan(plan);
        plan.setTheCliente(cliente);
        return true;
    }

    /**
     * Crear servicio adicional
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorServicio
     * @param disponibilidad
     * @return
     */
    public ServicioAdicional crearServicioAdicional(String codigo, String nombre, String descripcion, double valorServicio, boolean disponibilidad) {
        ServicioAdicional servicio = new ServicioAdicional(codigo, nombre, descripcion, valorServicio, disponibilidad);
        listServiciosAdicionales.add(servicio);
        return servicio;
    }

    /**
     * Consultar servicio adicional
     * @param codigo
     * @return
     */
    public ServicioAdicional consultarServicioAdicional(String codigo) {
        for (ServicioAdicional servicio : listServiciosAdicionales) {
            if (servicio.getCodigo().equals(codigo)) {
                return servicio;
            }
        }
        return null;
    }

    /**
     * Actualizar el servicio adicional
     * @param codigo
     * @param nombre
     * @param descripcion
     * @param valorServicio
     * @param disponibilidad
     * @return
     */
    public boolean actualizarServicioAdicional(String codigo, String nombre, String descripcion, double valorServicio, boolean disponibilidad) {
        ServicioAdicional servicio = consultarServicioAdicional(codigo);
        if (servicio == null) {
            return false;
        }
        servicio.setNombre(nombre);
        servicio.setDescripcion(descripcion);
        servicio.setValorServicio(valorServicio);
        servicio.setDisponibilidad(disponibilidad);
        return true;
    }

    /**
     * Eliminar el servicio adicional
     * @param codigo
     * @return
     */
    public boolean eliminarServicioAdicional(String codigo) {
        ServicioAdicional servicio = consultarServicioAdicional(codigo);
        if (servicio == null) {
            return false;
        }
        listServiciosAdicionales.remove(servicio);
        return true;
    }

    /**
     * Asignar un servicio a un entrenador
     * @param idEntrenador
     * @param codigoServicio
     * @return
     */

    public boolean asignarServicioAEntrenador(String idEntrenador, String codigoServicio) {
        Entrenador entrenador = consultarEntrenador(idEntrenador);
        ServicioAdicional servicio = consultarServicioAdicional(codigoServicio);
        if (entrenador == null || servicio == null) {
            return false;
        }
        entrenador.asignarServicioAdicional(servicio);
        servicio.setTheEntrenador(entrenador);
        return true;
    }

    public static void setInstance(Gimnasio instance) {
        Gimnasio.instance = instance;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPaginaWeb() {
        return paginaWeb;
    }

    public void setPaginaWeb(String paginaWeb) {
        this.paginaWeb = paginaWeb;
    }

    public ClienteFactory getFabricaClientes() {
        return fabricaClientes;
    }

    public void setFabricaClientes(ClienteFactory fabricaClientes) {
        this.fabricaClientes = fabricaClientes;
    }

    public EntrenadorFactory getFabricaEntrenadores() {
        return fabricaEntrenadores;
    }

    public void setFabricaEntrenadores(EntrenadorFactory fabricaEntrenadores) {
        this.fabricaEntrenadores = fabricaEntrenadores;
    }

    public PlanBasicoFactory getFabricaPlanBasico() {
        return fabricaPlanBasico;
    }

    public void setFabricaPlanBasico(PlanBasicoFactory fabricaPlanBasico) {
        this.fabricaPlanBasico = fabricaPlanBasico;
    }

    public PlanPersonalizadoFactory getFabricaPlanPersonalizado() {
        return fabricaPlanPersonalizado;
    }

    public void setFabricaPlanPersonalizado(PlanPersonalizadoFactory fabricaPlanPersonalizado) {
        this.fabricaPlanPersonalizado = fabricaPlanPersonalizado;
    }

    public PlanPremiumFactory getFabricaPlanPremium() {
        return fabricaPlanPremium;
    }

    public void setFabricaPlanPremium(PlanPremiumFactory fabricaPlanPremium) {
        this.fabricaPlanPremium = fabricaPlanPremium;
    }

    public ArrayList<Cliente> getListClientes() {
        return listClientes;
    }

    public void setListClientes(ArrayList<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    public ArrayList<Entrenador> getListEntrenadores() {
        return listEntrenadores;
    }

    public void setListEntrenadores(ArrayList<Entrenador> listEntrenadores) {
        this.listEntrenadores = listEntrenadores;
    }

    public ArrayList<PlanBasico> getListPlanes() {
        return listPlanes;
    }

    public void setListPlanes(ArrayList<PlanBasico> listPlanes) {
        this.listPlanes = listPlanes;
    }

    public ArrayList<ServicioAdicional> getListServiciosAdicionales() {
        return listServiciosAdicionales;
    }

    public void setListServiciosAdicionales(ArrayList<ServicioAdicional> listServiciosAdicionales) {
        this.listServiciosAdicionales = listServiciosAdicionales;
    }
}




