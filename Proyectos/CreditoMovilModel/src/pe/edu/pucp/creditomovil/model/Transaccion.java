/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.model;

import java.util.Date;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlElement;

@XmlRootElement
public class Transaccion {

    private Date fecha;
    private String concepto;
    private double monto;
    private boolean anulado;
    private Usuario usuarioRegistrado;
    private String agencia;
    private Credito credito;
    private int numOperacion;
    private byte[] foto;
    private MetodoPago metodoPago;

    public Transaccion(){}
    public Transaccion(Date fecha, String concepto, double monto,
            boolean anulado, Usuario usuarioRegistrado, String agencia,
            int numOperacion, Credito credito, byte[] foto) {
        this.fecha = fecha;
        this.concepto = concepto;
        this.monto = monto;
        this.anulado = anulado;
        this.usuarioRegistrado = usuarioRegistrado;
        this.agencia = agencia;
        this.numOperacion = numOperacion;
        this.credito = credito;
        this.foto = foto;
    }

    public void registrarTransaccion() {

    }

    public void anulaTransaccion() {

    }

//    public Transaccion encuentraTransaccion(){
//        
//    }
    public void imprimirVoucher() {

    }
@XmlElement
    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
@XmlElement
    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }
@XmlElement
    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }
@XmlElement
    public boolean isAnulado() {
        return anulado;
    }

    public void setAnulado(boolean anulado) {
        this.anulado = anulado;
    }
@XmlElement
    public Usuario getUsuarioRegistrado() {
        return usuarioRegistrado;
    }

    public void setUsuarioRegistrado(Usuario usuarioRegistrado) {
        this.usuarioRegistrado = usuarioRegistrado;
    }
@XmlElement
    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }
@XmlElement
    public int getNumOperacion() {
        return numOperacion;
    }

    public void setNumOperacion(int numOperacion) {
        this.numOperacion = numOperacion;
    }
@XmlElement
    public Credito getCredito() {
        return credito;
    }

    public void setCredito(Credito credito) {
        this.credito = credito;
    }
@XmlElement
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }
@XmlElement
    public MetodoPago getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(MetodoPago metodoPago) {
        this.metodoPago = metodoPago;
    }

}
