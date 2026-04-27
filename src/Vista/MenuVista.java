package Vista;
import Controlador.Controlador;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuVista {
    public static Scanner sc = new Scanner(System.in);

    public static void Menu() throws FileNotFoundException {
        while (true) {
            System.out.println("==================================================");
            System.out.println("= Sistema de reservas ScapeRoom - Menú principal =");
            System.out.println("==================================================");

            System.out.println("\n[1] - Crear reserva");
            System.out.println("[2] - Ver reservas");
            System.out.println("[3] - Eliminar reserva");
            System.out.println("[4] - Configuración");
            System.out.println("[5] - Salir");

            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    AnadirReserva();
                    break;
                case 2:
                    VerReserva();
                    break;
                case 3:
                    EliminarReserva(Controlador.obtenerLineasReserva());
                    break;
                case 4:
                    Configuracion();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del menú.");
            }
        }
    }

    public static void Configuracion() {
        System.out.println("==================================================");
        System.out.println("= Sistema de reservas ScapeRoom - Configuración =");
        System.out.println("==================================================");

        System.out.println("\n[1] - Establecer hora de apertura");
        System.out.println("[2] - Establecer hora de cierre");
        System.out.println("[3] - Establecer máximo de jugadores");
        System.out.println("[4] - Volver al menú principal");

        int opcionConfig = sc.nextInt();

        switch (opcionConfig) {
            case 1:
                System.out.println("Introduce la nueva hora de apertura (hh:mm, ej: 09:30):");
                String nuevaHoraApertura = sc.next();
                Controlador.editarConfiguracion("hora_apertura", nuevaHoraApertura);
                System.out.println("Hora de apertura actualizada.");
                break;
            case 2:
                System.out.println("Introduce la nueva hora de cierre (hh:mm, ej: 21:00):");
                String nuevaHoraCierre = sc.next();
                Controlador.editarConfiguracion("hora_cierre", nuevaHoraCierre);
                System.out.println("Hora de cierre actualizada.");
                break;
            case 3:
                System.out.println("Introduce el nuevo máximo de jugadores:");
                String nuevoMaxJugadores = sc.next();
                Controlador.editarConfiguracion("max_jugadores", nuevoMaxJugadores);
                System.out.println("Máximo de jugadores actualizado.");
                break;
            case 4:
                return;
            default:
                System.out.println("Opción no válida. Volviendo al menú principal.");
        }
    }

    public static void AnadirReserva() {
        System.out.println("==================================================");
        System.out.println("= Sistema de reservas ScapeRoom - Añadir reserva =");
        System.out.println("==================================================");

        System.out.println("\nIntroduce el ID de la sala");
        int idSala = sc.nextInt();

        System.out.println("\nIntroduce el nombre del cliente:");
        String nombreCliente = sc.next();

        System.out.println("\nIntroduce el teléfono del cliente:");
        int telefonoCliente = sc.nextInt();

        System.out.println("\nIntroduce el número de jugadores:");
        int numJugadores = sc.nextInt();
        String maxConfig = Controlador.obtenerConfiguracion("max_jugadores");
        if (maxConfig != null && numJugadores > Integer.parseInt(maxConfig)) {
            System.out.println("El máximo de jugadores permitido es " + maxConfig);
            return;
        }

        System.out.println("\nIntroduce la fecha de la reserva (dd/mm/yyyy):");
        String fechaReserva = sc.next();

        System.out.println("\nIntroduce la hora de la reserva (hh:mm, ej: 09:30):");
        String horaReserva = sc.next();
        String hApertura = Controlador.obtenerConfiguracion("hora_apertura");
        String hCierre = Controlador.obtenerConfiguracion("hora_cierre");

        if (hApertura != null && hCierre != null) {
            if (horaReserva.compareTo(hApertura) < 0 || horaReserva.compareTo(hCierre) > 0) {
                System.out.println("El local está cerrado. Horario: " + hApertura + " a " + hCierre);
                return;
            }
        }

        int idReserva = Controlador.obtenerLineasReserva().size() + 1;

        Modelo.Reserva nuevaReserva = new Modelo.Reserva(idSala, idReserva, fechaReserva, horaReserva, nombreCliente, telefonoCliente, numJugadores);

        Controlador.guardarReserva(nuevaReserva.toString());

        System.out.println("Reserva guardada con éxito!");
    }

    public static void VerReserva() throws FileNotFoundException {
        System.out.println("==================================================");
        System.out.println("=== Sistema de reserva ScapeRoom - Ver reserva ===");
        System.out.println("==================================================");

        Controlador.leerReservas();
    }

    public static void EliminarReserva(ArrayList<String> listaReservas) throws FileNotFoundException {
        System.out.println("=======================================================");
        System.out.println("=== Sistema de reserva ScapeRoom - Eliminar reserva ===");
        System.out.println("=======================================================");

        if (listaReservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
            return;
        }

        for (int i = 0; i < listaReservas.size(); i++) {
            System.out.println("[" + (i + 1) + "] - " + listaReservas.get(i));
        }

        System.out.println("\nIntroduce el número de la reserva que deseas eliminar:");
        int reservaEliminar = sc.nextInt();

        boolean borrado = Controlador.eliminarReservas(reservaEliminar);

        if (borrado) {
            System.out.println("Reserva eliminada correctamente.");
        } else {
            System.out.println("No se pudo eliminar la reserva. Verifica el número ingresado.");
        }
    }
}
