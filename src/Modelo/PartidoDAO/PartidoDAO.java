package Modelo.PartidoDAO;

import Modelo.GenericDAO;
import Modelo.JugadorDAO.Jugador;
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
public class PartidoDAO implements GenericDAO {
    Session session;
    Transaction transaction;

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
    public List<Object> analizarAccion(String Accion, Object object, Serializable id, Class entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> insert(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> update(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> delete(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> damePorId(Serializable id, Class entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listar(Class entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int superconsulta(String nombreEquipo, int idArbitro ){
        
        String consulta = "FROM Partido as Pa "
                            + " WHERE Pa.arbitro = :idArbitr AND "
                            + " Pa.equipoByLocal = ( FROM Equipo as Eq "
                                + " WHERE Eq.nombre = :nombrete)";
        List<Partido> result = new ArrayList<>();
        session=getSession();
        Query query = session.createQuery(consulta);
        query.setInteger("idArbitr", idArbitro);
        query.setString("nombrete", nombreEquipo);
         result = query.list();
         int totalPartidos=0;
           for (Partido respuesta : result) {
               String[] goles = (respuesta.getResultado()).split("-");
               if(Integer.parseInt(goles[0])> Integer.parseInt(goles[1]))
                   totalPartidos++;
               
           }
        closeSesion(session);

           
        String consulta2 = "FROM Partido as Pa "
                            + " WHERE Pa.arbitro = :idArbitrote AND "
                            + " Pa.equipoByVisitante = ( FROM Equipo as Eq "
                                + " WHERE Eq.nombre = :nombrazo)";
        result.clear();
        session=getSession();
        Query query2 = session.createQuery(consulta2);
        query2.setInteger("idArbitrote", idArbitro);
        query2.setString("nombrazo", nombreEquipo);
         result = query2.list();
           for (Partido respuesta : result) {
               String[] goles = (respuesta.getResultado()).split("-");
               if(Integer.parseInt(goles[0])< Integer.parseInt(goles[1]))
                   totalPartidos++;
               
           }
        
        closeSesion(session);
        return totalPartidos;
    }
    public int primeraConsulta(String nombreJugador){
        
        String consulta = "SELECT Ju.nombre, Ju.apellido, Ju.equipo " +
                            "FROM Jugador as Ju " +
                            "WHERE Ju.idCapitan = " +
                                "(SELECT Ju2.idCapitan " +
                                "FROM Jugador as Ju2 " +
                                "WHERE Ju2.nombre = :nombrete)";
        List<Object> result = new ArrayList<>();
        session=getSession();
        Query query = session.createQuery(consulta);
        query.setString("nombrete", nombreJugador);
         result = query.list();
         int totalPartidos=0;
           for (Object respuesta : result) {
               System.out.println(respuesta);
           }
        
        closeSesion(session);
        return totalPartidos;
    }


//SELECT * FROM partido as Pa 
//WHERE Pa.visitante = ? 
//AND Pa.arbitro =?
//AND( SELECT SUBSTRING_INDEX(Pa2.resultado,'-',1) FROM partido as Pa2)>
//(SELECTS SUBSTRING_INDEX(Pa3.resultado,'-',-1) FROM partido as Pa3)
    
//SELECT * FROM partido as Pa WHERE Pa.local = (SELECT Eq.id_equipo FROM equipo as Eq WHERE Eq.nombre = "Gran Canaria") AND Pa.arbitro = 2
}
//
//Select * FROM partido as Pa
//                 WHERE Pa.arbitro = 2 AND
//                 Pa.local = (SELECT Eq.id_equipo 
//                            FROM equipo as Eq 
//                            WHERE Eq.nombre = "Gran Canaria")