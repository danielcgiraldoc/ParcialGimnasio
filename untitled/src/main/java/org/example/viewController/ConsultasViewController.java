package org.example.viewController;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.model.Cliente;
import org.example.model.Gimnasio;

import java.io.IOException;

public class ConsultasViewController {

    @FXML
    private TextField txtTelefono;

    @FXML
    private Label lblResultado;

    private Gimnasio gimnasio;

    @FXML
    public void initialize() {
        gimnasio = Gimnasio.getInstance();
    }

    @FXML
    private void consultarTelefono() {

        String telefono = txtTelefono.getText().trim();

        if (telefono.isEmpty()) {
            mostrarAlerta("Ingrese un número de teléfono.");
            return;
        }

        Cliente cliente = gimnasio.consultarClientePorTelefono(telefono);

        if (cliente == null) {
            lblResultado.setText(
                    "No existe un cliente con ese número de teléfono."
            );
            return;
        }

        boolean esPerfecto = gimnasio.verificarNumeroPerfecto(telefono);

        if (esPerfecto) {
            lblResultado.setText(
                    "Cliente encontrado.\nEl número de teléfono ES perfecto."
            );
        } else {
            lblResultado.setText(
                    "Cliente encontrado.\nEl número de teléfono NO es perfecto."
            );
        }
    }

    @FXML
    private void volverInicio(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(
                getClass().getResource("/org.example/inicio.fxml")
        );

        Scene scene = new Scene(fxmlLoader.load());

        Stage stage = (Stage) ((Node) event.getSource())
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