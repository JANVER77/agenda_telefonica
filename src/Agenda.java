import java.util.ArrayList;

public class Agenda {
    //private ArrayList<Contacto> agenda = new ArrayList<>();

    Contacto[]  agenda2 = new Contacto[10];

    public Agenda(Contacto[] C) {
        this.agenda2 = C;
    }

    public Contacto[] getAgenda2() {
        return agenda2;
    }

    public void setAgenda2(Contacto[] agenda2) {
        this.agenda2 = agenda2;
    }

    public void añadirContacto(Contacto C){
        int tam_agenda = 0;
        for(Contacto c : agenda2){
            tam_agenda++;
        }
        agenda2[tam_agenda+1] = C;
    }
    public void existeContacto(Contacto C){

    }
    public void listarContactos(){
        for (Contacto c : agenda2) {
            if (c != null) {
                System.out.println(
                        c.getNombre() + " " +
                        c.getApellido() + " - " +
                        c.getTelefono()

                );
            }
        }
    }

    public void buscarContacto(String nombre){

        for (Contacto c : agenda2) {
            if (c != null && c.getNombre().equalsIgnoreCase(nombre)) {
                System.out.println(
                        c.getNombre() + " " +
                                c.getApellido() + " - " +
                                c.getTelefono()
                );
                return;
            }
        }
        System.out.println("Contacto no encontrado");
    }
    public void eliminarContacto(Contacto C){

    }
    public void modificarTelefono(Contacto C){

    }
    public void agendaLLena(){

    }
    public void espaciosLibres(){

    }

}


