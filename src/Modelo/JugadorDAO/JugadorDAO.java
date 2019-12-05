package Modelo.JugadorDAO;

import Modelo.EquipoDAO.Equipo;
import Modelo.GenericDAO;
import static Modelo.GenericDAO.sessionFactori;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Guzman
 */
public class JugadorDAO implements GenericDAO<Jugador>{
    Session session;
    Transaction transaction;
    List<Object> resultado = new ArrayList<>();
    
    @Override
    public List <Object> analizarAccion(String Accion, Jugador object, Serializable id, Class entityClass) {
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
                return listar(entityClass);
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
    public List<Object> insert(Jugador object) {
        session= getSession();
        starTransaction();
        session.save(object);
        endTransaction();
        closeSesion(session);
        resultado.add("Jugador añadido con exito.");
        return resultado;
        
    }
    
    @Override
    public List<Object> update(Jugador object) {
        session= getSession();
        starTransaction();
        Jugador datosJugador=(Jugador)object;
        Jugador jugador = new Jugador();
        jugador = (Jugador) session.get(Jugador.class, jugador.getIdJugador());
        if(jugador != null){
            jugador.setAltura(datosJugador.getAltura());
            jugador.setApellido(datosJugador.getApellido());
            jugador.setEquipo(datosJugador.getEquipo());
            jugador.setFechaAlta(datosJugador.getFechaAlta());
            jugador.setIdCapitan(datosJugador.getIdCapitan());
            jugador.setNombre(datosJugador.getNombre());
            jugador.setPosicion(datosJugador.getPosicion());
            jugador.setSalario(datosJugador.getSalario());
                    
            session.update(jugador);
            endTransaction();
            resultado.add("El Jugador con el id: "+jugador.getIdJugador()+" ha sido actualizada.");
            
        }else{
            resultado.add("No se ha encontrado el Jugador con el id: "+jugador.getIdJugador());
        }
        closeSesion(session);
        return resultado;
    }

    @Override
    public List<Object> delete(Jugador object) {
        session= getSession();
        starTransaction();
        if(object != null){
            session.delete(object);
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
        Jugador jugador = (Jugador) session.get(entityClass, id);

        if(jugador != null){
            endTransaction();
            resultado.add(jugador);
        }else{
            resultado.add("No se ha encontrado el jugador con la id: "+id);
        }
        closeSesion(session);
        return resultado ;
    }

    @Override
    public List<Object> listar(Class entityClass) {
        session= getSession();        
        Query consulta = session.createQuery("from Jugador");
        List<Object> resultado = consulta.list();
        session.close();
        return resultado;
    }
    
    
//SELECT nombre from jugador as Ju WHERE salario > (Select MAX(Ju2.salario) FROM jugador as Ju2 WHERE Ju2.equipo = 3) 
    
//Select nombre, apellido, equipo
//FROM jugador
//WHERE id_capitan =
//      (SELECT id_capitan
//       FROM jugador 
//       WHERE nombre = "Pablo")

//  SELECT count(*) from equipo as Eq
//    
//
//    SELECT nombre, apellido, equipo FROM jugador WHERE id_capitan = (SELECT id_capitan FROM jugador WHERE nombre = "Victor")
//
//    
//


}