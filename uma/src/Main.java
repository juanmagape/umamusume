import java.util.Scanner;
import java.util.Random;

public class Main {
    public static int mood = 50;
    public static int energia = 100;
    public static int speed = new Random().nextInt(90, 101);
    public static int stamina = new Random().nextInt(90, 101);
    public static int power = new Random().nextInt(90, 101);
    public static int guts = new Random().nextInt(90, 101);
    public static int wit = new Random().nextInt(90, 101);
    public static boolean practicePerfect = false;
    public static boolean practicePoor = false;
    public static Scanner print = new Scanner(System.in);


    public static void main(String[] args) throws InterruptedException {

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
                    entrenar();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    System.out.println("\nSaliendo del programa...");
                    seguirJugando = false;
                    break;
            }
        }
    }

    public static void entrenar() {

        int baseGanancias = randomEntrenar();
        int baseFailRate = 0;
        if (energia < 50) {
            baseFailRate = (50 - energia) * 2;
        }

        int[] ganancias = new int[5];
        int[] perdidasEnergia = new int[5];
        int[] porcentajeFallo = new int[5];

        String[] nombresStats = {"Speed", "Stamina", "Power", "Guts", "Wit"};

        for (int i = 0; i < 5; i++) {
            ganancias[i] = randomEntrenar();

            if (i == 4) {
                porcentajeFallo[i] = baseFailRate / 4;
                ganancias[i] = baseGanancias / 2;
                perdidasEnergia[i] = 0;
            } else {
                porcentajeFallo[i] = baseFailRate;
                perdidasEnergia[i] = randomEnergiaPerder();
            }
        }

        System.out.println("\n=== Entrenar (Energía actual: " + energia + ") ===");

        for (int i = 0; i < 5; i++) {
            System.out.println((i + 1) + ". " + nombresStats[i] + " | +" + ganancias[i] + " stat" + " | -" + perdidasEnergia[i] + " Ene" + " | Riesgo: " + porcentajeFallo[i] + "%");
        }

        System.out.print("\nElige un entrenamiento (1-5): ");
        int opcion = print.nextInt();
        int indice = opcion - 1;

        if (indice >= 0 && indice < 5) {

            int suerte = new Random().nextInt(100);

            if (suerte < porcentajeFallo[indice]) {
                System.out.println("El entrenamiento falló! " + nombresStats[indice] + " no subió.");
                energia -= 5;
                mood -= 20;
            } else {
                int statGanado = ganancias[indice];
                int energiaPerdida = perdidasEnergia[indice];

                System.out.println("Ganaste " + statGanado + " puntos en " + nombresStats[indice]);

                if(energiaPerdida > 0) {
                    System.out.println("Perdiste " + energiaPerdida + " de energía.");
                    energia -= energiaPerdida;
                } else {
                    System.out.println("No perdiste energía por entrenar Wit");
                }

                switch (indice) {
                    case 0: speed += statGanado; break;
                    case 1: stamina += statGanado; break;
                    case 2: power += statGanado; break;
                    case 3: guts += statGanado; break;
                    case 4: wit += statGanado; break;
                }
            }
        } else {
            System.out.println("Opción no válida.");
        }
    }
    public static int randomEntrenar() {
        return new Random().nextInt(10, 30);
    }



    public static int randomEnergiaPerder() {
        return new Random().nextInt(20, 40);
    }

    public static void falloEntrenar(){

        System.out.println("=== Enfermería ===");
        System.out.println("1. Descansa hasta que te encuentres mejor");
        System.out.println("2. Esto no es nada, sigamos entrenando!");
        System.out.println("3. Ver efectos");
        int opc = print.nextInt();

        switch(opc){
            case 1:
                int accion = new Random().nextInt(2);
                if (accion == 1) {
                    mood = mood - 20;
                } else {
                    mood = mood - 20;
                    speed = speed - 5;
                }
                break;
            case 2:
                int accion2 = new Random().nextInt(3);
                if (accion2 == 1){
                    mood = mood - 20;
                    speed = speed - 5;
                } else if (accion2 == 2) {
                    System.out.println("Has adquirido: Practice Poor");
                    mood = mood - 20;
                    speed = speed - 5;
                } else {
                    System.out.println("Has adquirido: Practice Perfect");
                }
                break;
            case 3:
                break;
            default:
                System.out.println("Opción no válida");
                falloEntrenar();
                break;
        }
    }
}