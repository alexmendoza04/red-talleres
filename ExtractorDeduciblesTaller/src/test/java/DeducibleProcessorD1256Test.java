import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD1256Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD1256Request = "AUSENCIA DE CONTROL EN TALLERES JAPAN AUTOS, 22% del DEL MONTO DEL SINIESTRO, Mínimo de US$500.00. AUSENCIA DE CONTROL";
        String casoD314 = "D1256";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD1256Request, casoD314);

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible1 = deducibles.get(0);
        assertEquals("22", deducible1.getDeducible(), "El deducible debe ser 22");
        assertEquals("500.00", deducible1.getCopago(), "El copago debe ser 500.00");
        assertEquals("USD", deducible1.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible1.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("JAPAN AUTOS", deducible1.getTaller(), "El taller debe ser JAPAN AUTOS");

    }

}
