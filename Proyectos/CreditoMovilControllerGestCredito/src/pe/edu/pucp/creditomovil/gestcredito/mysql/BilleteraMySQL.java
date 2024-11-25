package pe.edu.pucp.creditomovil.gestcredito.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.Types;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.gestcredito.dao.BilleteraDAO;
import pe.edu.pucp.creditomovil.model.Billetera;

public class BilleteraMySQL implements BilleteraDAO{
    
    private Connection conexion;
    private ResultSet rs;
    
    @Override
    public boolean insertar(Billetera billetera) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_foto", billetera.getFoto());
        parametrosEntrada.put("p_nombreTitular", billetera.getNombreTitular());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_idMetodoPago", 3);

        int metodoPagoId = DBManager.getInstance().ejecutarProcedimiento("InsertarMetodoPago", parametrosEntrada, parametrosSalida);
        billetera.setIdMetodoPago(metodoPagoId); // Asignar el ID al objeto cliente
        
        parametrosEntrada = new HashMap<>();
        parametrosSalida = new HashMap<>();

        parametrosEntrada.put("p_idMetodoPago", metodoPagoId);
        parametrosEntrada.put("p_numeroTelefono", billetera.getNumeroTelefono());
        parametrosEntrada.put("p_nombreBilletera", billetera.getNombreBilletera());
        
        int result = DBManager.getInstance().ejecutarProcedimiento("InsertarBilletera", parametrosEntrada, parametrosSalida);
        
        return result > 0;
    }

    @Override
    public boolean modificar(Billetera billetera) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", billetera.getIdMetodoPago());
        parametrosEntrada.put("p_foto", billetera.getFoto());
        parametrosEntrada.put("p_nombreTitular", billetera.getNombreTitular());
        parametrosEntrada.put("p_numeroTelefono", billetera.getNumeroTelefono());
        parametrosEntrada.put("p_nombreBilletera", billetera.getNombreBilletera());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        int result = DBManager.getInstance().ejecutarProcedimiento("ModificarBilletera", parametrosEntrada, parametrosSalida);
        return result>0;
    }
    
    @Override
    public boolean eliminar(int idMetodoPago) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", idMetodoPago);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        int result = DBManager.getInstance().ejecutarProcedimiento("EliminarBilletera", parametrosEntrada, parametrosSalida);
        
        return result>0;
    }

    @Override
    public Billetera obtenerPorId(int idMetodoPago) {
        
        Billetera bill = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", idMetodoPago);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        ResultSet rs = null;
        
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerBilleteraPorId", parametrosEntrada);
        try {
            if(rs.next()){
                bill = new Billetera(
                    rs.getInt("idMetodoPago"),
                    rs.getBytes("foto"),
                    rs.getString("nombreTitular"),
                    rs.getString("numeroTelefono"),
                    rs.getString("nombreBilletera")
                );
            }else{
                System.out.println("No se encontró el cliente");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
    
    @Override
    public Billetera obtenerPorNombre(String nombre) {
        
        Billetera bill = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_nombreBilletera", nombre);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        ResultSet rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerBilleteraPorNombre", parametrosEntrada);
        try {
            if(rs.next()){
                bill = new Billetera(
                    rs.getInt("idMetodoPago"),
                    rs.getBytes("foto"),
                    rs.getString("nombreTitular"),
                    rs.getString("numeroTelefono"),
                    rs.getString("nombreBilletera")
                );
            }else{
                System.out.println("No se encontró el cliente");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bill;
    }
    
    @Override
    public List<Billetera> listarTodos() {
        List<Billetera> billeteras = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();

        rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarBilleteras", parametrosEntrada);
        try{
            while(rs.next()){
                Billetera billetera = new Billetera(
                        
                        rs.getInt("idMetodoPago"),
                        rs.getBytes("foto"),
                        rs.getString("nombreTitular"),
                        rs.getString("numeroTelefono"),
                        rs.getString("nombreBilletera")
                        
                );
                billeteras.add(billetera);
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return billeteras;
    }
    
}
