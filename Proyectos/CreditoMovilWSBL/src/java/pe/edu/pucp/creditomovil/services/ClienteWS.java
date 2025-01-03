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
import java.util.HashMap;

import java.util.List;
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

import pe.edu.pucp.creditomovil.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.creditomovil.gestclientes.mysql.ClienteMySQL;
import pe.edu.pucp.creditomovil.model.Cliente;

@WebService(serviceName = "ClienteWS", targetNamespace
        = "https://services.creditomovil.pucp.edu.pe")
public class ClienteWS {

    private ClienteDAO daoCliente = new ClienteMySQL();
    
    @WebMethod(operationName = "insertarCliente")
    public boolean insertarCliente(@WebParam(name = "cliente") Cliente cliente) {
        boolean resultado = false;
        try{
            resultado = daoCliente.insertar(cliente);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    
    @WebMethod(operationName = "modificarCliente")
    public boolean modificarCliente(@WebParam(name = "cliente") Cliente cliente) {
        boolean resultado = false;
        try{
            resultado = daoCliente.modificar(cliente);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "eliminarCliente")
    public boolean eliminarCliente(@WebParam(name = "idcliente") String id) {
        boolean resultado = false;
        try{
            resultado = daoCliente.eliminar(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "obtenerPorIDCliente")
    public Cliente obtenerPorIDCliente(@WebParam(name = "idcliente") int id) {
        Cliente cliente = null;
        try{
            cliente = daoCliente.obtenerPorId(id);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cliente;
    }
    
    @WebMethod(operationName = "obtenerPorDocIdenCliente")
    public Cliente obtenerPorDocIdenCliente(@WebParam(name = "docIdentidad") String docIdentidad,
                                                @WebParam(name = "tipoDocumento")String tipoDocumento) {
        Cliente cliente = null;
        try{
            cliente = daoCliente.obtenerPorDocIdentidad(docIdentidad,tipoDocumento);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return cliente;
    }
    
    @WebMethod(operationName = "validarEmail")
    public int validarCorreo(@WebParam (name = "correo") String email){
        int resultado = -1;
        try{
            resultado = daoCliente.validarEmail(email);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "cambiarContra")
    public boolean cambiarContra(@WebParam (name = "codigocli") int codCli,
            @WebParam (name = "contra") String contra){
        boolean resultado = false;
        try{
            resultado = daoCliente.cambiarContra(codCli, contra);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return resultado;
    }
    
    @WebMethod(operationName = "listarClientesPorRanking")
    public List<Cliente> listarClientesPorRanking(@WebParam(name = "rankini") double rankini,
                                                @WebParam(name = "rankfin") double rankfin) {
        List<Cliente> clientes = null;
        try{
            clientes = daoCliente.listarPorRanking(rankini, rankfin);
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
    
    @WebMethod(operationName = "listarTodosClientes")
    public List<Cliente> listarTodosClientes() {
        List<Cliente> clientes = null;
        try{
            clientes = daoCliente.listarTodos();
        }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
        return clientes;
    }
    
        @WebMethod(operationName = "generarReporte")
    public byte [] reporteClientes(@WebParam (name = "idcliente") int idcli) throws Exception{
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("codCli", idcli);
            
            //obtiene imagen desde pe.edu.pucp.creditomovil.img
            params.put("logo", ImageIO.read(new File(getFileResource2("logo.png"))));
            
            //obtiene imagen desde pe.edu.pucp.creditomovil.img

            params.put("BordeSup",ImageIO.read(new File(getFileResource2("bordesupp.png"))));
            
            URL rutaSubReporte = ClienteWS.class.getResource("/pe/edu/pucp/creditomovil/reportes/ListarCreditos.jasper");
            String rutaArchivoSubReporte = URLDecoder.decode(rutaSubReporte.getPath(), "UTF-8");
            params.put("rutaSubReporte", rutaArchivoSubReporte);
            
            return generarBuffer(getFileResource("ReporteCliente.jrxml"), params);
            
            
        } catch (Exception ex){
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @WebMethod(operationName = "generarDesembolso")
    public byte [] reporteDesembolso(@WebParam (name = "idcliente") int idcli, 
            @WebParam (name = "idcredito") int idcred) throws Exception{
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("codCli", idcli);
            params.put("codCred", idcred);
            
            //obtiene imagen desde pe.edu.pucp.creditomovil.img
            params.put("logo", ImageIO.read(new File(getFileResource2("logo.png"))));
            
            //obtiene imagen desde pe.edu.pucp.creditomovil.img

            params.put("BordeSup",ImageIO.read(new File(getFileResource2("bordesupp.png"))));
            
            return generarBuffer(getFileResource("OrdenDesembolso.jrxml"), params);
            
            
        } catch (Exception ex){
            Logger.getLogger(ClienteWS.class.getName()).log(Level.SEVERE, null, ex);
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
}
