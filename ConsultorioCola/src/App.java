/*
    Arce Llamas Jesus Irvin
    Higuera Sanchez Dulce Mariela
    Reyes Contreras Ramses
    Rodriguez Cacho Ximena Charleene 
    ---------------------------------
    4/14/2024
    TSU-DSM-4A 
    Practica #10 - Colas
*/

import java.util.Scanner;
import cola.Cola;

public class App {
    static Scanner read = new Scanner(System.in);
    static Cola colaPacientes;

    public static void main(String[] args) {
        int opcion;
        colaPacientes = new Cola();

        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    agendarCita();
                    break;
                case 2:
                    citasMedicas();
                    break;
            }
        } while (opcion != 3);
    }

    public static void agendarCita() {
        clear();
        System.out.println("\t.------------------------------------------.");
        System.out.println("\t|       Agendar cita para paciente         |");
        System.out.println("\t'------------------------------------------'");
        System.out.println(".-------------------------------------------------------------.");
        System.out.println("| >>> Por Favor, ingrese el Día que desea Pedir su Cita: <<<  |" );
        System.out.println("'-------------------------------------------------------------'");
        System.out.println("\n1-Lunes");
        System.out.println("2-Martes");
        System.out.println("3-Miércoles");
    
        int dia;
        while (true) {
            System.out.print("Número del día: ");
            if (read.hasNextInt()) {
                dia = read.nextInt();
                if (dia < 1 || dia > 3) {
                    System.out.println("Número de día inválido. Por favor, elige 1, 2 o 3.");
                    continue;
                } else {
                    break;
                }
            } else {
                System.out.println("Error: Debe ingresar un número válido para el día.");
                read.next(); 
                continue;
            }
        }
    
        System.out.println("\nNombre del paciente:");
        String nombre = read.next();
        System.out.println("\nEdad del paciente:");
        int edad;
        while (true) {
            if (read.hasNextInt()) {
                edad = read.nextInt();
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido para la edad.");
                read.next(); 
                continue;
            }
        }
    
        colaPacientes.agregar(nombre, edad, dia);
    
        String respuesta;
        do {
            System.out.println("\n¿Deseas agendar otra cita? (s/n)");
            respuesta = read.next().toLowerCase(); // Convertir la respuesta a minúsculas
            if (!respuesta.equals("s") && !respuesta.equals("n")) {
                System.out.println("Respuesta inválida. Por favor, responde con 's' o 'n'.");
            }
        } while (!respuesta.equals("s") && !respuesta.equals("n"));
    
        if (respuesta.equals("s")) {
            agendarCita(); 
        } else if (respuesta.equals("n")) {
            Enter();
        }
    }
    

    public static void citasMedicas() {
        clear();
        System.out.println("\t.------------------------------------------.");
        System.out.println("\t|              Citas médicas               |");
        System.out.println("\t'------------------------------------------'");
        System.out.println(".------------------------------------------------------------.");
        System.out.println("|      >>> A continuación, ingrese el Día de la cita: <<<    |");
        System.out.println("'------------------------------------------------------------'");
        System.out.println("\n1-Lunes");
        System.out.println("2-Martes");
        System.out.println("3-Miércoles");
        System.out.println("4-Regresar");
        System.out.print("Opción: ");
    
        int dia;
        while (true) {
            if (read.hasNextInt()) {
                dia = read.nextInt();
                if (dia < 1 || dia > 4) {
                    System.out.println("Error: Por favor, ingrese una opción válida (1-4).");
                    System.out.print("Opción: ");
                    continue;
                }
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido.");
                System.out.print("Opción: ");
                read.next(); // Limpiar el buffer de entrada
                continue;
            }
        }
    
        clear();
        switch (dia) {
            case 1:
                System.out.println("\t.------------------------------------------.");
                System.out.println("\t|        Citas médicas del día: Lunes      |");
                System.out.println("\t'------------------------------------------'");
                System.out.println("");
                break;
            case 2:
                System.out.println("\t.------------------------------------------.");
                System.out.println("\t|        Citas médicas del día: Martes     |");
                System.out.println("\t'------------------------------------------'");
                System.out.println("");
                break;
            case 3:
                System.out.println("\t.------------------------------------------.");
                System.out.println("\t|        Citas médicas del día: Miercoles  |");
                System.out.println("\t'------------------------------------------'");
                System.out.println("");
                break;
            case 4:
                return;
        }
    
        while (true) {
            String pacienteEnConsulta = colaPacientes.verPrimero(dia);
            String siguientePacienteEnEspera = colaPacientes.verSiguiente(dia);
            System.out.println("Pacientes:");
            colaPacientes.imprimir(dia, pacienteEnConsulta, siguientePacienteEnEspera);
            if (pacienteEnConsulta != null) {
                System.out.println("\n\t\tActualmente atendiendo a: " + pacienteEnConsulta);
            } else {
                System.out.println("\n\t\tNo hay pacientes siendo atendidos actualmente.");
            }
    
            if (siguientePacienteEnEspera != null) {
                int edadSiguientePaciente = colaPacientes.obtenerEdadSiguiente(dia);
                System.out.println("\n\t\tSiguiente paciente en espera: " + siguientePacienteEnEspera + " (Edad: " + edadSiguientePaciente + ")");
            } else {
                if (pacienteEnConsulta == null) {
                    System.out.println("\n\t\tNo hay pacientes agendados para este día.");
                } else {
                    System.out.println("\n\t\tNo hay más pacientes en espera.");
                }
            }
    
            System.out.println("\nPresione N para pasar al siguiente paciente");
            System.out.println("\nPresione R para regresar al menú de citas médicas");
    
            String opcion = read.next().toUpperCase();
            switch (opcion) {
                case "N":
                    colaPacientes.pasarSiguiente(dia);
                    pacienteEnConsulta = colaPacientes.verPrimero(dia); 
                    siguientePacienteEnEspera = colaPacientes.verSiguiente(dia);
                    clear();
                    break;
                case "R":
                    citasMedicas();
                    return;
                default:
                    clear();
                    System.out.println("\t.-----------------------.");
                    System.out.println("\t|    Opcion Invalida    |");
                    System.out.println("\t'-----------------------'");
                    break;
            }
        }
    }
    

    public static int menu() {
        int opcion;
        do {
            clear();
            System.out.println("\t.------------------------------------------.");
            System.out.println("\t|        Sistema de Gestión de Pacientes   |");
            System.out.println("\t'------------------------------------------'");
            System.out.println(".------------------------------------------------------------.");
            System.out.println("|             >>> Bienvenid@, elige una opción: <<<          |");
            System.out.println("'------------------------------------------------------------'");
            System.out.println("\n1. Agendar cita");
            System.out.println("2. Citas médicas");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
    
            if (read.hasNextInt()) {
                opcion = read.nextInt();
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Error: Por favor, ingresa una opción válida (1-3).");
                    Enter();
                    continue;
                }
                break;
            } else {
                System.out.println("Error: Debes ingresar un número válido.");
                read.next(); 
                Enter(); 
                continue;
            }
        } while (true);
        return opcion;
    }

    public static void Enter() {
        Scanner continuar = new Scanner(System.in);
        continuar.useDelimiter("\n");
        System.out.println("-------------------------------------------------------------");
        System.out.print("Presione ENTER para continuar... ");
        continuar.next();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
