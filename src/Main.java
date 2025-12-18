import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Contacto contacto1 = new Contacto("Elker", "Herrera", "3003158254");
        Contacto contacto2 = new Contacto("Ana", "Herrera", "3003158254");
        Contacto contacto3 = new Contacto("Daniel", "Herrera", "3003158254");
        Contacto contacto4 = new Contacto("Jeffersson", "Herrera", "3003158254");

        Contacto contacto5 = new Contacto();


        Contacto[] agenda1 = new Contacto[10];

        agenda1[0] = contacto1;
        agenda1[1] = contacto2;
        agenda1[2] = contacto3;

        Agenda miAgenda = new Agenda(agenda1);

        miAgenda.listarContactos();

        miAgenda.añadirContacto(contacto4);

        miAgenda.añadirContacto(contacto2);

        miAgenda.listarContactos();

    }
}
