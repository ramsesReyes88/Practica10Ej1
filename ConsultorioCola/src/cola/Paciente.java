package cola;

public class Paciente {
    private String nombre;
    private int dia; // Cambio el tipo de día a int

    public Paciente(String nombre, int dia) {
        this.nombre = nombre;
        this.dia = dia;
    }

    // Mantén los métodos getNombre y getDia como están
    public String getNombre() {
        return nombre;
    }

    public int getDia() {
        return dia;
    }
}