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
/**
 *
 * @author aaron
 */
public class NotificacionMySQL implements NotificacionDAO{
    private Connection conexion;
    private ResultSet rs;

    @Override
    public void insertar(Notificacion notificacion) {
        Connection conn = null;
        CallableStatement csNotificacion = null;

        try {
            conn = DBManager.getInstance().getConnection();
            conn.setAutoCommit(false); // Inicia una transacción

            // Insertar en la tabla credito
            String sqlNotificacion = "{ CALL InsertarNotificacion(?, ?, ?, ?) }";
            csNotificacion = conn.prepareCall(sqlNotificacion);
            csNotificacion.registerOutParameter(1, java.sql.Types.INTEGER); // Parámetro de salida para el ID
            csNotificacion.setString(2, notificacion.getMensaje());
            csNotificacion.setInt(3, notificacion.getId_usuario());
            csNotificacion.setInt(4, notificacion.getActivo());
            csNotificacion.execute();

            // Obtener el ID generado¿
            int numNotificacionGenerado = csNotificacion.getInt(1);
            notificacion.setId_notificacion(numNotificacionGenerado);

            conn.commit(); // Confirma la transacción
        } catch (SQLException ex) {
            if (conn != null) {
                try {
                    conn.rollback(); // Revierte la transacción en caso de error
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            ex.printStackTrace();
        } finally {
            try {
                if (csNotificacion != null) {
                    csNotificacion.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void modificar(Notificacion notificacion) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBManager.getInstance().getConnection();
            String sql = "{ CALL ModificarNotificacion(?, ?, ?, ?) }";
            cs = conn.prepareCall(sql);

            cs.setInt(1, notificacion.getId_notificacion());
            cs.setString(2, notificacion.getMensaje());
            cs.setInt(3, notificacion.getId_usuario());
            cs.setInt(4, notificacion.getActivo());

            cs.execute();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (cs != null) {
                    cs.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public List<Notificacion> listarPorUsuario(int idUsuario) {
        List<Notificacion> notis = new ArrayList<>();
        CallableStatement cs;
        String query = "{CALL ListarNotificacionPorUsuario(?)}";

        try {
            conexion = DBManager.getInstance().getConnection();
            cs = conexion.prepareCall(query);
            cs.setInt(1, idUsuario);

            rs = cs.executeQuery();
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
