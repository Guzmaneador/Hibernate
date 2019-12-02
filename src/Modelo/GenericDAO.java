package Modelo;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Guzman
 */
public interface GenericDAO {
    public static final SessionFactory sessionFactori =(SessionFactory) HibernateUtil.getSessionFactory();
    
    public Session getSession();
    
    public void closeSesion(Session sesion);
    
    public void starTransaction();
    
    public void endTransaction();
    
    public void handleException(HibernateException hex);
    
    public void insert (Object object);
    
    public void update(Object object);
    
    public void delete (Object object);
    
    public Object damePorId(Serializable id, Class<Object> entityClass);
    
    public List<Object>  listar(Class<Object> entityClass);
    
    

}