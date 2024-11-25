/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.gestcredito.mysql;

import java.sql.CallableStatement;
import pe.edu.pucp.creditomovil.gestcredito.dao.TransaccionDAO;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.model.Transaccion;

/**
 *
 * @author diego
 */
public class TransaccionMySQL implements TransaccionDAO {

    private Connection conexion;
    private ResultSet rs = null;

    @Override
    public boolean insertar(Transaccion transaccion, int idUsuario, int idCredito, int idMetodo) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_usuario_id", idUsuario);
        parametrosEntrada.put("p_fecha_y_hora", new java.sql.Timestamp(transaccion.getFecha().getTime()));
        parametrosEntrada.put("p_concepto", transaccion.getConcepto());
        parametrosEntrada.put("p_monto", transaccion.getMonto());
        parametrosEntrada.put("p_anulado", transaccion.isAnulado());
        parametrosEntrada.put("p_agencia", transaccion.getAgencia());
        parametrosEntrada.put("p_credito_num_credito", idCredito);
        parametrosEntrada.put("p_foto", transaccion.getFoto());
        parametrosEntrada.put("p_metodo_metodo_pago_id", idMetodo);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        int resultado = DBManager.getInstance().ejecutarProcedimiento("InsertarTransaccion", parametrosEntrada, parametrosSalida);
        return resultado > 0;
    }

    @Override
    public boolean modificar(Transaccion transaccion) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_transaccion", transaccion.getNumOperacion());
        parametrosEntrada.put("p_usuario_usuario_id", transaccion.getUsuarioRegistrado().getIdUsuario());
        parametrosEntrada.put("p_fecha_y_hora", new java.sql.Timestamp(transaccion.getFecha().getTime()));
        parametrosEntrada.put("p_concepto", transaccion.getConcepto());
        parametrosEntrada.put("p_monto", transaccion.getMonto());
        parametrosEntrada.put("p_anulado", transaccion.isAnulado());
        parametrosEntrada.put("p_agencia", transaccion.getAgencia());
        parametrosEntrada.put("p_foto", transaccion.getFoto());
        parametrosEntrada.put("p_metodo_metodo_pago_id", transaccion.getMetodoPago().getIdMetodoPago());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarTransaccion", parametrosEntrada, parametrosSalida);
        return resultado > 0;
    }

    @Override
    public boolean eliminar(int numOperacion) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_transaccion", numOperacion);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarTransaccion", parametrosEntrada, parametrosSalida);
        return resultado > 0;
    }

    @Override
    public Transaccion obtenerPorId(int numOperacion) {
        Transaccion trans = new Transaccion();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_transaccion", numOperacion);
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerTransaccion", parametrosEntrada);
        try {
            if(rs.next()){
                trans.setAgencia(rs.getString("agencia"));
                trans.setAnulado(rs.getInt("anulado")==0?false:true);
                trans.setConcepto(rs.getString("concepto"));
                trans.setCredito(null);
                trans.setFecha(rs.getDate("fecha_y_hora"));
                trans.setFoto(rs.getBytes("foto"));
                trans.setMetodoPago(null);
                trans.setMonto(rs.getDouble("monto"));
                trans.setNumOperacion(rs.getInt("num_transaccion"));
                trans.setUsuarioRegistrado(null);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return trans;
    }
    
    @Override
    public List<Transaccion> listarPorCredito(int numCred){
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_credito", numCred);
        List<Transaccion> transacciones = new ArrayList<>();
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerTransaccionesPorCredito", parametrosEntrada);
        try {
            // Procesar el ResultSet y convertirlo en objetos Transaccion
            while (rs.next()) {
                int numOperacion = rs.getInt("num_transaccion");
                int usuarioId = rs.getInt("usuario_usuario_id");
                Date fecha = rs.getDate("fecha_y_hora");
                String concepto = rs.getString("concepto");
                double monto = rs.getDouble("monto");
                boolean anulado = rs.getBoolean("anulado");
                String agencia = rs.getString("agencia");
                byte[] foto = rs.getBytes("foto");
                int metodoPagoID = rs.getInt("metodo_metodo_pago_id");
                //cargar el usuario con metodo de obtenerCliente usando el ID

            // Crear una instancia de Transaccion
            Transaccion transaccion = new Transaccion(
                        fecha,
                        concepto,
                        monto,
                        anulado,
                        null,
                        agencia,
                        numOperacion,
                        null, // Credito se puede cargar por separado si es necesario
                        foto
                );

                // Agregar a la lista
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }

    @Override
    public List<Transaccion> listarTodos() {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        List<Transaccion> transacciones = new ArrayList<>();

        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarTransacciones", parametrosEntrada);
        try {
            // Procesar el ResultSet y convertirlo en objetos Transaccion
            while (rs.next()) {
                int numOperacion = rs.getInt("num_transaccion");
                int usuarioId = rs.getInt("usuario_usuario_id");
                Date fecha = rs.getDate("fecha_y_hora");
                String concepto = rs.getString("concepto");
                double monto = rs.getDouble("monto");
                boolean anulado = rs.getBoolean("anulado");
                String agencia = rs.getString("agencia");
                byte[] foto = rs.getBytes("foto");
                int metodoPagoID = rs.getInt("metodo_metodo_pago_id");
                //cargar el usuario con metodo de obtenerCliente usando el ID

            // Crear una instancia de Transaccion
            Transaccion transaccion = new Transaccion(
                        fecha,
                        concepto,
                        monto,
                        anulado,
                        null,
                        agencia,
                        numOperacion,
                        null, // Credito se puede cargar por separado si es necesario
                        foto
                );

                // Agregar a la lista
                transacciones.add(transaccion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transacciones;
    }
}
