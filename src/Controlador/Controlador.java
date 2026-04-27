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
    private static String rutaConfig = "src/Archivos/config.txt";

    private static File archivoReserva = new File(rutaReserva);
    private static File logs = new File(rutaLogs);
    private static File archivoConfig = new File(rutaConfig);

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


    public static void inicializarConfiguracion() {
        try {
            if (!archivoConfig.exists()) {
                FileWriter writer = new FileWriter(archivoConfig);
                writer.write("max_jugadores=6\n");
                writer.write("hora_apertura=09:00\n");
                writer.write("hora_cierre=22:00\n");
                writer.close();
                registrarLog("Archivo de configuración creado por defecto.");
            }
        } catch (IOException e) {
            registrarLog("Error al crear configuración: " + e.getMessage());
        }
    }

    public static String obtenerConfiguracion(String clave) {
        try {
            if (archivoConfig.exists()) {
                Scanner reader = new Scanner(archivoConfig);
                while (reader.hasNextLine()) {
                    String linea = reader.nextLine();
                    String[] partes = linea.split("=");
                    if (partes.length == 2 && partes[0].trim().equals(clave)) {
                        reader.close();
                        return partes[1].trim();
                    }
                }
                reader.close();
            }
        } catch (FileNotFoundException e) {
            registrarLog("No se encuentra config.txt");
        }
        return null;
    }

    public static void editarConfiguracion(String clave, String nuevoValor) {
        ArrayList<String> lineas = new ArrayList<>();
        try {
            if (archivoConfig.exists()) {
                Scanner reader = new Scanner(archivoConfig);
                while (reader.hasNextLine()) {
                    String linea = reader.nextLine();
                    String[] partes = linea.split("=");
                    if (partes.length == 2 && partes[0].trim().equals(clave)) {
                        lineas.add(clave + "=" + nuevoValor);
                    } else {
                        lineas.add(linea);
                    }
                }
                reader.close();

                FileWriter writer = new FileWriter(archivoConfig, false);
                for (String linea : lineas) {
                    writer.write(linea + "\n");
                }
                writer.close();
                registrarLog("Configuración '" + clave + "' actualizada a: " + nuevoValor);
            }
        } catch (IOException e) {
            registrarLog("Error al editar configuración: " + e.getMessage());
        }
    }
}