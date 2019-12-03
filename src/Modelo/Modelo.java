package Modelo;

import Modelo.EquipoDAO.Equipo;
import Modelo.JugadorDAO.Jugador;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guzman
 */
public interface Modelo {
    public List<Object> CRUDModelo(String accion, String tabla, Object object, String id);
    
    public Object JugadorDaoModelo(String Accion,Jugador object, Serializable id, Class entityClass);
    public List<Object> EquipoDaoModelo(String Accion,Equipo object, Serializable id, Class entityClass);
    
    
}
