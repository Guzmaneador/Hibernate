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
public class VistaDAO {
    Scanner teclado = new Scanner(System.in);
    private static final String EQUIPO = "Equipo";
    private static final String JUGADORES = "Jugador";
    Controlador controlador;

    public VistaDAO(Controlador controlador) {
        this.controlador = controlador;
    }
    
    
    public void menu(){
                System.out.println("\n---Selecione la tabla---");
                System.out.println("->1.Equipo.");
                System.out.println("->2.Jugadores.");
                System.out.println("->0.Volver a inicio.");
                switch (teclado.nextInt()) {
                    case 1:
                        menuAccionEquipo();
                        break;
                    case 2:
                        menuAccionJugador();
                        break;
                    case 3:

                        break;
                    default:
                        System.out.println("Parametro no valido");
                        menu();
                }
    }
    
    public void menuAccionJugador(){
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
                controlador.JugadorDaoControlador("INSERT", solicitarDatosJugador(), null, null);
                break;
            case 2:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                teclado.nextLine();
                controlador.JugadorDaoControlador("UPDATE", solicitarDatosJugador(), id, null);
                break;
            case 3:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                Jugador jugador = new Jugador();
                teclado.nextLine();
                jugador.setIdJugador(id);
                controlador.JugadorDaoControlador("DELETE",jugador,null, null);
                break;
            case 4:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                teclado.nextLine();
                controlador.JugadorDaoControlador("JUGADOR",null,id, Jugador.class);
                break;
            case 5:
                teclado.nextLine();
                System.out.print("Indique el id del Jugador: ");
                id = teclado.nextInt();
                teclado.nextLine();
                controlador.JugadorDaoControlador("LISTAR",null,id, Jugador.class);
                break;
            case 0:
                teclado.nextLine();
                menu();
                break;
            default:
                teclado.nextLine();
                System.out.println("Parametro invalido.");
                menuAccionJugador();
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
     public Equipo verificarEquipo(String id) {
        List<Object> objetos = controlador.CRUDControlado("READ", "Equipo", null, null);
        for (Object objeto : objetos) {
            Equipo equipo = (Equipo) objeto;
            if(equipo.getIdEquipo() == Integer.parseInt(id)){
                return equipo;
                
            }
        }
        System.out.println("No existe nigun Equipo con ese id");
        menu();
        return new Equipo();
    }

    private void menuAccionEquipo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}