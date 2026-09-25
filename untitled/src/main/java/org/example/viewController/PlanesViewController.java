package org.example.viewController;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

import org.example.controller.PlanController;
import org.example.model.Cliente;
import org.example.model.EspecialidadRequerida;
import org.example.model.Estado;
import org.example.model.Gimnasio;
import org.example.model.PlanBasico;
import org.example.model.PlanPersonalizado;
import org.example.model.PlanPremium;

import java.io.IOException;

public class PlanesViewController {

    @FXML
    private ComboBox<String> cbTipoPlan;

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextArea txtDescripcion;

    @FXML
    private TextField txtDuracion;

    @FXML
    private TextField txtValorMensual;

    @FXML
    private ComboBox<Estado> cbEstado;

    @FXML
    private ComboBox<String> cbCliente;

    @FXML
    private VBox panelPersonalizado;

    @FXML
    private TextField txtCantidadSesiones;

    @FXML
    private ComboBox<EspecialidadRequerida> cbEspecialidadRequerida;

    @FXML
    private TextField txtObjetivo;

    @FXML
    private VBox panelPremium;

    @FXML
    private ComboBox<String> cbAreasDeportivas;

    @FXML
    private ComboBox<String> cbClasesGrupales;

    @FXML
    private TextField txtValorAdicional;

    @FXML
    private TableView<PlanBasico> tablaPlanes;

    @FXML
    private TableColumn<PlanBasico, String> colCodigo;

    @FXML
    private TableColumn<PlanBasico, String> colNombre;

    @FXML
    private TableColumn<PlanBasico, String> colTipo;

    @FXML
    private TableColumn<PlanBasico, String> colDuracion;

    @FXML
    private TableColumn<PlanBasico, String> colValor;

    @FXML
    private TableColumn<PlanBasico, String> colEstado;

    private PlanController planController;

    @FXML
    public void initialize() {

        planController = new PlanController(Gimnasio.getInstance());

        cbTipoPlan.setItems(FXCollections.observableArrayList(
                "Básico",
                "Personalizado",
                "Premium"
        ));

        cbEstado.setItems(FXCollections.observableArrayList(
                Estado.values()
        ));

        cbEspecialidadRequerida.setItems(
                FXCollections.observableArrayList(
                        EspecialidadRequerida.values()
                )
        );

        cbAreasDeportivas.setItems(
                FXCollections.observableArrayList(
                        "Sí",
                        "No"
                )
        );

        cbClasesGrupales.setItems(
                FXCollections.observableArrayList(
                        "Sí",
                        "No"
                )
        );

        cargarClientes();
        configurarTabla();
        actualizarPaneles();
        actualizarTabla();
    }

    private void cargarClientes() {

        cbCliente.getItems().clear();

        for (Cliente cliente : planController.getGimnasio().getListClientes()) {
            cbCliente.getItems().add(cliente.getId());
        }
    }

