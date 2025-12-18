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
        for(int i = 0; i < agenda2.length; i++){
            if(agenda2[i] != null){
                tam_agenda++;
            }
        }
        if(existeContacto(C)){
            agenda2[tam_agenda+1] = C;
        }

    }
    public boolean existeContacto(Contacto C){
        for(int i = 0; i < agenda2.length; i++){
            if(agenda2[i] != null){
                if(agenda2[i].getNombre().equals(C.getNombre()) && agenda2[i].apellido.equals(C.getApellido())){
                    return false;
                }
            }

        }
        return true;
    }

    public void listarContactos(){
        int cantContatactos = 0;
        for(int i = 0; i < agenda2.length; i++){
            if(agenda2[i] != null){
                System.out.println(agenda2[i].getNombre());
            }
        }
    }
    public void buscarContacto(String nombre){

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


