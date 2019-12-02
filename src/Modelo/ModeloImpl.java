package Modelo;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guzman
 */
public class ModeloImpl implements Modelo{
    CRUD crud = new CRUD();

    @Override
    public List<Object> CRUDModelo(String accion, String tabla, Object object, String id) {
        return crud.analizarAccion(accion, tabla, object, id);
        
    }

    @Override
    public Object JugadorDaoModelo(String Accion, Object object, Serializable id, Class<Object> entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}