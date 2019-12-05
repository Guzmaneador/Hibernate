package Vista;

import Controlador.Controlador;
import Controlador.ControladorImpl;
import Modelo.EquipoDAO.Equipo;
import Modelo.JugadorDAO.Jugador;
import Modelo.ModeloImpl;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Guzman
 */
public class VistaDAOEquipos {
    Scanner teclado = new Scanner(System.in);
        Controlador controlador ;

    Vista vista = new VistaImpl();

    public VistaDAOEquipos() {
        controlador = new ControladorImpl(new ModeloImpl(),new VistaImpl());
    }
    
    public void menuAccionEquipo(){
        System.out.println("\n---Seleciones Una Accion---");
        System.out.println("->1. Insert.");
        System.out.println("->2. Update.");
        System.out.println("->3. Delete.");
        System.out.println("->4. Mostrar Equipo por Id.");
        System.out.println("->5. Listar Equipo.");
        System.out.println("->0. Volver al inicio.");
        int id ;
        switch (teclado.nextInt()) {
            case 1:
                teclado.nextLine();
                mostrarResultado(controlador.equipoDaoControlador("INSERT", solicitarDatosEquipo(), null, null));
                break;
            case 2:
                teclado.nextLine();
                System.out.print("Indique el id del Equipo: ");
                id = teclado.nextInt();
                teclado.nextLine();
                mostrarResultado(controlador.equipoDaoControlador("UPDATE", solicitarDatosEquipo(), id, null));
                break;
            case 3:
                teclado.nextLine();
                System.out.print("Indique el id del Equipo: ");
                id = teclado.nextInt();
                Equipo equipo = new Equipo();
                teclado.nextLine();
                equipo.setIdEquipo(id);
                mostrarResultado(controlador.equipoDaoControlador("DELETE",equipo,null, null));
                break;
            case 4:
                teclado.nextLine();
                System.out.print("Indique el id del Equipo: ");
                id = teclado.nextInt();
                teclado.nextLine();
                mostrarResultado(controlador.equipoDaoControlador("JUGADOR",null,id, Equipo.class));
                break;
            case 5:
                teclado.nextLine();
                mostrarResultado(controlador.equipoDaoControlador("LISTAR",null,null, Equipo.class));
                break;
            case 0:
                teclado.nextLine();
                vista.menuInicio();
                break;
            default:
                teclado.nextLine();
                System.out.println("Parametro invalido.");
                menuAccionEquipo();
        }
        
    }
    public Equipo solicitarDatosEquipo() {
        Equipo equipo = new Equipo();
        System.out.print("-->Nombre: ");
        equipo.setNombre(teclado.nextLine());
        System.out.print("-->Ciudad: ");
        equipo.setCiudad(teclado.nextLine());
        System.out.print("--> Web:");
        equipo.setWeb(teclado.nextLine());
        System.out.print("-->Puntos: ");
        equipo.setPuntos(teclado.nextInt());
        teclado.nextLine();
        return equipo;

    }
    

    public void mostrarResultado(List<Object> lista) {
        for (Object object : lista) {
            
            System.out.println(object);
        }
        menuAccionEquipo();
    }

}