    private void configurarTabla() {

        colCodigo.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getCodigo()
                )
        );

        colNombre.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getNombre()
                )
        );

        colTipo.setCellValueFactory(data -> {

            PlanBasico plan = data.getValue();

            if (plan instanceof PlanPersonalizado) {
                return new SimpleStringProperty("Personalizado");
            }

            if (plan instanceof PlanPremium) {
                return new SimpleStringProperty("Premium");
            }

            return new SimpleStringProperty("Básico");
        });

        colDuracion.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(
                                data.getValue().getDuracionMeses()
                        )
                )
        );

        colValor.setCellValueFactory(data ->
                new SimpleStringProperty(
                        String.valueOf(
                                data.getValue().getValorMensual()
                        )
                )
        );

        colEstado.setCellValueFactory(data ->
                new SimpleStringProperty(
                        data.getValue().getEstado().toString()
                )
        );
    }

    @FXML
    private void cambiarTipoPlan() {
        actualizarPaneles();
    }

    private void actualizarPaneles() {

        String tipo = cbTipoPlan.getValue();

        boolean personalizado = "Personalizado".equals(tipo);
        boolean premium = "Premium".equals(tipo);

        panelPersonalizado.setVisible(personalizado);
        panelPersonalizado.setManaged(personalizado);

        panelPremium.setVisible(premium);
        panelPremium.setManaged(premium);
    }

    @FXML
    private void guardarPlan() {

        try {

            String tipo = cbTipoPlan.getValue();
            String codigo = txtCodigo.getText();
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();

            if (tipo == null) {
                mostrarAlerta("Seleccione el tipo de plan.");
                return;
            }

            if (codigo.isEmpty() || nombre.isEmpty()) {
                mostrarAlerta("Complete los campos obligatorios.");
                return;
            }

            if (cbCliente.getValue() == null) {
                mostrarAlerta("Seleccione un cliente.");
                return;
            }

            int duracion = Integer.parseInt(
                    txtDuracion.getText()
            );

            double valorMensual = Double.parseDouble(
                    txtValorMensual.getText()
            );

            Estado estado = cbEstado.getValue();

            if (estado == null) {
                mostrarAlerta("Seleccione el estado.");
                return;
            }

            boolean registrado;

            if (tipo.equals("Básico")) {

                registrado = planController.registrarPlanBasico(
                        codigo,
                        nombre,
                        descripcion,
                        duracion,
                        valorMensual,
                        estado
                );

            } else if (tipo.equals("Personalizado")) {

                int cantidadSesiones = Integer.parseInt(
                        txtCantidadSesiones.getText()
                );

                EspecialidadRequerida especialidad =
                        cbEspecialidadRequerida.getValue();

                String objetivo = txtObjetivo.getText();

                if (especialidad == null || objetivo.isEmpty()) {
                    mostrarAlerta(
                            "Complete la información del plan personalizado."
                    );
                    return;
                }

                registrado = planController.registrarPlanPersonalizado(
                        codigo,
                        nombre,
                        descripcion,
                        duracion,
                        valorMensual,
                        estado,
                        cantidadSesiones,
                        especialidad,
                        objetivo
                );

            } else {

                boolean areasDeportivas =
                        "Sí".equals(cbAreasDeportivas.getValue());

                boolean clasesGrupales =
                        "Sí".equals(cbClasesGrupales.getValue());

                double valorAdicional = Double.parseDouble(
                        txtValorAdicional.getText()
                );

                registrado = planController.registrarPlanPremium(
                        codigo,
                        nombre,
                        descripcion,
                        duracion,
                        valorMensual,
                        estado,
                        areasDeportivas,
                        clasesGrupales,
                        valorAdicional
                );
            }

            if (!registrado) {
                mostrarAlerta(
                        "Ya existe un plan con ese código."
                );
                return;
            }

            boolean asignado = planController.asignarPlanACliente(
                    cbCliente.getValue(),
                    codigo
            );

            if (!asignado) {
                mostrarAlerta(
                        "El plan fue creado, pero no se pudo asignar al cliente."
                );
                actualizarTabla();
                return;
            }

            actualizarTabla();
            limpiarCampos();

            mostrarAlerta(
                    "Plan registrado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Revise los campos numéricos."
            );

        } catch (Exception e) {

            mostrarAlerta(
                    "No fue posible registrar el plan."
            );
        }
    }

    @FXML
    private void editarPlan() {

        try {

            String codigo = txtCodigo.getText();

            if (codigo.isEmpty()) {
                mostrarAlerta(
                        "Seleccione un plan."
                );
                return;
            }

            double valorMensual = Double.parseDouble(
                    txtValorMensual.getText()
            );

            Estado estado = cbEstado.getValue();

            if (estado == null) {
                mostrarAlerta(
                        "Seleccione el estado."
                );
                return;
            }

            boolean actualizado = planController.actualizarPlan(
                    codigo,
                    txtNombre.getText(),
                    txtDescripcion.getText(),
                    valorMensual,
                    estado
            );

            if (!actualizado) {
                mostrarAlerta(
                        "No se encontró el plan."
                );
                return;
            }

            if (cbCliente.getValue() != null) {

                planController.asignarPlanACliente(
                        cbCliente.getValue(),
                        codigo
                );
            }

            actualizarTabla();
            limpiarCampos();

            mostrarAlerta(
                    "Plan actualizado correctamente."
            );

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "El valor mensual debe ser numérico."
            );
        }
    }

    @FXML
    private void eliminarPlan() {

        String codigo = txtCodigo.getText();

        if (codigo.isEmpty()) {
            mostrarAlerta(
                    "Seleccione un plan."
            );
            return;
        }

        boolean eliminado = planController.eliminarPlan(codigo);

        if (eliminado) {

            actualizarTabla();
            limpiarCampos();

            mostrarAlerta(
                    "Plan eliminado correctamente."
            );

        } else {

            mostrarAlerta(
                    "No se encontró el plan."
            );
        }
    }

    @FXML
    private void seleccionarPlan() {

        PlanBasico plan =
                tablaPlanes.getSelectionModel().getSelectedItem();

        if (plan == null) {
            return;
        }

        txtCodigo.setText(
                plan.getCodigo()
        );

        txtNombre.setText(
                plan.getNombre()
        );

        txtDescripcion.setText(
                plan.getDescripcion()
        );

        txtDuracion.setText(
                String.valueOf(
                        plan.getDuracionMeses()
                )
        );

        txtValorMensual.setText(
                String.valueOf(
                        plan.getValorMensual()
                )
        );

        cbEstado.setValue(
                plan.getEstado()
        );

        if (plan.getTheCliente() != null) {

            cbCliente.setValue(
                    plan.getTheCliente().getId()
            );
        } else {

            cbCliente.setValue(null);
        }

        if (plan instanceof PlanPersonalizado personalizado) {

            cbTipoPlan.setValue(
                    "Personalizado"
            );

            txtCantidadSesiones.setText(
                    String.valueOf(
                            personalizado.getCantidadSesiones()
                    )
            );

            cbEspecialidadRequerida.setValue(
                    personalizado.getEspecialidadRequerida()
            );

            txtObjetivo.setText(
                    personalizado.getObjetivo()
            );

        } else if (plan instanceof PlanPremium premium) {

            cbTipoPlan.setValue(
                    "Premium"
            );

            cbAreasDeportivas.setValue(
                    premium.isAreasDeportivas()
                            ? "Sí"
                            : "No"
            );

            cbClasesGrupales.setValue(
                    premium.isClasesGrupales()
                            ? "Sí"
                            : "No"
            );

            txtValorAdicional.setText(
                    String.valueOf(
                            premium.getValorAdicional()
                    )
            );

        } else {

            cbTipoPlan.setValue(
                    "Básico"
            );
        }

        actualizarPaneles();
    }

    @FXML
    private void volverInicio(ActionEvent event)
            throws IOException {

        FXMLLoader fxmlLoader =
                new FXMLLoader(
                        getClass().getResource(
                                "/org.example/inicio.fxml"
                        )
                );

        Scene scene =
                new Scene(
                        fxmlLoader.load()
                );

        Stage stage =
                (Stage) ((Node) event.getSource())
                        .getScene()
                        .getWindow();

        stage.setScene(scene);
        stage.show();
    }

    private void actualizarTabla() {

        tablaPlanes.setItems(
                FXCollections.observableArrayList(
                        planController
                                .getGimnasio()
                                .getListPlanes()
                )
        );
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtDescripcion.clear();
        txtDuracion.clear();
        txtValorMensual.clear();

        cbTipoPlan.setValue(null);
        cbEstado.setValue(null);
        cbCliente.setValue(null);

        txtCantidadSesiones.clear();
        cbEspecialidadRequerida.setValue(null);
        txtObjetivo.clear();

        cbAreasDeportivas.setValue(null);
        cbClasesGrupales.setValue(null);
        txtValorAdicional.clear();

        actualizarPaneles();
    }

    private void mostrarAlerta(String mensaje) {

        Alert alerta =
                new Alert(Alert.AlertType.INFORMATION);

        alerta.setTitle("SmartGym");
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}