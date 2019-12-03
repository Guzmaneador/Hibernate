package Modelo.EquipoDAO;

import Modelo.GenericDAO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Guzman
 */
public class EquipoDAO implements GenericDAO<Equipo>{
    Session session;
    Transaction transaction;
    List<Object> resultado = new ArrayList<>();

    @Override
    public List<Object>analizarAccion(String Accion, Equipo object, Serializable id, Class entityClass) {
        switch (Accion) {
            case "INSERT":
                return insert(object);
                
            case "UPDATE":
                return update(object);
            case "DELETE":
                return delete(object);
            case "JUGADOR":
                return damePorId(id, entityClass);

            case "LISTAR":
//                return listar(entityClass);
                                throw new AssertionError();

            default:
                throw new AssertionError();
        }
    }
    
    @Override
    public Session getSession() {
        return sessionFactori.openSession();
    }

    @Override
    public void closeSesion(Session session) {
        session.close();
    }

    @Override
    public void starTransaction() {
       transaction = session.beginTransaction();
    }

    @Override
    public void endTransaction() {
        transaction.commit();
    }

    @Override
    public void handleException(HibernateException hex) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> insert(Equipo object) {
        session= getSession();
        starTransaction();
        session.save(object);
        endTransaction();
        closeSesion(session);
        resultado.add("Equipo Inseertado.");
        return resultado;
        
    }



    @Override
    public List<Object> update(Equipo object) {
        session= getSession();
        starTransaction();
        Equipo datosEquipo=(Equipo)object;
        Equipo equipo = new Equipo();
        equipo = (Equipo) session.get(Equipo.class, equipo.getIdEquipo());
        if(equipo != null){

            equipo.setNombre(datosEquipo.getNombre());

                    
            session.update(equipo);
            endTransaction();
            resultado.add("El Jugador con el id: "+equipo.getIdEquipo()+" ha sido actualizada.");
            
        }else{
            resultado.add("No se ha encontrado el Jugador con el id: "+equipo.getIdEquipo());
        }
        closeSesion(session);
        return resultado;
    }

    @Override
    public List<Object> delete(Equipo object) {
        session= getSession();
        starTransaction();
        Equipo jugadorBorrar = (Equipo) session.get(Equipo.class,object.getIdEquipo());
        if(jugadorBorrar != null){
            session.delete(jugadorBorrar);
            endTransaction();
            resultado.add("Se ha borrado el jugador" );
        }else{
            resultado.add("No se he podido borrar el Jugador indicado");
        }
        return resultado;
    }

    @Override
    public List<Object> damePorId(Serializable id, Class entityClass) {
        session= getSession();
        starTransaction();
        Equipo equipo = new Equipo();
        equipo =(Equipo) session.get(entityClass, id);
        if(equipo != null){
            endTransaction();
            resultado.add(equipo);
        }else{
            resultado.add("No se ha encontrado el jugador con la id: "+id);
        }
        closeSesion(session);
        return resultado ;
    }

    @Override
    public List<Object> listar(Class entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}