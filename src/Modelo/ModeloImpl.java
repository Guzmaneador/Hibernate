package Modelo;

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
    
    
}