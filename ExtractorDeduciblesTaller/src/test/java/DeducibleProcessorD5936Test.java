import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD5936Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD5936Request = "Por evento 15.00% del monto a indemnizar, mínimo US$ 150.00, en talleres afiliados\n" +
                "Siniestros atendidos en red de talleres afiliados multimarca  10.00% del monto a indemnizar, mínimo US$ 150.00\n" +
                "Robo Parcial 15% del monto a indemnizar, mínimo US$ 150.00\n" +
                "Accesorios musicales 10.00% del monto a indemnizar, mínimo US$ 150.00\n" +
                "Conductor varón menor  de 25 años, 20% del monto del siniestro mínimo US$ 300, para todo evento (incluido pérdida total)\n" +
                "Todo autos y SW mayores a US$ 50,000, Sistema de Rastreo Vehicular obligatorio para la cobertura de robo total.\n" +
                "Toda Camioneta Rural/SUV mayor a US$ 50,000, Sistema de Rastreo Vehicular obligatorio para la cobertura de robo total\n" +
                "Toyota Rav4, Land Cruiser, Land Crusier Prado, FJ Cruiser, Fortuner, Nissan Patrol, Pathfinder, Suzuki Grand Nomade, Honda CRV nuevas y hasta 2 años de antiguedad con Sistema de Rastreo Vehicular Obligatorio para cobertura de robo total (instalado y debidamente operativo con los mantenimientos solicitados por el proveedor).\n" +
                "Reposición de lunas nacionales, sin deducible\n" +
                "Por evento, Marca Mercedes Benz, BMW, Audi: 20% del monto a indemnizar, mínimo US$ 200.00 en talleres afiliados\n" +
                "Por evento, Marca Mercedes Benz, BMW, Audi: 15% del monto a indemnizar, mínimo US$ 150.00 en talleres afiliados multimarca";

        String casoD5936 = "D5936";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD5936Request, casoD5936);

        deducibles.sort(Comparator.comparing(Deducible::getDeducible).thenComparing(Deducible::getCopago));

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible1 = deducibles.get(0);
        assertEquals("10.00", deducible1.getDeducible(), "El deducible debe ser 10");
        assertEquals("150.00", deducible1.getCopago(), "El copago debe ser 150.00");
        assertEquals("USD", deducible1.getMoneda(), "La moneda debe ser USD");
        assertEquals("Multimarca", deducible1.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible1.getTaller(), "El taller debe ser NO TALLER");

        Deducible deducible2 = deducibles.get(1);
        assertEquals("15", deducible2.getDeducible(), "El deducible debe ser 15");
        assertEquals("150.00", deducible2.getCopago(), "El copago debe ser 150.00");
        assertEquals("USD", deducible2.getMoneda(), "La moneda debe ser USD");
        assertEquals("Multimarca", deducible2.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("MERCEDES BENZ, BMW, AUDI", deducible2.getMarca(), "La marca debe ser MERCEDES BENZ, BMW, AUDI");
        assertEquals("NO TALLER", deducible2.getTaller(), "El taller debe ser NO TALLER");

        Deducible deducible3 = deducibles.get(2);
        assertEquals("15.00", deducible3.getDeducible(), "El deducible debe ser 15");
        assertEquals("150.00", deducible3.getCopago(), "El copago debe ser 150.00");
        assertEquals("USD", deducible3.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible3.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible3.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible3.getTaller(), "NO TALLER");

        Deducible deducible4 = deducibles.get(3);
        assertEquals("20", deducible4.getDeducible(), "El deducible debe ser 20");
        assertEquals("200.00", deducible4.getCopago(), "El copago debe ser 200.00");
        assertEquals("USD", deducible4.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible4.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("MERCEDES BENZ, BMW, AUDI", deducible4.getMarca(), "La marca debe ser MERCEDES BENZ, BMW, AUDI");
        assertEquals("NO TALLER", deducible4.getTaller(), "NO TALLER");




    }

}
