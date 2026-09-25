package org.example.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

import java.time.LocalDate;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

class GimnasioTest {

    private static final Logger LOG = Logger.getLogger(GimnasioTest.class.getName());

    private static Gimnasio gimnasio;

    /**
     * Inicia el gimnasio
     */
    @BeforeAll
    static void setUpClass() {
        gimnasio = Gimnasio.getInstance();
    }

    /**
     * Establece el LOG inicial
     *
     * @param testInfo
     */
    @BeforeEach
    void setUp(TestInfo testInfo) {
        LOG.info("INICIO -> " + testInfo.getDisplayName());
    }

    /**
     * Establece el LOG final
     *
     * @param testInfo
     */
    @AfterEach
    void tearDown(TestInfo testInfo) {
        LOG.info("FIN -> " + testInfo.getDisplayName());
    }

    /**
     * Test para comprobar que se instancie el gimnasio uncio
     */
    @Test
    void testGetInstanceEsSingleton() {
        Gimnasio otraReferencia = Gimnasio.getInstance();
        assertSame(gimnasio, otraReferencia);
    }

    /**
     * Test para verificar el numero perfecto de un cliente
     */
    @Test
    void testVerificarNumeroPerfectoTrue() {
        assertTrue(gimnasio.verificarNumeroPerfecto("28"));
    }

    @Test
    void testVerificarNumeroPerfectoFalse() {
        assertFalse(gimnasio.verificarNumeroPerfecto("10"));
    }

    /**
     * Test para consultar los ingresos en una fecha establecida
     */
    @Test
    void testConsultarIngresosFecha() {
        LocalDate fecha = LocalDate.of(2026, 5, 10);

        gimnasio.crearCliente("Pedro Sánchez", "C004", "3008889900", "pedro@mail.com", 27, fecha);
        gimnasio.crearPlanBasico("PB002", "Plan Básico", "Descripción", 1, 45000, Estado.ACTIVO);
        gimnasio.asignarPlanACliente("C004", "PB002");

        assertEquals(45000, gimnasio.consultarIngresosFecha(fecha));
    }

    /**
     * Test para consultar los ingresos si el una suscripcion es inactiva
     */
    @Test
    void testConsultarIngresosFechaPlanNoActivo() {
        LocalDate fecha = LocalDate.of(2026, 6, 15);

        gimnasio.crearCliente("Sofía León", "C005", "3002223344", "sofia@mail.com", 22, fecha);
        gimnasio.crearPlanBasico("PB003", "Plan Básico", "Descripción", 1, 40000, Estado.SUSPENDIDO);
        gimnasio.asignarPlanACliente("C005", "PB003");

        assertEquals(0, gimnasio.consultarIngresosFecha(fecha));
    }
}