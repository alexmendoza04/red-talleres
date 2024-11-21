import org.rimacseguros.DeducibleProcessor;
import org.rimacseguros.Deducible;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD22Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD22Request = "(No Incluye I.G.V.)\n" +
                "Por evento 20% del monto a indemnizar, mínimo US$200.00\n" +
                "Excepto para:\n" +
                "Robo Parcial 10% del monto a indemnizar, mínimo US$150.00\n" +
                "Siniestros atendidos en talleres preferenciales 10% del monto a indemnizar, mínimo US$150.00\n" +
                "Robo de accesorios Musicales 10% del monto a indemnizar, mínimo 150.00\n" +
                "Responsabilidad civil 10% del monto a indemnizar, mínimo 250.00\n" +
                "Robo total solo se aseguran con GPS obligatorio hasta el segundo año de antigüedad, sin coaseguro. Tercer año, coaseguro 80/20";

        String casoD22 = "D22";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD22Request, casoD22);

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible = deducibles.get(0);
        assertEquals("10", deducible.getDeducible(), "El deducible debe ser 10");
        assertEquals("150.00", deducible.getCopago(), "El copago debe ser 150");
        assertEquals("USD", deducible.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible.getTaller(), "El taller debe ser NO TALLER");
    }

}
