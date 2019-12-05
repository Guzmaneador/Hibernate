package Vista;

import Controlador.*;
import Modelo.EquipoDAO.Equipo;
import Modelo.JugadorDAO.Jugador;
import Modelo.ModeloImpl;
import Modelo.PartidoDAO.PartidoDAO;
import java.sql.Date;
import java.util.List;
import java.util.Scanner;
import org.hibernate.Hibernate;

/**
 *
 * @author Guzman
 */
public class VistaImpl implements Vista {

    Controlador controlador = new ControladorImpl(new ModeloImpl(), this);
    Scanner teclado = new Scanner(System.in);
    private static final String EQUIPO = "Equipo";
    private static final String JUGADORES = "Jugador";
    private static final String CREATE = "CREATE";
    private static final String UPDATE = "UPDATE";
    private static final String READ = "READ";
    private static final String DELETE = "DELETE";
    VistaDAOJugadores vistaDao;
    PartidoDAO partidoDao = new PartidoDAO();

    public VistaImpl() {
    }

    public void menuInicio() {
                vistaDao= new VistaDAOJugadores();

        System.out.println("-----INICIO------");
        System.out.println("->1. Relizar un CRUD de la base de datos.");
        System.out.println("->2. Relizar un CRUD con GenereicDAO.");     
        System.out.println("->3. Consulta Jugadores mas cobran que el que mas cobra de un equipo.");     
        System.out.println("->10. Consultar nombre,apellido de jugadores con capitan de un jugador.");
        System.out.println("->11. Consultar partidos ganados por un equipo y arbrito.");
        System.out.println("->0. Salir");
        switch (teclado.nextInt()) {
            case 1:
                System.out.println("\n---Selecione la tabla---");
                System.out.println("->1.Equipo.");
                System.out.println("->2.Jugadores.");
                System.out.println("->0.Volver a inicio.");
                switch (teclado.nextInt()) {
                    case 1:
                        menuCRUD(EQUIPO);
                        break;
                    case 2:
                        menuCRUD(JUGADORES);
                        break;


                    default:
                        System.out.println("Parametro no valido");
                        menuInicio();
                }
                break;
            case 2:
                vistaDao.menu();
                break;
            case 3:
                teclado.nextLine();
                System.out.print("Indique el nombre del Equipo: ");
                String nombreEquipazo=teclado.nextLine();
                mostrarResultado(partidoDao.jugadoresMasCobran(nombreEquipazo));
                menuInicio();
                break;
            case 11:
                teclado.nextLine();
                System.out.print("Indique el nombre del Equipo: ");
                String nombreEquipo=teclado.nextLine();
                System.out.print("Indique el Id del Arbitro: ");
                System.out.println(partidoDao.superconsulta(nombreEquipo, teclado.nextInt()));
                teclado.nextLine();
                menuInicio();
                break;
            case 10:
                teclado.nextLine();
                System.out.print("Indique el nombre del Jugador: ");
                String nombreJugador=teclado.nextLine();
                partidoDao.primeraConsulta(nombreJugador);
                menuInicio();
                break;

            case 0:
                System.exit(-1);
                break;
            default:
                System.out.println("El parametro introducido no es valido.");
                menuInicio();
        }
    }

    public void menuCRUD(String tabla) {
        System.out.println("\n---Seleciones Una Accion---");
        System.out.println("->1. Create ");
        System.out.println("->2. Read ");
        System.out.println("->3. Update ");
        System.out.println("->4. Delete ");
        System.out.println("->0. Volver al inicio. ");
        String id ;
        switch (teclado.nextInt()) {
            case 1:
                teclado.nextLine();
                mostrarResultado(controlador.CRUDControlado(CREATE, tabla, solicitarDatosJugador(), null));
                break;
            case 2:
                teclado.nextLine();
                mostrarResultado(controlador.CRUDControlado(READ, tabla, null, null));

                break;
            case 3:
                teclado.nextLine();
                System.out.print("-->Indicque el id del "+tabla+" que desea modificar: ");
                id = teclado.nextLine();
                Object object = new Object();
                switch (tabla) {
                    case "Jugador":
                        object=solicitarDatosJugador();
                        break;
                    case "Equipo":
                        object=solicitarDatosEquipo();
                    default:
                        System.out.println("ERRORupdatesVista()");
                }
                mostrarResultado(controlador.CRUDControlado(UPDATE, tabla, object, id));

                break;
            case 4:
                teclado.nextLine();
                System.out.print("-->Indicque el id del "+tabla+" que desea eliminar: ");
                mostrarResultado(controlador.CRUDControlado(DELETE, tabla, null,teclado.nextLine()));
                break;
            case 0:
                teclado.nextLine();
                menuInicio();
                break;
            default:
                teclado.nextLine();
                System.out.println("Parametro invalido.");
                menuCRUD(tabla);
        }

    }

    public Jugador solicitarDatosJugador() {
        Jugador jugador = new Jugador();
        System.out.print("-->Nombre: ");
        jugador.setNombre(teclado.nextLine());
        System.out.print("-->Apellido: ");
        jugador.setApellido(teclado.nextLine());
        System.out.print("-->Posicion:");
        jugador.setPosicion(teclado.nextLine());
        System.out.print("-->Id Capitan: ");
        jugador.setIdCapitan(teclado.nextInt());
        teclado.nextLine();
        System.out.print("-->Fecha De Alta (yyyymmdd): ");
        jugador.setFechaAlta(new Date(teclado.nextLong()));
        System.out.print("-->Salario: ");
        jugador.setSalario(teclado.nextInt());
        teclado.nextLine();
        System.out.print("-->Id Equipo: ");
        jugador.setEquipo(verificarEquipo(teclado.nextLine()));
        System.out.print("-->Altura: ");
        jugador.setAltura(teclado.nextBigDecimal());
        return jugador;

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

    public Equipo verificarEquipo(String id) {
        List<Object> objetos = controlador.CRUDControlado(READ, "Equipo", null, null);
        for (Object objeto : objetos) {
            Equipo equipo = (Equipo) objeto;
            if(equipo.getIdEquipo() == Integer.parseInt(id)){
                return equipo;
                
            }
        }
        System.out.println("No existe nigun Equipo con ese id");
        menuInicio();
        return new Equipo();
    }
    
    public void mostrarResultado(List<Object> lista) {
        for (Object object : lista) {
            
            System.out.println(object);
        }
        menuInicio();
    }

}
