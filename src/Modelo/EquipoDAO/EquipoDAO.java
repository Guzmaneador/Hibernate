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
public class EquipoDAO implements GenericDAO{
    Session session;
    Transaction transaction;
    List<Object> resultado = new ArrayList<>();

    @Override
    public List<Object>analizarAccion(String Accion, Object object, Serializable id, Class entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public List<Object> insert(Object object) {
        session= getSession();
        starTransaction();
        session.save(object);
        endTransaction();
        closeSesion(session);
        resultado.add("Equipo Inseertado.");
        return resultado;
        
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
    public Object damePorId(Serializable id, Class entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object> listar(Class entityClass) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}