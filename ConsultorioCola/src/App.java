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
        System.out.println("Agendar cita para paciente");
        System.out.println("Día de la cita:");
        System.out.println("1-Lunes");
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
    
        System.out.println("Nombre del paciente:");
        String nombre = read.next();
        System.out.println("Edad del paciente:");
        int edad;
        while (true) {
            System.out.print("Edad: ");
            if (read.hasNextInt()) {
                edad = read.nextInt();
                break;
            } else {
                System.out.println("Error: Debe ingresar un número válido para la edad.");
                read.next(); 
                continue;
            }
        }
    
        colaPacientes.agregar(nombre, dia);
    
        String respuesta;
        do {
            System.out.println("¿Deseas agendar otra cita? (s/n)");
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
        System.out.println("Citas médicas");
        System.out.println("1-Lunes");
        System.out.println("2-Martes");
        System.out.println("3-Miércoles");
        System.out.println("4-Regresar");
        System.out.print("Opción: ");
        int dia = read.nextInt();
    
        clear();
        switch (dia) {
            case 1:
                System.out.println("Citas médicas del día: Lunes");
                break;
            case 2:
                System.out.println("Citas médicas del día: Martes");
                break;
            case 3:
                System.out.println("Citas médicas del día: Miércoles");
                break;
            case 4:
                return;
            default:
                System.out.println("Opción inválida");
                return;
        }
    
        String pacienteEnConsulta = colaPacientes.verPrimero(dia);
        String siguientePacienteEnEspera = colaPacientes.verSiguiente(dia);
        System.out.println("Pacientes:");
        colaPacientes.imprimir(dia, pacienteEnConsulta, siguientePacienteEnEspera);
        while (true) {
            if (pacienteEnConsulta != null) {
                System.out.println("\n\t\tActualmente atendiendo a: " + pacienteEnConsulta);
                if (siguientePacienteEnEspera != null) {
                    System.out.println("\n\t\tSiguiente paciente en espera: " + siguientePacienteEnEspera);
                } else {
                    System.out.println("\n\t\tNo hay más pacientes en espera.");
                }
            } else {
                System.out.println("\n\t\tNo hay pacientes agendados para este día.");
            }
    
            System.out.println("\nPresione N para pasar al siguiente paciente");
            System.out.println("\nPresione R para regresar al menú de citas médicas");
    
            String opcion = read.next().toUpperCase();
            switch (opcion) {
                case "N":
                    colaPacientes.pasarSiguiente(dia);
                    clear();
                    break; 
                case "R":
                    citasMedicas();
                    return; 
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        }
    }
    

    public static int menu() {
        int opcion;
        do {
            clear();
            System.out.println("Sistema de Gestión de Pacientes");
            System.out.println("1. Agendar cita");
            System.out.println("2. Citas médicas");
            System.out.println("3. Salir");
            System.out.println("Opción: ");
            opcion = read.nextInt();
        } while (opcion < 1 || opcion > 4);
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
