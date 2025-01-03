/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pe.edu.pucp.creditomovil.program.main;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import pe.edu.pucp.creditomovil.model.Cliente;
import pe.edu.pucp.creditomovil.model.Credito;
import pe.edu.pucp.creditomovil.model.Evaluacion;
import pe.edu.pucp.creditomovil.gestclientes.mysql.ClienteMySQL;
import pe.edu.pucp.creditomovil.gestclientes.dao.ClienteDAO;
import pe.edu.pucp.creditomovil.gestclientes.dao.CreditoDAO;
import pe.edu.pucp.creditomovil.gestclientes.dao.EvaluacionDAO;
import pe.edu.pucp.creditomovil.gestclientes.mysql.CreditoMySQL;
import pe.edu.pucp.creditomovil.gestclientes.mysql.EvaluacionMySQL;
import pe.edu.pucp.creditomovil.gestcredito.dao.BancoDAO;
import pe.edu.pucp.creditomovil.gestcredito.dao.BilleteraDAO;
import pe.edu.pucp.creditomovil.gestcredito.dao.TransaccionDAO;
import pe.edu.pucp.creditomovil.gestcredito.mysql.BancoMySQL;
import pe.edu.pucp.creditomovil.gestcredito.mysql.BilleteraMySQL;
import pe.edu.pucp.creditomovil.model.Transaccion;
import pe.edu.pucp.creditomovil.gestcredito.mysql.TransaccionMySQL;
import pe.edu.pucp.creditomovil.rrhh.dao.AdministradorDAO;
import pe.edu.pucp.creditomovil.rrhh.dao.SupervisorDAO;
import pe.edu.pucp.creditomovil.rrhh.dao.UsuarioDAO;
import pe.edu.pucp.creditomovil.model.Administrador;
import pe.edu.pucp.creditomovil.model.Banco;
import pe.edu.pucp.creditomovil.model.Billetera;
import pe.edu.pucp.creditomovil.model.Notificacion;
import pe.edu.pucp.creditomovil.model.Supervisor;
import pe.edu.pucp.creditomovil.model.TipoDocumento;
import pe.edu.pucp.creditomovil.model.Usuario;
import pe.edu.pucp.creditomovil.rrhh.dao.NotificacionDAO;
import pe.edu.pucp.creditomovil.rrhh.mysql.AdministradorMySQL;
import pe.edu.pucp.creditomovil.rrhh.mysql.NotificacionMySQL;
import pe.edu.pucp.creditomovil.rrhh.mysql.SupervisorMySQL;
import pe.edu.pucp.creditomovil.rrhh.mysql.UsuarioMySQL;

/**
 *
 * @author diego
 */
public class Principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TransaccionDAO transaccionDAO = new TransaccionMySQL();
        //Insertar
        Transaccion transaccion = new Transaccion();
        transaccion.setFecha(new Date());
        transaccion.setConcepto("concept");
        transaccion.setMonto(100.00);
        transaccion.setAnulado(false);
        transaccion.setAgencia("agencia");
        transaccion.setFoto(null);
        boolean exito = transaccionDAO.insertar(transaccion,7,1,2);

        if (exito) {
            System.out.println("Transaccion insertada con exito.");
        } else {
            System.out.println("Error al insertar la transaccion.");
        }
//        NotificacionDAO notidao = new NotificacionMySQL();
//        List<Notificacion> notis = notidao.listarPorUsuario(1);
//        for(Notificacion not : notis){
//            System.out.println("Mensaje: "+not.getMensaje());
//        }
//        Cliente cliente = clienteDAO.obtenerPorDocIdentidad("10551128", "DNI");
//        int idCliente = cliente.getCodigoCliente();
//        CreditoDAO creditoDAO = new CreditoMySQL();
//        Credito cred = creditoDAO.obtenerPorId(1);
//        int idCred = cred.getNumCredito();
//        BancoDAO bankdao = new BancoMySQL();
//        Banco bank = bankdao.obtenerPorId(2);
//        int idMetodo = bank.getIdMetodoPago();
//        TransaccionDAO transdao = new TransaccionMySQL();
//        Transaccion transaccion = new Transaccion(
//                new Date(), // Fecha actual
//                "Pago mensual", // Concepto
//                100.0, // Monto
//                false, // Anulado
//                cliente, // Usuario registrado
//                "Agencia Central", // Agencia
//                0, // Número de operación
//                cred, // Crédito asociado
//                null
//        );
//
//        if(transdao.insertar(transaccion,idCliente,idCred,idMetodo)){
//            System.out.println("SI");
//        }
//        
//        AdministradorDAO adminDao = new AdministradorMySQL();
//        
//        Administrador admin = adminDao.obtenerPorDocIdentidad("24422124", "DNI");
//        System.out.println("ID Usuario: " + admin.getIdUsuario());
        
        
//        Cliente cliente = clienteDAO.obtenerPorDocIdentidad("10551128", "DNI");
//        if(cliente != null){
//            System.out.println("ID Usuario: " + cliente.getIdUsuario());
//            System.out.println("Nombre: " + cliente.getNombre());
//            System.out.println("Apellido Paterno: " + cliente.getApPaterno());
//            System.out.println("Apellido Materno: " + cliente.getApMaterno());
//            System.out.println("Codigo Cliente: " + cliente.getCodigoCliente());
//            System.out.println("Direccion: " + cliente.getDireccion());
//            System.out.println("Telefono: " + cliente.getTelefono());
//            System.out.println("Email: " + cliente.getEmail());
//            System.out.println("Tipo Cliente: " + cliente.getTipoCliente());
//            System.out.println("Activo: " + cliente.getActivo());
//            System.out.println("Fecha de Registro: " + cliente.getFecha());
//            System.out.println("ultimo Logueo: " + cliente.getUltimoLogueo());
//            System.out.println("----------------------------------------");
//        }else {
//            System.out.println("No se encuentra sapo");
//        }
//        // Crear la instancia de ClienteDAO
//        ClienteDAO clienteDAO = new ClienteMySQL();
//        SupervisorDAO daoSup = new SupervisorMySQL();
//        List<Supervisor>list =  daoSup.listarTodos();
//        for(Supervisor supervisor:list){
//            System.out.println(supervisor.getNombre());
//            System.out.println(supervisor.getIdUsuario());
//        }
         //Configurar los datos del Cliente de prueba
