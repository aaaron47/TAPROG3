/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pe.edu.pucp.creditomovil.model;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.XmlSeeAlso;
/**
 *
 * @author aaron
 */
@XmlType
public class MetodoPagoInstancia extends MetodoPago{
    public MetodoPagoInstancia(){};
    public MetodoPagoInstancia(int idMetodoPago, byte[] foto,
            String nombreTitular){
        super(idMetodoPago,foto,nombreTitular);
    }
}
