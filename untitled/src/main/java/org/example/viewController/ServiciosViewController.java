package org.example.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import org.example.controller.ServicioController;
import org.example.model.Gimnasio;
import org.example.model.PlanBasico;
import org.example.model.PlanPersonalizado;
import org.example.model.PlanPremium;
import org.example.model.ServicioAdicional;

import java.io.IOException;

public class ServiciosViewController {

    @FXML private TextField txtCodigo;
    @FXML private TextField txtNombre;
    @FXML private TextArea txtDescripcion;
    @FXML private TextField txtValorServicio;
    @FXML private ComboBox<String> cbDisponibilidad;
    @FXML private ComboBox<PlanBasico> cbPlan;

    @FXML private TableView<ServicioAdicional> tablaServicios;
    @FXML private TableColumn<ServicioAdicional, String> colCodigo;
    @FXML private TableColumn<ServicioAdicional, String> colNombre;
    @FXML private TableColumn<ServicioAdicional, String> colDescripcion;
    @FXML private TableColumn<ServicioAdicional, String> colValor;
    @FXML private TableColumn<ServicioAdicional, String> colDisponibilidad;

    private ServicioController servicioController;

    @FXML
    public void initialize() {

        servicioController = new ServicioController(Gimnasio.getInstance());

        cbDisponibilidad.setItems(
                FXCollections.observableArrayList("Sí", "No")
        );

        cargarPlanes();
        configurarTabla();
        actualizarTabla();
    }

    private void cargarPlanes() {

        cbPlan.getItems().clear();
        cbPlan.getItems().addAll(servicioController.getPlanes());

        cbPlan.setConverter(new StringConverter<PlanBasico>() {

            @Override
            public String toString(PlanBasico plan) {

                if (plan == null) {
                    return "";
                }

                if (plan instanceof PlanPersonalizado) {
                    return "Personalizado - " + plan.getCodigo();
                }

                if (plan instanceof PlanPremium) {
                    return "Premium - " + plan.getCodigo();
                }

                return "Básico - " + plan.getCodigo();
            }

            @Override
            public PlanBasico fromString(String string) {
                return null;
            }
        });
    }

    private void configurarTabla() {

        colCodigo.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getCodigo()
                )
        );

        colNombre.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getNombre()
                )
        );

        colDescripcion.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getDescripcion()
                )
        );

        colValor.setCellValueFactory(
                data -> new SimpleStringProperty(
                        String.valueOf(data.getValue().getValorServicio())
                )
        );

        colDisponibilidad.setCellValueFactory(
                data -> new SimpleStringProperty(
                        data.getValue().getDisponibilidad() ? "Sí" : "No"
                )
        );
    }

    @FXML
    private void guardarServicio() {

        try {

            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();

            if (codigo.isEmpty() || nombre.isEmpty()) {
                mostrarAlerta("Complete los campos obligatorios.");
                return;
            }

            if (cbDisponibilidad.getValue() == null) {
                mostrarAlerta("Seleccione la disponibilidad.");
                return;
            }

            if (cbPlan.getValue() == null) {
                mostrarAlerta("Seleccione un plan.");
                return;
            }

            double valorServicio = Double.parseDouble(
                    txtValorServicio.getText()
            );

            boolean disponibilidad =
                    cbDisponibilidad.getValue().equals("Sí");

            if (servicioController.getGimnasio()
                    .consultarServicioAdicional(codigo) != null) {

                mostrarAlerta("Ya existe un servicio con ese código.");
                return;
            }

            servicioController.registrarServicio(
                    codigo,
                    nombre,
                    descripcion,
                    valorServicio,
                    disponibilidad
            );

            ServicioAdicional servicio =
                    servicioController.getGimnasio()
                            .consultarServicioAdicional(codigo);

            PlanBasico plan = cbPlan.getValue();

            if (disponibilidad) {
                servicioController.asignarServicioAPlan(
                        plan,
                        servicio
                );

                mostrarAlerta("Servicio registrado y asociado al plan.");
            } else {
                mostrarAlerta(
                        "Servicio registrado, pero no se asoció al plan porque no está disponible."
                );
            }

            actualizarTabla();
            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarAlerta("El valor del servicio debe ser numérico.");

        } catch (Exception e) {

            mostrarAlerta("No fue posible registrar el servicio.");
        }
    }

    private void actualizarTabla() {

        tablaServicios.setItems(
                FXCollections.observableArrayList(
                        servicioController
                                .getGimnasio()
                                .getListServiciosAdicionales()
                )
        );
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtValorServicio.clear();

        cbDisponibilidad.setValue(null);
        cbPlan.setValue(null);
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org.example/inicio.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage)
                ((Node) event.getSource())
                        .getScene()
                        .getWindow();

        stage.setScene(scene);
        stage.show();
    }

    private void mostrarAlerta(String mensaje) {

        Alert alerta = new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("SmartGym");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}