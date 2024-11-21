import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD88Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD88Request = ". 20% del monto a indemnizar, mínimo US$ 250.00, para todo y cada evento, en talleres afiliados\n" +
                "20% del monto a indemnizar, mínimo US$ 200.00, para todo y cada evento, en talleres afiliados multimarca\n" +
                "Pérdida Total, Incendio, Robo Total:  20% del monto del siniestro\n" +
                "Conductor varón menor  de 25 años, 25% del monto del siniestro mínimo US$ 300, para todo y cada evento\n" +
                "Rotura de lunas, solo para reposición de lunas nacionales sin deducible\n" +
                "Vías no autorizadas 25% del monto a indemnizar, mínimo US$ 300.00, para todo y cada evento";
        String casoD88 = "D88";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD88Request, casoD88);

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");

        Deducible deducible1 = deducibles.get(0);
        assertEquals("20", deducible1.getDeducible(), "El deducible debe ser 20");
        assertEquals("250.00", deducible1.getCopago(), "El copago debe ser 250");
        assertEquals("USD", deducible1.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible1.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible1.getTaller(), "El taller debe ser NO TALLER");

        Deducible deducible2 = deducibles.get(1);
        assertEquals("20", deducible2.getDeducible(), "El deducible debe ser 20");
        assertEquals("200.00", deducible2.getCopago(), "El copago debe ser 200.00");
        assertEquals("USD", deducible2.getMoneda(), "La moneda debe ser USD");
        assertEquals("Multimarca", deducible2.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("NO MARCA", deducible2.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible2.getTaller(), "El taller debe ser NO TALLER");
    }

}
