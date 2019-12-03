package Controlador;

import Modelo.EquipoDAO.Equipo;
import Modelo.JugadorDAO.Jugador;
import Modelo.Modelo;
import Vista.Vista;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Guzman
 */
public class ControladorImpl implements Controlador{
        Modelo modelo;
    Vista vista;
    
    public ControladorImpl(Modelo modelo, Vista vista) {
        this.modelo = modelo;
        this.vista = vista;
    }
    
    @Override
    public void iniciar(){
        vista.menuInicio();
    }
    
    public List<Object> CRUDControlado(String accion, String tabla, Object object, String id){
        return modelo.CRUDModelo(accion,tabla,object,id);
    }

    @Override
    public Object JugadorDaoControlador(String Accion, Jugador object, Serializable id, Class entityClass) {
        return modelo.JugadorDaoModelo(Accion, object, id, entityClass);
    }

    @Override
    public List<Object> equipoDaoControlador(String Accion, Equipo object, Serializable id, Class entityClass) {
        return modelo.EquipoDaoModelo(Accion, object, id, entityClass);
    }
    

}