import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneradorArchivo {

    public static void guardarEnHistorial(String monedaOrigen, String monedaDestino, double cantidad, double resultado) {
        try (FileWriter writer = new FileWriter("historial.txt", true)) {
            // Formato de fecha y hora actual
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();

            // Escribir en el archivo con formato para decimales
            writer.write("Fecha: " + dtf.format(now) + "\n");
            writer.write(String.format("Conversión: %.2f %s => %.2f %s%n", cantidad, monedaOrigen, resultado, monedaDestino));
            writer.write("------------------------\n");

            System.out.println("Conversión guardada en el historial.");
        } catch (IOException e) {
            System.out.println("Error al guardar en el archivo: " + e.getMessage());
        }
    }
}
