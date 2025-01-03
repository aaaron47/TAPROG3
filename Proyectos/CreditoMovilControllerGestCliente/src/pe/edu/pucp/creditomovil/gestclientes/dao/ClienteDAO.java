/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.creditomovil.gestclientes.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.Cliente;

/**
 *
 * @author Bleak
 */
public interface ClienteDAO {
    boolean insertar(Cliente usuario);
    boolean modificar(Cliente usuario);
    boolean eliminar(String id);
    int validarEmail(String email);
    boolean cambiarContra(int codcli, String contra);
    Cliente obtenerPorId(int id);
    Cliente obtenerPorDocIdentidad(String docIden, String tipoDocIden);
    public List<Cliente> listarPorRanking(double rankini, double rankfin);
    List<Cliente> listarTodos();
}
