package org.example.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class PlanBasicoTest {

    private static final Logger LOG = Logger.getLogger(PlanBasicoTest.class.getName());

    private PlanBasico plan;
    private ServicioAdicional servicioDisponible;
    private ServicioAdicional servicioNoDisponible;

    /**
     * Metodo para el LOG de inicio de prueba
     * @param testInfo
     */
    @BeforeEach
    void setUp(TestInfo testInfo) {
        LOG.info("INICIO -> " + testInfo.getDisplayName());
        plan = new PlanBasico("P001", "Plan Básico", "Descripción", 2, 50000, Estado.ACTIVO);
        servicioDisponible = new ServicioAdicional("S001", "Masajes", "Sesión de masajes", 15000, true);
        servicioNoDisponible = new ServicioAdicional("S002", "Nutrición", "Asesoría nutricional", 20000, false);
    }

    /**
     * Metodo para el LOG final de prueba
     * @param testInfo
     */
    @AfterEach
    void tearDown(TestInfo testInfo) {
        LOG.info("FIN -> " + testInfo.getDisplayName());
    }

    /**
     * Test para solicitar un servicio disponible
     */
    @Test
    void testSolicitarServicioDisponible() {
        plan.solicitarServicioAdicional(servicioDisponible);

        assertEquals(1, plan.getListServiciosAdicionales().size());
        assertTrue(plan.getListServiciosAdicionales().contains(servicioDisponible));
    }
    /**
     * Test para solicitar un servicio no disponible
     */
    @Test
    void testSolicitarServicioNoDisponible() {
        plan.solicitarServicioAdicional(servicioNoDisponible);

        assertEquals(0, plan.getListServiciosAdicionales().size());
    }

    /**
     * Calcular el valor total con un servicio incluido
     */
    @Test
    void testCalcularValorTotalConServicios() {
        plan.solicitarServicioAdicional(servicioDisponible);

        assertEquals(115000, plan.calcularValorTotal());
    }

    /**
     * Test que verifique el metodo de calcular el valor total de un plan personalizado
     */
    @Test
    void testCalcularValorTotalPlanPersonalizado() {
        Entrenador entrenador = new Entrenador.Builder()
                .nombre("Ana Torres")
                .id("E001")
                .telefono("3005556677")
                .especialidad(Especialidad.FUERZA)
                .valorSesion(20000)
                .build();

        PlanPersonalizado personalizado = new PlanPersonalizado.Builder("PP001", "Plan Personalizado",
                "Descripción", 1, 60000, Estado.ACTIVO)
                .cantidadSesiones(4)
                .especialidadRequerida(EspecialidadRequerida.FUERZA)
                .objetivo("Ganar masa muscular")
                .build();

        personalizado.setTheEntrenador(entrenador);

        assertEquals(140000, personalizado.calcularValorTotal());
    }

    /**
     * Test para verificar el funcionamiento del calculo total del valor del plan premium
     */
    @Test
    void testCalcularValorTotalPlanPremium() {
        PlanPremium premium = new PlanPremium.Builder("PR001", "Plan Premium",
                "Descripción", 1, 70000, Estado.ACTIVO)
                .areasDeportivas(true)
                .clasesGrupales(true)
                .valorAdicional(30000)
                .build();

        assertEquals(100000, premium.calcularValorTotal());
    }
}