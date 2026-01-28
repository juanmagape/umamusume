import java.util.Random;
import java.util.Scanner;

public class tests {
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
            int eventoRandom = new Random().nextInt(1, 101);
            if (eventoRandom >= 66) evento();

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

            if (mood < 0) {
                mood = 0;
            }
            if (mood > 100) {
                mood = 100;
            }

            if (energia < 0) {
                energia = 0;
            }
            if (energia > 100) {
                energia = 100;
            }


            System.out.println("Turno actual: " + contadorTurnos);

            if (contadorTurnos < 10) {
                int falta = 10 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para el Debut Race");
                continue;
            } else if (contadorTurnos == 10) {
                System.out.println("¡HOY ES EL DEBUT RACE!");
                carrera("Debut Race", 110, 100, 100, 90, 90); // Objetivos: speed, stamina, power, guts, wit
                continue;
            } else if (contadorTurnos < 15) {
                int falta = 15 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la Junior Cup");
                continue;
            } else if (contadorTurnos == 15) {
                System.out.println("¡HOY ES LA JUNIOR CUP!");
                carrera("Junior Cup", 140, 130, 130, 120, 110);
                continue;
            } else if (contadorTurnos < 30) {
                int falta = 30 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la Classic Race");
                continue;
            } else if (contadorTurnos == 30) {
                System.out.println("¡HOY ES LA CLASSIC RACE!");
                carrera("Classic Race", 180, 170, 170, 160, 150);
                continue;
            } else if (contadorTurnos < 48) {
                int falta = 48 - contadorTurnos;
                System.out.println("Faltan " + falta + " turnos para la URA Finals.");
            } else if (contadorTurnos == 48) {
                System.out.println("¡HOY ES LA URA FINALE!");
                carrera("URA Finale", 220, 210, 210, 200, 190);
                continue;

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
                    if (!practicePoor && !nightOwl) {
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
            System.out.println("=== Support " + (i + 1) + "/6 ===");
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

        System.out.println("Supports listos!\n");
    }

    public static void verSupports() {
        System.out.println("\n=== TUS SUPPORTS ===");

        for (int i = 0; i < 6; i++) {
            String estado = "";
            if (supportFriendship[i] >= 80) {
                estado = " FT DISPONIBLE ";
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
                extra += " =FT=";
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
                    case 0:
                        speed += statGanado;
                        break;
                    case 1:
                        stamina += statGanado;
                        break;
                    case 2:
                        power += statGanado;
                        break;
                    case 3:
                        guts += statGanado;
                        break;
                    case 4:
                        wit += statGanado;
                        break;
                }

                if (supportsEnEntrenamiento[indice] > 0) {
                    System.out.println();
                    for (int s = 0; s < 6; s++) {
                        int ganancia = rand.nextInt(3, 8);

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
                            System.out.println("FRIENDSHIP TRAINING! ");
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

            int suerteNightOwl = new Random().nextInt(1, 101);

            if (suerteNightOwl < 20) {
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
            nightOwl = false;
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
        } else if (mood < 40) {
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

        int porcentajeVictoria = 50;

        if (speedCarrera >= speedObj) porcentajeVictoria += 10;
        if (staminaCarrera >= staminaObj) porcentajeVictoria += 10;
        if (powerCarrera >= powerObj) porcentajeVictoria += 10;
        if (gutsCarrera >= gutsObj) porcentajeVictoria += 10;
        if (witCarrera >= witObj) porcentajeVictoria += 10;

        if (speedCarrera < speedObj * 0.8) porcentajeVictoria -= 10;
        if (staminaCarrera < staminaObj * 0.8) porcentajeVictoria -= 10;
        if (powerCarrera < powerObj * 0.8) porcentajeVictoria -= 10;
        if (gutsCarrera < gutsObj * 0.8) porcentajeVictoria -= 10;
        if (witCarrera < witObj * 0.8) porcentajeVictoria -= 10;

        System.out.println("\nProbabilidad de victoria: " + porcentajeVictoria + "%");
        System.out.println("Corriendo la carrera...\n");

        Thread.sleep(1500);

        Random rand = new Random();
        int resultado = rand.nextInt(100);

        if (resultado < porcentajeVictoria) {
            System.out.println("VICTORIA!");
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
        } System.exit(0);

        System.out.println("========================================\n");
    }

    static void evento() {
        System.out.println("\n========================================");
        System.out.println("          EVENTO ALEATORIO");
        System.out.println("========================================");

        int opc = new Random().nextInt(1, 11);
        int accion = 0;

        switch (opc) {
            case 1:
                System.out.println("Evento: A Grown-Up Present");
                System.out.println("Te han regalado unos zapatos de tacón muy elegantes, pero no son para correr.");
                System.out.println(nombreUma + " te pregunta qué debería hacer con ellos.");
                System.out.println("1 - Úsalos para relajarte y salir de compras.");
                System.out.println("2 - Estudia cómo están fabricados para mejorar tu técnica.");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    energia += 20;
                    mood += 20;
                    System.out.println("Qué día tan relajante! Se siente renovada.");
                } else {
                    speed += 10;
                    wit += 20;
                    System.out.println("Ha aprendido mucho sobre equilibrio y postura!");
                }
                break;

            case 2:
                System.out.println("Evento: Dashing through the Snow");
                System.out.println("¡Ha empezado a nevar fuertemente!");
                System.out.println(nombreUma + " mira la nieve con emoción.");
                System.out.println("1 - Vamos a correr sobre la nieve espesa!");
                System.out.println("2 - Hace frío, mejor nos quedamos dentro con un chocolate.");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    speed += 10;
                    power += 5;
                    System.out.println("El entrenamiento en la nieve fue duro, ¡pero efectivo!");
                } else if (accion == 2) {
                    energia += 30;
                    System.out.println("El cuerpo ha entrado en calor y ha descansado.");
                }
                break;

            case 3:
                System.out.println("Evento: Everyone's " + nombreUma);
                System.out.println("Un grupo de fans ha venido a animar durante el entrenamiento.");
                System.out.println("1 - Enséñales tu velocidad máxima!");
                System.out.println("2 - Demuestra tu fuerza y resistencia!");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    speed += 10;
                    System.out.println("Los fans quedaron impresionados por su velocidad!");
                } else if (accion == 2) {
                    power += 10;
                    guts += 5;
                    System.out.println("Los fans aplauden su tenacidad!");
                }
                break;

            case 4:
                System.out.println("Evento: High Level Rival");
                System.out.println("Te has encontrado con una rival muy fuerte en la pista.");
                System.out.println("Parece que quiere desafiarte.");
                System.out.println("1 - Intentar ganarle en un sprint largo.");
                System.out.println("2 - Competir cuerpo a cuerpo.");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    speed += 5;
                    stamina += 5;
                    System.out.println("Fue una carrera reñida, buen ejercicio cardiovascular.");
                } else if (accion == 2) {
                    power += 10;
                    guts += 5;
                    System.out.println("El choque de hombros fue intenso. ¡Más potencia!");
                }
                break;

            case 5:
                System.out.println("Evento: " + nombreUma + " Makes a Resolution");
                System.out.println(nombreUma + " está pensando en sus objetivos para este año.");
                System.out.println("1 - Centrarse en la táctica y la técnica.");
                System.out.println("2 - Centrarse en el aguante y el coraje.");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    speed += 5;
                    wit += 5;
                    System.out.println("Ha estudiado nuevas rutas de carrera.");
                } else if (accion == 2) {
                    stamina += 5;
                    guts += 5;
                    System.out.println("Siente el fuego en su interior.");
                }
                break;

            case 6:
                System.out.println("Evento: " + nombreUma + " Matures");
                System.out.println("Tienes un rato libre antes de entrenar.");
                System.out.println("1 - Leer libros de estrategia.");
                System.out.println("2 - Correr una maratón ligera.)");
                System.out.println("3 - Levantamiento de pesas.");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    wit += 10;
                    System.out.println("Su inteligencia en carrera ha mejorado.");
                } else if (accion == 2) {
                    stamina += 10;
                    System.out.println("Sus pulmones están más fuertes.");
                } else if (accion == 3) {
                    power += 10;
                    System.out.println("Sus músculos se sienten más potentes.");
                }
                break;

            case 7:
                System.out.println("Evento: " + nombreUma + " Perseveres");
                System.out.println("El entrenamiento está siendo brutal hoy. " + nombreUma + " quiere parar.");
                System.out.println("1 - ¡No te rindas! ¡Saca tu coraje!");
                System.out.println("2 - ¡Empuja con fuerza bruta!");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    guts += 10;
                    System.out.println("Ha superado sus límites mentales.");
                } else if (accion == 2) {
                    power += 10;
                    System.out.println("Ha superado sus límites físicos.");
                }
                break;

