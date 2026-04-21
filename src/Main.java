import Controlador.Controlador;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Controlador.registrarLog("El programa ha iniciado.");

        Vista.MenuVista.Menu();

    }
}
