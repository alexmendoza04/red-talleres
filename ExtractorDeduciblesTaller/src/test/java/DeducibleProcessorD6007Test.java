import org.rimacseguros.Deducible;
import org.rimacseguros.DeducibleProcessor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DeducibleProcessorD6007Test {

    private DeducibleProcessor deducibleProcessor;

    @BeforeEach
    void setUp() {
        deducibleProcessor = new DeducibleProcessor();
    }

    @Test
    void testExtractDeducibles_validText_returnsDeducible() {
        String inputTextD6007Request = "Por evento 15.00% del monto a indemnizar, mínimo US$ 150.00, en talleres afiliados\n" +
                "Siniestros atendidos en red de talleres afiliados multimarca  10.00% del monto a indemnizar, mínimo US$ 150.00\n" +
                "Robo Parcial 15% del monto a indemnizar, mínimo US$ 150.00\n" +
                "Accesorios musicales 10.00% del monto a indemnizar, mínimo US$ 150.00\n" +
                "Hyundai Tucson, Santa Fe: coaseguro por Robo Total (nuevos y hasta 2 años de antigüedad) 20%. Si el vehículo cuenta con GPS, se excluirá el coaseguro.\n" +
                "Por evento, Marca Mercedes Benz, BMW, Audi, Porsche Cayenne: 15% del monto a indemnizar, mínimo US$ 200.00 en talleres afiliados\n" +
                "Por evento, Marca Mercedes Benz, BMW, Audi, Porsche Cayenne: 10% del monto a indemnizar, mínimo US$ 150.00 en talleres afiliados multimarca\n" +
                "Marca Mercedes Benz, BMW, Audi, Porsche Cayenne\n" +
                "Mayores a US$ 75,000 hasta US$ 100,000: 15% del monto a indemnizar, mínimo US$ 1,500 para daños por hueco o daños por despiste contra sardineles por llantas Runflat\n" +
                "Menores a US$ 75,000: 15% del monto a indemnizar, mínimo US$ 800.00 para daños por hueco o daños por despiste contra sardineles por llantas Runflat\n" +
                "Reposición de lunas nacionales, sin deducible";

        String casoD6007 = "D6007";
        List<Deducible> deducibles = deducibleProcessor.extractDeducibles(inputTextD6007Request, casoD6007);
        deducibles.sort(Comparator.comparing(Deducible::getDeducible).thenComparing(Deducible::getCopago).thenComparing(Deducible::getMarca));

        assertTrue(deducibles.size() > 0, "La lista de deducibles no debe estar vacía");
        Deducible deducible3 = deducibles.get(0);
        assertEquals("10", deducible3.getDeducible(), "El deducible debe ser 10");
        assertEquals("150.00", deducible3.getCopago(), "El copago debe ser 150.00");
        assertEquals("USD", deducible3.getMoneda(), "La moneda debe ser USD");
        assertEquals("Multimarca", deducible3.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("MERCEDES BENZ, BMW, AUDI, PORSCHE CAYENNE", deducible3.getMarca(), "La marca debe ser MERCEDES BENZ, BMW, AUDI, PORSCHE CAYENNE");
        assertEquals("NO TALLER", deducible3.getTaller(), "NO TALLER");

        Deducible deducible1 = deducibles.get(1);
        assertEquals("10.00", deducible1.getDeducible(), "El deducible debe ser 10");
        assertEquals("150.00", deducible1.getCopago(), "El copago debe ser 150.00");
        assertEquals("USD", deducible1.getMoneda(), "La moneda debe ser USD");
        assertEquals("Multimarca", deducible1.getTipo(), "El tipo debe ser Multimarca");
        assertEquals("NO MARCA", deducible1.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible1.getTaller(), "NO TALLER");

        Deducible deducible4 = deducibles.get(2);
        assertEquals("15", deducible4.getDeducible(), "El deducible debe ser 15");
        assertEquals("200.00", deducible4.getCopago(), "El copago debe ser 200.00");
        assertEquals("USD", deducible4.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible4.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("MERCEDES BENZ, BMW, AUDI, PORSCHE CAYENNE", deducible4.getMarca(), "La marca debe ser MERCEDES BENZ, BMW, AUDI, PORSCHE CAYENNE");
        assertEquals("NO TALLER", deducible4.getTaller(), "El taller debe ser NO TALLER");

        Deducible deducible2 = deducibles.get(3);
        assertEquals("15.00", deducible2.getDeducible(), "El deducible debe ser 15");
        assertEquals("150.00", deducible2.getCopago(), "El copago debe ser 150.00");
        assertEquals("USD", deducible2.getMoneda(), "La moneda debe ser USD");
        assertEquals("NO TIPO", deducible2.getTipo(), "El tipo debe ser NO TIPO");
        assertEquals("NO MARCA", deducible2.getMarca(), "La marca debe ser NO MARCA");
        assertEquals("NO TALLER", deducible2.getTaller(), "El taller debe ser NO TALLER");

















    }

}
