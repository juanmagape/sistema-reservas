package Controlador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controlador {

    private static String RUTA_SALA = "src/Archivos/salas.txt";
    private static String RUTA_RESERVA = "src/Archivos/reservas.txt";

    private static File archivoSala = new File(RUTA_SALA);
    private static File archivoReserva = new File(RUTA_RESERVA);

    public static File getArchivoReserva() {
        return archivoReserva;
    }

    public static void guardarReserva(String datosReserva) {
        try {
            FileWriter writer = new FileWriter(archivoReserva, true);
            writer.write(datosReserva + "\n");
            writer.close();

        } catch (IOException e) {
            System.out.println("Error al guardar la reserva: " + e.getMessage());
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

        } catch (FileNotFoundException e) {
            System.out.println("El archivo de reservas no existe: " + e.getMessage());
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
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de reservas no existe todavía.");
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
}