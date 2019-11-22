package Controlador;

import Modelo.Modelo;
import Vista.Vista;

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
    

}