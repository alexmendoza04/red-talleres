import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD4514Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD4514equest = "Por evento 15.00% del monto a indemnizar, mínimo S/. 560.00, en talleres afiliados\n" +
                "Excepto para:\t\t\n" +
                "Siniestros atendidos en red de talleres afiliados multimarca 10.00% del monto a indemnizar, mínimo S/. 420.00\n" +
                "Robo Parcial 15% del monto a indemnizar, mínimo S/. 420.00\n" +
                "Accesorios musicales 10.00% del monto a indemnizar, mínimo S/. 420.00\n" +
                "Conductor varón menor  de 25 años, 20% del monto del siniestro mínimo S/. 840.00, para todo evento (incluído pédida total)\n" +
                "Toyota Rav4, Land Cruiser, Land Crusier Prado, FJ Cruiser, Fortuner, Nissan Patrol, Pathfinder, Suzuki Grand Nomade, Honda CRV nuevas y hasta 2 años con Sistema de Rastreo Vehicular Obligatorio para cobertura de robo total.\n" +
                "Camionetas Rurales/SUV mayores a S/. 140,000 Sistema de Rastreo Vehicular obligatorio para la cobertura de robo total.\n" +
                "Por evento, Marca Mercedes Benz, BMW, Audi: 20% del monto a indemnizar, mínimo S/. 560.00 en talleres afiliados.\n" +
                "Por evento, Marca Mercedes Benz, BMW, Audi: 15% del monto a indemnizar, mínimo S/. 420.00 en talleres afiliados multimarca.\n" +
                "Para Volcaduras incluyendo Xtrail, Pathfinder, Patrol, rurales y suv: 20%, monto del siniestro, mínimo S/. 420.00+ IGV\n" +
                "Imprudencia culposa 20% del siniestro mínimo S/. 840.00";

        String casoD4514 = "D4514";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD4514equest, casoD4514);

        deducibles.sort(Comparator.comparing(Deducible::getDeducible).thenComparing(Deducible::getCopago));

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible1 = deducibles.get(0);
        assertEquals("10.00", deducible1.getDeducible(), "El deducible debe ser 10");
        assertEquals("420.00", deducible1.getCopago(), "El copago debe ser 420.00");
        assertEquals("PEN", deducible1.getMoneda(), "La moneda debe ser PEN");
        assertEquals("Multimarca", deducible1.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible1.getTaller(), "El taller debe ser NO TALLER");


        Deducible deducible2 = deducibles.get(1);
        assertEquals("15", deducible2.getDeducible(), "El deducible debe ser 15");
        assertEquals("420.00", deducible2.getCopago(), "El copago debe ser 420.00");
        assertEquals("PEN", deducible2.getMoneda(), "La moneda debe ser PEN");
        assertEquals("Multimarca", deducible2.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("MERCEDES BENZ, BMW, AUDI", deducible2.getMarca(), "La marca debe ser MERCEDES BENZ, BMW, AUDI");
        assertEquals("NO TALLER", deducible2.getTaller(), "NO TALLER");

        Deducible deducible3 = deducibles.get(2);
        assertEquals("15.00", deducible3.getDeducible(), "El deducible debe ser 15");
        assertEquals("560.00", deducible3.getCopago(), "El copago debe ser 560");
        assertEquals("PEN", deducible3.getMoneda(), "La moneda debe ser PEN");
        assertEquals("NO TIPO", deducible3.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible3.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible3.getTaller(), "El taller debe ser NO TALLER");


        Deducible deducible4 = deducibles.get(3);
        assertEquals("20", deducible4.getDeducible(), "El deducible debe ser 20");
        assertEquals("560.00", deducible4.getCopago(), "El copago debe ser 560.00");
        assertEquals("PEN", deducible4.getMoneda(), "La moneda debe ser PEN");
        assertEquals("NO TIPO", deducible4.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("MERCEDES BENZ, BMW, AUDI", deducible4.getMarca(), "La marca debe ser MERCEDES BENZ, BMW, AUDI");
        assertEquals("NO TALLER", deducible4.getTaller(), "NO TALLER");


    }

}
