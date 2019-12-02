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
    
    public List<Object> analizarAccion(String Accion,Object object, Serializable id, Class entityClass);
    
    public Session getSession();
    
    public void closeSesion(Session sesion);
    
    public void starTransaction();
    
    public void endTransaction();
    
    public void handleException(HibernateException hex);
    
    public List<Object> insert (Object object);
    
    public List<Object> update(Object object);
    
    public List<Object> delete (Object object);
    
    public Object damePorId(Serializable id, Class entityClass);
    
    public List<Object>  listar(Class entityClass);
    
    

}