            case 8:
                System.out.println("Evento: Bottomless Pit");
                System.out.println(nombreUma + " tiene mucha hambre después de entrenar.");
                System.out.println("1 - Comer una ensalada ligera.");
                System.out.println("2 - Comer su postre favorito.");
                System.out.println("3 - COMERSE TODO EL BUFFET.");
                System.out.print("Elige una opción: ");
                int random = new Random().nextInt(1, 3);
                accion = print.nextInt();
                if (accion == 1){
                    energia += 10;
                    System.out.println("Comida sana. Recupera un poco de energía.");
                } else if (accion == 2){
                    mood += 10;
                    System.out.println("¡Delicioso! Se siente más feliz.");
                } else if (accion == 3){
                    if (random == 1){
                        energia += 30;
                        mood += 10;
                        System.out.println("¡Increíble! Ha comido como una bestia y se siente genial.");
                    } else {
                        energia += 30;
                        speed -= 5;
                        practicePoor = true;
                        power += 5;
                        System.out.println("Oh no... ha comido demasiado. Le duele el estómago (Practice Poor).");
                    }
                }
                break;

            case 9:
                System.out.println("Evento: New Year's Resolution");
                System.out.println("Feliz año nuevo! ¿Cómo pasamos las vacaciones?");
                System.out.println("1 - Descansar totalmente.");
                System.out.println("2 - Entrenamiento ligero especial.");
                System.out.print("Elige una opción: ");
                accion = print.nextInt();
                if (accion == 1) {
                    energia += 30;
                    System.out.println("Ha dormido muchísimo. Baterías recargadas.");
                } else if (accion == 2) {
                    wit += 5;
                    speed += 5;
                    stamina += 5;
                    power += 5;
                    guts += 5;
                    System.out.println("Un poco de todo para empezar bien el año.");
                }
                break;

