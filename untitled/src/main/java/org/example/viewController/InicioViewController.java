package org.example.viewController;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.io.IOException;

public class InicioViewController {

    @FXML
    private void abrirClientes(ActionEvent event) throws IOException {
        abrirVista(event, "clientes.fxml");
    }

    @FXML
    private void abrirEntrenadores(ActionEvent event) throws IOException {
        abrirVista(event, "entrenadores.fxml");
    }

    @FXML
    private void abrirPlanes(ActionEvent event) throws IOException {
        abrirVista(event, "planes.fxml");
    }

    @FXML
    private void abrirServicios(ActionEvent event) throws IOException {
        abrirVista(event, "servicios.fxml");
    }

    @FXML
    private void abrirInscripciones(ActionEvent event) throws IOException {
        abrirVista(event, "inscripciones.fxml");
    }

    @FXML
    private void abrirConsultas(ActionEvent event) throws IOException {
        abrirVista(event, "consultas.fxml");
    }

    private void abrirVista(ActionEvent event, String archivo) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org.example/" + archivo));

        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}