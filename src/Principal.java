import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        do {
            // Mostrar el menú
            System.out.println("******* MENU *******");
            System.out.println("[1] - Dolar =>> Peso argentino");
            System.out.println("[2] - Peso argentino =>> Dolar");
            System.out.println("[3] - Dolar =>>> Real brasilero");
            System.out.println("[4] - Real brasilero =>> Dolar");
            System.out.println("[5] - Dolar =>> Nuevo sol");
            System.out.println("[6] - Nuevo sol =>> Dolar");
            System.out.println("[7] - Salir");
            System.out.println("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    realizarConversion("USD", "ARS");
                    break;
                case 2:
                    realizarConversion("ARS", "USD");
                    break;
                case 3:
                    realizarConversion("USD", "BRL");
                    break;
                case 4:
                    realizarConversion("BRL", "USD");
                    break;
                case 5:
                    realizarConversion("USD", "PEN");
                    break;
                case 6:
                    realizarConversion("PEN", "USD");
                    break;
                case 7:
                    System.out.println("Gracias por utilizar el conversor de monedas.");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        } while (opcion != 7);
        scanner.close();
    }

    // Método para realizar la conversión usando la clase ConsultarTasa
    private static void realizarConversion(String monedaOrigen, String monedaDestino) {
        double tasa = ConsultarTasa.obtenerTasa(monedaOrigen, monedaDestino);
        if (tasa == 0) {
            System.out.println("No se pudo obtener la tasa de conversión.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la cantidad de " + monedaOrigen + " a convertir: ");
        double cantidad = scanner.nextDouble();

        double resultado = cantidad * tasa;
        System.out.println("La cantidad de " + cantidad + " " + monedaOrigen + " es equivalente a " + resultado + " " + monedaDestino);

        // Guardar en el historial
        GeneradorArchivo.guardarEnHistorial(monedaOrigen, monedaDestino, cantidad, resultado);
    }
}
