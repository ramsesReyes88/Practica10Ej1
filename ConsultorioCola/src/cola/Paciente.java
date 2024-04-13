package cola;

public class Paciente {
  
    private String nombre;
    private String dia;


    public Paciente(String nombre, String dia) {
        this.nombre = nombre;
        this.dia = dia;
    }


    public String getNombre() {
        return nombre;
    }

    public String getDia() {
        return dia;
    }
}
