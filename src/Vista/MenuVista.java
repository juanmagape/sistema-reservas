package Vista;
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
}
