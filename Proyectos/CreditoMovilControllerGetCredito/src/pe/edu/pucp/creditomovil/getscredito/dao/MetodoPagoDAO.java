package pe.edu.pucp.creditomovil.getscredito.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.MetodoPago;
import pe.edu.pucp.creditomovil.model.MetodoPago2;

public interface MetodoPagoDAO {
    boolean insertar(MetodoPago metodoPago);
    boolean modificar(MetodoPago metodoPago);
    boolean eliminar(int idMetodoPago);
    MetodoPago2 obtenerPorId(int idMetodoPago);
    List<MetodoPago2> listarTodos();
}
