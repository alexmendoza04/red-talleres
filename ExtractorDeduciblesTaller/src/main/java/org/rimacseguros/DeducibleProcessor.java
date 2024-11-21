package org.rimacseguros;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DeducibleProcessor {
    public static List<Deducible> extractDeducibles(String text, String caso) {
        List<Deducible> deducibles = new ArrayList<>();

        String[] lines = text.split("\\n");

        for (String line : lines) {
            switch (caso) {
                case "D85":
                case "D10":
                    processLineForDeducible(line, deducibles);
                    break;
                case "D6007":
                case "D5936":
                case "D4514":
                    processLineForEvent(line, deducibles);
                    break;
                case "D1256":
                    processLineForD1256(line, deducibles);
                    break;
                case "D86":
                    processLineForD86(line, deducibles);
                    break;
                case "D314":
                    processLineForD314(line, deducibles);
                    break;
                case "D88":
                    processLineForD88(line, deducibles);
                    break;
                case "D22":
                    processLineForD22(line, deducibles);
                    break;
                default:
                    System.err.println("Caso no soportado: " + caso);
                    break;
            }
        }
        return deducibles;
    }
    private static void processLineForD22(String line, List<Deducible> deducibles) {
        if (line.contains("Siniestros atendidos")) {
            Deducible deducible = new Deducible();
            extractDeducibleFromLine(line, deducible);
            deducibles.add(deducible);
        }
    }
    private static void processLineForD88(String line, List<Deducible> deducibles) {
        String normalizedLine = line.replace("\n", " ").trim();
        String regex = ".*monto a indemnizar.*\\d+.*talleres.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(normalizedLine);

        if (matcher.find()) {

            Deducible deducible = new Deducible();
            extractDeducibleFromLine(normalizedLine, deducible);
            deducibles.add(deducible);
        }

    }
    private static void processLineForD314(String line, List<Deducible> deducibles) {
        if (line.contains("monto del siniestro")) {
            Deducible deducible = new Deducible();
            extractDeducibleFromLine(line, deducible);
            deducibles.add(deducible);
        }
    }

    private static void processLineForD86(String line, List<Deducible> deducibles) {
        String normalizedLine = line.replace("\n", " ").trim();
        String regex = ".*Ausencia de control.*\\d+%.*m*nimo.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(normalizedLine);
        if (matcher.find()) {
            Deducible deducible = new Deducible();
            extractDeducibleFromLine(normalizedLine, deducible);
            deducibles.add(deducible);
        }
    }

    private static void processLineForD1256(String line, List<Deducible> deducibles) {
        if (line.contains("AUSENCIA DE CONTROL")) {
            Deducible deducible = new Deducible();
            extractDeducibleFromLine(line, deducible);
            deducibles.add(deducible);
        }
    }

    private static void processLineForEvent(String line, List<Deducible> deducibles) {
        if (line.contains("Por evento") || line.contains("Siniestros atendidos")) {
            Deducible deducible = new Deducible();
            extractDeducibleFromLine(line, deducible);
            deducibles.add(deducible);
        }
    }

    private static void processLineForDeducible(String line, List<Deducible> deducibles) {
        String normalizedLine = line.replace("\n", " ").trim();
        String regex = ".*\\d+%.*m*nimo.*";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(normalizedLine);

        if (matcher.find()) {
            Deducible deducible = new Deducible();
            extractDeducibleFromLine(normalizedLine, deducible); // Asegúrate de que esta función esté correctamente implementada
            deducibles.add(deducible);
        }
    }
    private static void extractDeducibleFromLine(String line, Deducible deducible) {
        deducible.setDeducible(extractDeducible(line));
        deducible.setCopago(extractCopago(line));
        deducible.setMoneda(extractMoneda(line));
        deducible.setTipo(extractTipo(line));
        deducible.setTaller(extractTaller(line));
        deducible.setMarca(extractMarca(line));
    }

    private static String extractMoneda(String line) {
        if (line.contains("US$")) {
            return "USD";
        } else if (line.contains("S/")) {
            return "PEN";
        } else {
            return "NO MONEDA";
        }
    }
    private static String extractTipo(String line) {
        return line.toLowerCase().contains(MULTIMARCA_PATTERN) ? "Multimarca" : "NO TIPO";
    }

    private static String extractDeducible(String line) {
        Matcher matcher = Pattern.compile(DEDUCIBLE_PATTERN).matcher(line);
        return matcher.find() ? matcher.group(1) : "NO DEDUCIBLE";
    }
    private static String extractCopago(String line) {
        Matcher matcher = Pattern.compile(COPAGO_PATTERN).matcher(line.toLowerCase());
        return matcher.find() ? matcher.group(1).replace(",", "") : "NO COPAGO";
    }

    private static String extractTaller(String line) {
        String lineLower = line.toLowerCase();

        if (lineLower.contains(TALLERES_AFILIADOS) || lineLower.contains(TALLERES_MULTIMARCA) || lineLower.contains(TALLERES_PREFERENCIALES) || lineLower.contains(TALLERES_ESPECIALES) || lineLower.contains(TALLERES_OTROS)) {
            return "NO TALLER";
        }

        Matcher matcherTaller = Pattern.compile("talleres\\s+([A-Za-z\\s]+)", Pattern.CASE_INSENSITIVE).matcher(line);
        if (matcherTaller.find()) {
            return matcherTaller.group(1).trim();
        }
        return "NO TALLER";
    }

    private static String extractMarca(String line) {
        List<String> marcas = Arrays.asList("Mercedes Benz", "BMW", "Audi", "Porsche Cayenne");
        boolean containsAll = marcas.stream().allMatch(line::contains);
        if (containsAll) {
            return "MERCEDES BENZ, BMW, AUDI, PORSCHE CAYENNE";
        }
        if (line.contains("Mercedes Benz") && line.contains("BMW") && line.contains("Audi")) {
            return "MERCEDES BENZ, BMW, AUDI";
        }
        return "NO MARCA";
    }

    private static final String DEDUCIBLE_PATTERN = "(\\d+(\\.\\d+)?)(?=%)";
    private static final String COPAGO_PATTERN = "m*nimo.*?([\\d,]+(?:\\.\\d+)?)";
    private static final String MULTIMARCA_PATTERN = "multimarca";
    private static final String TALLERES_AFILIADOS = "talleres afiliados";
    private static final String TALLERES_MULTIMARCA = "talleres multimarca";
    private static final String TALLERES_PREFERENCIALES = "talleres preferenciales";
    private static final String TALLERES_ESPECIALES = "talleres especiales";
    private static final String TALLERES_OTROS = "otros talleres";

}
