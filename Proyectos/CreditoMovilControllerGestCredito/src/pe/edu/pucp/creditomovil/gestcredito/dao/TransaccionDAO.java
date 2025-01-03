/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.creditomovil.gestcredito.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.Transaccion;

/**
 *
 * @author diego
 */
public interface TransaccionDAO {
    boolean insertar(Transaccion transaccion, int idUsuario, int idCredito, int idMetodo);
    boolean modificar(Transaccion transaccion);
    boolean eliminar(int numOperacion);
    Transaccion obtenerPorId(int numOperacion);
    List<Transaccion> listarPorCredito(int numCred);
    List<Transaccion> listarTodos();
}