//        Calendar fechaNacimiento = Calendar.getInstance();
//        fechaNacimiento.set(1990, Calendar.JANUARY, 1); // Fecha de ejemplo
//
//        Calendar fechaVencimiento = Calendar.getInstance();
//        fechaVencimiento.set(2025, Calendar.DECEMBER, 31); // Fecha de vencimiento de ejemplo
//      //INSERTAR CLIENTE (Funciona 3/11)
//        Cliente cliente = new Cliente(
//                0, // idUsuario (se generará automáticamente en la base de datos)
//                fechaNacimiento.getTime(),
//                "Diego",
//                "Silvestre",
//                "González",
//                "gaaaa123Password",
//                fechaVencimiento.getTime(),
//                true,
//                TipoDocumento.DNI,
//                "101024777",
//                "CL183",
//                "1563 Calle Falsa",
//                "123456789",
//                "juan.perez@example.com",
//                "EXCELENTE"
//        );
//
//        // Intentar insertar el cliente en la base de datos
//        boolean exito = clienteDAO.insertar(cliente);
//
//        if (exito) {
//            System.out.println("Cliente insertado exitosamente con ID: " + cliente.getIdUsuario());
//        } else {
//            System.out.println("Error al insertar el cliente.");
//        }
        //eliminar
//        String codigoCliente = "CL123";
//        boolean exito = clienteDAO.eliminar(codigoCliente);
//
//        if (exito) {
//            System.out.println("Cliente eliminado exitosamente.");
//        } else {
//            System.out.println("No se pudo eliminar el cliente. Verifica el codigo.");
//        }
//        //modificar (FUNCIONA 4/11)
//        Cliente cliente = new Cliente(
//            0, new Date(), "NombreActualizado", "ApellidoPaterno", "ApellidoMaterno", 
//            "nuevaContrasena", new Date(), true,TipoDocumento.PASAPORTE,"NUEVODNI", "CL183", "Nueva Direccion", 
//            "987654321", "nuevoemail@example.com", "VIP"
//        );
//
//        boolean exito = clienteDAO.modificar(cliente);
//
//        if (exito) {
//            System.out.println("Cliente actualizado exitosamente.");
//        } else {
//            System.out.println("No se pudo actualizar el cliente.");
//        }
        //listar (Funciona 3/11)
//        List<Cliente> listClientes = clienteDAO.listarTodos();
//
//        for (Cliente cliente : listClientes) {
//            System.out.println("ID Usuario: " + cliente.getIdUsuario());
//            System.out.println("Nombre: " + cliente.getNombre());
//            System.out.println("Apellido Paterno: " + cliente.getApPaterno());
//            System.out.println("Apellido Materno: " + cliente.getApMaterno());
//            System.out.println("Codigo Cliente: " + cliente.getCodigoCliente());
//            System.out.println("Direccion: " + cliente.getDireccion());
//            System.out.println("Telefono: " + cliente.getTelefono());
//            System.out.println("Email: " + cliente.getEmail());
//            System.out.println("Tipo Cliente: " + cliente.getTipoCliente());
//            System.out.println("Activo: " + cliente.getActivo());
//            System.out.println("Fecha de Registro: " + cliente.getFecha());
//            System.out.println("ultimo Logueo: " + cliente.getUltimoLogueo());
//            System.out.println("----------------------------------------");
//        }
//        //CREDITO
//        CreditoDAO creditoDAO = new CreditoMySQL();
//        //INSERTAR
//
//        // Crear un objeto Credito
//        Credito credito = new Credito(
//                "CRED123",
//                5000.00,
//                5.5,
//                new Date(), // Fecha de otorgamiento
//                cliente,
//                "Activo",
//                12 // Número de cuotas
//        );

        // Código del cliente al que se le asignará el crédito
//        String codigoCliente = cliente.getCodigoCliente(); // Asegúrate de que este código exista en la base de datos
        // Insertar el crédito
