package pe.edu.pucp.creditomovil.gestcredito.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.MetodoPago;
import pe.edu.pucp.creditomovil.model.MetodoPagoInstancia;

public interface MetodoPagoDAO {
    boolean insertar(MetodoPago metodoPago);
    boolean modificar(MetodoPago metodoPago);
    boolean eliminar(int idMetodoPago);
    MetodoPagoInstancia obtenerPorId(int idMetodoPago);
    List<MetodoPagoInstancia> listarTodos();
}
