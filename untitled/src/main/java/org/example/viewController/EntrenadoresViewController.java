package org.example.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.controller.EntrenadorController;
import org.example.model.Especialidad;
import org.example.model.Entrenador;
import org.example.model.Gimnasio;
import org.example.model.PlanBasico;
import org.example.model.ServicioAdicional;

import java.io.IOException;

public class EntrenadoresViewController {

    private EntrenadorController entrenadorController =
            new EntrenadorController(Gimnasio.getInstance());

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private ComboBox<Especialidad> cbEspecialidad;

    @FXML
    private TextField txtValorSesion;

    @FXML
    private ComboBox<PlanBasico> cbPlan;

    @FXML
    private ComboBox<ServicioAdicional> cbServicio;

    @FXML
    private TableView<Entrenador> tablaEntrenadores;

    @FXML
    private TableColumn<Entrenador, String> colNombre;

    @FXML
    private TableColumn<Entrenador, String> colIdentificacion;

    @FXML
    private TableColumn<Entrenador, String> colTelefono;

    @FXML
    private TableColumn<Entrenador, String> colEspecialidad;

    @FXML
    private TableColumn<Entrenador, String> colValor;

    @FXML
    private TableColumn<Entrenador, String> colPlan;

    @FXML
    private TableColumn<Entrenador, String> colServicio;

    private Entrenador entrenadorEditar;

    @FXML
    private void initialize() {

        cbEspecialidad.getItems().setAll(Especialidad.values());

        cbPlan.getItems().setAll(
                entrenadorController.getPlanes()
        );

        cbServicio.getItems().setAll(
                entrenadorController.getServicios()
        );

        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getNombre()));

        colIdentificacion.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getId()));

        colTelefono.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getTelefono()));

        colEspecialidad.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getEspecialidad() != null
                                ? data.getValue().getEspecialidad().toString()
                                : ""));

        colValor.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(data.getValue().getValorSesion())));

        colPlan.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getListPlanes().isEmpty()
                                ? ""
                                : data.getValue()
                                .getListPlanes()
                                .get(0)
                                .getNombre()));

        colServicio.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue()
                                .getListServiciosAdicionales()
                                .isEmpty()
                                ? ""
                                : data.getValue()
                                .getListServiciosAdicionales()
                                .get(0)
                                .getNombre()));

        cargarEntrenadores();
    }

    private void cargarEntrenadores() {

        tablaEntrenadores.getItems().setAll(
                entrenadorController.getGimnasio()
                        .getListEntrenadores()
                        .stream()
                        .filter(entrenador -> entrenador != null)
                        .toList()
        );
    }

    @FXML
    private void guardarEntrenador() {

        String nombre = txtNombre.getText();
        String id = txtIdentificacion.getText();
        String telefono = txtTelefono.getText();
        Especialidad especialidad = cbEspecialidad.getValue();
        String valorTexto = txtValorSesion.getText();

        if (nombre.isBlank() ||
                id.isBlank() ||
                telefono.isBlank() ||
                especialidad == null ||
                valorTexto.isBlank()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Datos incompletos",
                    "Por favor, completa todos los campos."
            );

            return;
        }

        double valorSesion;

        try {

            valorSesion = Double.parseDouble(valorTexto);

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Valor inválido",
                    "El valor de la sesión debe ser un número."
            );

            return;
        }

        if (entrenadorEditar != null) {

            boolean actualizado =
                    entrenadorController.actualizarEntrenador(
                            entrenadorEditar.getId(),
                            nombre,
                            telefono,
                            especialidad,
                            valorSesion
                    );

            if (actualizado) {

                mostrarAlerta(
                        Alert.AlertType.INFORMATION,
                        "Entrenador actualizado",
                        "El entrenador se actualizó correctamente."
                );

                asignarDatosAdicionales(entrenadorEditar);

                limpiarCampos();
                cargarEntrenadores();
            }

            return;
        }

        boolean registrado =
                entrenadorController.registrarEntrenador(
                        nombre,
                        id,
                        telefono,
                        especialidad,
                        valorSesion
                );

        if (registrado) {

            Entrenador nuevoEntrenador =
                    entrenadorController.buscarEntrenador(id);

            asignarDatosAdicionales(nuevoEntrenador);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Entrenador registrado",
                    "El entrenador se registró correctamente."
            );

            limpiarCampos();
            cargarEntrenadores();

        } else {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Error",
                    "Ya existe un entrenador con esa identificación."
            );
        }
    }

    private void asignarDatosAdicionales(Entrenador entrenador) {

        if (entrenador == null) {
            return;
        }

        PlanBasico plan = cbPlan.getValue();

        if (plan != null) {
            entrenadorController.asignarPlan(
                    entrenador,
                    plan
            );
        }

        ServicioAdicional servicio = cbServicio.getValue();

        if (servicio != null) {
            entrenadorController.asignarServicio(
                    entrenador,
                    servicio
            );
        }
    }

    @FXML
    private void editarEntrenador() {

        Entrenador seleccionado =
                tablaEntrenadores
                        .getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Entrenador no seleccionado",
                    "Selecciona un entrenador de la tabla."
            );

            return;
        }

        entrenadorEditar = seleccionado;

        txtNombre.setText(seleccionado.getNombre());
        txtIdentificacion.setText(seleccionado.getId());
        txtTelefono.setText(seleccionado.getTelefono());
        txtValorSesion.setText(
                String.valueOf(seleccionado.getValorSesion())
        );

        cbEspecialidad.setValue(
                seleccionado.getEspecialidad()
        );

        if (!seleccionado.getListPlanes().isEmpty()) {
            cbPlan.setValue(
                    seleccionado.getListPlanes().get(0)
            );
        } else {
            cbPlan.setValue(null);
        }

        if (!seleccionado.getListServiciosAdicionales().isEmpty()) {
            cbServicio.setValue(
                    seleccionado.getListServiciosAdicionales().get(0)
            );
        } else {
            cbServicio.setValue(null);
        }
    }

    @FXML
    private void eliminarEntrenador() {

        Entrenador seleccionado =
                tablaEntrenadores
                        .getSelectionModel()
                        .getSelectedItem();

        if (seleccionado == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Entrenador no seleccionado",
                    "Selecciona un entrenador de la tabla."
            );

            return;
        }

        boolean eliminado =
                entrenadorController.eliminarEntrenador(
                        seleccionado.getId()
                );

        if (eliminado) {

            tablaEntrenadores
                    .getItems()
                    .remove(seleccionado);

            mostrarAlerta(
                    Alert.AlertType.INFORMATION,
                    "Entrenador eliminado",
                    "El entrenador se eliminó correctamente."
            );

            limpiarCampos();
        }
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource(
                        "/org.example/inicio.fxml"
                )
        );

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage =
                (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();

        stage.setScene(scene);
        stage.show();
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtIdentificacion.clear();
        txtTelefono.clear();
        txtValorSesion.clear();

        cbEspecialidad.setValue(null);
        cbPlan.setValue(null);
        cbServicio.setValue(null);

        entrenadorEditar = null;
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}