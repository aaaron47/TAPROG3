/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.creditomovil.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;

import java.util.List;
import pe.edu.pucp.creditomovil.model.UsuarioInstancia;

import pe.edu.pucp.creditomovil.rrhh.dao.UsuarioDAO;
import pe.edu.pucp.creditomovil.rrhh.mysql.UsuarioMySQL;

@WebService(serviceName = "UsuarioWS")
public class UsuarioWS {

    private UsuarioDAO daoUsuario = new UsuarioMySQL();
    
    @WebMethod(operationName = "obtenerPorDocIdenUsuario")
    public UsuarioInstancia obtenerPorDocIdenUsuario(@WebParam(name = "docIdentidad") String docIdentidad,
                                                @WebParam(name = "tipoDocumento")String tipoDocumento) {
        UsuarioInstancia user = null;
        try{
            user = daoUsuario.obtenerPorDocIdentidad(docIdentidad, tipoDocumento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return user;
    }
}