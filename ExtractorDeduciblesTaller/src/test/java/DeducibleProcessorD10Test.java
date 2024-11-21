import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD10Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD10Request = "*Por Evento 15% del monto del siniestro, mínimo US$ 150.00 en Talleres Afiliados Multimarca\n" +
                "*Por Evento 15% del monto del siniestro, mínimo US$ 250.00 en Talleres Afiliados";

        String casoD10 = "D10";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD10Request, casoD10);

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible1 = deducibles.get(0);
        assertEquals("15", deducible1.getDeducible(), "El deducible debe ser 15");
        assertEquals("150.00", deducible1.getCopago(), "El copago debe ser 150");
        assertEquals("USD", deducible1.getMoneda(), "La moneda debe ser USD");
        assertEquals("Multimarca", deducible1.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible1.getTaller(), "El taller debe ser NO TALLER");

        Deducible deducible2 = deducibles.get(1);
        assertEquals("15", deducible2.getDeducible(), "El deducible debe ser 15");
        assertEquals("250.00", deducible2.getCopago(), "El copago debe ser 250.00");
        assertEquals("USD", deducible2.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible2.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible2.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible2.getTaller(), "El taller debe ser NO TALLER");
    }

}
