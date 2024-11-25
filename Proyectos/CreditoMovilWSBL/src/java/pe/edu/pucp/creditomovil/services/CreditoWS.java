/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.services;

import jakarta.jws.WebService;
import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import pe.edu.pucp.creditomovil.conexion.DBManager;

import pe.edu.pucp.creditomovil.gestclientes.dao.CreditoDAO;
import pe.edu.pucp.creditomovil.gestclientes.mysql.CreditoMySQL;
import pe.edu.pucp.creditomovil.model.Credito;

@WebService(serviceName = "CreditoWS", targetNamespace
        = "https://services.creditomovil.pucp.edu.pe")
public class CreditoWS {

    private CreditoDAO daoCredito = new CreditoMySQL();

    @WebMethod(operationName = "insertarCredito")
    public void insertarCredito(@WebParam(name = "credito") Credito credito, 
            @WebParam(name = "docCliente") String doc, @WebParam(name = "tipo_doc") String tipo_doc) {
        try{
            daoCredito.insertar(credito,doc,tipo_doc);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }

    }
    
    @WebMethod(operationName = "modificarCredito")
    public void modificarCredito(@WebParam(name = "credito") Credito credito) {
        try{
            daoCredito.modificar(credito);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "eliminarCredito")
    public void eliminarCredito(@WebParam(name = "credito") String id) {
        try{
            daoCredito.eliminar(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }
    
    @WebMethod(operationName = "obtenerPorIDCredito")
    public Credito obtenerPorIDCredito(@WebParam(name = "credito") int id) {
        Credito credito = null;
        try{
            credito = daoCredito.obtenerPorId(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return credito;
    }
    
    @WebMethod(operationName = "listarCreditosFiltro")
    public List<Credito> listarCreditosFiltro(@WebParam(name = "idcli") int idcli,
            @WebParam(name = "fechaini") Date fechaini, @WebParam(name = "fechafin") Date fechafin,
            @WebParam(name = "estado") String estado) {
        List<Credito> creditos = null;
        try{
            creditos = daoCredito.listarTodosFiltros(idcli, fechaini, fechafin, estado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return creditos;
    }
    
    @WebMethod(operationName = "listarCreditosSinCliFiltro")
    public List<Credito> listarCreditosSinCliFiltro(
            @WebParam(name = "fechaini") Date fechaini, @WebParam(name = "fechafin") Date fechafin,
            @WebParam(name = "estado") String estado) {
        List<Credito> creditos = null;
        try{
            creditos = daoCredito.listarTodosSinCliFiltros(fechaini, fechafin, estado);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return creditos;
    }
    
    @WebMethod(operationName = "listarCreditosCliente")
    public List<Credito> listarCreditosCliente(@WebParam(name = "idcli") int idcli) {
        List<Credito> creditos = null;
        try{
            creditos = daoCredito.listarCreditosPorCliente(idcli);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return creditos;
    }
    
    @WebMethod(operationName = "listarTodosCreditos")
    public List<Credito> listarTodosCreditos() {
        List<Credito> creditos = null;
        try{
            creditos = daoCredito.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return creditos;
    }

    @WebMethod(operationName = "generarReporteCreditos")
    public byte[] reporteCreditos(@WebParam (name = "fechainicio") Date fechaini,
            @WebParam(name = "fechafin") Date fechafin,
            @WebParam (name = "estadocred") String estado) throws Exception{
        try {
            Map<String, Object> params = new HashMap<>();        
            
            java.sql.Date fechainiSQL = new java.sql.Date(fechaini.getTime());
            java.sql.Date fechafinSQL = new java.sql.Date(fechafin.getTime());
            
            params.put("fechaini",fechainiSQL);
            params.put("fechafin",fechafinSQL);
            
            
            params.put("logo", ImageIO.read(new File(getFileResource2("logo.png"))));
            
            //obtiene imagen desde pe.edu.pucp.creditomovil.img
            
            params.put("BordeSup",ImageIO.read(new File(getFileResource2("bordesupp.png"))));
            params.put("estado",estado);
            return generarBuffer(getFileResource("ReporteCreditos.jrxml"), params);
            
        } catch(Exception ex){
            Logger.getLogger(CreditoWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    private String getFileResource(String fileName){ 
        String filePath = ClienteWS.class.getResource("/pe/edu/pucp/creditomovil/reportes/"+fileName).getPath();
        filePath = filePath.replace("%20", " ");
        return filePath;
    }
    
    private String getFileResource2(String fileName){ 
        String filePath = ClienteWS.class.getResource("/pe/edu/pucp/creditomovil/img/"+fileName).getPath();
        filePath = filePath.replace("%20", " ");
        return filePath;
    }
    
    public byte[] generarBuffer(String inFileXML, Map<String, Object> params) throws Exception{
        //Se compila una sola vez
        String fileJasper = inFileXML +".jasper";
        if(!new File(fileJasper).exists()){
            //para compilar en GlassFish se requiere las librerias: jasperreports-jdt, ecj
            JasperCompileManager.compileReportToFile(inFileXML, fileJasper);         
        }
        //1- leer el archivo compilado
        JasperReport jr = (JasperReport) JRLoader.loadObjectFromFile(fileJasper);
        //2- poblar el reporte
        Connection conn = DBManager.getInstance().getConnection();
        JasperPrint jp = JasperFillManager.fillReport(jr,params, conn);          
        return JasperExportManager.exportReportToPdf(jp);
    }
    
    @WebMethod(operationName = "obtenerIdClientePorCredito")
    public int obtenerIdClientePorCredito(@WebParam(name = "idCredito") int idCredito) {
        int idCliente = -1;
        try{
            idCliente = daoCredito.obtenerIdClientePorCredito(idCredito);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return idCliente;
    }
}