//        creditoDAO.insertar(credito, codigoCliente);
//        System.out.println("Credito insertado y asociado al cliente con codigo: " + codigoCliente);
        //eliminar
//        String numCredito = "CRED123";
//        creditoDAO.eliminar(numCredito);
//        System.out.println("Credito con numero " + numCredito + " ha sido eliminado.");
        //modificar
//        Credito credito = new Credito("CRED123", 5000.00, 3.5,
//                new Date(), null, "Activo", 12); // Asegúrate de que este crédito exista
        // Modificar el crédito
//        creditoDAO.modificar(credito);
//        System.out.println("Crédito con número " + credito.getNumCredito() + " ha sido modificado.");
        //listar
//        List<Credito> creditos = creditoDAO.listarTodos();
//        for (Credito credito : creditos) {
//            System.out.println("Numero de Credito: " + credito.getNumCredito());
//            System.out.println("Monto: " + credito.getMonto());
//            System.out.println("Tasa de Interes: " + credito.getTasaInteres());
//            System.out.println("Fecha de Otorgamiento: " + credito.getFechaOtorgamiento());
//            System.out.println("Estado: " + credito.getEstado());
//            System.out.println("Numero de Cuotas: " + credito.getNumCuotas());
//            System.out.println("------------------------------");
//        }
        //transaccion
//        TransaccionDAO transaccionDAO = new TransaccionMySQL();
        //Insertar
//        Transaccion transaccion = new Transaccion(
//                new Date(), // Fecha actual
//                "Pago mensual", // Concepto
//                100.0, // Monto
//                false, // Anulado
//                cliente, // Usuario registrado
//                "Agencia Central", // Agencia
//                1, // Número de operación
//                credito // Crédito asociado
//        );
//
//        boolean exito = transaccionDAO.insertar(transaccion);
//
//        if (exito) {
//            System.out.println("Transaccion insertada con exito.");
//        } else {
//            System.out.println("Error al insertar la transaccion.");
//        }
        //eliminar
//        int numTransaccion = 1;
//
//        boolean eliminado = transaccionDAO.eliminar(numTransaccion);
//        if (eliminado) {
//            System.out.println("La transaccion con numero " + numTransaccion + " fue eliminada exitosamente.");
//        } else {
//            System.out.println("No se encontró la transacción con número " + numTransaccion + " o no se pudo eliminar.");
//        }
        //modificar
//        Transaccion transaccion = new Transaccion(
//                new Date(), // Fecha
//                "Nuevo Concepto", // Concepto
//                1500.0, // Monto
//                false, // Anulado
//                cliente,
//        "Agencia Actualizada",              // Agencia
//        1,                              // Número de operación
//        credito
//    );
//    
//    boolean modificado = transaccionDAO.modificar(transaccion);
//        if (modificado) {
//            System.out.println("La transacción fue modificada exitosamente.");
//        } else {
//            System.out.println("No se pudo modificar la transacción.");
//        }
        //listar
//        List<Transaccion> transacciones = transaccionDAO.listarTodos();
        // Imprimir las transacciones
//    for (Transaccion transaccion : transacciones) {
//        System.out.println("Transacción #" + transaccion.getNumOperacion());
//        System.out.println("Fecha: " + transaccion.getFecha());
//        System.out.println("Concepto: " + transaccion.getConcepto());
//        System.out.println("Monto: " + transaccion.getMonto());
//        System.out.println("Anulado: " + transaccion.isAnulado());
//        System.out.println("Agencia: " + transaccion.getAgencia());
//        //System.out.println("Usuario ID: " + transaccion.getUsuarioRegistrado().getIdUsuario());
//        System.out.println("-------------------------------------");
//    }
        //FOTO
        //INSERTAR
        //ver lo de la foto, no se como se maneja
//        //SUPERVISOR
//        SupervisorDAO supervisorDAO = new SupervisorMySQL();
//        //INSERTAR
//        Supervisor supervisor = new Supervisor(
//                0, // ID de usuario (se generará automáticamente)
//                new Date(), // Fecha actual
//                "Carlos", // Nombre
//                "Pérez", // Apellido paterno
//                "López", // Apellido materno
//                "password123", // Contraseña
//                new Date(System.currentTimeMillis() + 86400000L * 365), // Fecha de vencimiento (1 año después)
//                true, // Activo
//                "SUP001", // Código de supervisor
//                101, // Código de cargo
//                "Agencia Central" // Agencia de asignación
//        );
//
//        // Insertar el supervisor en la base de datos
//        if (supervisorDAO.insertar(supervisor)) {
//            System.out.println("Supervisor insertado correctamente.");
//        } else {
//            System.out.println("Error al insertar el supervisor.");
//        }
        //eliminar
        //seguir similar a Usuario
        
        //EVALUACION
//        EvaluacionDAO daoEv = new EvaluacionMySQL();
//        Evaluacion ev = daoEv.obtenerPorId(14);
//        
//        System.out.println(ev.getClienteAsignado().getNombre() + " " +
//                ev.getClienteAsignado().getApPaterno() + " "+ev.getClienteAsignado().getApMaterno());
        //INSERTAR
        
    }

}
