/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.services;
import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.List;
import pe.edu.pucp.creditomovil.model.Notificacion;
import pe.edu.pucp.creditomovil.rrhh.dao.NotificacionDAO;
import pe.edu.pucp.creditomovil.rrhh.mysql.NotificacionMySQL;

@WebService(serviceName = "NotificacionWS")
public class NotificacionWS {

    private NotificacionDAO daoNotificacion = new NotificacionMySQL();
    
    @WebMethod(operationName = "insertarNotificacion")
    public void insertarNotificacion(@WebParam(name = "notificacion") Notificacion notificacion) {
        try{
            daoNotificacion.insertar(notificacion);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "modificarNotificacion")
    public void modificarNotificacion(@WebParam(name = "notificacion") Notificacion notificacion) {
        try{
            daoNotificacion.modificar(notificacion);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "listarPorUsuario")
    public List<Notificacion> listarPorUsuario(@WebParam(name = "idUsuario") int idUsuario) {
        List<Notificacion> notis = null;
        try{
            notis= daoNotificacion.listarPorUsuario(idUsuario);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return notis;
    }
}