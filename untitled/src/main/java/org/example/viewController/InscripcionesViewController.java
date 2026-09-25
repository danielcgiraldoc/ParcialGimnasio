package org.example.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import org.example.model.Cliente;
import org.example.model.Gimnasio;
import org.example.model.PlanBasico;
import org.example.model.ServicioAdicional;

import java.io.IOException;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class InscripcionesViewController {

    private final Gimnasio gimnasio = Gimnasio.getInstance();

    @FXML
    private TableView<Cliente> tablaInscripciones;

    @FXML
    private TableColumn<Cliente, String> colCliente;

    @FXML
    private TableColumn<Cliente, String> colPlan;

    @FXML
    private TableColumn<Cliente, String> colEstado;

    @FXML
    private TableColumn<Cliente, String> colServicios;

    @FXML
    private TableColumn<Cliente, String> colValorTotal;

    @FXML
    private DatePicker dpFechaIngresos;

    @FXML
    private Label lblIngresosTotales;

    @FXML
    private void initialize() {

        colCliente.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        colPlan.setCellValueFactory(data -> {
            PlanBasico plan = data.getValue().getThePlan();
            return new SimpleStringProperty(plan != null ? plan.getNombre() : "Sin plan");
        });

        colEstado.setCellValueFactory(data -> {
            PlanBasico plan = data.getValue().getThePlan();
            return new SimpleStringProperty(plan != null ? plan.getEstado().toString() : "-");
        });

        colServicios.setCellValueFactory(data -> {
            PlanBasico plan = data.getValue().getThePlan();
            if (plan == null || plan.getListServiciosAdicionales().isEmpty()) {
                return new SimpleStringProperty("Ninguno");
            }
            String servicios = plan.getListServiciosAdicionales().stream()
                    .map(ServicioAdicional::getNombre)
                    .collect(Collectors.joining(", "));
            return new SimpleStringProperty(servicios);
        });

        colValorTotal.setCellValueFactory(data -> {
            PlanBasico plan = data.getValue().getThePlan();
            return new SimpleStringProperty(plan != null ? String.valueOf(plan.calcularValorTotal()) : "-");
        });

        cargarInscripciones();
    }

    @FXML
    private void cargarInscripciones() {
        tablaInscripciones.getItems().setAll(
                gimnasio.getListClientes().stream()
                        .filter(cliente -> cliente != null)
                        .toList()
        );
    }

    @FXML
    private void calcularIngresos() {

        LocalDate fecha = dpFechaIngresos.getValue();

        if (fecha == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Fecha requerida", "Selecciona una fecha para calcular los ingresos.");
            return;
        }

        double ingresos = gimnasio.consultarIngresosFecha(fecha);

        lblIngresosTotales.setText("Ingresos totales: $" + ingresos);
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/inicio.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}
