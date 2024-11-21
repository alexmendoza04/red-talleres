import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD85Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD85Request = "*Los siniestros, serán atendidos únicamente en la relación de talleres especiales descritos en la cláusula VEHA07\n" +
                "20% del monto indemnizable, mínimo US$ 200\n" +
                "20% del monto indemnizable para pérdida total";
        String casoD85 = "D85";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD85Request, casoD85);

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible = deducibles.get(0);
        assertEquals("20", deducible.getDeducible(), "El deducible debe ser 20");
        assertEquals("200", deducible.getCopago(), "El copago debe ser 200");
        assertEquals("USD", deducible.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible.getTaller(), "El taller debe ser NO TALLER");
    }

}
