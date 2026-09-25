package org.example.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import org.example.controller.ClienteController;
import org.example.model.Cliente;
import org.example.model.Gimnasio;

import java.io.IOException;
import java.time.LocalDate;

public class ClientesViewController {

    private ClienteController clienteController =
            new ClienteController(Gimnasio.getInstance());

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtIdentificacion;

    @FXML
    private TextField txtTelefono;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtEdad;

    @FXML
    private DatePicker dpFechaRegistro;

    @FXML
    private TableView<Cliente> tablaClientes;

    @FXML
    private TableColumn<Cliente, String> colNombre;

    @FXML
    private TableColumn<Cliente, String> colIdentificacion;

    @FXML
    private TableColumn<Cliente, String> colTelefono;

    @FXML
    private TableColumn<Cliente, String> colCorreo;

    @FXML
    private TableColumn<Cliente, String> colEdad;

    @FXML
    private TableColumn<Cliente, String> colFechaRegistro;

    private Cliente clienteEditar;

    @FXML
    private void initialize() {

        colNombre.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNombre()));

        colIdentificacion.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getId()));

        colTelefono.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getTelefono()));

        colCorreo.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getCorreo()));

        colEdad.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getEdad())));

        colFechaRegistro.setCellValueFactory(data -> new SimpleStringProperty(String.valueOf(data.getValue().getFechaResgistro())));

        cargarClientes();
    }

    private void cargarClientes() {
        tablaClientes.getItems().setAll(clienteController.getGimnasio().getListClientes().stream().filter(cliente -> cliente != null).toList());
    }

    @FXML
    private void guardarCliente() {

        String nombre = txtNombre.getText();
        String id = txtIdentificacion.getText();
        String telefono = txtTelefono.getText();
        String correo = txtCorreo.getText();
        String edadTexto = txtEdad.getText();
        LocalDate fechaRegistro = dpFechaRegistro.getValue();

        if (nombre.isBlank() ||
                id.isBlank() ||
                telefono.isBlank() ||
                correo.isBlank() ||
                edadTexto.isBlank() ||
                fechaRegistro == null) {

            mostrarAlerta(Alert.AlertType.WARNING, "Datos incompletos", "Por favor, completa todos los campos.");
            return;
        }

        int edad;

        try {
            edad = Integer.parseInt(edadTexto);
        } catch (NumberFormatException e) {
            mostrarAlerta(Alert.AlertType.WARNING, "Edad inválida", "La edad debe ser un número.");
            return;
        }

        if (clienteEditar != null) {

            boolean actualizado = clienteController.actualizarCliente(clienteEditar.getId(), nombre, telefono, correo, edad);

            if (actualizado) {
                mostrarAlerta(Alert.AlertType.INFORMATION, "Cliente actualizado", "El cliente se actualizó correctamente.");

                clienteEditar = null;
                limpiarCampos();
                cargarClientes();
            }
            return;
        }

        boolean registrado = clienteController.registrarCliente(nombre, id, telefono, correo, edad, fechaRegistro);

        if (registrado) {
            mostrarAlerta(Alert.AlertType.INFORMATION, "Cliente registrado", "El cliente se registró correctamente.");

            limpiarCampos();
            cargarClientes();

        } else {

            mostrarAlerta(Alert.AlertType.ERROR, "Error", "Ya existe un cliente con esa identificación.");
        }
    }

    @FXML
    private void editarCliente() {

        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Cliente no seleccionado", "Selecciona un cliente de la tabla.");
            return;
        }
        clienteEditar = seleccionado;
        txtNombre.setText(seleccionado.getNombre());
        txtIdentificacion.setText(seleccionado.getId());
        txtTelefono.setText(seleccionado.getTelefono());
        txtCorreo.setText(seleccionado.getCorreo());
        txtEdad.setText(String.valueOf(seleccionado.getEdad()));
        dpFechaRegistro.setValue(seleccionado.getFechaResgistro());
    }

    @FXML
    private void eliminarCliente() {

        Cliente seleccionado = tablaClientes.getSelectionModel().getSelectedItem();

        if (seleccionado == null) {
            mostrarAlerta(Alert.AlertType.WARNING, "Cliente no seleccionado", "Selecciona un cliente de la tabla.");
            return;
        }

        boolean eliminado = clienteController.eliminarCliente(seleccionado.getId());

        if (eliminado) {
            tablaClientes.getItems().remove(seleccionado);

            mostrarAlerta(Alert.AlertType.INFORMATION, "Cliente eliminado", "El cliente se eliminó correctamente.");

            limpiarCampos();
        }
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/org.example/inicio.fxml"));

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setScene(scene);
        stage.show();
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtIdentificacion.clear();
        txtTelefono.clear();
        txtCorreo.clear();
        txtEdad.clear();
        dpFechaRegistro.setValue(null);
        clienteEditar = null;
    }

    private void mostrarAlerta(Alert.AlertType tipo, String titulo, String mensaje) {

        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }
}