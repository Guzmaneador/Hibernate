package Modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guzman
 */
public interface Modelo {
    public List<Object> CRUDModelo(String accion, String tabla, Object object, String id);
    public Object JugadorDaoModelo(String Accion,Object object, Serializable id, Class entityClass);
    
    
}
