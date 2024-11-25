package pe.edu.pucp.creditomovil.gestcredito.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.gestcredito.dao.MetodoPagoDAO;
import pe.edu.pucp.creditomovil.model.MetodoPago;
import pe.edu.pucp.creditomovil.model.MetodoPagoInstancia;

public class MetodoPagoMySQL implements MetodoPagoDAO {

    private Connection conexion;
    private ResultSet rs;

    @Override
    public boolean insertar(MetodoPago metodoPago) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_foto", metodoPago.getFoto());
        parametrosEntrada.put("p_nombreTitular", metodoPago.getNombreTitular());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        parametrosSalida.put("p_idMetodoPago", 3);

        int metodoPagoId = DBManager.getInstance().ejecutarProcedimiento("InsertarMetodoPago", parametrosEntrada, parametrosSalida);
        return metodoPagoId > 0;
    }

    @Override
    public boolean modificar(MetodoPago metodoPago) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", metodoPago.getIdMetodoPago());
        parametrosEntrada.put("p_foto", metodoPago.getFoto());
        parametrosEntrada.put("p_nombreTitular", metodoPago.getNombreTitular());
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        
        int resultado = DBManager.getInstance().ejecutarProcedimiento("ModificarMetodoPago", parametrosEntrada, parametrosSalida);
        
        return resultado > 0;
    }

    @Override
    public boolean eliminar(int idMetodoPago) {
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", idMetodoPago);
        
        HashMap<String, Object> parametrosSalida = new HashMap<>();
        int resultado = DBManager.getInstance().ejecutarProcedimiento("EliminarMetodoPago", parametrosEntrada, parametrosSalida);
        return resultado > 0;
    }

    @Override
    public MetodoPagoInstancia obtenerPorId(int idMetodoPago) {
        
        MetodoPagoInstancia metodoPago = null;
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        
        parametrosEntrada.put("p_idMetodoPago", idMetodoPago);
        ResultSet rs = null;
        
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ObtenerMetodoPagoPorId", parametrosEntrada);

        try {
            if(rs.next()){
                metodoPago = new MetodoPagoInstancia(
                    rs.getInt("idMetodoPago"),
                    rs.getBytes("foto"),
                    rs.getString("nombreTitular")
                );
            }else{
                System.out.println("No se encontró el cliente");
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return metodoPago;
    }

    @Override
    public List<MetodoPagoInstancia> listarTodos() {
        List<MetodoPagoInstancia> metodosPago = new ArrayList<>();
        HashMap<String, Object> parametrosEntrada = new HashMap<>();
        rs = null;
        rs = DBManager.getInstance().ejecutarProcedimientoLectura("ListarMetodosPago", parametrosEntrada);
        try{
            while(rs.next()){
                MetodoPagoInstancia metodo = new MetodoPagoInstancia(
                        
                        rs.getInt("idMetodoPago"),
                        rs.getBytes("foto"),
                        rs.getString("nombreTitular")
                        
                );
                metodosPago.add(metodo);
            }
            
        } catch (SQLException e){
            e.printStackTrace();
        }
        return metodosPago;
    }

}
