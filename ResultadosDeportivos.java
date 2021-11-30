import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ResultadosDeportivos {

    public static void main(String[] args) {
        long inicio = System.currentTimeMillis();
        String file = "results.csv";
        int matchers = 0;
        int noMatchers = 0;

        // 1875-03-06,England,Scotland,2,2,Friendly,London,England,FALSE
        Pattern pattern = Pattern.compile("^([\\d]{4})-\\d+-\\d+,(.+),(.+),(\\d+),(\\d+),.*$");

        Path path = Paths.get(file);
        try {
            List<String> list_lines = Files.lines(path).collect(Collectors.toList());
            for (String line : list_lines) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String lugar = "";
                    int resultadoUno = Integer.parseInt(matcher.group(4));
                    int resultadoDos = Integer.parseInt(matcher.group(5));
                    if (resultadoUno > resultadoDos) {
                        System.out.print("Local:    ");
                    } else if (resultadoUno < resultadoDos) {
                        System.out.print("Visitante:");
                    } else {
                        System.out.print("Empate:   ");
                    }
                    System.out.println("\t" + lugar + matcher.group(2) + ", " + matcher.group(3) + " => [ " + resultadoUno + " - " + resultadoDos + " ]");
                    matchers++;
                } else {
                    noMatchers++;
                }
            }
            System.out.println("\nSe encontraron coincidencias: " + matchers + " y sin coincidencias: " + noMatchers);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.err.println("Tiempo: " + (System.currentTimeMillis()- inicio) + " ms");
    }
}