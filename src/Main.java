import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Contacto contacto1 = new Contacto("Elker", "Herrera", "3003158254");
        Contacto contacto2 = new Contacto("Ana", "Torres", "3003158254");
        Contacto contacto3 = new Contacto("Daniel", "Herrera", "3003158254");
        Contacto contacto4 = new Contacto("Jeffersson", "Herrera", "3003158254");

        Contacto contacto5 = new Contacto();


        Contacto[] agenda1 = new Contacto[10];

        agenda1[0] = contacto1;
        agenda1[1] = contacto2;
        agenda1[2] = contacto3;

        Agenda miAgenda = new Agenda(15, agenda1);

        System.out.println("---- Antes de agregar ----");
        miAgenda.listarContactos();
        miAgenda.agendaLLena();
        miAgenda.espaciosLibres();

        System.out.println("--- Buscar contacto ---");
        miAgenda.buscarContacto("Ana");
        miAgenda.buscarContacto("Pedro");
        miAgenda.buscarContacto("sneider");

        miAgenda.añadirContacto(contacto4);

        miAgenda.añadirContacto(contacto2);

        miAgenda.listarContactos();


        System.out.println(miAgenda.agenda2.length);


    }
}
