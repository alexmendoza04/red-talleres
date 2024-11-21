import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD314Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD314Request = "10% del monto del siniestro, mínimo US$ 500.00, en Talleres Nissan Maquinarias\n" +
                "10% del monto del siniestro, mínimo US$ 700.00, en Otros Talleres\n" +
                "En caso de discrepancia prevalece el mayor. No incluye I.G.V.";

        String casoD314 = "D314";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD314Request, casoD314);

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible1 = deducibles.get(0);
        assertEquals("10", deducible1.getDeducible(), "El deducible debe ser 10");
        assertEquals("500.00", deducible1.getCopago(), "El copago debe ser 500.00");
        assertEquals("USD", deducible1.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible1.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("Nissan Maquinarias", deducible1.getTaller(), "El taller debe ser Nissan Maquinarias");

        Deducible deducible2 = deducibles.get(1);
        assertEquals("10", deducible2.getDeducible(), "El deducible debe ser 10");
        assertEquals("700.00", deducible2.getCopago(), "El copago debe ser 700.00");
        assertEquals("USD", deducible2.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible2.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible2.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible2.getTaller(), "El taller debe ser NO TALLER");
    }

}
