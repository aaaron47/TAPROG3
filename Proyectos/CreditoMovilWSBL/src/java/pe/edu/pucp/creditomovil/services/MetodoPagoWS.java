/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/WebService.java to edit this template
 */
package pe.edu.pucp.creditomovil.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.util.Date;

import java.util.List;

import pe.edu.pucp.creditomovil.getscredito.dao.MetodoPagoDAO;
import pe.edu.pucp.creditomovil.getscredito.mysql.MetodoPagoMySQL;
import pe.edu.pucp.creditomovil.model.MetodoPago;
import pe.edu.pucp.creditomovil.model.MetodoPago2;
import pe.edu.pucp.creditomovil.model.TipoDocumento;

/**
 *
 * @author Bleak
 */
@WebService(serviceName = "MetodoPagoWS")
public class MetodoPagoWS {


    private MetodoPagoDAO daoMetodoPago = new MetodoPagoMySQL();
    
    @WebMethod(operationName = "insertarMetodoPago")
    public boolean insertarMetodoPago(@WebParam(name = "metodoPago") MetodoPago metodoPago) {
        boolean resultado = false;
        try{
            resultado = daoMetodoPago.insertar(metodoPago);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
   
    
    @WebMethod(operationName = "modificarMetodoPago")
    public boolean modificarMetodoPago(@WebParam(name = "metodoPago") MetodoPago metodoPago) {
        boolean resultado = false;
        try{
            resultado = daoMetodoPago.modificar(metodoPago);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarMetodoPago")
    public boolean eliminarMetodoPago(@WebParam(name = "idmetodoPago") int id) {
        boolean resultado = false;
        try{
            resultado = daoMetodoPago.eliminar(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "obtenerPorIDMetodoPago")
    public MetodoPago2 obtenerPorIDMetodoPago(@WebParam(name = "idmetodoPago") int id) {
        MetodoPago2 metodoPago = null;
        try{
            metodoPago = daoMetodoPago.obtenerPorId(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return metodoPago;
    }
    
    @WebMethod(operationName = "listarTodosMetodoPagos")
    public List<MetodoPago2> listarTodosMetodoPagos() {
        List<MetodoPago2> metodoPagos = null;
        try{
            metodoPagos = daoMetodoPago.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return metodoPagos;
    }
}
