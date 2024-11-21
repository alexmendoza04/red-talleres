import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD86Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD86Request = "- Ausencia de control: 25.00% del monto indemnizar, mínimo US$ 500.00 (Talleres Afiliados).\n" +
                "- Ausencia de control: 25.00% del monto indemnizar, mínimo US$ 300.00 (Talleres Afiliados Multimarca).\n" +
                "-Pérdida total por ausencia de control: 25.00% del monto a i";
        String casoD86 = "D86";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD86Request, casoD86);
        deducibles.sort(Comparator.comparing(Deducible::getDeducible).thenComparing(Deducible::getCopago));

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible1 = deducibles.get(0);
        assertEquals("25.00", deducible1.getDeducible(), "El deducible debe ser 25");
        assertEquals("300.00", deducible1.getCopago(), "El copago debe ser 500");
        assertEquals("USD", deducible1.getMoneda(), "La moneda debe ser USD");
        assertEquals("Multimarca", deducible1.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible1.getTaller(), "El taller debe ser NO TALLER");

        Deducible deducible2 = deducibles.get(1);
        assertEquals("25.00", deducible2.getDeducible(), "El deducible debe ser 25 prueba");
        assertEquals("500.00", deducible2.getCopago(), "El copago debe ser 500.00");
        assertEquals("USD", deducible2.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible2.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible2.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible2.getTaller(), "El taller debe ser NO TALLER");



    }

}
