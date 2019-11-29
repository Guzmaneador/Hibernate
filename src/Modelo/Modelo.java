package Modelo;

import java.util.List;

/**
 *
 * @author Guzman
 */
public interface Modelo {
    public List<Object> CRUDModelo(String accion, String tabla, Object object, String id);
}
