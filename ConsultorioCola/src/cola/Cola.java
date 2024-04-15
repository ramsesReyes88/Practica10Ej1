package cola;

public class Cola {
    private final int numDias = 3; // Lunes, Martes, Miércoles
    private final int maxPacientesPorDia = 10; // Máximo de pacientes por día

    private Paciente[][] colas; // Un arreglo bidimensional para mantener las colas de pacientes

    public Cola() {
        colas = new Paciente[numDias][maxPacientesPorDia];
    }

    public boolean estaLlena(int dia) {
        return colas[dia - 1][maxPacientesPorDia - 1] != null;
    }

    public boolean estaVacia(int dia) {
        return colas[dia - 1][0] == null;
    }

    public void agregar(String nombre, int edad, int dia) {
        if (!estaLlena(dia)) {
            for (int i = 0; i < maxPacientesPorDia; i++) {
                if (colas[dia - 1][i] == null) {
                    colas[dia - 1][i] = new Paciente(nombre, edad, dia);
                    System.out.println("Cita agendada para el día " + obtenerNombreDia(dia) + ".");
                    return;
                }
            }
        }
        System.out.println("La cola para el día " + obtenerNombreDia(dia) + " está llena. No se pueden agregar más pacientes.");
    }

    public String retirar(int dia) {
        if (!estaVacia(dia)) {
            String nombrePaciente = colas[dia - 1][0].getNombre();
            for (int i = 0; i < maxPacientesPorDia - 1; i++) {
                colas[dia - 1][i] = colas[dia - 1][i + 1];
            }
            colas[dia - 1][maxPacientesPorDia - 1] = null; 
            return nombrePaciente;
        } else {
            return null;
        }
    }

    public void imprimir(int dia, String pacienteEnConsulta, String siguientePacienteEnEspera) {
        boolean hayPacientes = false;
        for (int i = 0; i < maxPacientesPorDia; i++) {
            if (colas[dia - 1][i] != null) {
                String nombrePaciente = colas[dia - 1][i].getNombre();
                if (!nombrePaciente.equals(pacienteEnConsulta) && !nombrePaciente.equals(siguientePacienteEnEspera)) {
                    System.out.println("Nombre: " + nombrePaciente);
                    hayPacientes = true;
                }
            }
        }
        if (!hayPacientes) {
            System.out.println("No hay pacientes en Lista de Espera.");
        }
    }
    

    public String verPrimero(int dia) {
        if (!estaVacia(dia)) {
            return colas[dia - 1][0].getNombre();
        } else {
            return null;
        }
    }

    public String verSiguiente(int dia) {
        if (!estaVacia(dia)) {
            for (int i = 1; i < maxPacientesPorDia; i++) {
                if (colas[dia - 1][i] != null) {
                    return colas[dia - 1][i].getNombre();
                }
            }
        }
        return null;
    }

    public int obtenerEdadSiguiente(int dia) {
        if (!estaVacia(dia)) {
            for (int i = 1; i < maxPacientesPorDia; i++) {
                if (colas[dia - 1][i] != null) {
                    return colas[dia - 1][i].getEdad();
                }
            }
        }
        return -1; 
    }

    

    public void pasarSiguiente(int dia) {
        if (!estaVacia(dia)) {
            // Mover todos los pacientes una posición hacia adelante en la cola
            for (int i = 0; i < maxPacientesPorDia - 1; i++) {
                colas[dia - 1][i] = colas[dia - 1][i + 1];
            }
            colas[dia - 1][maxPacientesPorDia - 1] = null; // Liberar la última posición
        } else {
            System.out.println("No hay pacientes en espera para este día.");
        }
    }
    

    public String obtenerNombreDia(int dia) {
        switch (dia) {
            case 1:
                return "Lunes";
            case 2:
                return "Martes";
            case 3:
                return "Miércoles";
            default:
                return "";
        }
    }
}
