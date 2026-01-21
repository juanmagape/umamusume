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
    public static Scanner print = new Scanner(System.in);

    public static String[] umas = {"Mambo", "Special Week", "Silence Suzuka", "Tokai Teio", "Oguri Cap", "Gold Ship", "Vodka", "El Condor Pasa", "Daitaku Helios", "Haru Urara", "Agnes Tachyon", "T.M Opera O"};
    public static int seleccionUma = new  Random().nextInt(0, 12);
    public static String nombreUma = umas[seleccionUma];

    public static void main(String[] args) throws InterruptedException {

        boolean seguirJugando = true;

        while (seguirJugando) {

            if (mood < 0) { mood = 0; }
            if (mood > 100) { mood = 100; }

            if (energia < 0) { energia = 0; }
            if (energia > 100) { energia = 100; }

            System.out.println("============================");
            System.out.println(nombreUma + " | Energia: " + energia + " | Mood: " + Mood());
            System.out.println("============================");
            System.out.println("Estadísticas de tu UMA actuales: ");
            System.out.println("Speed: " + speed);
            System.out.println("Stamina: " + stamina);
            System.out.println("Power: " + power);
            System.out.println("Guts: " + guts);
            System.out.println("Wit: " + wit);
            System.out.println("============================");

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
                    Recreation();
                    break;
                case 4:
                    System.out.println("\nSaliendo del programa...");
                    seguirJugando = false;
            }
        }
    }

    public static String Mood() {

        if (mood < 20) {
            speed -=(int) (speed * 0.10);
            stamina -= (int)(stamina * 0.10);
            power -=(int) (power * 0.10);
            guts -=(int) (guts * 0.10);
            wit -=(int) (wit * 0.10);

            return  "Awful";
        } else if (mood >= 20 && mood <= 39) {

            speed -=(int) (speed * 0.05);
            stamina -= (int)(stamina * 0.05);
            power -=(int) (power * 0.05);
            guts -=(int) (guts * 0.05);
            wit -=(int) (wit * 0.05);

            return "Bad";
        } else if (mood >= 40 && mood <= 59) {
            return "Normal";
        } else if (mood >= 60 && mood <= 79) {

            speed +=(int) (speed * 0.02);
            stamina +=(int) (stamina * 0.02);
            power +=(int) (power * 0.02);
            guts +=(int) (guts * 0.02);
            wit += (int) (wit * 0.02);

            return "Good";
        } else if (mood >= 80 && mood <= 100) {

            speed += (int) (speed * 0.04);
            stamina +=(int) (stamina * 0.04);
            power +=(int) (power * 0.04);
            guts +=(int) (guts * 0.04);
            wit +=(int) (wit * 0.04);

            return "Great";
        }
        return "Unkown";
    }

    public static void Recreation() {
        int recreacion = new Random().nextInt(1, 4);
        int posibilidadSubStats =  new Random().nextInt(1, 100);

        switch (recreacion) {
            case 1:
                System.out.println("Paseo al parque");
                mood += mood + 20;
                System.out.println("El mood a subido a " + Mood());
                break;
            case 2:
                System.out.println("Salida al Karaoke");
                mood += mood + 25;
                System.out.println("El mood a subido a " + Mood());
                if (posibilidadSubStats < 50) {
                    energia += energia + 10;
                    System.out.println("Energia + 10");
                }
                break;
            case 3:
                System.out.println("Visita a la pastelería");
                mood += mood + 30;
                System.out.println("El mood a subido a " + Mood());

                if (posibilidadSubStats < 50) {
                    energia += energia + 20;
                    System.out.println("Energia + 20");
                }

                break;
            case 4:
                System.out.println("Evento de recreación");
                mood += mood + 50;
                energia += energia + 30;
                System.out.println("El mood a subido a " + Mood());
                System.out.println("Energia + 30");
                break;
        }
    }
}


