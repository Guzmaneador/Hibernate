package Modelo;

import Modelo.EquipoDAO.EquipoDAO;
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
    EquipoDAO equipoDao= new EquipoDAO();

    @Override
    public List<Object> CRUDModelo(String accion, String tabla, Object object, String id) {
        return crud.analizarAccion(accion, tabla, object, id);
        
    }

    @Override
    public Object JugadorDaoModelo(String Accion, Object object, Serializable id, Class entityClass) {
        return jugadorDao.analizarAccion(Accion, object, id, entityClass);
    }

    @Override
    public List<Object> EquipoDaoModelo(String Accion, Object object, Serializable id, Class entityClass) {
        return equipoDao.analizarAccion(Accion, object, id, entityClass);
    }
    
    
}