package Controlador;

import Modelo.Modelo;
import Vista.Vista;
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
    }
    
    public List<Object> CRUDControlado(String accion, String tabla, Object object, String id){
        return modelo.CRUDModelo(accion,tabla,object,id);
    }
    

}