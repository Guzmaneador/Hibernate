package Modelo;

import Modelo.JugadorDAO.JugadorDAO;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guzman
 */
public class ModeloImpl implements Modelo{
    CRUD crud = new CRUD();
    JugadorDAO jugadorDao= new JugadorDAO();

    @Override
    public List<Object> CRUDModelo(String accion, String tabla, Object object, String id) {
        return crud.analizarAccion(accion, tabla, object, id);
        
    }

    @Override
    public Object JugadorDaoModelo(String Accion, Object object, Serializable id, Class entityClass) {
        jugadorDao.analizarAccion(Accion, object, id, entityClass);
    }
    
    
}