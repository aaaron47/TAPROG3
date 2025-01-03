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
import java.sql.Types;
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
        Connection conn = null;
        CallableStatement cs = null;
        boolean resultado = false;
        
        try {
            conn = DBManager.getInstance().getConnection();
            String sql = "{ CALL InsertarTransaccion(?, ?, ?, ?, ?, ?, ?, ?, ?) }";
            cs = conn.prepareCall(sql);

            // Configura los parámetros
            cs.setInt(1, idUsuario); 
            cs.setTimestamp(2, new java.sql.Timestamp(transaccion.getFecha().getTime()));
            cs.setString(3, transaccion.getConcepto());
            cs.setDouble(4, transaccion.getMonto());
            cs.setBoolean(5, transaccion.isAnulado());
            cs.setString(6, transaccion.getAgencia());
            cs.setInt(7, idCredito);
            cs.setBytes(8, transaccion.getFoto());
            cs.setInt(9, idMetodo);

            // Ejecuta la consulta
            resultado = cs.executeUpdate() > 0;
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

        return resultado;
    }

    @Override
    public boolean modificar(Transaccion transaccion) {
        Connection con = null;
        CallableStatement cs = null;
        boolean modificado = false;

        try {
            // Obtener la conexión a la base de datos
            con = DBManager.getInstance().getConnection();

            // Preparar la llamada al procedimiento almacenado
            cs = con.prepareCall("{CALL ModificarTransaccion(?, ?, ?, ?, ?, ?, ?, ?)}");

            // Establecer los parámetros de entrada
            cs.setInt(1, transaccion.getNumOperacion());
            cs.setInt(2, transaccion.getUsuarioRegistrado().getIdUsuario());
            cs.setTimestamp(3, new java.sql.Timestamp(transaccion.getFecha().getTime()));
            cs.setString(4, transaccion.getConcepto());
            cs.setDouble(5, transaccion.getMonto());
            cs.setBoolean(6, transaccion.isAnulado());
            cs.setString(7, transaccion.getAgencia());
            cs.setBytes(8, transaccion.getFoto());
            cs.setInt(9, transaccion.getMetodoPago().getIdMetodoPago());

            // Ejecutar el procedimiento y verificar si se modificó correctamente
            modificado = (cs.executeUpdate() > 0);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar recursos
            try {
                if (cs != null) {
                    cs.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return modificado;
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
