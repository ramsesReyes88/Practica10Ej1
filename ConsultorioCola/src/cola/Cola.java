package cola;

public class Cola {
    private int maxSize;
    private int frente;
    private int fin;
    private int numElementos;
    private Paciente[] pacientes;

    public Cola(int maxSize) {
        this.maxSize = maxSize;
        pacientes = new Paciente[maxSize];
        frente = 0;
        fin = -1;
        numElementos = 0;
    }

    public boolean estaLlena() {
        return numElementos == maxSize;
    }

    public boolean estaVacia() {
        return numElementos == 0;
    }

    public void agregar(String nombre, String dia) {
        if (!estaLlena()) {
            fin = (fin + 1) % maxSize; // Avanzar fin circularmente
            pacientes[fin] = new Paciente(nombre, dia);
            numElementos++;
        } else {
            System.out.println("La cola está llena. No se pueden agregar más pacientes.");
        }
    }
    

    public String retirar() {
        if (!estaVacia()) {
            Paciente pacienteAtendido = pacientes[frente];
            frente++;
            if (frente == maxSize) {
                frente = 0;
            }
            numElementos--;
            return pacienteAtendido.getNombre();
        } else {
            System.out.println("La cola está vacía. No hay pacientes para atender.");
            return null;
        }
    }
    

    public void imprimir() {
        if (!estaVacia()) {
            int actual = frente;
            int posicion = 1;
            System.out.println("Lista de pacientes en espera:");
            while (posicion <= numElementos) {
                System.out.println(posicion + ". Nombre: " + pacientes[actual].getNombre() + ", Día de la cita: " + pacientes[actual].getDia());
                actual = (actual + 1) % maxSize;
                posicion++;
            }
        } else {
            System.out.println("La cola está vacía. No hay pacientes en espera.");
        }
    }

    public void verPrimero() {
        if (!estaVacia()) {
            System.out.println("Siguiente paciente en la cola:");
            System.out.println("Nombre: " + pacientes[frente].getNombre() + ", Día de la cita: " + pacientes[frente].getDia());
        } else {
            System.out.println("La cola está vacía. No hay pacientes en espera.");
        }
    }
}
