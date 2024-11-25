package pe.edu.pucp.creditomovil.gestcredito.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.gestcredito.dao.BancoDAO;
import pe.edu.pucp.creditomovil.model.Banco;

public class BancoMySQL implements BancoDAO{
    private Connection conexion;
    private ResultSet rs;

    @Override
    public boolean insertar(Banco banco) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_foto", banco.getFoto());
        parametrosEntrada.put("p_nombreTitular", banco.getNombreTitular());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_idMetodoPago", Types.INTEGER);

        int res = DBManager.getInstance().ejecutarProcedimiento("InsertarMetodoPago", parametrosEntrada, parametrosSalida);
        int metodoPagoId = (int) parametrosSalida.get("p_idMetodoPago");
        banco.setIdMetodoPago(metodoPagoId); // Asignar el ID al objeto cliente
        
        parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", metodoPagoId);
        parametrosEntrada.put("p_CCI", banco.getCCI());
        parametrosEntrada.put("p_tipoCuenta", banco.getTipoCuenta());
        parametrosEntrada.put("p_nombreBanco", banco.getNombreBanco());
        
        parametrosSalida = new HashMap<>();
        
        int result = DBManager.getInstance().ejecutarProcedimiento("InsertarBanco", parametrosEntrada, parametrosSalida);
        
        return (metodoPagoId != -1 && result != 0);
    }

    @Override
    public boolean modificar(Banco banco) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", banco.getIdMetodoPago());
        parametrosEntrada.put("p_foto", banco.getFoto());
        parametrosEntrada.put("p_nombreTitular", banco.getNombreTitular());
        parametrosEntrada.put("p_CCI", banco.getCCI());
        parametrosEntrada.put("p_tipoCuenta", banco.getTipoCuenta());
        parametrosEntrada.put("p_nombreCuenta", banco.getNombreBanco());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarBanco", parametrosEntrada, parametrosSalida);
        
        return resultado>0;
    }

    @Override
    public boolean eliminar(int idMetodoPago) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", idMetodoPago);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarBanco", parametrosEntrada, parametrosSalida);
        return resultado>0;
    }

    @Override
    public Banco obtenerPorId(int idMetodoPago) {
        Banco bank = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", idMetodoPago);
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerBancoPorID", parametrosEntrada);
        try {
            if(rs.next()){
                bank = new Banco(
                    rs.getInt("idMetodoPago"),
                    rs.getBytes("foto"),
                    rs.getString("nombreTitular"),
                    rs.getString("CCI"),
                    rs.getString("tipoCuenta"),
                    rs.getString("nombreBanco")//ACA NUEVO//ACA NUEVO//ACA NUEVO
                );
            }else{
                System.out.println("No se encontró el cliente");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank;
    }

    @Override
    public Banco obtenerPorNombre(String nombre) {
        Banco bank = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_nombreBanco", nombre);
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerBancoPorNombre", parametrosEntrada);
        try {
            if(rs.next()){
                bank = new Banco(
                    rs.getInt("idMetodoPago"),
                    rs.getBytes("foto"),
                    rs.getString("nombreTitular"),
                    rs.getString("CCI"),
                    rs.getString("tipoCuenta"),
                    rs.getString("nombreBanco")//ACA NUEVO//ACA NUEVO//ACA NUEVO
                );
            }else{
                System.out.println("No se encontró el cliente");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bank;
    }
    
    
    @Override
    public List<Banco> listarTodos() {
        List<Banco> listaBancos = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarBancos", parametrosEntrada);
        try {
            while(rs.next()){
                Banco bank = new Banco(
                    rs.getInt("idMetodoPago"),
                    rs.getBytes("foto"),
                    rs.getString("nombreTitular"),
                    rs.getString("CCI"),
                    rs.getString("tipoCuenta"),
                    rs.getString("nombreBanco")//ACA NUEVO//ACA NUEVO//ACA NUEVO
                );
                
                listaBancos.add(bank);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaBancos;
    }
}
