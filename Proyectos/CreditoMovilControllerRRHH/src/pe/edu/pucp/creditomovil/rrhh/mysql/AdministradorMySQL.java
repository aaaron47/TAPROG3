/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.rrhh.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.rrhh.dao.AdministradorDAO;
import pe.edu.pucp.creditomovil.model.Administrador;
import pe.edu.pucp.creditomovil.model.TipoDocumento;

/**
 *
 * @author Bleak
 */
public class AdministradorMySQL implements AdministradorDAO {

    private Connection conexion;
    private ResultSet rs;

    @Override
    public void insertar(Administrador administrador) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_usuario_id", administrador.getIdUsuario());
        parametrosEntrada.put("p_codigo_admin", administrador.getCodigoAdm());
        parametrosEntrada.put("p_codigo_cargo", administrador.getCodigoCargo());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        int resultado = DBManager.getInstance().ejecutarProcedimiento("InsertarAdmin", parametrosEntrada, parametrosSalida);
    }

    @Override
    public void modificar(int id, Administrador administrador) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_usuario_id", administrador.getIdUsuario());
        parametrosEntrada.put("p_codigo_admin", administrador.getCodigoAdm());
        parametrosEntrada.put("p_codigo_cargo", administrador.getCodigoCargo());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarAdmin", parametrosEntrada, parametrosSalida);
        
    }

    @Override
    public void eliminar(String codigoAdmin) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_admin", codigoAdmin);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        String query = "{CALL EliminarAdmin(?)}";
        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarAdmin", parametrosEntrada, parametrosSalida);
    }

    @Override
    public Administrador obtenerPorId(String codigoAdmin) {
        Administrador admin = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_admin", codigoAdmin);
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerAdmin", parametrosEntrada);
        try {
            if (rs.next()) {
                String tipoDocStr = rs.getString("tipo_doc");
                if (tipoDocStr == null) {
                    tipoDocStr = "DNI"; //Por defecto es peruano
                }
                TipoDocumento tipoDoc = null;
                try {
                    tipoDoc = TipoDocumento.valueOf(tipoDocStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e);
                }
                admin = new Administrador(
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
                        rs.getString("codigo_admin"),
                        rs.getInt("codigo_cargo")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(AdministradorMySQL.class.getName()).log(Level.SEVERE, null, ex);
            }
        return admin;
    }

    @Override
    public Administrador obtenerPorDocIdentidad(String docIden, String tipoDocIden) {
        Administrador admin = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_doc_iden_sup", docIden);
        parametrosEntrada.put("p_tipo_doc_sup", tipoDocIden);
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerAdminPorDocIdentidad", parametrosEntrada);
        
        try {
            if (rs.next()) {
                String tipoDocStr = rs.getString("tipo_doc");
                if (tipoDocStr == null) {
                    tipoDocStr = "DNI"; //Por defecto es peruano
                }
                TipoDocumento tipoDoc = null;
                try {
                    tipoDoc = TipoDocumento.valueOf(tipoDocStr);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e);
                }
                admin = new Administrador(
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
                        rs.getString("codigo_admin"),
                        rs.getInt("codigo_cargo")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Administrador> listarTodos() {
        List<Administrador> administradores = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarAdmin", parametrosEntrada);
        try {
            while (rs.next()) {
                //
                Administrador admin = new Administrador(
                        rs.getInt("usuario_usuario_id"),
                        new java.util.Date(), "Diego", "Pérez", "Gonzalez", "miContrasena", new java.util.Date(), true,
                        TipoDocumento.DNI, "71608817", rs.getString("codigo_admin"),
                        rs.getInt("codigo_cargo")
                );
                administradores.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return administradores;
    }

}
