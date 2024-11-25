/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.rrhh.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.rrhh.dao.SupervisorDAO;
import pe.edu.pucp.creditomovil.model.Supervisor;
import pe.edu.pucp.creditomovil.model.TipoDocumento;

/**
 *
 * @author diego
 */
public class SupervisorMySQL implements SupervisorDAO {

    private Connection conexion;
    private ResultSet rs;

    @Override
    public boolean insertar(Supervisor supervisor) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_fecha", new java.sql.Date(supervisor.getFecha().getTime()));
        parametrosEntrada.put("p_nombre", supervisor.getNombre());
        parametrosEntrada.put("p_ap_paterno", supervisor.getApPaterno());
        parametrosEntrada.put("p_ap_materno", supervisor.getApMaterno());
        parametrosEntrada.put("p_contrasena", supervisor.getContrasenha());
        parametrosEntrada.put("p_fecha_venc", new java.sql.Date(supervisor.getFechaVencimiento().getTime()));
        parametrosEntrada.put("p_activo", supervisor.getActivo()? "1" : "0");
        parametrosEntrada.put("p_tipo_doc", supervisor.getTipoDocumento().name());
        parametrosEntrada.put("p_documento", supervisor.getDocumento());
        parametrosEntrada.put("p_codigo_sup", supervisor.getCodigoEv());
        parametrosEntrada.put("p_codigo_cargo", supervisor.getCodigoCargo());
        parametrosEntrada.put("p_agencia_asignacion", supervisor.getAgenciaAsignacion());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        int resultado = DBManager.getInstance().ejecutarProcedimiento("InsertarSupervisor", parametrosEntrada, parametrosSalida);

        return resultado>0;
    }

    @Override
    public void modificar(int id, Supervisor supervisor) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_usuario_id", id);
        parametrosEntrada.put("p_codigo_sup", supervisor.getCodigoEv());
        parametrosEntrada.put("p_fecha", new java.sql.Date(supervisor.getFecha().getTime()));
        parametrosEntrada.put("p_nombre", supervisor.getNombre());
        parametrosEntrada.put("p_ap_paterno", supervisor.getApPaterno());
        parametrosEntrada.put("p_ap_materno", supervisor.getApMaterno());
        parametrosEntrada.put("p_contrasena", supervisor.getContrasenha());
        parametrosEntrada.put("p_fecha_venc", new java.sql.Date(supervisor.getFechaVencimiento().getTime()));
        parametrosEntrada.put("p_activo", supervisor.getActivo()? "1" : "0");
        parametrosEntrada.put("p_ultimo_logeo", null);
        parametrosEntrada.put("p_codigo_cargo", supervisor.getCodigoCargo());
        parametrosEntrada.put("p_agencia_asignacion", supervisor.getAgenciaAsignacion());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarSupervisor", parametrosEntrada, parametrosSalida);
    }

    @Override
    public void eliminar(int usuarioId) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_supervisor", usuarioId);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        String query = "{CALL EliminarSupervisor(?)}";
        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarSupervisor", parametrosEntrada, parametrosSalida);

    }

    @Override
    public Supervisor obtenerPorId(int usuarioId) {
        Supervisor sup = new Supervisor();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_supervisor", usuarioId);
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerSupervisor", parametrosEntrada);

        try {
            if(rs.next()){
                String tipoDocStr = rs.getString("tipo_doc");
                if(tipoDocStr ==null) tipoDocStr = "DNI"; //Por defecto es peruano
                TipoDocumento tipoDoc = null;
                try {
                    tipoDoc = TipoDocumento.valueOf(tipoDocStr);
                } catch (IllegalArgumentException e) { 
                    System.out.println("Error: " + e);
                }
                sup = new Supervisor(
                    rs.getInt("usuario_id"),
                    rs.getDate("fecha"),
                    rs.getString("nombre"),
                    rs.getString("ap_paterno"),
                    rs.getString("ap_materno"),
                    rs.getString("contrasena"),
                    rs.getDate("fecha_venc"),
                    rs.getBoolean("activo"),
                    tipoDoc,
                    rs.getString("documento"),
                    rs.getString("codigo_sup"),
                    rs.getInt("codigo_cargo"),
                    rs.getString("agencia_asignacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sup;
    }
    
        @Override
    public Supervisor obtenerPorDocIdentidad(String docIden, String tipoDocIden) {
        Supervisor sup = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_doc_iden_sup", docIden);
        parametrosEntrada.put("p_tipo_doc_sup", tipoDocIden);
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerSupervisorPorDocIdentidad", parametrosEntrada);

        try {
            if(rs.next()){
                String tipoDocStr = rs.getString("tipo_doc");
                if(tipoDocStr ==null) tipoDocStr = "DNI"; //Por defecto es peruano
                TipoDocumento tipoDoc = null;
                try {
                    tipoDoc = TipoDocumento.valueOf(tipoDocStr);
                } catch (IllegalArgumentException e) { 
                    System.out.println("Error: " + e);
                }
                sup = new Supervisor(
                    rs.getInt("usuario_id"),
                    rs.getDate("fecha"),
                    rs.getString("nombre"),
                    rs.getString("ap_paterno"),
                    rs.getString("ap_materno"),
                    rs.getString("contrasena"),
                    rs.getDate("fecha_venc"),
                    rs.getBoolean("activo"),
                    tipoDoc,
                    rs.getString("documento"),
                    rs.getString("codigo_sup"),
                    rs.getInt("codigo_cargo"),
                    rs.getString("agencia_asignacion")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sup; 
    }

    @Override
    public List<Supervisor> listarTodos() {
        List<Supervisor> supervisores = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarSupervisores", parametrosEntrada);
        try {
            while (rs.next()) {
                
                String tipoDocStr = rs.getString("tipo_doc");
                TipoDocumento tipoDoc; 
                // es un enum asi que toca hacer el cambio de string a enum
                try {
                    tipoDoc = TipoDocumento.valueOf(tipoDocStr);
                } catch (IllegalArgumentException e) {
                    tipoDoc = TipoDocumento.DNI; // solo como manejo básico
                }
                
                Supervisor supervisor = new Supervisor(
                        rs.getInt("usuario_id"),
                        rs.getDate("fecha"),
                        rs.getString("nombre"),
                        rs.getString("ap_paterno"),
                        rs.getString("ap_materno"),
                        rs.getString("contrasena"),
                        rs.getDate("fecha_venc"),
                        rs.getBoolean("activo"),
                        tipoDoc,
                        rs.getString("documento"),
                        rs.getString("codigo_sup"),
                        rs.getInt("codigo_cargo"),
                        rs.getString("agencia_asignacion")
                );
                supervisores.add(supervisor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supervisores;
    }

}
