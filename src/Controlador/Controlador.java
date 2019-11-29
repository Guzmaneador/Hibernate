package Controlador;

import java.util.List;

/**
 *
 * @author Guzman
 */
public interface Controlador {
    public void iniciar();
    public List<Object> CRUDControlado(String accion, String tabla, Object object, String id);
}
