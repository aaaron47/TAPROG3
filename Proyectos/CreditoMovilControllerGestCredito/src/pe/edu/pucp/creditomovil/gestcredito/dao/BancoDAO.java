package pe.edu.pucp.creditomovil.gestcredito.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.Banco;

public interface BancoDAO {
    boolean insertar(Banco banco);
    boolean modificar(Banco banco);
    boolean eliminar(int idMetodoPago);
    Banco obtenerPorId(int idMetodoPago);
    Banco obtenerPorNombre(String nombre);
    List<Banco> listarTodos();
}