            case 10:
                System.out.println("Evento: " + nombreUma + "'s Gluttony Championship");
                System.out.println("¡Un concurso de comer Ramen!");
                System.out.println("1 - Intentar ganar (Riesgo medio).");
                System.out.println("2 - Participar por diversión (Seguro).");
                System.out.println("3 - IR A POR EL RÉCORD MUNDIAL!");
                System.out.print("Elige una opción: ");
                int random1 = new Random().nextInt(1, 101);
                accion = print.nextInt();
                if (accion == 1) {
                    if (random1 <= 70) {
                        energia += 40;
                        power += 10;
                        System.out.println("¡Victoria! Estaba delicioso.");
                    } else {
                        energia += 20;
                        speed -= 5;
                        practicePoor = true;
                        System.out.println("Comió demasiado rápido... se marea.");
                    }
                } else if (accion == 2) {
                    energia += 10;
                    power += 5;
                    System.out.println("Comió tranquila y disfrutó el caldo.");
                } else if (accion == 3) {
                    if (random1 <= 30) {
                        energia += 100;
                        power += 20;
                        System.out.println("¡DIOS MÍO! ¡Es un agujero negro! Energía al máximo.");
                    } else {
                        energia += 200;
                        speed -= 20;
                        power += 20;
                        practicePoor = true;
                        System.out.println("Ha explotado. Literalmente no puede moverse. Ha ganado peso (Speed down).");
                    }
                }
                break;
        }
        System.out.println("========================================\n");
    }
}