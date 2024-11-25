/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.gestclientes.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Types;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.creditomovil.model.Cliente;
import pe.edu.pucp.creditomovil.model.TipoDocumento;

/**
 *
 * @author Bleak
 */
public class ClienteMySQL implements ClienteDAO {


    @Override
    public boolean insertar(Cliente cliente) {

        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_fecha", new java.sql.Date(cliente.getFecha().getTime()));
        parametrosEntrada.put("p_nombre", cliente.getNombre());
        parametrosEntrada.put("p_ap_paterno", cliente.getApPaterno());
        parametrosEntrada.put("p_ap_materno", cliente.getApMaterno());
        parametrosEntrada.put("p_contrasena", cliente.getContrasenha());
        parametrosEntrada.put("p_fecha_venc", new java.sql.Date(cliente.getFechaVencimiento().getTime()));
        parametrosEntrada.put("p_activo", true);
        parametrosEntrada.put("p_ultimo_logeo", cliente.getUltimoLogueo() != null ? new java.sql.Date(cliente.getUltimoLogueo().getTime()) : new java.sql.Date(new java.util.Date().getTime()));
        parametrosEntrada.put("p_tipo_doc", cliente.getTipoDocumento().name());
        parametrosEntrada.put("p_documento", cliente.getDocumento());
        parametrosEntrada.put("p_rol", cliente.getRol().name());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_idUsuario",Types.INTEGER);
        
        int usuarioId = DBManager.getInstance().ejecutarProcedimiento("InsertarUsuario", parametrosEntrada, parametrosSalida);
        cliente.setIdUsuario(usuarioId); // Asignar el ID al objeto cliente

        parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_usuario_id", usuarioId);
        parametrosEntrada.put("p_direccion", cliente.getDireccion());
        parametrosEntrada.put("p_telefono", cliente.getTelefono());
        parametrosEntrada.put("p_email", cliente.getEmail());
        parametrosEntrada.put("p_tipo_cliente", cliente.getTipoCliente());
        parametrosEntrada.put("p_ranking", cliente.getRanking());
        
        parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_codigo_cliente",Types.INTEGER);
        
        int clienteId = DBManager.getInstance().ejecutarProcedimiento("InsertarCliente", parametrosEntrada, parametrosSalida);
        cliente.setCodigoCliente(clienteId);

        return (usuarioId!=-1 && clienteId!=-1);
    }

    @Override
    public boolean modificar(Cliente cliente) {
        
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_usuario_usuario_id", cliente.getIdUsuario());
        parametrosEntrada.put("p_codigo_cliente", cliente.getCodigoCliente());
        parametrosEntrada.put("p_fecha", new java.sql.Date(cliente.getFecha().getTime()));
        parametrosEntrada.put("p_nombre", cliente.getNombre());
        parametrosEntrada.put("p_ap_paterno", cliente.getApPaterno());
        parametrosEntrada.put("p_ap_materno", cliente.getApMaterno());
        parametrosEntrada.put("p_contrasena", cliente.getContrasenha());
        parametrosEntrada.put("p_fecha_venc", new java.sql.Date(cliente.getFechaVencimiento().getTime()));
        parametrosEntrada.put("p_activo", cliente.getActivo());
        parametrosEntrada.put("p_ultimo_logeo", cliente.getUltimoLogueo() != null ? new java.sql.Date(cliente.getUltimoLogueo().getTime()) : null);
        parametrosEntrada.put("p_tipo_doc", cliente.getTipoDocumento().name());
        parametrosEntrada.put("p_documento", cliente.getDocumento());
        parametrosEntrada.put("p_direccion", cliente.getDireccion());
        parametrosEntrada.put("p_telefono", cliente.getTelefono());
        parametrosEntrada.put("p_email", cliente.getEmail());
        parametrosEntrada.put("p_tipo_cliente", cliente.getTipoCliente());
        parametrosEntrada.put("p_ranking", cliente.getRanking());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarCliente", parametrosEntrada, parametrosSalida);
        
        return resultado>0;
    }

    @Override
    public boolean eliminar(String id) {
        
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_cliente", id);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarCliente", parametrosEntrada, parametrosSalida);
        return resultado>0;
    
    }
    
    @Override
    public int validarEmail(String email){
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
            parametrosEntrada.put("p_email", email);
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerClientePorEmail", parametrosEntrada);
        
        try {
            if(rs.next()){
                int resultado = rs.getInt("codigo_cliente");
                return resultado;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClienteMySQL.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
    
    @Override
    public boolean cambiarContra(int codcli, String contra){
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codcli", codcli);
        parametrosEntrada.put("p_contra", contra);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("CambiarContra", parametrosEntrada, parametrosSalida);
        return resultado!=0;
    }
    

    @Override
    public Cliente obtenerPorId(int id) {
        Cliente cli = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_codigo_cliente", id);
        
        ResultSet rs = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerClientePorId", parametrosEntrada);
        
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
                cli = new Cliente(
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
                        rs.getInt("codigo_cliente"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("tipo_cliente"),
                        rs.getDouble("ranking")
                );
            } else {
                System.out.println("No se encontró el cliente");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } 
        return cli;
    }

    @Override
    public Cliente obtenerPorDocIdentidad(String docIden, String tipoDocIden) {
        Cliente cli = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_doc_iden_cliente", docIden);
        parametrosEntrada.put("p_tipo_doc_iden", tipoDocIden);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        ResultSet rs = null;

        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerClientePorDocIdentidad", parametrosEntrada);
        
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
                cli = new Cliente(
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
                        rs.getInt("codigo_cliente"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("tipo_cliente"),
                        rs.getDouble("ranking")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cli;
    }

    @Override
    public List<Cliente> listarPorRanking(double rankini, double rankfin) {
        List<Cliente> listaClientes = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("ranking_inicio",rankini);
        parametrosEntrada.put("ranking_fin",rankfin);
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarClientesPorRanking", parametrosEntrada);
        
        try {
            while (rs.next()) {
                // Crea un nuevo objeto Cliente y llena sus datos

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
                Cliente cliente = new Cliente(
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
                        rs.getInt("codigo_cliente"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("tipo_cliente"),
                        rs.getDouble("ranking")
                );

                listaClientes.add(cliente); // Añade el cliente a la lista
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaClientes;
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> listaClientes = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarClientes", parametrosEntrada);
        
        try {
            while (rs.next()) {
                // Crea un nuevo objeto Cliente y llena sus datos

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
                Cliente cliente = new Cliente(
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
                        rs.getInt("codigo_cliente"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("tipo_cliente"),
                        rs.getDouble("ranking")
                );

                listaClientes.add(cliente); // Añade el cliente a la lista
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return listaClientes;
    }
}
