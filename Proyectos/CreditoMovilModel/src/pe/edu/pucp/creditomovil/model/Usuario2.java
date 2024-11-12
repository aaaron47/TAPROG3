/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.model;

import java.util.Date;

/**
 *
 * @author aaron
 */
public class Usuario2 extends Usuario {

    public Usuario2(){}
    public Usuario2(int idUsuario, Date fecha, String nombre, String apPaterno,
            String apMaterno, String contrasenha, Date fechaVencimiento,
            boolean activo, TipoDocumento tipoDocumento, String documento, Rol rol) {
        super(idUsuario, fecha, nombre, apPaterno, apMaterno, contrasenha, fechaVencimiento, activo, tipoDocumento, 
                documento, rol);
    }
}
