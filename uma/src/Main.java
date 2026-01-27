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
    public static int seleccionUma = new Random().nextInt(0, 12);
    public static String nombreUma = umas[seleccionUma];

    public static String[] supportNombres = new String[6];
    public static int[] supportTipos = new int[6];
    public static int[] supportFriendship = new int[6];

    public static void main(String[] args) throws InterruptedException {

        inicializarSupports();

        boolean seguirJugando = true;
        int contadorTurnos = 0;

        while (seguirJugando) {
            contadorTurnos++;

            if (nightOwl) {
                int nightRandom = new Random().nextInt(1, 101);

                if (nightRandom > 50) {
                    speed -= (int) (speed * 0.10);
                    stamina -= (int) (stamina * 0.10);
                    power -= (int) (power * 0.10);
                    guts -= (int) (guts * 0.10);
                    wit -= (int) (wit * 0.10);
                }
            }

            if (mood < 0) { mood = 0; }
            if (mood > 100) { mood = 100; }

            if (energia < 0) { energia = 0; }
            if (energia > 100) { energia = 100; }


            System.out.println("Turno actual: " + contadorTurnos);

            if (contadorTurnos < 10) {
                int falta = 10 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para el Debut Race");

            } else if (contadorTurnos == 10) {
                System.out.println("¡HOY ES EL DEBUT RACE!");
                carrera("Debut Race", 110, 100, 100, 90, 90); 

            } else if (contadorTurnos < 15) {
                int falta = 15 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la Junior Cup");

            } else if (contadorTurnos == 15) {
                System.out.println("¡HOY ES LA JUNIOR CUP!");
                carrera("Junior Cup", 140, 130, 130, 120, 110);

            } else if (contadorTurnos < 30) {
                int falta = 30 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la Classic Race");

            } else if (contadorTurnos == 30) {
                System.out.println("¡HOY ES LA CLASSIC RACE!");
                carrera("Classic Race", 180, 170, 170, 160, 150);

            } else if (contadorTurnos < 48) {
                int falta = 48 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la URA Finals.");
            } else if (contadorTurnos == 48) {
                System.out.println("¡HOY ES LA URA FINALE!");
                carrera("URA Finale", 220, 210, 210, 200, 190);

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
            System.out.println("[4] - Curarse");
            System.out.println("[5] - Ver Supports");
            System.out.println("[6] - Salir");
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
                    if (!practicePoor  && !nightOwl) {
                        System.out.println("No necesitas ir a la enfermería.");
                        break;
                    }
                    curarse();
                    break;
                case 5:
                    verSupports();
                    break;
                case 6:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
            }
        }
    }

    public static void inicializarSupports() {
        System.out.println("\n=== SELECCIÓN DE SUPPORTS ===");
        System.out.println("Selecciona 6 support cards\n");

        String[] tiposNombres = {"Speed", "Stamina", "Power", "Guts", "Wit"};

        for (int i = 0; i < 6; i++) {
            System.out.println("--- Support " + (i + 1) + "/6 ---");
            System.out.println("1. Speed | 2. Stamina | 3. Power | 4. Guts | 5. Wit");
            System.out.print("Elige tipo: ");

            int tipo = print.nextInt() - 1;
            if (tipo < 0 || tipo > 4) {
                tipo = 0;
            }

            supportNombres[i] = "Support " + (i + 1) + " (" + tiposNombres[tipo] + ")";
            supportTipos[i] = tipo;
            supportFriendship[i] = 0;

            System.out.println(supportNombres[i] + " añadido\n");
        }

        System.out.println("¡Supports listos!\n");
    }

    public static void verSupports() {
        System.out.println("\n=== TUS SUPPORTS ===");

        for (int i = 0; i < 6; i++) {
            String estado = "";
            if (supportFriendship[i] >= 80) {
                estado = " ★ FT DISPONIBLE ★";
            }
            System.out.println((i + 1) + ". " + supportNombres[i] +
                    " | Friendship: " + supportFriendship[i] + "/100" + estado);
        }

        System.out.println("====================\n");
    }

    public static void entrenar() {
        Random rand = new Random();

        int baseFailRate = 0;
        if (energia < 50) {
            baseFailRate = (50 - energia) * 2;
        }

        if (practicePoor) {
            baseFailRate += 15;
            System.out.println("[!] Tienes Practice Poor: El riesgo de fallo ha aumentado.");
        }

        if (practicePerfect) {
            baseFailRate -= 5;
            if (baseFailRate < 0) baseFailRate = 0;
            System.out.println("[!] Tienes Practice Perfect: El riesgo ha disminuido y ganas más stats.");
        }

        int[] ganancias = new int[5];
        int[] perdidasEnergia = new int[5];
        int[] porcentajeFallo = new int[5];
        int[] supportsEnEntrenamiento = new int[5];
        boolean[] tieneFT = new boolean[5];

        String[] nombresStats = {"Speed", "Stamina", "Power", "Guts", "Wit"};

        for (int tipo = 0; tipo < 5; tipo++) {
            supportsEnEntrenamiento[tipo] = 0;
            tieneFT[tipo] = false;

            for (int s = 0; s < 6; s++) {
                int probabilidad = 40;

                if (supportTipos[s] == tipo) {
                    probabilidad = 60;
                }

                if (supportFriendship[s] >= 80 && supportTipos[s] == tipo) {
                    probabilidad = 85;
                    tieneFT[tipo] = true;
                }

                if (rand.nextInt(100) < probabilidad) {
                    supportsEnEntrenamiento[tipo]++;
                }
            }
        }

        // Calcular stats para cada entrenamiento
        for (int i = 0; i < 5; i++) {
            int statBase = randomEntrenar();
            int energiaBase = randomEnergiaPerder();

            if (practicePerfect) {
                statBase += 5;
            }

            statBase += supportsEnEntrenamiento[i] * 3;

            if (tieneFT[i]) {
                statBase += 15;
                energiaBase += 5;
            }

            ganancias[i] = statBase;

            if (i == 4) {
                porcentajeFallo[i] = Math.max(0, baseFailRate / 4);
                ganancias[i] = ganancias[i] / 2;
                perdidasEnergia[i] = 0;
            } else {
                porcentajeFallo[i] = baseFailRate;
                perdidasEnergia[i] = energiaBase;
            }
        }

        System.out.println("\n=== Entrenar (Energía: " + energia + " | Mood: " + mood + ") ===");
        for (int i = 0; i < 5; i++) {
            String extra = "";

            if (supportsEnEntrenamiento[i] > 0) {
                extra += " [" + supportsEnEntrenamiento[i] + " supports]";
            }

            if (tieneFT[i]) {
                extra += " ★FT★";
            }

            System.out.println((i + 1) + ". " + nombresStats[i] + " | +" + ganancias[i] + " stat | -" + perdidasEnergia[i] + " Ene | " + "Riesgo: " + porcentajeFallo[i] + "%" + extra);
        }

        System.out.print("\nElige (1-5): ");
        int opcion = print.nextInt();
        int indice = opcion - 1;

        if (indice >= 0 && indice < 5) {

            int suerte = rand.nextInt(100);

            if (suerte < porcentajeFallo[indice]) {
                System.out.println("¡Fallo! " + nombresStats[indice] + " no subió.");
                falloEntrenar();
            } else {
                int statGanado = ganancias[indice];
                int energiaPerdida = perdidasEnergia[indice];

                System.out.println("¡Éxito! +" + statGanado + " " + nombresStats[indice]);

                if (energiaPerdida > 0) {
                    System.out.println("-" + energiaPerdida + " energía");
                    energia -= energiaPerdida;
                } else {
                    System.out.println("+5 energía (Wit)");
                    energia += 5;
                }

                switch (indice) {
                    case 0: speed += statGanado; break;
                    case 1: stamina += statGanado; break;
                    case 2: power += statGanado; break;
                    case 3: guts += statGanado; break;
                    case 4: wit += statGanado; break;
                }

                if (supportsEnEntrenamiento[indice] > 0) {
                    System.out.println();
                    for (int s = 0; s < 6; s++) {
                        int ganancia = rand.nextInt(3, 8); // 3-7 base

                        if (supportTipos[s] == indice) {
                            ganancia += 3;
                        }

                        int antes = supportFriendship[s];
                        supportFriendship[s] += ganancia;

                        if (supportFriendship[s] > 100) {
                            supportFriendship[s] = 100;
                        }

                        System.out.println("→ " + supportNombres[s] + " +" + ganancia +
                                " friendship (" + supportFriendship[s] + "/100)");

                        if (supportFriendship[s] >= 80 && antes < 80) {
                            System.out.println("   ★★★ ¡FRIENDSHIP TRAINING! ★★★");
                        }
                    }
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

    public static void falloEntrenar() {

        System.out.println("=== Enfermería ===");
        System.out.println("1. Descansa hasta que te encuentres mejor");
        System.out.println("2. Esto no es nada, sigamos entrenando!");
        System.out.println("3. Ver efectos");
        int opc = print.nextInt();

        switch (opc) {
            case 1:
                int accion = new Random().nextInt(2);
                if (accion == 1) {
                    mood = mood - 20;
                } else {
                    mood = mood - 20;
                    speed = speed - 5;
                    power = power - 5;
                    guts = guts - 5;
                    wit = wit - 5;
                    stamina = stamina - 5;
                }
                break;
            case 2:
                int accion2 = new Random().nextInt(3);
                if (accion2 == 1) {
                    mood = mood - 20;
                    speed = speed - 5;
                    power = power - 5;
                    guts = guts - 5;
                    wit = wit - 5;
                    stamina = stamina - 5;
                } else if (accion2 == 2) {
                    System.out.println("Has adquirido: Practice Poor");
                    mood = mood - 20;
                    speed = speed - 5;
                    power = power - 5;
                    guts = guts - 5;
                    wit = wit - 5;
                    stamina = stamina - 5;
                    practicePoor = true;
                } else {
                    System.out.println("Has adquirido: Practice Perfect");
                    practicePerfect = true;
                }
                break;
            case 3:
                System.out.println();
                falloEntrenar();
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
        int recreacion = new Random().nextInt(1, 5);
        int posibilidadSubStats = new Random().nextInt(1, 100);

        switch (recreacion) {
            case 1:
                System.out.println("Paseo al parque");
                mood = mood + 20;
                System.out.println("El mood ha subido a " + nombreMood());
                break;
            case 2:
                System.out.println("Salida al Karaoke");
                mood = mood + 25;
                System.out.println("El mood ha subido a " + nombreMood());
                if (posibilidadSubStats < 50) {
                    energia = energia + 10;
                    System.out.println("Energia +10");
                }
                break;
            case 3:
                System.out.println("Visita a la pastelería");
                mood = mood + 30;
                System.out.println("El mood ha subido a " + nombreMood());

                if (posibilidadSubStats < 50) {
                    energia = energia + 20;
                    System.out.println("Energia +20");
                }

                break;
            case 4:
                System.out.println("Evento de recreación");
                mood = mood + 50;
                energia = energia + 30;
                System.out.println("El mood ha subido a " + nombreMood());
                System.out.println("Energia +30");
                break;
        }
    }

    public static void Descanso() {

        int suerteDescanso = new Random().nextInt(1, 101);

        if (suerteDescanso <= 33) {
            System.out.println(nombreUma + " ha tenido un mal descanso...");
            energia += 25;
            System.out.println("Energía actual: " + energia);

            int suerteNightOwl = new Random().nextInt(1,101);

            if (suerteNightOwl < 20){
                System.out.println(nombreUma + "le ha dado insomnio.");
                nightOwl = true;
            }

        } else if (suerteDescanso <= 66) {
            energia += 45;
            System.out.println(nombreUma + " ha descansado bien. Energía actual: " + energia);

        } else {
            energia += 70;
            System.out.println("¡Descanso perfecto! Energía actual: " + energia);

        }

    }

    public static void curarse() {
        int suerte = new Random().nextInt(100);
        if (suerte < 80) {
            System.out.println(nombreUma + " se ha curado exitosamente!");
            practicePoor = false;
            energia += 30;
            System.out.println("Energia +30");
        } else {
            System.out.println(nombreUma + " no se ha curado.");
            energia += 10;
            System.out.println("Energia +10");
        }

    }
    public static void carrera(String nombreCarrera, int speedObj, int staminaObj, int powerObj, int gutsObj, int witObj) throws InterruptedException {
        System.out.println("\n========================================");
        System.out.println("       " + nombreCarrera);
        System.out.println("========================================");

        int speedCarrera = speed;
        int staminaCarrera = stamina;
        int powerCarrera = power;
        int gutsCarrera = guts;
        int witCarrera = wit;

        if (mood >= 80) {
            speedCarrera += 10;
            staminaCarrera += 10;
            powerCarrera += 10;
            gutsCarrera += 10;
            witCarrera += 10;
            System.out.println("[Mood Great: Todas las stats +10]");
        } else if (mood >= 60) {
            speedCarrera += 5;
            staminaCarrera += 5;
            powerCarrera += 5;
            gutsCarrera += 5;
            witCarrera += 5;
            System.out.println("[Mood Good: Todas las stats +5]");
        } else if (mood < 40) { // Bad
            speedCarrera -= 5;
            staminaCarrera -= 5;
            powerCarrera -= 5;
            gutsCarrera -= 5;
            witCarrera -= 5;
            System.out.println("[Mood Bad: Todas las stats -5]");
        } else if (mood < 20) {
            speedCarrera -= 10;
            staminaCarrera -= 10;
            powerCarrera -= 10;
            gutsCarrera -= 10;
            witCarrera -= 10;
            System.out.println("[Mood Awful: Todas las stats -10]");
        }

        int porcentajeVictoria = 100;

        if (speedCarrera < speedObj) {
            porcentajeVictoria -= 5;
        }
        if (staminaCarrera < staminaObj) {
            porcentajeVictoria -= 5;
        }
        if (powerCarrera < powerObj) {
            porcentajeVictoria -= 5;
        }
        if (gutsCarrera < gutsObj) {
            porcentajeVictoria -= 5;
        }
        if (witCarrera < witObj) {
            porcentajeVictoria -= 5;
        }

        System.out.println("\nProbabilidad de victoria: " + porcentajeVictoria + "%");
        System.out.println("Corriendo la carrera...\n");

        Thread.sleep(1500);

        Random rand = new Random();
        int resultado = rand.nextInt(100);

        if (resultado < porcentajeVictoria) {
            System.out.println("★★★ ¡VICTORIA! ★★★");
            System.out.println(nombreUma + " ha ganado la " + nombreCarrera + "!");
            mood += 20;
            energia += 15;
            System.out.println("\nRecompensas:");
            System.out.println("• Mood +20");
            System.out.println("• Energía +15");
        } else {
            System.out.println("Derrota...");
            System.out.println(nombreUma + " no logró ganar.");
            System.out.println(nombreUma + " no pudo cumplir su meta...");
            Thread.sleep(2000);
            System.out.println("\n============================");
            System.out.println("         RESULTADOS         ");
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
        }

        System.out.println("========================================\n");
    }
}