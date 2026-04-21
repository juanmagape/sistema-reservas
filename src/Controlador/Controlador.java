package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {

    private static String rutaLogs = "src/Archivos/logs.txt";
    private static String rutaReserva = "src/Archivos/reservas.txt";

    private static File archivoReserva = new File(rutaReserva);
    private static File logs = new File(rutaLogs);

    public static File getArchivoReserva() {
        return archivoReserva;
    }

    public static void guardarReserva(String datosReserva) {
        try {
            FileWriter writer = new FileWriter(archivoReserva, true);
            writer.write(datosReserva + "\n");
            writer.close();

            registrarLog("Reserva guardada: " + datosReserva);
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva: " + e.getMessage());
            registrarLog("Error al guardar la reserva: " + e.getMessage());
        }
    }


    public static void leerReservas() {
        try {
            Scanner reader = new Scanner(archivoReserva);

            while (reader.hasNextLine()) {
                String linea = reader.nextLine();
                System.out.println(linea);
            }

            reader.close();
            registrarLog("Se han revisado las reservas actuales.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de reservas no existe: " + e.getMessage());
            registrarLog("Error al leer las reservas: " + e.getMessage());
        }
    }


    public static boolean eliminarReservas(int numeroLinea) {
        ArrayList<String> todasLasLineas = new ArrayList<>();

        try {
            Scanner reader = new Scanner(archivoReserva);
            while (reader.hasNextLine()) {
                todasLasLineas.add(reader.nextLine());
            }
            reader.close();
            registrarLog("Se ha eliminado la reserva número: " + numeroLinea);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de reservas no existe todavía.");
            registrarLog("Error al eliminar la reserva: " + e.getMessage());
            return false;
        }

        int indiceReal = numeroLinea - 1;

        if (indiceReal >= 0 && indiceReal < todasLasLineas.size()) {

            todasLasLineas.remove(indiceReal);

            try {
                FileWriter writer = new FileWriter(archivoReserva, false);

                for (String lineaRestante : todasLasLineas) {
                    writer.write(lineaRestante + "\n");
                }

                writer.close();
                return true;

            } catch (IOException e) {
                System.out.println("Error al sobrescribir el archivo: " + e.getMessage());
                registrarLog("Error al eliminar la reserva: " + e.getMessage());
                return false;
            }

        } else {
            return false;
        }
    }

    public static ArrayList<String> obtenerLineasReserva() {
        ArrayList<String> lineas = new ArrayList<>();
        try {
            Scanner reader = new Scanner(archivoReserva);
            while (reader.hasNextLine()) {
                lineas.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
        }
        return lineas;
    }

    public static void registrarLog(String accion) {
        LocalDateTime ahora = LocalDateTime.now();

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String fechaFormateada = ahora.format(formato);

        String mensajeLog = "[" + fechaFormateada + "] " + accion;

        try {
            FileWriter writer = new FileWriter(logs, true);
            writer.write(mensajeLog + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el log: " + e.getMessage());
        }
    }
}