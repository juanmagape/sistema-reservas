package Vista;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MenuVista {
    public static Scanner sc = new Scanner(System.in);

    public static void Menu() {
        while (true) {
            System.out.println("================================================");
            System.out.println("=Sistema de reservas ScapeRoom - Menú principal=");
            System.out.println("================================================");

            System.out.println("\n[1] - Crear reserva");
            System.out.println("[2] - Ver reservas");
            System.out.println("[3] - Eliminar reserva");
            System.out.println("[4] - Salir");

            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    System.out.println("Has seleccionado: Crear reserva");
                    break;
                case 2:
                    System.out.println("Has seleccionado: Ver reservas");
                    break;
                case 3:
                    System.out.println("Has seleccionado: Eliminar reserva");
                    break;
                case 4:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del menú.");
            }
        }
    }

    public static void AnadirReserva() {
        System.out.println("================================================");
        System.out.println("=Sistema de reservas ScapeRoom - Añadir reserva=");
        System.out.println("================================================");

        System.out.println("\nIntroduce el ID de la sala");

        System.out.println("\nIntroduce el nombre del cliente:");
        String nombreCliente = sc.next();

        System.out.println("\nIntroduce el teléfono del cliente:");
        int telefonoCliente = sc.nextInt();

        System.out.println("Introduce el número de jugadores:");
        int numJugadores = sc.nextInt();

        System.out.println("Introduce la fecha de la reserva (dd/mm/yyyy):");
        String fechaReserva = sc.next();

        System.out.println("Introduce la hora de la reserva (hh:mm):");
        String horaReserva = sc.next();
    }

    public static void VerReserva(File fichero) throws FileNotFoundException {
        System.out.println("================================================");
        System.out.println("===Sistema de reserva ScapeRoom - Ver reserva===");
        System.out.println("================================================");

        Scanner reader = new Scanner(fichero);
        int i = 1;

        if (!reader.hasNextLine()) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        while (reader.hasNextLine()) {
            String linea = reader.nextLine();
            System.out.println("[" + i + "] - " + linea);
            i++;
        }
    }
}
