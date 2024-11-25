/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.rrhh.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.model.Rol;
import pe.edu.pucp.creditomovil.rrhh.dao.UsuarioDAO;
import pe.edu.pucp.creditomovil.model.Supervisor;
import pe.edu.pucp.creditomovil.model.TipoDocumento;
import pe.edu.pucp.creditomovil.model.Usuario;
import pe.edu.pucp.creditomovil.model.UsuarioInstancia;
/**
 *
 * @author diego
 */
public class UsuarioMySQL implements UsuarioDAO{
   private Connection conexion;
   private ResultSet rs;

    @Override
    public void insertar(Usuario usuario) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_fecha", new Date(usuario.getFecha().getTime()));
        parametrosEntrada.put("p_nombre", usuario.getNombre());
        parametrosEntrada.put("p_ap_paterno", usuario.getApPaterno());
        parametrosEntrada.put("p_ap_materno", usuario.getApMaterno());
        parametrosEntrada.put("p_contrasena", usuario.getContrasenha());
        parametrosEntrada.put("p_fecha_venc", new Date(usuario.getFechaVencimiento().getTime()));
        if(usuario.getActivo()) parametrosEntrada.put("p_activo", "S");
            parametrosEntrada.put("p_activo", "N");
        parametrosEntrada.put("p_ultimo_logeo", usuario.getUltimoLogueo() != null ? new Date(usuario.getUltimoLogueo().getTime()) : new Date(System.currentTimeMillis()));
        parametrosEntrada.put("p_tipo_doc", usuario.getTipoDocumento().name());
        parametrosEntrada.put("p_documento", usuario.getDocumento());
        parametrosEntrada.put("p_rol", null);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_idUsuario", 12);
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("InsertarUsuario", parametrosEntrada, parametrosSalida);
    }
   
    @Override
    public void modificar(int id,Usuario usuario) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_id", id);
        parametrosEntrada.put("p_fecha", new Date(usuario.getFecha().getTime()));
        parametrosEntrada.put("p_nombre", usuario.getNombre());
        parametrosEntrada.put("p_ap_paterno", usuario.getApPaterno());
        parametrosEntrada.put("p_ap_materno", usuario.getApMaterno());
        parametrosEntrada.put("p_contrasena", usuario.getContrasenha());
        parametrosEntrada.put("p_fecha_venc", new Date(usuario.getFechaVencimiento().getTime()));
        if(usuario.getActivo()) parametrosEntrada.put("p_activo", "S");
            parametrosEntrada.put("p_activo", "N");
        parametrosEntrada.put("p_ultimo_logeo", usuario.getUltimoLogueo() != null ? new Date(usuario.getUltimoLogueo().getTime()) : new Date(System.currentTimeMillis()));
        parametrosEntrada.put("p_clase", 1);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarUsuario", parametrosEntrada, parametrosSalida);

    }

    @Override
    public void eliminar(int id) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_id", id);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        String query = "{CALL EliminarUsuario(?)}";
        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarUsuario", parametrosEntrada, parametrosSalida);
        
    }

    @Override
    public UsuarioInstancia obtenerPorId(int id) {
        UsuarioInstancia user = new UsuarioInstancia();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_id", id);
        
        String query = "{CALL ObtenerUsuario(?)}";
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerUsuario", parametrosEntrada);
        
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
                Rol rol = null;
                try {
                    rol = Rol.valueOf(rs.getString("rol"));
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e);
                }
                user = new UsuarioInstancia(
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
                        rol
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user; //por ahora es null, necesito ver qué añadirle
    }
    
    @Override
    public UsuarioInstancia obtenerPorDocIdentidad(String docIden, String tipoDocIden) {
        UsuarioInstancia user = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_doc_iden", docIden);
        parametrosEntrada.put("p_tipo_doc_iden", tipoDocIden);
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerUsuarioPorDocIdentidad", parametrosEntrada);

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
                Rol rol = null;
                try {
                    rol = Rol.valueOf(rs.getString("rol"));
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e);
                }
                user = new UsuarioInstancia(
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
                        rol
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<Usuario> listarTodos() {
        List<Usuario> usuarios = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        ResultSet rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarUsuarios", parametrosEntrada);
        try {

            while (rs.next()) {
                
                String tipoDocStr = rs.getString("tipo_doc");
                TipoDocumento tipoDoc; 
                
                try {
                    tipoDoc = TipoDocumento.valueOf(tipoDocStr);
                } catch (IllegalArgumentException e) {
                    tipoDoc = TipoDocumento.DNI; // solo como manejo básico
                }
                
                Usuario usuario = new Supervisor(
                    rs.getInt("usuario_id"), 
                    rs.getDate("fecha"),
                    rs.getString("nombre"),                   // Columna 'nombre'
                    rs.getString("ap_paterno"),               // Columna 'ap_paterno'
                    rs.getString("ap_materno"),               // Columna 'ap_materno'
                    rs.getString("contrasena"),               // Columna 'contrasena'             
                    rs.getDate("fecha_venc"),          // Columna 'fecha_vencimiento'
                    rs.getString("activo").equals("S"),       // Convertimos "S" o "N" a booleano
                    tipoDoc,
                    rs.getString("documento"),
                    "A",1,"SUP123"
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuarios;
    }    
}
