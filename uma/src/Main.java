import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Scanner print = new Scanner(System.in);
        boolean seguirJugando = true;

        while (seguirJugando) {

            System.out.println("============================");
            System.out.println("[1] - Entrenar");
            System.out.println("[2] - Descanso");
            System.out.println("[3] - Recreación");
            System.out.println("[4] - Salir");
            System.out.println("============================");

            int opcion = print.nextInt();

            switch (opcion) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("\nSaliendo del programa...");
                    seguirJugando = false;
            }
        }
    }
}
