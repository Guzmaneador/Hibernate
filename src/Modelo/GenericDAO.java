package Modelo;

import java.io.Serializable;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Guzman
 */
public interface GenericDAO {
    public static final Session session=(Session) HibernateUtil.getSessionFactory();
    Transaction transaction = session.beginTransaction();
    
    public Session getSession();
    
    public void closeSesion(Session sesion);
    
    public void starTransaction();
    
    public void endTransaction();
    
    public void handleException(HibernateException hex);
    
    public void insert (Object object);
    
    public void update(Object object);
    
    public void delete (Object object);
    
    public Object damePorId(Serializable id, Class<Object> entityClass);
    
    public void  listar(Class<Object> entityClass);
    
    

}