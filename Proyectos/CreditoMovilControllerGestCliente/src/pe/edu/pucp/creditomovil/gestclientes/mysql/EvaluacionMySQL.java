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
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_clienteCodigoCliente", codigoCliente);
        parametrosEntrada.put("p_fechaRegistro", new java.sql.Date(evaluacion.getFechaRegistro().getTime()));
        parametrosEntrada.put("p_nombreNegocio", evaluacion.getNombreNegocio());
        parametrosEntrada.put("p_direccionNegocio", evaluacion.getDireccionNegocio());
        parametrosEntrada.put("p_telefonoNegocio", evaluacion.getTelefonoNegocio());
        parametrosEntrada.put("p_ventasDiarias", evaluacion.getVentasDiarias());
        parametrosEntrada.put("p_inventario", evaluacion.getInventario());
        parametrosEntrada.put("p_costoVentas", evaluacion.getCostoVentas());
        parametrosEntrada.put("p_margenGanancia", evaluacion.getMargenGanancia());
        parametrosEntrada.put("p_activo", evaluacion.isActivo());
        parametrosEntrada.put("p_puntaje", evaluacion.getPuntaje());
        parametrosEntrada.put("p_observaciones", evaluacion.getObservaciones());
        parametrosEntrada.put("p_foto", evaluacion.getFoto());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_num_evaluacion", Types.INTEGER);
        
        int resu = DBManager.getInstance().ejecutarProcedimiento("InsertarEvaluacion", parametrosEntrada, parametrosSalida);
        int numEv = (int) parametrosSalida.get("p_num_evaluacion");
        evaluacion.setNumeroEvaluacion(numEv);
        
        parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_supervisor_codigo_sup", codigoSupervisor);
        parametrosEntrada.put("p_evaluacion_num_evaluacion", numEv);
        
        parametrosSalida = new HashMap<>();
        
        int res = DBManager.getInstance().ejecutarProcedimiento("AsociarEvaluacionASupervisor", parametrosEntrada, parametrosSalida);

        return res>0;
    }

    @Override
    public void modificar(Evaluacion evaluacion) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_numero_evaluacion", evaluacion.getNumeroEvaluacion());
        Cliente cli = (Cliente) evaluacion.getClienteAsignado();
        if (cli.getCodigoCliente() != 0) {
            parametrosEntrada.put("p_cliente_codigo_cliente", cli.getCodigoCliente()); // Asegúrate de que clienteAsignado no sea null
        } else {
            parametrosEntrada.put("p_cliente_codigo_cliente", 0);
        }
        parametrosEntrada.put("p_fecha_registro", new java.sql.Date(evaluacion.getFechaRegistro().getTime()));
        parametrosEntrada.put("p_nombre_negocio", evaluacion.getNombreNegocio());
        parametrosEntrada.put("p_direccion_negocio", evaluacion.getDireccionNegocio());
        parametrosEntrada.put("p_telefono_negocio", evaluacion.getTelefonoNegocio());
        parametrosEntrada.put("p_ventas_diarias", evaluacion.getVentasDiarias());
        parametrosEntrada.put("p_inventario", evaluacion.getInventario());
        parametrosEntrada.put("p_costo_ventas", evaluacion.getCostoVentas());
        parametrosEntrada.put("p_margen_ganancia", evaluacion.getMargenGanancia());
        parametrosEntrada.put("p_activo", evaluacion.isActivo());
        parametrosEntrada.put("p_puntaje", evaluacion.getPuntaje());
        parametrosEntrada.put("p_observaciones", evaluacion.getObservaciones());
        parametrosEntrada.put("p_foto", evaluacion.getFoto());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarEvaluacion", parametrosEntrada, parametrosSalida);
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
