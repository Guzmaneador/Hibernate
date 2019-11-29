package Modelo;

import Modelo.JugadorDAO.Jugador;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Guzman
 */
public class CRUD {
    String accion,tabla,id;
    SessionFactory sessionFactori;
    Session session;
    Object object;
    List<Object> resultado ;
    
    
    
    public CRUD() {
        this.sessionFactori = HibernateUtil.getSessionFactory();
    }
    
    public List<Object> analizarAccion(String accion, String tabla, Object object, String id){
        resultado.clear();
        session = sessionFactori.openSession();
        this.accion=accion;
        this.tabla=tabla;
        this.id=id;
        this.object=object;
        switch (accion) {
            case "CREATE":
                return create();
            case "UPDATE":
                return update();
            case "DLETE":
                return delete();
            case "READ":
                return read();
            default:
                throw new AssertionError();
        }
        
    }
    private List<Object> create(){
        Transaction transaction = session.beginTransaction();
        session.save(object);
        session.close();
        resultado.add("Se ha añadido una nueva fila a la tabla: "+tabla);
        return resultado;


        
        
    }
    private List<Object> read(){
        Query consulta = session.createQuery("from "+tabla);
        resultado = consulta.list();
        session.close();
        return resultado;
        
    }
    
    private List<Object> update(){
        switch (tabla) {
            case "jugador":
                return updateJugador();
            case "equipo":
                return updateEquipo();
            default:
                System.out.println("ERRORupdates()");
        }
        return resultado;
    }
    public List<Object> delete(){
        
        object=(Object) session.get(Object.class, id);
        if(object != null){
            session.delete(object);
            resultado.add("Se ha borrado la fila con el Id: "+id+" de la tabla: "+tabla);
        }else{
            resultado.add("No se ha encontrado el Id: "+id+" de la tabla: "+tabla);
        }
        session.close();
        
        return resultado;
        
    }
    public List<Object> updateJugador(){
        Jugador datosJugador=(Jugador)object;
        Jugador jugador = new Jugador();
        jugador = (Jugador) session.get(Jugador.class, id);
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
            
        }else{
            resultado.add("No se ha encontrado el Id: "+id+" de la tabla: "+tabla);
        }
        session.close(); 
        return resultado;
        
        
        
    }
    public List<Object> updateEquipo(){
        return resultado;
    }
    

}