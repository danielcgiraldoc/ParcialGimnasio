package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.example.model.*;

import java.time.LocalDate;

public class App extends Application {

    /**
     * Inicia el programa (la interfaz visual)
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        cargarDatosDemo();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/inicio.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Smart Gym");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Carga y establece los datos "quemados" para la prueba, tambien obtiene la instancia del gimnasio ya creada
     * por medio del patron establecido (singleton)
     */
    private void cargarDatosDemo() {

        Gimnasio gimnasio = Gimnasio.getInstance();

        Cliente clienteAna = gimnasio.crearCliente("Ana Torres", "1001", "3001112233",
                "ana@mail.com", 24, LocalDate.of(2026, 3, 12));

        Cliente clienteJuan = gimnasio.crearCliente("Juan Pérez", "1002", "3002223344",
                "juan@mail.com", 31, LocalDate.of(2026, 5, 20));

        Cliente clienteLaura = gimnasio.crearCliente("Laura Gómez", "1003", "3003334455",
                "laura@mail.com", 27, LocalDate.of(2026, 9, 1));

        Entrenador entrenadorCarlos = gimnasio.crearEntrenador("Carlos Ruiz", "E001", "3005556677",
                Especialidad.FUERZA, 25000);

        Entrenador entrenadorMarta = gimnasio.crearEntrenador("Marta Díaz", "E002", "3006667788",
                Especialidad.SALUD_GENERAL, 20000);

        PlanBasico planBasico = gimnasio.crearPlanBasico("PB001", "Plan Básico",
                "Acceso a sala de pesas y cardio", 1, 50000, Estado.ACTIVO);

        PlanPersonalizado planPersonalizado = gimnasio.crearPlanPersonalizado("PP001", "Plan Personalizado",
                "Entrenamiento guiado por un entrenador", 1, 60000, Estado.ACTIVO,
                4, EspecialidadRequerida.FUERZA, "Ganar masa muscular");
        planPersonalizado.setTheEntrenador(entrenadorCarlos);

        PlanPremium planPremium = gimnasio.crearPlanPremium("PR001", "Plan Premium",
                "Acceso completo a todas las instalaciones", 1, 70000, Estado.ACTIVO,
                true, true, 30000);

        ServicioAdicional masajes = gimnasio.crearServicioAdicional("S001", "Masajes",
                "Sesión de masajes relajantes", 15000, true);

        ServicioAdicional nutricion = gimnasio.crearServicioAdicional("S002", "Asesoría Nutricional",
                "Plan de alimentación personalizado", 20000, true);
/**
 * Asignacion de los planes a los clientes por medio del codigo y id
 */
        gimnasio.asignarPlanACliente("1001", "PB001");
        gimnasio.asignarPlanACliente("1002", "PP001");
        gimnasio.asignarPlanACliente("1003", "PR001");
/**
 * Agrega a los planes los servicios adicionales
 */
        planBasico.solicitarServicioAdicional(masajes);
        planPremium.solicitarServicioAdicional(masajes);
        planPremium.solicitarServicioAdicional(nutricion);
/**
 * Asigna los servicios adicionales a un entrenador o "encargado"
 */
        gimnasio.asignarServicioAEntrenador("E001", "S001");
        gimnasio.asignarServicioAEntrenador("E002", "S002");

      }

    public static void main(String[] args) {
        launch(args);
    }
}