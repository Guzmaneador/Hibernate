package Controlador;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guzman
 */
public interface Controlador {
    public void iniciar();
    public List<Object> CRUDControlado(String accion, String tabla, Object object, String id);
        public Object JugadorDaoControlador(String Accion,Object object, Serializable id, Class entityClass);

}
