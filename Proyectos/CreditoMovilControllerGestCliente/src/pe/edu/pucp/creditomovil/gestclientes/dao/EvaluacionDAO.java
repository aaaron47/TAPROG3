/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.creditomovil.gestclientes.dao;

import java.util.Date;
import java.util.List;
import pe.edu.pucp.creditomovil.model.Evaluacion;

/**
 *
 * @author diego
 */
public interface EvaluacionDAO {
    boolean insertar(Evaluacion evaluacion, String codigoSupervisor, int codigoCliente);
    void modificar(Evaluacion evaluacion);
    void eliminar(int idEvaluacion);
    Evaluacion obtenerPorId(int idEvaluacion);
    List<Evaluacion>listarPorSupervisor(String codSup);
    List<Evaluacion> listarTodos();
    public List<Evaluacion> listarPorFechas(Date fechaini, Date fechafin);
}
