/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.rrhh.mysql;

import java.util.List;
import pe.edu.pucp.creditomovil.model.Notificacion;
import pe.edu.pucp.creditomovil.rrhh.dao.NotificacionDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.CallableStatement;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
/**
 *
 * @author aaron
 */
public class NotificacionMySQL implements NotificacionDAO{
    private Connection conexion;
    private ResultSet rs;

    @Override
    public void insertar(Notificacion notificacion) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_mensaje", notificacion.getMensaje());
        parametrosEntrada.put("p_id_usuario", notificacion.getId_usuario());
        parametrosEntrada.put("p_activo", notificacion.getActivo());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        parametrosSalida.put("p_id_notificacion", 1);
        
        int numNotificacionGenerado = DBManager.getInstance().ejecutarProcedimiento("InsertarNotificacion", parametrosEntrada, parametrosSalida);
        notificacion.setId_notificacion(numNotificacionGenerado);
    }

    @Override
    public void modificar(Notificacion notificacion) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_id_notificacion", notificacion.getId_notificacion());
        parametrosEntrada.put("p_mensaje", notificacion.getMensaje());
        parametrosEntrada.put("p_id_usuario", notificacion.getId_usuario());
        parametrosEntrada.put("p_activo", notificacion.getActivo());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarNotificacion", parametrosEntrada, parametrosSalida);

    }

    @Override
    public List<Notificacion> listarPorUsuario(int idUsuario) {
        List<Notificacion> notis = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("idUsuario", idUsuario);
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarNotificacionPorUsuario", parametrosEntrada);
        
        try {
            if (rs.next()) {
                Notificacion not = new Notificacion();
                not.setId_notificacion(rs.getInt("id_notificacion"));
                not.setMensaje(rs.getString("mensaje"));
                not.setId_usuario(rs.getInt("id_usuario"));
                not.setActivo(rs.getInt("activo"));
                notis.add(not);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return notis;
    }
    
}
