package Controlador;

import Modelo.EquipoDAO.Equipo;
import Modelo.JugadorDAO.Jugador;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guzman
 */
public interface Controlador {
    public void iniciar();

    public List<Object> CRUDControlado(String accion, String tabla, Object object, String id);
    
    public Object JugadorDaoControlador(String Accion,Jugador object, Serializable id, Class entityClass);
        
    public List<Object> equipoDaoControlador(String Accion,Equipo object, Serializable id, Class entityClass);


}
