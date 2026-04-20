package Controlador;
import java.io.*;

public class GestorFicheros {
    private static String RUTASALA = "src/Archivos/salas.txt";
    private static String RUTARESERVA = "src/Archivos/reservas.txt";

    public static void guardarSala(String datosSala) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTASALA, true))) {
            writer.write(datosSala);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la sala: " + e.getMessage());
        }
    }

    public static void leerReservas() {
        try (BufferedReader reader = new BufferedReader(new FileReader(RUTARESERVA))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer las reservas: " + e.getMessage());
        }
    }

    public static void guardarReserva(String datosReserva) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(RUTARESERVA, true))) {
            writer.write(datosReserva);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva: " + e.getMessage());
        }
    }
}
