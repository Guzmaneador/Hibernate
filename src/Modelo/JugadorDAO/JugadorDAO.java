package Modelo.JugadorDAO;

import Modelo.GenericDAO;
import static Modelo.GenericDAO.sessionFactori;
import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Guzman
 */
public class JugadorDAO implements GenericDAO{
    Session session;
    Transaction transaction;
    
    @Override
    public List <Object> analizarAccion(String Accion, Object object, Serializable id, Class entityClass) {
        switch (Accion) {
            case val:
                
                break;
            case val:
                
                break;
            case val:
                
                break;
            case val:
                
                break;
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
    public void insert(Object object) {
        session= getSession();
        starTransaction();
        session.save(object);
        endTransaction();
        closeSesion(session);
        
    }
    
    @Override
    public void update(Object object) {
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
            System.out.println("El Jugador con el id: "+jugador.getIdJugador()+" ha sido actualizada.");
            
        }else{
            System.out.println("No se ha encontrado el Jugador con el id: "+jugador.getIdJugador());
        }
        closeSesion(session);
    }

    @Override
    public void delete(Object object) {
        session= getSession();
        starTransaction();
        if(object != null){
            session.delete(object);
            endTransaction();
            System.out.println("Se ha borrado el jugador" );
        }else{
            System.out.println("No se he podido borrar el Jugador indicado");
        }
    }

    @Override
    public Object damePorId(Serializable id, Class entityClass) {      
        session= getSession();
        Jugador jugador = (Jugador) session.get(entityClass, id);
        starTransaction();
        closeSesion(session);

      return jugador;        
    }

    @Override
    public List<Object> listar(Class entityClass) {
        session= getSession();        
        Query consulta = session.createQuery("from Jugador");
        List<Object> resultado = consulta.list();
        session.close();
        return resultado;
    }
    
    
//SELECT nombre from jugador as Ju
//        Where salario >
//                  (Select MAX(Ju2.salario) FROM jugador as Ju2
//                        WHERE Ju2.empresa = ?)
    

//Select nombre, apellido, equipo
//FROM jugador as Ju
//capitan =
//      (SELECT capitan
//       FROM jugador 
//       WHERE nombre = ?)


    
}