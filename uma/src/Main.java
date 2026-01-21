import java.util.Random;
import java.util.Scanner;

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
    public static boolean nightOwl = false;
    public static Scanner print = new Scanner(System.in);

    public static String[] umas = {"Mambo", "Special Week", "Silence Suzuka", "Tokai Teio", "Oguri Cap", "Gold Ship", "Vodka", "El Condor Pasa", "Daitaku Helios", "Haru Urara", "Agnes Tachyon", "T.M Opera O"};
    public static int seleccionUma = new  Random().nextInt(0, 12);
    public static String nombreUma = umas[seleccionUma];

    public static void main(String[] args) throws InterruptedException {

        boolean seguirJugando = true;
        int contadorTurnos = 0;

        while (seguirJugando) {
            contadorTurnos++;

            if (nightOwl) {
                int nightRandom = new Random().nextInt(1, 101);

                if (nightRandom > 50) {
                    speed -=(int) (speed * 0.10);
                    stamina -= (int)(stamina * 0.10);
                    power -=(int) (power * 0.10);
                    guts -=(int) (guts * 0.10);
                    wit -=(int) (wit * 0.10);
                }
            }

            if (mood < 0) { mood = 0; }
            if (mood > 100) { mood = 100; }

            if (energia < 0) { energia = 0; }
            if (energia > 100) { energia = 100; }

            if (contadorTurnos == 10 || contadorTurnos == 15 || contadorTurnos == 30 || contadorTurnos == 48) {
                
            }

            System.out.println("Turno actual: " + contadorTurnos);


            if (contadorTurnos < 10) {
                int falta = 10 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para el Debut Race");

            } else if (contadorTurnos == 10) {
                System.out.println("¡HOY ES EL DEBUT RACE!");

            } else if (contadorTurnos < 15) {
                int falta = 15 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la Junior Cup");

            } else if (contadorTurnos == 15) {
                System.out.println("¡HOY ES LA JUNIOR CUP!");

            } else if (contadorTurnos < 30) {
                int falta = 30 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la Classic Race");

            } else if (contadorTurnos == 30) {
                System.out.println("¡HOY ES LA CLASSIC RACE!");

            } else if (contadorTurnos < 48) {
                int falta = 48 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la URA Finals.");
            } else if (contadorTurnos == 48) {
                System.out.println("¡HOY ES LA FINAL URA!");
            }

            System.out.println("============================");
            System.out.println(nombreUma + " | Energia: " + energia + " | Mood: " + nombreMood());
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
                    entrenar();
                    break;
                case 2:
                    Descanso();
                    break;
                case 3:
                    Recreation();
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

        public static String nombreMood() {
            if (mood < 20) return "Awful";
            if (mood < 40) return "Bad";
            if (mood < 60) return "Normal";
            if (mood < 80) return "Good";
            return "Great";
        }

        public static double multiplicadorMood() {
            if (mood < 20) return 0.90; 
            if (mood < 40) return 0.95; 
            if (mood < 60) return 1.00; 
            if (mood < 80) return 1.02; 
            return 1.04;
        }

        public static void Recreation() {
            int recreacion = new Random().nextInt(1, 4);
            int posibilidadSubStats =  new Random().nextInt(1, 100);

            switch (recreacion) {
                case 1:
                    System.out.println("Paseo al parque");
                    mood += mood + 20;
                    System.out.println("El mood a subido a " + multiplicadorMood());
                    break;
                case 2:
                    System.out.println("Salida al Karaoke");
                    mood += mood + 25;
                    System.out.println("El mood a subido a " + multiplicadorMood());
                    if (posibilidadSubStats < 50) {
                        energia += energia + 10;
                        System.out.println("Energia + 10");
                    }
                    break;
                case 3:
                    System.out.println("Visita a la pastelería");
                    mood += mood + 30;
                    System.out.println("El mood a subido a " + multiplicadorMood());

                    if (posibilidadSubStats < 50) {
                        energia += energia + 20;
                        System.out.println("Energia + 20");
                    }

                    break;
                case 4:
                    System.out.println("Evento de recreación");
                    mood += mood + 50;
                    energia += energia + 30;
                    System.out.println("El mood a subido a " + multiplicadorMood());
                    System.out.println("Energia + 30");
                    break;
            }
        }

        public static void Descanso() {

            int suerteDescanso = new Random().nextInt(1, 101);

            if (suerteDescanso <= 15) { 
                nightOwl = true;
                System.out.println("Has tenido un mal descanso. (Estado: Night Owl)");

            } else if (suerteDescanso <= 40) { 
                energia += 25;
                System.out.println("Descanso ligero. Energía actual: " + energia);

            } else if (suerteDescanso <= 85) { 
                energia += 45;
                System.out.println("Has descansado bien. Energía actual: " + energia);

            } else { 
                energia += 70;
                System.out.println("¡Descanso perfecto! Energía actual: " + energia);
            }

        }
    }



