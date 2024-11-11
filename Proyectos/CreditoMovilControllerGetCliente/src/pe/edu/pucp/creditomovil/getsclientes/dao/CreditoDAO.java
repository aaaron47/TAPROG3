/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.creditomovil.getsclientes.dao;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.creditomovil.model.Credito;

/**
 *
 * @author diego
 */
public interface CreditoDAO {
    void insertar(Credito credito, String codigoCliente, String tipodocCli);
    void modificar(Credito credito);
    void eliminar(String numCredito);
    Credito obtenerPorId(String numCredito);
    public List<Credito> listarTodosFiltros(int cli, Date fechaini, Date fechafin, String estado);
    public List<Credito> listarTodos(); 
}
