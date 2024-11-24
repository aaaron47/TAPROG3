package pe.edu.pucp.creditomovil.getscredito.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pe.edu.pucp.creditomovil.conexion.DBManager;
import pe.edu.pucp.creditomovil.getscredito.dao.MetodoPagoDAO;
import pe.edu.pucp.creditomovil.model.MetodoPago;
import pe.edu.pucp.creditomovil.model.MetodoPagoInstancia;

public class MetodoPagoMySQL implements MetodoPagoDAO {

    private Connection conexion;
    private ResultSet rs;

    @Override
    public boolean insertar(MetodoPago metodoPago) {

        CallableStatement cs;
        String query = "{CALL InsertarMetodoPago(?,?,?)}";
        boolean resultado = false;

        try {
            conexion = DBManager.getInstance().getConnection();
            cs = conexion.prepareCall(query);

            cs.setInt(1, metodoPago.getIdMetodoPago());
            cs.setString(2, metodoPago.getNombreTitular());
            cs.setBytes(3, metodoPago.getFoto());

            resultado = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public boolean modificar(MetodoPago metodoPago) {
        CallableStatement cs;
        String query = "{CALL ModificarMetodoPago(?,?,?)}";
        boolean resultado = false;
        
        try {
            
            conexion = DBManager.getInstance().getConnection();
            cs = conexion.prepareCall(query);
            cs.setInt(1, metodoPago.getIdMetodoPago());
            cs.setBytes(2, metodoPago.getFoto());
            cs.setString(3, metodoPago.getNombreTitular());
            
            resultado = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public boolean eliminar(int idMetodoPago) {
        CallableStatement cs;
        String query = "{CALL EliminarMetodoPago(?)}";
        boolean resultado = false;

        try {
            conexion = DBManager.getInstance().getConnection();
            cs = conexion.prepareCall(query);
            cs.setInt(1, idMetodoPago);

            resultado = cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultado;
    }

    @Override
    public MetodoPagoInstancia obtenerPorId(int idMetodoPago) {
        
        MetodoPagoInstancia metodoPago = null;
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;

        try {
            conn = DBManager.getInstance().getConnection();
            String sql = "{ CALL ObtenerMetodoPagoPorId(?) }";
            cs = conn.prepareCall(sql);
            cs.setInt(1, idMetodoPago);
            rs = cs.executeQuery();
            
            
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
        return metodoPago;
    }

    @Override
    public List<MetodoPagoInstancia> listarTodos() {
        List<MetodoPagoInstancia> metodosPago = new ArrayList<>();
        CallableStatement cs = null;
        String query = "{CALL ListarMetodosPago()}";
        rs = null;
        try{
            
            conexion = DBManager.getInstance().getConnection();
            cs = conexion.prepareCall(query);            
            rs = cs.executeQuery();
            
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
        } finally{
            try{
                if(rs != null) rs.close();
                if(cs != null) cs.close();
                if(conexion!=null) conexion.close();
            } catch(SQLException e){
                e.printStackTrace();
            }
        }
        return metodosPago;
    }

}
