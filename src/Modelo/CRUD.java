package Modelo;

import Modelo.EquipoDAO.Equipo;
import Modelo.JugadorDAO.Jugador;
import java.util.ArrayList;
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
            resultado = new ArrayList<>() ;

    }
    
    public List<Object> analizarAccion(String accion, String tabla, Object object, String id){
        if(resultado.size() != 0)
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
            case "DELETE":
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
        transaction.commit();
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

    public List<Object> delete(){
        switch (tabla) {
            case "Jugador":
                object=(Jugador) session.get(Jugador.class,Integer.parseInt(id));
                break;
            case "Equipo":
                object=(Equipo)session.get(Equipo.class,Integer.parseInt(id));
                break;
            default:
                System.out.println("ERRORupdates()");
                break;
        }
        
        Transaction transaction = session.beginTransaction();
        if(object != null){
            session.delete(object);
            transaction.commit();
            resultado.add("Se ha borrado la fila con el Id: "+id+" de la tabla: "+tabla);
        }else{
            resultado.add("No se he podido borrar el Id: "+id+" de la tabla: "+tabla);
        }
        
        session.close();
        
        return resultado;
        
    }
    
    private List<Object> update(){
        switch (tabla) {
            case "Jugador":
                return updateJugador();
            case "Equipo":
                return updateEquipo();
            default:
                System.out.println("ERRORupdates()");
        }
        return resultado;
    }
    
    public List<Object> updateJugador(){
        Jugador datosJugador=(Jugador)object;
        Jugador jugador = new Jugador();
        jugador = (Jugador) session.get(Jugador.class, Integer.parseInt(id));
        Transaction transaction = session.beginTransaction();
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
            transaction.commit();
            resultado.add("La fila de la taba "+tabla+" con el id "+id+" ha sido actualizada.");
            
        }else{
            resultado.add("No se ha encontrado el Id: "+id+" de la tabla: "+tabla);
        }
        session.close();
        return resultado;    
    }
    
    public List<Object> updateEquipo(){
        Equipo datosEquipo=(Equipo)object;
        Equipo equipo = new Equipo();
        equipo =(Equipo)session.get(Equipo.class,Integer.parseInt(id));

        Transaction transaction = session.beginTransaction();
        if(equipo != null){
            equipo.setNombre(datosEquipo.getNombre());
            equipo.setCiudad(datosEquipo.getCiudad());
            equipo.setWeb(datosEquipo.getWeb());
            equipo.setPuntos(datosEquipo.getPuntos());             
            session.update(equipo);
            transaction.commit();
            resultado.add("La fila de la taba "+tabla+" con el id "+id+" ha sido actualizada.");
            
        }else{
            resultado.add("No se ha encontrado el Id: "+id+" de la tabla: "+tabla);
        }
        session.close();
        return resultado;    
    }

    

}