package Vista;

import Controlador.Controlador;
import Modelo.EquipoDAO.Equipo;
import Modelo.JugadorDAO.Jugador;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Guzman
 */
public class VistaDAOEquipos {
    Scanner teclado = new Scanner(System.in);
    Controlador controlador;
    Vista vista = new VistaImpl();

    public VistaDAOEquipos(Controlador controlador) {
        this.controlador = controlador;
    }
    
    public void menuAccionEquipo(){
        System.out.println("\n---Seleciones Una Accion---");
        System.out.println("->1. Insert.");
        System.out.println("->2. Update.");
        System.out.println("->3. Delete.");
        System.out.println("->4. Mostrar Jugador por Id.");
        System.out.println("->5. Listar Jugadores.");
        System.out.println("->0. Volver al inicio.");
        int id ;
        switch (teclado.nextInt()) {
            case 1:
                teclado.nextLine();
                controlador.equipoDaoControlador("INSERT", solicitarDatosEquipo(), null, null);
                break;
            case 2:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                teclado.nextLine();
                controlador.equipoDaoControlador("UPDATE", solicitarDatosEquipo(), id, null);
                break;
            case 3:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                Equipo equipo = new Equipo();
                teclado.nextLine();
                equipo.setIdEquipo(id);
                controlador.equipoDaoControlador("DELETE",equipo,null, null);
                break;
            case 4:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                teclado.nextLine();
                controlador.equipoDaoControlador("JUGADOR",null,id, Jugador.class);
                break;
            case 5:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                teclado.nextLine();
                controlador.equipoDaoControlador("LISTAR",null,id, Jugador.class);
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