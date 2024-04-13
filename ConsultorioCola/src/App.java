import java.util.Scanner;
import cola.Cola;


public class App {
    static Scanner scanner = new Scanner(System.in);
    static Cola colaPacientes;

    public static void main(String[] args) {
        int opcion;
        int maxPacientesPorDia = 10;
        colaPacientes = new Cola(maxPacientesPorDia);
        
        do {
            opcion = menu();
            switch (opcion) {
                case 1:
                    agendarCita();
                    break;
                case 2:
                    siguientePaciente();
                    break;
                case 3:
                    imprimirListaEspera();
                    break;
            }
        } while (opcion != 4);
    }

    public static void agendarCita() {
        clearScreen();
        System.out.println("Agendar cita para paciente");
        System.out.println("Nombre del paciente:");
        String nombre = scanner.next();
        System.out.println("Día de la cita (lunes/martes/miercoles):");
        String dia = scanner.next();
        colaPacientes.agregar(nombre, dia); 
        System.out.println("Cita agendada con éxito.");
        pressEnterToContinue();
    }

    public static void siguientePaciente() {
        clearScreen();
        System.out.println("Siguiente paciente en la cola:");
        String siguiente = colaPacientes.retirar();
        if (siguiente != null) {
            System.out.println("Puede pasar " + siguiente + ". Por favor, prepárese.");
        } else {
            System.out.println("No hay más pacientes en espera.");
        }
        pressEnterToContinue();
    }

    public static void imprimirListaEspera() {
        clearScreen();
        System.out.println("Lista de pacientes en espera:");
        colaPacientes.imprimir();
        pressEnterToContinue();
    }

    public static int menu() {
        int opcion;
        do {
            clearScreen();
            System.out.println("Sistema de Gestión de Pacientes");
            System.out.println("1. Agendar cita");
            System.out.println("2. Siguiente paciente");
            System.out.println("3. Imprimir lista de espera");
            System.out.println("4. Salir");
            System.out.println("Opción: ");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 4);
        return opcion;
    }

    public static void pressEnterToContinue() {
        System.out.println("Presione Enter para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
