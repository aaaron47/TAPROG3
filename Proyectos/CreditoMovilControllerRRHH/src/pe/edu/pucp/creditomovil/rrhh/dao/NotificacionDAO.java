/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pe.edu.pucp.creditomovil.rrhh.dao;

import java.util.List;
import pe.edu.pucp.creditomovil.model.Notificacion;

/**
 *
 * @author aaron
 */
public interface NotificacionDAO {
    void insertar(Notificacion notificacion);
    void modificar(Notificacion notificacion);
    List<Notificacion> listarPorUsuario(int idUsuario);
}
