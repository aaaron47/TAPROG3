/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.gestclientes.mysql;

import java.sql.Connection;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.model.Cliente;
import pe.edu.pucp.creditomovil.model.Evaluacion;
import pe.edu.pucp.creditomovil.gestclientes.dao.EvaluacionDAO;
import pe.edu.pucp.creditomovil.gestclientes.dao.ClienteDAO;

/**
 *
 * @author diego
 */
public class EvaluacionMySQL implements EvaluacionDAO {

    private Connection conexion;
    private ResultSet rs = null;

    @Override
    public boolean insertar(Evaluacion evaluacion, String codigoSupervisor, int codigoCliente) {
        Connection conn = null;
        CallableStatement cs = null;
        CallableStatement csAsociar = null;
        boolean resultado = false;

        try {
            conn = DBManager.getInstance().getConnection();
            String sql = "{ CALL InsertarEvaluacion(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) }";
            cs = conn.prepareCall(sql);

            // Configura los parámetros
            cs.registerOutParameter(1, java.sql.Types.INTEGER);
            cs.setInt(2, codigoCliente);
            cs.setDate(3, new java.sql.Date(evaluacion.getFechaRegistro().getTime()));
            cs.setString(4, evaluacion.getNombreNegocio());
            cs.setString(5, evaluacion.getDireccionNegocio());
            cs.setString(6, evaluacion.getTelefonoNegocio());
            cs.setDouble(7, evaluacion.getVentasDiarias());
            cs.setDouble(8, evaluacion.getInventario());
            cs.setDouble(9, evaluacion.getCostoVentas());
            cs.setDouble(10, evaluacion.getMargenGanancia());
            cs.setBoolean(11, evaluacion.isActivo());
            cs.setDouble(12, evaluacion.getPuntaje());
            cs.setString(13, evaluacion.getObservaciones());
            cs.setBytes(14, evaluacion.getFoto());

            // Ejecuta la consulta
            resultado = cs.executeUpdate() > 0;
            
            int numEvaluacionGenerado = cs.getInt(1);
            evaluacion.setNumeroEvaluacion(numEvaluacionGenerado);
            
            String sqlAsociar = "{ CALL AsociarEvaluacionASupervisor(?, ?) }";
            csAsociar = conn.prepareCall(sqlAsociar);
            csAsociar.setString(1, codigoSupervisor);
            csAsociar.setInt(2, numEvaluacionGenerado);
            csAsociar.execute();
            
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
    public void modificar(Evaluacion evaluacion) {
        CallableStatement cs;
        String query = "{CALL ModificarEvaluacion(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
        int resultado = 0;

        try {
            conexion = DBManager.getInstance().getConnection();
            cs = conexion.prepareCall(query);
            cs.setInt(1, evaluacion.getNumeroEvaluacion());
            Cliente cli = (Cliente) evaluacion.getClienteAsignado();
            if (cli.getCodigoCliente() != 0) {
                cs.setInt(2, cli.getCodigoCliente()); // Asegúrate de que clienteAsignado no sea null
            } else {
                cs.setString(2, " ");
            }
            cs.setDate(3, new java.sql.Date(evaluacion.getFechaRegistro().getTime()));
            cs.setString(4, evaluacion.getNombreNegocio());
            cs.setString(5, evaluacion.getDireccionNegocio());
            cs.setString(6, evaluacion.getTelefonoNegocio());
            cs.setDouble(7, evaluacion.getVentasDiarias());
            cs.setDouble(8, evaluacion.getInventario());
            cs.setDouble(9, evaluacion.getCostoVentas());
            cs.setDouble(10, evaluacion.getMargenGanancia());
            cs.setBoolean(11, evaluacion.isActivo());
            cs.setDouble(12, evaluacion.getPuntaje());
            cs.setString(13, evaluacion.getObservaciones());
            cs.setBytes(14, evaluacion.getFoto());

            resultado = cs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminar(int idEvaluacion) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_numero_evaluacion", idEvaluacion);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarEvaluacion", parametrosEntrada, parametrosSalida);

    }

    @Override
    public Evaluacion obtenerPorId(int idEvaluacion) {
        ClienteDAO daoCliente = new ClienteMySQL();
        Evaluacion ev = new Evaluacion();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_numero_evaluacion", idEvaluacion);
        String query = "{CALL ObtenerEvaluacion(?)}";
        ResultSet rs = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerEvaluacion", parametrosEntrada);
        
        try {
            if (rs.next()) {
                ev.setActivo(true);
                Cliente cli = daoCliente.obtenerPorId(rs.getInt("cliente_codigo_cliente"));
                ev.setClienteAsignado(cli);
                ev.setCostoVentas(rs.getDouble("costo_ventas"));
                ev.setDireccionNegocio(rs.getString("direccion_negocio"));
                ev.setFechaRegistro(rs.getDate("fecha_registro"));
                ev.setInventario(rs.getDouble("inventario"));
                ev.setMargenGanancia(rs.getDouble("margen_ganancia"));
                ev.setNombreNegocio(rs.getString("nombre_negocio"));
                ev.setNumeroEvaluacion(idEvaluacion);
                ev.setObservaciones(rs.getString("observaciones"));
                ev.setPuntaje(rs.getDouble("puntaje"));
                ev.setTelefonoNegocio(rs.getString("telefono_negocio"));
                ev.setVentasDiarias(rs.getDouble("ventas_diarias"));
                ev.setevaluador(null);
                ev.setFoto(rs.getBytes("foto"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ev; 
    }

    @Override
    public List<Evaluacion> listarPorSupervisor(String codSup) {
        List<Evaluacion> evaluaciones = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("codigoSupervisor", codSup);
        
        String query = "{CALL ListarEvaluacionesPorSupervisor(?)}";
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarEvaluacionesPorSupervisor", parametrosEntrada);
        
        ClienteDAO clienteDAO = new ClienteMySQL();
        try {
            while (rs.next()) {
                int numEva = rs.getInt("num_evaluacion");
                int codClien = rs.getInt("cliente_codigo_cliente");
                Cliente cliente = clienteDAO.obtenerPorId(codClien);

                Date fechaReg = rs.getDate("fecha_registro");
                String nombreNeg = rs.getString("nombre_negocio");
                String dirNeg = rs.getString("direccion_negocio");
                String telNeg = rs.getString("telefono_negocio");
                double ventasDia = rs.getDouble("ventas_diarias");
                double inventario = rs.getDouble("inventario");
                double costoVentas = rs.getDouble("costo_ventas");
                double margenGan = rs.getDouble("margen_ganancia");
                boolean activo = rs.getBoolean("activo");
                double puntaje = rs.getDouble("puntaje");
                String obser = rs.getString("observaciones");
                byte[] foto = rs.getBytes("foto");

                Evaluacion eva = new Evaluacion(fechaReg, nombreNeg, dirNeg, telNeg,
                        null, cliente, ventasDia, inventario, costoVentas,
                        margenGan, numEva, activo, puntaje, obser, foto);
                evaluaciones.add(eva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluaciones;
    }

    @Override
    public List<Evaluacion> listarTodos() {
        List<Evaluacion> evaluaciones = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        String query = "{CALL ListarEvaluaciones()}";

        ResultSet rs = null;
        
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarEvaluaciones", parametrosEntrada);
        
        ClienteDAO clienteDAO = new ClienteMySQL();
        try {
            while (rs.next()) {
                int numEva = rs.getInt("num_evaluacion");
                int codClien = rs.getInt("cliente_codigo_cliente");
                Cliente cliente = clienteDAO.obtenerPorId(codClien);

                Date fechaReg = rs.getDate("fecha_registro");
                String nombreNeg = rs.getString("nombre_negocio");
                String dirNeg = rs.getString("direccion_negocio");
                String telNeg = rs.getString("telefono_negocio");
                double ventasDia = rs.getDouble("ventas_diarias");
                double inventario = rs.getDouble("inventario");
                double costoVentas = rs.getDouble("costo_ventas");
                double margenGan = rs.getDouble("margen_ganancia");
                boolean activo = rs.getBoolean("activo");
                double puntaje = rs.getDouble("puntaje");
                String obser = rs.getString("observaciones");
                byte[] foto = rs.getBytes("foto");

                Evaluacion eva = new Evaluacion(fechaReg, nombreNeg, dirNeg, telNeg,
                        null, cliente, ventasDia, inventario, costoVentas,
                        margenGan, numEva, activo, puntaje, obser, foto);
                evaluaciones.add(eva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluaciones;
    }

    @Override
    public List<Evaluacion> listarPorFechas(Date fechaini, Date fechafin) {
        java.sql.Date fechainiSQL = new java.sql.Date(fechaini.getTime());
        java.sql.Date fechafinSQL = new java.sql.Date(fechafin.getTime());
        List<Evaluacion> evaluaciones = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("fecha_inicio", fechainiSQL);
        parametrosEntrada.put("fecha_fin", fechafinSQL);
        
        String query = "{CALL ListarEvaluacionesPorRangoFechas(?, ?)}";

        ClienteDAO clienteDAO = new ClienteMySQL();
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarEvaluacionesPorRangoFechas", parametrosEntrada);

        try {
            while (rs.next()) {
                int numEva = rs.getInt("num_evaluacion");
                int codClien = rs.getInt("cliente_codigo_cliente");
                Cliente cliente = clienteDAO.obtenerPorId(codClien);

                Date fechaReg = rs.getDate("fecha_registro");
                String nombreNeg = rs.getString("nombre_negocio");
                String dirNeg = rs.getString("direccion_negocio");
                String telNeg = rs.getString("telefono_negocio");
                double ventasDia = rs.getDouble("ventas_diarias");
                double inventario = rs.getDouble("inventario");
                double costoVentas = rs.getDouble("costo_ventas");
                double margenGan = rs.getDouble("margen_ganancia");
                boolean activo = rs.getBoolean("activo");
                double puntaje = rs.getDouble("puntaje");
                String obser = rs.getString("observaciones");
                byte[] foto = rs.getBytes("foto");

                // Crear el objeto Evaluacion
                Evaluacion eva = new Evaluacion(
                        fechaReg,
                        nombreNeg,
                        dirNeg,
                        telNeg,
                        null,
                        cliente,
                        ventasDia,
                        inventario,
                        costoVentas,
                        margenGan,
                        numEva,
                        activo,
                        puntaje,
                        obser,
                        foto
                );
                evaluaciones.add(eva);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return evaluaciones;
    }
}
