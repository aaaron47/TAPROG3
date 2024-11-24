/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.model;

/**
 *
 * @author aaron
 */
public class MetodoPagoInstancia extends MetodoPago{
    public MetodoPagoInstancia(){};
    public MetodoPagoInstancia(int idMetodoPago, byte[] foto,
            String nombreTitular){
        super(idMetodoPago,foto,nombreTitular);
    }
}
