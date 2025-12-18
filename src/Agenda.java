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
                    System.out.println("El contacto ya existe en la agenda");
                    return false;
                }
            }

        }
        return true;
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
    public void eliminarContacto(Contacto C) {

        for (int i = 0; i < agenda2.length; i++) {

            if (agenda2[i] != null) {
                if (agenda2[i].getNombre().equals(C.getNombre()) &&
                        agenda2[i].getApellido().equals(C.getApellido())) {

                    agenda2[i] = null; // eliminar contacto
                    System.out.println("Contacto eliminado correctamente.");
                    return;
                }
            }
        }

        System.out.println("El contacto no existe.");
    }

    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {

        for (int i = 0; i < agenda2.length; i++) {

            if (agenda2[i] != null) {
                if (agenda2[i].getNombre().equals(nombre) &&
                        agenda2[i].getApellido().equals(apellido)) {

                    agenda2[i].setTelefono(nuevoTelefono);
                    System.out.println("Teléfono modificado correctamente.");
                    return;
                }
            }
        }

        // Si no se encontró
        System.out.println("El contacto no existe.");
    }

    public void agendaLLena(){
        boolean llena = true;

        for (Contacto c : agenda2) {
            if (c == null) {
                llena = false;
                break;
            }
        }

        if (llena) {
            System.out.println("La agenda esta llena. No tienes espacio disponible.");
        } else {
            System.out.println("La agenda aun tiene espacios disponibles.");
        }

    }
    public void espaciosLibres(){
        int espacios = 0;

        for (Contacto c : agenda2) {
            if (c == null) {
                espacios++;
            }
        }

        System.out.println("Estos son tus espacios disponibles en la agenda: " + espacios);

    }

}


