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
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_monto", credito.getMonto());
        parametrosEntrada.put("p_tasa_interes", credito.getTasaInteres());
        parametrosEntrada.put("p_fecha_otorgamiento", new java.sql.Date(credito.getFechaOtorgamiento().getTime()));
        parametrosEntrada.put("p_estado", credito.getEstado().name());
        parametrosEntrada.put("p_num_cuotas", credito.getNumCuotas());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_num_credito", 6);
        
        int numCredito = DBManager.getInstance().ejecutarProcedimiento("InsertarCredito", parametrosEntrada, parametrosSalida);
        credito.setNumCredito(numCredito);

        parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_credito", numCredito);
        parametrosEntrada.put("p_doc_identidad", codigoCliente);
        parametrosEntrada.put("p_tip_doc", tipodocCli);
        
        parametrosSalida = new HashMap<>();
        
        int result = DBManager.getInstance().ejecutarProcedimiento("AsociarCreditoACliente", parametrosEntrada, parametrosSalida);
        
    }

    @Override
    public void modificar(Credito credito) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_num_credito", credito.getNumCredito());
        parametrosEntrada.put("p_monto", credito.getMonto());
        parametrosEntrada.put("p_tasa_interes", credito.getTasaInteres());
        parametrosEntrada.put("p_fecha_otorgamiento", new java.sql.Date(credito.getFechaOtorgamiento().getTime()));
        parametrosEntrada.put("p_estado", credito.getEstado().name());
        parametrosEntrada.put("p_num_cuotas", credito.getNumCuotas());
        parametrosEntrada.put("p_cant_cuotas_pagadas", credito.getCantCuotasPagadas());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarCredito", parametrosEntrada, parametrosSalida);
        
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
        java.sql.Date fechainiSQL = new java.sql.Date(fechaini.getTime());
        java.sql.Date fechafinSQL = new java.sql.Date(fechafin.getTime());
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_cliente", cli);
        parametrosEntrada.put("fecha_inicio", fechainiSQL);
        parametrosEntrada.put("fecha_fin", fechafinSQL);
        parametrosEntrada.put("estado_credito", estado);
        
        ResultSet rs = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerCreditosPorCliente", parametrosEntrada);

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
    public List<Credito> listarTodosSinCliFiltros(Date fechaini, Date fechafin, String estado) {
        List<Credito> listaCreditos = new ArrayList<>();
        java.sql.Date fechainiSQL = new java.sql.Date(fechaini.getTime());
        java.sql.Date fechafinSQL = new java.sql.Date(fechafin.getTime());
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("fecha_inicio", fechainiSQL);
        parametrosEntrada.put("fecha_fin", fechafinSQL);
        parametrosEntrada.put("estado_credito", estado);
        
        ResultSet rs = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerCreditosFiltro", parametrosEntrada);

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
        
        parametrosSalida.put("id_cliente", 2);
        
        String query = "{CALL ObtenerIdClientePorCredito(?, ?)}";
        idCliente = DBManager.getInstance().ejecutarProcedimiento("ObtenerIdClientePorCredito", parametrosEntrada, parametrosSalida);
        return idCliente;
    }
}
