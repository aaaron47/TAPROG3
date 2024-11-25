/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.gestclientes.mysql;

import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.gestclientes.dao.CreditoDAO;
import java.sql.Connection;
import java.util.Date;
import java.sql.CallableStatement;
import java.sql.Types;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.model.Credito;
import pe.edu.pucp.creditomovil.model.Estado;

/**
 *
 * @author diego
 */
public class CreditoMySQL implements CreditoDAO {

    private Connection conexion;
    private ResultSet rs;

    @Override
    public void insertar(Credito credito, String codigoCliente, String tipodocCli) {
        Connection conn = null;
        CallableStatement csCredito = null;
        CallableStatement csAsociar = null;

        try {
            conn = DBManager.getInstance().getConnection();
            conn.setAutoCommit(false); // Inicia una transacción

            // Insertar en la tabla credito
            String sqlCredito = "{ CALL InsertarCredito(?, ?, ?, ?, ?, ?) }";
            csCredito = conn.prepareCall(sqlCredito);
            csCredito.registerOutParameter(1, java.sql.Types.INTEGER); // Parámetro de salida para el ID
            csCredito.setDouble(2, credito.getMonto());
            csCredito.setDouble(3, credito.getTasaInteres());
            csCredito.setDate(4, new java.sql.Date(credito.getFechaOtorgamiento().getTime()));
            csCredito.setString(5, credito.getEstado().name());
            csCredito.setInt(6, credito.getNumCuotas());
            csCredito.execute();

            // Obtener el ID generado y asignarlo al objeto Credito
            int numCreditoGenerado = csCredito.getInt(1);
            credito.setNumCredito(numCreditoGenerado);

            // Asociar el crédito al cliente
            String sqlAsociar = "{ CALL AsociarCreditoACliente(?, ?, ?) }";
            csAsociar = conn.prepareCall(sqlAsociar);
            csAsociar.setInt(1, numCreditoGenerado); // Usar el ID generado en la asociación
            csAsociar.setString(2, codigoCliente);
            csAsociar.setString(3, tipodocCli);
            csAsociar.execute();

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
                if (csAsociar != null) {
                    csAsociar.close();
                }
                if (csCredito != null) {
                    csCredito.close();
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
    public void modificar(Credito credito) {
        Connection conn = null;
        CallableStatement cs = null;

        try {
            conn = DBManager.getInstance().getConnection();
            String sql = "{ CALL ModificarCredito(?, ?, ?, ?, ?, ?, ?) }";
            cs = conn.prepareCall(sql);

            cs.setInt(1, credito.getNumCredito());
            cs.setDouble(2, credito.getMonto());
            cs.setDouble(3, credito.getTasaInteres());
            cs.setDate(4, new java.sql.Date(credito.getFechaOtorgamiento().getTime()));
            cs.setString(5, credito.getEstado().name());
            cs.setInt(6, credito.getNumCuotas());
            cs.setInt(7, credito.getCantCuotasPagadas());

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
    public void eliminar(String numCredito) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_credito", numCredito);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarCredito", parametrosEntrada, parametrosSalida);
    }

    @Override
    public Credito obtenerPorId(int numCredito) {
        Credito cred = new Credito();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_credito", numCredito);
        
        ResultSet rs = null;
        
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerCredito", parametrosEntrada);

        try {
            if (rs.next()) {
                cred.setEstado(Estado.valueOf(rs.getString("estado")));
                cred.setFechaOtorgamiento(rs.getDate("fecha_otorgamiento"));
                cred.setMonto(rs.getDouble("monto"));
                cred.setNumCredito(rs.getInt("num_credito"));
                cred.setNumCuotas(rs.getInt("num_cuotas"));
                cred.setTasaInteres(rs.getDouble("tasa_interes"));
                cred.setCliente(null);
                cred.setCantCuotasPagadas(rs.getInt("cant_cuotas_pagadas"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cred;
    }

    @Override
    public List<Credito> listarTodosFiltros(int cli, Date fechaini, Date fechafin, String estado) {
        List<Credito> listaCreditos = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBManager.getInstance().getConnection();
        String sql = "{ CALL ObtenerCreditosPorCliente(?, ?, ?, ?) }";

        java.sql.Date fechainiSQL = new java.sql.Date(fechaini.getTime());
        java.sql.Date fechafinSQL = new java.sql.Date(fechafin.getTime());

        try {
            cs = conn.prepareCall(sql);
            cs.setInt(1, cli);
            cs.setDate(2, fechainiSQL);
            cs.setDate(3, fechafinSQL);
            cs.setString(4, estado);
            rs = cs.executeQuery();

            while (rs.next()) {
                int numCredito = rs.getInt("num_credito");
                double monto = rs.getDouble("monto");
                double tasaInteres = rs.getDouble("tasa_interes");
                Date fechaOtorgamiento = rs.getDate("fecha_otorgamiento");
                Estado est = Estado.valueOf(rs.getString("estado"));
                int numCuotas = rs.getInt("num_cuotas");
                int cantCuotasPagadas = rs.getInt("cant_cuotas_pagadas");

                // Crear el objeto Credito. Nota que el cliente es null por simplicidad
                Credito credito = new Credito(numCredito, monto, tasaInteres, fechaOtorgamiento, null, est, numCuotas,
                        cantCuotasPagadas);
                listaCreditos.add(credito);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return listaCreditos;
    }

    @Override
    public List<Credito> listarTodosSinCliFiltros(Date fechaini, Date fechafin, String estado) {
        List<Credito> listaCreditos = new ArrayList<>();
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        conn = DBManager.getInstance().getConnection();
        String sql = "{ CALL ObtenerCreditosFiltro(?, ?, ?) }";

        java.sql.Date fechainiSQL = new java.sql.Date(fechaini.getTime());
        java.sql.Date fechafinSQL = new java.sql.Date(fechafin.getTime());

        try {
            cs = conn.prepareCall(sql);
            cs.setDate(1, fechainiSQL);
            cs.setDate(2, fechafinSQL);
            cs.setString(3, estado);
            rs = cs.executeQuery();

            while (rs.next()) {
                int numCredito = rs.getInt("num_credito");
                double monto = rs.getDouble("monto");
                double tasaInteres = rs.getDouble("tasa_interes");
                Date fechaOtorgamiento = rs.getDate("fecha_otorgamiento");
                Estado est = Estado.valueOf(rs.getString("estado"));
                int numCuotas = rs.getInt("num_cuotas");
                int cantCuotasPagadas = rs.getInt("cant_cuotas_pagadas");
                // Crear el objeto Credito. Nota que el cliente es null por simplicidad
                Credito credito = new Credito(numCredito, monto, tasaInteres, fechaOtorgamiento, null, est, numCuotas,
                        cantCuotasPagadas);
                listaCreditos.add(credito);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
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
        return listaCreditos;
    }

    @Override
    public List<Credito> listarCreditosPorCliente(int cli) {
        List<Credito> listaCreditos = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_cliente", cli);
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerCreditosPorClienteSinFiltro", parametrosEntrada);
        
        try {
            while (rs.next()) {
                int numCredito = rs.getInt("num_credito");
                double monto = rs.getDouble("monto");
                double tasaInteres = rs.getDouble("tasa_interes");
                Date fechaOtorgamiento = rs.getDate("fecha_otorgamiento");
                Estado est = Estado.valueOf(rs.getString("estado"));
                int numCuotas = rs.getInt("num_cuotas");
                int cantCuotasPagadas = rs.getInt("cant_cuotas_pagadas");
                // Crear el objeto Credito. Nota que el cliente es null por simplicidad
                Credito credito = new Credito(numCredito, monto, tasaInteres, fechaOtorgamiento, null, est, numCuotas,
                cantCuotasPagadas);
                listaCreditos.add(credito);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaCreditos;
    }
    
     @Override
    public List<Credito> listarTodos() {
        List<Credito> listaCreditos = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        ResultSet rs = null;
        
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarCreditos", parametrosEntrada);
        
        try {
            while (rs.next()) {
                int numCredito = rs.getInt("num_credito");
                double monto = rs.getDouble("monto");
                double tasaInteres = rs.getDouble("tasa_interes");
                Date fechaOtorgamiento = rs.getDate("fecha_otorgamiento");
                Estado est = Estado.valueOf(rs.getString("estado"));
                int numCuotas = rs.getInt("num_cuotas");
                int cantCuotasPagadas = rs.getInt("cant_cuotas_pagadas");
                // Crear el objeto Credito. Nota que el cliente es null por simplicidad
                
                
                
                Credito credito = new Credito(numCredito, monto, tasaInteres, fechaOtorgamiento, null, est, numCuotas,
                cantCuotasPagadas);
                listaCreditos.add(credito);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return listaCreditos;
    }

     @Override
    public int obtenerIdClientePorCredito(int numCredito) {
        int idCliente = -1; // Valor predeterminado en caso de no encontrar un cliente
        CallableStatement cs;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("id_credito", numCredito);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("id_cliente", Types.INTEGER);
        
        String query = "{CALL ObtenerIdClientePorCredito(?, ?)}";
        int res = DBManager.getInstance().ejecutarProcedimiento("ObtenerIdClientePorCredito", parametrosEntrada, parametrosSalida);
        idCliente = (int) parametrosSalida.get("id_cliente");
        return idCliente;
    }
}
