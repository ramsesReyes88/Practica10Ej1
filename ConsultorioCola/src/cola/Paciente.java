package cola;

public class Paciente {
    private String nombre;
    private int edad;
    private int dia;

    public Paciente(String nombre, int edad, int dia) {
        this.nombre = nombre;
        this.edad = edad;
        this.dia = dia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDiaCita() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}