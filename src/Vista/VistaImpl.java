package Vista;

import Controlador.*;
import Modelo.ModeloImpl;
import java.util.Scanner;

/**
 *
 * @author Guzman
 */
public class VistaImpl implements Vista{
    Controlador controlador = new ControladorImpl(new ModeloImpl(),this);
    Scanner teclado = new Scanner(System.in);
    private static final String EQUIPO="equipo"; 
    private static final String JUGADORES="jugador"; 
    private static final String CREATE="CREATE"; 
    private static final String UPDATE="UPDATE"; 
    private static final String READ="READ"; 
    private static final String DELETE="DELETE"; 
    
    
    public void menuInicio(){
        System.out.println("-----INICIO------");
        System.out.println("->1. Relizar un CRUD de la base de datos.");
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
                    case 3:
                        
                        break;
                    default:
                        System.out.println("Parametro no valido");
                        menuInicio();
                }
            break;
            
            
            case 0:
                System.exit(-1);
            break;            
            default:
                System.out.println("El parametro introducido no es valido.");
                menuInicio();
        }
    }
    
    public void menuCRUD(String tabla){
         System.out.println("\n---Seleciones Una Accion---");
                System.out.println("->1. Create ");
                System.out.println("->2. Read ");
                System.out.println("->3. Update ");
                System.out.println("->4. Delete ");
                System.out.println("->0. Volver al inicio. ");
                switch (teclado.nextInt()) {
                    case 1:
                        controlador.CRUDControlado(CREATE,tabla,null,null);
                        break;
                    case 2:
                        controlador.CRUDControlado(CREATE,tabla,null,null);

                        break;
                    case 3:
                        controlador.CRUDControlado(CREATE,tabla,null,null);

                        break;
                    case 4:
                        controlador.CRUDControlado(CREATE,tabla,null,null);

                        break;
                    case 0:
                        menuInicio();
                        break;
                    default:
                        System.out.println("Parametro invalido.");
                        menuCRUD(tabla);
                }
        
    }

}