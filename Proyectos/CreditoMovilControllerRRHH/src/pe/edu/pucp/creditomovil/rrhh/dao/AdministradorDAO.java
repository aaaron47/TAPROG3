/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.creditomovil.rrhh.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.Administrador;
/**
 *
 * @author Bleak
 */
public interface AdministradorDAO {
    void insertar(Administrador administrador);
    void modificar(int id, Administrador administrador);
    void eliminar(String id);
    Administrador obtenerPorId(String id);
    public Administrador obtenerPorDocIdentidad(String docIden, String tipoDocIden);
    List<Administrador> listarTodos();
}
