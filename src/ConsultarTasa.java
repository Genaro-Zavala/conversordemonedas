import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConsultarTasa {

    public static double obtenerTasa(String monedaOrigen, String monedaDestino) {
        try {
            // Crea el cliente HTTP y solicita la API con la moneda base
            String apiUrl = "https://v6.exchangerate-api.com/v6/9a518f55b4d21180347df5fe/latest/" + monedaOrigen;
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String jsonResponse = response.body();

            // Parsear la respuesta JSON
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);
            JsonObject rates = jsonObject.getAsJsonObject("conversion_rates");

            // Retorna la tasa de la moneda de destino
            return rates.get(monedaDestino).getAsDouble();

        } catch (Exception e) {
            System.out.println("Error al obtener la tasa de conversión: " + e.getMessage());
            return 0; // Retornar 0 en caso de error
        }
    }
}

