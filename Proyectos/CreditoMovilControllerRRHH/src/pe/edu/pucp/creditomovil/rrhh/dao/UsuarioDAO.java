/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.creditomovil.rrhh.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.Usuario;
import pe.edu.pucp.creditomovil.model.UsuarioInstancia;

/**
 *
 * @author diego
 */
public interface UsuarioDAO {
    void insertar(Usuario usuario);
    void modificar(int id,Usuario usuario);
    void eliminar(int id);
    Usuario obtenerPorId(int id);
    UsuarioInstancia obtenerPorDocIdentidad(String docIden, String tipoDocIden);
    List<Usuario> listarTodos();
}
